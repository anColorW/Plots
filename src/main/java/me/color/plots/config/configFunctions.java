package me.color.plots.config;

import me.color.plots.Main;
import me.color.plots.functions.mainFunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class configFunctions {

    configClass config;

    public configFunctions(Main instance){
        config = instance.config;
    }

    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String[] findClosestPlot(Block block){

        String name = "";
        int distance = 99999;

        mainFunctions.Vector3 vec3Cuboid = new mainFunctions.Vector3();
        mainFunctions.Vector3 vec3Block = new mainFunctions.Vector3();

        for(String key : configClass.cuboidConfig.getConfigurationSection("cuboids").getKeys(false)) {
            vec3Cuboid.x =  configClass.getInt("cuboids." + key + ".X");
            vec3Cuboid.y =  configClass.getInt("cuboids." + key + ".Y");
            vec3Cuboid.z =  configClass.getInt("cuboids." + key + ".Z");

            vec3Block.x = block.getX();
            vec3Block.y = block.getY();
            vec3Block.z = block.getZ();

            int finalDistance = mainFunctions.calculateDistance(vec3Cuboid, vec3Block);

            if (finalDistance < distance){
                name = key;
                distance = finalDistance;
            }

        }
        String[] nameDistance = new String[2];

        nameDistance[0] = name;
        nameDistance[1] = Integer.toString(distance);

        return nameDistance;
    }

    public static boolean isFriend(String plotName, String playerName){

        ArrayList<String> list = (ArrayList<String>) configClass.cuboidConfig.getStringList("cuboids." + plotName + ".friends");

        for (String s : list) {
            if (s.equals(playerName)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPlot(Player p){

        return configClass.get("cuboids." + p.getName()) != null;
    }
    public static void plotInfo(Player p){

        if (hasPlot(p)){
            p.sendMessage("Plot info: " + p.getName());
            p.sendMessage("Coords: " +
                    configClass.get("cuboids." + p.getName() + ".X") + "/" +
                    configClass.get("cuboids." + p.getName() + ".Y") + "/" +
                    configClass.get("cuboids." + p.getName() + ".Z")
            );
            p.sendMessage("Expires: WIP");

            ArrayList<String> list = (ArrayList<String>) configClass.cuboidConfig.getStringList("cuboids." + p.getName() + ".friends");


            String finalString = "";

            for (String playerName : list) {
                String check = Bukkit.getPlayer(playerName) != null ? chat("&a" + playerName) : chat("&c" + playerName) ;
                if (!(list.get(list.size() - 1).equals(playerName))){
                    finalString = finalString + check + ", ";
                } else{
                    finalString = finalString + check;
                }

            }
            p.sendMessage("Friends: " + finalString);

        } else{
            p.sendMessage("You dont have any plot!");
        }

    }

}
