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
                ActionBar.send((Player) sender, ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.Actionbar-Enabled")));

            }
            else
            {
                this.SC.disableStaffChat(sender.getName());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.Disabled")));
                ActionBar.send((Player) sender, ChatColor.RED + ChatColor.translateAlternateColorCodes('&', this.SC.getConfig().getString("features.staffchat.Actionbar-Disabled")));

            }

        } else {

            sender.sendMessage(SC.noPermission);
            return false;
        }  return false;
    }
}