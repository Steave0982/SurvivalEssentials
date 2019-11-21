package com.sandwich.commands;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    private SurvivalEssentials SC;

    public KickCommand(SurvivalEssentials SC) {
        this.SC = SC;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player)sender;
        if (label.equalsIgnoreCase("kick")) {
            if (p.hasPermission("Server.kick")) {
                if (args.length >= 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        String message = "";
                        for (int i = 1; i < args.length; i++) {
                            message = String.valueOf(message) + args[i] + " ";
                        }
                        p.sendMessage(SC.prefix + "You have kicked the player!");
                        target.kickPlayer(ChatColor.RED + "You were kicked\n" + "Reason: " + message + "\n Kicked by: " + p.getDisplayName());
                    } else {
                        p.sendMessage("player is not Online!");
                    }

                } else {

                    p.sendMessage(SC.prefix + "" + ChatColor.RED + "uses: /kick <player> <message>");
                }

            }
            else {

                p.sendMessage("don`t have Permissions for this command!");
            }
        }


        return true;
    }
}


