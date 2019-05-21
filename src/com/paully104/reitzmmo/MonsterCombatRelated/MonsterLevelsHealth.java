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
    
    
    //NAMEPLATE SECTION
    private final boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("NAMEPLATES_ENABLED");

    private final boolean zombieNameplate = API.monsterConfig.getBoolean("ZOMBIE_NAMEPLATES_ENABLED");
    private final boolean wolfNameplate = API.monsterConfig.getBoolean("WOLF_NAMEPLATES_ENABLED");
    private final boolean villagerNameplate = API.monsterConfig.getBoolean("VILLAGER_NAMEPLATES_ENABLED");
    private final boolean squidNameplate = API.monsterConfig.getBoolean("SQUID_NAMEPLATES_ENABLED");
    private final boolean spiderNameplate = API.monsterConfig.getBoolean("SPIDER_NAMEPLATES_ENABLED");
    private final boolean snowmanNameplate = API.monsterConfig.getBoolean("SNOWMAN_NAMEPLATES_ENABLED");
    private final boolean slimeNameplate = API.monsterConfig.getBoolean("SLIME_NAMEPLATES_ENABLED");
    private final boolean skeletonNameplate = API.monsterConfig.getBoolean("SKELETON_NAMEPLATES_ENABLED");
    private final boolean silverfishNameplate = API.monsterConfig.getBoolean("SILVERFISH_NAMEPLATES_ENABLED");
    private final boolean sheepNameplate = API.monsterConfig.getBoolean("SHEEP_NAMEPLATES_ENABLED");
    private final boolean rabbitNameplate = API.monsterConfig.getBoolean("RABBIT_NAMEPLATES_ENABLED");
    private final boolean pigzombieNameplate = API.monsterConfig.getBoolean("PIGZOMBIE_NAMEPLATES_ENABLED");
    private final boolean pigNameplate = API.monsterConfig.getBoolean("PIG_NAMEPLATES_ENABLED");
    private final boolean mushroomcowNameplate = API.monsterConfig.getBoolean("MUSHROOMCOW_NAMEPLATES_ENABLED");
    private final boolean magmacubeNameplate = API.monsterConfig.getBoolean("MAGMACUBE_NAMEPLATES_ENABLED");
    private final boolean guardianNameplate = API.monsterConfig.getBoolean("GUARDIAN_NAMEPLATES_ENABLED");
    private final boolean giantNameplate = API.monsterConfig.getBoolean("GIANT_NAMEPLATES_ENABLED");
    private final boolean ghastNameplate = API.monsterConfig.getBoolean("GHAST_NAMEPLATES_ENABLED");
    private final boolean endermiteNameplate = API.monsterConfig.getBoolean("ENDERMITE_NAMEPLATES_ENABLED");
    private final boolean endermanNameplate = API.monsterConfig.getBoolean("ENDERMAN_NAMEPLATES_ENABLED");
    private final boolean enderdragonNameplate = API.monsterConfig.getBoolean("ENDERDRAGON_NAMEPLATES_ENABLED");
    private final boolean creeperNameplate = API.monsterConfig.getBoolean("CREEPER_NAMEPLATES_ENABLED");
    private final boolean cowNameplate = API.monsterConfig.getBoolean("COW_NAMEPLATES_ENABLED");
    private final boolean chickenNameplate = API.monsterConfig.getBoolean("CHICKEN_NAMEPLATES_ENABLED");
    private final boolean cavespiderNameplate = API.monsterConfig.getBoolean("CAVESPIDER_NAMEPLATES_ENABLED");
    private final boolean blazeNameplate = API.monsterConfig.getBoolean("BLAZE_NAMEPLATES_ENABLED");
    private final boolean witchNameplate = API.monsterConfig.getBoolean("WITCH_NAMEPLATES_ENABLED");
    private final boolean witherSkeletonNameplate = API.monsterConfig.getBoolean("WITHERSKELETON_NAMEPLATES_ENABLED");
    private final boolean shulkerSkeletonNameplate = API.monsterConfig.getBoolean("SHULKER_NAMEPLATES_ENABLED");
    private final boolean pillagerNameplate = API.monsterConfig.getBoolean("PILLAGER_NAMEPLATES_ENABLED");
    private final boolean illusionerNameplate = API.monsterConfig.getBoolean("ILLUSIONER_NAMEPLATES_ENABLED");
    private final boolean evokerNameplate = API.monsterConfig.getBoolean("EVOKER_NAMEPLATES_ENABLED");
    private final boolean ravagerNameplate = API.monsterConfig.getBoolean("RAVAGER_NAMEPLATES_ENABLED");
    private final boolean batNameplate = API.monsterConfig.getBoolean("BAT_NAMEPLATES_ENABLED");
    private final boolean drownedNameplate = API.monsterConfig.getBoolean("DROWNED_NAMEPLATES_ENABLED");
    
    

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

                        if(monsterNameplatesEnabled && zombieNameplate) {


                            e.getEntity().setCustomNameVisible(true);
                        }
                        
                        
                    } else {
                        e.getEntity().setMaxHealth(distance * zombieBaseHP);
                        e.getEntity().setHealth(distance * zombieBaseHP);
                        //superfastZombie
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                        //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                        e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                        //updated on 4/26/2017 to increase the chance of getting tons of zombies :)
                        e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(e.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).getValue() + 0.20);

                        if(monsterNameplatesEnabled && zombieNameplate) {


                            e.getEntity().setCustomNameVisible(true);
                        }

                    }
                    break;
                case WOLF:
                    e.getEntity().setMaxHealth(distance * wolfBaseHP);
                    e.getEntity().setHealth(distance * wolfBaseHP);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                    if(monsterNameplatesEnabled && wolfNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case VILLAGER:
                    e.getEntity().setMaxHealth(distance * villagerBaseHP);
                    e.getEntity().setHealth(distance * villagerBaseHP);
                    if(monsterNameplatesEnabled && villagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SQUID:
                    e.getEntity().setMaxHealth(distance * squidBaseHP);
                    e.getEntity().setHealth(distance * squidBaseHP);
                    if(monsterNameplatesEnabled && squidNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SPIDER:
                    e.getEntity().setMaxHealth(distance * spiderBaseHP);
                    e.getEntity().setHealth(distance * spiderBaseHP);
                    if(monsterNameplatesEnabled && spiderNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SNOWMAN:
                    e.getEntity().setMaxHealth(distance * snowmanBaseHP);
                    e.getEntity().setHealth(distance * snowmanBaseHP);
                    if(monsterNameplatesEnabled && snowmanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SLIME:
                    e.getEntity().setMaxHealth(distance * slimeBaseHP);
                    e.getEntity().setHealth(distance * slimeBaseHP);
                    if(monsterNameplatesEnabled && slimeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SKELETON:
                    e.getEntity().setMaxHealth(distance * skeletonBaseHP);
                    e.getEntity().setHealth(distance * skeletonBaseHP);
                    //slower Skellies because they suck
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * .80);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 2.00);
                    if(monsterNameplatesEnabled && skeletonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    //Lets slow down the skeleton firing rate to make it more fair UPDATE: Not an accessible trait!
                    break;
                case SILVERFISH:
                    e.getEntity().setMaxHealth(distance * silverfishBaseHP);
                    e.getEntity().setHealth(distance * silverfishBaseHP);
                    if(monsterNameplatesEnabled && silverfishNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SHEEP:
                    e.getEntity().setMaxHealth(distance * sheepBaseHP);
                    e.getEntity().setHealth(distance * sheepBaseHP);
                    if(monsterNameplatesEnabled && sheepNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case RABBIT:
                    e.getEntity().setMaxHealth(distance * rabbitBaseHP);
                    e.getEntity().setHealth(distance * rabbitBaseHP);
                    if(monsterNameplatesEnabled && rabbitNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PIG_ZOMBIE:
                    e.getEntity().setMaxHealth(distance * pigzombieBaseHP);
                    e.getEntity().setHealth(distance * pigzombieBaseHP);
                    //superfast
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.25);
                    if(monsterNameplatesEnabled && pigzombieNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PIG:
                    e.getEntity().setMaxHealth(distance * pigBaseHP);
                    e.getEntity().setHealth(distance * pigBaseHP);
                    if(monsterNameplatesEnabled && pigNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case MUSHROOM_COW:
                    e.getEntity().setMaxHealth(distance * mushroomcowBaseHP);
                    e.getEntity().setHealth(distance * mushroomcowBaseHP);
                    if(monsterNameplatesEnabled && mushroomcowNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case MAGMA_CUBE:
                    e.getEntity().setMaxHealth(distance * magmacubeBaseHP);
                    e.getEntity().setHealth(distance * magmacubeBaseHP);
                    if(monsterNameplatesEnabled && magmacubeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GUARDIAN:
                    e.getEntity().setMaxHealth(distance * guardianBaseHP);
                    e.getEntity().setHealth(distance * guardianBaseHP);
                    if(monsterNameplatesEnabled && guardianNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ELDER_GUARDIAN:
                    e.getEntity().setMaxHealth(distance * guardianBaseHP);
                    e.getEntity().setHealth(distance * guardianBaseHP);
                    if(monsterNameplatesEnabled && guardianNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GIANT:
                    e.getEntity().setMaxHealth(distance * giantBaseHP);
                    e.getEntity().setHealth(distance * giantBaseHP);
                    if(monsterNameplatesEnabled && giantNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GHAST:
                    e.getEntity().setMaxHealth(distance * ghastBaseHP);
                    e.getEntity().setHealth(distance * ghastBaseHP);
                    //Slow them down
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * .85);
                    if(monsterNameplatesEnabled && ghastNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDERMITE:
                    e.getEntity().setMaxHealth(distance * endermiteBaseHP);
                    e.getEntity().setHealth(distance * endermiteBaseHP);
                    if(monsterNameplatesEnabled && endermanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDERMAN:
                    e.getEntity().setMaxHealth(distance * endermanBaseHP);
                    e.getEntity().setHealth(distance * endermanBaseHP);
                    if(monsterNameplatesEnabled && endermanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDER_DRAGON:
                    distance = 250;
                    e.getEntity().setMaxHealth(distance * enderdragonBaseHP);
                    e.getEntity().setHealth(distance * enderdragonBaseHP);
                    if(monsterNameplatesEnabled && enderdragonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CREEPER:
                    e.getEntity().setMaxHealth(distance * creeperBaseHP);
                    e.getEntity().setHealth(distance * creeperBaseHP);
                    //superFastCreepers?

                    //this is paul being reasonable
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 1.4);
                    e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getValue() * 1.75);
                    if(monsterNameplatesEnabled && creeperNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case COW:
                    e.getEntity().setMaxHealth(distance * cowBaseHP);
                    e.getEntity().setHealth(distance * cowBaseHP);
                    if(monsterNameplatesEnabled && cowNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CHICKEN:
                    e.getEntity().setMaxHealth(distance * chickenBaseHP);
                    e.getEntity().setHealth(distance * chickenBaseHP);
                    if(monsterNameplatesEnabled && chickenNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CAVE_SPIDER:
                    e.getEntity().setMaxHealth(distance * cavespiderBaseHP);
                    e.getEntity().setHealth(distance * cavespiderBaseHP);
                    if(monsterNameplatesEnabled && cavespiderNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case BLAZE:
                    e.getEntity().setMaxHealth(distance * blazeBaseHP);
                    e.getEntity().setHealth(distance * blazeBaseHP);
                    if(monsterNameplatesEnabled && blazeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case WITCH:
                    e.getEntity().setMaxHealth(distance * witchBaseHP);
                    e.getEntity().setHealth(distance * witchBaseHP);
                    if(monsterNameplatesEnabled && witchNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case WITHER_SKELETON:
                    e.getEntity().setMaxHealth(distance * witherSkeletonBaseHP);
                    e.getEntity().setHealth(distance * witherSkeletonBaseHP);
                    if(monsterNameplatesEnabled && witherSkeletonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SHULKER:
                    e.getEntity().setMaxHealth(distance * shulkerSkeletonBaseHP);
                    e.getEntity().setHealth(distance * shulkerSkeletonBaseHP);
                    if(monsterNameplatesEnabled && shulkerSkeletonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PILLAGER:
                    e.getEntity().setMaxHealth(distance * pillagerBaseHP);
                    e.getEntity().setHealth(distance * pillagerBaseHP);
                    if(monsterNameplatesEnabled && pillagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ILLUSIONER:
                    e.getEntity().setMaxHealth(distance * illusionerBaseHP);
                    e.getEntity().setHealth(distance * illusionerBaseHP);
                    if(monsterNameplatesEnabled && illusionerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case EVOKER:
                    e.getEntity().setMaxHealth(distance * evokerBaseHP);
                    e.getEntity().setHealth(distance * evokerBaseHP);
                    if(monsterNameplatesEnabled && evokerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case RAVAGER:
                    e.getEntity().setMaxHealth(distance * ravagerBaseHP);
                    e.getEntity().setHealth(distance * ravagerBaseHP);
                    if(monsterNameplatesEnabled && ravagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;

                case BAT:
                    e.getEntity().setMaxHealth(distance *batBaseHP);
                    e.getEntity().setHealth(distance * batBaseHP);
                    if(monsterNameplatesEnabled && batNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case DROWNED:
                    e.getEntity().setMaxHealth(distance *drownedBaseHP);
                    e.getEntity().setHealth(distance * drownedBaseHP);
                    if(monsterNameplatesEnabled && drownedNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;



            }


        }




    }


}
