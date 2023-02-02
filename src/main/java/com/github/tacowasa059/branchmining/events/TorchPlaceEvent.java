package com.github.tacowasa059.branchmining.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class TorchPlaceEvent implements Listener {
    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e){
        Player player=e.getPlayer();
        Block block=e.getBlockPlaced();
        if(block.getType()== Material.TORCH){
            player.getInventory().addItem(new ItemStack(Material.TORCH,1));
        }
    }
}
