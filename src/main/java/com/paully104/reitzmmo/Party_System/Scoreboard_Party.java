package com.paully104.reitzmmo.Party_System;

import net.minecraft.server.v1_14_R1.ChatComponentScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import javax.sound.midi.SysexMessage;
import java.util.List;

public class Scoreboard_Party{

    public Scoreboard Scoreboard_PartySetup(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        try {


            //Lets get all the players in their party and add to the board
            if(Party_API.Party_Leaders.containsKey(p.getName()))
            {
                //They are a party leader lets get their members
                //this currently works
                Party party = Party_API.Party_Leaders.get(p.getName());
                @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();


                Objective objective = sb.registerNewObjective(p.getDisplayName(), "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(ChatColor.RED + "Party");

                Score first;
                Score second;
                Score third;
                Score fourth;


                    first=objective.getScore(p.getName());
                    first.setScore((int)(Bukkit.getPlayer(p.getName()).getHealth()));

                    if(null != members.get(0)) {
                        second = objective.getScore(members.get(0));
                        second.setScore((int) (Bukkit.getPlayer(members.get(0)).getHealth()));
                    }
                    if(null != members.get(1)) {
                        third = objective.getScore(members.get(1));
                        third.setScore((int) (Bukkit.getPlayer(members.get(1)).getHealth()));
                    }

                    Bukkit.getPlayer(p.getName()).setScoreboard(sb);
                if(null != members.get(0)) {
                    Bukkit.getPlayer(members.get(0)).setScoreboard(sb);
                }
                if(null != members.get(1)) {
                    Bukkit.getPlayer(members.get(1)).setScoreboard(sb);
                }
                try{
                    Bukkit.getPlayer(members.get(2)).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException out)
                {

                }
            }

            else if(Party_API.inParty.containsKey(p.getName()))
            {
                String leader = Party_API.inParty.get(p.getName());
                Party party = Party_API.Party_Leaders.get(leader);
                @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();
                //leader first

                Objective objective = sb.registerNewObjective(leader, "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(ChatColor.RED + "Party");

                Score first;
                Score second;
                Score third;
                Score fourth;


                first=objective.getScore(leader);
                first.setScore((int)(Bukkit.getPlayer(leader).getHealth()));

                    second = objective.getScore(p.getName());
                    second.setScore((int) (Bukkit.getPlayer(p.getName()).getHealth()));

                if(null != members.get(1)) {
                    third = objective.getScore(members.get(1));
                    third.setScore((int) (Bukkit.getPlayer(members.get(1)).getHealth()));
                }

                Bukkit.getPlayer(leader).setScoreboard(sb);
                Bukkit.getPlayer(p.getName()).setScoreboard(sb);

                try{
                    Bukkit.getPlayer(members.get(0)).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException out)
                {

                }
                try{
                    Bukkit.getPlayer(members.get(1)).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException out)
                {

                }
                try{
                    Bukkit.getPlayer(members.get(2)).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException out)
                {

                }
            }
        } catch (IllegalArgumentException ignored)
        {
        }
        return sb;
    }
}
