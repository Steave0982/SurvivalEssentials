package com.sandwich.commands;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InstallCommand implements CommandExecutor {

    private SurvivalEssentials SC;

    public InstallCommand(SurvivalEssentials SC) {
        this.SC = SC;
    }

    //TODO Make a command to make it easier to install the needed addons from placeholderapi
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("install")) {
            if(commandSender.isOp()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download Server");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi ecloud download Player");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi reload");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");
            } else {
                commandSender.sendMessage(SC.noPermission);
            }
        }
        return true;
    }
}
