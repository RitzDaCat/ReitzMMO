/*     */ package com.paully104.reitzmmo.Menu;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Menu
/*     */   implements Listener
/*     */ {
/*  35 */   public static final Inventory GUI_MENU = Bukkit.createInventory(null, 9, "Reitz Menu");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  43 */     if (API.menuConfig.getBoolean("TeleportHomeEnabled"))
/*     */     {
/*  45 */       createDisplay(Material.FEATHER, 0, "Home", "Teleport to your home point!");
/*     */     }
/*  47 */     createDisplay(Material.MAP, 1, "Stats", "Get your combat stats!");
/*     */     
/*  49 */     if (API.partyConfig.getBoolean("Parties_Enabled")) {
/*  50 */       createDisplay(Material.PLAYER_HEAD, 2, "Party", "Get the party commands!");
/*     */     }
/*     */     
/*  53 */     createDisplay(Material.WOODEN_SWORD, 3, "Sword Skills", "Apply sword weaponskills");
/*     */     
/*  55 */     createDisplay(Material.MAP, 4, "Town Menu", "Town Menu");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryMoveEvent(InventoryMoveItemEvent event) {
/*  65 */     if (event.getDestination() == GUI_MENU) {
/*  66 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent event) {
/*  74 */     Player player = (Player)event.getWhoClicked();
/*  75 */     ItemStack clicked = event.getCurrentItem();
/*  76 */     Inventory inventory = event.getInventory();
/*  77 */     if (inventory == GUI_MENU) {
/*  78 */       if (null != clicked) {
/*  79 */         if (clicked.hasItemMeta()) {
/*  80 */           double health; switch (((ItemMeta)Objects.<ItemMeta>requireNonNull(clicked.getItemMeta())).getDisplayName()) {
/*     */ 
/*     */             
/*     */             case "Home":
/*  84 */               event.setCancelled(true);
/*  85 */               player.closeInventory();
/*  86 */               health = player.getHealth();
/*  87 */               player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
/*  88 */               player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
/*  89 */               Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> { if (player.getHealth() >= health) { if (null != player.getBedSpawnLocation()) { player.teleport(player.getBedSpawnLocation()); } else { player.teleport(player.getWorld().getSpawnLocation()); }  } else { player.sendMessage(ChatColor.RED + "Can't teleport while in combat!"); }  }100L);
/*     */               break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case "Stats":
/* 107 */               event.setCancelled(true);
/* 108 */               player.closeInventory();
/* 109 */               player.performCommand("Reitz Stats");
/*     */               break;
/*     */ 
/*     */             
/*     */             case "Fix Health":
/* 114 */               event.setCancelled(true);
/* 115 */               player.closeInventory();
/* 116 */               player.performCommand("Reitz FixHealth");
/*     */               break;
/*     */ 
/*     */             
/*     */             case "Party":
/* 121 */               event.setCancelled(true);
/* 122 */               player.closeInventory();
/* 123 */               player.performCommand("RParty");
/*     */               break;
/*     */ 
/*     */             
/*     */             case "Fix EXP":
/* 128 */               event.setCancelled(true);
/* 129 */               player.closeInventory();
/* 130 */               player.performCommand("Reitz FixEXP");
/*     */               break;
/*     */ 
/*     */             
/*     */             case "Town Menu":
/* 135 */               event.setCancelled(true);
/* 136 */               player.closeInventory();
/* 137 */               player.openInventory(Town_Menu.TOWN_MENU);
/*     */               break;
/*     */             
/*     */             case "Sword Skills":
/* 141 */               event.setCancelled(true);
/* 142 */               player.closeInventory();
/* 143 */               player.openInventory(Melee_Skills.Melee_Skills);
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 151 */           event.setCancelled(true);
/*     */         }
/*     */         else {
/*     */           
/* 155 */           event.setCancelled(true);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 161 */         event.setCancelled(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createDisplay(Material material, int Slot, String name, String lore) {
/* 170 */     ItemStack item = new ItemStack(material);
/* 171 */     ItemMeta meta = item.getItemMeta();
/* 172 */     ((ItemMeta)Objects.<ItemMeta>requireNonNull(meta)).setDisplayName(name);
/* 173 */     ArrayList<String> Lore = new ArrayList<>();
/* 174 */     Lore.add(lore);
/* 175 */     meta.setLore(Lore);
/* 176 */     item.setItemMeta(meta);
/*     */     
/* 178 */     GUI_MENU.setItem(Slot, item);
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Menu\Menu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */