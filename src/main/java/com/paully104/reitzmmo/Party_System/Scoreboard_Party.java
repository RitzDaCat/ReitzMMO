package com.paully104.reitzmmo.Party_System;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;
import java.util.Objects;

public class Scoreboard_Party{

    public void Scoreboard_PartySetup(Player p) {
        Scoreboard sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
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
                    first.setScore((int)(Objects.requireNonNull(Bukkit.getPlayer(p.getName())).getHealth()));

                    if(null != members.get(0)) {
                        second = objective.getScore(members.get(0));
                        second.setScore((int) (Objects.requireNonNull(Bukkit.getPlayer(members.get(0))).getHealth()));
                    }
                    if(null != members.get(1)) {
                        third = objective.getScore(members.get(1));
                        third.setScore((int) (Objects.requireNonNull(Bukkit.getPlayer(members.get(1))).getHealth()));
                    }

                    Objects.requireNonNull(Bukkit.getPlayer(p.getName())).setScoreboard(sb);
                if(null != members.get(0)) {
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(0))).setScoreboard(sb);
                }
                if(null != members.get(1)) {
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(1))).setScoreboard(sb);
                }
                try{
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(2))).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException ignored)
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
                first.setScore((int)(Objects.requireNonNull(Bukkit.getPlayer(leader)).getHealth()));

                    second = objective.getScore(p.getName());
                    second.setScore((int) (Objects.requireNonNull(Bukkit.getPlayer(p.getName())).getHealth()));

                if(null != members.get(1)) {
                    third = objective.getScore(members.get(1));
                    third.setScore((int) (Objects.requireNonNull(Bukkit.getPlayer(members.get(1))).getHealth()));
                }

                Objects.requireNonNull(Bukkit.getPlayer(leader)).setScoreboard(sb);
                Objects.requireNonNull(Bukkit.getPlayer(p.getName())).setScoreboard(sb);

                try{
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(0))).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException ignored)
                {

                }
                try{
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(1))).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException ignored)
                {

                }
                try{
                    Objects.requireNonNull(Bukkit.getPlayer(members.get(2))).setScoreboard(sb);
                }
                catch (IndexOutOfBoundsException ignored)
                {

                }
            }
        } catch (IllegalArgumentException ignored)
        {
        }
    }
}
