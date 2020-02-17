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
/*    */ public class PartyConfig
/*    */ {
/*    */   public static void Configuration() {
/* 14 */     File file = FileManager.partyConfig;
/* 15 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 16 */     configuration.options().header("This config is used to set all party configurations");
/*    */     
/* 18 */     configuration.addDefault("Parties_Enabled", Boolean.valueOf(true));
/* 19 */     configuration.addDefault("PartyEXPMaxDistance", Integer.valueOf(100));
/* 20 */     configuration.addDefault("Party_Scoreboard", null);
/* 21 */     configuration.addDefault("Party_Scoreboard.Enabled", Boolean.valueOf(false));
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
/* 32 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 36 */       configuration.save(file);
/*    */     }
/* 38 */     catch (IOException e) {
/*    */ 
/*    */       
/* 41 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\PartyConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */