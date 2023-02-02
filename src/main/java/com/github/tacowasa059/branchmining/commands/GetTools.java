package com.github.tacowasa059.branchmining.commands;

import com.github.tacowasa059.branchmining.utils.giveItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetTools implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            giveItem.onGive(player);
        }
        return true;
    }
}
