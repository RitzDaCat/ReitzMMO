/*    */ package com.paully104.reitzmmo.Hologram;
/*    */ 
/*    */ import com.paully104.reitzmmo.ConfigFiles.API;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hologram
/*    */ {
/*    */   public void setHologram(World w, Location location, int exp) {
/* 17 */     ArmorStand a = (ArmorStand)w.spawnEntity(location, EntityType.ARMOR_STAND);
/* 18 */     a.setVisible(false);
/* 19 */     a.setGravity(false);
/* 20 */     a.setCustomName(ChatColor.GREEN + "+EXP: " + exp);
/* 21 */     a.setCustomNameVisible(true);
/*    */     
/* 23 */     Objects.requireNonNull(a); Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove, 50L);
/*    */   }
/*    */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Hologram\Hologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */