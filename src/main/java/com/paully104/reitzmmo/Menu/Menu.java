package com.paully104.reitzmmo.Menu;

import com.paully104.reitzmmo.ConfigFiles.API;
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

import static com.paully104.reitzmmo.ConfigFiles.API.plugin;

/**
 * Created by Paul on 4/26/2017.
 */
public class Menu implements Listener {

    //Check If Menu is Enabled


    //The Idea is to make these obsolute with a nifty menu :)
    //sender.sendMessage(ChatColor.GOLD + "~ReitzRPGMMO main  menu listing commands~");
    //sender.sendMessage(ChatColor.GOLD + "1. /Reitz Stats");
    //sender.sendMessage(ChatColor.GOLD + "2. /Reitz Fix");
    //sender.sendMessage(ChatColor.GOLD + "3. /Rparty");

    public static final Inventory GUI_MENU = Bukkit.createInventory(null, 9, "Reitz Menu");
    // The first parameter, is the inventory owner. I make it null to let everyone use it.
    //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
    //The third parameter, is the inventory name. This will accept chat colors.
    static {

        //Icons

        if(API.menuConfig.getBoolean("TeleportHomeEnabled"))
        {
            createDisplay(Material.FEATHER, 0, "Home", "Teleport to your home point!");
        }
        createDisplay(Material.MAP, 1, "Stats","Get your combat stats!");
        //createDisplay(Material.SPECTRAL_ARROW, 3, "Fix Health","If your health is bugged fix it");
        if(API.partyConfig.getBoolean("Parties_Enabled")) {
            createDisplay(Material.PLAYER_HEAD, 2, "Party", "Get the party commands!");
            //createDisplay(Material.SPECTRAL_ARROW, 3, "Weaponskills","Apply weaponskills");
        }
        //createDisplay(Material.ARMOR_STAND, 6, "Fix EXP","Fix floating EXP");
        //createDisplay(Material.MAP, 7, "Town Menu","Teleport to a town");
        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }

    @EventHandler
    public void onInventoryMoveEvent(InventoryMoveItemEvent event)
    {
        System.out.println(event.getDestination().toString());
        if (event.getDestination() == GUI_MENU) {

            System.out.println("InventoryMoveEvent on GUI_MENU");
            System.out.println(event.getDestination().toString());
            event.setCancelled(true);
        }

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory == GUI_MENU) {
            if(null != clicked) {
                if (clicked.hasItemMeta()) {
                    switch(Objects.requireNonNull(clicked.getItemMeta()).getDisplayName()) {

                        case "Home":
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            double health = player.getHealth();
                            player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
                            player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                if (player.getHealth() >= health) {
                                    if (null != player.getBedSpawnLocation()) {
                                        player.teleport(player.getBedSpawnLocation()); // Adds dirt
                                    } else {
                                        player.teleport(player.getWorld().getSpawnLocation());
                                        //player.sendMessage(ChatColor.RED + "No homepoint set!");
                                    }

                                } else {
                                    player.sendMessage(ChatColor.RED + "Can't teleport while in combat!");
                                }
                                //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                            }, 100); //5000 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE
                            break;


                        case "Stats":  // The item that the player clicked it dirt
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            player.performCommand("Reitz Stats"); // Adds dirt
                            break;


                        case "Fix Health": // The item that the player clicked it dirt
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            player.performCommand("Reitz FixHealth"); // Adds dirt
                            break;


                        case "Party":  // The item that the player clicked it dirt
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            player.performCommand("RParty"); // Adds dirt
                            break;


                        case "Fix EXP": // The item that the player clicked it dirt
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            player.performCommand("Reitz FixEXP"); // Adds dirt
                            break;


                        case "Town Menu":  // The item that the player clicked it dirt
                            event.setCancelled(true); // Make it so the dirt is back in its original spot
                            player.closeInventory(); // Closes there inventory
                            player.openInventory(Town_Menu.TOWN_MENU); // Adds dirt
                            break;




                    }
                    //outside the switch
                    event.setCancelled(true);
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

        Menu.GUI_MENU.setItem(Slot, item);

    }

}
