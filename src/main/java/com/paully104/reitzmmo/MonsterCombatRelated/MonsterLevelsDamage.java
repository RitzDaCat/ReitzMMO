/*      */ package com.paully104.reitzmmo.MonsterCombatRelated;
/*      */ 
/*      */ import com.paully104.reitzmmo.ConfigFiles.API;
/*      */ import com.paully104.reitzmmo.Enum.Armor_Defense;
/*      */ import com.paully104.reitzmmo.Enum.Weapon_Damage;
/*      */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*      */ import com.paully104.reitzmmo.PlayerData.PlayerData;
/*      */ import java.util.Collection;
/*      */ import org.bukkit.attribute.Attribute;
/*      */ import org.bukkit.attribute.AttributeModifier;
/*      */ import org.bukkit.entity.Arrow;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.entity.ThrownPotion;
/*      */ import org.bukkit.event.EventHandler;
/*      */ import org.bukkit.event.Listener;
/*      */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MonsterLevelsDamage
/*      */   implements Listener
/*      */ {
/*      */   private static final String ARMORTOTAL = "Armor Total: ";
/*   35 */   private final int zombieBaseAttack = API.monsterConfig.getInt("Zombie.base_attack");
/*   36 */   private final int wolfBaseAttack = API.monsterConfig.getInt("Wolf.base_attack");
/*   37 */   private final int villagerBaseAttack = API.monsterConfig.getInt("Villager.base_attack");
/*   38 */   private final int squidBaseAttack = API.monsterConfig.getInt("Squid.base_attack");
/*   39 */   private final int spiderBaseAttack = API.monsterConfig.getInt("Spider.base_attack");
/*   40 */   private final int snowmanBaseAttack = API.monsterConfig.getInt("Snowman.base_attack");
/*   41 */   private final int slimeBaseAttack = API.monsterConfig.getInt("Slime.base_attack");
/*   42 */   private final int skeletonBaseAttack = API.monsterConfig.getInt("Skeleton.base_attack");
/*   43 */   private final int silverfishBaseAttack = API.monsterConfig.getInt("Silverfish_base_attack");
/*   44 */   private final int sheepBaseAttack = API.monsterConfig.getInt("Sheep.base_attack");
/*   45 */   private final int rabbitBaseAttack = API.monsterConfig.getInt("Rabbit.base_attack");
/*   46 */   private final int pigzombieBaseAttack = API.monsterConfig.getInt("Pigzombie.base_attack");
/*   47 */   private final int pigBaseAttack = API.monsterConfig.getInt("Pig.base_attack");
/*   48 */   private final int mushroomcowBaseAttack = API.monsterConfig.getInt("Mushroomcow.base_attack");
/*   49 */   private final int magmacubeBaseAttack = API.monsterConfig.getInt("Magmacube.base_attack");
/*   50 */   private final int guardianBaseAttack = API.monsterConfig.getInt("Guardian.base_attack");
/*   51 */   private final int giantBaseAttack = API.monsterConfig.getInt("Giant.base_attack");
/*   52 */   private final int ghastBaseAttack = API.monsterConfig.getInt("Ghast.base_attack");
/*   53 */   private final int endermiteBaseAttack = API.monsterConfig.getInt("Endermite.base_attack");
/*   54 */   private final int endermanBaseAttack = API.monsterConfig.getInt("Enderman.base_attack");
/*   55 */   private final int enderdragonBaseAttack = API.monsterConfig.getInt("Enderdragon.base_attack");
/*   56 */   private final int creeperBaseAttack = API.monsterConfig.getInt("Creeper.base_attack");
/*   57 */   private final int cowBaseAttack = API.monsterConfig.getInt("Cow.base_attack");
/*   58 */   private final int chickenBaseAttack = API.monsterConfig.getInt("Chicken.base_attack");
/*   59 */   private final int cavespiderBaseAttack = API.monsterConfig.getInt("Cavespider.base_attack");
/*   60 */   private final int blazeBaseAttack = API.monsterConfig.getInt("Blaze.base_attack");
/*   61 */   private final int witchBaseAttack = API.monsterConfig.getInt("Witch.base_attack");
/*   62 */   private final int witherSkeletonBaseAttack = API.monsterConfig.getInt("Witherskeleton.base_attack");
/*   63 */   private final int shulkerBaseAttack = API.monsterConfig.getInt("Shulker.base_attack");
/*   64 */   private final int pillagerBaseAttack = API.monsterConfig.getInt("Pillager.base_attack");
/*   65 */   private final int illusionerBaseAttack = API.monsterConfig.getInt("Illusioner.base_attack");
/*   66 */   private final int evokerBaseAttack = API.monsterConfig.getInt("Evoker.base_attack");
/*   67 */   private final int ravagerBaseAttack = API.monsterConfig.getInt("Ravager.base_attack");
/*   68 */   private final int batBaseAttack = API.monsterConfig.getInt("Bat.base_attack");
/*   69 */   private final int drownedBaseAttack = API.monsterConfig.getInt("Drowned.base_attack");
/*   70 */   private final int vexBaseAttack = API.monsterConfig.getInt("Vex.base_attack");
/*   71 */   private final int vindicatorBaseAttack = API.monsterConfig.getInt("Vindicator.base_attack");
/*   72 */   private final int zombievillagerBaseAttack = API.monsterConfig.getInt("Zombievillager.base_attack");
/*   73 */   private final int foxBaseAttack = API.monsterConfig.getInt("Fox.base_attack");
/*   74 */   private final int pandaBaseAttack = API.monsterConfig.getInt("Panda.base_attack");
/*   75 */   private final int beeBaseAttack = API.monsterConfig.getInt("Bee.base_attack");
/*      */   
/*   77 */   private final boolean debugEnabled = API.debugConfig.getBoolean("MonsterAttackingPlayer");
/*      */ 
/*      */   
/*   80 */   private final int defenseScale = API.playerConfig.getInt("Scaling.Player.DefenseScale");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @EventHandler
/*      */   public void monsterAttackingPlayer(EntityDamageByEntityEvent e) {
/*   87 */     int worldEnabled = API.worldConfig.getInt(e.getEntity().getLocation().getWorld().getName());
/*   88 */     if (worldEnabled != -1 && !e.getEntity().hasMetadata("NPC")) {
/*   89 */       Entity defender = e.getEntity();
/*   90 */       Entity attacker = e.getDamager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*   96 */       EntityType attackerType = attacker.getType();
/*   97 */       EntityType defenderType = defender.getType();
/*   98 */       if (defenderType == EntityType.PLAYER) {
/*      */         int monster_attack, damage_done; Arrow arrow; int helmet, chestplate, leggings, boots, armorTotal; String attackerUUID; PlayerData personAttacking; int personAttacking_Attack, weaponDamage; ThrownPotion potion;
/*  100 */         String defenderUUID = defender.getUniqueId().toString();
/*  101 */         PlayerData pd = (PlayerData)API.Players.get(defenderUUID);
/*  102 */         Player defendingPlayer = (Player)defender;
/*  103 */         int player_defense = pd.getData().getInt("Level") * this.defenseScale;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  113 */         int helmBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getHelmet());
/*  114 */         int chestBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getChestplate());
/*  115 */         int leggingBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getLeggings());
/*  116 */         int bootBonus = nameSpaceKey.getItemDefenseFromContainer(defendingPlayer.getInventory().getBoots());
/*  117 */         int totalBonus = helmBonus + chestBonus + leggingBonus + bootBonus;
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  122 */           helmet = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getHelmet().getType().toString().toUpperCase()).getValue();
/*      */           try {
/*  124 */             Collection<AttributeModifier> helmetEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  125 */             int helmetBonus = (int)((AttributeModifier)helmetEnchantAttribute.iterator().next()).getAmount();
/*  126 */             helmet += helmetBonus;
/*      */           }
/*  128 */           catch (NullPointerException nullPointerException) {}
/*      */         
/*      */         }
/*  131 */         catch (NullPointerException error) {
/*      */           
/*  133 */           helmet = 0;
/*      */         } 
/*      */         
/*      */         try {
/*  137 */           chestplate = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getChestplate().getType().toString().toUpperCase()).getValue();
/*      */ 
/*      */           
/*      */           try {
/*  141 */             Collection<AttributeModifier> chestplateEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  142 */             int chestplateBonus = (int)((AttributeModifier)chestplateEnchantAttribute.iterator().next()).getAmount();
/*  143 */             chestplate += chestplateBonus;
/*      */           }
/*  145 */           catch (NullPointerException nullPointerException) {}
/*      */ 
/*      */         
/*      */         }
/*  149 */         catch (NullPointerException error) {
/*      */           
/*  151 */           chestplate = 0;
/*      */         } 
/*      */         try {
/*  154 */           leggings = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getLeggings().getType().toString().toUpperCase()).getValue();
/*      */           
/*      */           try {
/*  157 */             Collection<AttributeModifier> leggingsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  158 */             int leggingsBonus = (int)((AttributeModifier)leggingsEnchantAttribute.iterator().next()).getAmount();
/*  159 */             leggings += leggingsBonus;
/*      */           }
/*  161 */           catch (NullPointerException nullPointerException) {}
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  166 */         catch (NullPointerException error) {
/*      */           
/*  168 */           leggings = 0;
/*      */         } 
/*      */         try {
/*  171 */           boots = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getBoots().getType().toString().toUpperCase()).getValue();
/*      */           
/*      */           try {
/*  174 */             Collection<AttributeModifier> bootsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  175 */             int bootsBonus = (int)((AttributeModifier)bootsEnchantAttribute.iterator().next()).getAmount();
/*  176 */             boots += bootsBonus;
/*      */           }
/*  178 */           catch (NullPointerException nullPointerException) {}
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  183 */         catch (NullPointerException error) {
/*  184 */           boots = 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  190 */         switch (attackerType) {
/*      */ 
/*      */           
/*      */           case PLAYER:
/*  194 */             attackerUUID = attacker.getUniqueId().toString();
/*  195 */             personAttacking = (PlayerData)API.Players.get(attackerUUID);
/*  196 */             personAttacking_Attack = personAttacking.getData().getInt("Attack");
/*  197 */             weaponDamage = 0;
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/*  202 */               Player human = (Player)attacker;
/*  203 */               if (!human.getInventory().getItemInMainHand().toString().contains("AIR")) {
/*      */                 
/*      */                 try {
/*  206 */                   weaponDamage = Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue();
/*      */                 }
/*  208 */                 catch (IllegalArgumentException error) {
/*  209 */                   weaponDamage = 0;
/*  210 */                   if (this.debugEnabled) {
/*  211 */                     System.out.println("weapon damaged set to 0");
/*      */                   
/*      */                   }
/*      */                 
/*      */                 }
/*      */                 finally {
/*      */                   
/*  218 */                   if (this.debugEnabled) {
/*  219 */                     System.out.println("Finally statement happening");
/*      */                   }
/*      */ 
/*      */                   
/*  223 */                   if (human.getInventory().getItemInMainHand().getItemMeta().hasAttributeModifiers()) {
/*  224 */                     if (this.debugEnabled) {
/*  225 */                       System.out.println("Item has bonus stats for main attack");
/*      */                     }
/*  227 */                     Collection<AttributeModifier> weaponStats = human.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
/*  228 */                     int weaponBonus = (int)((AttributeModifier)weaponStats.iterator().next()).getAmount();
/*      */                     
/*  230 */                     weaponDamage += weaponBonus;
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*  248 */               else if (this.debugEnabled) {
/*  249 */                 System.err.print("empty hands");
/*      */               }
/*      */             
/*  252 */             } catch (IllegalArgumentException error) {
/*      */               
/*  254 */               if (this.debugEnabled) {
/*  255 */                 System.err.println(error);
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/*  260 */             damage_done = personAttacking_Attack + weaponDamage - player_defense;
/*  261 */             if (damage_done < 1) {
/*  262 */               if (this.debugEnabled) {
/*  263 */                 System.out.println("Cant do less then 1 damage!");
/*      */               }
/*  265 */               damage_done = 1;
/*      */             } 
/*      */ 
/*      */             
/*  269 */             e.setDamage((personAttacking_Attack + weaponDamage - player_defense));
/*      */             
/*  271 */             if (this.debugEnabled) {
/*  272 */               System.out.println("Personattacking_Attack: " + personAttacking_Attack);
/*  273 */               System.out.println("WeaponDamage: " + weaponDamage);
/*  274 */               System.out.println("[PVP]: " + attacker.getName() + " " + (personAttacking_Attack + weaponDamage) + " -> " + defender
/*  275 */                   .getName() + " " + player_defense);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case ZOMBIE:
/*  283 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.zombieBaseAttack;
/*  284 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  285 */             damage_done = monster_attack - player_defense + armorTotal;
/*  286 */             if (damage_done < 1) {
/*  287 */               damage_done = 1;
/*      */             }
/*  289 */             e.setDamage(damage_done);
/*  290 */             if (this.debugEnabled) {
/*  291 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  292 */               System.out.println("[Monster Attack: " + monster_attack);
/*  293 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  294 */               int totalDamage = armorTotal + player_defense;
/*  295 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case WOLF:
/*  301 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.wolfBaseAttack;
/*      */             
/*  303 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  304 */             damage_done = monster_attack - player_defense + armorTotal;
/*  305 */             if (damage_done < 1) {
/*  306 */               damage_done = 1;
/*      */             }
/*  308 */             e.setDamage(damage_done);
/*  309 */             if (this.debugEnabled) {
/*  310 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  311 */               System.out.println("[Monster Attack: " + monster_attack);
/*  312 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  313 */               int totalDamage = armorTotal + player_defense;
/*  314 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SQUID:
/*  320 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.squidBaseAttack;
/*  321 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  322 */             damage_done = monster_attack - player_defense + armorTotal;
/*  323 */             if (damage_done < 1) {
/*  324 */               damage_done = 1;
/*      */             }
/*  326 */             e.setDamage(damage_done);
/*  327 */             if (this.debugEnabled) {
/*  328 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  329 */               System.out.println("[Monster Attack: " + monster_attack);
/*  330 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  331 */               int totalDamage = armorTotal + player_defense;
/*  332 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SNOWMAN:
/*  338 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.snowmanBaseAttack;
/*  339 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  340 */             damage_done = monster_attack - player_defense + armorTotal;
/*  341 */             if (damage_done < 1) {
/*  342 */               damage_done = 1;
/*      */             }
/*  344 */             e.setDamage(damage_done);
/*  345 */             if (this.debugEnabled) {
/*  346 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  347 */               System.out.println("[Monster Attack: " + monster_attack);
/*  348 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  349 */               int totalDamage = armorTotal + player_defense;
/*  350 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SLIME:
/*  356 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.slimeBaseAttack;
/*  357 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  358 */             damage_done = monster_attack - player_defense + armorTotal;
/*  359 */             if (damage_done < 1) {
/*  360 */               damage_done = 1;
/*      */             }
/*  362 */             e.setDamage(damage_done);
/*  363 */             if (this.debugEnabled) {
/*  364 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  365 */               System.out.println("[Monster Attack: " + monster_attack);
/*  366 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  367 */               int totalDamage = armorTotal + player_defense;
/*  368 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SILVERFISH:
/*  374 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.silverfishBaseAttack;
/*      */             
/*  376 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  377 */             damage_done = monster_attack - player_defense + armorTotal;
/*  378 */             if (damage_done < 1) {
/*  379 */               damage_done = 1;
/*      */             }
/*  381 */             e.setDamage(damage_done);
/*  382 */             if (this.debugEnabled) {
/*  383 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  384 */               System.out.println("[Monster Attack: " + monster_attack);
/*  385 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  386 */               int totalDamage = armorTotal + player_defense;
/*  387 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SHEEP:
/*  393 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.sheepBaseAttack;
/*  394 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  395 */             damage_done = monster_attack - player_defense + armorTotal;
/*  396 */             if (damage_done < 1) {
/*  397 */               damage_done = 1;
/*      */             }
/*  399 */             e.setDamage(damage_done);
/*  400 */             if (this.debugEnabled) {
/*  401 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  402 */               System.out.println("[Monster Attack: " + monster_attack);
/*  403 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  404 */               int totalDamage = armorTotal + player_defense;
/*  405 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case RABBIT:
/*  411 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.rabbitBaseAttack;
/*  412 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  413 */             damage_done = monster_attack - player_defense + armorTotal;
/*  414 */             if (damage_done < 1) {
/*  415 */               damage_done = 1;
/*      */             }
/*  417 */             e.setDamage(damage_done);
/*  418 */             if (this.debugEnabled) {
/*  419 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  420 */               System.out.println("[Monster Attack: " + monster_attack);
/*  421 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  422 */               int totalDamage = armorTotal + player_defense;
/*  423 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case PIG_ZOMBIE:
/*  429 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pigzombieBaseAttack;
/*  430 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  431 */             damage_done = monster_attack - player_defense + armorTotal;
/*  432 */             if (damage_done < 1) {
/*  433 */               damage_done = 1;
/*      */             }
/*  435 */             e.setDamage(damage_done);
/*  436 */             if (this.debugEnabled) {
/*  437 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  438 */               System.out.println("[Monster Attack: " + monster_attack);
/*  439 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  440 */               int totalDamage = armorTotal + player_defense;
/*  441 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case PIG:
/*  447 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pigBaseAttack;
/*  448 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  449 */             damage_done = monster_attack - player_defense + armorTotal;
/*  450 */             if (damage_done < 1) {
/*  451 */               damage_done = 1;
/*      */             }
/*  453 */             e.setDamage(damage_done);
/*  454 */             if (this.debugEnabled) {
/*  455 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  456 */               System.out.println("[Monster Attack: " + monster_attack);
/*  457 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  458 */               int totalDamage = armorTotal + player_defense;
/*  459 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case MUSHROOM_COW:
/*  465 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.mushroomcowBaseAttack;
/*  466 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  467 */             damage_done = monster_attack - player_defense + armorTotal;
/*  468 */             if (damage_done < 1) {
/*  469 */               damage_done = 1;
/*      */             }
/*  471 */             e.setDamage(damage_done);
/*  472 */             if (this.debugEnabled) {
/*  473 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  474 */               System.out.println("[Monster Attack: " + monster_attack);
/*  475 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  476 */               int totalDamage = armorTotal + player_defense;
/*  477 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case MAGMA_CUBE:
/*  483 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.magmacubeBaseAttack;
/*  484 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  485 */             damage_done = monster_attack - player_defense + armorTotal;
/*  486 */             if (damage_done < 1) {
/*  487 */               damage_done = 1;
/*      */             }
/*  489 */             e.setDamage(damage_done);
/*  490 */             if (this.debugEnabled) {
/*  491 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  492 */               System.out.println("[Monster Attack: " + monster_attack);
/*  493 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  494 */               int totalDamage = armorTotal + player_defense;
/*  495 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case GUARDIAN:
/*  501 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.guardianBaseAttack;
/*  502 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  503 */             damage_done = monster_attack - player_defense + armorTotal;
/*  504 */             if (damage_done < 1) {
/*  505 */               damage_done = 1;
/*      */             }
/*  507 */             e.setDamage(damage_done);
/*  508 */             if (this.debugEnabled) {
/*  509 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  510 */               System.out.println("[Monster Attack: " + monster_attack);
/*  511 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  512 */               int totalDamage = armorTotal + player_defense;
/*  513 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case GIANT:
/*  519 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.giantBaseAttack;
/*  520 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  521 */             damage_done = monster_attack - player_defense + armorTotal;
/*  522 */             if (damage_done < 1) {
/*  523 */               damage_done = 1;
/*      */             }
/*  525 */             e.setDamage(damage_done);
/*  526 */             if (this.debugEnabled) {
/*  527 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  528 */               System.out.println("[Monster Attack: " + monster_attack);
/*  529 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  530 */               int totalDamage = armorTotal + player_defense;
/*  531 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case GHAST:
/*  537 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.ghastBaseAttack;
/*  538 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  539 */             damage_done = monster_attack - player_defense + armorTotal;
/*  540 */             if (damage_done < 1) {
/*  541 */               damage_done = 1;
/*      */             }
/*  543 */             e.setDamage(damage_done);
/*  544 */             if (this.debugEnabled) {
/*  545 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  546 */               System.out.println("[Monster Attack: " + monster_attack);
/*  547 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  548 */               int totalDamage = armorTotal + player_defense;
/*  549 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ENDERMITE:
/*  555 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.endermiteBaseAttack;
/*  556 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  557 */             damage_done = monster_attack - player_defense + armorTotal;
/*  558 */             if (damage_done < 1) {
/*  559 */               damage_done = 1;
/*      */             }
/*  561 */             e.setDamage(damage_done);
/*  562 */             if (this.debugEnabled) {
/*  563 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  564 */               System.out.println("[Monster Attack: " + monster_attack);
/*  565 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  566 */               int totalDamage = armorTotal + player_defense;
/*  567 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ENDERMAN:
/*  573 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.endermanBaseAttack;
/*  574 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  575 */             damage_done = monster_attack - player_defense + armorTotal;
/*  576 */             if (damage_done < 1) {
/*  577 */               damage_done = 1;
/*      */             }
/*  579 */             e.setDamage(damage_done);
/*  580 */             if (this.debugEnabled) {
/*  581 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  582 */               System.out.println("[Monster Attack: " + monster_attack);
/*  583 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  584 */               int totalDefense = armorTotal + player_defense;
/*  585 */               System.out.println("Armor Total: " + totalDefense);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ENDER_DRAGON:
/*  591 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.enderdragonBaseAttack;
/*  592 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  593 */             damage_done = monster_attack - player_defense + armorTotal;
/*  594 */             if (damage_done < 1) {
/*  595 */               damage_done = 1;
/*      */             }
/*  597 */             e.setDamage(damage_done);
/*  598 */             if (this.debugEnabled) {
/*  599 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  600 */               System.out.println("[Monster Attack: " + monster_attack);
/*  601 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  602 */               int totalDefense = armorTotal + player_defense;
/*  603 */               System.out.println("Armor Total: " + totalDefense);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case CREEPER:
/*  609 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.creeperBaseAttack;
/*  610 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  611 */             damage_done = monster_attack - player_defense + armorTotal;
/*  612 */             if (damage_done < 1) {
/*  613 */               damage_done = 1;
/*      */             }
/*  615 */             e.setDamage(damage_done);
/*  616 */             if (this.debugEnabled) {
/*  617 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  618 */               System.out.println("[Monster Attack: " + monster_attack);
/*  619 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  620 */               int totalDamage = armorTotal + player_defense;
/*  621 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case COW:
/*  627 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.cowBaseAttack;
/*  628 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  629 */             damage_done = monster_attack - player_defense + armorTotal;
/*  630 */             if (damage_done < 1) {
/*  631 */               damage_done = 1;
/*      */             }
/*  633 */             e.setDamage(damage_done);
/*  634 */             if (this.debugEnabled) {
/*  635 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  636 */               System.out.println("[Monster Attack: " + monster_attack);
/*  637 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  638 */               int totalDamage = armorTotal + player_defense;
/*  639 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case CHICKEN:
/*  645 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.chickenBaseAttack;
/*  646 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  647 */             damage_done = monster_attack - player_defense + armorTotal;
/*  648 */             if (damage_done < 1) {
/*  649 */               damage_done = 1;
/*      */             }
/*  651 */             e.setDamage(damage_done);
/*  652 */             if (this.debugEnabled) {
/*  653 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  654 */               System.out.println("[Monster Attack: " + monster_attack);
/*  655 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  656 */               int totalDamage = armorTotal + player_defense;
/*  657 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case CAVE_SPIDER:
/*  663 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.cavespiderBaseAttack;
/*      */             
/*  665 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  666 */             damage_done = monster_attack - player_defense + armorTotal;
/*  667 */             if (damage_done < 1) {
/*  668 */               damage_done = 1;
/*      */             }
/*  670 */             e.setDamage(damage_done);
/*  671 */             if (this.debugEnabled) {
/*  672 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  673 */               System.out.println("[Monster Attack: " + monster_attack);
/*  674 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  675 */               int totalDamage = armorTotal + player_defense;
/*  676 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case SPIDER:
/*  682 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.spiderBaseAttack;
/*      */             
/*  684 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  685 */             damage_done = monster_attack - player_defense + armorTotal;
/*  686 */             if (damage_done < 1) {
/*  687 */               damage_done = 1;
/*      */             }
/*  689 */             e.setDamage(damage_done);
/*  690 */             if (this.debugEnabled) {
/*  691 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  692 */               System.out.println("[Monster Attack: " + monster_attack);
/*  693 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  694 */               int totalDamage = armorTotal + player_defense;
/*  695 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case WITHER_SKELETON:
/*  701 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.witherSkeletonBaseAttack;
/*      */             
/*  703 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  704 */             damage_done = monster_attack - player_defense + armorTotal;
/*  705 */             if (damage_done < 1) {
/*  706 */               damage_done = 1;
/*      */             }
/*  708 */             e.setDamage(damage_done);
/*  709 */             if (this.debugEnabled) {
/*  710 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  711 */               System.out.println("[Monster Attack: " + monster_attack);
/*  712 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  713 */               int totalDamage = armorTotal + player_defense;
/*  714 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case BLAZE:
/*  720 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.blazeBaseAttack;
/*      */             
/*  722 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  723 */             damage_done = monster_attack - player_defense + armorTotal;
/*  724 */             if (damage_done < 1) {
/*  725 */               damage_done = 1;
/*      */             }
/*  727 */             e.setDamage(damage_done);
/*  728 */             if (this.debugEnabled) {
/*  729 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  730 */               System.out.println("[Monster Attack: " + monster_attack);
/*  731 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  732 */               int totalDamage = armorTotal + player_defense;
/*  733 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case PILLAGER:
/*  739 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pillagerBaseAttack;
/*      */             
/*  741 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  742 */             damage_done = monster_attack - player_defense + armorTotal;
/*  743 */             if (damage_done < 1) {
/*  744 */               damage_done = 1;
/*      */             }
/*  746 */             e.setDamage(damage_done);
/*  747 */             if (this.debugEnabled) {
/*  748 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  749 */               System.out.println("[Monster Attack: " + monster_attack);
/*  750 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  751 */               int totalDamage = armorTotal + player_defense;
/*  752 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ILLUSIONER:
/*  758 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.illusionerBaseAttack;
/*      */             
/*  760 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  761 */             damage_done = monster_attack - player_defense + armorTotal;
/*  762 */             if (damage_done < 1) {
/*  763 */               damage_done = 1;
/*      */             }
/*  765 */             e.setDamage(damage_done);
/*  766 */             if (this.debugEnabled) {
/*  767 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  768 */               System.out.println("[Monster Attack: " + monster_attack);
/*  769 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  770 */               int totalDamage = armorTotal + player_defense;
/*  771 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case EVOKER:
/*  777 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.evokerBaseAttack;
/*      */             
/*      */             try {
/*  780 */               helmet = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getHelmet().getType().toString().toUpperCase()).getValue();
/*  781 */               Collection<AttributeModifier> helmetEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  782 */               int helmetBonus = (int)((AttributeModifier)helmetEnchantAttribute.iterator().next()).getAmount();
/*  783 */               helmet += helmetBonus;
/*  784 */             } catch (NullPointerException error) {
/*  785 */               helmet = 0;
/*      */             } 
/*      */             try {
/*  788 */               chestplate = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getChestplate().getType().toString().toUpperCase()).getValue();
/*  789 */               Collection<AttributeModifier> chestplateEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  790 */               int chestplateBonus = (int)((AttributeModifier)chestplateEnchantAttribute.iterator().next()).getAmount();
/*  791 */               chestplate += chestplateBonus;
/*  792 */             } catch (NullPointerException error) {
/*  793 */               chestplate = 0;
/*      */             } 
/*      */             try {
/*  796 */               leggings = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getLeggings().getType().toString().toUpperCase()).getValue();
/*  797 */               Collection<AttributeModifier> leggingsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  798 */               int leggingsBonus = (int)((AttributeModifier)leggingsEnchantAttribute.iterator().next()).getAmount();
/*  799 */               leggings += leggingsBonus;
/*  800 */             } catch (NullPointerException error) {
/*  801 */               leggings = 0;
/*      */             } 
/*      */             try {
/*  804 */               boots = Armor_Defense.Armor_Defenses.valueOf(defendingPlayer.getInventory().getBoots().getType().toString().toUpperCase()).getValue();
/*  805 */               Collection<AttributeModifier> bootsEnchantAttribute = defendingPlayer.getInventory().getHelmet().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ARMOR);
/*  806 */               int bootsBonus = (int)((AttributeModifier)bootsEnchantAttribute.iterator().next()).getAmount();
/*  807 */               boots += bootsBonus;
/*  808 */             } catch (NullPointerException error) {
/*  809 */               boots = 0;
/*      */             } 
/*  811 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  812 */             damage_done = monster_attack - player_defense + armorTotal;
/*  813 */             if (damage_done < 1) {
/*  814 */               damage_done = 1;
/*      */             }
/*  816 */             e.setDamage(damage_done);
/*  817 */             if (this.debugEnabled) {
/*  818 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  819 */               System.out.println("[Monster Attack: " + monster_attack);
/*  820 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  821 */               int totalDamage = armorTotal + player_defense;
/*  822 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case BAT:
/*  828 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.batBaseAttack;
/*      */             
/*  830 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  831 */             damage_done = monster_attack - player_defense + armorTotal;
/*  832 */             if (damage_done < 1) {
/*  833 */               damage_done = 1;
/*      */             }
/*  835 */             e.setDamage(damage_done);
/*  836 */             if (this.debugEnabled) {
/*  837 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  838 */               System.out.println("[Monster Attack: " + monster_attack);
/*  839 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  840 */               int totalDamage = armorTotal + player_defense;
/*  841 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case RAVAGER:
/*  847 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.ravagerBaseAttack;
/*      */             
/*  849 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  850 */             damage_done = monster_attack - player_defense + armorTotal;
/*  851 */             if (damage_done < 1) {
/*  852 */               damage_done = 1;
/*      */             }
/*  854 */             e.setDamage(damage_done);
/*  855 */             if (this.debugEnabled) {
/*  856 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  857 */               System.out.println("[Monster Attack: " + monster_attack);
/*  858 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  859 */               int totalDamage = armorTotal + player_defense;
/*  860 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case DROWNED:
/*  866 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.drownedBaseAttack;
/*      */             
/*  868 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  869 */             damage_done = monster_attack - player_defense + armorTotal;
/*  870 */             if (damage_done < 1) {
/*  871 */               damage_done = 1;
/*      */             }
/*  873 */             e.setDamage(damage_done);
/*  874 */             if (this.debugEnabled) {
/*  875 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  876 */               System.out.println("[Monster Attack: " + monster_attack);
/*  877 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  878 */               int totalDamage = armorTotal + player_defense;
/*  879 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case VEX:
/*  886 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.vexBaseAttack;
/*      */             
/*  888 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  889 */             damage_done = monster_attack - player_defense + armorTotal;
/*  890 */             if (damage_done < 1) {
/*  891 */               damage_done = 1;
/*      */             }
/*  893 */             e.setDamage(damage_done);
/*  894 */             if (this.debugEnabled) {
/*  895 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  896 */               System.out.println("[Monster Attack: " + monster_attack);
/*  897 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  898 */               int totalDamage = armorTotal + player_defense;
/*  899 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case VINDICATOR:
/*  906 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.vindicatorBaseAttack;
/*      */             
/*  908 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  909 */             damage_done = monster_attack - player_defense + armorTotal;
/*  910 */             if (damage_done < 1) {
/*  911 */               damage_done = 1;
/*      */             }
/*  913 */             e.setDamage(damage_done);
/*  914 */             if (this.debugEnabled) {
/*  915 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  916 */               System.out.println("[Monster Attack: " + monster_attack);
/*  917 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  918 */               int totalDamage = armorTotal + player_defense;
/*  919 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ZOMBIE_VILLAGER:
/*  925 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.zombievillagerBaseAttack;
/*      */             
/*  927 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  928 */             damage_done = monster_attack - player_defense + armorTotal;
/*  929 */             if (damage_done < 1) {
/*  930 */               damage_done = 1;
/*      */             }
/*  932 */             e.setDamage(damage_done);
/*  933 */             if (this.debugEnabled) {
/*  934 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  935 */               System.out.println("[Monster Attack: " + monster_attack);
/*  936 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  937 */               int totalDamage = armorTotal + player_defense;
/*  938 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case PANDA:
/*  944 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.pandaBaseAttack;
/*      */             
/*  946 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  947 */             damage_done = monster_attack - player_defense + armorTotal;
/*  948 */             if (damage_done < 1) {
/*  949 */               damage_done = 1;
/*      */             }
/*  951 */             e.setDamage(damage_done);
/*  952 */             if (this.debugEnabled) {
/*  953 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  954 */               System.out.println("[Monster Attack: " + monster_attack);
/*  955 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  956 */               int totalDamage = armorTotal + player_defense;
/*  957 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case FOX:
/*  964 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.foxBaseAttack;
/*      */             
/*  966 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  967 */             damage_done = monster_attack - player_defense + armorTotal;
/*  968 */             if (damage_done < 1) {
/*  969 */               damage_done = 1;
/*      */             }
/*  971 */             e.setDamage(damage_done);
/*  972 */             if (this.debugEnabled) {
/*  973 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  974 */               System.out.println("[Monster Attack: " + monster_attack);
/*  975 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  976 */               int totalDamage = armorTotal + player_defense;
/*  977 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case BEE:
/*  983 */             monster_attack = nameSpaceKey.getMonsterLevelFromContainer(attacker) * this.beeBaseAttack;
/*      */             
/*  985 */             armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/*  986 */             damage_done = monster_attack - player_defense + armorTotal;
/*  987 */             if (damage_done < 1) {
/*  988 */               damage_done = 1;
/*      */             }
/*  990 */             e.setDamage(damage_done);
/*  991 */             if (this.debugEnabled) {
/*  992 */               System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/*  993 */               System.out.println("[Monster Attack: " + monster_attack);
/*  994 */               System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/*  995 */               int totalDamage = armorTotal + player_defense;
/*  996 */               System.out.println("Armor Total: " + totalDamage);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case ARROW:
/* 1002 */             arrow = (Arrow)attacker;
/* 1003 */             if (arrow.getShooter() instanceof org.bukkit.entity.Skeleton) {
/*      */               
/* 1005 */               monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)arrow.getShooter()) * this.skeletonBaseAttack;
/*      */               
/* 1007 */               armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/* 1008 */               damage_done = monster_attack - player_defense + armorTotal;
/* 1009 */               if (damage_done < 1) {
/* 1010 */                 damage_done = 1;
/*      */               }
/* 1012 */               e.setDamage(damage_done);
/* 1013 */               if (this.debugEnabled) {
/* 1014 */                 System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/* 1015 */                 System.out.println("[Monster Attack: " + monster_attack);
/* 1016 */                 System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/* 1017 */                 int totalDamage = armorTotal + player_defense;
/* 1018 */                 System.out.println("Armor Total: " + totalDamage);
/*      */               } 
/*      */             } 
/*      */             break;
/*      */           
/*      */           case SPLASH_POTION:
/* 1024 */             potion = (ThrownPotion)attacker;
/* 1025 */             if (potion.getShooter() instanceof org.bukkit.entity.Witch) {
/*      */               
/* 1027 */               monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)potion.getShooter()) * this.witchBaseAttack;
/*      */               
/* 1029 */               armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/* 1030 */               damage_done = monster_attack - player_defense + armorTotal;
/* 1031 */               if (damage_done < 1) {
/* 1032 */                 damage_done = 1;
/*      */               }
/* 1034 */               e.setDamage(damage_done);
/* 1035 */               if (this.debugEnabled) {
/* 1036 */                 System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/* 1037 */                 System.out.println("[Monster Attack: " + monster_attack);
/* 1038 */                 System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/* 1039 */                 int totalDamage = armorTotal + player_defense;
/* 1040 */                 System.out.println("Armor Total: " + totalDamage);
/*      */               } 
/*      */             } 
/*      */             break;
/*      */           case SHULKER_BULLET:
/* 1045 */             arrow = (Arrow)attacker;
/* 1046 */             if (arrow.getShooter() instanceof org.bukkit.entity.Shulker) {
/*      */ 
/*      */               
/* 1049 */               monster_attack = nameSpaceKey.getMonsterLevelFromContainer((Entity)arrow.getShooter()) * this.shulkerBaseAttack;
/*      */               
/* 1051 */               armorTotal = helmet + chestplate + leggings + boots + totalBonus;
/* 1052 */               damage_done = monster_attack - player_defense + armorTotal;
/* 1053 */               if (damage_done < 1) {
/* 1054 */                 damage_done = 1;
/*      */               }
/* 1056 */               e.setDamage(damage_done);
/* 1057 */               if (this.debugEnabled) {
/* 1058 */                 System.out.println("[MAP]: " + attacker.getCustomName() + " -> " + defender.getName() + " " + player_defense);
/* 1059 */                 System.out.println("[Monster Attack: " + monster_attack);
/* 1060 */                 System.out.println("nameSpaceKeyValue:" + nameSpaceKey.getMonsterLevelFromContainer(attacker));
/* 1061 */                 int totalDamage = armorTotal + player_defense;
/* 1062 */                 System.out.println("Armor Total: " + totalDamage);
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\MonsterCombatRelated\MonsterLevelsDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */