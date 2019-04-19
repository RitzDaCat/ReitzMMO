package com.paully104.reitzmmo.Skills;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Paul on 5/6/2017.
 */
class Barrage {

    public static void performBarrage(EntityShootBowEvent event, Entity arrow)
    {

        for (int i = 1; i < 5; i++)
        {
            /*Barrage needs to be more fluid was originally 0,i,0 */
            arrow.getWorld().spawnArrow(arrow.getLocation().add(0,1,0), arrow.getVelocity(), 3, 3).setShooter(event.getEntity());
        }


    }
}
