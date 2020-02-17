/*    */ package com.paully104.reitzmmo.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Weaponskill_Item_Check
/*    */ {
/*    */   public enum Weaponskill_Check
/*    */   {
/* 10 */     WOOD_SWORD, GOLD_SWORD, STONE_SWORD, IRON_SWORD, DIAMOND_SWORD,
/* 11 */     WOOD_AXE, GOLD_AXE, STONE_AXE, IRON_AXE, DIAMOND_AXE,
/* 12 */     WOOD_SPADE, GOLD_SPADE, STONE_SPADE, IRON_SPADE, DIAMOND_SPADE,
/* 13 */     WOOD_HOE, GOLD_HOE, STONE_HOE, IRON_HOE, DIAMOND_HOE;
/*    */     private final boolean value;
/*    */     
/*    */     Weaponskill_Check() {
/* 17 */       this.value = true;
/*    */     }
/*    */     
/*    */     public boolean getValue() {
/* 21 */       return this.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Enum\Weaponskill_Item_Check.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */