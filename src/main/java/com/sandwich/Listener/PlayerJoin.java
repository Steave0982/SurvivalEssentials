package com.sandwich.Listener;

import com.sandwich.SurvivalEssentials;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {

    private SurvivalEssentials se;

    String leavemessage;
    String joinmessage;
    boolean joins;
    Sound joinsound;

    public PlayerJoin(SurvivalEssentials se) {
        this.se = se;
        this.joinmessage = se.getConfig().getString("Join-Message.Join.Message").replace("&", "§");
        this.leavemessage = se.getConfig().getString("Join-Message.Leave.Message").replace("&", "§");
        this.joinsound = Sound.valueOf(se.getConfig().getString("Join-Message.Join.Sound"));
        this.joins = se.getConfig().getBoolean("Join-Message.Join.enabledSound");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void Join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        this.se.getServer().broadcastMessage(PlaceholderAPI.setPlaceholders(p, this.joinmessage.replace("%player%", p.getName())));
        if (this.joins) {
            p.getWorld().playSound(p.getLocation(), this.joinsound, 1.0F, 1.0F);
        }

    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void Leave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);
        this.se.getServer().broadcastMessage(
                PlaceholderAPI.setPlaceholders(p, this.leavemessage.replace("%player%", p.getName())));
    }
}