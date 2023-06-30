package me.color.plots.commands;

import me.color.plots.Main;
import me.color.plots.config.configClass;
import me.color.plots.config.configFunctions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class mainCommand implements CommandExecutor {

    Main plugin;
    public mainCommand(Main instance){
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof  Player)){
            sender.sendMessage("You can only execute command as Player!");
            return true;
        }
        Player p = (Player) sender;


        if (args.length == 0){
                configFunctions.plotInfo(p);
        } else{
            if (args.length == 2 && args[1].length() > 0){
                if (Bukkit.getPlayer(args[1]) != null){
                    ArrayList<String> list = (ArrayList<String>) configClass.cuboidConfig.getStringList("cuboids." + p.getName() + ".friends");
                    switch (args[0]) {
                        case "add":
                            list.add(args[1]);
                            configClass.add("cuboids." + p.getName() + ".friends", list);
                            break;
                        case "remove":
                            if (configFunctions.isFriend(p.getName(), args[1])) {
                                list.remove(args[1]);
                                configClass.add("cuboids." + p.getName() + ".friends", list);
                            }
                            break;
                        case "tp":
                            if (!p.isOp()) {
                                p.sendMessage("You are not Admin!");
                                return true;
                            }
                            if (configClass.get("cuboids." + args[1]) != null) {
                                Location plotLocation = new Location(p.getWorld(),
                                        configClass.getInt("cuboids." + args[1] + ".X"),
                                        configClass.getInt("cuboids." + args[1] + ".Y") + 100,
                                        configClass.getInt("cuboids." + args[1] + ".Z")
                                );

                                p.teleport(plotLocation);
                            } else {
                                p.sendMessage("Plot doesnt exist.");
                            }
                            break;
                        default:
                            printCommandInfo(p);
                            break;
                    }
                } else{
                    printCommandInfo(p);
                }
            } else{
                printCommandInfo(p);
            }
        }
        return true;
    }

    void printCommandInfo(Player p){
        p.sendMessage("Correct usage: \n /plot add [PlayerName] \n /plot remove [PlayerName]");
        if (p.isOp()){
            p.sendMessage("/plot tp [PlayerName]");
        }
    }
}
