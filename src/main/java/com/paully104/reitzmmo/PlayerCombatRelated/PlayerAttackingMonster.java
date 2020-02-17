/*     */ package com.paully104.reitzmmo.PlayerCombatRelated;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.Enum.Weapon_Damage;
/*     */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.boss.BossBar;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerAttackingMonster
/*     */   implements Listener
/*     */ {
/*  32 */   public static List<String> playerHasMusic = new ArrayList<>();
/*  33 */   private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");
/*  34 */   private final boolean namePlatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");
/*  35 */   private final int bowMinimumDamage = API.playerConfig.getInt("MinimumDamage.Arrow");
/*  36 */   private final boolean battleMusicEnabled = API.playerConfig.getBoolean("Music.BattleMusic.Enabled");
/*     */   
/*     */   private static final String ATTACK = "Attack";
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void playerAttackingMonster(EntityDamageByEntityEvent e) {
/*  43 */     int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(e.getEntity().getLocation().getWorld())).getName());
/*  44 */     if (worldEnabled != -1) {
/*     */ 
/*     */ 
/*     */       
/*  48 */       Entity defender = e.getEntity();
/*  49 */       Entity attacker = e.getDamager();
/*     */ 
/*     */       
/*  52 */       if (!(defender instanceof Player) && !defender.hasMetadata("NPC")) {
/*     */ 
/*     */         
/*  55 */         int player_attack = 0;
/*  56 */         int monster_defense = 0;
/*  57 */         int damage_done = 0;
/*  58 */         int weaponDamage = 0;
/*  59 */         int weaponBonus = 0;
/*  60 */         int totalDamage = 0;
/*     */ 
/*     */         
/*  63 */         if (attacker instanceof Player) {
/*     */           String monster_level_from_name;
/*  65 */           Player p = (Player)attacker;
/*  66 */           if (!playerHasMusic.contains(p.getUniqueId().toString()) && this.battleMusicEnabled) {
/*  67 */             p.playSound(p.getLocation(), Sound.MUSIC_DISC_11, 100.0F, 1.0F);
/*  68 */             playerHasMusic.add(p.getUniqueId().toString());
/*     */           } 
/*  70 */           PlayerData pd = (PlayerData)API.Players.get(attacker.getUniqueId().toString());
/*  71 */           player_attack = pd.getData().getInt("Attack");
/*     */           try {
/*  73 */             monster_level_from_name = ((String)Objects.<String>requireNonNull(defender.getCustomName())).replaceAll("\\D+", "");
/*  74 */           } catch (NullPointerException error) {
/*  75 */             String levelColor = ChatColor.YELLOW + "[" + '\001' + "]";
/*  76 */             defender.setCustomName(e.getEntityType() + levelColor);
/*  77 */             monster_level_from_name = "1";
/*  78 */             if (this.namePlatesEnabled) {
/*  79 */               defender.setCustomNameVisible(true);
/*     */             }
/*     */           } 
/*  82 */           monster_defense = Integer.parseInt(monster_level_from_name);
/*     */           
/*  84 */           HumanEntity human = (HumanEntity)attacker;
/*     */ 
/*     */           
/*  87 */           if (!human.getInventory().getItemInMainHand().toString().contains("AIR")) {
/*     */             try {
/*  89 */               weaponDamage = Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().name()).getValue();
/*  90 */             } catch (IllegalArgumentException ignored) {
/*  91 */               weaponDamage = 0;
/*     */             
/*     */             }
/*     */             finally {
/*     */               
/*  96 */               weaponBonus = nameSpaceKey.getItemDamageFromContainer(human.getInventory().getItemInMainHand());
/*  97 */               totalDamage = weaponDamage + weaponBonus + player_attack - monster_defense;
/*  98 */               if (totalDamage < 1) {
/*  99 */                 totalDamage = 1;
/*     */               }
/*     */               
/* 102 */               e.setDamage(totalDamage);
/*     */             }
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 108 */             totalDamage = weaponDamage + weaponBonus + player_attack - monster_defense;
/* 109 */             if (totalDamage < 1) {
/* 110 */               totalDamage = 1;
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 117 */           e.setDamage(totalDamage);
/*     */ 
/*     */ 
/*     */           
/* 121 */           String uuid = p.getUniqueId().toString();
/* 122 */           BossBar bar = createBossBar.playerBossBar.get(uuid);
/* 123 */           createBossBar.updateBossBaronPlayer((Player)attacker, (LivingEntity)defender, damage_done);
/* 124 */           Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> createBossBar.removeBossBaronPlayer(p), 300L);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 131 */         if (attacker instanceof Arrow) {
/*     */           
/* 133 */           Arrow arrow = (Arrow)attacker;
/* 134 */           if (arrow.getShooter() instanceof Player) {
/*     */             String monster_level_from_name;
/*     */             
/* 137 */             Player p = (Player)arrow.getShooter();
/* 138 */             double damage = e.getDamage();
/* 139 */             PlayerData pd = (PlayerData)API.Players.get(p.getUniqueId().toString());
/* 140 */             player_attack = pd.getData().getInt("Attack");
/*     */ 
/*     */             
/*     */             try {
/* 144 */               monster_level_from_name = ((String)Objects.<String>requireNonNull(defender.getCustomName())).replaceAll("\\D+", "");
/* 145 */             } catch (NullPointerException error) {
/*     */               
/* 147 */               String levelColor = ChatColor.YELLOW + "[" + '\001' + "]";
/* 148 */               defender.setCustomName(e.getEntityType() + levelColor);
/* 149 */               monster_level_from_name = "1";
/* 150 */               if (this.namePlatesEnabled) {
/* 151 */                 defender.setCustomNameVisible(true);
/*     */               }
/*     */             } 
/*     */             
/*     */             try {
/* 156 */               monster_defense = Integer.parseInt(monster_level_from_name);
/*     */             }
/* 158 */             catch (NumberFormatException f) {
/*     */               
/* 160 */               monster_defense = 0;
/*     */             } 
/*     */             
/* 163 */             damage = damage + player_attack - monster_defense;
/* 164 */             if (damage < this.bowMinimumDamage)
/*     */             {
/* 166 */               damage = this.bowMinimumDamage + 0.0D;
/*     */             }
/* 168 */             e.setDamage(damage);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlayerCombatRelated\PlayerAttackingMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */