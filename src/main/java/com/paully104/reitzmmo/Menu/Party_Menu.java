package com.paully104.reitzmmo.Menu;

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

/**
 * Created by Paul on 4/26/2017.
 */
public class Party_Menu implements Listener{


    public static final Inventory PARTY_MENU = Bukkit.createInventory(null, 9, "Party Menu");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.

    static {
        //Icons
/*
        sender.sendMessage(ChatColor.GOLD + "~RParty Commands~");
        sender.sendMessage(ChatColor.GOLD + "1. /Rparty create");
        sender.sendMessage(ChatColor.GOLD + "2. /Rparty add");
        sender.sendMessage(ChatColor.GOLD + "3. /Rparty remove");
        sender.sendMessage(ChatColor.GOLD + "4. /Rparty disband");
        sender.sendMessage(ChatColor.GOLD + "5. /Rparty members");
        sender.sendMessage(ChatColor.GOLD + "t. /Rparty leave");
        */
        createDisplay(Material.ANVIL, 0, "Create", "Create a party!");
        createDisplay(Material.ARROW, 1, "Add", "/Rparty add USERNAME");
        createDisplay(Material.BEDROCK, 2, "Remove", "/Rparty remove USERNAME");
        createDisplay(Material.SKELETON_SKULL, 3, "Disband", "/rparty disband");
        createDisplay(Material.SHIELD, 4, "Get Members", "Show party members");
        createDisplay(Material.MINECART, 5, "Leave", "Leave a party");
        createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");


        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event)
    {
        System.out.println(event.getDestination().toString());
        if (event.getDestination() == PARTY_MENU) {
            System.out.println(event.getDestination().toString());
            System.out.println("InventoryMoveEvent on GUI_MENU");
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory == PARTY_MENU) {
            if(null != clicked)
            {
                if(clicked.hasItemMeta())
                {
                    if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("Create")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.performCommand("Rparty create");
                        // Adds dirt

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Add")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        //Well who the hell are we adding?
                        player.sendMessage(ChatColor.GREEN + "Use /rparty add USERNAME");

                        // Adds dirt

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Remove")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        // Adds dirt
                        player.sendMessage(ChatColor.GREEN + "Use /rparty remove USERNAME");

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Disband")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.performCommand("Rparty disband");
                        // Adds dirt

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Get Members")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.performCommand("Rparty members");
                        // Adds dirt

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Leave")) { // The item that the player clicked it dirt
                        event.setCancelled(true); // Make it so the dirt is back in its original spot
                        player.closeInventory(); // Closes there inventory
                        player.performCommand("Rparty leave");
                        // Adds dirt

                    } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
                        player.performCommand("Reitz");

                    } else {
                        event.setCancelled(true);

                    }
                }
                else
                {
                    event.setCancelled(true);

                }
            }
            else
            {
                event.setCancelled(true);
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

        Party_Menu.PARTY_MENU.setItem(Slot, item);

    }


}











    //DANGER ZONE






