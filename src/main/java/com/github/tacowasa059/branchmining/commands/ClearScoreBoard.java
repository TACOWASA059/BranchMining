package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearScoreBoard implements CommandExecutor {
    private final BranchMining branchMining;
    public ClearScoreBoard(BranchMining branchMining){
        this.branchMining=branchMining;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED+"このコマンドを実行するにはOP権限が必要です。");
                return true;
            }
            branchMining.schedule.clearScoreboard();
            player.sendMessage(ChatColor.RED+"スコアボードを削除しました。");
        }
        return true;
    }
}
