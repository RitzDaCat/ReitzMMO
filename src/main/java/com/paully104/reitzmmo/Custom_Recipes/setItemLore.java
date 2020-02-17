/*    */ package com.paully104.reitzmmo.Custom_Recipes;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class setItemLore
/*    */ {
/*    */   public setItemLore(Item item, String lore) {
/* 13 */     ItemStack itemStack = item.getItemStack();
/* 14 */     ItemMeta itemmeta = itemStack.getItemMeta();
/* 15 */     itemmeta.setLore(Arrays.asList(new String[] { lore }));
/*    */     
/* 17 */     item.setItemStack(itemStack);
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Custom_Recipes\setItemLore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */