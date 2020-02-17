/*     */ package com.paully104.reitzmmo.Menu;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
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
/*     */ public class Town_Menu
/*     */   implements Listener
/*     */ {
/*  32 */   static FileConfiguration towns = API.getTownConfig();
/*  33 */   public static Set<String> townList = towns.getConfigurationSection("Towns").getKeys(false);
/*  34 */   public static final Inventory TOWN_MENU = Bukkit.createInventory(null, 9, "Town Menu");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  40 */     int index = 0;
/*  41 */     for (String name : townList) {
/*  42 */       createDisplay(Material.GRASS, index, name, name);
/*  43 */       index++;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryMoveEvent(InventoryMoveItemEvent event) {
/*  60 */     if (event.getDestination() == TOWN_MENU)
/*     */     {
/*     */ 
/*     */       
/*  64 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent event) {
/*  73 */     Player player = (Player)event.getWhoClicked();
/*  74 */     ItemStack clicked = event.getCurrentItem();
/*  75 */     Inventory inventory = event.getInventory();
/*  76 */     if (inventory == TOWN_MENU && 
/*  77 */       null != clicked && 
/*  78 */       clicked.hasItemMeta()) {
/*     */       
/*  80 */       System.out.println(clicked.getItemMeta().getDisplayName());
/*  81 */       if (townList.contains(clicked.getItemMeta().getDisplayName())) {
/*     */ 
/*     */         
/*  84 */         event.setCancelled(true);
/*  85 */         player.closeInventory();
/*  86 */         player.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds");
/*  87 */         player.sendMessage(ChatColor.YELLOW + "Teleport will cancel if health is lost!");
/*     */         
/*  89 */         String name = clicked.getItemMeta().getDisplayName();
/*  90 */         int x = API.townConfig.getInt("Towns." + name + ".X");
/*  91 */         int y = API.townConfig.getInt("Towns." + name + ".Y");
/*  92 */         int z = API.townConfig.getInt("Towns." + name + ".Z");
/*  93 */         String world = API.townConfig.getString("Towns." + name + ".World");
/*     */         
/*  95 */         double health = player.getHealth();
/*  96 */         Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> { if (player.getHealth() >= health) { player.teleport(new Location(Bukkit.getWorld(world), x, y, z)); } else { player.sendMessage(ChatColor.RED + "Can't teleport while in combat!"); }  }100L);
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
/*     */       }
/* 108 */       else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
/* 109 */         player.performCommand("Reitz");
/*     */       }
/*     */       else {
/*     */         
/* 113 */         event.setCancelled(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createDisplay(Material material, int Slot, String name, String lore) {
/* 126 */     ItemStack item = new ItemStack(material);
/* 127 */     ItemMeta meta = item.getItemMeta();
/* 128 */     ((ItemMeta)Objects.<ItemMeta>requireNonNull(meta)).setDisplayName(name);
/* 129 */     ArrayList<String> Lore = new ArrayList<>();
/* 130 */     Lore.add(lore);
/* 131 */     meta.setLore(Lore);
/* 132 */     item.setItemMeta(meta);
/*     */     
/* 134 */     TOWN_MENU.setItem(Slot, item);
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Menu\Town_Menu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */