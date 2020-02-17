/*    */ package com.paully104.reitzmmo.PlayerCombatRelated;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.boss.BarColor;
/*    */ import org.bukkit.boss.BarStyle;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class createBossBar
/*    */ {
/* 21 */   public static final HashMap<String, BossBar> playerBossBar = new HashMap<>();
/* 22 */   public static final Boolean bossBarEnabled = Boolean.valueOf(API.playerConfig.getBoolean("BossBar.Enabled"));
/*    */ 
/*    */   
/*    */   public static void setBossBaronPlayer(Player p) {
/* 26 */     if (bossBarEnabled.booleanValue()) {
/*    */       
/* 28 */       String uuid = p.getUniqueId().toString();
/* 29 */       BossBar bossbar = Bukkit.createBossBar("MobBar", BarColor.RED, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
/* 30 */       bossbar.setVisible(false);
/* 31 */       bossbar.addPlayer(p);
/* 32 */       playerBossBar.put(uuid, bossbar);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void updateBossBaronPlayer(Player p, LivingEntity le, int damagedone) {
/* 39 */     if (!(le instanceof org.bukkit.entity.ArmorStand) && bossBarEnabled.booleanValue()) {
/* 40 */       String uuid = p.getUniqueId().toString();
/* 41 */       ((BossBar)playerBossBar.get(uuid)).setTitle(le.getCustomName());
/*    */ 
/*    */       
/* 44 */       double percentHealth = (le.getHealth() - damagedone) / le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
/* 45 */       if (percentHealth < 0.0D)
/*    */       {
/* 47 */         percentHealth = 0.0D;
/*    */       }
/* 49 */       ((BossBar)playerBossBar.get(uuid)).setProgress(percentHealth);
/* 50 */       ((BossBar)playerBossBar.get(uuid)).setVisible(true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void removeBossBaronPlayer(Player p) {
/* 59 */     if (bossBarEnabled.booleanValue()) {
/* 60 */       String uuid = p.getUniqueId().toString();
/* 61 */       ((BossBar)playerBossBar.get(uuid)).setVisible(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void deleteBossBaronPlayer(Player p) {
/* 67 */     if (bossBarEnabled.booleanValue()) {
/* 68 */       String uuid = p.getUniqueId().toString();
/* 69 */       ((BossBar)playerBossBar.get(uuid)).removeAll();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlayerCombatRelated\createBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */