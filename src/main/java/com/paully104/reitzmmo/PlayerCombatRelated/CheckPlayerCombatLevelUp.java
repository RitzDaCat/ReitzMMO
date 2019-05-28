package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 3/22/2016.
 */
class CheckPlayerCombatLevelUp {

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerLevelUp");

    public  void CheckLevelUp(Player p)
    {
        int combatexp = API.Players.get(p.getUniqueId().toString()).getData().getInt("Combat-EXP");
        int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
        int combatexpneeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
        //level up occurs
        if(combatexp >= combatexpneeded)
        {
            level = level+1;
            combatexp = combatexp - combatexpneeded;
            API.Players.get(p.getUniqueId().toString()).getData().set("Combat-EXP", combatexp);
            API.Players.get(p.getUniqueId().toString()).getData().set("Level", level);

            /* message sent to players for leveling u */
            p.sendMessage(ChatColor.GREEN + "[ReitzMMO]" + ChatColor.WHITE + " You have leveled up to: " + ChatColor.YELLOW +  level);
            String levelMessage = ChatColor.YELLOW + Integer.toString(level);
            p.sendTitle(levelMessage,"Congratulations, you have leveled up!",10,70,10);

            API.Players.get(p.getUniqueId().toString()).getData().set("Attack", (level * API.playerConfig.getInt("AttackScale")));
            API.Players.get(p.getUniqueId().toString()).getData().set("Health", (18 + (level * API.playerConfig.getInt("HealthScale"))));
            API.Players.get(p.getUniqueId().toString()).getData().set("CombatEXP", combatexp);
            p.setMaxHealth((18 + (level * API.playerConfig.getInt("HealthScale"))));


        }

        if(debugEnabled)
        {
            System.out.println(p.getName() + " combatexp: " + combatexp + " " + "combatexpneeded: " + combatexpneeded);


        }




    }
}
