package com.paully104.reitzmmo.Skills;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Paul on 5/7/2017.
 */
class Chicken_Launcher {

    public static void performChickenLauncher(EntityShootBowEvent event, Entity arrow)
    {
        Entity projectile =  event.getProjectile();
        for(int i=0; i< 3; i++)
        {
            event.setProjectile(arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.CHICKEN));
            event.getProjectile().setVelocity(projectile.getVelocity());
        }
    }
}
