package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.utils.DataList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ShowCount implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid=player.getUniqueId();
            if(DataList.sum.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"入手アイテム数 : "+DataList.sum.get(uuid));
            }
            if(DataList.coal.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"石炭鉱石 : "+DataList.coal.get(uuid));
            }
            if(DataList.iron.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"鉄鉱石 : "+DataList.iron.get(uuid));
            }
            if(DataList.laps.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"ラピスラズリ鉱石 : "+DataList.laps.get(uuid));
            }
            if(DataList.diamond.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"ダイヤモンド鉱石 : "+DataList.diamond.get(uuid));
            }
            if(DataList.red_stone.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"レッドストーン鉱石 : "+DataList.red_stone.get(uuid));
            }
            if(DataList.emerald.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"エメラルド鉱石 : "+DataList.emerald.get(uuid));
            }if(DataList.gold.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"金鉱石 : "+DataList.gold.get(uuid));
            }
            if(DataList.ancient_debris.containsKey(uuid)){
                player.sendMessage(ChatColor.AQUA+"古代のがれき : "+DataList.ancient_debris.get(uuid));
            }
        }

        return true;
    }
}
