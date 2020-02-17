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
/*    */ public class PlayerConfig
/*    */ {
/*    */   public static void Configuration() {
/* 14 */     File file = FileManager.playerConfig;
/* 15 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 16 */     configuration.options().header("This config is used to set all monster health related configurations");
/*    */     
/* 18 */     configuration.addDefault("BossBar", null);
/* 19 */     configuration.addDefault("BossBar.Enabled", Boolean.valueOf(true));
/* 20 */     configuration.addDefault("Music", null);
/* 21 */     configuration.addDefault("Music.BattleMusic", null);
/* 22 */     configuration.addDefault("Music.BattleMusic.Enabled", Boolean.valueOf(false));
/* 23 */     configuration.addDefault("Scaling.World", null);
/* 24 */     configuration.addDefault("Scaling.World.WorldBaseCombatEXP", null);
/* 25 */     configuration.addDefault("Scaling.World.WorldBaseCombatEXP.Base", Integer.valueOf(25));
/* 26 */     configuration.addDefault("Scaling.World.WorldBaseCombatEXP.Multiplier", Integer.valueOf(2));
/* 27 */     configuration.addDefault("Scaling.Player.HealthScale", Integer.valueOf(2));
/* 28 */     configuration.addDefault("Scaling.Player.AttackScale", Integer.valueOf(2));
/* 29 */     configuration.addDefault("Scaling.Player.DefenseScale", Integer.valueOf(2));
/* 30 */     configuration.addDefault("MinimumDamage", null);
/* 31 */     configuration.addDefault("MinimumDamage.Arrow", Integer.valueOf(2));
/*    */ 
/*    */     
/* 34 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 38 */       configuration.save(file);
/*    */     }
/* 40 */     catch (IOException e) {
/*    */ 
/*    */       
/* 43 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\PlayerConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */