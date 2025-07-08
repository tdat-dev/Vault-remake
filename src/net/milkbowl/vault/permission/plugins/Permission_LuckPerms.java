package net.milkbowl.vault.permission.plugins;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import java.util.List;
import java.util.stream.Collectors;

public class Permission_LuckPerms extends Permission {
    private LuckPerms luckPerms;

    public Permission_LuckPerms() {
        super();
        try {
            this.luckPerms = net.luckperms.api.LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            this.luckPerms = null;
        }
    }

    @Override
    public String getName() {
        return "LuckPerms";
    }

    @Override
    public boolean isEnabled() {
        return luckPerms != null;
    }

    @Override
    public boolean hasSuperPermsCompat() {
        return true;
    }

    @Override
    public boolean playerHas(String world, String player, String permission) {
        if (luckPerms == null)
            return false;
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return false;
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    @Override
    public boolean playerAdd(String world, String player, String permission) {
        if (luckPerms == null)
            return false;
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return false;
        Node node = Node.builder(permission).build();
        user.data().add(node);
        luckPerms.getUserManager().saveUser(user);
        return true;
    }

    @Override
    public boolean playerRemove(String world, String player, String permission) {
        if (luckPerms == null)
            return false;
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return false;
        Node node = Node.builder(permission).build();
        user.data().remove(node);
        luckPerms.getUserManager().saveUser(user);
        return true;
    }

    @Override
    public boolean groupHas(String world, String group, String permission) {
        if (luckPerms == null)
            return false;
        Group g = luckPerms.getGroupManager().getGroup(group);
        if (g == null)
            return false;
        return g.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    @Override
    public boolean groupAdd(String world, String group, String permission) {
        if (luckPerms == null)
            return false;
        Group g = luckPerms.getGroupManager().getGroup(group);
        if (g == null)
            return false;
        Node node = Node.builder(permission).build();
        g.data().add(node);
        luckPerms.getGroupManager().saveGroup(g);
        return true;
    }

    @Override
    public boolean groupRemove(String world, String group, String permission) {
        if (luckPerms == null)
            return false;
        Group g = luckPerms.getGroupManager().getGroup(group);
        if (g == null)
            return false;
        Node node = Node.builder(permission).build();
        g.data().remove(node);
        luckPerms.getGroupManager().saveGroup(g);
        return true;
    }

    @Override
    public String[] getPlayerGroups(String world, String player) {
        if (luckPerms == null)
            return new String[0];
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return new String[0];
        List<String> groups = user.getNodes().stream()
                .filter(node -> node instanceof InheritanceNode)
                .map(node -> ((InheritanceNode) node).getGroupName())
                .collect(Collectors.toList());
        return groups.toArray(new String[0]);
    }

    @Override
    public String getPrimaryGroup(String world, String player) {
        if (luckPerms == null)
            return null;
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return null;
        return user.getPrimaryGroup();
    }

    @Override
    public boolean playerInGroup(String world, String player, String group) {
        if (luckPerms == null)
            return false;
        User user = luckPerms.getUserManager().getUser(player);
        if (user == null)
            return false;
        return user.getPrimaryGroup().equalsIgnoreCase(group);
    }

    @Override
    public boolean hasGroupSupport() {
        return true;
    }

    @Override
    public String[] getGroups() {
        return new String[0];
    }

    @Override
    public boolean playerAddGroup(String world, String player, String group) {
        // Implement nếu cần, hoặc trả về false nếu không hỗ trợ
        return false;
    }

    @Override
    public boolean playerRemoveGroup(String world, String player, String group) {
        // Implement nếu cần, hoặc trả về false nếu không hỗ trợ
        return false;
    }
}