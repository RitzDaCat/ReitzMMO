package io.github.paulanthonyreitz.reitzmmo.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class Party_Menu implements Listener {
    public static final Inventory PARTY_MENU = Bukkit.createInventory(null, 9, "Party Menu");

    static {
        createDisplay(Material.ANVIL, 0, "Create", "Create a party!");
        createDisplay(Material.ARROW, 1, "Add", "/Rparty add USERNAME");
        createDisplay(Material.BEDROCK, 2, "Remove", "/Rparty remove USERNAME");
        createDisplay(Material.SKELETON_SKULL, 3, "Disband", "/rparty disband");
        createDisplay(Material.SHIELD, 4, "Get Members", "Show party members");
        createDisplay(Material.MINECART, 5, "Leave", "Leave a party");
        createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event) {
        System.out.println(event.getDestination().toString());
        if (event.getDestination() == PARTY_MENU)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        if (inventory == PARTY_MENU)
            if (null != clicked) {
                if (clicked.hasItemMeta()) {
                    if (Objects.<ItemMeta>requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("Create")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.performCommand("Rparty create");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Add")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "Use /rparty add USERNAME");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Remove")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "Use /rparty remove USERNAME");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Disband")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.performCommand("Rparty disband");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Get Members")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.performCommand("Rparty members");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Leave")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        player.performCommand("Rparty leave");
                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
                        player.performCommand("Reitz");
                    } else {
                        event.setCancelled(true);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
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
        PARTY_MENU.setItem(Slot, item);
    }
}





    //DANGER ZONE






