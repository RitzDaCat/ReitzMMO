/*    */ package com.paully104.reitzmmo.Citizens;
/*    */ 
/*    */ import net.citizensnpcs.api.event.NPCSpawnEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.scoreboard.DisplaySlot;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class npcCreateEvent
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void npccreateEvent(NPCSpawnEvent e) {
/* 22 */     Player p = (Player)e.getNPC().getEntity();
/* 23 */     p.getScoreboard().getObjective(DisplaySlot.BELOW_NAME).setDisplayName("TEST");
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Citizens\npcCreateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */