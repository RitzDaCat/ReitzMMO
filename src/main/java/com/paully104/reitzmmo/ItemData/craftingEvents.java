/*    */ package com.paully104.reitzmmo.ItemData;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.CraftItemEvent;
/*    */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class craftingEvents
/*    */   implements Listener
/*    */ {
/*    */   private static final String LEVEL = "Level";
/*    */   
/*    */   public static boolean isArmour(Material type) {
/* 21 */     if (type.name().contains("BOOT") || type.name().contains("LEGGING") || type
/* 22 */       .name().contains("CHESTPLATE") || type.name().contains("HELMET") || type.name().contains("CAP"))
/*    */     {
/* 24 */       return true;
/*    */     }
/*    */     
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isWeapon(Material type) {
/* 32 */     if (type.name().contains("AXE") || type.name().contains("SWORD") || type
/* 33 */       .name().contains("TRIDENT") || type.name().contains("BOW"))
/*    */     {
/* 35 */       return true;
/*    */     }
/*    */     
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerCrafting(PrepareItemCraftEvent event) {}
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerUse(CraftItemEvent event) {
/* 51 */     if (isArmour(event.getRecipe().getResult().getType())) {
/*    */       
/* 53 */       Player p = (Player)event.getWhoClicked();
/* 54 */       int level = ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().getInt("Level");
/* 55 */       int rnd = (new Random()).nextInt(level);
/*    */       
/* 57 */       nameSpaceKey.setItemDefenseContainer(event.getInventory().getResult(), rnd);
/*    */ 
/*    */     
/*    */     }
/* 61 */     else if (isWeapon(event.getRecipe().getResult().getType())) {
/*    */       
/* 63 */       Player p = (Player)event.getWhoClicked();
/* 64 */       int level = ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().getInt("Level");
/* 65 */       int rnd = (new Random()).nextInt(level);
/*    */       
/* 67 */       nameSpaceKey.setItemDamageContainer(event.getInventory().getResult(), rnd);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ItemData\craftingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */