/*     */ package com.paully104.reitzmmo.Command_Handlers;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.Menu.Menu;
/*     */ import com.paully104.reitzmmo.Party_System.Party_API;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeInstance;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReitzRPGMain
/*     */   implements CommandExecutor
/*     */ {
/*     */   private static final String REITZ = "Reitz";
/*     */   private static final String REITZMMO = "ReitzMMO";
/*     */   private static final String LEVEL = "Level";
/*     */   private static final String ATTACK = "Attack";
/*     */   private static final String HEALTH = "Health";
/*     */   private static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";
/*     */   private static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";
/*     */   private static final String PLAYERCOMBATEXP = "Combat-EXP";
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  38 */     Player pl = Bukkit.getPlayer(sender.getName());
/*  39 */     int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(((Player)Objects.<Player>requireNonNull(pl)).getLocation().getWorld())).getName());
/*     */     
/*  41 */     if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && worldEnabled != -1) {
/*     */ 
/*     */       
/*  44 */       if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 0) {
/*  45 */         ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).openInventory(Menu.GUI_MENU);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  51 */         return true;
/*     */       } 
/*  53 */       if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("Stats")) {
/*     */ 
/*     */         
/*  56 */         Player p = Bukkit.getPlayer(sender.getName());
/*  57 */         String uuid = ((Player)Objects.<Player>requireNonNull(p)).getUniqueId().toString();
/*  58 */         sender.sendMessage(ChatColor.GOLD + "|||Current Stats|||");
/*  59 */         sender.sendMessage(ChatColor.GOLD + "     Level: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
/*  60 */         sender.sendMessage(ChatColor.RED + "     Attack: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
/*  61 */         sender.sendMessage(ChatColor.YELLOW + "     Health: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
/*  62 */         sender.sendMessage(ChatColor.DARK_GREEN + "     Combat-EXP: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
/*  63 */         int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
/*  64 */         int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/*  65 */         sender.sendMessage(ChatColor.DARK_GREEN + "     CombatEXP Needed: " + combatexpNeeded);
/*  66 */         return true;
/*     */       } 
/*  68 */       if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixHealth")) {
/*     */         
/*  70 */         Player p = Bukkit.getPlayer(sender.getName());
/*  71 */         String uuid = ((Player)Objects.<Player>requireNonNull(p)).getUniqueId().toString();
/*  72 */         int combatexp = ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP");
/*  73 */         int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
/*  74 */         int combatexpneeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/*     */         
/*  76 */         if (combatexp >= combatexpneeded)
/*     */         {
/*     */           
/*  79 */           level++;
/*  80 */           combatexp -= combatexpneeded;
/*  81 */           p.sendMessage("~Fixing stats due to plugin changes...");
/*  82 */           p.sendMessage("You leveled up to: " + level);
/*  83 */           ((PlayerData)API.Players.get(uuid)).getData().set("Level", Integer.valueOf(level));
/*  84 */           ((PlayerData)API.Players.get(uuid)).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("AttackScale")));
/*  85 */           ((PlayerData)API.Players.get(uuid)).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("HealthScale")));
/*  86 */           p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
/*  87 */           p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
/*  88 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(API.getPlayerDataFromAPI(p, "Health"));
/*  89 */           ((PlayerData)API.Players.get(uuid)).getData().set("Combat-EXP", Integer.valueOf(combatexp));
/*     */         }
/*     */         else
/*     */         {
/*  93 */           p.sendMessage("~Fixing stats due to plugin changes...");
/*  94 */           ((PlayerData)API.Players.get(uuid)).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("AttackScale")));
/*  95 */           ((PlayerData)API.Players.get(uuid)).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("HealthScale")));
/*  96 */           p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
/*  97 */           p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
/*  98 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(API.getPlayerDataFromAPI(p, "Health"));
/*     */         }
/*     */       
/*     */       }
/* 102 */       else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixEXP")) {
/*     */         
/* 104 */         World world = ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).getWorld();
/* 105 */         for (Entity e : world.getEntities()) {
/* 106 */           if (e instanceof org.bukkit.entity.ArmorStand) {
/* 107 */             e.remove();
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 113 */       else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("Reload")) {
/*     */ 
/*     */         
/* 116 */         Bukkit.broadcastMessage("[ReitzMMO] reloading... saving all users data");
/* 117 */         for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/*     */ 
/*     */           
/* 120 */           String name = p.getName();
/* 121 */           String uuid = p.getUniqueId().toString();
/* 122 */           PlayerData pd = new PlayerData(uuid);
/* 123 */           System.out.println(p.getName() + " has exited the game!");
/*     */ 
/*     */           
/* 126 */           Integer level = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
/* 127 */           Integer attack = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
/* 128 */           Integer health = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
/* 129 */           Integer combatexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
/*     */ 
/*     */           
/* 132 */           pd.getData().set("Level", level);
/* 133 */           pd.getData().set("Attack", attack);
/* 134 */           pd.getData().set("Health", health);
/* 135 */           pd.getData().set("Combat-EXP", combatexp);
/* 136 */           pd.getData().set("DisplayName", p.getDisplayName());
/* 137 */           pd.save();
/*     */ 
/*     */ 
/*     */           
/* 141 */           if (Party_API.Party_Leaders.containsKey(name)) {
/*     */             
/* 143 */             p.performCommand("Rparty disband");
/*     */             continue;
/*     */           } 
/* 146 */           if (Party_API.inParty.containsKey(name))
/*     */           {
/* 148 */             p.performCommand("Rparty leave");
/*     */           }
/*     */         } 
/*     */         
/* 152 */         Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
/*     */ 
/*     */ 
/*     */         
/* 156 */         Bukkit.broadcastMessage("[ReitzMMO] Loading players stats from configs");
/* 157 */         for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/*     */ 
/*     */           
/* 160 */           String uuid = p.getUniqueId().toString();
/* 161 */           PlayerData pd = new PlayerData(uuid);
/* 162 */           pd.getData().set("UUID", uuid);
/*     */           
/* 164 */           int Level = pd.getData().getInt("Level");
/* 165 */           int Attack = pd.getData().getInt("Attack");
/* 166 */           double Health = pd.getData().getDouble("Health");
/* 167 */           int CombatEXP = pd.getData().getInt("Combat-EXP");
/*     */           
/* 169 */           if (Level == 0) {
/* 170 */             pd.getData().set("Level", Integer.valueOf(1));
/*     */           }
/*     */           
/* 173 */           if (Attack == 0) {
/* 174 */             pd.getData().set("Attack", Integer.valueOf(1));
/*     */           }
/*     */           
/* 177 */           if (Health == 0.0D) {
/* 178 */             pd.getData().set("Health", Integer.valueOf(20));
/* 179 */             ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(20.0D);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 184 */             ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(Health);
/*     */           } 
/* 186 */           if (CombatEXP == 0) {
/* 187 */             pd.getData().set("Combat-EXP", Integer.valueOf(0));
/*     */           }
/*     */           
/* 190 */           pd.getData().set("DisplayName", p.getDisplayName());
/* 191 */           pd.save();
/* 192 */           API.Players.put(p.getUniqueId().toString(), pd);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Command_Handlers\ReitzRPGMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */