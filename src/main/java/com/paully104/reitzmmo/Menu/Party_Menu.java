/*     */ package com.paully104.reitzmmo.Menu;
/*     */ 
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
/*     */ public class Party_Menu
/*     */   implements Listener
/*     */ {
/*  24 */   public static final Inventory PARTY_MENU = Bukkit.createInventory(null, 9, "Party Menu");
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
/*     */   static {
/*  40 */     createDisplay(Material.ANVIL, 0, "Create", "Create a party!");
/*  41 */     createDisplay(Material.ARROW, 1, "Add", "/Rparty add USERNAME");
/*  42 */     createDisplay(Material.BEDROCK, 2, "Remove", "/Rparty remove USERNAME");
/*  43 */     createDisplay(Material.SKELETON_SKULL, 3, "Disband", "/rparty disband");
/*  44 */     createDisplay(Material.SHIELD, 4, "Get Members", "Show party members");
/*  45 */     createDisplay(Material.MINECART, 5, "Leave", "Leave a party");
/*  46 */     createDisplay(Material.REDSTONE_BLOCK, 8, "Return To Menu", "Return to Reitz menu screen");
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
/*  57 */     System.out.println(event.getDestination().toString());
/*  58 */     if (event.getDestination() == PARTY_MENU)
/*     */     {
/*     */       
/*  61 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent event) {
/*  69 */     Player player = (Player)event.getWhoClicked();
/*  70 */     ItemStack clicked = event.getCurrentItem();
/*  71 */     Inventory inventory = event.getInventory();
/*  72 */     if (inventory == PARTY_MENU) {
/*  73 */       if (null != clicked) {
/*     */         
/*  75 */         if (clicked.hasItemMeta())
/*     */         {
/*  77 */           if (((ItemMeta)Objects.<ItemMeta>requireNonNull(clicked.getItemMeta())).getDisplayName().equalsIgnoreCase("Create")) {
/*  78 */             event.setCancelled(true);
/*  79 */             player.closeInventory();
/*  80 */             player.performCommand("Rparty create");
/*     */           
/*     */           }
/*  83 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Add")) {
/*  84 */             event.setCancelled(true);
/*  85 */             player.closeInventory();
/*     */             
/*  87 */             player.sendMessage(ChatColor.GREEN + "Use /rparty add USERNAME");
/*     */ 
/*     */           
/*     */           }
/*  91 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Remove")) {
/*  92 */             event.setCancelled(true);
/*  93 */             player.closeInventory();
/*     */             
/*  95 */             player.sendMessage(ChatColor.GREEN + "Use /rparty remove USERNAME");
/*     */           }
/*  97 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Disband")) {
/*  98 */             event.setCancelled(true);
/*  99 */             player.closeInventory();
/* 100 */             player.performCommand("Rparty disband");
/*     */           
/*     */           }
/* 103 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Get Members")) {
/* 104 */             event.setCancelled(true);
/* 105 */             player.closeInventory();
/* 106 */             player.performCommand("Rparty members");
/*     */           
/*     */           }
/* 109 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Leave")) {
/* 110 */             event.setCancelled(true);
/* 111 */             player.closeInventory();
/* 112 */             player.performCommand("Rparty leave");
/*     */           
/*     */           }
/* 115 */           else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("Return To Menu")) {
/* 116 */             player.performCommand("Reitz");
/*     */           } else {
/*     */             
/* 119 */             event.setCancelled(true);
/*     */           }
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 125 */           event.setCancelled(true);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 131 */         event.setCancelled(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createDisplay(Material material, int Slot, String name, String lore) {
/* 140 */     ItemStack item = new ItemStack(material);
/* 141 */     ItemMeta meta = item.getItemMeta();
/* 142 */     ((ItemMeta)Objects.<ItemMeta>requireNonNull(meta)).setDisplayName(name);
/* 143 */     ArrayList<String> Lore = new ArrayList<>();
/* 144 */     Lore.add(lore);
/* 145 */     meta.setLore(Lore);
/* 146 */     item.setItemMeta(meta);
/*     */     
/* 148 */     PARTY_MENU.setItem(Slot, item);
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Menu\Party_Menu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */