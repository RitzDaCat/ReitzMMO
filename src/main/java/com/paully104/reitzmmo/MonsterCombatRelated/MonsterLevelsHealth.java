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
    private final int shulkerBaseHP = API.monsterConfig.getInt("SHULKER_BASE_HP");
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
    private final boolean shulkerNameplate = API.monsterConfig.getBoolean("SHULKER_NAMEPLATES_ENABLED");
    private final boolean pillagerNameplate = API.monsterConfig.getBoolean("PILLAGER_NAMEPLATES_ENABLED");
    private final boolean illusionerNameplate = API.monsterConfig.getBoolean("ILLUSIONER_NAMEPLATES_ENABLED");
    private final boolean evokerNameplate = API.monsterConfig.getBoolean("EVOKER_NAMEPLATES_ENABLED");
    private final boolean ravagerNameplate = API.monsterConfig.getBoolean("RAVAGER_NAMEPLATES_ENABLED");
    private final boolean batNameplate = API.monsterConfig.getBoolean("BAT_NAMEPLATES_ENABLED");
    private final boolean drownedNameplate = API.monsterConfig.getBoolean("DROWNED_NAMEPLATES_ENABLED");
    
    //Monster SPEED SECTION
    private final int zombieSpeed = API.monsterConfig.getInt("ZOMBIE_SPEED");
    private final int wolfSpeed = API.monsterConfig.getInt("WOLF_SPEED");
    private final int villagerSpeed = API.monsterConfig.getInt("VILLAGER_SPEED");
    private final int squidSpeed = API.monsterConfig.getInt("SQUID_SPEED");
    private final int spiderSpeed = API.monsterConfig.getInt("SPIDER_SPEED");
    private final int snowmanSpeed = API.monsterConfig.getInt("SNOWMAN_SPEED");
    private final int slimeSpeed = API.monsterConfig.getInt("SLIME_SPEED");
    private final int skeletonSpeed = API.monsterConfig.getInt("SKELETON_SPEED");
    private final int silverfishSpeed = API.monsterConfig.getInt("SILVERFISH_SPEED");
    private final int sheepSpeed = API.monsterConfig.getInt("SHEEP_SPEED");
    private final int rabbitSpeed = API.monsterConfig.getInt("RABBIT_SPEED");
    private final int pigzombieSpeed = API.monsterConfig.getInt("PIGZOMBIE_SPEED");
    private final int pigSpeed = API.monsterConfig.getInt("PIG_SPEED");
    private final int mushroomcowSpeed = API.monsterConfig.getInt("MUSHROOMCOW_SPEED");
    private final int magmacubeSpeed = API.monsterConfig.getInt("MAGMACUBE_SPEED");
    private final int guardianSpeed = API.monsterConfig.getInt("GUARDIAN_SPEED");
    private final int giantSpeed = API.monsterConfig.getInt("GIANT_SPEED");
    private final int ghastSpeed = API.monsterConfig.getInt("GHAST_SPEED");
    private final int endermiteSpeed = API.monsterConfig.getInt("ENDERMITE_SPEED");
    private final int endermanSpeed = API.monsterConfig.getInt("ENDERMAN_SPEED");
    private final int enderdragonSpeed = API.monsterConfig.getInt("ENDERDRAGON_SPEED");
    private final int creeperSpeed = API.monsterConfig.getInt("CREEPER_SPEED");
    private final int cowSpeed = API.monsterConfig.getInt("COW_SPEED");
    private final int chickenSpeed = API.monsterConfig.getInt("CHICKEN_SPEED");
    private final int cavespiderSpeed = API.monsterConfig.getInt("CAVESPIDER_SPEED");
    private final int blazeSpeed = API.monsterConfig.getInt("BLAZE_SPEED");
    private final int witchSpeed = API.monsterConfig.getInt("WITCH_SPEED");
    private final int witherSkeletonSpeed = API.monsterConfig.getInt("WITHERSKELETON_SPEED");
    private final int shulkerSpeed = API.monsterConfig.getInt("SHULKER_SPEED");
    private final int pillagerSpeed = API.monsterConfig.getInt("PILLAGER_SPEED");
    private final int illusionerSpeed = API.monsterConfig.getInt("ILLUSIONER_SPEED");
    private final int evokerSpeed = API.monsterConfig.getInt("EVOKER_SPEED");
    private final int ravagerSpeed = API.monsterConfig.getInt("RAVAGER_SPEED");
    private final int batSpeed = API.monsterConfig.getInt("BAT_SPEED");
    private final int drownedSpeed = API.monsterConfig.getInt("DROWNED_SPEED");
    
    //Monster MINIMUM SECTION
    private final int zombieMinLevel = API.monsterConfig.getInt("ZOMBIE_MIN_LEVEL");
    private final int wolfMinLevel = API.monsterConfig.getInt("WOLF_MIN_LEVEL");
    private final int villagerMinLevel = API.monsterConfig.getInt("VILLAGER_MIN_LEVEL");
    private final int squidMinLevel = API.monsterConfig.getInt("SQUID_MIN_LEVEL");
    private final int spiderMinLevel = API.monsterConfig.getInt("SPIDER_MIN_LEVEL");
    private final int snowmanMinLevel = API.monsterConfig.getInt("SNOWMAN_MIN_LEVEL");
    private final int slimeMinLevel = API.monsterConfig.getInt("SLIME_MIN_LEVEL");
    private final int skeletonMinLevel = API.monsterConfig.getInt("SKELETON_MIN_LEVEL");
    private final int silverfishMinLevel = API.monsterConfig.getInt("SILVERFISH_MIN_LEVEL");
    private final int sheepMinLevel = API.monsterConfig.getInt("SHEEP_MIN_LEVEL");
    private final int rabbitMinLevel = API.monsterConfig.getInt("RABBIT_MIN_LEVEL");
    private final int pigzombieMinLevel = API.monsterConfig.getInt("PIGZOMBIE_MIN_LEVEL");
    private final int pigMinLevel = API.monsterConfig.getInt("PIG_MIN_LEVEL");
    private final int mushroomcowMinLevel = API.monsterConfig.getInt("MUSHROOMCOW_MIN_LEVEL");
    private final int magmacubeMinLevel = API.monsterConfig.getInt("MAGMACUBE_MIN_LEVEL");
    private final int guardianMinLevel = API.monsterConfig.getInt("GUARDIAN_MIN_LEVEL");
    private final int giantMinLevel = API.monsterConfig.getInt("GIANT_MIN_LEVEL");
    private final int ghastMinLevel = API.monsterConfig.getInt("GHAST_MIN_LEVEL");
    private final int endermiteMinLevel = API.monsterConfig.getInt("ENDERMITE_MIN_LEVEL");
    private final int endermanMinLevel = API.monsterConfig.getInt("ENDERMAN_MIN_LEVEL");
    private final int enderdragonMinLevel = API.monsterConfig.getInt("ENDERDRAGON_MIN_LEVEL");
    private final int creeperMinLevel = API.monsterConfig.getInt("CREEPER_MIN_LEVEL");
    private final int cowMinLevel = API.monsterConfig.getInt("COW_MIN_LEVEL");
    private final int chickenMinLevel = API.monsterConfig.getInt("CHICKEN_MIN_LEVEL");
    private final int cavespiderMinLevel = API.monsterConfig.getInt("CAVESPIDER_MIN_LEVEL");
    private final int blazeMinLevel = API.monsterConfig.getInt("BLAZE_MIN_LEVEL");
    private final int witchMinLevel = API.monsterConfig.getInt("WITCH_MIN_LEVEL");
    private final int witherSkeletonMinLevel = API.monsterConfig.getInt("WITHERSKELETON_MIN_LEVEL");
    private final int shulkerMinLevel = API.monsterConfig.getInt("SHULKER_MIN_LEVEL");
    private final int pillagerMinLevel = API.monsterConfig.getInt("PILLAGER_MIN_LEVEL");
    private final int illusionerMinLevel = API.monsterConfig.getInt("ILLUSIONER_MIN_LEVEL");
    private final int evokerMinLevel = API.monsterConfig.getInt("EVOKER_MIN_LEVEL");
    private final int ravagerMinLevel = API.monsterConfig.getInt("RAVAGER_MIN_LEVEL");
    private final int batMinLevel = API.monsterConfig.getInt("BAT_MIN_LEVEL");
    private final int drownedMinLevel = API.monsterConfig.getInt("DROWNED_MIN_LEVEL");

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
        //variables
        String levelColor; //used for nameplate creation
        int hp; //mobs hp


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
                levelColor = ChatColor.YELLOW + "[" + distance + "]";
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
                levelColor = ChatColor.YELLOW + "[" + distance + "]";
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
                levelColor = ChatColor.YELLOW + "[" + distance + "]";
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
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    e.getEntity().setCustomName("Dumb " + mobName + levelColor);
                    if(specialMobGlowingEnabled) {
                        e.getEntity().setGlowing(true);
                    }
                    if(specialMobSilentEnabled){
                        e.getEntity().setSilent(true);
                    }
                    e.getEntity().getEquipment().setHelmet(new ItemStack(Material.BUCKET, 1));
            }

            //updated on 5/7 for bad boys

            //configure health per mob


            switch (e.getEntity().getType()) {
                case ZOMBIE:
                    Zombie zombie = (Zombie) e.getEntity();
                    if (zombie.isBaby()) {
                        hp = distance * zombieBaseHP;
                        if(hp < zombieMinLevel * zombieBaseHP)
                        {
                            hp = zombieMinLevel * zombieBaseHP;
                        }
                        e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                                e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * zombieSpeed);
                        //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                        //updated on 4/26/2017 to increase the chance of getting tons of zombies :)

                        if(monsterNameplatesEnabled && zombieNameplate) {


                            e.getEntity().setCustomNameVisible(true);
                        }
                        
                        
                    } else {
                        hp = distance * zombieBaseHP;
                        if(hp < zombieMinLevel * zombieBaseHP)
                        {
                            hp = zombieMinLevel * zombieBaseHP;
                        }
                        e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                                e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                        //Update onm 4/26/2017 To slow
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * zombieSpeed);
                        //Updated on 4/26/2017 to increase follow_range to 2.0 from 1.25
                        //updated on 4/26/2017 to increase the chance of getting tons of zombies :)

                        if(monsterNameplatesEnabled && zombieNameplate) {


                            e.getEntity().setCustomNameVisible(true);
                        }

                    }
                    break;
                case WOLF:
                    hp = distance * wolfBaseHP;
                    if(hp < wolfMinLevel * wolfBaseHP)
                    {
                        hp = wolfMinLevel * wolfBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * wolfSpeed);
                    if(monsterNameplatesEnabled && wolfNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;

                case VILLAGER:
                    hp = distance * villagerBaseHP;
                    if(hp < villagerMinLevel * villagerBaseHP)
                    {
                        hp = villagerMinLevel * villagerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * villagerSpeed);
                    if(monsterNameplatesEnabled && villagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SQUID:
                    hp = distance * squidBaseHP;
                    if(hp < squidMinLevel * squidBaseHP)
                    {
                        hp = squidMinLevel * squidBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * squidSpeed);
                    if(monsterNameplatesEnabled && squidNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SPIDER:
                    hp = distance * spiderBaseHP;
                    if(hp < spiderMinLevel * spiderBaseHP)
                    {
                        hp = spiderMinLevel * spiderBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * spiderSpeed);
                    if(monsterNameplatesEnabled && spiderNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SNOWMAN:
                    hp = distance * snowmanBaseHP;
                    if(hp < snowmanMinLevel * snowmanBaseHP)
                    {
                        hp = snowmanMinLevel * snowmanBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);;
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * snowmanSpeed);
                    if(monsterNameplatesEnabled && snowmanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SLIME:
                    hp = distance * slimeBaseHP;
                    if(hp < slimeMinLevel * slimeBaseHP)
                    {
                        hp = slimeMinLevel * slimeBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * slimeSpeed);
                    if(monsterNameplatesEnabled && slimeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SKELETON:
                    hp = distance * skeletonBaseHP;
                    if(hp < skeletonMinLevel * skeletonBaseHP)
                    {
                        hp = skeletonMinLevel * skeletonBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    //slower Skellies because they suck
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * skeletonSpeed);
                    if(monsterNameplatesEnabled && skeletonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    //Lets slow down the skeleton firing rate to make it more fair UPDATE: Not an accessible trait!
                    break;
                case SILVERFISH:
                    hp = distance * silverfishBaseHP;
                    if(hp < silverfishMinLevel * silverfishBaseHP)
                    {
                        hp = silverfishMinLevel * silverfishBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * silverfishSpeed);
                    if(monsterNameplatesEnabled && silverfishNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SHEEP:
                    hp = distance * sheepBaseHP;
                    if(hp < sheepMinLevel * sheepBaseHP)
                    {
                        hp = sheepMinLevel * sheepBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * sheepSpeed);
                    if(monsterNameplatesEnabled && sheepNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case RABBIT:
                    hp = distance * rabbitBaseHP;
                    if(hp < rabbitMinLevel * rabbitBaseHP)
                    {
                        hp = rabbitMinLevel * rabbitBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * rabbitSpeed);
                    if(monsterNameplatesEnabled && rabbitNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PIG_ZOMBIE:
                    hp = distance * pigzombieBaseHP;
                    if(hp < pigzombieMinLevel * pigzombieBaseHP)
                    {
                        hp = pigzombieMinLevel * pigzombieBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    //superfast
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * pigzombieSpeed);
                    if(monsterNameplatesEnabled && pigzombieNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PIG:
                    hp = distance * pigBaseHP;
                    if(hp < pigMinLevel * pigBaseHP)
                    {
                        hp = pigMinLevel * pigBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * pigSpeed);
                    if(monsterNameplatesEnabled && pigNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case MUSHROOM_COW:
                    hp = distance * mushroomcowBaseHP;
                    if(hp < mushroomcowMinLevel * mushroomcowBaseHP)
                    {
                        hp = mushroomcowMinLevel * mushroomcowBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * mushroomcowSpeed);
                    if(monsterNameplatesEnabled && mushroomcowNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case MAGMA_CUBE:
                    hp = distance * magmacubeBaseHP;
                    if(hp < magmacubeMinLevel * magmacubeBaseHP)
                    {
                        hp = magmacubeMinLevel * magmacubeBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * magmacubeSpeed);
                    if(monsterNameplatesEnabled && magmacubeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GUARDIAN:
                    hp = distance * guardianBaseHP;
                    if(hp < guardianMinLevel * guardianBaseHP)
                    {
                        hp = guardianMinLevel * guardianBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * guardianSpeed);
                    if(monsterNameplatesEnabled && guardianNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ELDER_GUARDIAN:
                    hp = distance * guardianBaseHP;
                    if(hp < guardianMinLevel * guardianBaseHP)
                    {
                        hp = guardianMinLevel * guardianBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * guardianSpeed);
                    if(monsterNameplatesEnabled && guardianNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GIANT:
                    hp = distance * giantBaseHP;
                    if(hp < giantMinLevel * giantBaseHP)
                    {
                        hp = giantMinLevel * giantBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * giantSpeed);
                    if(monsterNameplatesEnabled && giantNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case GHAST:
                    hp = distance * ghastBaseHP;
                    if(hp < ghastMinLevel * ghastBaseHP)
                    {
                        hp = ghastMinLevel * ghastBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * ghastSpeed);
                    //Slow them down
                    if(monsterNameplatesEnabled && ghastNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDERMITE:
                    hp = distance * endermiteBaseHP;
                    if(hp < endermiteMinLevel * endermiteBaseHP)
                    {
                        hp = endermiteMinLevel * endermiteBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * endermiteSpeed);
                    if(monsterNameplatesEnabled && endermanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDERMAN:
                    hp = distance * endermanBaseHP;
                    if(hp < endermanMinLevel * endermanBaseHP)
                    {
                        hp = endermanMinLevel * endermanBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * endermanSpeed);
                    if(monsterNameplatesEnabled && endermanNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDER_DRAGON:
                    hp = distance * enderdragonBaseHP;
                    if(hp < enderdragonMinLevel * enderdragonBaseHP)
                    {
                        hp = enderdragonMinLevel * enderdragonBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * enderdragonBaseHP);
                    if(monsterNameplatesEnabled && enderdragonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CREEPER:
                    hp = distance * creeperBaseHP;
                    if(hp < creeperMinLevel * creeperBaseHP)
                    {
                        hp = creeperMinLevel * creeperBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * creeperSpeed);
                    //superFastCreepers?

                    //this is paul being reasonable
                    if(monsterNameplatesEnabled && creeperNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case COW:
                    hp = distance * cowBaseHP;
                    if(hp < cowMinLevel * cowBaseHP)
                    {
                        hp = cowMinLevel * cowBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * cowSpeed);
                    if(monsterNameplatesEnabled && cowNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CHICKEN:
                    hp = distance * chickenBaseHP;
                    if(hp < chickenMinLevel * chickenBaseHP)
                    {
                        hp = chickenMinLevel * chickenBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * chickenSpeed);
                    if(monsterNameplatesEnabled && chickenNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case CAVE_SPIDER:
                    hp = distance * cavespiderBaseHP;
                    if(hp < cavespiderMinLevel * cavespiderBaseHP)
                    {
                        hp = cavespiderMinLevel * cavespiderBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * cavespiderBaseHP);
                    if(monsterNameplatesEnabled && cavespiderNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case BLAZE:
                    hp = distance * blazeBaseHP;
                    if(hp < blazeMinLevel * blazeBaseHP)
                    {
                        hp = blazeMinLevel * blazeBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * blazeSpeed);
                    if(monsterNameplatesEnabled && blazeNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case WITCH:
                    hp = distance * witchBaseHP;
                    if(hp < witchMinLevel * witchBaseHP)
                    {
                        hp = witchMinLevel * witchBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * witchSpeed);
                    if(monsterNameplatesEnabled && witchNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case WITHER_SKELETON:
                    hp = distance * witherSkeletonBaseHP;
                    if(hp < witherSkeletonMinLevel * witherSkeletonBaseHP)
                    {
                        hp = witherSkeletonMinLevel * witherSkeletonBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * witherSkeletonSpeed);
                    if(monsterNameplatesEnabled && witherSkeletonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SHULKER:
                    hp = distance * shulkerBaseHP;
                    if(hp < shulkerMinLevel * shulkerBaseHP)
                    {
                        hp = shulkerMinLevel * shulkerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * shulkerSpeed);
                    if(monsterNameplatesEnabled && shulkerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case PILLAGER:
                    hp = distance * pillagerBaseHP;
                    if(hp < pillagerMinLevel * pillagerBaseHP)
                    {
                        hp = pillagerMinLevel * pillagerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * pillagerSpeed);
                    if(monsterNameplatesEnabled && pillagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ILLUSIONER:
                    hp = distance * illusionerBaseHP;
                    if(hp < illusionerMinLevel * illusionerBaseHP)
                    {
                        hp = illusionerMinLevel * illusionerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * illusionerSpeed);
                    if(monsterNameplatesEnabled && illusionerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case EVOKER:
                    hp = distance * evokerBaseHP;
                    if(hp < evokerMinLevel * evokerBaseHP)
                    {
                        hp = evokerMinLevel * evokerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * evokerSpeed);
                    if(monsterNameplatesEnabled && evokerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case RAVAGER:
                    hp = distance * ravagerBaseHP;
                    if(hp < ravagerMinLevel * ravagerBaseHP)
                    {
                        hp = ravagerMinLevel * ravagerBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * ravagerSpeed);
                    if(monsterNameplatesEnabled && ravagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;

                case BAT:
                    hp = distance * batBaseHP;
                    if(hp < batMinLevel * batBaseHP)
                    {
                        hp = batMinLevel * batBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * batSpeed);
                    if(monsterNameplatesEnabled && batNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case DROWNED:
                    hp = distance * drownedBaseHP;
                    if(hp < drownedMinLevel * drownedBaseHP)
                    {
                        hp = drownedMinLevel * drownedBaseHP;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * drownedSpeed);
                    if(monsterNameplatesEnabled && drownedNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;



            }


        }




    }


}
