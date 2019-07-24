package com.sandwich.staffchat;

import com.sandwich.SurvivalEssentials;
import com.sandwich.actionbar.ActionBar;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class EnableSC
        implements CommandExecutor
{
    private SurvivalEssentials se;

    public EnableSC(SurvivalEssentials instance) {
        this.se = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("StaffChat.use")) {

            if (!this.se.getToggledStaff().contains(sender.getName()))
            {

                this.se.enableStaffChat(sender.getName());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.se.getConfig().getString("Enabled")));

            }
            else
            {
                this.se.disableStaffChat(sender.getName());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.se.getConfig().getString("Disabled")));
            }

        } else {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.se.getConfig().getString("no-permission")));
            return false;
        }  return false;
    }
}
