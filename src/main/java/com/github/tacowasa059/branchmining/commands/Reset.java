package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import com.github.tacowasa059.branchmining.utils.DataList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reset implements CommandExecutor {
    private final BranchMining branchMining;

    public Reset(BranchMining branchMining) {
        this.branchMining=branchMining;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED+"このコマンドはop権限が必要です");
                return true;
            }
            player.sendMessage(ChatColor.GREEN +"カウントをリセットしました。");
        }
        DataList.resetMap();
        branchMining.resetTime();
        return true;
    }
}
