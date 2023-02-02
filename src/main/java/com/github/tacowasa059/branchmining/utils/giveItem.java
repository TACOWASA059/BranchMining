package com.github.tacowasa059.branchmining.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//itemを付与する
public class giveItem {
    static public void onGive(Player player){
        Inventory inventory= player.getInventory();
        inventory.clear();
        ItemStack pickaxe=new ItemStack(Material.NETHERITE_PICKAXE,1);
        // add Efficiency V enchantment
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 5);
        // add Silk Touch enchantment
        pickaxe.addEnchantment(Enchantment.SILK_TOUCH, 1);
        // add Unbreaking III enchantment
        pickaxe.addEnchantment(Enchantment.DURABILITY, 3);

        ItemMeta pickaxe_meta=pickaxe.getItemMeta();
        pickaxe_meta.setUnbreakable(true);
        pickaxe.setItemMeta(pickaxe_meta);

        ItemStack shovel=new ItemStack(Material.NETHERITE_SHOVEL,1);
        shovel.addEnchantment(Enchantment.DIG_SPEED, 5);
        shovel.addEnchantment(Enchantment.SILK_TOUCH, 1);
        shovel.addEnchantment(Enchantment.DURABILITY, 3);

        ItemMeta shovel_meta=shovel.getItemMeta();
        shovel_meta.setUnbreakable(true);
        shovel.setItemMeta(shovel_meta);


        ItemStack sword=new ItemStack(Material.NETHERITE_SWORD,1);
        sword.addEnchantment(Enchantment.DAMAGE_ALL,5);
        sword.addEnchantment(Enchantment.DURABILITY,3);

        ItemMeta sword_meta= sword.getItemMeta();
        sword_meta.setUnbreakable(true);
        sword.setItemMeta(sword_meta);

        player.getInventory().addItem(pickaxe);
        player.getInventory().addItem(shovel);
        player.getInventory().addItem(sword);
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF,64));
        player.getInventory().addItem(new ItemStack(Material.COBBLESTONE,64));
        player.getInventory().addItem(new ItemStack(Material.TORCH,128));
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET,1));
    }
}
