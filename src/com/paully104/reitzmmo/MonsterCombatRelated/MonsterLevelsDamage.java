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
    private final int pillagerBaseAttack = API.monsterConfig.getInt("PILLAGER_BASE_ATTACK");
    
    //debug section
    private final boolean debugEnabled = API.debugConfig.getBoolean("MonsterAttackingPlayer");



    @EventHandler
    public void monsterAttackingPlayer(EntityDamageByEntityEvent e) {
        //The world is set to -1 means don't apply to mobs
        int worldEnabled = API.worldConfig.getInt(e.getEntity().getLocation().getWorld().getName());
        if(worldEnabled != -1) {
            Entity defender = e.getEntity();//player
            Entity attacker = e.getDamager();//monster
            int player_defense;
            int monster_attack;
            int damage_done;
            Arrow arrow;
            String monster_level_from_name;

            EntityType attackerType = attacker.getType();
            EntityType defenderType = defender.getType();
            if (defenderType == EntityType.PLAYER) {
                PlayerData pd = API.Players.get(defender.getName());
                switch(attackerType)
                        {
                            case ZOMBIE:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * zombieBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() +
                                            " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case WOLF:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * wolfBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case VILLAGER:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * villagerBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SQUID:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * squidBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SNOWMAN:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * snowmanBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SLIME:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * slimeBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SILVERFISH:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * silverfishBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SHEEP:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * sheepBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case RABBIT:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * rabbitBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case PIG_ZOMBIE:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * pigzombieBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case PIG:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * pigBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case MUSHROOM_COW:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * mushroomcowBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case MAGMA_CUBE:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * magmacubeBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case GUARDIAN:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * guardianBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case GIANT:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * giantBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case GHAST:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * ghastBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case ENDERMITE:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * endermiteBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case ENDERMAN:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * endermanBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case ENDER_DRAGON:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * enderdragonBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case CREEPER:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * creeperBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case COW:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * cowBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case CHICKEN:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * chickenBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case CAVE_SPIDER:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * cavespiderBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case SPIDER:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * spiderBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case WITHER_SKELETON:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * witherSkeletonBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case BLAZE:
                                player_defense = pd.getData().getInt("Level");
                                monster_level_from_name = attacker.getCustomName().replaceAll("\\D+", "");
                                monster_attack = Integer.parseInt(monster_level_from_name) * blazeBaseAttack;
                                damage_done = monster_attack - player_defense;
                                if (damage_done < 1) {
                                    damage_done = 1;
                                }
                                e.setDamage(damage_done);
                                if (debugEnabled) {
                                    System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                }
                                break;
                            case ARROW:
                                arrow = (Arrow) attacker;
                                if (arrow.getShooter() instanceof Skeleton)
                                {
                                    player_defense = pd.getData().getInt("Level");
                                    monster_level_from_name = ((Skeleton) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                                    monster_attack = Integer.parseInt(monster_level_from_name) * skeletonBaseAttack;
                                    damage_done = monster_attack - player_defense;
                                    if (damage_done < 1) {
                                        damage_done = 1;
                                    }
                                    e.setDamage(damage_done);
                                    if (debugEnabled) {
                                        System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                    }
                                } else {

                                    //Not from skeleton archer
                                }
                                break;
                            case SPLASH_POTION:
                                arrow = (Arrow) attacker;
                                if (arrow.getShooter() instanceof Witch)
                                {
                                    player_defense = pd.getData().getInt("Level");
                                    monster_level_from_name = ((Witch) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                                    monster_attack = Integer.parseInt(monster_level_from_name) * witchBaseAttack;
                                    damage_done = monster_attack - player_defense;
                                    if (damage_done < 1) {
                                        damage_done = 1;
                                    }
                                    e.setDamage(damage_done);
                                    if (debugEnabled) {
                                        System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                    }
                                }
                                break;
                            case SHULKER_BULLET:
                                arrow = (Arrow) attacker;
                                if (arrow.getShooter() instanceof Shulker)
                                {
                                    player_defense = pd.getData().getInt("Level");
                                    monster_level_from_name = ((Witch) arrow.getShooter()).getCustomName().replaceAll("\\D+", "");
                                    monster_attack = Integer.parseInt(monster_level_from_name) * shulkerBaseAttack;
                                    damage_done = monster_attack - player_defense;
                                    if (damage_done < 1) {
                                        damage_done = 1;
                                    }
                                    e.setDamage(damage_done);
                                    if (debugEnabled) {
                                        System.out.println("[MAP]: " + attacker.getType() + " " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                    }
                                }
                        }

                }


            }
        }
    }


