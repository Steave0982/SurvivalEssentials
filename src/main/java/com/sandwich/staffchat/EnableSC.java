package com.sandwich.staffchat;

import com.sandwich.SurvivalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class EnableSC
        implements CommandExecutor
{
    private SurvivalEssentials SC;

    public EnableSC(SurvivalEssentials instance) {
        this.SC = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("StaffChat.use")) {

            if (!this.SC.getToggledStaff().contains(sender.getName()))
            {

                this.SC.enableStaffChat(sender.getName());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.Enabled")));

            }
            else
            {
                this.SC.disableStaffChat(sender.getName());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.Disabled")));
            }

        } else {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.no-permission")));
            return false;
        }  return false;
    }
}