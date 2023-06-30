package me.color.plots.config;

import me.color.plots.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
public class configClass {

    Main plugin;
    public configClass(Main instance){
        plugin = instance;
    }
    public static File cuboidList;
    public static FileConfiguration cuboidConfig;


    public void initializeCuboidConfig(){
        cuboidList = new File(plugin.getDataFolder(), "cuboids.yml");
        cuboidConfig = YamlConfiguration.loadConfiguration(cuboidList);
        saveCuboidConfig();
    }

    public static String get(String str){
        return cuboidConfig.getString(str);
    }
    public static int getInt(String str){
        return cuboidConfig.getInt(str);
    }

    public static boolean getBool(String str){
        return cuboidConfig.getBoolean(str);
    }
    public static void add(String path, Object value){
        cuboidConfig.set(path,value);
        saveCuboidConfig();
    }

    public static void saveCuboidConfig(){
        try{
            cuboidConfig.save(cuboidList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
