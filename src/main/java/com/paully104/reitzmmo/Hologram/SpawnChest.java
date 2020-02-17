/*     */ package com.paully104.reitzmmo.Hologram;
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Chest;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class SpawnChest {
/*  18 */   private final int chestTimeUntilDisappear = API.lootConfig.getInt("General.BonusChest.TimeUntilDisappear");
/*     */ 
/*     */   
/*  21 */   private final boolean wooden_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.wooden_sword.Enabled");
/*  22 */   private final int wooden_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.wooden_sword.PercentChance");
/*  23 */   private final boolean gold_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.gold_sword.Enabled");
/*  24 */   private final int gold_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.gold_sword.PercentChance");
/*  25 */   private final boolean stone_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.stone_sword.Enabled");
/*  26 */   private final int stone_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.stone_sword.PercentChance");
/*  27 */   private final boolean iron_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.iron_sword.Enabled");
/*  28 */   private final int iron_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.iron_sword.PercentChance");
/*  29 */   private final boolean diamond_swordEnabled = API.lootConfig.getBoolean("General.BonusChest.Items.diamond_sword.Enabled");
/*  30 */   private final int diamond_swordPercentChance = API.lootConfig.getInt("General.BonusChest.Items.diamond_sword.PercentChance");
/*     */ 
/*     */ 
/*     */   
/*  34 */   ItemStack wooden_sword = new ItemStack(Material.WOODEN_SWORD, 1);
/*  35 */   ItemStack gold_sword = new ItemStack(Material.GOLDEN_SWORD, 1);
/*  36 */   ItemStack stone_sword = new ItemStack(Material.STONE_SWORD, 1);
/*  37 */   ItemStack iron_sword = new ItemStack(Material.IRON_SWORD, 1);
/*  38 */   ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChest(World w, Location location, final String name, int monster_level) {
/*  43 */     List<ItemStack> originalWeapons = new ArrayList<>();
/*  44 */     originalWeapons.add(this.wooden_sword);
/*  45 */     originalWeapons.add(this.gold_sword);
/*  46 */     originalWeapons.add(this.stone_sword);
/*  47 */     originalWeapons.add(this.iron_sword);
/*  48 */     originalWeapons.add(this.diamond_sword);
/*     */ 
/*     */     
/*  51 */     for (ItemStack item : originalWeapons)
/*     */     {
/*  53 */       nameSpaceKey.setItemDamageContainer(item, monster_level);
/*     */     }
/*     */ 
/*     */     
/*  57 */     final Block block = location.getBlock();
/*  58 */     block.setType(Material.CHEST);
/*  59 */     final Chest chest = (Chest)block.getState();
/*  60 */     int random = ThreadLocalRandom.current().nextInt(0, 101);
/*  61 */     if (this.wooden_swordEnabled && this.wooden_swordPercentChance > random)
/*     */     {
/*  63 */       chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(0) });
/*     */     }
/*  65 */     int random2 = ThreadLocalRandom.current().nextInt(0, 101);
/*  66 */     if (this.gold_swordEnabled && this.gold_swordPercentChance > random2)
/*     */     {
/*  68 */       chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(1) });
/*     */     }
/*  70 */     int random3 = ThreadLocalRandom.current().nextInt(0, 101);
/*  71 */     if (this.stone_swordEnabled && this.stone_swordPercentChance > random3)
/*     */     {
/*  73 */       chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(2) });
/*     */     }
/*  75 */     int random4 = ThreadLocalRandom.current().nextInt(0, 101);
/*  76 */     if (this.iron_swordEnabled && this.iron_swordPercentChance > random4)
/*     */     {
/*  78 */       chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(3) });
/*     */     }
/*  80 */     int random5 = ThreadLocalRandom.current().nextInt(0, 101);
/*  81 */     if (this.diamond_swordEnabled && this.diamond_swordPercentChance > random5)
/*     */     {
/*  83 */       chest.getInventory().addItem(new ItemStack[] { originalWeapons.get(4) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     final ArmorStand a = (ArmorStand)w.spawnEntity(location.add(0.0D, 0.5D, 0.0D), EntityType.ARMOR_STAND);
/*  91 */     a.setVisible(false);
/*  92 */     a.setGravity(false);
/*  93 */     long chestDisappearTime = this.chestTimeUntilDisappear * 20L;
/*  94 */     long start = System.currentTimeMillis();
/*  95 */     final long end = (this.chestTimeUntilDisappear * 1000) + start;
/*     */ 
/*     */ 
/*     */     
/*  99 */     Objects.requireNonNull(a); Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove, chestDisappearTime);
/*     */     
/* 101 */     Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
/*     */           public void run() {
/* 103 */             chest.getInventory().clear();
/* 104 */             block.setType(Material.AIR);
/*     */           }
/*     */         },  chestDisappearTime);
/*     */ 
/*     */     
/* 109 */     (new BukkitRunnable() {
/*     */         public void run() {
/* 111 */           long timeRemaining = (end - System.currentTimeMillis()) / 1000L;
/* 112 */           a.setCustomName(ChatColor.GOLD + name + ChatColor.RED + "[" + timeRemaining + "]");
/* 113 */           a.setCustomNameVisible(true);
/*     */           
/* 115 */           if (timeRemaining <= 0L)
/*     */           {
/* 117 */             cancel();
/*     */           
/*     */           }
/*     */         }
/* 121 */       }).runTaskTimer(API.plugin, 0L, 20L);
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Hologram\SpawnChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */