/*     */ package com.paully104.reitzmmo;
/*     */ import com.paully104.reitzmmo.Command_Handlers.Party_Commands;
/*     */ import com.paully104.reitzmmo.Command_Handlers.ReitzRPGMain;
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.ConfigFiles.ChatConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.DebugConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.FileManager;
/*     */ import com.paully104.reitzmmo.ConfigFiles.LootConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.MenuConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.MonsterConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.PartyConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.PlayerConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.SpecialMonsterConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.TownConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.WeaponskillConfig;
/*     */ import com.paully104.reitzmmo.ConfigFiles.WorldConfig;
/*     */ import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
/*     */ import com.paully104.reitzmmo.ItemData.craftingEvents;
/*     */ import com.paully104.reitzmmo.Menu.Melee_Skills;
/*     */ import com.paully104.reitzmmo.Menu.Menu;
/*     */ import com.paully104.reitzmmo.Menu.Party_Menu;
/*     */ import com.paully104.reitzmmo.Menu.Town_Menu;
/*     */ import com.paully104.reitzmmo.Metrics.Metrics;
/*     */ import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
/*     */ import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
/*     */ import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
/*     */ import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
/*     */ import com.paully104.reitzmmo.Party_System.Party_API;
/*     */ import com.paully104.reitzmmo.PlaceHolderAPI.registerPlaceHolders;
/*     */ import com.paully104.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
/*     */ import com.paully104.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import com.paully104.reitzmmo.Scoreboard.HP_Scoreboard;
/*     */ import com.paully104.reitzmmo.Scoreboard.Partyboard;
/*     */ import com.paully104.reitzmmo.Skills.onRightClickWeaponSkills;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeInstance;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Main extends JavaPlugin {
/*     */   public static final String LEVEL = "Level";
/*     */   
/*     */   public void onEnable() {
/*  53 */     Metrics metrics = new Metrics((Plugin)this);
/*     */     
/*  55 */     metrics.addCustomChart((Metrics.CustomChart)new Metrics.SimplePie("chart_id", () -> "My value"));
/*     */     
/*  57 */     getLogger().info("ReitzRPGMMO is now enabled");
/*  58 */     API.plugin = (Plugin)this;
/*     */     
/*  60 */     PlayerData.setup((Plugin)this);
/*     */     
/*  62 */     FileManager.FileManagerFiles();
/*     */     
/*  64 */     MonsterConfig.Configuration();
/*  65 */     PlayerConfig.Configuration();
/*  66 */     DebugConfig.Configuration();
/*  67 */     WorldConfig.Configuration();
/*  68 */     PartyConfig.Configuration();
/*  69 */     WeaponskillConfig.Configuration();
/*  70 */     ChatConfig.Configuration();
/*  71 */     SpecialMonsterConfig.Configuration();
/*  72 */     LootConfig.Configuration();
/*  73 */     MenuConfig.Configuration();
/*  74 */     TownConfig.Configuration();
/*     */     
/*  76 */     API.setMonsterConfig();
/*  77 */     API.setPlayerConfig();
/*  78 */     API.setDebugConfig();
/*  79 */     API.setWorldConfig();
/*  80 */     API.setPartyConfig();
/*  81 */     API.setWeaponskillConfig();
/*  82 */     API.setcustombowConfig();
/*  83 */     API.setChatConfig();
/*  84 */     API.setSpecialMonsterConfig();
/*  85 */     API.setLootConfig();
/*  86 */     API.setMenuConfig();
/*  87 */     API.setTownConfig();
/*     */     
/*  89 */     ((PluginCommand)Objects.<PluginCommand>requireNonNull(getCommand("reitz"))).setExecutor((CommandExecutor)new ReitzRPGMain());
/*  90 */     ((PluginCommand)Objects.<PluginCommand>requireNonNull(getCommand("rrm"))).setExecutor((CommandExecutor)new ReitzRPGMain());
/*  91 */     ((PluginCommand)Objects.<PluginCommand>requireNonNull(getCommand("rparty"))).setExecutor((CommandExecutor)new Party_Commands());
/*     */ 
/*     */ 
/*     */     
/*  95 */     registerEvents((Plugin)this, new Listener[] { (Listener)new OnPlayerJoinStatSetup(), (Listener)new MonsterLevelsHealth(), (Listener)new OnPlayerExitStatSave(), (Listener)new MonsterLevelsDamage(), (Listener)new PlayerAttackingMonster(), (Listener)new PlayerDefeatsMonster(), (Listener)new Menu(), (Listener)new Party_Menu(), (Listener)new Melee_Skills(), (Listener)new onRightClickWeaponSkills(), (Listener)new Town_Menu(), (Listener)new craftingEvents(), (Listener)new npcCreateEvent(), (Listener)new HP_Scoreboard(), (Listener)new Partyboard() });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/*     */ 
/*     */       
/* 106 */       String uuid = p.getUniqueId().toString();
/* 107 */       PlayerData pd = new PlayerData(uuid);
/* 108 */       pd.getData().set("UUID", uuid);
/*     */       
/* 110 */       int Level = pd.getData().getInt("Level");
/* 111 */       int Attack = pd.getData().getInt("Attack");
/* 112 */       double Health = pd.getData().getDouble("Health");
/* 113 */       int CombatEXP = pd.getData().getInt("Combat-EXP");
/*     */       
/* 115 */       if (Level == 0) {
/* 116 */         pd.getData().set("Level", Integer.valueOf(1));
/*     */       }
/*     */       
/* 119 */       if (Attack == 0) {
/* 120 */         pd.getData().set("Attack", Integer.valueOf(1));
/*     */       }
/*     */       
/* 123 */       if (Health == 0.0D) {
/* 124 */         pd.getData().set("Health", Integer.valueOf(20));
/* 125 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(20.0D);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 130 */         ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(Health);
/*     */       } 
/* 132 */       if (CombatEXP == 0) {
/* 133 */         pd.getData().set("Combat-EXP", Integer.valueOf(0));
/*     */       }
/*     */       
/* 136 */       pd.getData().set("DisplayName", p.getDisplayName());
/* 137 */       pd.save();
/* 138 */       API.Players.put(p.getUniqueId().toString(), pd);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     for (World world : Bukkit.getWorlds()) {
/*     */       
/* 147 */       for (ArmorStand stand : world.getEntitiesByClass(ArmorStand.class)) {
/*     */         
/*     */         try {
/* 150 */           if (!stand.isVisible() && stand.getCustomName().contains("+EXP:"))
/*     */           {
/* 152 */             stand.remove();
/*     */           }
/*     */         }
/* 155 */         catch (NullPointerException nullPointerException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
/*     */     {
/* 163 */       registerPlaceHolders.registerPlaceHoldersReitzMMO(); } 
/*     */   }
/*     */   public static final String ATTACK = "Attack"; public static final String HEALTH = "Health"; public static final String COMBATEXP = "Combat-EXP";
/*     */   public static final String DISPLAYNAME = "DisplayName";
/*     */   
/*     */   public void onDisable() {
/* 169 */     Bukkit.broadcastMessage("[ReitzMMO] disabling... saving all users data");
/* 170 */     for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/*     */       
/* 172 */       String name = p.getName();
/* 173 */       String uuid = p.getUniqueId().toString();
/* 174 */       PlayerData pd = new PlayerData(uuid);
/* 175 */       System.err.println(p.getName() + " has exited the game!");
/*     */ 
/*     */       
/* 178 */       Integer level = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
/* 179 */       Integer attack = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
/* 180 */       Integer health = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
/* 181 */       Integer combatexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
/*     */ 
/*     */       
/* 184 */       pd.getData().set("Level", level);
/* 185 */       pd.getData().set("Attack", attack);
/* 186 */       pd.getData().set("Health", health);
/* 187 */       pd.getData().set("Combat-EXP", combatexp);
/* 188 */       pd.getData().set("DisplayName", p.getDisplayName());
/* 189 */       pd.save();
/*     */       
/* 191 */       if (Party_API.Party_Leaders.containsKey(name)) {
/*     */         
/* 193 */         p.performCommand("Rparty disband");
/*     */       
/*     */       }
/* 196 */       else if (Party_API.inParty.containsKey(name)) {
/*     */         
/* 198 */         p.performCommand("Rparty leave");
/*     */       } 
/*     */       
/* 201 */       ReitzMMO_Book.removeLoginBook(p);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 206 */     Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
/*     */   }
/*     */   
/*     */   private static void registerEvents(Plugin plugin, Listener... listeners) {
/* 210 */     for (Listener listener : listeners)
/* 211 */       Bukkit.getServer().getPluginManager().registerEvents(listener, plugin); 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */