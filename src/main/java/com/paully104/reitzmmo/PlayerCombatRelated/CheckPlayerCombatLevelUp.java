package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * Created by Paul on 3/22/2016.
 */
class CheckPlayerCombatLevelUp {

    private static final String PLAYERCOMBATEXP = "Combat-EXP";

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerLevelUp");
    private final static String ATTACKSCALE = "Scaling.Player.AttackScale";
    private final static String HEALTHSCALE = "Scaling.Player.HealthScale";
    private final static String DEFENSESCALE = "Scaling.Player.DefenseScale";
    private final static String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";
    private final static String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";

    private final static String ATTACK = "Attack";
    private final static String HEALTH = "Health";
    private final static String LEVEL = "Level";

    public  void CheckLevelUp(Player p)
    {
        int combatexp = API.Players.get(p.getUniqueId().toString()).getData().getInt(PLAYERCOMBATEXP);
        int level = API.Players.get(p.getUniqueId().toString()).getData().getInt(LEVEL);
        int combatexpneeded = level * (API.playerConfig.getInt(WORLDBASECOMBATEXP) * API.playerConfig.getInt(WORLDBASECOMBATEXP_MULTIPLIER));
        System.out.println("combatexpneeded:" + combatexpneeded);
        //level up occurs
        if(combatexp >= combatexpneeded)
        {
            level = level+1;
            combatexp = combatexp - combatexpneeded;
            API.Players.get(p.getUniqueId().toString()).getData().set(PLAYERCOMBATEXP, combatexp);
            API.Players.get(p.getUniqueId().toString()).getData().set(LEVEL, level);

            /* message sent to players for leveling u */
            p.sendMessage(ChatColor.GREEN + "[ReitzMMO]" + ChatColor.WHITE + " You have leveled up to: " + ChatColor.YELLOW +  level);
            String levelMessage = ChatColor.YELLOW + Integer.toString(level);
            p.sendTitle(levelMessage,"Congratulations, you have leveled up!",10,70,10);

            API.Players.get(p.getUniqueId().toString()).getData().set(ATTACK, (level * API.playerConfig.getInt(ATTACKSCALE)));
            API.Players.get(p.getUniqueId().toString()).getData().set(HEALTH, (18 + (level * API.playerConfig.getInt(HEALTHSCALE))));
            API.Players.get(p.getUniqueId().toString()).getData().set(PLAYERCOMBATEXP, combatexp);
            Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((18 + (level * API.playerConfig.getInt(HEALTHSCALE))));


        }

        if(debugEnabled)
        {
            System.out.println(p.getName() + " combatexp: " + combatexp + " " + "combatexpneeded: " + combatexpneeded);


        }




    }
}
