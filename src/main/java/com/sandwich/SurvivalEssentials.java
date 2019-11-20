package com.sandwich;

import com.sandwich.Listener.Motd;
import com.sandwich.Listener.PlayerJoin;
import com.sandwich.commands.ClearChatCommand;
import com.sandwich.commands.InstallCommand;
import com.sandwich.commands.VanishCommand;
import com.sandwich.staffchat.ChatEvent;
import com.sandwich.staffchat.EnableSC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


/**
 * Main Class for the plugin
 *
 * @author Alexander Milanovich
 */


import java.util.logging.Logger;

public class SurvivalEssentials extends JavaPlugin {

    private static SurvivalEssentials instance;
    private ArrayList<String> ToggledStaff;
    Logger log;

    public SurvivalEssentials() {
        SurvivalEssentials.instance = this;
        this.log = Bukkit.getLogger();
        instance = this;
    }

    public String notPlayer = ChatColor.RED + "You must be a player to do this command!";
    public String noPermission = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("General.no-permission"));
    // public DB db = new DB(this);

    public Permission Fly = new Permission("se.fly");
    public Permission Vanish = new Permission("se.vanish");
    public Permission Admin = new Permission("se.admin");
    public Permission ClearChat = new Permission("se.clearchat");

    Permission[] permissions = {
            Fly, Vanish, Admin
    };


    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            for (Permission permission : permissions) {
                Bukkit.getServer().getPluginManager().addPermission(permission);
            }
            log.info(ChatColor.GREEN + "SurvivalEssentials is Enabled");
            getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
            getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
            getServer().getPluginManager().registerEvents(new Motd(this), this);
            getCommand("staffchat").setExecutor(new EnableSC(this));
            getCommand("vanish").setExecutor(new VanishCommand(this));
            getCommand("cc").setExecutor(new ClearChatCommand(this));
            getCommand("ccme").setExecutor(new ClearChatCommand(this));
            getCommand("install").setExecutor(new InstallCommand(this));
            getConfig().options().copyDefaults(true);
            saveConfig();



            this.ToggledStaff = new ArrayList();
            // databaseCon();
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
    }

    public void enableStaffChat(String player) {
        this.ToggledStaff.add(player);
    }


    public void disableStaffChat(String player) {
        this.ToggledStaff.remove(player);
    }


    public ArrayList<String> getToggledStaff() {
        return (ArrayList) this.ToggledStaff.clone();
    }


    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "SurvivalEssentials is Disabled");
    }

    //TODO create a method to detect if databse has been enabled in config, then attemp to connect to DB Depending on which is selected
     /*   public void databaseCon() {
            if (getConfig().getBoolean("database.enabled") == true) {
                if (getConfig().getString("database.database_type") == "MySQL") {
                    MySQL.connect();
                }
                if (getConfig().getString("database.database_type") == "MongoDB") {
                    MongoDB.connect(getConfig().getString("database.host"), getConfig().getInt("database.port"));
                }
            }
       } */

}