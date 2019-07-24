package com.sandwich;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class SEConfig {

    /*
        Thank you Christopher Nethercott (@chriscn) for this config class!
     */
    SurvivalEssentials plugin;
    FileConfiguration config;
    public SEConfig(SurvivalEssentials plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public SEConfig() {
        this.config = plugin.getConfig();
    }

    public boolean DEBUG = config.getBoolean("debug"); // public variable on whether debug is enabled.

    public String getAndColour(String configPath) {
        String configValue = config.getString(configPath);
        return ChatColor.translateAlternateColorCodes('&', configValue);
    }

    public boolean permissionCheck(Player player, CommandPermissions permissionNode) {
        return player.hasPermission(permissionNode.getPermissionGroup().getPermission());
    }

    public boolean permissionCheck(Player player, GroupPermissions permission) {
        return player.hasPermission(permission.getPermission());
    }

    public enum GroupPermissions {
        ADMIN("sc.admin"),
        STAFF("sc.staff"),
        DONATOR("sc.donator"),
        MEMBER("sc.member"),
        PLAYER("sc.player");

        private Permission permission;

        GroupPermissions(String permissionPath) {
            this.permission = new Permission(permissionPath);
        }

        public Permission getPermission() {
            return this.permission;
        }
    }

    public enum CommandPermissions {
        // these are hard coded paths to config
        FLY("features.fly.fly_permission"),
        GAMEMODE("features.gamemode.gamemode_permission"),
        CHAT("features.chat.chat_permission"),
        HELP("features.help.help_permission");

        private GroupPermissions permissionGroup;

        CommandPermissions(String configPath) {
            switch (new SEConfig().config.getString(configPath)) {
                case "admin":
                    this.permissionGroup = GroupPermissions.ADMIN;
                    break;
                case "staff":
                    this.permissionGroup = GroupPermissions.STAFF;
                    break;
                case "donator":
                    this.permissionGroup = GroupPermissions.DONATOR;
                    break;
                case "member":
                    this.permissionGroup = GroupPermissions.MEMBER;
                    break;
                case "player":
                    this.permissionGroup = GroupPermissions.PLAYER;
                    break;
                default:
                    Bukkit.getLogger().severe("Configuration Path invalid at config Path: " + configPath);
                    throw new IllegalArgumentException("Configuration Path invalid at config Path: " + configPath);
            }
        }

        public GroupPermissions getPermissionGroup() {
            return this.permissionGroup;
        }
    }
}