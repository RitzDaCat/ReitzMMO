/*     */ package com.paully104.reitzmmo.PlayerCombatRelated;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.Hologram.Hologram;
/*     */ import com.paully104.reitzmmo.Party_System.Party;
/*     */ import com.paully104.reitzmmo.Party_System.Party_API;
/*     */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class PlayerDefeatsMonster
/*     */   implements Listener {
/*     */   public static final String PLAYERCOMBATEXP = "Combat-EXP";
/*     */   public static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";
/*     */   public static final String LEVEL = "Level";
/*     */   public static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";
/*  35 */   private final int PartyEXPMaxDistance = API.partyConfig.getInt("PartyEXPMaxDistance");
/*  36 */   private final boolean debug = API.debugConfig.getBoolean("PartyEXP");
/*  37 */   private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");
/*  38 */   private final int combatEXPMultipler = API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/*  39 */   private final boolean expHologramEnabled = API.chatConfig.getBoolean("expHologramsEnabled");
/*  40 */   private final boolean expChatEnabled = API.chatConfig.getBoolean("expChatEnabled");
/*     */   
/*  42 */   private final boolean mobsDropAttackUpItems = API.lootConfig.getBoolean("General.MobsDropAttackUpItems.Enabled");
/*  43 */   private final int mobsDropAttackUpItemsChance = API.lootConfig.getInt("General.MobsDropAttackUpItems.PercentChance");
/*     */   
/*  45 */   private final boolean mobsDropBonusChest = API.lootConfig.getBoolean("General.BonusChest.Enabled");
/*  46 */   private final int mobsDropBonusChestPercentChance = API.lootConfig.getInt("General.BonusChest.PercentChance");
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void MonsterDeathCausedByPlayer(EntityDeathEvent e) {
/*  51 */     int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(e.getEntity().getLocation().getWorld())).getName());
/*  52 */     if (worldEnabled != -1 && e.getEntity().getKiller() != null && !(e.getEntity() instanceof Player)) {
/*     */ 
/*     */ 
/*     */       
/*  56 */       LivingEntity livingEntity = e.getEntity();
/*  57 */       Player player = e.getEntity().getKiller();
/*     */       
/*  59 */       if (player.getGameMode() != GameMode.CREATIVE) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  64 */         if (PlayerAttackingMonster.playerHasMusic.contains(player.getUniqueId().toString())) {
/*  65 */           player.stopSound(Sound.MUSIC_DISC_11);
/*  66 */           PlayerAttackingMonster.playerHasMusic.remove(player.getUniqueId().toString());
/*     */         } 
/*     */         
/*  69 */         String playerName = ((Player)Objects.<Player>requireNonNull(player)).getName();
/*  70 */         String monster_level_from_name = "1";
/*     */         try {
/*  72 */           monster_level_from_name = ((String)Objects.<String>requireNonNull(livingEntity.getCustomName())).replaceAll("\\D+", "");
/*  73 */         } catch (NullPointerException ex) {
/*  74 */           monster_level_from_name = "1";
/*     */         } 
/*  76 */         int monster_level = Integer.parseInt(monster_level_from_name);
/*  77 */         String lootConfigItem = API.lootConfig.getString(monster_level_from_name + "." + e.getEntity().getType() + ".item");
/*  78 */         int lootConfigChance = API.lootConfig.getInt(monster_level_from_name + "." + e.getEntity().getType() + ".chance");
/*     */         
/*  80 */         Random randomLoot = new Random();
/*     */         
/*  82 */         int randomLootChance = randomLoot.nextInt(100) + 1;
/*  83 */         if (randomLootChance <= lootConfigChance)
/*     */         {
/*     */           
/*  86 */           e.getDrops().add(new ItemStack(Objects.<Material>requireNonNull(Material.getMaterial(Objects.<String>requireNonNull(lootConfigItem)))));
/*     */         }
/*     */ 
/*     */         
/*  90 */         if (this.debug) {
/*  91 */           System.out.println("PARTYEXPDISTANCE: " + this.PartyEXPMaxDistance);
/*     */         }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 115 */         if (Party_API.Party_Leaders.containsKey(playerName)) {
/*     */ 
/*     */           
/* 118 */           Party party = (Party)Party_API.Party_Leaders.get(playerName);
/* 119 */           List<String> members = party.get_MembersList();
/* 120 */           if (this.debug) {
/* 121 */             System.out.println(party.get_MembersList());
/*     */           }
/* 123 */           for (String people : members) {
/* 124 */             Player partyMember = Bukkit.getPlayer(people);
/* 125 */             if (partyMember == null && this.debug)
/*     */             {
/* 127 */               System.out.println("Player error");
/*     */             }
/*     */ 
/*     */             
/* 131 */             if (this.debug) {
/* 132 */               System.out.println("Distance #1 " + livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()));
/*     */             }
/* 134 */             if (livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()) <= this.PartyEXPMaxDistance) {
/* 135 */               Integer currentexp = Integer.valueOf(((PlayerData)API.Players.get(partyMember.getUniqueId().toString())).getData().getInt("Combat-EXP"));
/* 136 */               int new_exp = currentexp.intValue() + monster_level * this.combatEXPMultipler;
/* 137 */               ((PlayerData)API.Players.get(partyMember.getUniqueId().toString())).getData().set("Combat-EXP", Integer.valueOf(new_exp));
/* 138 */               CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
/* 139 */               test.CheckLevelUp(partyMember); continue;
/*     */             } 
/* 141 */             if (this.debug) {
/* 142 */               System.out.println("Player is to far #2");
/* 143 */               System.out.println("Player:" + partyMember.getName());
/* 144 */               System.out.println("Distance" + livingEntity.getLocation().distance(partyMember.getLocation()));
/* 145 */               System.out.println("Location:" + partyMember.getLocation());
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 151 */           Hologram hologram = new Hologram();
/* 152 */           Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
/*     */           
/* 154 */           int expGained = monster_level * this.combatEXPMultipler;
/* 155 */           if (this.expHologramEnabled) {
/* 156 */             hologram.setHologram(player.getWorld(), monster, expGained);
/*     */           }
/* 158 */           if (this.expChatEnabled) {
/* 159 */             Player p = player;
/* 160 */             String uuid = p.getUniqueId().toString();
/* 161 */             TextComponent component = new TextComponent();
/*     */             
/* 163 */             int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
/* 164 */             int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/* 165 */             int combatexpCurrent = ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP");
/* 166 */             int expNeededToLevel = combatexpNeeded - combatexpCurrent;
/* 167 */             component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
/* 168 */             String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
/* 169 */             component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
/*     */             
/* 171 */             player.spigot().sendMessage((BaseComponent)component);
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 176 */         else if (Party_API.inParty.containsKey(playerName)) {
/*     */           
/* 178 */           String leader = (String)Party_API.inParty.get(playerName);
/*     */           
/* 180 */           Party party = (Party)Party_API.Party_Leaders.get(leader);
/* 181 */           List<String> members = party.get_MembersList();
/*     */           
/* 183 */           for (String people : members) {
/*     */             
/* 185 */             Player partyMember = Bukkit.getPlayer(people);
/* 186 */             if (partyMember == null && this.debug)
/*     */             {
/* 188 */               System.out.println("Player error");
/*     */             }
/*     */ 
/*     */             
/* 192 */             if (this.debug) {
/* 193 */               System.out.println("Distance #1 " + livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()));
/*     */             }
/*     */             
/* 196 */             if (livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()) <= this.PartyEXPMaxDistance) {
/*     */               
/* 198 */               String uuid = partyMember.getUniqueId().toString();
/* 199 */               Integer currentexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
/*     */               
/* 201 */               int new_exp = currentexp.intValue() + monster_level * this.combatEXPMultipler;
/* 202 */               ((PlayerData)API.Players.get(uuid)).getData().set("Combat-EXP", Integer.valueOf(new_exp));
/* 203 */               CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
/* 204 */               test.CheckLevelUp(partyMember); continue;
/*     */             } 
/* 206 */             if (this.debugEnabled) {
/* 207 */               System.out.println("Player is to far #2");
/* 208 */               System.out.println("Player:" + partyMember.getName());
/* 209 */               System.out.println("Distance" + livingEntity.getLocation().distance(partyMember.getLocation()));
/* 210 */               System.out.println("Location:" + partyMember.getLocation());
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 215 */           Hologram hologram = new Hologram();
/* 216 */           Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
/* 217 */           int expGained = monster_level * this.combatEXPMultipler;
/* 218 */           if (this.expHologramEnabled) {
/* 219 */             hologram.setHologram(player.getWorld(), monster, expGained);
/*     */           }
/* 221 */           if (this.expChatEnabled) {
/* 222 */             Player p = player;
/* 223 */             TextComponent component = new TextComponent();
/* 224 */             String uuid = p.getUniqueId().toString();
/* 225 */             int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
/* 226 */             int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/* 227 */             int combatexpCurrent = ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP");
/* 228 */             int expNeededToLevel = combatexpNeeded - combatexpCurrent;
/* 229 */             component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
/* 230 */             String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
/* 231 */             component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
/*     */             
/* 233 */             player.spigot().sendMessage((BaseComponent)component);
/*     */           } 
/*     */         } else {
/*     */           
/* 237 */           monster_level_from_name = "1";
/* 238 */           String uuid = player.getUniqueId().toString();
/* 239 */           int currentexp = ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP");
/*     */           try {
/* 241 */             monster_level_from_name = livingEntity.getCustomName().replaceAll("\\D+", "");
/* 242 */           } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */           
/* 245 */           monster_level = Integer.parseInt(monster_level_from_name);
/* 246 */           int new_exp = currentexp + monster_level * this.combatEXPMultipler;
/* 247 */           ((PlayerData)API.Players.get(uuid)).getData().set("Combat-EXP", Integer.valueOf(new_exp));
/* 248 */           CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
/* 249 */           test.CheckLevelUp(player);
/* 250 */           Hologram hologram = new Hologram();
/* 251 */           Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
/* 252 */           int expGained = this.combatEXPMultipler * monster_level;
/* 253 */           if (this.expHologramEnabled) {
/* 254 */             hologram.setHologram(player.getWorld(), monster, expGained);
/*     */           }
/* 256 */           if (this.expChatEnabled) {
/* 257 */             Player p = player;
/* 258 */             TextComponent component = new TextComponent();
/*     */             
/* 260 */             int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
/* 261 */             int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
/* 262 */             int combatexpCurrent = ((PlayerData)API.Players.get(p.getUniqueId().toString())).getData().getInt("Combat-EXP");
/* 263 */             int expNeededToLevel = combatexpNeeded - combatexpCurrent;
/* 264 */             component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
/* 265 */             String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
/* 266 */             component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
/*     */             
/* 268 */             player.spigot().sendMessage((BaseComponent)component);
/*     */           } 
/*     */ 
/*     */           
/* 272 */           if (this.debug) {
/* 273 */             System.out.println("Player location: " + player.getLocation());
/*     */           }
/* 275 */           if (this.debug) {
/* 276 */             System.out.println("Headheight location: " + player.getEyeLocation());
/*     */           }
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 587 */       createBossBar.removeBossBaronPlayer(player);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\PlayerCombatRelated\PlayerDefeatsMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */