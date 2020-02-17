package io.github.paulanthonyreitz.reitzmmo.Menu;

import java.util.ArrayList;
import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.ItemData.craftingEvents;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Melee_Skills implements Listener {
    private final int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");

    private final int underFireLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.LevelRequirement");

    private final int knockbackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");

    private final int knockbackLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.LevelRequirement");

    private static void createDisplay(Material material, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        ((ItemMeta)Objects.<ItemMeta>requireNonNull(meta)).setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        Melee_Skills.setItem(Slot, item);
    }

    public static final Inventory Melee_Skills = Bukkit.createInventory(null, 9, "Melee Skills");

    static {
        int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");
        boolean underFireEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Under_Fire.Enabled");
        int underFireDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurabilityLoss");
        int knocbkackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");
        boolean knockbackEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Knockback.Enabled");
        int knockbackDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurabilityLoss");
        if (underFireEnabled)
            createDisplay(Material.FEATHER, 0, "Under Fire", "Faster for " + underFireDuration + "s cost: " + underFireDurabilityLoss);
        if (knockbackEnabled)
            createDisplay(Material.SHIELD, 1, "Knockback", "Knockback target " + underFireDuration + "s cost: " + knockbackDurabilityLoss);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (inventory == Melee_Skills)
            if (null != clicked) {
                if (clicked.hasItemMeta()) {
                    if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("Under Fire")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        String uuid = player.getUniqueId().toString();
                        PlayerData pd = (PlayerData)API.Players.get(uuid);
                        int level = pd.getData().getInt("Level");
                        if (level >= this.underFireLevelNeeded)
                            if (craftingEvents.isWeapon(itemInMainHand.getType()))
                                nameSpaceKey.setItemWeaponSkillContainer(itemInMainHand, "Under Fire");
                    }
                    if (Objects.requireNonNull(clicked.getItemMeta()).getDisplayName().equalsIgnoreCase("Knockback")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        String uuid = player.getUniqueId().toString();
                        PlayerData pd = API.Players.get(uuid);
                        int level = pd.getData().getInt("Level");
                        if (level >= this.underFireLevelNeeded)
                            if (craftingEvents.isWeapon(itemInMainHand.getType()))
                                nameSpaceKey.setItemWeaponSkillContainer(itemInMainHand, "Knockback");
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
    }
}
