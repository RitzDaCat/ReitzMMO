/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TownConfig
/*    */ {
/*    */   public static void Configuration() {
/* 12 */     File file = FileManager.townConfig;
/* 13 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 14 */     configuration.options().header("Town information for Town Menu");
/*    */     
/* 16 */     configuration.addDefault("Towns", null);
/* 17 */     configuration.addDefault("Towns.StartingTown", null);
/* 18 */     configuration.addDefault("Towns.StartingTown.Name", "Starting Town");
/* 19 */     configuration.addDefault("Towns.StartingTown.World", "World");
/* 20 */     configuration.addDefault("Towns.StartingTown.X", Double.valueOf(9.982D));
/* 21 */     configuration.addDefault("Towns.StartingTown.Y", Integer.valueOf(72));
/* 22 */     configuration.addDefault("Towns.StartingTown.Z", Double.valueOf(-56.751D));
/* 23 */     configuration.addDefault("Towns.Lakeshore", null);
/* 24 */     configuration.addDefault("Towns.Lakeshore.Name", "Lakeshore");
/* 25 */     configuration.addDefault("Towns.Lakeshore.World", "World");
/* 26 */     configuration.addDefault("Towns.Lakeshore.X", Double.valueOf(-339.134D));
/* 27 */     configuration.addDefault("Towns.Lakeshore.Y", Integer.valueOf(77));
/* 28 */     configuration.addDefault("Towns.Lakeshore.Z", Double.valueOf(1323.494D));
/*    */     
/* 30 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 34 */       configuration.save(file);
/*    */     }
/* 36 */     catch (IOException e) {
/*    */ 
/*    */       
/* 39 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\TownConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */