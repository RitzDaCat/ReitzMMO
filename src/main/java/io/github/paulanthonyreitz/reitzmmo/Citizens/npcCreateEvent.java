package io.github.paulanthonyreitz.reitzmmo.Citizens;

import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

public class npcCreateEvent implements Listener {
    @EventHandler
    public void npccreateEvent(NPCSpawnEvent e) {
        Player p = (Player)e.getNPC().getEntity();
        p.getScoreboard().getObjective(DisplaySlot.BELOW_NAME).setDisplayName("TEST");
    }
}
