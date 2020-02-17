/*    */ package com.paully104.reitzmmo.Party_System;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Party
/*    */ {
/* 11 */   private final List<String> members = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public Party(String creator) {
/* 15 */     this.members.add(creator);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set_Member(String person) {
/* 20 */     this.members.add(person);
/*    */   }
/*    */ 
/*    */   
/*    */   public void Remove_Member(String person) {
/* 25 */     this.members.remove(person);
/*    */   }
/*    */   
/*    */   public String get_Members() {
/* 29 */     return Arrays.toString(this.members.toArray());
/*    */   }
/*    */   
/*    */   public List get_MembersList() {
/* 33 */     return this.members;
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Party_System\Party.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */