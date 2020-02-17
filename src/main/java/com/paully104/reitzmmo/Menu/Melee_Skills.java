/*     */ package com.paully104.reitzmmo.Menu;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.ItemData.craftingEvents;
/*     */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Melee_Skills
/*     */   implements Listener
/*     */ {
/*  25 */   private final int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");
/*  26 */   private final int underFireLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.LevelRequirement");
/*  27 */   private final int knockbackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");
/*  28 */   private final int knockbackLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.LevelRequirement");
/*     */   private static void createDisplay(Material material, int Slot, String name, String lore) {
/*  30 */     ItemStack item = new ItemStack(material);
/*  31 */     ItemMeta meta = item.getItemMeta();
/*  32 */     ((ItemMeta)Objects.<ItemMeta>requireNonNull(meta)).setDisplayName(name);
/*  33 */     ArrayList<String> Lore = new ArrayList<>();
/*  34 */     Lore.add(lore);
/*  35 */     meta.setLore(Lore);
/*  36 */     item.setItemMeta(meta);
/*     */     
/*  38 */     Melee_Skills.setItem(Slot, item);
/*     */   }
/*  40 */   public static final Inventory Melee_Skills = Bukkit.createInventory(null, 9, "Melee Skills");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  46 */     int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");
/*  47 */     boolean underFireEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Under_Fire.Enabled");
/*  48 */     int underFireDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurabilityLoss");
/*     */     
/*  50 */     int knocbkackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");
/*  51 */     boolean knockbackEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Knockback.Enabled");
/*  52 */     int knockbackDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurabilityLoss");
/*  53 */     if (underFireEnabled) {
/*  54 */       createDisplay(Material.FEATHER, 0, "Under Fire", "Faster for " + underFireDuration + "s cost: " + underFireDurabilityLoss);
/*     */     }
/*  56 */     if (knockbackEnabled)
/*     */     {
/*  58 */       createDisplay(Material.SHIELD, 1, "Knockback", "Knockback target " + underFireDuration + "s cost: " + knockbackDurabilityLoss);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent event) {
/*  64 */     Player player = (Player)event.getWhoClicked();
/*  65 */     ItemStack clicked = event.getCurrentItem();
/*  66 */     Inventory inventory = event.getInventory();
/*  67 */     ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
/*  68 */     if (inventory == Melee_Skills)
/*     */     {
/*  70 */       if (null != clicked) {
/*     */         
/*  72 */         if (clicked.hasItemMeta())
/*     */         {
/*  74 */           if (((ItemMeta)Objects.<ItemMeta>requireNonNull(clicked.getItemMeta())).getDisplayName().equalsIgnoreCase("Under Fire")) {
/*     */             
/*  76 */             event.setCancelled(true);
/*  77 */             player.closeInventory();
/*  78 */             String uuid = player.getUniqueId().toString();
/*  79 */             PlayerData pd = (PlayerData)API.Players.get(uuid);
/*  80 */             int level = pd.getData().getInt("Level");
/*  81 */             if (level >= this.underFireLevelNeeded)
/*     */             {
/*  83 */               if (craftingEvents.isWeapon(itemInMainHand.getType())) {
/*  84 */                 nameSpaceKey.setItemWeaponSkillContainer(itemInMainHand, "Under Fire");
/*     */               }
/*     */             }
/*     */           } 
/*  88 */           if (((ItemMeta)Objects.<ItemMeta>requireNonNull(clicked.getItemMeta())).getDisplayName().equalsIgnoreCase("Knockback"))
/*     */           {
/*  90 */             event.setCancelled(true);
/*  91 */             player.closeInventory();
/*  92 */             String uuid = player.getUniqueId().toString();
/*  93 */             PlayerData pd = (PlayerData)API.Players.get(uuid);
/*  94 */             int level = pd.getData().getInt("Level");
/*  95 */             if (level >= this.underFireLevelNeeded)
/*     */             {
/*  97 */               if (craftingEvents.isWeapon(itemInMainHand.getType())) {
/*  98 */                 nameSpaceKey.setItemWeaponSkillContainer(itemInMainHand, "Knockback");
/*     */               }
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 106 */           event.setCancelled(true);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 111 */         event.setCancelled(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Menu\Melee_Skills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */