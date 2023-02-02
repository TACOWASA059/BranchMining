package com.github.tacowasa059.branchmining.events;

import com.github.tacowasa059.branchmining.BranchMining;
import com.github.tacowasa059.branchmining.utils.DataList;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private final BranchMining branchMining;
    public JoinEvent(BranchMining branchMining){
        this.branchMining=branchMining;
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player player=e.getPlayer();
        if(!DataList.time_count.containsKey(player.getUniqueId())){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.RED+"あなたは既に死んでいます。");
        }
    }
}
