package com.sandwich;

import com.sandwich.Listener.PlayerJoin;
import com.sandwich.staffchat.ChatEvent;
import com.sandwich.staffchat.EnableSC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Main Class for the plugin
 *
 * @author Alexander Milanovich
 */

import java.util.ArrayList;
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




    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            log.info(ChatColor.GREEN + "SurvivalEssentials is Enabled");
            getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
            getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
            getCommand("staffchat").setExecutor(new EnableSC(this));
            getConfig().options().copyDefaults(true);
            saveConfig();
            this.ToggledStaff = new ArrayList();
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
        return (ArrayList)this.ToggledStaff.clone();
    }



    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "SurvivalEssentials is Disabled");
    }


}
