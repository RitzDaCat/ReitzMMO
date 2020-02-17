package io.github.paulanthonyreitz.reitzmmo.Hologram;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnChest {
    private final int chestTimeUntilDisappear = API.lootConfig.getInt("General.BonusChest.TimeUntilDisappear");

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

    ItemStack wooden_sword = new ItemStack(Material.WOODEN_SWORD, 1);

    ItemStack gold_sword = new ItemStack(Material.GOLDEN_SWORD, 1);

    ItemStack stone_sword = new ItemStack(Material.STONE_SWORD, 1);

    ItemStack iron_sword = new ItemStack(Material.IRON_SWORD, 1);

    ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);

    public void setChest(World w, Location location, final String name, int monster_level) {
        List<ItemStack> originalWeapons = new ArrayList<>();
        originalWeapons.add(this.wooden_sword);
        originalWeapons.add(this.gold_sword);
        originalWeapons.add(this.stone_sword);
        originalWeapons.add(this.iron_sword);
        originalWeapons.add(this.diamond_sword);
        for (ItemStack item : originalWeapons)
            nameSpaceKey.setItemDamageContainer(item, monster_level);
        final Block block = location.getBlock();
        block.setType(Material.CHEST);
        final Chest chest = (Chest)block.getState();
        int random = ThreadLocalRandom.current().nextInt(0, 101);
        if (this.wooden_swordEnabled && this.wooden_swordPercentChance > random)
            chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(0) });
        int random2 = ThreadLocalRandom.current().nextInt(0, 101);
        if (this.gold_swordEnabled && this.gold_swordPercentChance > random2)
            chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(1) });
        int random3 = ThreadLocalRandom.current().nextInt(0, 101);
        if (this.stone_swordEnabled && this.stone_swordPercentChance > random3)
            chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(2) });
        int random4 = ThreadLocalRandom.current().nextInt(0, 101);
        if (this.iron_swordEnabled && this.iron_swordPercentChance > random4)
            chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(3) });
        int random5 = ThreadLocalRandom.current().nextInt(0, 101);
        if (this.diamond_swordEnabled && this.diamond_swordPercentChance > random5)
            chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(4) });
        final ArmorStand a = (ArmorStand)w.spawnEntity(location.add(0.0D, 0.5D, 0.0D), EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        long chestDisappearTime = this.chestTimeUntilDisappear * 20L;
        long start = System.currentTimeMillis();
        final long end = (this.chestTimeUntilDisappear * 1000) + start;
        Objects.requireNonNull(a);
        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove, chestDisappearTime);
        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
            public void run() {
                chest.getInventory().clear();
                block.setType(Material.AIR);
            }
        },  chestDisappearTime);
        (new BukkitRunnable() {
            public void run() {
                long timeRemaining = (end - System.currentTimeMillis()) / 1000L;
                a.setCustomName(ChatColor.GOLD + name + ChatColor.RED + "[" + timeRemaining + "]");
                a.setCustomNameVisible(true);
                if (timeRemaining <= 0L)
                    cancel();
            }
        }).runTaskTimer(API.plugin, 0L, 20L);
    }
}
