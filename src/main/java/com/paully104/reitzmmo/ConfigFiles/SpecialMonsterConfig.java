/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpecialMonsterConfig
/*    */ {
/*    */   public static void Configuration() {
/* 12 */     File file = FileManager.specialMonsterConfig;
/* 13 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 14 */     configuration.options().header("This config is used to configure special monsters");
/*    */     
/* 16 */     configuration.addDefault("specialMonsterGlowEnabled", Boolean.valueOf(true));
/* 17 */     configuration.addDefault("specialMonsterSilentEnabled", Boolean.valueOf(true));
/* 18 */     configuration.addDefault("kingMobsEnabled", Boolean.valueOf(true));
/* 19 */     configuration.addDefault("kingMobsLVDifference", Integer.valueOf(25));
/*    */     
/* 21 */     configuration.addDefault("notoriousMobsEnabled", Boolean.valueOf(true));
/* 22 */     configuration.addDefault("notoriousMobsLVDifference", Integer.valueOf(15));
/*    */     
/* 24 */     configuration.addDefault("devilishMobsEnabled", Boolean.valueOf(true));
/* 25 */     configuration.addDefault("devilishMobsLVDifference", Integer.valueOf(5));
/*    */     
/* 27 */     configuration.addDefault("dumbMobsEnabled", Boolean.valueOf(true));
/* 28 */     configuration.addDefault("dumbMobsLVDifference", Integer.valueOf(-5));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 42 */       configuration.save(file);
/*    */     }
/* 44 */     catch (IOException e) {
/*    */ 
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\SpecialMonsterConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */