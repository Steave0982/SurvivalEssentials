package com.sandwich.commands;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    private SurvivalEssentials SC;

    public ClearChatCommand(SurvivalEssentials SC) {
        this.SC = SC; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cc")) {

            if (sender.hasPermission(SC.ClearChat)) {
                for (Player p : Bukkit.getOnlinePlayers()) {

                    for (int i = 0; i < 100; i++) {
                        p.sendMessage("");
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', SC.getConfig().getString("features.clearchat.GlobalClear")));
                }
            } else {
                sender.sendMessage(SC.noPermission);
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("ccme")) {

            for (int i = 0; i < 100; i++) {
                sender.sendMessage("");
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SC.getConfig().getString("features.clearchat.PersonalClear")));
            return true;
        }
        return false;
    }
}

