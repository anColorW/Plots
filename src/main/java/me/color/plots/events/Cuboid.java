package me.color.plots.events;

import me.color.plots.config.configClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import me.color.plots.config.configFunctions;
public class Cuboid implements Listener {



    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e){
        Location loc = e.getClickedBlock().getLocation();

        if (!e.getClickedBlock().getType().equals(Material.AIR)){
            e.getPlayer().sendMessage("X: " + loc.getBlockX() + "\n Y: " + loc.getBlockY() + "\n Z: " + loc.getBlockZ());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        //optimize it with taking the X coordinates from config file and then returning if value is lower or greater than min/max X

        String closestPlot = configFunctions.findClosestPlot(e.getBlock())[0];
        int distance = Integer.parseInt(configFunctions.findClosestPlot(e.getBlock())[1]);

        if (e.getBlock().getType().equals(Material.AIR)){
            return;
        }

        if (closestPlot != null){
            if (distance < 100){
                int cuboidX =  configClass.getInt("cuboids." + closestPlot + ".X");
                int cuboidZ =  configClass.getInt("cuboids." + closestPlot + ".Z");
                System.out.println(cuboidX);
                for(int x = cuboidX - 10; x <= cuboidX + 10; x++) {
                    for(int z = cuboidZ - 10; z <= cuboidZ + 10; z++) {
                        if (!e.getPlayer().getName().equals(closestPlot)) {
                            if (!configFunctions.isFriend(closestPlot, e.getPlayer().getName())){
                                if (e.getBlock().getLocation().getBlockX() == x) {
                                    if (e.getBlock().getLocation().getBlockZ() == z) {
                                        e.getPlayer().sendMessage("Hey, you cannot break this block!");
                                        e.setCancelled(true);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
