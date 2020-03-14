package io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MonsterDefeatsPlayer implements Listener {

    @EventHandler
    public static void monsterDefeatsPlayer(PlayerDeathEvent e)
    {
        Player p = (Player)e.getEntity();
        for(Player pp : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(p.getName() + " took a lethal hit for: " + p.getLastDamage() + " damage!!!");
            p.sendMessage("That damage came from: " + p.getLastDamageCause().getEventName() + ", ouch!");

        }


    }
}
