package com.paully104.reitzmmo.Menu;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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


/**
 * Created by Paul on 5/10/2017.
 */
public class Town_Menu implements Listener {

    public static final Inventory TOWN_MENU = Bukkit.createInventory(null, 9, "Town  Menu");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.

    static {
        //Icons

        createDisplay(Material.GRASS, 0,"Starting Fort","Teleport to Starting Fort");
        createDisplay(Material.ARMOR_STAND, 1,"Friend Zone","Teleport to Friend Zone");
        createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event)
    {
        System.out.println(event.getDestination().getName());
        if (event.getDestination().getName().equals(TOWN_MENU.getName())) {

            System.out.println("InventoryMoveEvent on TOWN MENU");
            System.out.println(event.getDestination().getName());
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.getName().equals(TOWN_MENU.getName()))
        {
            if(clicked.hasItemMeta())
            {
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Starting Fort")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                    player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                    //Make the character wait 5 seconds of no damage
                    double health = player.getHealth();
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                        if(player.getHealth() >= health)
                        {
                            player.teleport(new Location(Bukkit.getWorld("world"),-291.624,76,313.300));

                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                        }
                        //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                    }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE

                }
                if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Friend Zone")) { // The item that the player clicked it dirt
                    event.setCancelled(true); // Make it so the dirt is back in its original spot
                    player.closeInventory(); // Closes there inventory
                    player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                    player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                    double health = player.getHealth();
                    //wait 5 seconds
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> {
                        if(player.getHealth() >= health)
                        {
                            player.teleport(new Location(Bukkit.getWorld("world"),-136.302,63,193.659)); // Adds dirt

                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                        }
                        //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                    }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE


                }
                if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu"))
                {
                    player.performCommand("Reitz");

                }

            }
        }
    }
    private static void createDisplay(Material material, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        Town_Menu.TOWN_MENU.setItem(Slot, item);

    }
}
