package com.paully104.reitzmmo.Skills;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Paul on 5/6/2017.
 */
class Bomb_Arrow {

    public static void performFireArrow(EntityShootBowEvent event, Entity arrow)
    {
        Projectile projectile = (Projectile)event.getProjectile();
            event.setProjectile(arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT));
            event.getProjectile().setVelocity(projectile.getVelocity());
    }
}
