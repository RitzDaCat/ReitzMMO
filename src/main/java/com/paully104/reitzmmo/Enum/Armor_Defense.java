/*    */ package com.paully104.reitzmmo.Enum;
/*    */ 
/*    */ 
/*    */ public class Armor_Defense
/*    */ {
/*    */   public enum Armor_Defenses
/*    */   {
/*  8 */     LEATHER_HELMET(1), GOLDEN_HELMET(2), CHAINMAIL_HELMET(2), IRON_HELMET(2), TURTLE_HELMET(2), DIAMOND_HELMET(3),
/*  9 */     LEATHER_CHESTPLATE(3), GOLDEN_CHESTPLATE(5), CHAINMAIL_CHESTPLATE(5), IRON_CHESTPLATE(6), DIAMOND_CHESTPLATE(8),
/* 10 */     LEATHER_LEGGINGS(2), GOLDEN_LEGGINGS(3), CHAINMAIL_LEGGINGS(4), IRON_LEGGINGS(5), DIAMOND_LEGGINGS(6),
/* 11 */     LEATHER_BOOTS(1), GOLDEN_BOOTS(2), CHAINMAIL_BOOTS(1), IRON_BOOTS(2), DIAMOND_BOOTS(3), ELYTRA(0),
/* 12 */     PUMPKIN(0), WHITE_BANNER(0);
/*    */ 
/*    */     
/*    */     private final int value;
/*    */ 
/*    */     
/*    */     Armor_Defenses(int value) {
/* 19 */       this.value = value;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int getValue() {
/* 26 */       return this.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Enum\Armor_Defense.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */