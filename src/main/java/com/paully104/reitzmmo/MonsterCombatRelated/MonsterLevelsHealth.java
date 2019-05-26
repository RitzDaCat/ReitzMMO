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
    private final int blocksPerMobLevel = API.monsterConfig.getInt("General.blocks-per-mob-level");

    private final int zombieBaseHP = API.monsterConfig.getInt("Zombie.base_hp");
    private final int wolfBaseHP = API.monsterConfig.getInt("Wolf.base_hp");
    private final int villagerBaseHP = API.monsterConfig.getInt("Villager.base_hp");
    private final int squidBaseHP = API.monsterConfig.getInt("Squid.base_hp");
    private final int spiderBaseHP = API.monsterConfig.getInt("Spider.base_hp");
    private final int snowmanBaseHP = API.monsterConfig.getInt("Snowman.base_hp");
    private final int slimeBaseHP = API.monsterConfig.getInt("Slime.base_hp");
    private final int skeletonBaseHP = API.monsterConfig.getInt("Skeleton.base_hp");
    private final int silverfishBaseHP = API.monsterConfig.getInt("Silverfish.base_hp");
    private final int sheepBaseHP = API.monsterConfig.getInt("Sheep.base_hp");
    private final int rabbitBaseHP = API.monsterConfig.getInt("Rabbit.base_hp");
    private final int pigzombieBaseHP = API.monsterConfig.getInt("Pigzombie.base_hp");
    private final int pigBaseHP = API.monsterConfig.getInt("Pig.base_hp");
    private final int mushroomcowBaseHP = API.monsterConfig.getInt("Mushroomcow.base_hp");
    private final int magmacubeBaseHP = API.monsterConfig.getInt("Magmacube.base_hp");
    private final int guardianBaseHP = API.monsterConfig.getInt("Guardian.base_hp");
    private final int giantBaseHP = API.monsterConfig.getInt("Giant.base_hp");
    private final int ghastBaseHP = API.monsterConfig.getInt("Ghast.base_hp");
    private final int endermiteBaseHP = API.monsterConfig.getInt("Endermite.base_hp");
    private final int endermanBaseHP = API.monsterConfig.getInt("Enderman.base_hp");
    private final int enderdragonBaseHP = API.monsterConfig.getInt("Enderdragon.base_hp");
    private final int creeperBaseHP = API.monsterConfig.getInt("Creeper.base_hp");
    private final int cowBaseHP = API.monsterConfig.getInt("Cow.base_hp");
    private final int chickenBaseHP = API.monsterConfig.getInt("Chicken.base_hp");
    private final int cavespiderBaseHP = API.monsterConfig.getInt("Cavespider.base_hp");
    private final int blazeBaseHP = API.monsterConfig.getInt("Blaze.base_hp");
    private final int witchBaseHP = API.monsterConfig.getInt("Witch.base_hp");
    private final int witherSkeletonBaseHP = API.monsterConfig.getInt("Witherskeleton.base_hp");
    private final int shulkerBaseHP = API.monsterConfig.getInt("Shulker.base_hp");
    private final int pillagerBaseHP = API.monsterConfig.getInt("Pillager.base_hp");
    private final int illusionerBaseHP = API.monsterConfig.getInt("Illusioner.base_hp");
    private final int evokerBaseHP = API.monsterConfig.getInt("Evoker.base_hp");
    private final int ravagerBaseHP = API.monsterConfig.getInt("Ravager.base_hp");
    private final int batBaseHP = API.monsterConfig.getInt("Bat.base_hp");
    private final int drownedBaseHP = API.monsterConfig.getInt("Drowned.base_hp");
    private final int zombievillagerBaseHP = API.monsterConfig.getInt("Zombievillager.base_hp");
    private final int polarBearBaseHP = API.monsterConfig.getInt("Polarbear.base_hp");
    private final int wanderingTraderBaseHP = API.monsterConfig.getInt("Wanderingtrader.base_hp");
    private final int donkeyBaseHP = API.monsterConfig.getInt("Donkey.base_hp");
    private final int llamaBaseHP = API.monsterConfig.getInt("Llama.base_hp");
    private final int salmonBaseHP = API.monsterConfig.getInt("Salmon.base_hp");

    //NAMEPLATE SECTION
    private final boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");

    private final boolean zombieNameplate = API.monsterConfig.getBoolean("Zombie.nameplates_enabled");
    private final boolean wolfNameplate = API.monsterConfig.getBoolean("Wolf.nameplates_enabled");
    private final boolean villagerNameplate = API.monsterConfig.getBoolean("Villager.nameplates_enabled");
    private final boolean squidNameplate = API.monsterConfig.getBoolean("Squid.nameplates_enabled");
    private final boolean spiderNameplate = API.monsterConfig.getBoolean("Spider.nameplates_enabled");
    private final boolean snowmanNameplate = API.monsterConfig.getBoolean("Snowman.nameplates_enabled");
    private final boolean slimeNameplate = API.monsterConfig.getBoolean("Slime.nameplates_enabled");
    private final boolean skeletonNameplate = API.monsterConfig.getBoolean("Skeleton.nameplates_enabled");
    private final boolean silverfishNameplate = API.monsterConfig.getBoolean("Silverfish.nameplates_enabled");
    private final boolean sheepNameplate = API.monsterConfig.getBoolean("Sheep.nameplates_enabled");
    private final boolean rabbitNameplate = API.monsterConfig.getBoolean("Rabbit.nameplates_enabled");
    private final boolean pigzombieNameplate = API.monsterConfig.getBoolean("Pigzombie.nameplates_enabled");
    private final boolean pigNameplate = API.monsterConfig.getBoolean("Pig.nameplates_enabled");
    private final boolean mushroomcowNameplate = API.monsterConfig.getBoolean("Mushroomcow.nameplates_enabled");
    private final boolean magmacubeNameplate = API.monsterConfig.getBoolean("Magmacube.nameplates_enabled");
    private final boolean guardianNameplate = API.monsterConfig.getBoolean("Guardian.nameplates_enabled");
    private final boolean giantNameplate = API.monsterConfig.getBoolean("Giant.nameplates_enabled");
    private final boolean ghastNameplate = API.monsterConfig.getBoolean("Ghast.nameplates_enabled");
    private final boolean endermiteNameplate = API.monsterConfig.getBoolean("Endermite.nameplates_enabled");
    private final boolean endermanNameplate = API.monsterConfig.getBoolean("Enderman.nameplates_enabled");
    private final boolean enderdragonNameplate = API.monsterConfig.getBoolean("Enderdragon.nameplates_enabled");
    private final boolean creeperNameplate = API.monsterConfig.getBoolean("Creeper.nameplates_enabled");
    private final boolean cowNameplate = API.monsterConfig.getBoolean("Cow.nameplates_enabled");
    private final boolean chickenNameplate = API.monsterConfig.getBoolean("Chicken.nameplates_enabled");
    private final boolean cavespiderNameplate = API.monsterConfig.getBoolean("Cavespider.nameplates_enabled");
    private final boolean blazeNameplate = API.monsterConfig.getBoolean("Blaze.nameplates_enabled");
    private final boolean witchNameplate = API.monsterConfig.getBoolean("Witch.nameplates_enabled");
    private final boolean witherSkeletonNameplate = API.monsterConfig.getBoolean("Witherskeleton.nameplates_enabled");
    private final boolean shulkerNameplate = API.monsterConfig.getBoolean("Shulker.nameplates_enabled");
    private final boolean pillagerNameplate = API.monsterConfig.getBoolean("Pillager.nameplates_enabled");
    private final boolean illusionerNameplate = API.monsterConfig.getBoolean("Illusioner.nameplates_enabled");
    private final boolean evokerNameplate = API.monsterConfig.getBoolean("Evoker.nameplates_enabled");
    private final boolean ravagerNameplate = API.monsterConfig.getBoolean("Ravager.nameplates_enabled");
    private final boolean batNameplate = API.monsterConfig.getBoolean("Bat.nameplates_enabled");
    private final boolean drownedNameplate = API.monsterConfig.getBoolean("Drowned.nameplates_enabled");
    private final boolean zombievillagerNameplate = API.monsterConfig.getBoolean("Zombievillager.nameplates_enabled");
    private final boolean polarBearNameplate = API.monsterConfig.getBoolean("Polarbear.nameplates_enabled");
    private final boolean wanderingTraderNameplate = API.monsterConfig.getBoolean("Wanderingtrader.nameplates_enabled");
    private final boolean donkeyNameplate = API.monsterConfig.getBoolean("Donkey.nameplates_enabled");
    private final boolean llamaNameplate = API.monsterConfig.getBoolean("Llama.nameplates_enabled");
    private final boolean salmonNameplate = API.monsterConfig.getBoolean("Salmon.nameplates_enabled");

    //Monster SPEED SECTION
    private final int zombieSpeed = API.monsterConfig.getInt("Zombie.speed");
    private final int wolfSpeed = API.monsterConfig.getInt("Wolf.speed");
    private final int villagerSpeed = API.monsterConfig.getInt("Villager.speed");
    private final int squidSpeed = API.monsterConfig.getInt("Squid.speed");
    private final int spiderSpeed = API.monsterConfig.getInt("Spider.speed");
    private final int snowmanSpeed = API.monsterConfig.getInt("Snowman.speed");
    private final int slimeSpeed = API.monsterConfig.getInt("Slime.speed");
    private final int skeletonSpeed = API.monsterConfig.getInt("Skeleton.speed");
    private final int silverfishSpeed = API.monsterConfig.getInt("Silverfish.speed");
    private final int sheepSpeed = API.monsterConfig.getInt("Sheep.speed");
    private final int rabbitSpeed = API.monsterConfig.getInt("Rabbit.speed");
    private final int pigzombieSpeed = API.monsterConfig.getInt("Pigzombie.speed");
    private final int pigSpeed = API.monsterConfig.getInt("Pig.speed");
    private final int mushroomcowSpeed = API.monsterConfig.getInt("Mushroomcow.speed");
    private final int magmacubeSpeed = API.monsterConfig.getInt("Magmacube.speed");
    private final int guardianSpeed = API.monsterConfig.getInt("Guardian.speed");
    private final int giantSpeed = API.monsterConfig.getInt("Giant.speed");
    private final int ghastSpeed = API.monsterConfig.getInt("Ghast.speed");
    private final int endermiteSpeed = API.monsterConfig.getInt("Endermite.speed");
    private final int endermanSpeed = API.monsterConfig.getInt("Enderman.speed");
    private final int enderdragonSpeed = API.monsterConfig.getInt("Enderdragon.speed");
    private final int creeperSpeed = API.monsterConfig.getInt("Creeper.speed");
    private final int cowSpeed = API.monsterConfig.getInt("Cow.speed");
    private final int chickenSpeed = API.monsterConfig.getInt("Chicken.speed");
    private final int cavespiderSpeed = API.monsterConfig.getInt("Cavespider.speed");
    private final int blazeSpeed = API.monsterConfig.getInt("Blaze.speed");
    private final int witchSpeed = API.monsterConfig.getInt("Witch.speed");
    private final int witherSkeletonSpeed = API.monsterConfig.getInt("Witherskeleton.speed");
    private final int shulkerSpeed = API.monsterConfig.getInt("Shulker.speed");
    private final int pillagerSpeed = API.monsterConfig.getInt("Pillager.speed");
    private final int illusionerSpeed = API.monsterConfig.getInt("Illusioner.speed");
    private final int evokerSpeed = API.monsterConfig.getInt("Evoker.speed");
    private final int ravagerSpeed = API.monsterConfig.getInt("Ravager.speed");
    private final int batSpeed = API.monsterConfig.getInt("Bat.speed");
    private final int drownedSpeed = API.monsterConfig.getInt("Drowned.speed");
    private final int zombievillagerSpeed = API.monsterConfig.getInt("Zombievillager.speed");
    private final int polarBearSpeed = API.monsterConfig.getInt("Polarbear.speed");
    private final int wanderingTraderSpeed = API.monsterConfig.getInt("Wanderingtrader.speed");
    private final int donkeySpeed = API.monsterConfig.getInt("Donkey.speed");
    private final int llamaSpeed = API.monsterConfig.getInt("Llama.speed");
    private final int salmonSpeed = API.monsterConfig.getInt("Salmon.speed");

    //Monster MINIMUM SECTION
    private final int zombieMinLevel = API.monsterConfig.getInt("Zombie.min_level");
    private final int wolfMinLevel = API.monsterConfig.getInt("Wolf.min_level");
    private final int villagerMinLevel = API.monsterConfig.getInt("Villager.min_level");
    private final int squidMinLevel = API.monsterConfig.getInt("Squid.min_level");
    private final int spiderMinLevel = API.monsterConfig.getInt("Spider.min_level");
    private final int snowmanMinLevel = API.monsterConfig.getInt("Snowman.min_level");
    private final int slimeMinLevel = API.monsterConfig.getInt("Slime.min_level");
    private final int skeletonMinLevel = API.monsterConfig.getInt("Skeleton.min_level");
    private final int silverfishMinLevel = API.monsterConfig.getInt("Silverfish.min_level");
    private final int sheepMinLevel = API.monsterConfig.getInt("Sheep.min_level");
    private final int rabbitMinLevel = API.monsterConfig.getInt("Rabbit.min_level");
    private final int pigzombieMinLevel = API.monsterConfig.getInt("Pigzombie.min_level");
    private final int pigMinLevel = API.monsterConfig.getInt("Pig.min_level");
    private final int mushroomcowMinLevel = API.monsterConfig.getInt("Mushroomcow.min_level");
    private final int magmacubeMinLevel = API.monsterConfig.getInt("Magmacube.min_level");
    private final int guardianMinLevel = API.monsterConfig.getInt("Guardian.min_level");
    private final int giantMinLevel = API.monsterConfig.getInt("Giant.min_level");
    private final int ghastMinLevel = API.monsterConfig.getInt("Ghast.min_level");
    private final int endermiteMinLevel = API.monsterConfig.getInt("Endermite.min_leveL");
    private final int endermanMinLevel = API.monsterConfig.getInt("Enderman.min_level");
    private final int enderdragonMinLevel = API.monsterConfig.getInt("Enderdragon.min_level");
    private final int creeperMinLevel = API.monsterConfig.getInt("Creeper.min_level");
    private final int cowMinLevel = API.monsterConfig.getInt("Cow.min_level");
    private final int chickenMinLevel = API.monsterConfig.getInt("Chicken.min_level");
    private final int cavespiderMinLevel = API.monsterConfig.getInt("Cavespider.min_level");
    private final int blazeMinLevel = API.monsterConfig.getInt("Blaze.min_level");
    private final int witchMinLevel = API.monsterConfig.getInt("Witch.min_leveL");
    private final int witherSkeletonMinLevel = API.monsterConfig.getInt("Witherskeleton.min_level");
    private final int shulkerMinLevel = API.monsterConfig.getInt("Shulker.min_level");
    private final int pillagerMinLevel = API.monsterConfig.getInt("Pillager.min_level");
    private final int illusionerMinLevel = API.monsterConfig.getInt("Illusioner.min_level");
    private final int evokerMinLevel = API.monsterConfig.getInt("Evoker.min_level");
    private final int ravagerMinLevel = API.monsterConfig.getInt("Ravager.min_level");
    private final int batMinLevel = API.monsterConfig.getInt("Bat.min_level");
    private final int drownedMinLevel = API.monsterConfig.getInt("Drowned.min_level");
    private final int zombievillagerMinLevel = API.monsterConfig.getInt("Zombievillager.min_level");
    private final int polarBearMinLevel = API.monsterConfig.getInt("Polarbear.min_level");
    private final int wanderingTraderMinLevel = API.monsterConfig.getInt("Wanderingtrader.min_level");
    private final int donkeyMinLevel = API.monsterConfig.getInt("Donkey.min_level");
    private final int llamaMinLevel = API.monsterConfig.getInt("Llama.min_level");
    private final int salmonMinLevel = API.monsterConfig.getInt("Salmon.min_level");


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
                            distance = zombieMinLevel;
                        }
                        e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                        e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                            distance = zombieMinLevel;
                        }
                        e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                                e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = wolfMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = villagerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = squidMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = spiderMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = snowmanMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        };
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
                        distance = slimeMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = skeletonMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = silverfishMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = sheepMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = rabbitMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = pigzombieMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = pigMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = mushroomcowMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = magmacubeMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = guardianMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = guardianMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = guardianMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = ghastMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = endermiteMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * endermiteSpeed);
                    if(monsterNameplatesEnabled && endermiteNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ENDERMAN:
                    hp = distance * endermanBaseHP;
                    if(hp < endermanMinLevel * endermanBaseHP)
                    {
                        hp = endermanMinLevel * endermanBaseHP;
                        distance = endermanMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        if(e.getEntity().getCustomName() != null)
                        {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = enderdragonMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                        if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * enderdragonSpeed);
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
                        distance = creeperMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = cowMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = chickenMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = cavespiderMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * cavespiderSpeed);
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
                        distance = blazeMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = witchMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = witherSkeletonMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = shulkerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = pillagerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = illusionerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = evokerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = ravagerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = batMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                                            e.getEntity().setHealth(hp);
                        levelColor = ChatColor.YELLOW + "[" + distance + "]";
                                                if(e.getEntity().getCustomName() == null) {
                            e.getEntity().setCustomName(mobName + levelColor);
                        }
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
                        distance = drownedMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * drownedSpeed);
                    if(monsterNameplatesEnabled && drownedNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case ZOMBIE_VILLAGER:
                    hp = distance * zombievillagerBaseHP;
                    if(hp < zombievillagerMinLevel * zombievillagerBaseHP)
                    {
                        hp = zombievillagerMinLevel * zombievillagerBaseHP;
                        distance = zombievillagerMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * zombievillagerSpeed);
                    if(monsterNameplatesEnabled && zombievillagerNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case POLAR_BEAR:
                    hp = distance * polarBearBaseHP;
                    if(hp < polarBearMinLevel * polarBearBaseHP)
                    {
                        hp = polarBearMinLevel * polarBearBaseHP;
                        distance = polarBearMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * polarBearSpeed);
                    if(monsterNameplatesEnabled && polarBearNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case WANDERING_TRADER:
                    hp = distance * wanderingTraderBaseHP;
                    if(hp < wanderingTraderMinLevel * wanderingTraderBaseHP)
                    {
                        hp = wanderingTraderMinLevel * wanderingTraderBaseHP;
                        distance = wanderingTraderMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * wanderingTraderSpeed);
                    if(monsterNameplatesEnabled && wanderingTraderNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case DONKEY:
                    hp = distance * donkeyBaseHP;
                    if(hp < donkeyMinLevel * donkeyBaseHP)
                    {
                        hp = donkeyMinLevel * donkeyBaseHP;
                        distance = donkeyMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * donkeySpeed);
                    if(monsterNameplatesEnabled && donkeyNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case LLAMA:
                    hp = distance * llamaBaseHP;
                    if(hp < llamaMinLevel * llamaBaseHP)
                    {
                        hp = llamaMinLevel * llamaBaseHP;
                        distance = llamaMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * llamaSpeed);
                    if(monsterNameplatesEnabled && llamaNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;
                case SALMON:
                    hp = distance * salmonBaseHP;
                    if(hp < salmonMinLevel * salmonBaseHP)
                    {
                        hp = salmonMinLevel * salmonBaseHP;
                        distance = salmonMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if(e.getEntity().getCustomName() == null) {
                        e.getEntity().setCustomName(mobName + levelColor);
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * salmonSpeed);
                    if(monsterNameplatesEnabled && salmonNameplate)
                    {
                        e.getEntity().setCustomNameVisible(true);
                    }
                    break;



            }


        }




    }


}
