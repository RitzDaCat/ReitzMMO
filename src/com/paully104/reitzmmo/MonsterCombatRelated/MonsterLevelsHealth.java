package com.paully104.reitzmmo.MonsterCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * Created by Paul on 3/22/2016.
 */
public class MonsterLevelsHealth implements Listener {

    //SpecialMobs Enabled Section
    private final boolean kingMobsEnabled = API.specialMonsterConfig.getBoolean("kingMobsEnabled");
    private final boolean notoriousMobsEnabled = API.specialMonsterConfig.getBoolean("notoriusMobsEnabled");
    private final boolean devilishMobsEnabled = API.specialMonsterConfig.getBoolean("devilishMobsEnabled");
    private final boolean dumbMobsEnabled = API.specialMonsterConfig.getBoolean("dumbMobsEnabled");

    //SpecialMobs LV Section
    private final int kingMobsLV = API.specialMonsterConfig.getInt("kingMobsLVDifference");
    private final int notoriousMobsLV = API.specialMonsterConfig.getInt("notoriousMobsLVDifference");
    private final int devilishMobsLV = API.specialMonsterConfig.getInt("devilishMobsLVDifference");
    private final int dumbMobsLV = API.specialMonsterConfig.getInt("dumbMobsLVDifference");

    //SpecialMobsSpecialStuff
    private final boolean specialMobGlowingEnabled = API.specialMonsterConfig.getBoolean("specialMonsterGlowEnabled");
    private final boolean specialMobSilentEnabled = API.specialMonsterConfig.getBoolean("specialMonsterSilentEnabled");


    //MONSTER HP SECTION
    private final int blocksPerMobLevel = API.monsterConfig.getInt("BLOCKS-PER-MOB-LEVEL");
    private final int zombieBaseHP = API.monsterConfig.getInt("ZOMBIE_BASE_HP");
    private final int wolfBaseHP = API.monsterConfig.getInt("WOLF_BASE_HP");
    private final int villagerBaseHP = API.monsterConfig.getInt("VILLAGER_BASE_HP");
    private final int squidBaseHP = API.monsterConfig.getInt("SQUID_BASE_HP");
    private final int spiderBaseHP = API.monsterConfig.getInt("SPIDER_BASE_HP");
    private final int snowmanBaseHP = API.monsterConfig.getInt("SNOWMAN_BASE_HP");
    private final int slimeBaseHP = API.monsterConfig.getInt("SLIME_BASE_HP");
    private final int skeletonBaseHP = API.monsterConfig.getInt("SKELETON_BASE_HP");
    private final int silverfishBaseHP = API.monsterConfig.getInt("SILVERFISH_BASE_HP");
    private final int sheepBaseHP = API.monsterConfig.getInt("SHEEP_BASE_HP");
    private final int rabbitBaseHP = API.monsterConfig.getInt("RABBIT_BASE_HP");
    private final int pigzombieBaseHP = API.monsterConfig.getInt("PIGZOMBIE_BASE_HP");
    private final int pigBaseHP = API.monsterConfig.getInt("PIG_BASE_HP");
    private final int mushroomcowBaseHP = API.monsterConfig.getInt("MUSHROOMCOW_BASE_HP");
    private final int magmacubeBaseHP = API.monsterConfig.getInt("MAGMACUBE_BASE_HP");
    private final int guardianBaseHP = API.monsterConfig.getInt("GUARDIAN_BASE_HP");
    private final int giantBaseHP = API.monsterConfig.getInt("GIANT_BASE_HP");
    private final int ghastBaseHP = API.monsterConfig.getInt("GHAST_BASE_HP");
    private final int endermiteBaseHP = API.monsterConfig.getInt("ENDERMITE_BASE_HP");
    private final int endermanBaseHP = API.monsterConfig.getInt("ENDERMAN_BASE_HP");
    private final int enderdragonBaseHP = API.monsterConfig.getInt("ENDERDRAGON_BASE_HP");
    private final int creeperBaseHP = API.monsterConfig.getInt("CREEPER_BASE_HP");
    private final int cowBaseHP = API.monsterConfig.getInt("COW_BASE_HP");
    private final int chickenBaseHP = API.monsterConfig.getInt("CHICKEN_BASE_HP");
    private final int cavespiderBaseHP = API.monsterConfig.getInt("CAVESPIDER_BASE_HP");
    private final int blazeBaseHP = API.monsterConfig.getInt("BLAZE_BASE_HP");
    private final int witchBaseHP = API.monsterConfig.getInt("WITCH_BASE_HP");
    private final int witherSkeletonBaseHP = API.monsterConfig.getInt("WITHERSKELETON_BASE_HP");
    private final int shulkerSkeletonBaseHP = API.monsterConfig.getInt("SHULKER_BASE_HP");
    private final int pillagerBaseHP = API.monsterConfig.getInt("PILLAGER_BASE_HP");
    private final int illusionerBaseHP = API.monsterConfig.getInt("ILLUSIONER_BASE_HP");
    private final int evokerBaseHP = API.monsterConfig.getInt("EVOKER_BASE_HP");
    private final int ravagerBaseHP = API.monsterConfig.getInt("RAVAGER_BASE_HP");
    private final int batBaseHP = API.monsterConfig.getInt("BAT_BASE_HP");
    private final int drownedBaseHP = API.monsterConfig.getInt("DROWNED_BASE_HP");
    private final boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("NAMEPLATES_ENABLED");

    private int calculateDistanceFromSpawn(Location worldSpawn, Location monsterSpawn)
    {
        float deltaX = (float) (worldSpawn.getX() - monsterSpawn.getX());
        float deltaY= (float) (worldSpawn.getY() - monsterSpawn.getY());
        float deltaZ= (float) (worldSpawn.getZ() - monsterSpawn.getZ());
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        String worldName = monsterSpawn.getWorld().getName();
        int distance2 = (Math.round(distance) / blocksPerMobLevel);
        //change this later to the config option
        int baseWorldDamage = API.worldConfig.getInt(worldName);

        distance2 = distance2 + baseWorldDamage;
        if(distance2 < 1)
        {
            distance2 = 1;
        }
        return distance2;

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void applyMonsterLevelOnSpawn(CreatureSpawnEvent e)
    {

        int worldEnabled = API.worldConfig.getInt(e.getLocation().getWorld().getName());
        if(worldEnabled != -1)
        {
            if(monsterNameplatesEnabled) {
                e.getEntity().setCustomNameVisible(true);
            }

            Location worldSpawn = e.getLocation().getWorld().getSpawnLocation();
            Location monsterSpawn = e.getLocation();
            if (null == monsterSpawn) return;//if there's a problem
            int distance = calculateDistanceFromSpawn(worldSpawn, monsterSpawn);

            //The world is set to -1 means don't apply to mobs


            Random r = new Random();
            int low = 0;
            int high = 100;
            int result = r.nextInt(high - low) + low;
            String mobName = e.getEntityType().toString().substring(0, 1).toUpperCase() + e.getEntityType().toString().toLowerCase().substring(1);
            if ((result >= 98 && e.getEntity() instanceof Monster) && kingMobsEnabled)
            {
                distance = distance + kingMobsLV;
                if(distance <1)
                {
                    distance = 1;
                }
                String levelColor = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("King " + mobName + levelColor);
                if(specialMobGlowingEnabled) {
                    e.getEntity().setGlowing(true);
                }
                if(specialMobSilentEnabled){
                    e.getEntity().setSilent(true);
                }

            }
            else if ((result >= 90 && e.getEntity() instanceof Monster) && notoriousMobsEnabled)
            {
                distance = distance + notoriousMobsLV;
                if(distance <1)
                {
                    distance = 1;
                }
                String levelColor = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("Notorious " + mobName + levelColor);
                if(specialMobGlowingEnabled) {
                    e.getEntity().setGlowing(true);
                }
                if(specialMobSilentEnabled){
                    e.getEntity().setSilent(true);
                }

            }
            else if ((result == 66 && e.getEntity() instanceof Monster) && devilishMobsEnabled)
            {
                distance = distance + devilishMobsLV;
                if(distance <1)
                {
                    distance = 1;
                }
                String levelColor = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("Devilish " + mobName + levelColor);
                if(specialMobGlowingEnabled) {
                    e.getEntity().setGlowing(true);
                }
                if(specialMobSilentEnabled){
                    e.getEntity().setSilent(true);
                }
            }
            else if ((result <= 1 && e.getEntity() instanceof Monster) && dumbMobsEnabled)
            {

                    distance = distance + dumbMobsLV;
                    if(distance <1)
                    {
                        distance = 1;
                    }
                    String levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    e.getEntity().setCustomName("Dumb " + mobName + levelColor);
                    if(specialMobGlowingEnabled) {
                        e.getEntity().setGlowing(true);
                    }
                    if(specialMobSilentEnabled){
                        e.getEntity().setSilent(true);
                    }
                    e.getEntity().getEquipment().setHelmet(new ItemStack(Material.BUCKET, 1));
            }
            else
                {
                String levelColor = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName(mobName + levelColor);
                }
            //updated on 5/7 for bad boys

            //configure health per mob

            switch (e.getEntity().getType()) {
                case ZOMBIE:
                    Zombie zombie = (Zombie) e.getEntity();
                    if (zombie.isBaby()) {
                        e.getEntity().setMaxHealth(distance * zombieBaseHP);
                        e.getEntity().setHealth(distance * zombieBaseHP);
                        //Update onm 4/26/2017 To slow the hell down baby zombies
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 0.6);
                        //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                        e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                        //updated on 4/26/2017 to increase the chance of getting tons of zombies :)
                        e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() + 0.2);
                    } else {
                        e.getEntity().setMaxHealth(distance * zombieBaseHP);
                        e.getEntity().setHealth(distance * zombieBaseHP);
                        //superfastZombie
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                        //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                        e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                        //updated on 4/26/2017 to increase the chance of getting tons of zombies :)
                        e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() + 0.20);

                    }
                    break;
                case WOLF:
                    e.getEntity().setMaxHealth(distance * wolfBaseHP);
                    e.getEntity().setHealth(distance * wolfBaseHP);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                    break;
                case VILLAGER:
                    e.getEntity().setMaxHealth(distance * villagerBaseHP);
                    e.getEntity().setHealth(distance * villagerBaseHP);
                    break;
                case SQUID:
                    e.getEntity().setMaxHealth(distance * squidBaseHP);
                    e.getEntity().setHealth(distance * squidBaseHP);
                    break;
                case SPIDER:
                    e.getEntity().setMaxHealth(distance * spiderBaseHP);
                    e.getEntity().setHealth(distance * spiderBaseHP);
                    break;
                case SNOWMAN:
                    e.getEntity().setMaxHealth(distance * snowmanBaseHP);
                    e.getEntity().setHealth(distance * snowmanBaseHP);
                    break;
                case SLIME:
                    e.getEntity().setMaxHealth(distance * slimeBaseHP);
                    e.getEntity().setHealth(distance * slimeBaseHP);
                    break;
                case SKELETON:
                    e.getEntity().setMaxHealth(distance * skeletonBaseHP);
                    e.getEntity().setHealth(distance * skeletonBaseHP);
                    //slower Skellies because they suck
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * .80);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                    //Lets slow down the skeleton firing rate to make it more fair UPDATE: Not an accessible trait!
                    break;
                case SILVERFISH:
                    e.getEntity().setMaxHealth(distance * silverfishBaseHP);
                    e.getEntity().setHealth(distance * silverfishBaseHP);
                    break;
                case SHEEP:
                    e.getEntity().setMaxHealth(distance * sheepBaseHP);
                    e.getEntity().setHealth(distance * sheepBaseHP);
                    break;
                case RABBIT:
                    e.getEntity().setMaxHealth(distance * rabbitBaseHP);
                    e.getEntity().setHealth(distance * rabbitBaseHP);
                    break;
                case PIG_ZOMBIE:
                    e.getEntity().setMaxHealth(distance * pigzombieBaseHP);
                    e.getEntity().setHealth(distance * pigzombieBaseHP);
                    //superfast
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                    break;
                case PIG:
                    e.getEntity().setMaxHealth(distance * pigBaseHP);
                    e.getEntity().setHealth(distance * pigBaseHP);
                    break;
                case MUSHROOM_COW:
                    e.getEntity().setMaxHealth(distance * mushroomcowBaseHP);
                    e.getEntity().setHealth(distance * mushroomcowBaseHP);
                    break;
                case MAGMA_CUBE:
                    e.getEntity().setMaxHealth(distance * magmacubeBaseHP);
                    e.getEntity().setHealth(distance * magmacubeBaseHP);
                    break;
                case GUARDIAN:
                    e.getEntity().setMaxHealth(distance * guardianBaseHP);
                    e.getEntity().setHealth(distance * guardianBaseHP);
                    break;
                case ELDER_GUARDIAN:
                    e.getEntity().setMaxHealth(distance * guardianBaseHP);
                    e.getEntity().setHealth(distance * guardianBaseHP);
                    break;
                case GIANT:
                    e.getEntity().setMaxHealth(distance * giantBaseHP);
                    e.getEntity().setHealth(distance * giantBaseHP);
                    break;
                case GHAST:
                    e.getEntity().setMaxHealth(distance * ghastBaseHP);
                    e.getEntity().setHealth(distance * ghastBaseHP);
                    //Slow them down
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * .85);
                    break;
                case ENDERMITE:
                    e.getEntity().setMaxHealth(distance * endermiteBaseHP);
                    e.getEntity().setHealth(distance * endermiteBaseHP);
                    break;
                case ENDERMAN:
                    e.getEntity().setMaxHealth(distance * endermanBaseHP);
                    e.getEntity().setHealth(distance * endermanBaseHP);
                    break;
                case ENDER_DRAGON:
                    distance = 250;
                    e.getEntity().setMaxHealth(distance * enderdragonBaseHP);
                    e.getEntity().setHealth(distance * enderdragonBaseHP);
                    break;
                case CREEPER:
                    e.getEntity().setMaxHealth(distance * creeperBaseHP);
                    e.getEntity().setHealth(distance * creeperBaseHP);
                    //superFastCreepers?

                    //this is paul being reasonable
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.4);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 1.75);
                    break;
                case COW:
                    e.getEntity().setMaxHealth(distance * cowBaseHP);
                    e.getEntity().setHealth(distance * cowBaseHP);
                    break;
                case CHICKEN:
                    e.getEntity().setMaxHealth(distance * chickenBaseHP);
                    e.getEntity().setHealth(distance * chickenBaseHP);
                    break;
                case CAVE_SPIDER:
                    e.getEntity().setMaxHealth(distance * cavespiderBaseHP);
                    e.getEntity().setHealth(distance * cavespiderBaseHP);
                    break;
                case BLAZE:
                    e.getEntity().setMaxHealth(distance * blazeBaseHP);
                    e.getEntity().setHealth(distance * blazeBaseHP);
                    break;
                case WITCH:
                    e.getEntity().setMaxHealth(distance * witchBaseHP);
                    e.getEntity().setHealth(distance * witchBaseHP);
                    break;
                case WITHER_SKELETON:
                    e.getEntity().setMaxHealth(distance * witherSkeletonBaseHP);
                    e.getEntity().setHealth(distance * witherSkeletonBaseHP);
                    break;
                case SHULKER:
                    e.getEntity().setMaxHealth(distance * shulkerSkeletonBaseHP);
                    e.getEntity().setHealth(distance * shulkerSkeletonBaseHP);
                    break;
                case PILLAGER:
                    e.getEntity().setMaxHealth(distance * pillagerBaseHP);
                    e.getEntity().setHealth(distance * pillagerBaseHP);
                    break;
                case ILLUSIONER:
                    e.getEntity().setMaxHealth(distance * illusionerBaseHP);
                    e.getEntity().setHealth(distance * illusionerBaseHP);
                    break;
                case EVOKER:
                    e.getEntity().setMaxHealth(distance * evokerBaseHP);
                    e.getEntity().setHealth(distance * evokerBaseHP);
                    break;
                case RAVAGER:
                    e.getEntity().setMaxHealth(distance * ravagerBaseHP);
                    e.getEntity().setHealth(distance * ravagerBaseHP);
                    break;

                case BAT:
                    e.getEntity().setMaxHealth(distance *batBaseHP);
                    e.getEntity().setHealth(distance * batBaseHP);
                    break;
                case DROWNED:
                    e.getEntity().setMaxHealth(distance *drownedBaseHP);
                    e.getEntity().setHealth(distance * drownedBaseHP);
                    break;



            }


        }




    }


}
