package io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated;


import java.util.Objects;
import java.util.Random;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class MonsterLevelsHealth implements Listener {
    private final boolean kingMobsEnabled = API.specialMonsterConfig.getBoolean("kingMobsEnabled");

    private final boolean notoriousMobsEnabled = API.specialMonsterConfig.getBoolean("notoriusMobsEnabled");

    private final boolean devilishMobsEnabled = API.specialMonsterConfig.getBoolean("devilishMobsEnabled");

    private final boolean dumbMobsEnabled = API.specialMonsterConfig.getBoolean("dumbMobsEnabled");

    private final int kingMobsLV = API.specialMonsterConfig.getInt("kingMobsLVDifference");

    private final int notoriousMobsLV = API.specialMonsterConfig.getInt("notoriousMobsLVDifference");

    private final int devilishMobsLV = API.specialMonsterConfig.getInt("devilishMobsLVDifference");

    private final int dumbMobsLV = API.specialMonsterConfig.getInt("dumbMobsLVDifference");

    private final boolean specialMobGlowingEnabled = API.specialMonsterConfig.getBoolean("specialMonsterGlowEnabled");

    private final boolean specialMobSilentEnabled = API.specialMonsterConfig.getBoolean("specialMonsterSilentEnabled");

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

    private final int huskBaseHP = API.monsterConfig.getInt("Husk.base_hp");

    private final int vindicatorBaseHP = API.monsterConfig.getInt("Vindicator.base_hp");

    private final int vexBaseHP = API.monsterConfig.getInt("Vex.base_hp");

    private final int foxBaseHP = API.monsterConfig.getInt("Fox.base_hp");

    private final int pandaBaseHP = API.monsterConfig.getInt("Panda.base_hp");

    private final int beeBaseHP = API.monsterConfig.getInt("Bees.base_hp");

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

    private final boolean huskNameplate = API.monsterConfig.getBoolean("Husk.nameplates_enabled");

    private final boolean vindicatorNameplate = API.monsterConfig.getBoolean("Vindicator.nameplates_enabled");

    private final boolean vexNameplate = API.monsterConfig.getBoolean("Vex.nameplates_enabled");

    private final boolean foxNameplate = API.monsterConfig.getBoolean("Fox.nameplates_enabled");

    private final boolean pandaNameplate = API.monsterConfig.getBoolean("Panda.nameplates_enabled");

    private final boolean beeNameplate = API.monsterConfig.getBoolean("Bee.nameplates_enabled");

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

    private final int huskSpeed = API.monsterConfig.getInt("Husk.speed");

    private final int vindicatorSpeed = API.monsterConfig.getInt("Vindicator.speed");

    private final int vexSpeed = API.monsterConfig.getInt("Vex.speed");

    private final int foxSpeed = API.monsterConfig.getInt("Fox.speed");

    private final int pandaSpeed = API.monsterConfig.getInt("Panda.speed");

    private final int beeSpeed = API.monsterConfig.getInt("Bee.speed");

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

    private final int huskMinLevel = API.monsterConfig.getInt("Husk.min_level");

    private final int vindicatorMinLevel = API.monsterConfig.getInt("Vindicator.min_level");

    private final int vexMinLevel = API.monsterConfig.getInt("Vex.min_level");

    private final int foxMinLevel = API.monsterConfig.getInt("Fox.min_level");

    private final int pandaMinLevel = API.monsterConfig.getInt("Panda.min_level");

    private final int beeMinLevel = API.monsterConfig.getInt("Bee.min_level");

    private int calculateDistanceFromSpawn(Location worldSpawn, Location monsterSpawn) {
        float deltaX = (float)(worldSpawn.getX() - monsterSpawn.getX());
        float deltaY = (float)(worldSpawn.getY() - monsterSpawn.getY());
        float deltaZ = (float)(worldSpawn.getZ() - monsterSpawn.getZ());
        float distance = (float)Math.sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ));
        String worldName = Objects.requireNonNull(monsterSpawn.getWorld()).getName();
        int distance2 = Math.round(distance) / this.blocksPerMobLevel;
        int baseWorldDamage = API.worldConfig.getInt(worldName);
        distance2 += baseWorldDamage;
        if (distance2 < 1)
            distance2 = 1;
        return distance2;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void applyMonsterLevelOnSpawn(CreatureSpawnEvent e) {
        int worldEnabled = API.worldConfig.getInt(Objects.<World>requireNonNull(e.getLocation().getWorld()).getName());
        if (worldEnabled != -1) {
            String levelColor;
            int hp;
            Zombie zombie;
            Location worldSpawn = e.getLocation().getWorld().getSpawnLocation();
            Location monsterSpawn = e.getLocation();
            int distance = calculateDistanceFromSpawn(worldSpawn, monsterSpawn);
            Random r = new Random();
            int low = 0;
            int high = 100;
            int result = r.nextInt(high - low) + low;
            String mobName = e.getEntityType().toString().substring(0, 1).toUpperCase() + e.getEntityType().toString().toLowerCase().substring(1);
            if (result >= 98 && e.getEntity() instanceof org.bukkit.entity.Monster && this.kingMobsEnabled) {
                distance += this.kingMobsLV;
                if (distance < 1)
                    distance = 1;
                String str = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("King " + mobName + str);
                if (this.specialMobGlowingEnabled)
                    e.getEntity().setGlowing(true);
                if (this.specialMobSilentEnabled)
                    e.getEntity().setSilent(true);
            } else if (result >= 90 && e.getEntity() instanceof org.bukkit.entity.Monster && this.notoriousMobsEnabled) {
                distance += this.notoriousMobsLV;
                if (distance < 1)
                    distance = 1;
                String str = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("Notorious " + mobName + str);
                if (this.specialMobGlowingEnabled)
                    e.getEntity().setGlowing(true);
                if (this.specialMobSilentEnabled)
                    e.getEntity().setSilent(true);
            } else if (result == 66 && e.getEntity() instanceof org.bukkit.entity.Monster && this.devilishMobsEnabled) {
                distance += this.devilishMobsLV;
                if (distance < 1)
                    distance = 1;
                String str = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("Devilish " + mobName + str);
                if (this.specialMobGlowingEnabled)
                    e.getEntity().setGlowing(true);
                if (this.specialMobSilentEnabled)
                    e.getEntity().setSilent(true);
            } else if (result <= 1 && e.getEntity() instanceof org.bukkit.entity.Monster && this.dumbMobsEnabled) {
                distance += this.dumbMobsLV;
                if (distance < 1)
                    distance = 1;
                String str = ChatColor.YELLOW + "[" + distance + "]";
                e.getEntity().setCustomName("Dumb " + mobName + str);
                if (this.specialMobGlowingEnabled)
                    e.getEntity().setGlowing(true);
                if (this.specialMobSilentEnabled)
                    e.getEntity().setSilent(true);
                ((EntityEquipment)Objects.<EntityEquipment>requireNonNull(e.getEntity().getEquipment())).setHelmet(new ItemStack(Material.BUCKET, 1));
            }
            switch (e.getEntity().getType()) {
                case ZOMBIE:
                    zombie = (Zombie)e.getEntity();
                    if (zombie.isBaby()) {
                        int i = distance * this.zombieBaseHP;
                        if (i < this.zombieMinLevel * this.zombieBaseHP) {
                            i = this.zombieMinLevel * this.zombieBaseHP;
                            distance = this.zombieMinLevel;
                        }
                        nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), i);
                        Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(i);
                        e.getEntity().setHealth(i);
                        String str = ChatColor.YELLOW + "[" + distance + "]";
                        if (e.getEntity().getCustomName() == null)
                            e.getEntity().setCustomName(mobName + str);
                        e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.zombieSpeed);
                        if (this.monsterNameplatesEnabled && this.zombieNameplate)
                            e.getEntity().setCustomNameVisible(true);
                        break;
                    }
                    hp = distance * this.zombieBaseHP;
                    if (hp < this.zombieMinLevel * this.zombieBaseHP) {
                        hp = this.zombieMinLevel * this.zombieBaseHP;
                        distance = this.zombieMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.zombieSpeed);
                    if (this.monsterNameplatesEnabled && this.zombieNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case WOLF:
                    hp = distance * this.wolfBaseHP;
                    if (hp < this.wolfMinLevel * this.wolfBaseHP) {
                        hp = this.wolfMinLevel * this.wolfBaseHP;
                        distance = this.wolfMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.wolfSpeed);
                    if (this.monsterNameplatesEnabled && this.wolfNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case FOX:
                    hp = distance * this.foxBaseHP;
                    if (hp < this.foxMinLevel * this.foxBaseHP) {
                        hp = this.foxMinLevel * this.foxBaseHP;
                        distance = this.foxMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.foxSpeed);
                    if (this.monsterNameplatesEnabled && this.foxNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case PANDA:
                    hp = distance * this.pandaBaseHP;
                    if (hp < this.pandaMinLevel * this.pandaBaseHP) {
                        hp = this.pandaMinLevel * this.pandaBaseHP;
                        distance = this.pandaMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.pandaSpeed);
                    if (this.monsterNameplatesEnabled && this.pandaNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case VILLAGER:
                    hp = distance * this.villagerBaseHP;
                    if (hp < this.villagerMinLevel * this.villagerBaseHP) {
                        hp = this.villagerMinLevel * this.villagerBaseHP;
                        distance = this.villagerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.villagerSpeed);
                    if (this.monsterNameplatesEnabled && this.villagerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SQUID:
                    hp = distance * this.squidBaseHP;
                    if (hp < this.squidMinLevel * this.squidBaseHP) {
                        hp = this.squidMinLevel * this.squidBaseHP;
                        distance = this.squidMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.squidSpeed);
                    if (this.monsterNameplatesEnabled && this.squidNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SPIDER:
                    hp = distance * this.spiderBaseHP;
                    if (hp < this.spiderMinLevel * this.spiderBaseHP) {
                        hp = this.spiderMinLevel * this.spiderBaseHP;
                        distance = this.spiderMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.spiderSpeed);
                    if (this.monsterNameplatesEnabled && this.spiderNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SNOWMAN:
                    hp = distance * this.snowmanBaseHP;
                    if (hp < this.snowmanMinLevel * this.snowmanBaseHP) {
                        hp = this.snowmanMinLevel * this.snowmanBaseHP;
                        distance = this.snowmanMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.snowmanSpeed);
                    if (this.monsterNameplatesEnabled && this.snowmanNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SLIME:
                    hp = distance * this.slimeBaseHP;
                    if (hp < this.slimeMinLevel * this.slimeBaseHP) {
                        hp = this.slimeMinLevel * this.slimeBaseHP;
                        distance = this.slimeMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.slimeSpeed);
                    if (this.monsterNameplatesEnabled && this.slimeNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SKELETON:
                    hp = distance * this.skeletonBaseHP;
                    if (hp < this.skeletonMinLevel * this.skeletonBaseHP) {
                        hp = this.skeletonMinLevel * this.skeletonBaseHP;
                        distance = this.skeletonMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.skeletonSpeed);
                    if (this.monsterNameplatesEnabled && this.skeletonNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SILVERFISH:
                    hp = distance * this.silverfishBaseHP;
                    if (hp < this.silverfishMinLevel * this.silverfishBaseHP) {
                        hp = this.silverfishMinLevel * this.silverfishBaseHP;
                        distance = this.silverfishMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.silverfishSpeed);
                    if (this.monsterNameplatesEnabled && this.silverfishNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SHEEP:
                    hp = distance * this.sheepBaseHP;
                    if (hp < this.sheepMinLevel * this.sheepBaseHP) {
                        hp = this.sheepMinLevel * this.sheepBaseHP;
                        distance = this.sheepMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.sheepSpeed);
                    if (this.monsterNameplatesEnabled && this.sheepNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case RABBIT:
                    hp = distance * this.rabbitBaseHP;
                    if (hp < this.rabbitMinLevel * this.rabbitBaseHP) {
                        hp = this.rabbitMinLevel * this.rabbitBaseHP;
                        distance = this.rabbitMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.rabbitSpeed);
                    if (this.monsterNameplatesEnabled && this.rabbitNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case PIG_ZOMBIE:
                    hp = distance * this.pigzombieBaseHP;
                    if (hp < this.pigzombieMinLevel * this.pigzombieBaseHP) {
                        hp = this.pigzombieMinLevel * this.pigzombieBaseHP;
                        distance = this.pigzombieMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.pigzombieSpeed);
                    if (this.monsterNameplatesEnabled && this.pigzombieNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case PIG:
                    hp = distance * this.pigBaseHP;
                    if (hp < this.pigMinLevel * this.pigBaseHP) {
                        hp = this.pigMinLevel * this.pigBaseHP;
                        distance = this.pigMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.pigSpeed);
                    if (this.monsterNameplatesEnabled && this.pigNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case MUSHROOM_COW:
                    hp = distance * this.mushroomcowBaseHP;
                    if (hp < this.mushroomcowMinLevel * this.mushroomcowBaseHP) {
                        hp = this.mushroomcowMinLevel * this.mushroomcowBaseHP;
                        distance = this.mushroomcowMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.mushroomcowSpeed);
                    if (this.monsterNameplatesEnabled && this.mushroomcowNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case MAGMA_CUBE:
                    hp = distance * this.magmacubeBaseHP;
                    if (hp < this.magmacubeMinLevel * this.magmacubeBaseHP) {
                        hp = this.magmacubeMinLevel * this.magmacubeBaseHP;
                        distance = this.magmacubeMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.magmacubeSpeed);
                    if (this.monsterNameplatesEnabled && this.magmacubeNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case GUARDIAN:
                    hp = distance * this.guardianBaseHP;
                    if (hp < this.guardianMinLevel * this.guardianBaseHP) {
                        hp = this.guardianMinLevel * this.guardianBaseHP;
                        distance = this.guardianMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.guardianSpeed);
                    if (this.monsterNameplatesEnabled && this.guardianNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ELDER_GUARDIAN:
                    hp = distance * this.guardianBaseHP;
                    if (hp < this.guardianMinLevel * this.guardianBaseHP) {
                        hp = this.guardianMinLevel * this.guardianBaseHP;
                        distance = this.guardianMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.guardianSpeed);
                    if (this.monsterNameplatesEnabled && this.guardianNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case GIANT:
                    hp = distance * this.giantBaseHP;
                    if (hp < this.giantMinLevel * this.giantBaseHP) {
                        hp = this.giantMinLevel * this.giantBaseHP;
                        distance = this.guardianMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.giantSpeed);
                    if (this.monsterNameplatesEnabled && this.giantNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case GHAST:
                    hp = distance * this.ghastBaseHP;
                    if (hp < this.ghastMinLevel * this.ghastBaseHP) {
                        hp = this.ghastMinLevel * this.ghastBaseHP;
                        distance = this.ghastMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.ghastSpeed);
                    if (this.monsterNameplatesEnabled && this.ghastNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ENDERMITE:
                    hp = distance * this.endermiteBaseHP;
                    if (hp < this.endermiteMinLevel * this.endermiteBaseHP) {
                        hp = this.endermiteMinLevel * this.endermiteBaseHP;
                        distance = this.endermiteMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.endermiteSpeed);
                    if (this.monsterNameplatesEnabled && this.endermiteNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ENDERMAN:
                    hp = distance * this.endermanBaseHP;
                    if (hp < this.endermanMinLevel * this.endermanBaseHP) {
                        hp = this.endermanMinLevel * this.endermanBaseHP;
                        distance = this.endermanMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() != null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.endermanSpeed);
                    if (this.monsterNameplatesEnabled && this.endermanNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ENDER_DRAGON:
                    hp = distance * this.enderdragonBaseHP;
                    if (hp < this.enderdragonMinLevel * this.enderdragonBaseHP) {
                        hp = this.enderdragonMinLevel * this.enderdragonBaseHP;
                        distance = this.enderdragonMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.enderdragonSpeed);
                    if (this.monsterNameplatesEnabled && this.enderdragonNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case CREEPER:
                    hp = distance * this.creeperBaseHP;
                    if (hp < this.creeperMinLevel * this.creeperBaseHP) {
                        hp = this.creeperMinLevel * this.creeperBaseHP;
                        distance = this.creeperMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.creeperSpeed);
                    if (this.monsterNameplatesEnabled && this.creeperNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case COW:
                    hp = distance * this.cowBaseHP;
                    if (hp < this.cowMinLevel * this.cowBaseHP) {
                        hp = this.cowMinLevel * this.cowBaseHP;
                        distance = this.cowMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.cowSpeed);
                    if (this.monsterNameplatesEnabled && this.cowNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case CHICKEN:
                    hp = distance * this.chickenBaseHP;
                    if (hp < this.chickenMinLevel * this.chickenBaseHP) {
                        hp = this.chickenMinLevel * this.chickenBaseHP;
                        distance = this.chickenMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.chickenSpeed);
                    if (this.monsterNameplatesEnabled && this.chickenNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case CAVE_SPIDER:
                    hp = distance * this.cavespiderBaseHP;
                    if (hp < this.cavespiderMinLevel * this.cavespiderBaseHP) {
                        hp = this.cavespiderMinLevel * this.cavespiderBaseHP;
                        distance = this.cavespiderMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.cavespiderSpeed);
                    if (this.monsterNameplatesEnabled && this.cavespiderNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case BLAZE:
                    hp = distance * this.blazeBaseHP;
                    if (hp < this.blazeMinLevel * this.blazeBaseHP) {
                        hp = this.blazeMinLevel * this.blazeBaseHP;
                        distance = this.blazeMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.blazeSpeed);
                    if (this.monsterNameplatesEnabled && this.blazeNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case WITCH:
                    hp = distance * this.witchBaseHP;
                    if (hp < this.witchMinLevel * this.witchBaseHP) {
                        hp = this.witchMinLevel * this.witchBaseHP;
                        distance = this.witchMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.witchSpeed);
                    if (this.monsterNameplatesEnabled && this.witchNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case WITHER_SKELETON:
                    hp = distance * this.witherSkeletonBaseHP;
                    if (hp < this.witherSkeletonMinLevel * this.witherSkeletonBaseHP) {
                        hp = this.witherSkeletonMinLevel * this.witherSkeletonBaseHP;
                        distance = this.witherSkeletonMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.witherSkeletonSpeed);
                    if (this.monsterNameplatesEnabled && this.witherSkeletonNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case SHULKER:
                    hp = distance * this.shulkerBaseHP;
                    if (hp < this.shulkerMinLevel * this.shulkerBaseHP) {
                        hp = this.shulkerMinLevel * this.shulkerBaseHP;
                        distance = this.shulkerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.shulkerSpeed);
                    if (this.monsterNameplatesEnabled && this.shulkerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case PILLAGER:
                    hp = distance * this.pillagerBaseHP;
                    if (hp < this.pillagerMinLevel * this.pillagerBaseHP) {
                        hp = this.pillagerMinLevel * this.pillagerBaseHP;
                        distance = this.pillagerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.pillagerSpeed);
                    if (this.monsterNameplatesEnabled && this.pillagerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ILLUSIONER:
                    hp = distance * this.illusionerBaseHP;
                    if (hp < this.illusionerMinLevel * this.illusionerBaseHP) {
                        hp = this.illusionerMinLevel * this.illusionerBaseHP;
                        distance = this.illusionerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.illusionerSpeed);
                    if (this.monsterNameplatesEnabled && this.illusionerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case EVOKER:
                    hp = distance * this.evokerBaseHP;
                    if (hp < this.evokerMinLevel * this.evokerBaseHP) {
                        hp = this.evokerMinLevel * this.evokerBaseHP;
                        distance = this.evokerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.evokerSpeed);
                    if (this.monsterNameplatesEnabled && this.evokerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case RAVAGER:
                    hp = distance * this.ravagerBaseHP;
                    if (hp < this.ravagerMinLevel * this.ravagerBaseHP) {
                        hp = this.ravagerMinLevel * this.ravagerBaseHP;
                        distance = this.ravagerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.ravagerSpeed);
                    if (this.monsterNameplatesEnabled && this.ravagerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case BAT:
                    hp = distance * this.batBaseHP;
                    if (hp < this.batMinLevel * this.batBaseHP) {
                        hp = this.batMinLevel * this.batBaseHP;
                        distance = this.batMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.batSpeed);
                    if (this.monsterNameplatesEnabled && this.batNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    break;
                case DROWNED:
                    hp = distance * this.drownedBaseHP;
                    if (hp < this.drownedMinLevel * this.drownedBaseHP) {
                        hp = this.drownedMinLevel * this.drownedBaseHP;
                        distance = this.drownedMinLevel;
                    }
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.drownedSpeed);
                    if (this.monsterNameplatesEnabled && this.drownedNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case HUSK:
                    hp = distance * this.huskBaseHP;
                    if (hp < this.huskMinLevel * this.huskBaseHP) {
                        hp = this.huskMinLevel * this.huskBaseHP;
                        distance = this.huskMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.huskSpeed);
                    if (this.monsterNameplatesEnabled && this.huskNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case ZOMBIE_VILLAGER:
                    hp = distance * this.zombievillagerBaseHP;
                    if (hp < this.zombievillagerMinLevel * this.zombievillagerBaseHP) {
                        hp = this.zombievillagerMinLevel * this.zombievillagerBaseHP;
                        distance = this.zombievillagerMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.zombievillagerSpeed);
                    if (this.monsterNameplatesEnabled && this.zombievillagerNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case POLAR_BEAR:
                    hp = distance * this.polarBearBaseHP;
                    if (hp < this.polarBearMinLevel * this.polarBearBaseHP) {
                        hp = this.polarBearMinLevel * this.polarBearBaseHP;
                        distance = this.polarBearMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.polarBearSpeed);
                    if (this.monsterNameplatesEnabled && this.polarBearNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case WANDERING_TRADER:
                    hp = distance * this.wanderingTraderBaseHP;
                    if (hp < this.wanderingTraderMinLevel * this.wanderingTraderBaseHP) {
                        hp = this.wanderingTraderMinLevel * this.wanderingTraderBaseHP;
                        distance = this.wanderingTraderMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.wanderingTraderSpeed);
                    if (this.monsterNameplatesEnabled && this.wanderingTraderNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case DONKEY:
                    hp = distance * this.donkeyBaseHP;
                    if (hp < this.donkeyMinLevel * this.donkeyBaseHP) {
                        hp = this.donkeyMinLevel * this.donkeyBaseHP;
                        distance = this.donkeyMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.donkeySpeed);
                    if (this.monsterNameplatesEnabled && this.donkeyNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case LLAMA:
                    hp = distance * this.llamaBaseHP;
                    if (hp < this.llamaMinLevel * this.llamaBaseHP) {
                        hp = this.llamaMinLevel * this.llamaBaseHP;
                        distance = this.llamaMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.llamaSpeed);
                    if (this.monsterNameplatesEnabled && this.llamaNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    break;
                case SALMON:
                    hp = distance * this.salmonBaseHP;
                    if (hp < this.salmonMinLevel * this.salmonBaseHP) {
                        hp = this.salmonMinLevel * this.salmonBaseHP;
                        distance = this.salmonMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.salmonSpeed);
                    if (this.monsterNameplatesEnabled && this.salmonNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case VEX:
                    hp = distance * this.vexBaseHP;
                    if (hp < this.vexMinLevel * this.vexBaseHP) {
                        hp = this.vexMinLevel * this.vexBaseHP;
                        distance = this.vexMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.vexSpeed);
                    if (this.monsterNameplatesEnabled && this.vexNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case VINDICATOR:
                    hp = distance * this.vindicatorBaseHP;
                    if (hp < this.vindicatorMinLevel * this.vindicatorBaseHP) {
                        hp = this.vindicatorMinLevel * this.vindicatorBaseHP;
                        distance = this.vindicatorMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.vindicatorSpeed);
                    if (this.monsterNameplatesEnabled && this.vindicatorNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
                case BEE:
                    hp = distance * this.beeBaseHP;
                    if (hp < this.beeMinLevel * this.beeBaseHP) {
                        hp = this.beeMinLevel * this.vindicatorBaseHP;
                        distance = this.beeMinLevel;
                    }
                    nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
                    e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                    e.getEntity().setHealth(hp);
                    levelColor = ChatColor.YELLOW + "[" + distance + "]";
                    if (e.getEntity().getCustomName() == null)
                        e.getEntity().setCustomName(mobName + levelColor);
                    e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * this.beeSpeed);
                    if (this.monsterNameplatesEnabled && this.beeNameplate)
                        e.getEntity().setCustomNameVisible(true);
                    break;
            }
        }
    }
}
