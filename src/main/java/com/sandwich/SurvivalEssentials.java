package com.sandwich;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Main Class for the plugin
 *
 * @author Alexander Milanovich
 */

import java.util.logging.Logger;

public class SurvivalEssentials extends JavaPlugin {

    private static SurvivalEssentials instance;

    public SurvivalEssentials() {
        SurvivalEssentials.instance = this;
    }


    Logger log = Bukkit.getLogger();


    @Override
    public void onEnable() {
        log.info(ChatColor.GREEN + "SurvivalEssentials is Enabled");
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.RED + "SurvivalEssentials is Disabled");

    }


}
