package com.paully104.reitzmmo.Party_System;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class createPartyScoreboard {

    public static final HashMap<String, Scoreboard> playerPartyScoreBoard = new HashMap<>();
    public static final Boolean partyScoreBoardEnabled =API.partyConfig.getBoolean("Party_Scoreboard.Enabled");

    public void setPartyScoreboardonPlayer(Player p)
    {
        if(partyScoreBoardEnabled) {
            //create a scoreboard for the player
            String uuid = p.getUniqueId().toString();
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective objective = board.registerNewObjective("Party", "Dummy", "Test2", RenderType.INTEGER);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.YELLOW + "[Party]");
            playerPartyScoreBoard.put(uuid, board);

        }



    }

    public void removePlayerFromScoreboard(Player boardOwner, Player whoIsBeingRemoved)
    {
        if(partyScoreBoardEnabled) {
            //This works for the party leader
            String whoisbeingremoveName = whoIsBeingRemoved.getDisplayName();
            System.out.println(whoisbeingremoveName);
            String uuid = boardOwner.getUniqueId().toString();
            Scoreboard board = playerPartyScoreBoard.get(uuid);
            board.clearSlot(DisplaySlot.SIDEBAR);
            System.out.println("Objectives" + board.getObjectives().toArray().toString());
            Objective objective = board.getObjective("Party");
            objective.unregister();
            setPartyScoreboardonPlayer(boardOwner);
            updatePartyScoreboardonPlayer(boardOwner);

            //person who left needs to be updated
            String leavingUUID = whoIsBeingRemoved.getUniqueId().toString();
            Scoreboard leavingBoard = playerPartyScoreBoard.get(leavingUUID);
            leavingBoard.clearSlot(DisplaySlot.SIDEBAR);
            Objective objective2 = leavingBoard.getObjective("Party");
            objective2.unregister();
            setPartyScoreboardonPlayer(whoIsBeingRemoved);
        }




    }

    public void updatePartyScoreboardonPlayer(Player p) {
        if (partyScoreBoardEnabled) {
            String uuid = p.getUniqueId().toString();
            Scoreboard board = playerPartyScoreBoard.get(uuid);
            //lets try setting this here after a clear?
            Objective objective = board.getObjective("Party");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.YELLOW + "[Party]");

            //PARTY LOGIC
            if ((Party_API.Party_Leaders.containsKey(p.getName()))) {
                //You are in a party and the leader
                Party party = Party_API.Party_Leaders.get(p.getName());
                p.setScoreboard(board);
                //p.sendMessage(party.get_Members());
                for (Object member : party.get_MembersList()) {
                    try {
                        //each object is the player in the party need to make a score for each
                        Score test = objective.getScore(member.toString());
                        test.setScore((int) Bukkit.getPlayer(member.toString()).getHealth());
                        Bukkit.getPlayer(member.toString()).setScoreboard(board);
                    } catch (NullPointerException e) {

                    }
                }
            } else if (Party_API.inParty.containsKey(p.getName())) {
                String leader = Party_API.inParty.get(p.getName());
                Party party = Party_API.Party_Leaders.get(leader);
                for (Object member : party.get_MembersList()) {
                    //each object is the player in the party need to make a score for each
                    try {
                        Score test = objective.getScore(member.toString());
                        test.setScore((int) Bukkit.getPlayer(member.toString()).getHealth());
                        Bukkit.getPlayer(member.toString()).setScoreboard(board);
                    } catch (NullPointerException e) {

                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "[ERROR]You are not in a party!");
            }
            System.out.println(board.getScores("Test"));
            System.out.println(board.getObjectives());

        }
    }
        //p.setScoreboard(board);





}
