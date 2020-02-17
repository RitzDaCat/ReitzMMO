/*    */ package com.paully104.reitzmmo.ConfigFiles;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootConfig
/*    */ {
/*    */   public static void Configuration() {
/* 12 */     File file = FileManager.lootConfig;
/* 13 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/* 14 */     configuration.options().header("This config is used to configure the loot table of mobs by level and mob type and you can add 1 item");
/*    */     
/* 16 */     configuration.addDefault("General", null);
/* 17 */     configuration.addDefault("General.MobsDropAttackUpItems", Boolean.valueOf(true));
/* 18 */     configuration.addDefault("General.MobsDropAttackUpItems.Enabled", Boolean.valueOf(true));
/* 19 */     configuration.addDefault("General.MobsDropAttackUpItems.PercentChance", Integer.valueOf(25));
/*    */     
/* 21 */     configuration.addDefault("General.BonusChest", null);
/* 22 */     configuration.addDefault("General.BonusChest.Enabled", Boolean.valueOf(true));
/* 23 */     configuration.addDefault("General.BonusChest.PercentChance", Integer.valueOf(5));
/* 24 */     configuration.addDefault("General.BonusChest.TimeUntilDisappear", Integer.valueOf(45));
/*    */ 
/*    */     
/* 27 */     configuration.addDefault("General.BonusChest.Items", Boolean.valueOf(true));
/*    */     
/* 29 */     configuration.addDefault("General.BonusChest.Items.wooden_sword", Boolean.valueOf(true));
/* 30 */     configuration.addDefault("General.BonusChest.Items.wooden_sword.Enabled", Boolean.valueOf(true));
/* 31 */     configuration.addDefault("General.BonusChest.Items.wooden_sword.PercentChance", Integer.valueOf(25));
/*    */     
/* 33 */     configuration.addDefault("General.BonusChest.Items.gold_sword", Boolean.valueOf(true));
/* 34 */     configuration.addDefault("General.BonusChest.Items.gold_sword.Enabled", Boolean.valueOf(true));
/* 35 */     configuration.addDefault("General.BonusChest.Items.gold_sword.PercentChance", Integer.valueOf(25));
/*    */     
/* 37 */     configuration.addDefault("General.BonusChest.Items.stone_sword", Boolean.valueOf(true));
/* 38 */     configuration.addDefault("General.BonusChest.Items.stone_sword.Enabled", Boolean.valueOf(true));
/* 39 */     configuration.addDefault("General.BonusChest.Items.stone_sword.PercentChance", Integer.valueOf(25));
/*    */     
/* 41 */     configuration.addDefault("General.BonusChest.Items.iron_sword", Boolean.valueOf(true));
/* 42 */     configuration.addDefault("General.BonusChest.Items.iron_sword.Enabled", Boolean.valueOf(true));
/* 43 */     configuration.addDefault("General.BonusChest.Items.iron_sword.PercentChance", Integer.valueOf(25));
/*    */     
/* 45 */     configuration.addDefault("General.BonusChest.Items.diamond_sword", Boolean.valueOf(true));
/* 46 */     configuration.addDefault("General.BonusChest.Items.diamond_sword.Enabled", Boolean.valueOf(true));
/* 47 */     configuration.addDefault("General.BonusChest.Items.diamond_sword.PercentChance", Integer.valueOf(25));
/*    */ 
/*    */     
/* 50 */     configuration.addDefault("1.ZOMBIE", Integer.valueOf(1));
/* 51 */     configuration.addDefault("1.ZOMBIE.chance", Integer.valueOf(10));
/* 52 */     configuration.addDefault("1.ZOMBIE.item", "BUCKET");
/*    */     
/* 54 */     configuration.addDefault("2.ZOMBIE", Integer.valueOf(2));
/* 55 */     configuration.addDefault("2.ZOMBIE.chance", Integer.valueOf(10));
/* 56 */     configuration.addDefault("2.ZOMBIE.item", "BREAD");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     configuration.options().copyDefaults(true);
/*    */ 
/*    */     
/*    */     try {
/* 70 */       configuration.save(file);
/*    */     }
/* 72 */     catch (IOException e) {
/*    */ 
/*    */       
/* 75 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\LootConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */