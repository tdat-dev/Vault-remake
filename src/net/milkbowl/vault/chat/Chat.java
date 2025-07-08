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
package net.milkbowl.vault.chat;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * The main Chat API - allows for prefix/suffix/group handling
 */
public abstract class Chat {

    private Permission perms;

    /**
     * Gets the permission handler this chat handler is using
     * @return Permission handler
     */
    public Permission getPermission() {
        return perms;
    }

    /**
     * Set the permission handler to use for this Chat handler
     * @param perms Permission handler
     */
    public void setPermission(Permission perms) {
        this.perms = perms;
    }

    /**
     * Checks if the permission system is enabled.  If not, it's safe to assume that there is no prefix/suffix/group data available
     * @return Success or Failure
     */
    public abstract boolean isEnabled();

    /**
     * Get name of permission method
     * @return Name of Permission Method
     */
    public abstract String getName();

    /**
     * Get players prefix
     * @param world World name
     * @param player Player name
     * @return Prefix
     */
    public abstract String getPlayerPrefix(String world, String player);

    /**
     * Get players prefix
     * @param world World Object
     * @param player Player name
     * @return Prefix
     */
    public String getPlayerPrefix(World world, String player) {
        if (world == null) {
            return getPlayerPrefix((String) null, player);
        }
        return getPlayerPrefix(world.getName(), player);
    }

    /**
     * Get a players prefix in the players current world
     * @param player Player Object
     * @return Prefix
     */
    public String getPlayerPrefix(Player player) {
        if (player == null) {
            return null;
        }
        return getPlayerPrefix(player.getWorld().getName(), player.getName());
    }

    /**
     * Get players prefix
     * @param world World name
     * @param player OfflinePlayer
     * @return Prefix
     */
    public String getPlayerPrefix(String world, OfflinePlayer player) {
        if (player == null) {
            return null;
        }
        return getPlayerPrefix(world, player.getName());
    }

    /**
     * Get a players prefix in the players current world
     * @param player OfflinePlayer Object
     * @return Prefix
     */
    public String getPlayerPrefix(OfflinePlayer player) {
        if (player == null) {
            return null;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerPrefix((String) null, player.getName());
        }
        return getPlayerPrefix(p.getWorld().getName(), player.getName());
    }

    /**
     * Set players prefix
     * @param world World name
     * @param player Player name
     * @param prefix Prefix
     */
    public abstract void setPlayerPrefix(String world, String player, String prefix);

    /**
     * Set players prefix
     * @param world World Object
     * @param player Player name
     * @param prefix Prefix
     */
    public void setPlayerPrefix(World world, String player, String prefix) {
        if (world == null) {
            setPlayerPrefix((String) null, player, prefix);
        } else {
            setPlayerPrefix(world.getName(), player, prefix);
        }
    }

    /**
     * Set players prefix
     * @param player Player Object
     * @param prefix Prefix
     */
    public void setPlayerPrefix(Player player, String prefix) {
        if (player == null) {
            return;
        }
        setPlayerPrefix(player.getWorld().getName(), player.getName(), prefix);
    }

    /**
     * Set players prefix
     * @param world World name
     * @param player OfflinePlayer Object
     * @param prefix Prefix
     */
    public void setPlayerPrefix(String world, OfflinePlayer player, String prefix) {
        if (player == null) {
            return;
        }
        setPlayerPrefix(world, player.getName(), prefix);
    }

    /**
     * Set players prefix
     * @param player OfflinePlayer Object
     * @param prefix Prefix
     */
    public void setPlayerPrefix(OfflinePlayer player, String prefix) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerPrefix((String) null, player.getName(), prefix);
            return;
        }
        setPlayerPrefix(p.getWorld().getName(), player.getName(), prefix);
    }

    /**
     * Get players suffix
     * @param world World name
     * @param player Player name
     * @return Suffix
     */
    public abstract String getPlayerSuffix(String world, String player);

    /**
     * Get players suffix
     * @param world World Object
     * @param player Player name
     * @return Suffix
     */
    public String getPlayerSuffix(World world, String player) {
        if (world == null) {
            return getPlayerSuffix((String) null, player);
        }
        return getPlayerSuffix(world.getName(), player);
    }

    /**
     * Get players suffix in players current world
     * @param player Player Object
     * @return Suffix
     */
    public String getPlayerSuffix(Player player) {
        if (player == null) {
            return null;
        }
        return getPlayerSuffix(player.getWorld().getName(), player.getName());
    }

    /**
     * Get players suffix
     * @param world World name
     * @param player OfflinePlayer Object
     * @return Suffix
     */
    public String getPlayerSuffix(String world, OfflinePlayer player) {
        if (player == null) {
            return null;
        }
        return getPlayerSuffix(world, player.getName());
    }

    /**
     * Get players suffix in players current world
     * @param player OfflinePlayer Object
     * @return Suffix
     */
    public String getPlayerSuffix(OfflinePlayer player) {
        if (player == null) {
            return null;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerSuffix((String) null, player.getName());
        }
        return getPlayerSuffix(p.getWorld().getName(), player.getName());
    }

    /**
     * Set players suffix
     * @param world World name
     * @param player Player name
     * @param suffix Suffix
     */
    public abstract void setPlayerSuffix(String world, String player, String suffix);

    /**
     * Set players suffix
     * @param world World Object
     * @param player Player name
     * @param suffix Suffix
     */
    public void setPlayerSuffix(World world, String player, String suffix) {
        if (world == null) {
            setPlayerSuffix((String) null, player, suffix);
        } else {
            setPlayerSuffix(world.getName(), player, suffix);
        }
    }

    /**
     * Set players suffix
     * @param player Player Object
     * @param suffix Suffix
     */
    public void setPlayerSuffix(Player player, String suffix) {
        if (player == null) {
            return;
        }
        setPlayerSuffix(player.getWorld().getName(), player.getName(), suffix);
    }

    /**
     * Set players suffix
     * @param world World name
     * @param player OfflinePlayer Object
     * @param suffix Suffix
     */
    public void setPlayerSuffix(String world, OfflinePlayer player, String suffix) {
        if (player == null) {
            return;
        }
        setPlayerSuffix(world, player.getName(), suffix);
    }

    /**
     * Set players suffix
     * @param player OfflinePlayer Object
     * @param suffix Suffix
     */
    public void setPlayerSuffix(OfflinePlayer player, String suffix) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerSuffix((String) null, player.getName(), suffix);
            return;
        }
        setPlayerSuffix(p.getWorld().getName(), player.getName(), suffix);
    }

    /**
     * Get group prefix
     * @param world World name
     * @param group Group name
     * @return Prefix
     */
    public abstract String getGroupPrefix(String world, String group);

    /**
     * Get group prefix
     * @param world World Object
     * @param group Group name
     * @return Prefix
     */
    public String getGroupPrefix(World world, String group) {
        if (world == null) {
            return getGroupPrefix((String) null, group);
        }
        return getGroupPrefix(world.getName(), group);
    }

    /**
     * Set group prefix
     * @param world World name
     * @param group Group name
     * @param prefix Prefix
     */
    public abstract void setGroupPrefix(String world, String group, String prefix);

    /**
     * Set group prefix
     * @param world World Object
     * @param group Group name
     * @param prefix Prefix
     */
    public void setGroupPrefix(World world, String group, String prefix) {
        if (world == null) {
            setGroupPrefix((String) null, group, prefix);
        } else {
            setGroupPrefix(world.getName(), group, prefix);
        }
    }

    /**
     * Get group suffix
     * @param world World name
     * @param group Group name
     * @return Suffix
     */
    public abstract String getGroupSuffix(String world, String group);

    /**
     * Get group suffix
     * @param world World Object
     * @param group Group name
     * @return Suffix
     */
    public String getGroupSuffix(World world, String group) {
        if (world == null) {
            return getGroupSuffix((String) null, group);
        }
        return getGroupSuffix(world.getName(), group);
    }

    /**
     * Set group suffix
     * @param world World name
     * @param group Group name
     * @param suffix Suffix
     */
    public abstract void setGroupSuffix(String world, String group, String suffix);

    /**
     * Set group suffix
     * @param world World Object
     * @param group Group name
     * @param suffix Suffix
     */
    public void setGroupSuffix(World world, String group, String suffix) {
        if (world == null) {
            setGroupSuffix((String) null, group, suffix);
        } else {
            setGroupSuffix(world.getName(), group, suffix);
        }
    }

    /**
     * Get a players informational node (Integer) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract int getPlayerInfoInteger(String world, String player, String node, int defaultValue);

    /**
     * Get a players informational node (Integer) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(World world, String player, String node, int defaultValue) {
        if (world == null) {
            return getPlayerInfoInteger((String) null, player, node, defaultValue);
        }
        return getPlayerInfoInteger(world.getName(), player, node, defaultValue);
    }

    /**
     * Get a players informational node (Integer) value
     * @param player Player Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(Player player, String node, int defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoInteger(player.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Integer) value
     * @param world World name
     * @param player OfflinePlayer name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(String world, OfflinePlayer player, String node, int defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoInteger(world, player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Integer) value in players current world
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(OfflinePlayer player, String node, int defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerInfoInteger((String) null, player.getName(), node, defaultValue);
        }
        return getPlayerInfoInteger(p.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Set a players informational node (Integer) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setPlayerInfoInteger(String world, String player, String node, int value);

    /**
     * Set a players informational node (Integer) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoInteger(World world, String player, String node, int value) {
        if (world == null) {
            setPlayerInfoInteger((String) null, player, node, value);
        } else {
            setPlayerInfoInteger(world.getName(), player, node, value);
        }
    }

    /**
     * Set a players informational node (Integer) value
     * @param player Player Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoInteger(Player player, String node, int value) {
        if (player == null) {
            return;
        }
        setPlayerInfoInteger(player.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Set a players informational node (Integer) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoInteger(String world, OfflinePlayer player, String node, int value) {
        if (player == null) {
            return;
        }
        setPlayerInfoInteger(world, player.getName(), node, value);
    }

    /**
     * Set a players informational node (Integer) value
     * @param player Player Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoInteger(OfflinePlayer player, String node, int value) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerInfoInteger((String) null, player.getName(), node, value);
            return;
        }
        setPlayerInfoInteger(p.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Get a groups informational node (Integer) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract int getGroupInfoInteger(String world, String group, String node, int defaultValue);

    /**
     * Get a groups informational node (Integer) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public int getGroupInfoInteger(World world, String group, String node, int defaultValue) {
        if (world == null) {
            return getGroupInfoInteger((String) null, group, node, defaultValue);
        }
        return getGroupInfoInteger(world.getName(), group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Integer) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setGroupInfoInteger(String world, String group, String node, int value);

    /**
     * Set a groups informational node (Integer) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public void setGroupInfoInteger(World world, String group, String node, int value) {
        if (world == null) {
            setGroupInfoInteger((String) null, group, node, value);
        } else {
            setGroupInfoInteger(world.getName(), group, node, value);
        }
    }

    /**
     * Get a players informational node (Double) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract double getPlayerInfoDouble(String world, String player, String node, double defaultValue);

    /**
     * Get a players informational node (Double) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(World world, String player, String node, double defaultValue) {
        if (world == null) {
            return getPlayerInfoDouble((String) null, player, node, defaultValue);
        }
        return getPlayerInfoDouble(world.getName(), player, node, defaultValue);
    }

    /**
     * Get a players informational node (Double) value
     * @param player Player Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(Player player, String node, double defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoDouble(player.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Double) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(String world, OfflinePlayer player, String node, double defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoDouble(world, player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Double) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(OfflinePlayer player, String node, double defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerInfoDouble((String) null, player.getName(), node, defaultValue);
        }
        return getPlayerInfoDouble(p.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Set a players informational node (Double) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setPlayerInfoDouble(String world, String player, String node, double value);

    /**
     * Set a players informational node (Double) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoDouble(World world, String player, String node, double value) {
        if (world == null) {
            setPlayerInfoDouble((String) null, player, node, value);
        } else {
            setPlayerInfoDouble(world.getName(), player, node, value);
        }
    }

    /**
     * Set a players informational node (Double) value
     * @param player Player Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoDouble(Player player, String node, double value) {
        if (player == null) {
            return;
        }
        setPlayerInfoDouble(player.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Set a players informational node (Double) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoDouble(String world, OfflinePlayer player, String node, double value) {
        if (player == null) {
            return;
        }
        setPlayerInfoDouble(world, player.getName(), node, value);
    }

    /**
     * Set a players informational node (Double) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoDouble(OfflinePlayer player, String node, double value) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerInfoDouble((String) null, player.getName(), node, value);
            return;
        }
        setPlayerInfoDouble(p.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Get a groups informational node (Double) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract double getGroupInfoDouble(String world, String group, String node, double defaultValue);

    /**
     * Get a groups informational node (Double) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public double getGroupInfoDouble(World world, String group, String node, double defaultValue) {
        if (world == null) {
            return getGroupInfoDouble((String) null, group, node, defaultValue);
        }
        return getGroupInfoDouble(world.getName(), group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Double) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setGroupInfoDouble(String world, String group, String node, double value);

    /**
     * Set a groups informational node (Double) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public void setGroupInfoDouble(World world, String group, String node, double value) {
        if (world == null) {
            setGroupInfoDouble((String) null, group, node, value);
        } else {
            setGroupInfoDouble(world.getName(), group, node, value);
        }
    }

    /**
     * Get a players informational node (Boolean) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract boolean getPlayerInfoBoolean(String world, String player, String node, boolean defaultValue);

    /**
     * Get a players informational node (Boolean) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(World world, String player, String node, boolean defaultValue) {
        if (world == null) {
            return getPlayerInfoBoolean((String) null, player, node, defaultValue);
        }
        return getPlayerInfoBoolean(world.getName(), player, node, defaultValue);
    }

    /**
     * Get a players informational node (Boolean) value
     * @param player Player Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(Player player, String node, boolean defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoBoolean(player.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Boolean) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(String world, OfflinePlayer player, String node, boolean defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoBoolean(world, player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (Boolean) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(OfflinePlayer player, String node, boolean defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerInfoBoolean((String) null, player.getName(), node, defaultValue);
        }
        return getPlayerInfoBoolean(p.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Set a players informational node (Boolean) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setPlayerInfoBoolean(String world, String player, String node, boolean value);

    /**
     * Set a players informational node (Boolean) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(World world, String player, String node, boolean value) {
        if (world == null) {
            setPlayerInfoBoolean((String) null, player, node, value);
        } else {
            setPlayerInfoBoolean(world.getName(), player, node, value);
        }
    }

    /**
     * Set a players informational node (Boolean) value
     * @param player Player Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(Player player, String node, boolean value) {
        if (player == null) {
            return;
        }
        setPlayerInfoBoolean(player.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Set a players informational node (Boolean) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(String world, OfflinePlayer player, String node, boolean value) {
        if (player == null) {
            return;
        }
        setPlayerInfoBoolean(world, player.getName(), node, value);
    }

    /**
     * Set a players informational node (Boolean) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(OfflinePlayer player, String node, boolean value) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerInfoBoolean((String) null, player.getName(), node, value);
            return;
        }
        setPlayerInfoBoolean(p.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Get a groups informational node (Boolean) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract boolean getGroupInfoBoolean(String world, String group, String node, boolean defaultValue);

    /**
     * Get a groups informational node (Boolean) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getGroupInfoBoolean(World world, String group, String node, boolean defaultValue) {
        if (world == null) {
            return getGroupInfoBoolean((String) null, group, node, defaultValue);
        }
        return getGroupInfoBoolean(world.getName(), group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Boolean) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setGroupInfoBoolean(String world, String group, String node, boolean value);

    /**
     * Set a groups informational node (Boolean) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public void setGroupInfoBoolean(World world, String group, String node, boolean value) {
        if (world == null) {
            setGroupInfoBoolean((String) null, group, node, value);
        } else {
            setGroupInfoBoolean(world.getName(), group, node, value);
        }
    }

    /**
     * Get a players informational node (String) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract String getPlayerInfoString(String world, String player, String node, String defaultValue);

    /**
     * Get a players informational node (String) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(World world, String player, String node, String defaultValue) {
        if (world == null) {
            return getPlayerInfoString((String) null, player, node, defaultValue);
        }
        return getPlayerInfoString(world.getName(), player, node, defaultValue);
    }

    /**
     * Get a players informational node (String) value
     * @param player Player Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(Player player, String node, String defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoString(player.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (String) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(String world, OfflinePlayer player, String node, String defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        return getPlayerInfoString(world, player.getName(), node, defaultValue);
    }

    /**
     * Get a players informational node (String) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(OfflinePlayer player, String node, String defaultValue) {
        if (player == null) {
            return defaultValue;
        }
        Player p = player.getPlayer();
        if (p == null) {
            return getPlayerInfoString((String) null, player.getName(), node, defaultValue);
        }
        return getPlayerInfoString(p.getWorld().getName(), player.getName(), node, defaultValue);
    }

    /**
     * Set a players informational node (String) value
     * @param world World name
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setPlayerInfoString(String world, String player, String node, String value);

    /**
     * Set a players informational node (String) value
     * @param world World Object
     * @param player Player name
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoString(World world, String player, String node, String value) {
        if (world == null) {
            setPlayerInfoString((String) null, player, node, value);
        } else {
            setPlayerInfoString(world.getName(), player, node, value);
        }
    }

    /**
     * Set a players informational node (String) value
     * @param player Player Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoString(Player player, String node, String value) {
        if (player == null) {
            return;
        }
        setPlayerInfoString(player.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Set a players informational node (String) value
     * @param world World name
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoString(String world, OfflinePlayer player, String node, String value) {
        if (player == null) {
            return;
        }
        setPlayerInfoString(world, player.getName(), node, value);
    }

    /**
     * Set a players informational node (String) value
     * @param player OfflinePlayer Object
     * @param node Node name
     * @param value Value to set
     */
    public void setPlayerInfoString(OfflinePlayer player, String node, String value) {
        if (player == null) {
            return;
        }
        Player p = player.getPlayer();
        if (p == null) {
            setPlayerInfoString((String) null, player.getName(), node, value);
            return;
        }
        setPlayerInfoString(p.getWorld().getName(), player.getName(), node, value);
    }

    /**
     * Get a groups informational node (String) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public abstract String getGroupInfoString(String world, String group, String node, String defaultValue);

    /**
     * Get a groups informational node (String) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param defaultValue Default value
     * @return Value
     */
    public String getGroupInfoString(World world, String group, String node, String defaultValue) {
        if (world == null) {
            return getGroupInfoString((String) null, group, node, defaultValue);
        }
        return getGroupInfoString(world.getName(), group, node, defaultValue);
    }

    /**
     * Set a groups informational node (String) value
     * @param world World name
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public abstract void setGroupInfoString(String world, String group, String node, String value);

    /**
     * Set a groups informational node (String) value
     * @param world World Object
     * @param group Group name
     * @param node Node name
     * @param value Value to set
     */
    public void setGroupInfoString(World world, String group, String node, String value) {
        if (world == null) {
            setGroupInfoString((String) null, group, node, value);
        } else {
            setGroupInfoString(world.getName(), group, node, value);
        }
    }
}
