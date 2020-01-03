package com.paully104.reitzmmo.ItemData;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.setItemLore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class craftingArmorEvent implements Listener {

    private final static String LEVEL = "Level";

    public boolean isArmour(Material type) {

        if (type.name().contains("BOOT") || type.name().contains("LEGGING") ||
                type.name().contains("CHESTPLATE") || type.name().contains("HELMET") || type.name().contains("CAP"))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @EventHandler
    public void onPlayerCrafting(PrepareItemCraftEvent event)
    {
        //System.out.println(event);
    }

    @EventHandler
    public void onPlayerUse(CraftItemEvent event) {

        if(isArmour(event.getRecipe().getResult().getType()))
        {
            Player p = (Player)event.getWhoClicked();
            int level = API.Players.get(p.getUniqueId().toString()).getData().getInt(LEVEL);
            int rnd = new Random().nextInt(level);
            //nameSpaceKey.setItemDefenseContainer(event.getRecipe().getResult(),rnd);
            nameSpaceKey.setItemDefenseContainer(event.getInventory().getResult(),rnd);
            System.out.print("Is Armor: " + event.getRecipe().getResult().getType());

        }
        else
        {
            System.out.print("Not armor: " + event.getRecipe().getResult().getType());
        }

    }
}
