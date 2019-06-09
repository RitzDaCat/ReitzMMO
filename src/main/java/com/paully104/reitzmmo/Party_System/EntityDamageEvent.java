package com.paully104.reitzmmo.Party_System;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageEvent implements Listener {

    @EventHandler
    public void EntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent e) {

        if(e.getEntity() instanceof Player)
        {
            Player p = (Player)e.getEntity();
            if (Party_API.Party_Leaders.containsKey((p.getName())))
            {
                Scoreboard_Party scoreboard = new Scoreboard_Party();
                scoreboard.Scoreboard_PartySetup(p);
            }
            if (Party_API.inParty.containsKey(p.getName())) {
                Scoreboard_Party scoreboard = new Scoreboard_Party();
                scoreboard.Scoreboard_PartySetup(p);
            }

        }




    }
}
