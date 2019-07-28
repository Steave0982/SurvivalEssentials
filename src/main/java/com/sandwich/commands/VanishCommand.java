package com.sandwich.commands;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Vanish Class
 *
 * @author Christopher Nethercott
 * @author Alexander Milanovich
 */


public class VanishCommand implements CommandExecutor, Listener {

    ArrayList<UUID> vanished = new ArrayList<>();

    SurvivalEssentials plugin;
    public VanishCommand(SurvivalEssentials se) {
        plugin = se;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            UUID playerUUID = player.getUniqueId();
            if (player.hasPermission(plugin.Vanish)) {
                if (args.length == 0) {
                    if (!vanished.contains(playerUUID)) {
                        vanishPlayer(playerUUID, true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("features.vanish.Enabled")));
                        return true;
                    } else {
                        vanishPlayer(playerUUID, false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("features.vanish.Disabled")));
                        return true;
                    }
                } else if (args.length == 1) {
                    UUID toVanish = Bukkit.getPlayerExact(args[0]).getUniqueId();
                    if (toVanish != null) {
                        if (!vanished.contains(toVanish)) {
                            vanishPlayer(toVanish, true);
                            Bukkit.getPlayer(toVanish).sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("features.vanish.Enabled")));
                            player.sendMessage(ChatColor.GREEN + "You have vanished " + toVanish);
                            return true;
                        } else {
                            vanishPlayer(toVanish, false);
                            Bukkit.getPlayer(toVanish).sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("features.vanish.Disabled")));
                            player.sendMessage(ChatColor.RED + "You have unvanished " + toVanish);
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "The player you specified is not online.");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Too many arguments.");
                    return false;
                }
            } else {
                player.sendMessage(plugin.noPermission);
                return true;
            }
        } else {
            commandSender.sendMessage(plugin.notPlayer);
            return true;
        }
    }

    @EventHandler
    public void onVanishJoinEvent(PlayerJoinEvent event) {
        for (UUID uuid : vanished) {
            event.getPlayer().hidePlayer(plugin, Bukkit.getPlayer(uuid));
        }
    }

    @EventHandler
    public void onVanishQuitEvent(PlayerQuitEvent event) {
        if (vanished.contains(event.getPlayer().getUniqueId())) {
            vanished.remove(event.getPlayer().getUniqueId());
        }
    }

    /**
     * @param vanishPlayer The UUID of the player that you would like to vanish
     * @param shouldIVanish True, Vanish Player; False, Don't Vanish Player. Allows one subroutine.
     */
    private void vanishPlayer(UUID vanishPlayer, boolean shouldIVanish) {
        if (shouldIVanish) {
            vanished.add(vanishPlayer);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.hidePlayer(plugin, Bukkit.getPlayer(vanishPlayer));
            }
            //                        SuperTrailsAPI.getPlayerData(player).setHidden(true, HideReason.CUSTOM_VANISH);

        } else {
            vanished.remove(vanishPlayer);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.showPlayer(plugin, Bukkit.getPlayer(vanishPlayer));
            }
            //                        SuperTrailsAPI.getPlayerData(player).setHidden(false, HideReason.CUSTOM_VANISH);
        }
    }
}
