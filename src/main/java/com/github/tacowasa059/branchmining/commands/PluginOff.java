package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import com.github.tacowasa059.branchmining.utils.DataList;
import com.github.tacowasa059.branchmining.utils.DisplayNameList;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


public class PluginOff implements CommandExecutor {
    private final BranchMining branchMining;
    public PluginOff(BranchMining branchMining){
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
            if(branchMining.getExecute()==true){
                DataList.time_count.clear();
                branchMining.schedule.disable();
                branchMining.stop();
                HandlerList.unregisterAll();
                for(Player player1:branchMining.getServer().getOnlinePlayers()){
                    DisplayNameList.name_list.clear();
                    player1.sendTitle(ChatColor.RED+"STOP","" ,1, 20, 1);
                    player1.playSound(player1.getLocation(), Sound.BLOCK_ANVIL_USE,1,1);
                }
                player.sendMessage(ChatColor.GREEN+"Plugin has been disabled.");
            }
            else{
                player.sendMessage(ChatColor.RED+"Plugin has already been disabled.");
            }
        }

        return true;
    }
}
