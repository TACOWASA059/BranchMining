package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetTimeLimit implements CommandExecutor {
    private final BranchMining branchMining;
    public SetTimeLimit(BranchMining branchMining){
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
            if(args.length!=1){
                player.sendMessage(ChatColor.RED+"引数の数が不適当です。");
                return true;
            }
            double value=Double.parseDouble(args[0]);
            this.branchMining.getConfig().set("Limit_Time",value);
            player.sendMessage(ChatColor.GREEN+"現在の値は"+this.branchMining.getConfig().getDouble("Limit_Time")+"です。");
            this.branchMining.saveConfig();
        }
        return true;
    }
}
