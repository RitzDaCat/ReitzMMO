package com.paully104.reitzmmo.Custom_Recipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**
 * Created by Paul on 8/6/2016.
 */
public class Custom_Arrows{

    public static void setCustomArrow()
    {
    ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta meta = arrow.getItemMeta();
        meta.setDisplayName("Cheap Arrow");
        meta.setLore(Collections.singletonList(ChatColor.WHITE + "Got any flint my good sir?"));
        arrow.setItemMeta(meta);
    ShapelessRecipe cheapArrow = new ShapelessRecipe(arrow);
        cheapArrow.addIngredient(Material.STICK);
        cheapArrow.addIngredient(Material.FEATHER);
        Bukkit.getServer().addRecipe(cheapArrow);
    }

}
