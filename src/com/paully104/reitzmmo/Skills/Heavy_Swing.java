package com.paully104.reitzmmo.Skills;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

/**
 * Created by Paul on 5/7/2017.
 */
class Heavy_Swing {

    public static void performHeavySwing(PlayerInteractEntityEvent event) {
        Vector dir = event.getPlayer().getLocation().getDirection();
            event.getRightClicked().setVelocity(dir.multiply(2.00));

    }
}
