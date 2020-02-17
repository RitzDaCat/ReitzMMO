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
/*    */ public class WorldConfig
/*    */ {
/*    */   public static void Configuration() {
/* 15 */     File file = FileManager.worldConfig;
/* 16 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 17 */     configuration.options().header("This config is used to set all world levels configurations.\n Use -1 to make the plugin disabled on a world");
/*    */     
/* 19 */     configuration.addDefault("world", Integer.valueOf(1));
/* 20 */     configuration.addDefault("world_nether", Integer.valueOf(15));
/* 21 */     configuration.addDefault("world_the_end", Integer.valueOf(30));
/* 22 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 26 */       configuration.save(file);
/*    */     }
/* 28 */     catch (IOException e) {
/*    */ 
/*    */       
/* 31 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\WorldConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */