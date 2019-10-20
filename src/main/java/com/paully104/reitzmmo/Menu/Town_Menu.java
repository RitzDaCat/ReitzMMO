package com.paully104.reitzmmo.Menu;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.ConfigFiles.FileManager;
import com.paully104.reitzmmo.ConfigFiles.TownConfig;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Created by Paul on 5/10/2017.
 */
public class Town_Menu implements Listener {


    static FileConfiguration towns = API.getTownConfig();
    public static Set<String> townList = towns.getConfigurationSection("Towns").getKeys(false);
    public static final Inventory TOWN_MENU = Bukkit.createInventory(null, 9, "Town Menu");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.

    static {
        int index = 0;
        for(String name : townList) {
            createDisplay(Material.GRASS, index,name,name);
            index++;
        }


        //createDisplay(Material.GRASS, 0,"Starting Fort","Teleport to Starting Fort");
        //createDisplay(Material.ARMOR_STAND, 1,"Friend Zone","Teleport to Friend Zone");
        createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }


    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event)
    {
        //System.out.println(event.getDestination().toString());
        if (event.getDestination() == TOWN_MENU) {

            //System.out.println("InventoryMoveEvent on TOWN MENU");
            //System.out.println(event.getDestination().toString());
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory == TOWN_MENU) {
            if(null != clicked) {
                if (clicked.hasItemMeta()) {
                    //loop through config spawns
                    System.out.println(clicked.getItemMeta().getDisplayName());
                    if(townList.contains(clicked.getItemMeta().getDisplayName()))
                    {
                        //if the item clicked exists in the config
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                        player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                        //configuration.addDefault("Towns.StartingTown.X", "38.809");
                        String name = clicked.getItemMeta().getDisplayName();
                        int x = API.townConfig.getInt("Towns."+name+".X");
                        int y = API.townConfig.getInt("Towns."+name+".Y");
                        int z = API.townConfig.getInt("Towns."+name+".Z");
                        String world = API.townConfig.getString("Towns."+name+".World");
                        //Make the character wait 5 seconds of no damage
                        double health = player.getHealth();
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                            if (player.getHealth() >= health) {
                                player.teleport(new Location(Bukkit.getWorld(world), x, y, z));

                            } else {
                                player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                            }
                            //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                        }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE


                    }
                    else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
                        player.performCommand("Reitz");

                    }
                    else {
                        event.setCancelled(true);

                    }

                    }



                }
            }
        }

    private static void createDisplay(Material material, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        Town_Menu.TOWN_MENU.setItem(Slot, item);

    }
}

/*
OLD STATIC
                    if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("World Spawn Base")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                        player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                        //Make the character wait 5 seconds of no damage
                        double health = player.getHealth();
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                            if (player.getHealth() >= health) {
                                player.teleport(new Location(Bukkit.getWorld("world"), 38.809, 77, -30.477));

                            } else {
                                player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                            }
                            //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                        }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE

                    }

 */

