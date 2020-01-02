package com.paully104.reitzmmo.Custom_Recipes;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class setItemLore{

    public setItemLore(Item item, String lore)
    {
        ItemStack itemStack = item.getItemStack(); //item stack
        ItemMeta itemmeta = itemStack.getItemMeta(); //item meta
        itemmeta.setLore(Arrays.asList(lore)); ///set lore

        item.setItemStack(itemStack); //replace the item with this modified stack?


    }

}
