/*     */ package com.paully104.reitzmmo.Skills;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import net.md_5.bungee.api.ChatMessageType;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class onRightClickWeaponSkills
/*     */   implements Listener
/*     */ {
/*  30 */   ArrayList<String> underFireUsers = new ArrayList<>();
/*     */   
/*  32 */   private final boolean underFireEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Under_Fire.Enabled");
/*  33 */   private final int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");
/*  34 */   private final int underFireSpeedIncrease = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.MovementSpeedIncreasePercent");
/*  35 */   private final int underFireLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.LevelRequirement");
/*  36 */   private final int underFireDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurabilityLoss");
/*     */   
/*  38 */   private final boolean knockbackEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Knockback.Enabled");
/*  39 */   private final int knockbackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");
/*  40 */   private final int knockbackSpeedIncrease = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.MovementSpeedIncreasePercent");
/*  41 */   private final int knockbackLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.LevelRequirement");
/*  42 */   private final int knockbackDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurabilityLoss");
/*  43 */   private final int knockbackStrength = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.KnockbackStrength");
/*     */ 
/*     */   
/*     */   public void knockback(Player p, Entity t) {
/*  47 */     Location l = t.getLocation().subtract(p.getLocation());
/*  48 */     double distance = t.getLocation().distance(p.getLocation());
/*  49 */     Vector v = l.toVector().multiply(this.knockbackStrength / distance);
/*  50 */     t.setVelocity(v);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerUse(PlayerInteractEvent event) {
/*  55 */     final Player p = event.getPlayer();
/*  56 */     String uuid = p.getUniqueId().toString();
/*  57 */     PlayerData pd = (PlayerData)API.Players.get(uuid);
/*  58 */     int level = pd.getData().getInt("Level");
/*  59 */     ItemStack modifiedItem = p.getInventory().getItemInMainHand();
/*  60 */     if (modifiedItem.hasItemMeta()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       String weaponSkill = nameSpaceKey.getItemWeaponSkillFromContainer(p.getInventory().getItemInMainHand());
/*  66 */       Damageable im = (Damageable)modifiedItem.getItemMeta();
/*  67 */       int currentDamage = im.getDamage();
/*  68 */       int maxDamage = modifiedItem.getType().getMaxDurability();
/*  69 */       if (currentDamage < maxDamage) {
/*     */         
/*  71 */         if (level >= this.underFireLevelNeeded && !this.underFireUsers.contains(p.getUniqueId().toString()) && this.underFireEnabled && weaponSkill.equalsIgnoreCase("Under Fire") && (event.getAction() == Action.RIGHT_CLICK_AIR || event
/*  72 */           .getAction() == Action.RIGHT_CLICK_BLOCK)) {
/*     */ 
/*     */ 
/*     */           
/*  76 */           p.getPlayer().setWalkSpeed(0.2F * (this.underFireSpeedIncrease / 100));
/*  77 */           this.underFireUsers.add(p.getUniqueId().toString());
/*  78 */           p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Under Fire [Activated]")).color(ChatColor.GREEN).create());
/*     */ 
/*     */           
/*  81 */           int damage = this.underFireDurabilityLoss;
/*     */           
/*  83 */           im.setDamage(currentDamage + damage);
/*  84 */           modifiedItem.setItemMeta((ItemMeta)im);
/*  85 */           Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
/*     */                 public void run() {
/*  87 */                   p.getPlayer().setWalkSpeed(0.2F);
/*  88 */                   onRightClickWeaponSkills.this.underFireUsers.remove(p.getUniqueId().toString());
/*  89 */                   p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Under Fire " + ChatColor.RED + "[Deactivated]")).color(ChatColor.GREEN).create());
/*     */                 }
/*     */               }this.underFireDuration * 20L);
/*     */         } 
/*     */ 
/*     */         
/*  95 */         if (level >= this.knockbackLevelNeeded && this.knockbackEnabled && weaponSkill.equalsIgnoreCase("Knockback") && (event.getAction() == Action.RIGHT_CLICK_AIR || event
/*  96 */           .getAction() == Action.RIGHT_CLICK_BLOCK))
/*     */         {
/*  98 */           int damage = this.knockbackDurabilityLoss;
/*  99 */           im.setDamage(currentDamage + damage);
/* 100 */           modifiedItem.setItemMeta((ItemMeta)im);
/* 101 */           p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Knockback [Activated]")).color(ChatColor.GREEN).create());
/* 102 */           p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0F, 1.0F);
/* 103 */           List entities = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
/* 104 */           for (Object e : entities) {
/* 105 */             Entity en = (Entity)e;
/* 106 */             if (!(en instanceof Player)) {
/* 107 */               knockback(p, en);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 116 */         p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("NOT ENOUGH DURABILITY")).color(ChatColor.RED).create());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Skills\onRightClickWeaponSkills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */