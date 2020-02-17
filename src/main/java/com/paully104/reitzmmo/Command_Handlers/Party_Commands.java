/*     */ package com.paully104.reitzmmo.Command_Handlers;
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import com.paully104.reitzmmo.Menu.Party_Menu;
/*     */ import com.paully104.reitzmmo.Party_System.Party;
/*     */ import com.paully104.reitzmmo.Party_System.Party_API;
/*     */ import com.paully104.reitzmmo.Party_System.Party_Queue;
/*     */ import com.paully104.reitzmmo.Scoreboard.HP_Scoreboard;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scoreboard.DisplaySlot;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.ScoreboardManager;
/*     */ 
/*     */ public class Party_Commands implements CommandExecutor {
/*  28 */   private final boolean partyEnabled = API.partyConfig.getBoolean("Parties_Enabled");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  35 */     Player pl = Bukkit.getPlayer(sender.getName());
/*  36 */     int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(((Player)Objects.<Player>requireNonNull(pl)).getLocation().getWorld())).getName());
/*     */     
/*  38 */     if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && this.partyEnabled && worldEnabled != -1) {
/*     */ 
/*     */       
/*  41 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 0) {
/*  42 */         ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).openInventory(Party_Menu.PARTY_MENU);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  52 */         return true;
/*     */       } 
/*     */       
/*  55 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
/*  56 */         .equalsIgnoreCase("create")) {
/*     */         
/*  58 */         if (!Party_API.inParty.containsKey(sender.getName()) && !Party_API.Party_Leaders.containsKey(sender.getName())) {
/*  59 */           Party party = new Party(sender.getName());
/*  60 */           Party_API.Party_Leaders.put(sender.getName(), party);
/*  61 */           sender.sendMessage(ChatColor.GREEN + "You have created a party!");
/*  62 */           sender.sendMessage(ChatColor.WHITE + "Use /rparty add to invite!");
/*     */         } else {
/*  64 */           sender.sendMessage(ChatColor.RED + "[ERROR]You are already in a party!");
/*     */         } 
/*  66 */         return true;
/*     */       } 
/*  68 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
/*  69 */         .equalsIgnoreCase("disband")) {
/*     */ 
/*     */         
/*  72 */         String name = sender.getName();
/*  73 */         if (Party_API.Party_Leaders.containsKey(name)) {
/*     */ 
/*     */           
/*  76 */           Party party_leaders = (Party)Party_API.Party_Leaders.get(name);
/*     */           
/*  78 */           List<String> members = party_leaders.get_MembersList();
/*  79 */           for (String people : members) {
/*     */             
/*  81 */             Party_API.inParty.remove(people);
/*  82 */             ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(people))).sendMessage(ChatColor.YELLOW + "Party has been disbanded!");
/*     */             try {
/*  84 */               if (!Bukkit.getPlayer(people).hasMetadata("NPC")) {
/*  85 */                 Scoreboard sb = ((ScoreboardManager)Objects.<ScoreboardManager>requireNonNull(Bukkit.getScoreboardManager())).getMainScoreboard();
/*     */                 
/*  87 */                 Objective objective = sb.getObjective("showhealth");
/*  88 */                 ((Objective)Objects.<Objective>requireNonNull(objective)).setDisplaySlot(DisplaySlot.BELOW_NAME);
/*  89 */                 objective.setDisplayName(ChatColor.RED + "❤");
/*  90 */                 ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(people))).setScoreboard(sb);
/*  91 */                 ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(people))).setHealth(((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(people))).getHealth());
/*     */               } 
/*  93 */             } catch (NullPointerException nullPointerException) {}
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 101 */           sender.sendMessage(ChatColor.YELLOW + "disbanding party...");
/* 102 */           Party_API.Party_Leaders.remove(name);
/*     */           
/*     */           try {
/* 105 */             if (!Bukkit.getPlayer(sender.getName()).hasMetadata("NPC")) {
/*     */               
/* 107 */               pl.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
/* 108 */               Scoreboard sb = ((ScoreboardManager)Objects.<ScoreboardManager>requireNonNull(Bukkit.getScoreboardManager())).getMainScoreboard();
/*     */               
/* 110 */               Objective objective = sb.getObjective("showhealth");
/* 111 */               ((Objective)Objects.<Objective>requireNonNull(objective)).setDisplaySlot(DisplaySlot.BELOW_NAME);
/* 112 */               objective.setDisplayName(ChatColor.RED + "❤");
/* 113 */               pl.setScoreboard(sb);
/* 114 */               ((Player)Objects.<Player>requireNonNull(pl)).setHealth(((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).getHealth());
/*     */             } 
/* 116 */           } catch (NullPointerException nullPointerException) {}
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 121 */           sender.sendMessage(ChatColor.RED + "[ERROR]You are not a party leader!");
/*     */         } 
/*     */         
/* 124 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 128 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
/* 129 */         .equalsIgnoreCase("members")) {
/*     */ 
/*     */         
/* 132 */         if (Party_API.Party_Leaders.containsKey(sender.getName())) {
/*     */           
/* 134 */           sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
/* 135 */           Party party = (Party)Party_API.Party_Leaders.get(sender.getName());
/* 136 */           sender.sendMessage(party.get_Members());
/* 137 */         } else if (Party_API.inParty.containsKey(sender.getName())) {
/* 138 */           sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
/* 139 */           String leader = (String)Party_API.inParty.get(sender.getName());
/* 140 */           Party party = (Party)Party_API.Party_Leaders.get(leader);
/* 141 */           sender.sendMessage(party.get_Members());
/*     */         } else {
/* 143 */           sender.sendMessage(ChatColor.RED + "[ERROR]You are not in a party!");
/*     */         } 
/*     */         
/* 146 */         return true;
/*     */       } 
/* 148 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 && args[0]
/* 149 */         .equalsIgnoreCase("add")) {
/*     */ 
/*     */         
/* 152 */         if (args[1].equals(sender.getName())) {
/* 153 */           sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.YELLOW + " Unable to invite yourself");
/*     */         }
/* 155 */         else if (!Party_API.inParty.containsKey(sender.getName()) && Party_API.Party_Leaders.containsKey(sender.getName())) {
/* 156 */           System.out.println("Add Party");
/* 157 */           sender.sendMessage(ChatColor.WHITE + "sending invite to player: " + args[1]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 164 */           Player invitedPlayer = Bukkit.getPlayer(args[1]);
/* 165 */           if (((Player)Objects.<Player>requireNonNull(invitedPlayer)).isOnline())
/*     */           {
/* 167 */             String invitedPlayerName = invitedPlayer.getName();
/* 168 */             String uuid = invitedPlayer.getUniqueId().toString();
/*     */             
/* 170 */             Party_Queue queue = new Party_Queue(sender.getName(), ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).getName(), uuid);
/*     */ 
/*     */             
/* 173 */             ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).sendMessage(ChatColor.YELLOW + "[PARTY]" + ChatColor.GREEN + "Party invite from: " + sender.getName());
/* 174 */             TextComponent component = new TextComponent();
/* 175 */             component.setBold(Boolean.valueOf(true));
/* 176 */             component.setText("Click " + ChatColor.YELLOW + "[HERE]" + ChatColor.WHITE + " to accept the party invite.");
/* 177 */             component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("From: " + sender.getName())).create()));
/* 178 */             component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rparty join"));
/* 179 */             ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).spigot().sendMessage((BaseComponent)component);
/* 180 */             Party_API.Password_Queue.put(((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).getName(), queue);
/*     */           }
/*     */           else
/*     */           {
/* 184 */             sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Requested player is offline!");
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 195 */           sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You must first create a party and be its leader!");
/*     */           
/* 197 */           TextComponent component = new TextComponent();
/* 198 */           component.setBold(Boolean.valueOf(true));
/* 199 */           component.setText("Click " + ChatColor.YELLOW + "[HERE]" + ChatColor.WHITE + " to create a party!");
/* 200 */           component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("From: " + sender.getName())).create()));
/* 201 */           component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rparty create"));
/* 202 */           sender.spigot().sendMessage((BaseComponent)component);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 209 */         return true;
/*     */       } 
/*     */       
/* 212 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
/* 213 */         .equalsIgnoreCase("join")) {
/*     */         
/* 215 */         if (!Party_API.inParty.containsKey(sender.getName())) {
/* 216 */           Party_Queue queue = (Party_Queue)Party_API.Password_Queue.get(sender.getName());
/* 217 */           String passcode = queue.getPasscode();
/* 218 */           if (((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).getUniqueId().toString().equalsIgnoreCase(passcode)) {
/* 219 */             sender.sendMessage(ChatColor.GREEN + "Joining... " + queue.getCreator() + "'s party");
/* 220 */             ((Party)Party_API.Party_Leaders.get(queue.getCreator())).set_Member(sender.getName());
/* 221 */             ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(queue.getCreator()))).sendMessage(ChatColor.GREEN + sender.getName() + " has joined your party!");
/* 222 */             Party_API.Password_Queue.remove(sender.getName());
/* 223 */             Party_API.inParty.put(sender.getName(), queue.getCreator());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 229 */             if (Party_API.Party_Leaders.containsKey(sender.getName()))
/*     */             {
/*     */               
/* 232 */               Party party = (Party)Party_API.Party_Leaders.get(sender.getName());
/* 233 */               for (Object object : party.get_MembersList());
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 239 */             else if (Party_API.inParty.containsKey(sender.getName()))
/*     */             {
/* 241 */               String leader = (String)Party_API.inParty.get(sender.getName());
/* 242 */               Party party = (Party)Party_API.Party_Leaders.get(leader);
/* 243 */               for (Object object : party.get_MembersList());
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 253 */             sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Incorrect passcode!");
/*     */           } 
/*     */         } else {
/*     */           
/* 257 */           sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You /are already in a party!");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 263 */         return true;
/*     */       } 
/*     */       
/* 266 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 && args[0]
/* 267 */         .equalsIgnoreCase("remove")) {
/*     */ 
/*     */         
/* 270 */         if (Party_API.Party_Leaders.containsKey(sender.getName())) {
/* 271 */           sender.sendMessage(ChatColor.RED + "removing member...");
/* 272 */           ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).sendMessage(ChatColor.RED + sender.getName() + " has remove you from party!");
/* 273 */           Party party = (Party)Party_API.Party_Leaders.get(sender.getName());
/* 274 */           party.Remove_Member(args[1]);
/* 275 */           Party_API.Party_Leaders.put(sender.getName(), party);
/* 276 */           Party_API.inParty.remove(args[1]);
/* 277 */           HP_Scoreboard.setHPScoreboard(Bukkit.getPlayer(args[1]));
/*     */         } else {
/* 279 */           sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You are unable to remove members!");
/*     */         } 
/*     */ 
/*     */         
/* 283 */         if (Party_API.Party_Leaders.containsKey(sender.getName())) {
/*     */ 
/*     */           
/* 286 */           Party party = (Party)Party_API.Party_Leaders.get(sender.getName());
/* 287 */           for (Object o : party.get_MembersList())
/*     */           {
/* 289 */             Bukkit.getPlayer(o.toString()).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 294 */         else if (Party_API.inParty.containsKey(sender.getName())) {
/*     */           
/* 296 */           String leader = (String)Party_API.inParty.get(sender.getName());
/* 297 */           Party party = (Party)Party_API.Party_Leaders.get(leader);
/* 298 */           for (Object o : party.get_MembersList())
/*     */           {
/* 300 */             Bukkit.getPlayer(o.toString()).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 306 */         return true;
/*     */       } 
/*     */       
/* 309 */       if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
/* 310 */         .equalsIgnoreCase("leave"))
/*     */       {
/* 312 */         String name = sender.getName();
/* 313 */         if (Party_API.inParty.containsKey(name)) {
/* 314 */           String leader = (String)Party_API.inParty.get(name);
/*     */           
/* 316 */           Party party = (Party)Party_API.Party_Leaders.get(leader);
/*     */           
/* 318 */           List<String> members = party.get_MembersList();
/* 319 */           System.out.println(party.get_MembersList());
/* 320 */           party.Remove_Member(name);
/* 321 */           Party_API.inParty.remove(name);
/* 322 */           Party_API.Party_Leaders.put(leader, party);
/* 323 */           HP_Scoreboard.setHPScoreboard(Bukkit.getPlayer(sender.getName()));
/* 324 */           for (String people : members)
/*     */           {
/*     */             
/* 327 */             Player partyMember = Bukkit.getPlayer(people);
/* 328 */             ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(people))).sendMessage(name + " has left the party!");
/* 329 */             if (partyMember == null)
/*     */             {
/* 331 */               System.out.println("Player error");
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 337 */           sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Not in a party to leave");
/*     */         } 
/*     */ 
/*     */         
/* 341 */         return true;
/*     */       }
/*     */     
/* 344 */     } else if (cmd.getName().equalsIgnoreCase("/rparty") && !this.partyEnabled) {
/*     */       
/* 346 */       sender.sendMessage(ChatColor.RED + "[PARTY]" + ChatColor.WHITE + "ReitzMMO parties are disabled");
/*     */     } 
/*     */ 
/*     */     
/* 350 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Command_Handlers\Party_Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */