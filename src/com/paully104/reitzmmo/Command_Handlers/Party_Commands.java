package com.paully104.reitzmmo.Command_Handlers;

import com.paully104.reitzmmo.Menu.Party_Menu;
import com.paully104.reitzmmo.Party_System.Party;
import com.paully104.reitzmmo.Party_System.Party_API;
import com.paully104.reitzmmo.Party_System.Party_Queue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by Paul on 7/29/2016.
 */
public class Party_Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 0) {
            Bukkit.getPlayer(sender.getName()).openInventory(Party_Menu.PARTY_MENU);
            /*
            sender.sendMessage(ChatColor.GOLD + "~RParty Commands~");
            sender.sendMessage(ChatColor.GOLD + "1. /Rparty create");
            sender.sendMessage(ChatColor.GOLD + "2. /Rparty add");
            sender.sendMessage(ChatColor.GOLD + "3. /Rparty remove");
            sender.sendMessage(ChatColor.GOLD + "4. /Rparty disband");
            sender.sendMessage(ChatColor.GOLD + "5. /Rparty members");
            sender.sendMessage(ChatColor.GOLD + "t. /Rparty leave");
            */
            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("create")) {
            //CREATE PARTY
            if(!(Party_API.inParty.containsKey(sender.getName())) && !(Party_API.Party_Leaders.containsKey(sender.getName())))
            {//You are not in a party and you are not already hosting a party
                Party party = new Party(sender.getName());
                Party_API.Party_Leaders.put(sender.getName(), party);
                sender.sendMessage(ChatColor.GREEN+"You have created a party!");
                sender.sendMessage(ChatColor.WHITE+"Use /rparty add to invite!");
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are already in a party!");
            }
            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("disband")) {
            //PARTY DISBAND
            String name = sender.getName();
            if ((Party_API.Party_Leaders.containsKey(name)))
            {
                //you are the party leader, you can disband
                //first remove all members
                Party party_leaders = Party_API.Party_Leaders.get(name);
                //noinspection unchecked
                @SuppressWarnings("unchecked") List<String> members = party_leaders.get_MembersList();
                System.out.println(party_leaders.get_MembersList());

                for (String people : members)
                {
                    if(people.equalsIgnoreCase("null"))
                    {

                    }
                    else
                    {
                        Party_API.inParty.remove(people);
                        Bukkit.getPlayer(people).sendMessage(ChatColor.YELLOW + "Party has been disbanded!");

                    }

                }
                //after all the players are safely removed then we can remove the leader
                sender.sendMessage(ChatColor.YELLOW + "disbanding party...");
                Party_API.Party_Leaders.remove(name);
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are not a party leader!");
            }

            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("members")) {

            if((Party_API.Party_Leaders.containsKey(sender.getName())))
            {
                //You are in a party and the leader
                sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                Party party = Party_API.Party_Leaders.get(sender.getName());
                sender.sendMessage(party.get_Members());
            }
            else if(Party_API.inParty.containsKey(sender.getName()))
            {
                sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                String leader = Party_API.inParty.get(sender.getName());
                Party party = Party_API.Party_Leaders.get(leader);
                sender.sendMessage(party.get_Members());
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are not in a party!");
            }

            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 &&
                args[0].equalsIgnoreCase("add")) {
            if(!(Party_API.inParty.containsKey(sender.getName())) && (Party_API.Party_Leaders.containsKey(sender.getName()))) {
                System.out.println("Add Party");
                sender.sendMessage(ChatColor.WHITE + "sending invite to player: " + args[1]);

                //used a passcode because its unique but players names are unique?
                //Random r = new Random();
                //int passcode = r.nextInt(100) + 1;

                //lets cache some values for performance
                Player invitedPlayer = Bukkit.getPlayer(args[1]);
                String invitedPlayerName = invitedPlayer.getName();
                String uuid = invitedPlayer.getUniqueId().toString();

                Party_Queue queue = new Party_Queue(sender.getName(), Bukkit.getPlayer(args[1]).getName(), uuid);
                Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GREEN + "Party invite from: " + sender.getName());
                //Bukkit.getPlayer(args[1]).sendMessage(ChatColor.WHITE + "Passcode: " + passcode);
                Bukkit.getPlayer(args[1]).sendMessage(ChatColor.WHITE + "Use /rparty join to join!");
                Party_API.Password_Queue.put(Bukkit.getPlayer(args[1]).getName(), queue);
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "You must first create a party and be its leader!");

            }
            //Party party = Party_API.Party_Leaders.get(sender.getName());
            //party.set_Member(args[0]);
            //Party_API.Party_Leaders.put(sender.getName(),party);

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("join")) {
            if(!(Party_API.inParty.containsKey(sender.getName())))
            {
                sender.sendMessage(ChatColor.YELLOW + "~Attempting to join party~");
                Party_Queue queue = Party_API.Password_Queue.get(sender.getName());
                String passcode = queue.getPasscode();
                if (Bukkit.getPlayer(sender.getName()).getUniqueId().toString().equalsIgnoreCase(passcode)) {
                    sender.sendMessage(ChatColor.GREEN+"Passcode was correct joining " + queue.getCreator() + "'s" +  " party");
                    Party_API.Party_Leaders.get(queue.getCreator()).set_Member(sender.getName());
                    Bukkit.getPlayer(queue.getCreator()).sendMessage(ChatColor.GREEN+sender.getName() + " has joined your party!");
                    Party_API.Password_Queue.remove(sender.getName());
                    Party_API.inParty.put(sender.getName(), queue.getCreator());

                } else {
                    sender.sendMessage(ChatColor.RED+"[ERROR]Incorrect passcode!");

                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"You /are already in a party!");

            }
            //Party party = Party_API.Party_Leaders.get(sender.getName());
            //party.set_Member(args[0]);
            //Party_API.Party_Leaders.put(sender.getName(),party);

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 &&
                args[0].equalsIgnoreCase("remove")) {

            if((Party_API.Party_Leaders.containsKey(sender.getName()))) {
                sender.sendMessage(ChatColor.RED + "removing member...");
                Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED+sender.getName() + " has remove you from party!");
                Party party = Party_API.Party_Leaders.get(sender.getName());
                party.Remove_Member(args[1]);
                Party_API.Party_Leaders.put(sender.getName(), party);
                Party_API.inParty.remove(args[1]);
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"You can't do that!");

            }

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("leave")) {
            String name = sender.getName();
                if (Party_API.inParty.containsKey(name))
             {
                //party member kills mob
                String leader = Party_API.inParty.get(name);

                Party party = Party_API.Party_Leaders.get(leader);
                 //noinspection unchecked
                 @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();
                System.out.println(party.get_MembersList());
                 party.Remove_Member(name);
                Party_API.inParty.remove(name);
                 Party_API.Party_Leaders.put(leader,party);
                for (String people : members) {

                    Player partyMember = Bukkit.getPlayer(people);
                    Bukkit.getPlayer(people).sendMessage(name + " has left the party!");
                    if (partyMember == null) {
                        System.out.println("Player error");

                    }
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"No party to leave!");

            }

            return true;
        }


        return false;
    }

}

