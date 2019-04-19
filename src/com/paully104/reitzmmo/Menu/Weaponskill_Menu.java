package com.paully104.reitzmmo.Menu;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Enum.Weaponskill_Item_Check;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import com.paully104.reitzmmo.Skills.Weapon_Skills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Paul on 4/29/2017.
 */
    public class Weaponskill_Menu implements Listener {

    //Get Config values
    private final int spinAttackLevel = API.weaponskillConfig.getInt("Spin_Attack_Level");
    private final int spinAttackDamageScale = API.weaponskillConfig.getInt("Spin_Attack_Damage_Scale");

    public static final Inventory WEAPONSKILL_MENU = Bukkit.createInventory(null, 18, "Weaponskill Menu");

    static {
        //Icons
    /*WEAPON SKILL LIST
    1. Spin attack - spin weapon around knockback enemys
    2. Charge attack - movement speed up , stun maybe?
    3. Weapon throw
    4. Uppercut - to the moon

    */

    /* put spell on weapons so they can swap around

     */
        createDisplay(Material.SPECTRAL_ARROW, 0, "Spin Attack","[Level 2] SPIN ATTACKAROOO");
        createDisplay(Material.SPECTRAL_ARROW, 1, "Heavy Swing","[Level 4] Heavy Swing");
        createDisplay(Material.SPECTRAL_ARROW, 2, "WORK IN PROGRESS","WORK IN PROGRESS");
        createDisplay(Material.SPECTRAL_ARROW, 3, "WORK IN PROGRESS","WORK IN PROGRESS");
        createDisplay(Material.BOW, 4,"Barrage","[Level 2]Barrage");
        createDisplay(Material.BOW, 5,"Bomb Arrow","[Level 2]Bomb Arrow");
        createDisplay(Material.BOW, 6,"Chicken launcher","[Level 2]Chicken launcher");
        createDisplay(Material.BOW, 7,"Shift Back","[Level 2]Shift Back");
        createDisplay(Material.REDSTONE_BLOCK, 8, "Main Menu","Return to main menu");

        //GUI_MENU.setItem(0, new ItemStack(Material.DIRT, 3));
        //GUI_MENU.setItem(8, new ItemStack(Material.GOLD_BLOCK, 4));
        //The first parameter, is the slot that is assigned to. Starts counting at 0
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.getName().equals(WEAPONSKILL_MENU.getName())) {
            PlayerData pd = API.Players.get(player.getName());
            int level = pd.getData().getInt("Level");
            if(clicked.hasItemMeta()) {
                boolean weaponIsSkillable = false;
                try {
                    weaponIsSkillable = (Weaponskill_Item_Check.Weaponskill_Check.valueOf(player.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue());
                }
                catch (IllegalArgumentException error)
                {
                    weaponIsSkillable = false;
                }
                System.out.println(weaponIsSkillable);
                if(weaponIsSkillable)
                {
                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Spin Attack") && level >= spinAttackLevel) {
                        event.setCancelled(true);
                        player.closeInventory();
                        //Need to set the lore of the item
                        System.out.println("1.0 " + player.getInventory().getItemInMainHand().getData().getItemType());
                        System.out.println("2.0 " + player.getInventory().getName());
                        System.out.println("3.0 " + player.getInventory().getHeldItemSlot());
                        System.out.println("4.0 " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        System.out.println("5.0 " + player.getInventory().getItemInMainHand().getDurability());
                        System.out.println("6.0" + player.getInventory().getItemInMainHand().getEnchantments());

                        Material material = player.getInventory().getItemInMainHand().getData().getItemType();
                        Inventory playerinventory = player.getInventory();
                        int slot = player.getInventory().getHeldItemSlot();
                        short durability = player.getInventory().getItemInMainHand().getDurability();
                        String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        Map enchantments = player.getInventory().getItemInMainHand().getEnchantments();
                        if (displayName == null) {
                            displayName = player.getInventory().getItemInMainHand().getType().name().replace("_"," ").toLowerCase();

                            //displayName = player.getInventory().getItemInMainHand().getType().toString();

                        }
                        String lore = "Spin Attack";


                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        Weapon_Skills.setWeaponSkillOnWeapon(material, playerinventory, slot, displayName, lore,enchantments);
                        System.out.println("MAX DURABILITY: " + player.getInventory().getItem(slot).getType().getMaxDurability());
                        System.out.println("USED DURABILITY: " + durability);
                        player.getInventory().getItem(slot).setDurability(durability);

                    }

                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Main Menu")) {
                        event.setCancelled(true);
                        player.openInventory(Menu.GUI_MENU);

                    } else {
                        event.setCancelled(true);

                    }
                    player.updateInventory();
                }
                //BOW SECTION
                else if(player.getInventory().getItemInMainHand().getType() == Material.BOW)
                {
                    //BARRAGE
                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Barrage")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        //Need to set the lore of the item
                        System.out.println("1.0 " + player.getInventory().getItemInMainHand().getData().getItemType());
                        System.out.println("2.0 " + player.getInventory().getName());
                        System.out.println("3.0 " + player.getInventory().getHeldItemSlot());
                        System.out.println("4.0 " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        System.out.println("5.0 " + player.getInventory().getItemInMainHand().getDurability());
                        System.out.println("6.0" + player.getInventory().getItemInMainHand().getEnchantments());

                        Material material = player.getInventory().getItemInMainHand().getData().getItemType();
                        Inventory playerinventory = player.getInventory();
                        int slot = player.getInventory().getHeldItemSlot();
                        short durability = player.getInventory().getItemInMainHand().getDurability();
                        String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        Map enchantments = player.getInventory().getItemInMainHand().getEnchantments();
                        if (displayName == null) {
                            displayName = player.getInventory().getItemInMainHand().getType().name().replace("_"," ").toLowerCase();

                            //displayName = player.getInventory().getItemInMainHand().getType().toString();

                        }
                        String lore = "Barrage";


                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        Weapon_Skills.setWeaponSkillOnWeapon(material, playerinventory, slot, displayName, lore,enchantments);
                        System.out.println("MAX DURABILITY: " + player.getInventory().getItem(slot).getType().getMaxDurability());
                        System.out.println("USED DURABILITY: " + durability);
                        player.getInventory().getItem(slot).setDurability(durability);

                    }

                    //Bomb Arrow
                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Bomb Arrow")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        //Need to set the lore of the item
                        System.out.println("1.0 " + player.getInventory().getItemInMainHand().getData().getItemType());
                        System.out.println("2.0 " + player.getInventory().getName());
                        System.out.println("3.0 " + player.getInventory().getHeldItemSlot());
                        System.out.println("4.0 " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        System.out.println("5.0 " + player.getInventory().getItemInMainHand().getDurability());
                        System.out.println("6.0" + player.getInventory().getItemInMainHand().getEnchantments());

                        Material material = player.getInventory().getItemInMainHand().getData().getItemType();
                        Inventory playerinventory = player.getInventory();
                        int slot = player.getInventory().getHeldItemSlot();
                        short durability = player.getInventory().getItemInMainHand().getDurability();
                        String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        Map enchantments = player.getInventory().getItemInMainHand().getEnchantments();
                        if (displayName == null) {
                            displayName = player.getInventory().getItemInMainHand().getType().name().replace("_"," ").toLowerCase();

                            //displayName = player.getInventory().getItemInMainHand().getType().toString();

                        }
                        String lore = "Bomb Arrow";


                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        Weapon_Skills.setWeaponSkillOnWeapon(material, playerinventory, slot, displayName, lore,enchantments);
                        System.out.println("MAX DURABILITY: " + player.getInventory().getItem(slot).getType().getMaxDurability());
                        System.out.println("USED DURABILITY: " + durability);
                        player.getInventory().getItem(slot).setDurability(durability);

                    }

                    //Chicken Launcher
                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Chicken launcher")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        //Need to set the lore of the item
                        System.out.println("1.0 " + player.getInventory().getItemInMainHand().getData().getItemType());
                        System.out.println("2.0 " + player.getInventory().getName());
                        System.out.println("3.0 " + player.getInventory().getHeldItemSlot());
                        System.out.println("4.0 " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        System.out.println("5.0 " + player.getInventory().getItemInMainHand().getDurability());
                        System.out.println("6.0" + player.getInventory().getItemInMainHand().getEnchantments());

                        Material material = player.getInventory().getItemInMainHand().getData().getItemType();
                        Inventory playerinventory = player.getInventory();
                        int slot = player.getInventory().getHeldItemSlot();
                        short durability = player.getInventory().getItemInMainHand().getDurability();
                        String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        Map enchantments = player.getInventory().getItemInMainHand().getEnchantments();
                        if (displayName == null) {
                            displayName = player.getInventory().getItemInMainHand().getType().name().replace("_"," ").toLowerCase();

                            //displayName = player.getInventory().getItemInMainHand().getType().toString();

                        }
                        String lore = "Chicken launcher";


                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        Weapon_Skills.setWeaponSkillOnWeapon(material, playerinventory, slot, displayName, lore,enchantments);
                        System.out.println("MAX DURABILITY: " + player.getInventory().getItem(slot).getType().getMaxDurability());
                        System.out.println("USED DURABILITY: " + durability);
                        player.getInventory().getItem(slot).setDurability(durability);

                    }
                    if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Shift Back")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        //Need to set the lore of the item
                        System.out.println("1.0 " + player.getInventory().getItemInMainHand().getData().getItemType());
                        System.out.println("2.0 " + player.getInventory().getName());
                        System.out.println("3.0 " + player.getInventory().getHeldItemSlot());
                        System.out.println("4.0 " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                        System.out.println("5.0 " + player.getInventory().getItemInMainHand().getDurability());
                        System.out.println("6.0" + player.getInventory().getItemInMainHand().getEnchantments());

                        Material material = player.getInventory().getItemInMainHand().getData().getItemType();
                        Inventory playerinventory = player.getInventory();
                        int slot = player.getInventory().getHeldItemSlot();
                        short durability = player.getInventory().getItemInMainHand().getDurability();
                        String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        Map enchantments = player.getInventory().getItemInMainHand().getEnchantments();
                        if (displayName == null) {
                            displayName = player.getInventory().getItemInMainHand().getType().name().replace("_"," ").toLowerCase();

                            //displayName = player.getInventory().getItemInMainHand().getType().toString();

                        }
                        String lore = "Shift Back";


                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        Weapon_Skills.setWeaponSkillOnWeapon(material, playerinventory, slot, displayName, lore,enchantments);
                        System.out.println("MAX DURABILITY: " + player.getInventory().getItem(slot).getType().getMaxDurability());
                        System.out.println("USED DURABILITY: " + durability);
                        player.getInventory().getItem(slot).setDurability(durability);

                    }

                }

                else
                {
                    event.setCancelled(true);
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED+ "You can't add a weaponskill to that!");
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
        Weaponskill_Menu.WEAPONSKILL_MENU.setItem(Slot, item);

    }




}
