package me.color.plots.events;

import me.color.plots.Main;
import me.color.plots.config.configClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import me.color.plots.config.configFunctions;

public class createPlot implements Listener {

    configClass config;
    public createPlot(Main instance){
        config = instance.config;
    }


    @EventHandler
    public void NoteBlockPlaceEvent(BlockPlaceEvent e){

        Player p = e.getPlayer();


        if (e.getBlock().getType().equals(Material.NOTE_BLOCK)){
            if (!configFunctions.hasPlot(p)){
                createP(p, e.getBlock());
            } else{
                p.sendMessage("Hey, you already have plot!");
            }
        }
    }

    void createP(Player p, Block b){
        configClass.add("cuboids." + p.getName() + ".X", b.getLocation().getBlockX());
        configClass.add("cuboids." + p.getName() + ".Y", b.getLocation().getBlockY());
        configClass.add("cuboids." + p.getName() + ".Z", b.getLocation().getBlockZ());
        configClass.add("cuboids." + p.getName() + ".friends", "");
    }




}
