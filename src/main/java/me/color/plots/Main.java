package me.color.plots;

import me.color.plots.commands.mainCommand;
import me.color.plots.events.Cuboid;
import me.color.plots.events.createPlot;
import org.bukkit.plugin.java.JavaPlugin;
import me.color.plots.config.configClass;

public final class Main extends JavaPlugin {


    public configClass config = new configClass(this);

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(new Cuboid(), this);
        this.getServer().getPluginManager().registerEvents(new createPlot(this), this);
        this.getCommand("plot").setExecutor(new mainCommand(this));

        config.initializeCuboidConfig();
        configClass.saveCuboidConfig();
    }



}
