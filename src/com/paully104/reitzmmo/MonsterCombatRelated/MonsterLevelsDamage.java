package com.paully104.reitzmmo.MonsterCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class MonsterLevelsDamage implements Listener {

    private final int zombieBaseAttack = API.monsterConfig.getInt("ZOMBIE_BASE_ATTACK");
    private final int wolfBaseAttack  = API.monsterConfig.getInt("WOLF_BASE_ATTACK");
    private final int villagerBaseAttack  = API.monsterConfig.getInt("VILLAGER_BASE_ATTACK");
    private final int squidBaseAttack  = API.monsterConfig.getInt("SQUID_BASE_ATTACK");
    private final int spiderBaseAttack = API.monsterConfig.getInt("SPIDER_BASE_ATTACK");
    private final int snowmanBaseAttack  = API.monsterConfig.getInt("SNOWMAN_BASE_ATTACK");
    private final int slimeBaseAttack = API.monsterConfig.getInt("SLIME_BASE_ATTACK");
    private final int skeletonBaseAttack  = API.monsterConfig.getInt("SKELETON_BASE_ATTACK");
    private final int silverfishBaseAttack  = API.monsterConfig.getInt("SILVERFISH_BASE_ATTACK");
    private final int sheepBaseAttack = API.monsterConfig.getInt("SHEEP_BASE_ATTACK");
    private final int rabbitBaseAttack  = API.monsterConfig.getInt("RABBIT_BASE_ATTACK");
    private final int pigzombieBaseAttack  = API.monsterConfig.getInt("PIGZOMBIE_BASE_ATTACK");
    private final int pigBaseAttack  = API.monsterConfig.getInt("PIG_BASE_ATTACK");
    private final int mushroomcowBaseAttack  = API.monsterConfig.getInt("MUSHROOMCOW_BASE_ATTACK");
    private final int magmacubeBaseAttack  = API.monsterConfig.getInt("MAGMACUBE_BASE_ATTACK");
    private final int guardianBaseAttack  = API.monsterConfig.getInt("GUARDIAN_BASE_ATTACK");
    private final int giantBaseAttack  = API.monsterConfig.getInt("GIANT_BASE_ATTACK");
    private final int ghastBaseAttack = API.monsterConfig.getInt("GHAST_BASE_ATTACK");
    private final int endermiteBaseAttack  = API.monsterConfig.getInt("ENDERMITE_BASE_ATTACK");
    private final int endermanBaseAttack = API.monsterConfig.getInt("ENDERMAN_BASE_ATTACK");
    private final int enderdragonBaseAttack = API.monsterConfig.getInt("ENDERDRAGON_BASE_ATTACK");
    private final int creeperBaseAttack  = API.monsterConfig.getInt("CREEPER_BASE_ATTACK");
    private final int cowBaseAttack  = API.monsterConfig.getInt("COW_BASE_ATTACK");
    private final int chickenBaseAttack  = API.monsterConfig.getInt("CHICKEN_BASE_ATTACK");
    private final int cavespiderBaseAttack = API.monsterConfig.getInt("CAVESPIDER_BASE_ATTACK");
    private final int blazeBaseAttack  = API.monsterConfig.getInt("BLAZE_BASE_ATTACK");
    private final int witchBaseAttack  = API.monsterConfig.getInt("WITCH_BASE_ATTACK");
    private final int witherSkeletonBaseAttack = API.monsterConfig.getInt("WITHERSKELETON_BASE_ATTACK");
    private final int shulkerBaseAttack = API.monsterConfig.getInt("SHULKER_BASE_ATTACK");
    
    //debug section
    private final boolean debugEnabled = API.debugConfig.getBoolean("MonsterAttackingPlayer");



    @EventHandler
    public void monsterAttackingPlayer(EntityDamageByEntityEvent e)
    {
        Entity defender = e.getEntity();//player
        Entity attacker = e.getDamager();//monster
        int player_defense;
        int monster_attack;
        int damage_done;
        String monster_level_from_name;

        //Zombie
        if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.ZOMBIE)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * zombieBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +
                    " -> " + defender.getName() + " " + player_defense);}
        }
        //wolf
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.WOLF)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * wolfBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Villager
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.VILLAGER)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * villagerBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Squid
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SQUID)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * squidBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Spider
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SPIDER)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * spiderBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Snowman
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SNOWMAN)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * snowmanBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Slime
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SLIME)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * slimeBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }

        //Skeleton - If it was an arrow from a skeleton
        else if(defender.getType() == EntityType.PLAYER && (attacker.getType() == EntityType.ARROW))
        {
            Arrow arrow = (Arrow)attacker;
            if (arrow.getShooter() instanceof Skeleton)
            {
                PlayerData pd = API.Players.get(defender.getName());
                player_defense = pd.getData().getInt("Level");
                monster_level_from_name = ((Skeleton) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                monster_attack = Integer.parseInt(monster_level_from_name) * skeletonBaseAttack;
                damage_done = monster_attack - player_defense;
                if (damage_done < 1) {
                    damage_done = 1;
                }
                e.setDamage(damage_done);
                if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
            }
            else
            {

                //Not from skeleton archer
            }
        }
        //Silverfish
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SILVERFISH)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * silverfishBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Sheep
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SHEEP)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * sheepBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Rabbit
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.RABBIT)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * rabbitBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Pig Zombie
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.PIG_ZOMBIE)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) *pigzombieBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Pig
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.PIG)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * pigBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Mushroom Cow
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.MUSHROOM_COW)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * mushroomcowBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Magma Cube
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.MAGMA_CUBE)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * magmacubeBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Guardian
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.GUARDIAN)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * guardianBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Giant
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.GIANT)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * giantBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Ghast
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.GHAST)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * ghastBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Endermite
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.ENDERMITE)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * endermiteBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Enderman
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.ENDERMAN)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * endermanBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Ender Dragon
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.ENDER_DRAGON)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * enderdragonBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Creeper
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.CREEPER)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * creeperBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Cow
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.COW)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * cowBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Chicken
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.CHICKEN)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * chickenBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Cave Spider
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.CAVE_SPIDER)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * cavespiderBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Spider
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.SPIDER)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * spiderBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //witherSkeleton
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.WITHER_SKELETON)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * witherSkeletonBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Blaze
        else if((defender.getType() == EntityType.PLAYER && !(attacker.getType() == EntityType.ARROW) && attacker.getType() == EntityType.BLAZE)) {
            PlayerData pd = API.Players.get(defender.getName());
            player_defense = pd.getData().getInt("Level");
            monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
            monster_attack = Integer.parseInt(monster_level_from_name) * blazeBaseAttack;
            damage_done = monster_attack - player_defense;
            if (damage_done < 1) {
                damage_done = 1;
            }
            e.setDamage(damage_done);
            if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
        }
        //Witch throws potion
        else if(defender.getType() == EntityType.PLAYER && (attacker.getType() == EntityType.SPLASH_POTION
        || attacker.getType() ==EntityType.SPLASH_POTION))
        {
            Arrow arrow = (Arrow)attacker;
            if (arrow.getShooter() instanceof Witch)
            {
                PlayerData pd = API.Players.get(defender.getName());
                player_defense = pd.getData().getInt("Level");
                monster_level_from_name = ((Witch) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                monster_attack = Integer.parseInt(monster_level_from_name) * witchBaseAttack;
                damage_done = monster_attack - player_defense;
                if (damage_done < 1) {
                    damage_done = 1;
                }
                e.setDamage(damage_done);
                if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
            }
            else
            {

                //Not from skeleton archer
            }
        }
        //SHULKER
        else if(defender.getType() == EntityType.PLAYER && (attacker.getType() == EntityType.SHULKER_BULLET))
        {
            Arrow arrow = (Arrow)attacker;
            if (arrow.getShooter() instanceof Shulker)
            {
                PlayerData pd = API.Players.get(defender.getName());
                player_defense = pd.getData().getInt("Level");
                monster_level_from_name = ((Witch) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                monster_attack = Integer.parseInt(monster_level_from_name) * shulkerBaseAttack;
                damage_done = monster_attack - player_defense;
                if (damage_done < 1) {
                    damage_done = 1;
                }
                e.setDamage(damage_done);
                if(debugEnabled) {System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +                     " -> " + defender.getName() + " " + player_defense);}
            }
            else
            {

                //Not from skeleton archer
            }
        }


    }

}
