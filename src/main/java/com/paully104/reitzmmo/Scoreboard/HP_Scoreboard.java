/*    */ package com.paully104.reitzmmo.Scoreboard;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.scoreboard.DisplaySlot;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ import org.bukkit.scoreboard.ScoreboardManager;
/*    */ import org.bukkit.scoreboard.Team;
/*    */ 
/*    */ public class HP_Scoreboard implements Listener {
/*    */   @EventHandler
/*    */   public void onPlayerJoinSetBoard(PlayerJoinEvent e) {
/* 16 */     ScoreboardManager manager = Bukkit.getScoreboardManager();
/* 17 */     Scoreboard board = manager.getNewScoreboard();
/*    */     
/* 19 */     Objective objective = board.registerNewObjective("showhealth", "health");
/* 20 */     Team team = board.registerNewTeam("Humans");
/* 21 */     team.addPlayer((OfflinePlayer)e.getPlayer());
/* 22 */     objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
/* 23 */     objective.setDisplayName("/ 20");
/*    */     
/* 25 */     for (Player online : Bukkit.getOnlinePlayers()) {
/*    */       
/* 27 */       online.setScoreboard(board);
/* 28 */       online.setHealth(online.getHealth());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setHPScoreboard(Player p) {
/* 34 */     ScoreboardManager manager = Bukkit.getScoreboardManager();
/* 35 */     Scoreboard board = manager.getNewScoreboard();
/*    */     
/* 37 */     Objective objective = board.registerNewObjective("showhealth", "health");
/* 38 */     Team team = board.registerNewTeam("Humans");
/* 39 */     team.addPlayer((OfflinePlayer)p);
/* 40 */     objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
/* 41 */     objective.setDisplayName("/ 20");
/*    */     
/* 43 */     for (Player online : Bukkit.getOnlinePlayers()) {
/*    */       
/* 45 */       online.setScoreboard(board);
/* 46 */       online.setHealth(online.getHealth());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Scoreboard\HP_Scoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */