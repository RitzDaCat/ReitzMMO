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
/*    */ public class DebugConfig
/*    */ {
/*    */   public static void Configuration() {
/* 14 */     File file = FileManager.debugConfig;
/* 15 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 16 */     configuration.options().header("This config is used to set all debug options");
/*    */     
/* 18 */     configuration.addDefault("MonsterAttackingPlayer", Boolean.valueOf(false));
/* 19 */     configuration.addDefault("PlayerAttackingMonster", Boolean.valueOf(false));
/* 20 */     configuration.addDefault("PlayerLevelUp", Boolean.valueOf(false));
/* 21 */     configuration.addDefault("PartyEXP", Boolean.valueOf(false));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 32 */       configuration.save(file);
/*    */     }
/* 34 */     catch (IOException e) {
/*    */ 
/*    */       
/* 37 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\DebugConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */