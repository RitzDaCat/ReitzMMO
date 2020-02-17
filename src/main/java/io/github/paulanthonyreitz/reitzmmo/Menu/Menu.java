package io.github.paulanthonyreitz.reitzmmo.Menu;



import java.util.ArrayList;
import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
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

public class Menu implements Listener {
    public static final Inventory GUI_MENU = Bukkit.createInventory(null, 9, "Reitz Menu");

    static {
        if (API.menuConfig.getBoolean("TeleportHomeEnabled"))
            createDisplay(Material.FEATHER, 0, "Home", "Teleport to your home point!");
        createDisplay(Material.MAP, 1, "Stats", "Get your combat stats!");
        if (API.partyConfig.getBoolean("Parties_Enabled"))
            createDisplay(Material.PLAYER_HEAD, 2, "Party", "Get the party commands!");
        createDisplay(Material.WOODEN_SWORD, 3, "Sword Skills", "Apply sword weaponskills");
        createDisplay(Material.MAP, 4, "Town Menu", "Town Menu");
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event) {
        if (event.getDestination() == GUI_MENU)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        if (inventory == GUI_MENU)
            if (null != clicked) {
                if (clicked.hasItemMeta()) {
                    double health;
                    switch (Objects.<ItemMeta>requireNonNull(clicked.getItemMeta()).getDisplayName()) {
                        case "Home":
                            event.setCancelled(true);
                            player.closeInventory();
                            health = player.getHealth();
                            player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                            player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                                if (player.getHealth() >= health) {
                                    if (null != player.getBedSpawnLocation()) {
                                        player.teleport(player.getBedSpawnLocation());
                                    } else {
                                        player.teleport(player.getWorld().getSpawnLocation());
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                                }
                            },100L);
                            break;
                        case "Stats":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.performCommand("Reitz Stats");
                            break;
                        case "Fix Health":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.performCommand("Reitz FixHealth");
                            break;
                        case "Party":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.performCommand("RParty");
                            break;
                        case "Fix EXP":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.performCommand("Reitz FixEXP");
                            break;
                        case "Town Menu":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.openInventory(Town_Menu.TOWN_MENU);
                            break;
                        case "Sword Skills":
                            event.setCancelled(true);
                            player.closeInventory();
                            player.openInventory(Melee_Skills.Melee_Skills);
                            break;
                    }
                    event.setCancelled(true);
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
        GUI_MENU.setItem(Slot, item);
    }
}
