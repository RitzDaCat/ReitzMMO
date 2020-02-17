/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*    */ import java.util.HashMap;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ import org.bukkit.scoreboard.ScoreboardManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class API
/*    */ {
/* 21 */   public static final HashMap<String, PlayerData> Players = new HashMap<>();
/*    */   public static FileConfiguration monsterConfig;
/*    */   public static FileConfiguration playerConfig;
/*    */   public static FileConfiguration debugConfig;
/*    */   public static FileConfiguration worldConfig;
/*    */   public static FileConfiguration partyConfig;
/*    */   public static FileConfiguration weaponskillConfig;
/*    */   public static FileConfiguration custombowConfig;
/*    */   public static FileConfiguration chatConfig;
/*    */   public static FileConfiguration specialMonsterConfig;
/*    */   public static FileConfiguration lootConfig;
/*    */   public static FileConfiguration menuConfig;
/*    */   public static FileConfiguration townConfig;
/*    */   public static Plugin plugin;
/* 35 */   public static Scoreboard sb = ((ScoreboardManager)Objects.<ScoreboardManager>requireNonNull(Bukkit.getScoreboardManager())).getMainScoreboard();
/*    */   
/* 37 */   public static void setMonsterConfig() { monsterConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.monsterHPConfig); }
/* 38 */   public static void setPlayerConfig() { playerConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.playerConfig); } public static void setPartyConfig() {
/* 39 */     partyConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.partyConfig);
/*    */   } public static void setWorldConfig() {
/* 41 */     worldConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.worldConfig);
/*    */   }
/*    */   public static void setDebugConfig() {
/* 44 */     debugConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.debugConfig);
/*    */   }
/* 46 */   public static void setWeaponskillConfig() { weaponskillConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.weaponskillConfig); }
/* 47 */   public static void setcustombowConfig() { custombowConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.customBowConfig); }
/* 48 */   public static void setChatConfig() { chatConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.chatConfig); }
/* 49 */   public static void setSpecialMonsterConfig() { specialMonsterConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.specialMonsterConfig); }
/* 50 */   public static void setLootConfig() { lootConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.lootConfig); }
/* 51 */   public static void setMenuConfig() { menuConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.menuConfig); } public static void setTownConfig() {
/* 52 */     townConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(FileManager.townConfig);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getPlayerDataFromAPI(Player p, String requestedStat) {
/* 58 */     return ((PlayerData)Players.get(p.getUniqueId().toString())).getData().getInt(requestedStat);
/*    */   }
/*    */   
/*    */   public static void setPlayerDataFromAPI(Player p, String requestedStat, int value) {
/* 62 */     ((PlayerData)Players.get(p.getUniqueId().toString())).getData().set(requestedStat, Integer.valueOf(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public static FileConfiguration getTownConfig() {
/* 67 */     return townConfig;
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\API.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */