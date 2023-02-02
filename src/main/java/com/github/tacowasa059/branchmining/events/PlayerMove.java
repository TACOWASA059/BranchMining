package com.github.tacowasa059.branchmining.events;

import com.github.tacowasa059.branchmining.utils.PlayerLocList;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.awt.*;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player=e.getPlayer();
        if(!PlayerLocList.locationHashMap.containsKey(player.getUniqueId())){
            return;
        }

        Location location1=player.getLocation();
        Location location0=PlayerLocList.locationHashMap.get(player.getUniqueId());
        double rel_x= (int)location1.getX()-(int)location0.getX();
        double rel_z= (int)location1.getZ()-(int)location0.getZ();
        int count=(int)Math.max(Math.abs(rel_x)/4.0,Math.abs(rel_z)/4.0);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(ChatColor.YELLOW+Integer.valueOf(count).toString() +"週目"));

    }
}
