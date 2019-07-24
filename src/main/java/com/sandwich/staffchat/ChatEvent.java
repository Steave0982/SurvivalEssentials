package com.sandwich.staffchat;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatEvent implements Listener
{
    private SurvivalEssentials se;

    public ChatEvent(SurvivalEssentials se) {
        this.se = se;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void chatEvent(AsyncPlayerChatEvent event) {
        if (this.se.getToggledStaff().contains(event.getPlayer().getName()) || (event.getMessage().toCharArray()[0] == this.se.getConfig().getString("prefix").toCharArray()[0] && event.getPlayer().hasPermission("StaffChat.use"))) {

            event.setCancelled(true);
            String formatting = this.se.getConfig().getString("message-format");
            formatting = formatting.replace("{NAME}", event.getPlayer().getName());
            formatting = formatting.replace("{MESSAGE}", event.getMessage());
            formatting = ChatColor.translateAlternateColorCodes('&', formatting);
            if (event.getMessage().toCharArray()[0] == this.se.getConfig().getString("prefix").toCharArray()[0]) {
                formatting = formatting.replace("!", "");
            }
            for (Player player : Bukkit.getOnlinePlayers()) {

                if (player.hasPermission("StaffChat.use")) player.sendMessage(formatting);
            }
        }
    }
}
