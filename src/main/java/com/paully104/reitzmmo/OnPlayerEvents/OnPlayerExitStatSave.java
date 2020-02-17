/*    */ package com.paully104.reitzmmo.OnPlayerEvents;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
/*    */ import com.paully104.reitzmmo.Party_System.Party_API;
/*    */ import com.paully104.reitzmmo.PlayerCombatRelated.createBossBar;
/*    */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnPlayerExitStatSave
/*    */   implements Listener
/*    */ {
/*    */   private static final String HEALTH = "Health";
/*    */   private static final String ATTACK = "Attack";
/*    */   private static final String LEVEL = "Level";
/*    */   private static final String PLAYERCOMBATEXP = "Combat-EXP";
/*    */   
/*    */   @EventHandler
/*    */   public void OnPlayerExit(PlayerQuitEvent e) {
/* 26 */     Player p = e.getPlayer();
/* 27 */     if (!e.getPlayer().hasMetadata("NPC")) {
/*    */       
/* 29 */       String name = p.getName();
/* 30 */       String uuid = p.getUniqueId().toString();
/* 31 */       PlayerData pd = new PlayerData(uuid);
/* 32 */       System.out.println(p.getName() + " has exited the game!");
/*    */ 
/*    */       
/* 35 */       Integer level = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
/* 36 */       Integer attack = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
/* 37 */       Integer health = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
/* 38 */       Integer combatexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
/*    */ 
/*    */       
/* 41 */       pd.getData().set("Level", level);
/* 42 */       pd.getData().set("Attack", attack);
/* 43 */       pd.getData().set("Health", health);
/* 44 */       pd.getData().set("Combat-EXP", combatexp);
/* 45 */       pd.getData().set("DisplayName", p.getDisplayName());
/* 46 */       pd.save();
/*    */ 
/*    */ 
/*    */       
/* 50 */       if (Party_API.Party_Leaders.containsKey(name)) {
/* 51 */         p.performCommand("Rparty disband");
/*    */       }
/* 53 */       else if (Party_API.inParty.containsKey(name)) {
/* 54 */         p.performCommand("Rparty leave");
/*    */       } 
/*    */       
/* 57 */       ReitzMMO_Book.removeLoginBook(p);
/* 58 */       createBossBar.deleteBossBaronPlayer(p);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\OnPlayerEvents\OnPlayerExitStatSave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */