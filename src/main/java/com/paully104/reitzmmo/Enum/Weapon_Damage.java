/*    */ package com.paully104.reitzmmo.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Weapon_Damage
/*    */ {
/*    */   public enum Weapon_Damages
/*    */   {
/* 10 */     WOODEN_SWORD(4), GOLDEN_SWORD(4), STONE_SWORD(5), IRON_SWORD(6), DIAMOND_SWORD(7),
/* 11 */     WOODEN_AXE(7), GOLDEN_AXE(7), STONE_AXE(9), IRON_AXE(9), DIAMOND_AXE(9),
/* 12 */     WOODEN_SPADE(2), GOLDEN_SPADE(2), STONE_SPADE(3), IRON_SPADE(4), DIAMOND_SPADE(5),
/* 13 */     WOODEN_HOE(1), GOLDEN_HOE(1), STONE_HOE(2), IRON_HOE(3), DIAMOND_HOE(4), BOW(0);
/*    */     
/*    */     private final int value;
/*    */     
/*    */     Weapon_Damages(int value) {
/* 18 */       this.value = value;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int getValue() {
/* 25 */       return this.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Enum\Weapon_Damage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */