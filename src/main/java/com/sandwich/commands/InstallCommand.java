package com.sandwich.commands;

import com.sandwich.SurvivalEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.TimeUnit;

public class InstallCommand implements CommandExecutor {

    private SurvivalEssentials SC;

    public InstallCommand(SurvivalEssentials SC) {
        this.SC = SC;
    }

    //TODO Make a command to make it easier to install the needed addons from placeholderapi
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("install")) {
            if(commandSender.isOp()) {
              /*  try{

                    Bukkit.dispatchCommand(commandSender, "papi ecloud download Server");
                    Bukkit.broadcastMessage("1");
                    Thread.sleep(3000); //sleep for 3 seconds
                    Bukkit.dispatchCommand(commandSender, "papi reload");
                    Bukkit.broadcastMessage("2");
                    Thread.sleep(3000);
                    //TODO find out why Player expansion can not be found
                    Bukkit.dispatchCommand(commandSender, "papi ecloud download Player");
                    Thread.sleep(3000);
                    Bukkit.broadcastMessage("3");
                    Bukkit.dispatchCommand(commandSender, "papi reload");
                }
                catch(InterruptedException e){
                    System.out.println("got interrupted!");
                }
                */

            } else {
                commandSender.sendMessage(SC.noPermission);
            }
        }
        return true;
    }
}
