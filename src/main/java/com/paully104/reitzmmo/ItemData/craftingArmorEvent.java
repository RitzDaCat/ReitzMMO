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

        if (type.name().contains("Boot") || type.name().contains("Legging") ||
                type.name().contains("Chestplate") || type.name().contains("Helmet") || type.name().contains("Cap"))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @EventHandler
    public void onPlayerCrafting(PrepareItemCraftEvent c)
    {
        System.out.println((c.getRecipe()));

    }

    @EventHandler
    public void onPlayerUse(CraftItemEvent event) {

        if(isArmour(event.getRecipe().getResult().getType()))
        {
            Player p = (Player)event.getWhoClicked();
            int level = API.Players.get(p.getUniqueId().toString()).getData().getInt(LEVEL);
            int rnd = new Random().nextInt(level);
            nameSpaceKey.setItemDefenseContainer(event.getRecipe().getResult(),rnd);
            System.out.print(event.getRecipe().getResult().getType());

        }
        else
        {
            System.out.print("Not armor: " + event.getRecipe().getResult().getType());
        }

    }
}
