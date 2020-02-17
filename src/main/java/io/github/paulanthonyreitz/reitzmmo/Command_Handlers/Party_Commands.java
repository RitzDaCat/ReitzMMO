package io.github.paulanthonyreitz.reitzmmo.Command_Handlers;

import java.util.List;
import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Menu.Party_Menu;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_API;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_Queue;
import io.github.paulanthonyreitz.reitzmmo.Scoreboard.HP_Scoreboard;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Party_Commands implements CommandExecutor {
    private final boolean partyEnabled = API.partyConfig.getBoolean("Parties_Enabled");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player pl = Bukkit.getPlayer(sender.getName());
        int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(((Player)Objects.<Player>requireNonNull(pl)).getLocation().getWorld())).getName());
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && this.partyEnabled && worldEnabled != -1) {
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 0) {
                Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName())).openInventory(Party_Menu.PARTY_MENU);
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
                    .equalsIgnoreCase("create")) {
                if (!Party_API.inParty.containsKey(sender.getName()) && !Party_API.Party_Leaders.containsKey(sender.getName())) {
                    Party party = new Party(sender.getName());
                    Party_API.Party_Leaders.put(sender.getName(), party);
                    sender.sendMessage(ChatColor.GREEN + "You have created a party!");
                    sender.sendMessage(ChatColor.WHITE + "Use /rparty add to invite!");
                } else {
                    sender.sendMessage(ChatColor.RED + "[ERROR]You are already in a party!");
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
                    .equalsIgnoreCase("disband")) {
                String name = sender.getName();
                if (Party_API.Party_Leaders.containsKey(name)) {
                    Party party_leaders = (Party)Party_API.Party_Leaders.get(name);
                    List<String> members = party_leaders.get_MembersList();
                    for (String people : members) {
                        Party_API.inParty.remove(people);
                        Objects.<Player>requireNonNull(Bukkit.getPlayer(people)).sendMessage(ChatColor.YELLOW + "Party has been disbanded!");
                        try {
                            if (!Bukkit.getPlayer(people).hasMetadata("NPC")) {
                                Scoreboard sb = ((ScoreboardManager)Objects.<ScoreboardManager>requireNonNull(Bukkit.getScoreboardManager())).getMainScoreboard();
                                Objective objective = sb.getObjective("showhealth");
                                Objects.<Objective>requireNonNull(objective).setDisplaySlot(DisplaySlot.BELOW_NAME);
                            }
                        } catch (NullPointerException nullPointerException) {}
                    }
                    sender.sendMessage(ChatColor.YELLOW + "disbanding party...");
                    Party_API.Party_Leaders.remove(name);
                    try {
                        if (!Bukkit.getPlayer(sender.getName()).hasMetadata("NPC")) {
                            pl.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                            Scoreboard sb = Objects.<ScoreboardManager>requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
                            Objective objective = sb.getObjective("showhealth");
                            Objects.<Objective>requireNonNull(objective).setDisplaySlot(DisplaySlot.BELOW_NAME);
                        }
                    } catch (NullPointerException nullPointerException) {}
                } else {
                    sender.sendMessage(ChatColor.RED + "[ERROR]You are not a party leader!");
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
                    .equalsIgnoreCase("members")) {
                if (Party_API.Party_Leaders.containsKey(sender.getName())) {
                    sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                    Party party = (Party)Party_API.Party_Leaders.get(sender.getName());
                    sender.sendMessage(party.get_Members());
                } else if (Party_API.inParty.containsKey(sender.getName())) {
                    sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                    String leader = (String)Party_API.inParty.get(sender.getName());
                    Party party = (Party)Party_API.Party_Leaders.get(leader);
                    sender.sendMessage(party.get_Members());
                } else {
                    sender.sendMessage(ChatColor.RED + "[ERROR]You are not in a party!");
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 && args[0]
                    .equalsIgnoreCase("add")) {
                if (args[1].equals(sender.getName())) {
                    sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.YELLOW + " Unable to invite yourself");
                } else if (!Party_API.inParty.containsKey(sender.getName()) && Party_API.Party_Leaders.containsKey(sender.getName())) {
                    System.out.println("Add Party");
                    sender.sendMessage(ChatColor.WHITE + "sending invite to player: " + args[1]);
                    Player invitedPlayer = Bukkit.getPlayer(args[1]);
                    if (Objects.<Player>requireNonNull(invitedPlayer).isOnline()) {
                        String invitedPlayerName = invitedPlayer.getName();
                        String uuid = invitedPlayer.getUniqueId().toString();
                        Party_Queue queue = new Party_Queue(sender.getName(), ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).getName(), uuid);
                        Objects.requireNonNull(Bukkit.getPlayer(args[1])).sendMessage(ChatColor.YELLOW + "[PARTY]" + ChatColor.GREEN + "Party invite from: " + sender.getName());
                        TextComponent component = new TextComponent();
                        component.setBold(Boolean.valueOf(true));
                        component.setText("Click " + ChatColor.YELLOW + "[HERE]" + ChatColor.WHITE + " to accept the party invite.");
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("From: " + sender.getName())).create()));
                        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rparty join"));
                        Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1])).spigot().sendMessage((BaseComponent)component);
                        Party_API.Password_Queue.put(((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(args[1]))).getName(), queue);
                    } else {
                        sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Requested player is offline!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You must first create a party and be its leader!");
                    TextComponent component = new TextComponent();
                    component.setBold(Boolean.valueOf(true));
                    component.setText("Click " + ChatColor.YELLOW + "[HERE]" + ChatColor.WHITE + " to create a party!");
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("From: " + sender.getName())).create()));
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rparty create"));
                    sender.spigot().sendMessage(component);
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
                    .equalsIgnoreCase("join")) {
                if (!Party_API.inParty.containsKey(sender.getName())) {
                    Party_Queue queue = Party_API.Password_Queue.get(sender.getName());
                    String passcode = queue.getPasscode();
                    if (Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).getUniqueId().toString().equalsIgnoreCase(passcode)) {
                        sender.sendMessage(ChatColor.GREEN + "Joining... " + queue.getCreator() + "'s party");
                        Party_API.Party_Leaders.get(queue.getCreator()).set_Member(sender.getName());
                        Objects.requireNonNull(Bukkit.getPlayer(queue.getCreator())).sendMessage(ChatColor.GREEN + sender.getName() + " has joined your party!");
                        Party_API.Password_Queue.remove(sender.getName());
                        Party_API.inParty.put(sender.getName(), queue.getCreator());
                        if (Party_API.Party_Leaders.containsKey(sender.getName())) {
                            Party party = Party_API.Party_Leaders.get(sender.getName());
                            for (Object object : party.get_MembersList());
                        } else if (Party_API.inParty.containsKey(sender.getName())) {
                            String leader = (String)Party_API.inParty.get(sender.getName());
                            Party party = Party_API.Party_Leaders.get(leader);
                            for (Object object : party.get_MembersList());
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Incorrect passcode!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You /are already in a party!");
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 && args[0]
                    .equalsIgnoreCase("remove")) {
                if (Party_API.Party_Leaders.containsKey(sender.getName())) {
                    sender.sendMessage(ChatColor.RED + "removing member...");
                    Objects.requireNonNull(Bukkit.getPlayer(args[1])).sendMessage(ChatColor.RED + sender.getName() + " has remove you from party!");
                    Party party = Party_API.Party_Leaders.get(sender.getName());
                    party.Remove_Member(args[1]);
                    Party_API.Party_Leaders.put(sender.getName(), party);
                    Party_API.inParty.remove(args[1]);
                    HP_Scoreboard.setHPScoreboard(Bukkit.getPlayer(args[1]));
                } else {
                    sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " You are unable to remove members!");
                }
                if (Party_API.Party_Leaders.containsKey(sender.getName())) {
                    Party party = Party_API.Party_Leaders.get(sender.getName());
                    for (Object o : party.get_MembersList())
                        Bukkit.getPlayer(o.toString()).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                } else if (Party_API.inParty.containsKey(sender.getName())) {
                    String leader = Party_API.inParty.get(sender.getName());
                    Party party = Party_API.Party_Leaders.get(leader);
                    for (Object o : party.get_MembersList())
                        Bukkit.getPlayer(o.toString()).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                }
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 && args[0]
                    .equalsIgnoreCase("leave")) {
                String name = sender.getName();
                if (Party_API.inParty.containsKey(name)) {
                    String leader = Party_API.inParty.get(name);
                    Party party = Party_API.Party_Leaders.get(leader);
                    List<String> members = party.get_MembersList();
                    System.out.println(party.get_MembersList());
                    party.Remove_Member(name);
                    Party_API.inParty.remove(name);
                    Party_API.Party_Leaders.put(leader, party);
                    HP_Scoreboard.setHPScoreboard(Bukkit.getPlayer(sender.getName()));
                    for (String people : members) {
                        Player partyMember = Bukkit.getPlayer(people);
                        Objects.<Player>requireNonNull(Bukkit.getPlayer(people)).sendMessage(name + " has left the party!");
                        if (partyMember == null)
                            System.out.println("Player error");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "[Error]" + ChatColor.WHITE + " Not in a party to leave");
                }
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("/rparty") && !this.partyEnabled) {
            sender.sendMessage(ChatColor.RED + "[PARTY]" + ChatColor.WHITE + "ReitzMMO parties are disabled");
        }
        return false;
    }
}
