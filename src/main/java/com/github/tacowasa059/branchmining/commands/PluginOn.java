package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import com.github.tacowasa059.branchmining.Schedule;
import com.github.tacowasa059.branchmining.events.BlockBreak;
import com.github.tacowasa059.branchmining.events.JoinEvent;
import com.github.tacowasa059.branchmining.events.PlayerMove;
import com.github.tacowasa059.branchmining.events.TorchPlaceEvent;
import com.github.tacowasa059.branchmining.utils.*;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginOn implements CommandExecutor {
    private final BranchMining branchMining;
    public PluginOn(BranchMining branchMining){
        this.branchMining=branchMining;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED+"このコマンドを実行するにはOP権限が必要です。");
                return true;
            }
            if(branchMining.getExecute()==false){
                player.sendMessage(ChatColor.GREEN+"Plugin has been enabled.");
                branchMining.getServer().getPluginManager().registerEvents(new BlockBreak(branchMining),branchMining);
                branchMining.getServer().getPluginManager().registerEvents(new PlayerMove(),branchMining);
                branchMining.getServer().getPluginManager().registerEvents(new JoinEvent(branchMining),branchMining);
                branchMining.getServer().getPluginManager().registerEvents(new TorchPlaceEvent(),branchMining);
                branchMining.start();
                branchMining.schedule.enable();

                for(Player player1:branchMining.getServer().getOnlinePlayers()){
                    DisplayNameList.name_list.put(player1.getUniqueId(),player1.getDisplayName());
                    TimeCount.addTime(player1.getUniqueId());
                    branchMining.schedule.InitializeBar(player1.getUniqueId());
                    player1.setGameMode(GameMode.SURVIVAL);
                    player1.sendTitle(ChatColor.RED+"START","" ,1, 20, 1);
                    player1.playSound(player1.getLocation(), Sound.BLOCK_ANVIL_PLACE,1,1);
                    giveItem.onGive(player1);
                    if(!PlayerLocList.locationHashMap.containsKey(player1.getUniqueId())){
                        PlayerLocList.locationHashMap.put(player1.getUniqueId(),player1.getLocation());
                    }
                    player1.getWorld().spawnParticle(Particle.BARRIER,player1.getLocation(),1);
                }
            }
            else{
                player.sendMessage(ChatColor.RED+"Plugin has already been enabled.");
            }
        }

        return true;
    }
}
