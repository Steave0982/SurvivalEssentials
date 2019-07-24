package com.sandwich.Listener;

import com.sandwich.SurvivalEssentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Motd implements Listener {

    private SurvivalEssentials se;

    public Motd(SurvivalEssentials se) {
        this.se = se;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerPing(ServerListPingEvent event) {
        String motd1 = se.getConfig().getString("MOTD.Line-1");
        String motd2 = se.getConfig().getString("MOTD.Line-2");
        motd1 = motd1.replace("&", "§");
        motd2 = motd2.replace("&", "§");
        event.setMotd(String.valueOf(motd1) + "\n" + motd2);
        if (se.getConfig().get("Server-Maximum-Players.Modify") != null &&
                se.getConfig().getBoolean("Server-Maximum-Players.Modify")) {
            event.setMaxPlayers(se.getConfig().getInt("Server-Maximum-Players.Maximum-Players"));
        }
    }
}
