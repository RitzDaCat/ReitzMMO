package io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterCombatAbilities implements Listener
{

    @EventHandler
    public void monsterUsingAbilities(EntityDamageByEntityEvent e) {

        int worldEnabled = API.worldConfig.getInt(e.getEntity().getLocation().getWorld().getName());
        if (worldEnabled != -1 && !e.getEntity().hasMetadata("NPC"))
        {
            Entity defender = e.getEntity();
            Entity attacker = e.getDamager();
            EntityType attackerType = attacker.getType();
            EntityType defenderType = defender.getType();
            if (defenderType == EntityType.PLAYER)
            {
                if(nameSpaceKey.getMonsterNameFromContainer(attacker).contains("King"))
                {
                    int random = ThreadLocalRandom.current().nextInt(10);
                    if(random<5)
                    {
                        Player p = (Player) defender;
                        p.setVelocity(new Vector(0,2,0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,600,1));
                        p.sendMessage(ChatColor.RED + nameSpaceKey.getMonsterNameFromContainer(attacker) + " " + ChatColor.MAGIC + "ALL HAIL THE KING");

                    }

                }
                else if(nameSpaceKey.getMonsterNameFromContainer(attacker).contains("Devilish"))
                {
                    int random = ThreadLocalRandom.current().nextInt(10);
                    if(random<5)
                    {
                        Player p = (Player) defender;
                        p.setVelocity(new Vector(0,2,0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN,600,1));
                        p.sendMessage(ChatColor.RED + nameSpaceKey.getMonsterNameFromContainer(attacker) + " " + ChatColor.MAGIC + "Devil Magic...");

                    }

                }
                else if(nameSpaceKey.getMonsterNameFromContainer(attacker).contains("Notorious"))
                {
                    int random = ThreadLocalRandom.current().nextInt(10);
                    if(random<5)
                    {
                        Player p = (Player) defender;
                        p.setVelocity(new Vector(0,2,0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,600,1));
                        p.sendMessage(ChatColor.RED + nameSpaceKey.getMonsterNameFromContainer(attacker) + " " + ChatColor.MAGIC + "The hunt...");

                    }

                }
                else if(nameSpaceKey.getMonsterNameFromContainer(attacker).contains("Dumb"))
                {
                    int random = ThreadLocalRandom.current().nextInt(10);
                    if(random<5)
                    {
                        Player p = (Player) defender;
                        p.setVelocity(new Vector(0,2,0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,600,1));
                        p.sendMessage(ChatColor.RED + nameSpaceKey.getMonsterNameFromContainer(attacker) + " " + ChatColor.MAGIC + "Durrr");

                    }

                }

            }
        }
    }
}