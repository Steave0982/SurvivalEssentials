package com.sandwich;

import com.sandwich.staffchat.ChatEvent;
import com.sandwich.staffchat.EnableSC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

    public SurvivalEssentials() {
        SurvivalEssentials.instance = this;
    }


    Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        log.info(ChatColor.GREEN + "SurvivalEssentials is Enabled");
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        getCommand("staffchat").setExecutor(new EnableSC(this));
        this.ToggledStaff = new ArrayList();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
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
