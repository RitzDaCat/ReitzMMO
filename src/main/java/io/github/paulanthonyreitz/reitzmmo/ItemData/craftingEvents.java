package io.github.paulanthonyreitz.reitzmmo.ItemData;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import java.util.Random;

public class craftingEvents implements Listener {
    private static final String LEVEL = "Level";

    public static boolean isArmour(Material type) {
        if (type.name().contains("BOOT") || type.name().contains("LEGGING") || type
                .name().contains("CHESTPLATE") || type.name().contains("HELMET") || type.name().contains("CAP"))
            return true;
        return false;
    }

    public static boolean isWeapon(Material type) {
        if (type.name().contains("AXE") || type.name().contains("SWORD") || type
                .name().contains("TRIDENT") || type.name().contains("BOW"))
            return true;
        return false;
    }

    @EventHandler
    public void onPlayerCrafting(PrepareItemCraftEvent event) {}

    @EventHandler
    public void onPlayerUse(CraftItemEvent event) {
        if (isArmour(event.getRecipe().getResult().getType())) {
            Player p = (Player)event.getWhoClicked();
            int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
            int rnd = (new Random()).nextInt(level);
            nameSpaceKey.setItemDefenseContainer(event.getInventory().getResult(), rnd);
        } else if (isWeapon(event.getRecipe().getResult().getType())) {
            Player p = (Player)event.getWhoClicked();
            int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
            int rnd = (new Random()).nextInt(level);
            nameSpaceKey.setItemDamageContainer(event.getInventory().getResult(), rnd);
        }
    }
}
