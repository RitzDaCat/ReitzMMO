/*    */ package com.paully104.reitzmmo.OnPlayerEvents;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
/*    */ import com.paully104.reitzmmo.PlayerCombatRelated.createBossBar;
/*    */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeInstance;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnPlayerJoinStatSetup
/*    */   implements Listener
/*    */ {
/*    */   private static final String HEALTH = "Health";
/*    */   private static final String ATTACK = "Attack";
/*    */   private static final String LEVEL = "Level";
/*    */   private static final String PLAYERCOMBATEXP = "Combat-EXP";
/*    */   
/*    */   @EventHandler(priority = EventPriority.NORMAL)
/*    */   public void OnPlayerJoinStatSetup(PlayerJoinEvent e) {
/* 30 */     Player p = e.getPlayer();
/* 31 */     if (!e.getPlayer().hasMetadata("NPC")) {
/*    */       
/* 33 */       p.setWalkSpeed(0.2F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       String uuid = p.getUniqueId().toString();
/* 42 */       PlayerData pd = new PlayerData(uuid);
/* 43 */       pd.getData().set("UUID", uuid);
/*    */       
/* 45 */       int Level = pd.getData().getInt("Level");
/* 46 */       int Attack = pd.getData().getInt("Attack");
/* 47 */       double Health = pd.getData().getDouble("Health");
/* 48 */       int CombatEXP = pd.getData().getInt("Combat-EXP");
/*    */       
/* 50 */       if (Level == 0) {
/* 51 */         pd.getData().set("Level", Integer.valueOf(1));
/*    */       }
/*    */       
/* 54 */       if (Attack == 0) {
/* 55 */         pd.getData().set("Attack", Integer.valueOf(1));
/*    */       }
/*    */       
/* 58 */       if (Health == 0.0D) {
/* 59 */         pd.getData().set("Health", Integer.valueOf(20));
/* 60 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(20.0D);
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 65 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(Health);
/*    */       } 
/* 67 */       if (CombatEXP == 0) {
/* 68 */         pd.getData().set("Combat-EXP", Integer.valueOf(0));
/*    */       }
/*    */       
/* 71 */       pd.getData().set("DisplayName", p.getDisplayName());
/* 72 */       pd.save();
/* 73 */       API.Players.put(p.getUniqueId().toString(), pd);
/*    */ 
/*    */ 
/*    */       
/* 77 */       ReitzMMO_Book.setLoginBook(p);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 84 */       createBossBar.setBossBaronPlayer(p);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\OnPlayerEvents\OnPlayerJoinStatSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */