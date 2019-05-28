package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.Custom_Bows;
import com.paully104.reitzmmo.Enum.Weapon_Damage;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Collection;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerAttackingMonster implements Listener {

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");
    private final boolean namePlatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");

    @EventHandler
    public void playerAttackingMonster(EntityDamageByEntityEvent e) {
        int worldEnabled = API.worldConfig.getInt(e.getEntity().getLocation().getWorld().getName());
        if (worldEnabled != -1) {

            Boolean isProjectile = false;
            Entity defender = e.getEntity();//monster
            Entity attacker = e.getDamager();//player

            //We do not want to use this for PVP
            if (!(defender instanceof Player)) {


                int player_attack = 0;
                int monster_defense = 0;
                int damage_done = 0;
                String monster_level_from_name = "";

                if (attacker instanceof Player) {
                    //lets ignore if the damage source is custom
                    PlayerData pd = API.Players.get(attacker.getUniqueId().toString());
                    player_attack = pd.getData().getInt("Attack");
                    try {
                        monster_level_from_name = defender.getCustomName().replaceAll("\\D+", "");
                    } catch (NullPointerException error) {
                        String levelColor = ChatColor.YELLOW + "[" + 1 + "]";
                        defender.setCustomName(e.getEntityType() + levelColor);
                        monster_level_from_name = "1";
                        if (namePlatesEnabled) {
                            defender.setCustomNameVisible(true);
                        }
                    }
                    monster_defense = Integer.parseInt(monster_level_from_name);
                    //Damage done is player attack - monster defence so a lv 2 attack against a lv 1 would do 1 damage
                    damage_done = player_attack - monster_defense;
                    if (damage_done < 1) {
                        damage_done = 1;
                    }
                    HumanEntity human = (HumanEntity) attacker;
                    try {
                        //if in list
                        int weaponDamage = 0;
                        if (!(human.getInventory().getItemInMainHand().toString().contains("AIR"))) {

                            try {
                                weaponDamage = (Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue());
                            } catch (NullPointerException error) {
                                weaponDamage = 0;
                            } finally {
                                //if the weapon has special stats
                                if (human.getInventory().getItemInMainHand().getItemMeta().hasAttributeModifiers()) {
                                    Collection<AttributeModifier> weaponStats = human.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
                                    int weaponBonus = (int) weaponStats.iterator().next().getAmount();
                                    weaponDamage = weaponDamage + weaponBonus;
                                    damage_done = damage_done + weaponDamage;
                                    e.setDamage(damage_done);
                                    if (debugEnabled) {
                                        System.out.println(weaponStats.iterator().next().getAmount());
                                    }

                                    //[14:07:39 INFO]: AttributeModifier{uuid=00000000-0000-0b38-0000-0000000da6be, name=generic.attackDamage, operation=ADD_NUMBER, amount=20.0, slot=}
                                    //int weaponBonus = human.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).;
                                    //weaponDamage = weaponDamage + weaponBonus;
                                }
                            }
                        } else {
                            //empty handed

                            damage_done = damage_done + weaponDamage;
                            e.setDamage(damage_done);
                            if (debugEnabled == true) {
                                System.out.print("empty hands");
                            }
                        }
                    } catch (IllegalArgumentException error) {
                        e.setDamage(damage_done);//if not in list
                    }


                } else if (e.getDamager() instanceof Arrow) {
                    isProjectile = true;
                    if (debugEnabled == true) {
                        System.out.println("Arrow attack event");
                    }
                    Arrow arrow = (Arrow) e.getDamager();
                    Entity shooter = (Entity) arrow.getShooter();
                    if (shooter instanceof Player) {
                        PlayerData pd = API.Players.get(shooter.getUniqueId().toString());
                        player_attack = pd.getData().getInt("Attack");
                        monster_level_from_name = defender.getCustomName().replaceAll("\\D+", "");
                        monster_defense = Integer.parseInt(monster_level_from_name);
                        //updated on 5/6/2017 to add new custom bow recipes
                        damage_done = player_attack - monster_defense + Custom_Bows.getBowDamage(((Player) shooter).getInventory().getItemInMainHand());
                        if (debugEnabled == true) {
                            System.out.println("Damage Done: " + player_attack + " " + monster_defense + " " + Custom_Bows.getBowDamage(((Player) shooter).getInventory().getItemInMainHand()));
                        }

                        //custom item logic

                        //check if cheap arrow
                        //make sure 1 damage is done minimum
                        if (damage_done < 1) {
                            damage_done = 1;
                        }
                        e.setDamage(damage_done);
                        if (debugEnabled && isProjectile) {
                            System.out.println("[PAM]: " + shooter.getName() + " " + player_attack +
                                    " -> " + defender.getCustomName() + " " + monster_defense + "\n" + " Damage_Done -> " + damage_done +
                                    "\n" + " Mob HP: " + ((LivingEntity) defender).getHealth());

                        }
                    }

                }

                if (debugEnabled && !isProjectile && !(e.getDamager() instanceof TNTPrimed)) {
                    System.out.println("[PAM]: " + attacker.getName() + " " + player_attack +
                            " -> " + defender.getCustomName() + " " + monster_defense + "\n" + " Damage_Done -> " + damage_done +
                            "\n" + " Mob HP: " + "Broken");

                }
                if (debugEnabled == true) {
                    System.out.println(e.getDamage() + "Damage DONE");
                }

            }
        }
    }
}
