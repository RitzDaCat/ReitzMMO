/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatConfig
/*    */ {
/*    */   public static void Configuration() {
/* 12 */     File file = FileManager.chatConfig;
/* 13 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 14 */     configuration.options().header("This config is used to configure chat output from the plugin");
/*    */     
/* 16 */     configuration.addDefault("expHologramsEnabled", Boolean.valueOf(true));
/* 17 */     configuration.addDefault("expChatEnabled", Boolean.valueOf(true));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 31 */       configuration.save(file);
/*    */     }
/* 33 */     catch (IOException e) {
/*    */ 
/*    */       
/* 36 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\ChatConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */