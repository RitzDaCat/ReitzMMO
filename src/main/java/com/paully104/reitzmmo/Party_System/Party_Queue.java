/*    */ package com.paully104.reitzmmo.Party_System;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Party_Queue
/*    */ {
/*    */   private final String creator;
/*    */   private final String invited;
/*    */   private final String passcode;
/*    */   
/*    */   public Party_Queue(String creator, String invited, String passcode) {
/* 16 */     this.creator = creator;
/* 17 */     this.invited = invited;
/* 18 */     this.passcode = passcode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPasscode() {
/* 23 */     return this.passcode;
/*    */   }
/*    */   
/*    */   public String getCreator() {
/* 27 */     return this.creator;
/*    */   }
/*    */   
/*    */   public String getInvited() {
/* 31 */     return this.invited;
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Party_System\Party_Queue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */