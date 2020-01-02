package com.paully104.reitzmmo.Hologram;
import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.ItemData.nameSpaceKey;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpawnChest {

    //Chest Time
    private final int chestTimeUntilDisappear = API.lootConfig.getInt("General.BonusChest.TimeUntilDisappear");

    //If Weapons are enabled
    private final boolean wooden_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.wooden_sword.Enabled");
    private final int wooden_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.wooden_sword.PercentChance");
    private final boolean gold_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.gold_sword.Enabled");
    private final int gold_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.gold_sword.PercentChance");
    private final boolean stone_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.stone_sword.Enabled");
    private final int stone_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.stone_sword.PercentChance");
    private final boolean iron_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.iron_sword.Enabled");
    private final int iron_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.iron_sword.PercentChance");
    private final boolean diamond_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.diamond_sword.Enabled");
    private final int diamond_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.diamond_sword.PercentChance");


    //ItemStacks
    ItemStack wooden_sword = new ItemStack(Material.WOODEN_SWORD, 1);
    ItemStack gold_sword = new ItemStack(Material.GOLDEN_SWORD, 1);
    ItemStack stone_sword = new ItemStack(Material.STONE_SWORD, 1);
    ItemStack iron_sword = new ItemStack(Material.IRON_SWORD, 1);
    ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);

    public void setChest(World w, Location location, String name,int monster_level) {


        List<ItemStack> originalWeapons = new ArrayList<>();
        originalWeapons.add(wooden_sword);
        originalWeapons.add(gold_sword);
        originalWeapons.add(stone_sword);
        originalWeapons.add(iron_sword);
        originalWeapons.add(diamond_sword);


        for(ItemStack item : originalWeapons)
        {
            nameSpaceKey.setItemDamageContainer(item,monster_level);

        }

        Block block = location.getBlock();
        block.setType(Material.CHEST);
        org.bukkit.block.Chest chest = (org.bukkit.block.Chest)block.getState();
        int random = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(wooden_swordEnabled && wooden_swordPercentChance > random)
        {
            chest.getInventory().addItem(originalWeapons.get(0));
        }
        int random2 = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(gold_swordEnabled && gold_swordPercentChance > random2)
        {
            chest.getInventory().addItem(originalWeapons.get(1));
        }
        int random3 = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(stone_swordEnabled && stone_swordPercentChance > random3)
        {
            chest.getInventory().addItem(originalWeapons.get(2));
        }
        int random4 = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(iron_swordEnabled && iron_swordPercentChance > random4)
        {
            chest.getInventory().addItem(originalWeapons.get(3));
        }
        int random5 = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(diamond_swordEnabled && diamond_swordPercentChance > random5)
        {
            chest.getInventory().addItem(originalWeapons.get(4));
        }



        //spawn an armorstand where the chest is at

        ArmorStand a = (ArmorStand)w.spawnEntity(location.add(0,.5,0), EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        long chestDisappearTime = chestTimeUntilDisappear *20L;
        long start = System.currentTimeMillis();
        long end = (chestTimeUntilDisappear * 1000) + start;



        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove,chestDisappearTime);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
            public void run() {
                chest.getInventory().clear();
                block.setType(Material.AIR);
            }
        }, chestDisappearTime);


        new BukkitRunnable() {
            public void run() {
                long timeRemaining = (end - System.currentTimeMillis())/1000;
                a.setCustomName(ChatColor.GOLD+ name +ChatColor.RED+ "[" + timeRemaining + "]");
                a.setCustomNameVisible(true);

                if(timeRemaining <= 0)
                {
                    this.cancel();

                }
            }
        }.runTaskTimer(API.plugin, 0L, 20L);









    }


}

