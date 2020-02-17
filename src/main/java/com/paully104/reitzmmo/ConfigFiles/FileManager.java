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
/*    */ public class FileManager
/*    */ {
/*    */   public static File monsterHPConfig;
/*    */   public static File playerConfig;
/*    */   public static File debugConfig;
/*    */   public static File worldConfig;
/*    */   public static File partyConfig;
/*    */   public static File weaponskillConfig;
/*    */   public static File customBowConfig;
/*    */   public static File chatConfig;
/*    */   public static File specialMonsterConfig;
/*    */   public static File lootConfig;
/*    */   public static File menuConfig;
/*    */   public static File townConfig;
/*    */   
/*    */   public static void FileManagerFiles() {
/* 28 */     monsterHPConfig = new File("plugins/ReitzMMO/MonsterSettings/MonsterConfig.yml");
/* 29 */     playerConfig = new File("plugins/ReitzMMO/PlayerSettings/PlayerConfig.yml");
/* 30 */     debugConfig = new File("plugins/ReitzMMO/DebugSettings/DebugConfig.yml");
/* 31 */     worldConfig = new File("plugins/ReitzMMO/WorldSettings/WorldConfig.yml");
/* 32 */     partyConfig = new File("plugins/ReitzMMO/PartySettings/PartyConfig.yml");
/* 33 */     weaponskillConfig = new File("plugins/ReitzMMO/WeaponSkillSettings/WeaponskillConfig.yml");
/* 34 */     customBowConfig = new File("plugins/ReitzMMO/CustomWeaponSettings/CustomBowConfig.yml");
/* 35 */     chatConfig = new File("plugins/ReitzMMO/ChatSettings/ChatConfig.yml");
/* 36 */     specialMonsterConfig = new File("plugins/ReitzMMO/MonsterSettings/SpecialMonsterConfig.yml");
/* 37 */     lootConfig = new File("plugins/ReitzMMO/LootSettings/LootConfig.yml");
/* 38 */     menuConfig = new File("plugins/ReitzMMO/MenuSettings/MenuConfig.yml");
/* 39 */     townConfig = new File("plugins/ReitzMMO/TownSettings/TownConfig.yml");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void FileManagerSave(File file) {
/* 45 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/*    */     try {
/* 47 */       configuration.save(file);
/* 48 */     } catch (IOException e) {
/*    */       
/* 50 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\FileManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */