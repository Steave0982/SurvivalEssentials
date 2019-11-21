package com.sandwich.database;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {

    static SurvivalEssentials se;

    public MySQL(SurvivalEssentials instance) {
        se = instance;
    }

    public static String host = se.getConfig().getString("database.host");
    public static int port = se.getConfig().getInt("database.port");
    public static String database = se.getConfig().getString("database.dastabase");
    public static String username = se.getConfig().getString("database.username");
    public static String password = se.getConfig().getString("database.password");
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    //TODO Re-design player information storage
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                console.sendMessage("\247c[\2476ServerCore\247c] \247bMySQL-Enabled!");
                PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Players (Name VARCHAR(100),UUID VARCHAR(100),Kills INT(100),PRIMARY KEY (Name))");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO Clean disconnect function / redesign
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("\247c[\2476Server-Core\247c]\247bMySQL-Disconnected!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // isConnected
    public static boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }

    public static void storePlayer(UUID uuid, String name, int pkills) throws SQLException {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT IGNORE INTO Players (Name,UUID,Kills) VALUES (?,?,?)");
        ps.setString(1, name);
        ps.setString(2, String.valueOf(uuid));
        ps.setInt(3, pkills);
        ps.executeUpdate();
    }
}