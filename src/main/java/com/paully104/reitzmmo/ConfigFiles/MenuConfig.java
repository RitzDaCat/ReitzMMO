/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MenuConfig
/*    */ {
/*    */   public static void Configuration() {
/* 12 */     File file = FileManager.menuConfig;
/* 13 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 14 */     configuration.options().header("This config is used to configure the GUI menu options");
/*    */     
/* 16 */     configuration.addDefault("TeleportHomeEnabled", Boolean.valueOf(true));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 30 */       configuration.save(file);
/*    */     }
/* 32 */     catch (IOException e) {
/*    */ 
/*    */       
/* 35 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\MenuConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */