/*     */ package com.paully104.reitzmmo.ItemData;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ import org.bukkit.persistence.PersistentDataType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class nameSpaceKey
/*     */ {
/*  19 */   public static NamespacedKey itemDamageKey = new NamespacedKey(API.plugin, "ReitzMMOItemDamageKey");
/*  20 */   public static NamespacedKey itemDefenseKey = new NamespacedKey(API.plugin, "ReitzMMOItemDefenseKey");
/*  21 */   public static NamespacedKey itemWeaponSkillKey = new NamespacedKey(API.plugin, "ReitzMMOWeaponSkillKey");
/*  22 */   public static NamespacedKey itemSpeedKey = new NamespacedKey(API.plugin, "ReitzMMOItemSpeedlKey");
/*  23 */   public static NamespacedKey monsterLevel = new NamespacedKey(API.plugin, "ReitzMMOMonsterLevel");
/*     */   public static NamespacedKey getItemDamageKey() {
/*  25 */     return itemDamageKey;
/*     */   } public static NamespacedKey getItemDefenseKey() {
/*  27 */     return itemDefenseKey;
/*     */   }
/*  29 */   public static NamespacedKey getItemWeaponSkillKey() { return itemWeaponSkillKey; }
/*  30 */   public static NamespacedKey getItemSpeedKey() { return itemSpeedKey; } public static NamespacedKey getMonsterLevelKey() {
/*  31 */     return monsterLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setItemDamageContainer(ItemStack item, int number) {
/*  36 */     ItemMeta meta = item.getItemMeta();
/*  37 */     meta.getPersistentDataContainer().set(itemDamageKey, PersistentDataType.INTEGER, Integer.valueOf(number));
/*  38 */     meta.setLore(Arrays.asList(new String[] { ChatColor.RED + "Bonus Damage: " + number }));
/*  39 */     item.setItemMeta(meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setItemDefenseContainer(ItemStack item, int number) {
/*  47 */     ItemMeta meta = item.getItemMeta();
/*  48 */     meta.getPersistentDataContainer().set(itemDefenseKey, PersistentDataType.INTEGER, Integer.valueOf(number));
/*  49 */     meta.setLore(Arrays.asList(new String[] { ChatColor.BLUE + "Bonus Defense: " + number }));
/*  50 */     item.setItemMeta(meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setSpeedContainer(ItemStack item, int number) {
/*  56 */     ItemMeta meta = item.getItemMeta();
/*  57 */     meta.getPersistentDataContainer().set(itemSpeedKey, PersistentDataType.INTEGER, Integer.valueOf(number));
/*  58 */     meta.setLore(Arrays.asList(new String[] { ChatColor.GREEN + "Bonus Speed: " + number }));
/*  59 */     item.setItemMeta(meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setMonsterLevelContainer(Entity e, int number) {
/*  65 */     e.getPersistentDataContainer().set(getMonsterLevelKey(), PersistentDataType.INTEGER, Integer.valueOf(number));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setItemWeaponSkillContainer(ItemStack item, String skill) {
/*  71 */     ItemMeta meta = item.getItemMeta();
/*     */     
/*  73 */     meta.getPersistentDataContainer().set(itemWeaponSkillKey, PersistentDataType.STRING, skill);
/*  74 */     ArrayList<Object> lore = new ArrayList(0);
/*  75 */     if (null != meta.getLore())
/*     */     {
/*  77 */       for (Object eachLoreLine : meta.getLore()) {
/*     */         
/*  79 */         String eachString = eachLoreLine.toString();
/*  80 */         if (eachString.contains("Weapon")) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*  86 */         lore.add(eachLoreLine);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     lore.add(ChatColor.GOLD + "Weapon Skill: " + skill);
/*  94 */     System.out.println(lore.toString());
/*  95 */     meta.setLore(lore);
/*  96 */     item.setItemMeta(meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getItemDamageFromContainer(ItemStack item) {
/* 105 */     if (item.hasItemMeta()) {
/*     */       
/* 107 */       ItemMeta meta = item.getItemMeta();
/* 108 */       PersistentDataContainer container = meta.getPersistentDataContainer();
/* 109 */       if (container.has(itemDamageKey, PersistentDataType.INTEGER)) {
/*     */         
/* 111 */         int foundvalue = ((Integer)container.get(itemDamageKey, PersistentDataType.INTEGER)).intValue();
/* 112 */         return foundvalue;
/*     */       } 
/* 114 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getItemDefenseFromContainer(ItemStack item) {
/* 124 */     if (null != item) {
/*     */       
/* 126 */       if (item.hasItemMeta()) {
/* 127 */         ItemMeta meta = item.getItemMeta();
/* 128 */         PersistentDataContainer container = meta.getPersistentDataContainer();
/* 129 */         if (container.has(itemDefenseKey, PersistentDataType.INTEGER)) {
/*     */           
/* 131 */           int foundvalue = ((Integer)container.get(itemDefenseKey, PersistentDataType.INTEGER)).intValue();
/* 132 */           return foundvalue;
/*     */         } 
/* 134 */         return 0;
/*     */       } 
/*     */       
/* 137 */       return 0;
/*     */     } 
/*     */     
/* 140 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getItemSpeedFromContainer(ItemStack item) {
/* 144 */     if (null != item) {
/*     */       
/* 146 */       if (item.hasItemMeta()) {
/* 147 */         ItemMeta meta = item.getItemMeta();
/* 148 */         PersistentDataContainer container = meta.getPersistentDataContainer();
/* 149 */         if (container.has(itemSpeedKey, PersistentDataType.INTEGER)) {
/*     */           
/* 151 */           int foundvalue = ((Integer)container.get(itemSpeedKey, PersistentDataType.INTEGER)).intValue();
/* 152 */           return foundvalue;
/*     */         } 
/* 154 */         return 0;
/*     */       } 
/*     */       
/* 157 */       return 0;
/*     */     } 
/*     */     
/* 160 */     return 0;
/*     */   }
/*     */   
/*     */   public static String getItemWeaponSkillFromContainer(ItemStack item) {
/* 164 */     if (null != item) {
/*     */       
/* 166 */       if (item.hasItemMeta()) {
/* 167 */         ItemMeta meta = item.getItemMeta();
/* 168 */         PersistentDataContainer container = meta.getPersistentDataContainer();
/* 169 */         if (container.has(itemWeaponSkillKey, PersistentDataType.STRING)) {
/*     */           
/* 171 */           String foundvalue = (String)container.get(itemWeaponSkillKey, PersistentDataType.STRING);
/* 172 */           return foundvalue;
/*     */         } 
/* 174 */         return "None";
/*     */       } 
/*     */       
/* 177 */       return "None";
/*     */     } 
/*     */     
/* 180 */     return "None";
/*     */   }
/*     */   
/*     */   public static int getMonsterLevelFromContainer(Entity e) {
/* 184 */     if (e.getPersistentDataContainer().has(getMonsterLevelKey(), PersistentDataType.INTEGER))
/*     */     {
/* 186 */       return ((Integer)e.getPersistentDataContainer().get(getMonsterLevelKey(), PersistentDataType.INTEGER)).intValue();
/*     */     }
/*     */     
/* 189 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ItemData\nameSpaceKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */