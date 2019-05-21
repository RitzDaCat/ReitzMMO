package com.paully104.reitzmmo.Party_System;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityRegainHealthEvent implements Listener {

    @EventHandler
    public void EntityRegainHealthEvent(org.bukkit.event.entity.EntityRegainHealthEvent e) {

        if(e.getEntity() instanceof Player)
        {
            Player p = (Player)e.getEntity();
            Scoreboard_Party scoreboard = new Scoreboard_Party();
            if (Party_API.Party_Leaders.containsKey((p.getName())))
            {
                scoreboard.Scoreboard_PartySetup(p);
            }
            else
            {

            }
            if (Party_API.inParty.containsKey(p.getName())) {
                scoreboard.Scoreboard_PartySetup(p);
            }
            else
            {

            }

        }




    }

}
