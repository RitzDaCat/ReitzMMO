package com.paully104.reitzmmo.Custom_Recipes;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**
 * Created by Paul on 5/6/2017.
 */
public class Custom_Bows {
/*
        configuration.addDefault("WOOD_BOW_DAMAGE", 2);
        configuration.addDefault("STONE_BOW_DAMAGE", 4);
        configuration.addDefault("IRON_BOW_DAMAGE",6);
        configuration.addDefault("GOLD_BOW_DAMAGE",8);
        configuration.addDefault("DIAMOND_BOW_DAMAGE",10);
 */


    public static void setCustomWoodBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Wooden Bow");
        meta.setLore(Collections.singletonList(ChatColor.YELLOW+"Wooden Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe woodBow = new ShapelessRecipe(bow);
        woodBow.addIngredient(Material.BOW);
        woodBow.addIngredient(Material.ACACIA_WOOD);
        Bukkit.getServer().addRecipe(woodBow);
    }

    public static void setStoneBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Stone Bow");
        meta.setLore(Collections.singletonList("Stone Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe stoneBow = new ShapelessRecipe(bow);
        stoneBow.addIngredient(Material.BOW);
        stoneBow.addIngredient(Material.STONE);
        Bukkit.getServer().addRecipe(stoneBow);


    }
    public static void setIronBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Iron Bow");
        meta.setLore(Collections.singletonList("Iron Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe ironBow = new ShapelessRecipe(bow);
        ironBow.addIngredient(Material.BOW);
        ironBow.addIngredient(Material.IRON_BLOCK);
        Bukkit.getServer().addRecipe(ironBow);


    }
    public static void setGoldBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Gold Bow");
        meta.setLore(Collections.singletonList("Gold Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe goldBow = new ShapelessRecipe(bow);
        goldBow.addIngredient(Material.BOW);
        goldBow.addIngredient(Material.GOLD_BLOCK);
        Bukkit.getServer().addRecipe(goldBow);


    }

    public static void setDiamondBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Diamond Bow");
        meta.setLore(Collections.singletonList("Diamond Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe diamondBow = new ShapelessRecipe(bow);
        diamondBow.addIngredient(Material.BOW);
        diamondBow.addIngredient(Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(diamondBow);


    }

    public static void setLlamaBow()
    {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName("Llama Bow");
        meta.setLore(Collections.singletonList("Llama Bow"));
        bow.setItemMeta(meta);
        ShapelessRecipe diamondBow = new ShapelessRecipe(bow);
        diamondBow.addIngredient(Material.BOW);
        diamondBow.addIngredient(Material.CAKE);
        Bukkit.getServer().addRecipe(diamondBow);


    }

    public static int getBowDamage(ItemStack bow)
    {
        int woodBowDamage = API.custombowConfig.getInt("WOOD_BOW_DAMAGE");
        int stoneBowDamage = API.custombowConfig.getInt("STONE_BOW_DAMAGE");
        int ironBowDamage = API.custombowConfig.getInt("IRON_BOW_DAMAGE");
        int goldBowDamage = API.custombowConfig.getInt("GOLD_BOW_DAMAGE");
        int diamondBowDamage = API.custombowConfig.getInt("DIAMOND_BOW_DAMAGE");

        if(bow.getItemMeta() != null)
        {
            if(bow.getItemMeta().getLore() != null)
            {
                if(bow.getItemMeta().getDisplayName().contains("Wooden Bow"))
                {
                    return woodBowDamage;

                }
                if(bow.getItemMeta().getDisplayName().contains("Stone Bow"))
                {
                    return  stoneBowDamage;

                }
                if(bow.getItemMeta().getDisplayName().contains("Iron Bow"))
                {
                    return  ironBowDamage;

                }
                if(bow.getItemMeta().getDisplayName().contains("Gold Bow"))
                {
                    return  goldBowDamage;

                }
                if(bow.getItemMeta().getDisplayName().contains("Diamond Bow"))
                {
                    return  diamondBowDamage;

                }

            }

        }
        else
        {
            return 0;
        }

        return 0;

    }
}
