/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeaponskillConfig
/*    */ {
/*    */   public static void Configuration() {
/* 15 */     File file = FileManager.weaponskillConfig;
/* 16 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 17 */     configuration.options().header("WeaponSkill Settings");
/*    */ 
/*    */     
/* 20 */     configuration.addDefault("Melee", null);
/* 21 */     configuration.addDefault("Melee.WeaponSkills", null);
/* 22 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire", null);
/* 23 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire.Enabled", Boolean.valueOf(true));
/* 24 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire.DurationInSeconds", Integer.valueOf(10));
/* 25 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire.MovementSpeedIncreasePercent", Integer.valueOf(200));
/* 26 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire.LevelRequirement", Integer.valueOf(2));
/* 27 */     configuration.addDefault("Melee.WeaponSkills.Under_Fire.DurabilityLoss", Integer.valueOf(10));
/*    */     
/* 29 */     configuration.addDefault("Melee.WeaponSkills.Knockback", null);
/* 30 */     configuration.addDefault("Melee.WeaponSkills.Knockback.Enabled", Boolean.valueOf(true));
/* 31 */     configuration.addDefault("Melee.WeaponSkills.Knockback.DurationInSeconds", Integer.valueOf(10));
/* 32 */     configuration.addDefault("Melee.WeaponSkills.Knockback.MovementSpeedIncreasePercent", Integer.valueOf(200));
/* 33 */     configuration.addDefault("Melee.WeaponSkills.Knockback.LevelRequirement", Integer.valueOf(2));
/* 34 */     configuration.addDefault("Melee.WeaponSkills.Knockback.DurabilityLoss", Integer.valueOf(10));
/* 35 */     configuration.addDefault("Melee.WeaponSkills.Knockback.KnockbackStrength", Integer.valueOf(10));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 48 */       configuration.save(file);
/*    */     }
/* 50 */     catch (IOException e) {
/*    */ 
/*    */       
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\WeaponskillConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */