/*    */ package com.paully104.reitzmmo.PlayerData;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   private final String uuid;
/*    */   private final FileConfiguration config;
/*    */   private File file;
/*    */   private static File dir;
/*    */   private static Plugin plugin;
/*    */   private boolean debug;
/*    */   
/*    */   public static void setup(Plugin instance) {
/* 35 */     plugin = instance;
/* 36 */     dir = new File(plugin.getDataFolder() + File.separator + "Players");
/* 37 */     if (!dir.exists()) {
/*    */       
/* 39 */       dir.mkdir();
/* 40 */       plugin.getLogger().fine("The player data directories have been setup.");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PlayerData(String uuid) {
/* 51 */     this.uuid = uuid;
/* 52 */     this.file = new File(dir + File.separator + this.uuid + ".yml");
/* 53 */     if (this.file.exists()) {
/* 54 */       this.file = new File(dir + File.separator + this.uuid + ".yml");
/*    */       
/*    */       try {
/* 57 */         this.file.createNewFile();
/* 58 */       } catch (IOException e) {
/* 59 */         plugin.getLogger().severe("The data file for " + this.uuid + " could not be created! Reason: " + e.getMessage());
/*    */       } 
/*    */     } 
/* 62 */     this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FileConfiguration getData() {
/* 71 */     return this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public File getFile() {
/* 80 */     return this.file;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void save() {
/*    */     try {
/* 90 */       this.config.save(this.file);
/* 91 */     } catch (IOException e) {
/* 92 */       plugin.getLogger().severe("The data file for " + this.uuid + " could not be saved! Reason: " + e.getMessage());
/* 93 */       if (this.debug)
/* 94 */         e.printStackTrace(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlayerData\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */