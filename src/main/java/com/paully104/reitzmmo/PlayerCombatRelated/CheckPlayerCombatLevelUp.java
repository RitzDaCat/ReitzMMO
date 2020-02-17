/*    */ package com.paully104.reitzmmo.PlayerCombatRelated;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeInstance;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CheckPlayerCombatLevelUp
/*    */ {
/*    */   private static final String PLAYERCOMBATEXP = "Combat-EXP";
/* 17 */   private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerLevelUp");
/*    */   
/*    */   private static final String ATTACKSCALE = "Scaling.Player.AttackScale";
/*    */   
/*    */   private static final String HEALTHSCALE = "Scaling.Player.HealthScale";
/*    */   private static final String DEFENSESCALE = "Scaling.Player.DefenseScale";
/*    */   private static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";
/*    */   private static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";
/*    */   private static final String ATTACK = "Attack";
/*    */   private static final String HEALTH = "Health";
/*    */   private static final String LEVEL = "Level";
/*    */   
/*    */   public void CheckLevelUp(Player p) {
/* 30 */     int combatexp = ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().getInt("Combat-EXP");
/* 31 */     int level = ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().getInt("Level");
/* 32 */     int combatexpneeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/*    */     
/* 34 */     if (combatexp >= combatexpneeded) {
/*    */       
/* 36 */       level++;
/* 37 */       combatexp -= combatexpneeded;
/* 38 */       ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().set("Combat-EXP", Integer.valueOf(combatexp));
/* 39 */       ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().set("Level", Integer.valueOf(level));
/*    */ 
/*    */       
/* 42 */       p.sendMessage(ChatColor.GREEN + "[ReitzMMO]" + ChatColor.WHITE + " You have leveled up to: " + ChatColor.YELLOW + level);
/* 43 */       String levelMessage = ChatColor.YELLOW + Integer.toString(level);
/* 44 */       p.sendTitle(levelMessage, "Congratulations, you have leveled up!", 10, 70, 10);
/*    */       
/* 46 */       ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("Scaling.Player.AttackScale")));
/* 47 */       ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("Scaling.Player.HealthScale")));
/* 48 */       ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().set("Combat-EXP", Integer.valueOf(combatexp));
/* 49 */       ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue((18 + level * API.playerConfig.getInt("Scaling.Player.HealthScale")));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 54 */     if (this.debugEnabled)
/*    */     {
/* 56 */       System.out.println(p.getName() + " combatexp: " + combatexp + " combatexpneeded: " + combatexpneeded);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlayerCombatRelated\CheckPlayerCombatLevelUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */