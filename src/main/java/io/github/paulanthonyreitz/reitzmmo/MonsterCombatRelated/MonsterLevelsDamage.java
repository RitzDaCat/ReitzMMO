package io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated;

import java.util.Collection;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Enum.Armor_Defense;
import io.github.paulanthonyreitz.reitzmmo.Enum.Weapon_Damage;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MonsterLevelsDamage implements Listener {
    private static final String ARMORTOTAL = "Armor Total: ";

    private final int zombieBaseAttack = API.monsterConfig.getInt("Zombie.base_attack");

    private final int wolfBaseAttack = API.monsterConfig.getInt("Wolf.base_attack");

    private final int villagerBaseAttack = API.monsterConfig.getInt("Villager.base_attack");

    private final int squidBaseAttack = API.monsterConfig.getInt("Squid.base_attack");

    private final int spiderBaseAttack = API.monsterConfig.getInt("Spider.base_attack");

    private final int snowmanBaseAttack = API.monsterConfig.getInt("Snowman.base_attack");

    private final int slimeBaseAttack = API.monsterConfig.getInt("Slime.base_attack");

    private final int skeletonBaseAttack = API.monsterConfig.getInt("Skeleton.base_attack");

    private final int silverfishBaseAttack = API.monsterConfig.getInt("Silverfish_base_attack");

    private final int sheepBaseAttack = API.monsterConfig.getInt("Sheep.base_attack");

    private final int rabbitBaseAttack = API.monsterConfig.getInt("Rabbit.base_attack");

    private final int pigzombieBaseAttack = API.monsterConfig.getInt("Pigzombie.base_attack");

    private final int pigBaseAttack = API.monsterConfig.getInt("Pig.base_attack");

    private final int mushroomcowBaseAttack = API.monsterConfig.getInt("Mushroomcow.base_attack");

    private final int magmacubeBaseAttack = API.monsterConfig.getInt("Magmacube.base_attack");

    private final int guardianBaseAttack = API.monsterConfig.getInt("Guardian.base_attack");

    private final int giantBaseAttack = API.monsterConfig.getInt("Giant.base_attack");

    private final int ghastBaseAttack = API.monsterConfig.getInt("Ghast.base_attack");

    private final int endermiteBaseAttack = API.monsterConfig.getInt("Endermite.base_attack");

    private final int endermanBaseAttack = API.monsterConfig.getInt("Enderman.base_attack");

    private final int enderdragonBaseAttack = API.monsterConfig.getInt("Enderdragon.base_attack");

    private final int creeperBaseAttack = API.monsterConfig.getInt("Creeper.base_attack");

    private final int cowBaseAttack = API.monsterConfig.getInt("Cow.base_attack");

    private final int chickenBaseAttack = API.monsterConfig.getInt("Chicken.base_attack");

    private final int cavespiderBaseAttack = API.monsterConfig.getInt("Cavespider.base_attack");

    private final int blazeBaseAttack = API.monsterConfig.getInt("Blaze.base_attack");

    private final int witchBaseAttack = API.monsterConfig.getInt("Witch.base_attack");

    private final int witherSkeletonBaseAttack = API.monsterConfig.getInt("Witherskeleton.base_attack");

    private final int shulkerBaseAttack = API.monsterConfig.getInt("Shulker.base_attack");

    private final int pillagerBaseAttack = API.monsterConfig.getInt("Pillager.base_attack");

    private final int illusionerBaseAttack = API.monsterConfig.getInt("Illusioner.base_attack");

    private final int evokerBaseAttack = API.monsterConfig.getInt("Evoker.base_attack");

    private final int ravagerBaseAttack = API.monsterConfig.getInt("Ravager.base_attack");

    private final int batBaseAttack = API.monsterConfig.getInt("Bat.base_attack");

    private final int drownedBaseAttack = API.monsterConfig.getInt("Drowned.base_attack");

    private final int vexBaseAttack = API.monsterConfig.getInt("Vex.base_attack");

    private final int vindicatorBaseAttack = API.monsterConfig.getInt("Vindicator.base_attack");

    private final int zombievillagerBaseAttack = API.monsterConfig.getInt("Zombievillager.base_attack");

    private final int foxBaseAttack = API.monsterConfig.getInt("Fox.base_attack");

    private final int pandaBaseAttack = API.monsterConfig.getInt("Panda.base_attack");

    private final int beeBaseAttack = API.monsterConfig.getInt("Bee.base_attack");

    private final boolean debugEnabled = API.debugConfig.getBoolean("MonsterAttackingPlayer");

    private final int defenseScale = API.playerConfig.getInt("Scaling.Player.DefenseScale");

    @EventHandler
    public void monsterAttackingPlayer(EntityDamageByEntityEvent e) {
        int worldEnabled = API.worldConfig.getInt(e.getEntity().getLocation().getWorld().getName());
        if (worldEnabled != -1 && !e.getEntity().hasMetadata("NPC")) {
            Entity defender = e.getEntity();
            Entity attacker = e.getDamager();
            EntityType attackerType = attacker.getType();
            EntityType defenderType = defender.getType();
            if (defenderType == EntityType.PLAYER) {
                int monster_attack, damage_done;
                Arrow arrow;
                int helmet, chestplate, leggings, boots, armorTotal;
                String attackerUUID;
                PlayerData personAttacking;
                int personAttacking_Attack, weaponDamage;
                ThrownPotion potion;
                String defenderUUID = defender.getUniqueId().toString();
                PlayerData pd = (PlayerData)API.Players.get(defenderUUID);
                Player defendingPlayer = (Player)defender;
                int player_defense = pd.getData().getInt("Level") * this.defenseScale;
                int helmBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getHelmet());
                int chestBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getChestplate());
                int leggingBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getLeggings());
                int bootBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getBoots());
                int totalBonus = helmBonus + chestBonus + leggingBonus + bootBonus;
                try {
                    helmet = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getHelmet().getType().toString().toUpperCase()).getValue();
                    try {
                        Collection<AttributeModifier> helmetEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                        int helmetBonus = (int) helmetEnchantAttribute.iterator().next().getAmount();
                        helmet += helmetBonus;
                    } catch (NullPointerException nullPointerException) {}
                } catch (NullPointerException error) {
                    helmet = 0;
                }
                try {
                    chestplate = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getChestplate().getType().toString().toUpperCase()).getValue();
                    try {
                        Collection<AttributeModifier> chestplateEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                        int chestplateBonus = (int) chestplateEnchantAttribute.iterator().next().getAmount();
                        chestplate += chestplateBonus;
                    } catch (NullPointerException nullPointerException) {}
                } catch (NullPointerException error) {
                    chestplate = 0;
                }
                try {
                    leggings = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getLeggings().getType().toString().toUpperCase()).getValue();
                    try {
                        Collection<AttributeModifier> leggingsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                        int leggingsBonus = (int) leggingsEnchantAttribute.iterator().next().getAmount();
                        leggings += leggingsBonus;
                    } catch (NullPointerException nullPointerException) {}
                } catch (NullPointerException error) {
                    leggings = 0;
                }
                try {
                    boots = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getBoots().getType().toString().toUpperCase()).getValue();
                    try {
                        Collection<AttributeModifier> bootsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                        int bootsBonus = (int) bootsEnchantAttribute.iterator().next().getAmount();
                        boots += bootsBonus;
                    } catch (NullPointerException nullPointerException) {}
                } catch (NullPointerException error) {
                    boots = 0;
                }
                switch (attackerType) {
                    case PLAYER:
                        attackerUUID = attacker.getUniqueId().toString();
                        personAttacking = API.Players.get(attackerUUID);
                        personAttacking_Attack = personAttacking.getData().getInt("Attack");
                        weaponDamage = 0;
                        try {
                            Player human = (Player)attacker;
                            if (!human.getInventory().getItemInMainHand().toString().contains("AIR")) {
                                try {
                                    weaponDamage = Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue();
                                } catch (IllegalArgumentException error) {
                                    weaponDamage = 0;
                                    if (this.debugEnabled)
                                        System.out.println("weapon damaged set to 0");
                                } finally {
                                    if (this.debugEnabled)
                                        System.out.println("Finally statement happening");
                                    if (human.getInventory().getItemInMainHand().getItemMeta().hasAttributeModifiers()) {
                                        if (this.debugEnabled)
                                            System.out.println("Item has bonus stats for main attack");
                                        Collection<AttributeModifier> weaponStats = human.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
                                        int weaponBonus = (int)((AttributeModifier)weaponStats.iterator().next()).getAmount();
                                        weaponDamage += weaponBonus;
                                    }
                                }
                            } else if (this.debugEnabled) {
                                System.err.print("empty hands");
                            }
                        } catch (IllegalArgumentException error) {
                            if (this.debugEnabled)
                                System.err.println(error);
                        }
                        damage_done = personAttacking_Attack + weaponDamage - player_defense;
                        if (damage_done < 1) {
                            if (this.debugEnabled)
                                System.out.println("Cant do less then 1 damage!");
                            damage_done = 1;
                        }
                        e.setDamage((personAttacking_Attack + weaponDamage - player_defense));
                        if (this.debugEnabled) {
                            System.out.println("Personattacking_Attack: " + personAttacking_Attack);
                            System.out.println("WeaponDamage: " + weaponDamage);
                            System.out.println("[PVP]: " + attacker.getName() + " " + (personAttacking_Attack + weaponDamage) + " -> " + defender
                                    .getName() + " " + player_defense);
                        }
                        break;
                    case ZOMBIE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.zombieBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case WOLF:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.wolfBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SQUID:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.squidBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SNOWMAN:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.snowmanBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SLIME:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.slimeBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SILVERFISH:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.silverfishBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SHEEP:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.sheepBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case RABBIT:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.rabbitBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case PIG_ZOMBIE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pigzombieBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case PIG:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pigBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case MUSHROOM_COW:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.mushroomcowBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case MAGMA_CUBE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.magmacubeBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case GUARDIAN:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.guardianBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case GIANT:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.giantBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case GHAST:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.ghastBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case ENDERMITE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.endermiteBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case ENDERMAN:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.endermanBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDefense = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDefense);
                        }
                        break;
                    case ENDER_DRAGON:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.enderdragonBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDefense = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDefense);
                        }
                        break;
                    case CREEPER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.creeperBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case COW:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.cowBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case CHICKEN:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.chickenBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case CAVE_SPIDER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.cavespiderBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case SPIDER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.spiderBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case WITHER_SKELETON:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.witherSkeletonBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case BLAZE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.blazeBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case PILLAGER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pillagerBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case ILLUSIONER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.illusionerBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case EVOKER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.evokerBaseAttack;
                        try {
                            helmet = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getHelmet().getType().toString().toUpperCase()).getValue();
                            Collection<AttributeModifier> helmetEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                            int helmetBonus = (int)((AttributeModifier)helmetEnchantAttribute.iterator().next()).getAmount();
                            helmet += helmetBonus;
                        } catch (NullPointerException error) {
                            helmet = 0;
                        }
                        try {
                            chestplate = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getChestplate().getType().toString().toUpperCase()).getValue();
                            Collection<AttributeModifier> chestplateEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                            int chestplateBonus = (int)((AttributeModifier)chestplateEnchantAttribute.iterator().next()).getAmount();
                            chestplate += chestplateBonus;
                        } catch (NullPointerException error) {
                            chestplate = 0;
                        }
                        try {
                            leggings = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getLeggings().getType().toString().toUpperCase()).getValue();
                            Collection<AttributeModifier> leggingsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                            int leggingsBonus = (int)((AttributeModifier)leggingsEnchantAttribute.iterator().next()).getAmount();
                            leggings += leggingsBonus;
                        } catch (NullPointerException error) {
                            leggings = 0;
                        }
                        try {
                            boots = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getBoots().getType().toString().toUpperCase()).getValue();
                            Collection<AttributeModifier> bootsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
                            int bootsBonus = (int)((AttributeModifier)bootsEnchantAttribute.iterator().next()).getAmount();
                            boots += bootsBonus;
                        } catch (NullPointerException error) {
                            boots = 0;
                        }
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case BAT:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.batBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case RAVAGER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.ravagerBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case DROWNED:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.drownedBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case VEX:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.vexBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case VINDICATOR:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.vindicatorBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case ZOMBIE_VILLAGER:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.zombievillagerBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case PANDA:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pandaBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case FOX:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.foxBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case BEE:
                        monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.beeBaseAttack;
                        armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                        damage_done = monster_attack - player_defense + armorTotal;
                        if (damage_done < 1)
                            damage_done = 1;
                        e.setDamage(damage_done);
                        if (this.debugEnabled) {
                            System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                            System.out.println("[Monster Attack: " + monster_attack);
                            System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                            int totalDamage = armorTotal + player_defense;
                            System.out.println("Armor Total: " + totalDamage);
                        }
                        break;
                    case ARROW:
                        arrow = (Arrow)attacker;
                        if (arrow.getShooter() instanceof org.bukkit.entity.Skeleton) {
                            monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)arrow.getShooter()) * this.skeletonBaseAttack;
                            armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                            damage_done = monster_attack - player_defense + armorTotal;
                            if (damage_done < 1)
                                damage_done = 1;
                            e.setDamage(damage_done);
                            if (this.debugEnabled) {
                                System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                System.out.println("[Monster Attack: " + monster_attack);
                                System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                                int totalDamage = armorTotal + player_defense;
                                System.out.println("Armor Total: " + totalDamage);
                            }
                        }
                        break;
                    case SPLASH_POTION:
                        potion = (ThrownPotion)attacker;
                        if (potion.getShooter() instanceof org.bukkit.entity.Witch) {
                            monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)potion.getShooter()) * this.witchBaseAttack;
                            armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                            damage_done = monster_attack - player_defense + armorTotal;
                            if (damage_done < 1)
                                damage_done = 1;
                            e.setDamage(damage_done);
                            if (this.debugEnabled) {
                                System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                System.out.println("[Monster Attack: " + monster_attack);
                                System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                                int totalDamage = armorTotal + player_defense;
                                System.out.println("Armor Total: " + totalDamage);
                            }
                        }
                        break;
                    case SHULKER_BULLET:
                        arrow = (Arrow)attacker;
                        if (arrow.getShooter() instanceof org.bukkit.entity.Shulker) {
                            monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)arrow.getShooter()) * this.shulkerBaseAttack;
                            armorTotal = helmet + chestplate + leggings + boots + totalBonus;
                            damage_done = monster_attack - player_defense + armorTotal;
                            if (damage_done < 1)
                                damage_done = 1;
                            e.setDamage(damage_done);
                            if (this.debugEnabled) {
                                System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
                                System.out.println("[Monster Attack: " + monster_attack);
                                System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
                                int totalDamage = armorTotal + player_defense;
                                System.out.println("Armor Total: " + totalDamage);
                            }
                        }
                        break;
                }
            }
        }
    }
}
