package com.paully104.reitzmmo.ItemData;

import com.paully104.reitzmmo.Command_Handlers.ReitzRPGMain;
import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class nameSpaceKey {
    public static NamespacedKey itemDamageKey = new NamespacedKey(API.plugin, "ReitzMMOItemDamageKey");

    public static NamespacedKey getItemDamageKey() {
        return itemDamageKey;
    }

    public static void setCustomTagOnItemStack(ItemStack item, int number)
    {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemDamageKey, PersistentDataType.INTEGER,number);
        meta.setLore(Arrays.asList(ChatColor.RED+"Bonus Damage: " +number));
        item.setItemMeta(meta);

    }
    public static int getCustomTagonItemStack(ItemStack item)

    {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if(container.has(itemDamageKey, PersistentDataType.INTEGER)){

            int foundvalue = container.get(itemDamageKey,PersistentDataType.INTEGER);
            return foundvalue;
        }
        else {
            return 0;
        }
    }
}
