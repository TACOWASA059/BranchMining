package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.utils.PlayerLocList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLoc implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            Location location=player.getLocation();
            PlayerLocList.locationHashMap.put(player.getUniqueId(),location);
            player.sendMessage(ChatColor.GREEN+"基準位置が"+location+"に設定されました。");
        }
        return true;

    }
}
