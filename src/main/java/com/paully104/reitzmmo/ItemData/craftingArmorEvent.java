package com.paully104.reitzmmo.ItemData;

import com.paully104.reitzmmo.Custom_Recipes.setItemLore;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class craftingArmorEvent implements Listener {

    public boolean isArmour(Material type) {

        if (type.name().contains("boot") || type.name().contains("legging") ||
                type.name().contains("chestplate") || type.name().contains("helm"))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @EventHandler
    public void onPlayerUse(CraftItemEvent event) {

        if(isArmour(event.getRecipe().getResult().getType()))
        {
            nameSpaceKey.setItemDefenseContainer(event.getCurrentItem(),1);

        }

    }
}
