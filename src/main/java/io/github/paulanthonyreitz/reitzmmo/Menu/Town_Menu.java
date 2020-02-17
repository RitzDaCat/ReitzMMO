package io.github.paulanthonyreitz.reitzmmo.Menu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Town_Menu implements Listener {
    static FileConfiguration towns = API.getTownConfig();

    public static Set<String> townList = towns.getConfigurationSection("Towns").getKeys(false);

    public static final Inventory TOWN_MENU = Bukkit.createInventory(null, 9, "Town Menu");

    static {
        int index = 0;
        for (String name : townList) {
            createDisplay(Material.GRASS, index, name, name);
            index++;
        }
        createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event) {
        if (event.getDestination() == TOWN_MENU)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        if (inventory == TOWN_MENU &&
                null != clicked &&
                clicked.hasItemMeta()) {
            System.out.println(clicked.getItemMeta().getDisplayName());
            if (townList.contains(clicked.getItemMeta().getDisplayName())) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                String name = clicked.getItemMeta().getDisplayName();
                int x = API.townConfig.getInt("Towns." + name + ".X");
                int y = API.townConfig.getInt("Towns." + name + ".Y");
                int z = API.townConfig.getInt("Towns." + name + ".Z");
                String world = API.townConfig.getString("Towns." + name + ".World");
                double health = player.getHealth();
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                    if (player.getHealth() >= health) {
                        player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                    } else {
                        player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                    }
                },100L);
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
                player.performCommand("Reitz");
            } else {
                event.setCancelled(true);
            }
        }
    }

    private static void createDisplay(Material material, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        Objects.<ItemMeta>requireNonNull(meta).setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        TOWN_MENU.setItem(Slot, item);
    }
}
