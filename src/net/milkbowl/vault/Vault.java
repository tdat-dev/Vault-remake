/* This file is part of Vault.

    Vault is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Vault is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Vault.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.milkbowl.vault;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.milkbowl.vault.permission.plugins.Permission_SuperPerms;
import net.milkbowl.vault.permission.plugins.Permission_LuckPerms;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Vault extends JavaPlugin {

    private static final String VAULT_BUKKIT_URL = "https://github.com/tdat-dev/Vault-remake/releases/latest";
    private static Logger log;
    private Permission perms;
    private String newVersionTitle = "";
    private double newVersion = 0;
    private double currentVersion = 0;
    private String currentVersionTitle = "";
    private ServicesManager sm;
    private Vault plugin;

    @Override
    public void onDisable() {
        // Remove all Service Registrations
        getServer().getServicesManager().unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public void onEnable() {
        plugin = this;
        log = this.getLogger();
        currentVersionTitle = getDescription().getVersion().split("-")[0];
        currentVersion = Double.valueOf(currentVersionTitle.replaceFirst("\\.", ""));
        sm = getServer().getServicesManager();
        // set defaults
        getConfig().addDefault("update-check", true);
        getConfig().options().copyDefaults(true);
        saveConfig();
        // Load Vault Addons
        loadPermission();

        getCommand("vault-info").setExecutor(this);
        getCommand("vault-convert").setExecutor(this);
        getServer().getPluginManager().registerEvents(new VaultListener(), this);
        // Schedule to check the version every 30 minutes for an update. This is to
        // update the most recent
        // version so if an admin reconnects they will be warned about newer versions.
        this.getServer().getScheduler().runTask(this, new Runnable() {

            @Override
            public void run() {
                // Programmatically set the default permission value cause Bukkit doesn't handle
                // plugin.yml properly for Load order STARTUP plugins
                org.bukkit.permissions.Permission perm = getServer().getPluginManager().getPermission("vault.update");
                if (perm == null) {
                    perm = new org.bukkit.permissions.Permission("vault.update");
                    perm.setDefault(PermissionDefault.OP);
                    plugin.getServer().getPluginManager().addPermission(perm);
                }
                perm.setDescription("Allows a user or the console to check for vault updates");

                getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {

                    @Override
                    public void run() {
                        if (getServer().getConsoleSender().hasPermission("vault.update")
                                && getConfig().getBoolean("update-check", true)) {
                            try {
                                log.info("Checking for Updates ... ");
                                newVersion = updateCheck(currentVersion);
                                if (newVersion > currentVersion) {
                                    log.warning("Phiên bản mới: " + newVersionTitle + " đã có!"
                                            + " Bạn đang dùng phiên bản: " + currentVersionTitle);
                                    log.warning("Cập nhật tại: https://github.com/tdat-dev/Vault-remake");
                                } else if (currentVersion > newVersion) {
                                    log.info("Phiên bản mới: " + newVersionTitle + " | Phiên bản hiện tại: "
                                            + currentVersionTitle);
                                }
                            } catch (Exception e) {
                                // ignore exceptions
                            }
                        }
                    }
                }, 0, 432000);

            }
        });

        // Load up the Plugin metrics
        Metrics metrics = new Metrics(this, 887);
        findCustomData(metrics);

        log.info(String.format("Enabled Version %s", getDescription().getVersion()));
    }

    /**
     * Attempts to load Permission Addons
     */
    private void loadPermission() {
        // Try to load Starburst
        hookPermission("Starburst", Permission_SuperPerms.class, ServicePriority.Highest,
                "com.dthielke.starburst.StarburstPlugin");

        // Try to load PermissionsEx
        hookPermission("PermissionsEx", Permission_SuperPerms.class, ServicePriority.Highest,
                "ru.tehkode.permissions.bukkit.PermissionsEx");

        // Try to load OverPermissions
        hookPermission("OverPermissions", Permission_SuperPerms.class, ServicePriority.Highest,
                "com.overmc.overpermissions.internal.OverPermissions");

        // Try to load PermissionsBukkit
        hookPermission("PermissionsBukkit", Permission_SuperPerms.class, ServicePriority.Normal,
                "com.platymuus.bukkit.permissions.PermissionsPlugin");

        // Try to load DroxPerms
        hookPermission("DroxPerms", Permission_SuperPerms.class, ServicePriority.High,
                "de.hydrox.bukkit.DroxPerms.DroxPerms");

        // Try to load SimplyPerms
        hookPermission("SimplyPerms", Permission_SuperPerms.class, ServicePriority.Highest,
                "net.crystalyx.bukkit.simplyperms.SimplyPlugin");

        // Try to load bPermissions2
        hookPermission("bPermissions 2", Permission_SuperPerms.class, ServicePriority.Highest,
                "de.bananaco.bpermissions.api.WorldManager");

        // Try to load Privileges
        hookPermission("Privileges", Permission_SuperPerms.class, ServicePriority.Highest,
                "net.krinsoft.privileges.Privileges");

        // Try to load bPermissions
        hookPermission("bPermissions", Permission_SuperPerms.class, ServicePriority.High,
                "de.bananaco.permissions.SuperPermissionHandler");

        // Try to load GroupManager
        hookPermission("GroupManager", Permission_SuperPerms.class, ServicePriority.High,
                "org.anjocaido.groupmanager.GroupManager");

        // Try to load Permissions 3 (Yeti)
        hookPermission("Permissions 3 (Yeti)", Permission_SuperPerms.class, ServicePriority.Normal,
                "com.nijiko.permissions.ModularControl");

        // Try to load Xperms
        hookPermission("Xperms", Permission_SuperPerms.class, ServicePriority.Low, "com.github.sebc722.Xperms");

        // Try to load TotalPermissions
        hookPermission("TotalPermissions", Permission_SuperPerms.class, ServicePriority.Normal,
                "net.ae97.totalpermissions.TotalPermissions");

        // Try to load rscPermissions
        hookPermission("rscPermissions", Permission_SuperPerms.class, ServicePriority.Normal,
                "ru.simsonic.rscPermissions.MainPluginClass");

        // Try to load KPerms
        hookPermission("KPerms", Permission_SuperPerms.class, ServicePriority.Normal,
                "com.lightniinja.kperms.KPermsPlugin");

        // Try to load LuckPerms
        hookPermission("LuckPerms", Permission_LuckPerms.class, ServicePriority.Highest,
                "me.lucko.luckperms.bukkit.LuckPermsPlugin");

        Permission perms = new Permission_SuperPerms(this);
        sm.register(Permission.class, perms, this, ServicePriority.Lowest);
        log.info(String.format("[Permission] SuperPermissions loaded as backup permission system."));

        this.perms = sm.getRegistration(Permission.class).getProvider();
    }

    private void hookPermission(String name, Class<? extends Permission> hookClass, ServicePriority priority,
            String... packages) {
        try {
            if (packagesExists(packages)) {
                Permission perms = hookClass.getConstructor(Plugin.class).newInstance(this);
                sm.register(Permission.class, perms, this, priority);
                log.info(String.format("[Permission] %s found: %s", name, perms.isEnabled() ? "Loaded" : "Waiting"));
            }
        } catch (Exception e) {
            log.severe(String.format(
                    "[Permission] There was an error hooking %s - check to make sure you're using a compatible version!",
                    name));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (!sender.hasPermission("vault.admin")) {
            sender.sendMessage("You do not have permission to use that command!");
            return true;
        }

        if (command.getName().equalsIgnoreCase("vault-info")) {
            infoCommand(sender);
            return true;
        } else if (command.getName().equalsIgnoreCase("vault-convert")) {
            convertCommand(sender, args);
            return true;
        } else {
            // Show help
            sender.sendMessage("Vault Commands:");
            sender.sendMessage("  /vault-info - Displays information about Vault");
            sender.sendMessage("  /vault-convert [economy1] [economy2] - Converts from one Economy to another");
            return true;
        }
    }

    private void convertCommand(CommandSender sender, String[] args) {
        Collection<RegisteredServiceProvider<Economy>> econs = this.getServer().getServicesManager()
                .getRegistrations(Economy.class);
        if (econs == null || econs.size() < 2) {
            sender.sendMessage("You must have at least 2 economies loaded to convert.");
            return;
        } else if (args.length != 2) {
            sender.sendMessage(
                    "You must specify only the economy to convert from and the economy to convert to. (names should not contain spaces)");
            return;
        }
        Economy econ1 = null;
        Economy econ2 = null;
        String economies = "";
        for (RegisteredServiceProvider<Economy> econ : econs) {
            String econName = econ.getProvider().getName().replace(" ", "");
            if (econName.equalsIgnoreCase(args[0])) {
                econ1 = econ.getProvider();
            } else if (econName.equalsIgnoreCase(args[1])) {
                econ2 = econ.getProvider();
            }
            if (economies.length() > 0) {
                economies += ", ";
            }
            economies += econName;
        }

        if (econ1 == null) {
            sender.sendMessage("Could not find " + args[0] + " loaded on the server, check your spelling.");
            sender.sendMessage("Valid economies are: " + economies);
            return;
        } else if (econ2 == null) {
            sender.sendMessage("Could not find " + args[1] + " loaded on the server, check your spelling.");
            sender.sendMessage("Valid economies are: " + economies);
            return;
        }

        sender.sendMessage("This may take some time to convert, expect server lag.");
        for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
            if (econ1.hasAccount(op)) {
                if (econ2.hasAccount(op)) {
                    continue;
                }
                econ2.createPlayerAccount(op);
                double diff = econ1.getBalance(op) - econ2.getBalance(op);
                if (diff > 0) {
                    econ2.depositPlayer(op, diff);
                } else if (diff < 0) {
                    econ2.withdrawPlayer(op, -diff);
                }

            }
        }
        sender.sendMessage("Converson complete, please verify the data before using it.");
    }

    private void infoCommand(CommandSender sender) {
        // Get String of Registered Economy Services
        String registeredEcons = null;
        Collection<RegisteredServiceProvider<Economy>> econs = this.getServer().getServicesManager()
                .getRegistrations(Economy.class);
        for (RegisteredServiceProvider<Economy> econ : econs) {
            Economy e = econ.getProvider();
            if (registeredEcons == null) {
                registeredEcons = e.getName();
            } else {
                registeredEcons += ", " + e.getName();
            }
        }

        // Get String of Registered Permission Services
        String registeredPerms = null;
        Collection<RegisteredServiceProvider<Permission>> perms = this.getServer().getServicesManager()
                .getRegistrations(Permission.class);
        for (RegisteredServiceProvider<Permission> perm : perms) {
            Permission p = perm.getProvider();
            if (registeredPerms == null) {
                registeredPerms = p.getName();
            } else {
                registeredPerms += ", " + p.getName();
            }
        }

        // Get Economy & Permission primary Services
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        Economy econ = null;
        if (rsp != null) {
            econ = rsp.getProvider();
        }
        Permission perm = null;
        RegisteredServiceProvider<Permission> rspp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rspp != null) {
            perm = rspp.getProvider();
        }
        // Send user some info!
        sender.sendMessage(
                String.format("[%s] Vault v%s Information", getDescription().getName(), getDescription().getVersion()));
        sender.sendMessage(String.format("[%s] Economy: %s [%s]", getDescription().getName(),
                econ == null ? "None" : econ.getName(), registeredEcons));
        sender.sendMessage(String.format("[%s] Permission: %s [%s]", getDescription().getName(),
                perm == null ? "None" : perm.getName(), registeredPerms));
    }

    /**
     * Determines if all packages in a String array are within the Classpath
     * This is the best way to determine if a specific plugin exists and will be
     * loaded. If the plugin package isn't loaded, we shouldn't bother waiting
     * for it!
     * 
     * @param packages String Array of package names to check
     * @return Success or Failure
     */
    private static boolean packagesExists(String... packages) {
        try {
            for (String pkg : packages) {
                Class.forName(pkg);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public double updateCheck(double currentVersion) {
        try {
            URL url = new URL("https://api.curseforge.com/servermods/files?projectids=33184");
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("User-Agent", "Vault Update Checker");
            conn.setDoOutput(true);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final String response = reader.readLine();
            final JSONArray array = (JSONArray) JSONValue.parse(response);

            if (array.size() == 0) {
                this.getLogger().warning("No files found, or Feed URL is bad.");
                return currentVersion;
            }
            // Pull the last version from the JSON
            newVersionTitle = ((String) ((JSONObject) array.get(array.size() - 1)).get("name")).replace("Vault", "")
                    .trim();
            return Double.valueOf(newVersionTitle.replaceFirst("\\.", "").trim());
        } catch (Exception e) {
            log.info("There was an issue attempting to check for the latest version.");
        }
        return currentVersion;
    }

    private void findCustomData(Metrics metrics) {
        // Create our Economy Graph and Add our Economy plotters
        RegisteredServiceProvider<Economy> rspEcon = Bukkit.getServer().getServicesManager()
                .getRegistration(Economy.class);
        Economy econ = null;
        if (rspEcon != null) {
            econ = rspEcon.getProvider();
        }
        final String econName = econ != null ? econ.getName() : "No Economy";
        metrics.addCustomChart(new SimplePie("economy", new Callable<String>() {
            @Override
            public String call() {
                return econName;
            }
        }));

        // Create our Permission Graph and Add our permission Plotters
        final String permName = Bukkit.getServer().getServicesManager().getRegistration(Permission.class).getProvider()
                .getName();
        metrics.addCustomChart(new SimplePie("permission", new Callable<String>() {
            @Override
            public String call() {
                return permName;
            }
        }));
    }

    public class VaultListener implements Listener {

        @EventHandler(priority = EventPriority.MONITOR)
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            if (perms.has(player, "vault.update")) {
                try {
                    if (newVersion > currentVersion) {
                        player.sendMessage(
                                "Vault " + newVersionTitle + " is out! You are running " + currentVersionTitle);
                        player.sendMessage("Update Vault at: " + VAULT_BUKKIT_URL);
                    }
                } catch (Exception e) {
                    // Ignore exceptions
                }
            }
        }

    }
}
