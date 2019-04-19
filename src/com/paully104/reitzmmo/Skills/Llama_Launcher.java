package com.paully104.reitzmmo.Skills;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Paul on 5/7/2017.
 */
class Llama_Launcher {

    public static void performLlamaLauncher(EntityShootBowEvent event, Entity arrow)
    {
        Entity projectile =  event.getProjectile();
        for(int i=0; i< 3; i++)
        {
            event.setProjectile(arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.LLAMA));
            event.getProjectile().setVelocity(projectile.getVelocity());
        }
    }
}

