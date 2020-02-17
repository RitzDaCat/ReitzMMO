/*     */ package com.paully104.reitzmmo.Scoreboard;
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.Party_System.Party;
/*     */ import com.paully104.reitzmmo.Party_System.Party_API;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.scoreboard.DisplaySlot;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.Score;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.ScoreboardManager;
/*     */ 
/*     */ public class Partyboard implements Listener {
/*  21 */   Map<UUID, Scoreboard> scoreboards = new HashMap<>();
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void setupPartyBoard(final PlayerJoinEvent e) {
/*  26 */     (new BukkitRunnable() {
/*     */         public void run() {
/*     */           Objective obj;
/*  29 */           Player p = e.getPlayer();
/*     */           
/*  31 */           String playerName = ((Player)Objects.<Player>requireNonNull(p)).getName();
/*  32 */           Boolean debug = Boolean.valueOf(false);
/*  33 */           String pm0 = "";
/*  34 */           String pm1 = "";
/*  35 */           String pm2 = "";
/*  36 */           String pm3 = "";
/*  37 */           String pm4 = "";
/*  38 */           String pm5 = "";
/*  39 */           Party party = null;
/*  40 */           List<String> members = null;
/*  41 */           Scoreboard scoreboard = null;
/*     */           
/*  43 */           int size = 0;
/*  44 */           if (Party_API.Party_Leaders.containsKey(playerName)) {
/*     */ 
/*     */             
/*  47 */             party = (Party)Party_API.Party_Leaders.get(playerName);
/*  48 */             members = party.get_MembersList();
/*  49 */             size = members.size();
/*     */           } 
/*  51 */           if (Party_API.inParty.containsKey(playerName)) {
/*     */             
/*  53 */             String leader = (String)Party_API.inParty.get(playerName);
/*     */             
/*  55 */             party = (Party)Party_API.Party_Leaders.get(leader);
/*  56 */             members = party.get_MembersList();
/*  57 */             size = members.size();
/*     */           } 
/*  59 */           if (null == members || null == party) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/*  64 */           if (!Partyboard.this.scoreboards.containsKey(p.getUniqueId())) {
/*     */             
/*  66 */             ScoreboardManager manager = Bukkit.getScoreboardManager();
/*  67 */             scoreboard = manager.getNewScoreboard();
/*  68 */             Partyboard.this.scoreboards.put(p.getUniqueId(), scoreboard);
/*  69 */             obj = scoreboard.registerNewObjective("test", "dummy", ChatColor.BLUE + "Party List");
/*  70 */             obj.setDisplaySlot(DisplaySlot.SIDEBAR);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/*  75 */             ScoreboardManager manager = Bukkit.getScoreboardManager();
/*  76 */             scoreboard = Partyboard.this.scoreboards.get(p.getUniqueId());
/*  77 */             obj = scoreboard.getObjective("test");
/*     */           } 
/*     */ 
/*     */           
/*  81 */           for (String s : scoreboard.getEntries()) {
/*  82 */             scoreboard.resetScores(s);
/*     */           }
/*     */ 
/*     */           
/*  86 */           if (size > 0) {
/*     */             
/*  88 */             pm0 = members.get(0);
/*  89 */             Score score0 = obj.getScore(ChatColor.GOLD + pm0 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm0).getHealth()) + "♥");
/*  90 */             score0.setScore(0);
/*     */           } 
/*  92 */           if (members.size() > 1) {
/*     */             
/*  94 */             pm1 = members.get(1);
/*  95 */             Score score1 = obj.getScore(ChatColor.GOLD + pm1 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm1).getHealth()) + "♥");
/*  96 */             score1.setScore(1);
/*     */           } 
/*  98 */           if (members.size() > 2) {
/*     */             
/* 100 */             pm2 = members.get(2);
/* 101 */             Score score2 = obj.getScore(ChatColor.GOLD + pm2 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm2).getHealth()) + "♥");
/* 102 */             score2.setScore(2);
/*     */           } 
/* 104 */           if (members.size() > 3) {
/* 105 */             pm3 = members.get(3);
/* 106 */             Score score3 = obj.getScore(ChatColor.GOLD + pm3 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm3).getHealth()) + "♥");
/* 107 */             score3.setScore(3);
/*     */           } 
/* 109 */           if (members.size() > 4) {
/* 110 */             pm4 = members.get(4);
/* 111 */             Score score4 = obj.getScore(ChatColor.GOLD + pm4 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm4).getHealth()) + "♥");
/* 112 */             score4.setScore(4);
/*     */           } 
/* 114 */           if (members.size() > 5) {
/* 115 */             pm5 = members.get(5);
/* 116 */             Score score5 = obj.getScore(ChatColor.GOLD + pm5 + " HP: " + ChatColor.RED + Math.round(Bukkit.getPlayer(pm5).getHealth()) + "♥");
/* 117 */             score5.setScore(5);
/*     */           } 
/* 119 */           p.setScoreboard(scoreboard);
/*     */         }
/* 124 */       }).runTaskTimer(API.plugin, 20L, 20L);
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Scoreboard\Partyboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */