package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.BranchMining;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShowTime implements CommandExecutor {
    private final BranchMining branchMining;

    public ShowTime(BranchMining branchMining) {
        this.branchMining = branchMining;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            branchMining.show_time(player);
        }
        return true;
    }
}
