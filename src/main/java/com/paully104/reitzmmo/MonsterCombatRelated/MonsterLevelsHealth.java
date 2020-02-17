/*      */ package com.paully104.reitzmmo.MonsterCombatRelated;
/*      */ 
/*      */ import com.paully104.reitzmmo.ConfigFiles.API;
/*      */ import com.paully104.reitzmmo.ItemData.nameSpaceKey;
/*      */ import java.util.Objects;
/*      */ import java.util.Random;
/*      */ import org.bukkit.ChatColor;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.attribute.Attribute;
/*      */ import org.bukkit.attribute.AttributeInstance;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.Zombie;
/*      */ import org.bukkit.event.EventHandler;
/*      */ import org.bukkit.event.EventPriority;
/*      */ import org.bukkit.event.Listener;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.inventory.EntityEquipment;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ 
/*      */ 
/*      */ public class MonsterLevelsHealth
/*      */   implements Listener
/*      */ {
/*   27 */   private final boolean kingMobsEnabled = API.specialMonsterConfig.getBoolean("kingMobsEnabled");
/*   28 */   private final boolean notoriousMobsEnabled = API.specialMonsterConfig.getBoolean("notoriusMobsEnabled");
/*   29 */   private final boolean devilishMobsEnabled = API.specialMonsterConfig.getBoolean("devilishMobsEnabled");
/*   30 */   private final boolean dumbMobsEnabled = API.specialMonsterConfig.getBoolean("dumbMobsEnabled");
/*      */ 
/*      */   
/*   33 */   private final int kingMobsLV = API.specialMonsterConfig.getInt("kingMobsLVDifference");
/*   34 */   private final int notoriousMobsLV = API.specialMonsterConfig.getInt("notoriousMobsLVDifference");
/*   35 */   private final int devilishMobsLV = API.specialMonsterConfig.getInt("devilishMobsLVDifference");
/*   36 */   private final int dumbMobsLV = API.specialMonsterConfig.getInt("dumbMobsLVDifference");
/*      */ 
/*      */   
/*   39 */   private final boolean specialMobGlowingEnabled = API.specialMonsterConfig.getBoolean("specialMonsterGlowEnabled");
/*   40 */   private final boolean specialMobSilentEnabled = API.specialMonsterConfig.getBoolean("specialMonsterSilentEnabled");
/*      */ 
/*      */ 
/*      */   
/*   44 */   private final int blocksPerMobLevel = API.monsterConfig.getInt("General.blocks-per-mob-level");
/*      */   
/*   46 */   private final int zombieBaseHP = API.monsterConfig.getInt("Zombie.base_hp");
/*   47 */   private final int wolfBaseHP = API.monsterConfig.getInt("Wolf.base_hp");
/*   48 */   private final int villagerBaseHP = API.monsterConfig.getInt("Villager.base_hp");
/*   49 */   private final int squidBaseHP = API.monsterConfig.getInt("Squid.base_hp");
/*   50 */   private final int spiderBaseHP = API.monsterConfig.getInt("Spider.base_hp");
/*   51 */   private final int snowmanBaseHP = API.monsterConfig.getInt("Snowman.base_hp");
/*   52 */   private final int slimeBaseHP = API.monsterConfig.getInt("Slime.base_hp");
/*   53 */   private final int skeletonBaseHP = API.monsterConfig.getInt("Skeleton.base_hp");
/*   54 */   private final int silverfishBaseHP = API.monsterConfig.getInt("Silverfish.base_hp");
/*   55 */   private final int sheepBaseHP = API.monsterConfig.getInt("Sheep.base_hp");
/*   56 */   private final int rabbitBaseHP = API.monsterConfig.getInt("Rabbit.base_hp");
/*   57 */   private final int pigzombieBaseHP = API.monsterConfig.getInt("Pigzombie.base_hp");
/*   58 */   private final int pigBaseHP = API.monsterConfig.getInt("Pig.base_hp");
/*   59 */   private final int mushroomcowBaseHP = API.monsterConfig.getInt("Mushroomcow.base_hp");
/*   60 */   private final int magmacubeBaseHP = API.monsterConfig.getInt("Magmacube.base_hp");
/*   61 */   private final int guardianBaseHP = API.monsterConfig.getInt("Guardian.base_hp");
/*   62 */   private final int giantBaseHP = API.monsterConfig.getInt("Giant.base_hp");
/*   63 */   private final int ghastBaseHP = API.monsterConfig.getInt("Ghast.base_hp");
/*   64 */   private final int endermiteBaseHP = API.monsterConfig.getInt("Endermite.base_hp");
/*   65 */   private final int endermanBaseHP = API.monsterConfig.getInt("Enderman.base_hp");
/*   66 */   private final int enderdragonBaseHP = API.monsterConfig.getInt("Enderdragon.base_hp");
/*   67 */   private final int creeperBaseHP = API.monsterConfig.getInt("Creeper.base_hp");
/*   68 */   private final int cowBaseHP = API.monsterConfig.getInt("Cow.base_hp");
/*   69 */   private final int chickenBaseHP = API.monsterConfig.getInt("Chicken.base_hp");
/*   70 */   private final int cavespiderBaseHP = API.monsterConfig.getInt("Cavespider.base_hp");
/*   71 */   private final int blazeBaseHP = API.monsterConfig.getInt("Blaze.base_hp");
/*   72 */   private final int witchBaseHP = API.monsterConfig.getInt("Witch.base_hp");
/*   73 */   private final int witherSkeletonBaseHP = API.monsterConfig.getInt("Witherskeleton.base_hp");
/*   74 */   private final int shulkerBaseHP = API.monsterConfig.getInt("Shulker.base_hp");
/*   75 */   private final int pillagerBaseHP = API.monsterConfig.getInt("Pillager.base_hp");
/*   76 */   private final int illusionerBaseHP = API.monsterConfig.getInt("Illusioner.base_hp");
/*   77 */   private final int evokerBaseHP = API.monsterConfig.getInt("Evoker.base_hp");
/*   78 */   private final int ravagerBaseHP = API.monsterConfig.getInt("Ravager.base_hp");
/*   79 */   private final int batBaseHP = API.monsterConfig.getInt("Bat.base_hp");
/*   80 */   private final int drownedBaseHP = API.monsterConfig.getInt("Drowned.base_hp");
/*   81 */   private final int zombievillagerBaseHP = API.monsterConfig.getInt("Zombievillager.base_hp");
/*   82 */   private final int polarBearBaseHP = API.monsterConfig.getInt("Polarbear.base_hp");
/*   83 */   private final int wanderingTraderBaseHP = API.monsterConfig.getInt("Wanderingtrader.base_hp");
/*   84 */   private final int donkeyBaseHP = API.monsterConfig.getInt("Donkey.base_hp");
/*   85 */   private final int llamaBaseHP = API.monsterConfig.getInt("Llama.base_hp");
/*   86 */   private final int salmonBaseHP = API.monsterConfig.getInt("Salmon.base_hp");
/*   87 */   private final int huskBaseHP = API.monsterConfig.getInt("Husk.base_hp");
/*   88 */   private final int vindicatorBaseHP = API.monsterConfig.getInt("Vindicator.base_hp");
/*   89 */   private final int vexBaseHP = API.monsterConfig.getInt("Vex.base_hp");
/*   90 */   private final int foxBaseHP = API.monsterConfig.getInt("Fox.base_hp");
/*   91 */   private final int pandaBaseHP = API.monsterConfig.getInt("Panda.base_hp");
/*   92 */   private final int beeBaseHP = API.monsterConfig.getInt("Bees.base_hp");
/*      */ 
/*      */   
/*   95 */   private final boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");
/*      */   
/*   97 */   private final boolean zombieNameplate = API.monsterConfig.getBoolean("Zombie.nameplates_enabled");
/*   98 */   private final boolean wolfNameplate = API.monsterConfig.getBoolean("Wolf.nameplates_enabled");
/*   99 */   private final boolean villagerNameplate = API.monsterConfig.getBoolean("Villager.nameplates_enabled");
/*  100 */   private final boolean squidNameplate = API.monsterConfig.getBoolean("Squid.nameplates_enabled");
/*  101 */   private final boolean spiderNameplate = API.monsterConfig.getBoolean("Spider.nameplates_enabled");
/*  102 */   private final boolean snowmanNameplate = API.monsterConfig.getBoolean("Snowman.nameplates_enabled");
/*  103 */   private final boolean slimeNameplate = API.monsterConfig.getBoolean("Slime.nameplates_enabled");
/*  104 */   private final boolean skeletonNameplate = API.monsterConfig.getBoolean("Skeleton.nameplates_enabled");
/*  105 */   private final boolean silverfishNameplate = API.monsterConfig.getBoolean("Silverfish.nameplates_enabled");
/*  106 */   private final boolean sheepNameplate = API.monsterConfig.getBoolean("Sheep.nameplates_enabled");
/*  107 */   private final boolean rabbitNameplate = API.monsterConfig.getBoolean("Rabbit.nameplates_enabled");
/*  108 */   private final boolean pigzombieNameplate = API.monsterConfig.getBoolean("Pigzombie.nameplates_enabled");
/*  109 */   private final boolean pigNameplate = API.monsterConfig.getBoolean("Pig.nameplates_enabled");
/*  110 */   private final boolean mushroomcowNameplate = API.monsterConfig.getBoolean("Mushroomcow.nameplates_enabled");
/*  111 */   private final boolean magmacubeNameplate = API.monsterConfig.getBoolean("Magmacube.nameplates_enabled");
/*  112 */   private final boolean guardianNameplate = API.monsterConfig.getBoolean("Guardian.nameplates_enabled");
/*  113 */   private final boolean giantNameplate = API.monsterConfig.getBoolean("Giant.nameplates_enabled");
/*  114 */   private final boolean ghastNameplate = API.monsterConfig.getBoolean("Ghast.nameplates_enabled");
/*  115 */   private final boolean endermiteNameplate = API.monsterConfig.getBoolean("Endermite.nameplates_enabled");
/*  116 */   private final boolean endermanNameplate = API.monsterConfig.getBoolean("Enderman.nameplates_enabled");
/*  117 */   private final boolean enderdragonNameplate = API.monsterConfig.getBoolean("Enderdragon.nameplates_enabled");
/*  118 */   private final boolean creeperNameplate = API.monsterConfig.getBoolean("Creeper.nameplates_enabled");
/*  119 */   private final boolean cowNameplate = API.monsterConfig.getBoolean("Cow.nameplates_enabled");
/*  120 */   private final boolean chickenNameplate = API.monsterConfig.getBoolean("Chicken.nameplates_enabled");
/*  121 */   private final boolean cavespiderNameplate = API.monsterConfig.getBoolean("Cavespider.nameplates_enabled");
/*  122 */   private final boolean blazeNameplate = API.monsterConfig.getBoolean("Blaze.nameplates_enabled");
/*  123 */   private final boolean witchNameplate = API.monsterConfig.getBoolean("Witch.nameplates_enabled");
/*  124 */   private final boolean witherSkeletonNameplate = API.monsterConfig.getBoolean("Witherskeleton.nameplates_enabled");
/*  125 */   private final boolean shulkerNameplate = API.monsterConfig.getBoolean("Shulker.nameplates_enabled");
/*  126 */   private final boolean pillagerNameplate = API.monsterConfig.getBoolean("Pillager.nameplates_enabled");
/*  127 */   private final boolean illusionerNameplate = API.monsterConfig.getBoolean("Illusioner.nameplates_enabled");
/*  128 */   private final boolean evokerNameplate = API.monsterConfig.getBoolean("Evoker.nameplates_enabled");
/*  129 */   private final boolean ravagerNameplate = API.monsterConfig.getBoolean("Ravager.nameplates_enabled");
/*  130 */   private final boolean batNameplate = API.monsterConfig.getBoolean("Bat.nameplates_enabled");
/*  131 */   private final boolean drownedNameplate = API.monsterConfig.getBoolean("Drowned.nameplates_enabled");
/*  132 */   private final boolean zombievillagerNameplate = API.monsterConfig.getBoolean("Zombievillager.nameplates_enabled");
/*  133 */   private final boolean polarBearNameplate = API.monsterConfig.getBoolean("Polarbear.nameplates_enabled");
/*  134 */   private final boolean wanderingTraderNameplate = API.monsterConfig.getBoolean("Wanderingtrader.nameplates_enabled");
/*  135 */   private final boolean donkeyNameplate = API.monsterConfig.getBoolean("Donkey.nameplates_enabled");
/*  136 */   private final boolean llamaNameplate = API.monsterConfig.getBoolean("Llama.nameplates_enabled");
/*  137 */   private final boolean salmonNameplate = API.monsterConfig.getBoolean("Salmon.nameplates_enabled");
/*  138 */   private final boolean huskNameplate = API.monsterConfig.getBoolean("Husk.nameplates_enabled");
/*  139 */   private final boolean vindicatorNameplate = API.monsterConfig.getBoolean("Vindicator.nameplates_enabled");
/*  140 */   private final boolean vexNameplate = API.monsterConfig.getBoolean("Vex.nameplates_enabled");
/*  141 */   private final boolean foxNameplate = API.monsterConfig.getBoolean("Fox.nameplates_enabled");
/*  142 */   private final boolean pandaNameplate = API.monsterConfig.getBoolean("Panda.nameplates_enabled");
/*  143 */   private final boolean beeNameplate = API.monsterConfig.getBoolean("Bee.nameplates_enabled");
/*      */ 
/*      */   
/*  146 */   private final int zombieSpeed = API.monsterConfig.getInt("Zombie.speed");
/*  147 */   private final int wolfSpeed = API.monsterConfig.getInt("Wolf.speed");
/*  148 */   private final int villagerSpeed = API.monsterConfig.getInt("Villager.speed");
/*  149 */   private final int squidSpeed = API.monsterConfig.getInt("Squid.speed");
/*  150 */   private final int spiderSpeed = API.monsterConfig.getInt("Spider.speed");
/*  151 */   private final int snowmanSpeed = API.monsterConfig.getInt("Snowman.speed");
/*  152 */   private final int slimeSpeed = API.monsterConfig.getInt("Slime.speed");
/*  153 */   private final int skeletonSpeed = API.monsterConfig.getInt("Skeleton.speed");
/*  154 */   private final int silverfishSpeed = API.monsterConfig.getInt("Silverfish.speed");
/*  155 */   private final int sheepSpeed = API.monsterConfig.getInt("Sheep.speed");
/*  156 */   private final int rabbitSpeed = API.monsterConfig.getInt("Rabbit.speed");
/*  157 */   private final int pigzombieSpeed = API.monsterConfig.getInt("Pigzombie.speed");
/*  158 */   private final int pigSpeed = API.monsterConfig.getInt("Pig.speed");
/*  159 */   private final int mushroomcowSpeed = API.monsterConfig.getInt("Mushroomcow.speed");
/*  160 */   private final int magmacubeSpeed = API.monsterConfig.getInt("Magmacube.speed");
/*  161 */   private final int guardianSpeed = API.monsterConfig.getInt("Guardian.speed");
/*  162 */   private final int giantSpeed = API.monsterConfig.getInt("Giant.speed");
/*  163 */   private final int ghastSpeed = API.monsterConfig.getInt("Ghast.speed");
/*  164 */   private final int endermiteSpeed = API.monsterConfig.getInt("Endermite.speed");
/*  165 */   private final int endermanSpeed = API.monsterConfig.getInt("Enderman.speed");
/*  166 */   private final int enderdragonSpeed = API.monsterConfig.getInt("Enderdragon.speed");
/*  167 */   private final int creeperSpeed = API.monsterConfig.getInt("Creeper.speed");
/*  168 */   private final int cowSpeed = API.monsterConfig.getInt("Cow.speed");
/*  169 */   private final int chickenSpeed = API.monsterConfig.getInt("Chicken.speed");
/*  170 */   private final int cavespiderSpeed = API.monsterConfig.getInt("Cavespider.speed");
/*  171 */   private final int blazeSpeed = API.monsterConfig.getInt("Blaze.speed");
/*  172 */   private final int witchSpeed = API.monsterConfig.getInt("Witch.speed");
/*  173 */   private final int witherSkeletonSpeed = API.monsterConfig.getInt("Witherskeleton.speed");
/*  174 */   private final int shulkerSpeed = API.monsterConfig.getInt("Shulker.speed");
/*  175 */   private final int pillagerSpeed = API.monsterConfig.getInt("Pillager.speed");
/*  176 */   private final int illusionerSpeed = API.monsterConfig.getInt("Illusioner.speed");
/*  177 */   private final int evokerSpeed = API.monsterConfig.getInt("Evoker.speed");
/*  178 */   private final int ravagerSpeed = API.monsterConfig.getInt("Ravager.speed");
/*  179 */   private final int batSpeed = API.monsterConfig.getInt("Bat.speed");
/*  180 */   private final int drownedSpeed = API.monsterConfig.getInt("Drowned.speed");
/*  181 */   private final int zombievillagerSpeed = API.monsterConfig.getInt("Zombievillager.speed");
/*  182 */   private final int polarBearSpeed = API.monsterConfig.getInt("Polarbear.speed");
/*  183 */   private final int wanderingTraderSpeed = API.monsterConfig.getInt("Wanderingtrader.speed");
/*  184 */   private final int donkeySpeed = API.monsterConfig.getInt("Donkey.speed");
/*  185 */   private final int llamaSpeed = API.monsterConfig.getInt("Llama.speed");
/*  186 */   private final int salmonSpeed = API.monsterConfig.getInt("Salmon.speed");
/*  187 */   private final int huskSpeed = API.monsterConfig.getInt("Husk.speed");
/*  188 */   private final int vindicatorSpeed = API.monsterConfig.getInt("Vindicator.speed");
/*  189 */   private final int vexSpeed = API.monsterConfig.getInt("Vex.speed");
/*  190 */   private final int foxSpeed = API.monsterConfig.getInt("Fox.speed");
/*  191 */   private final int pandaSpeed = API.monsterConfig.getInt("Panda.speed");
/*  192 */   private final int beeSpeed = API.monsterConfig.getInt("Bee.speed");
/*      */ 
/*      */   
/*  195 */   private final int zombieMinLevel = API.monsterConfig.getInt("Zombie.min_level");
/*  196 */   private final int wolfMinLevel = API.monsterConfig.getInt("Wolf.min_level");
/*  197 */   private final int villagerMinLevel = API.monsterConfig.getInt("Villager.min_level");
/*  198 */   private final int squidMinLevel = API.monsterConfig.getInt("Squid.min_level");
/*  199 */   private final int spiderMinLevel = API.monsterConfig.getInt("Spider.min_level");
/*  200 */   private final int snowmanMinLevel = API.monsterConfig.getInt("Snowman.min_level");
/*  201 */   private final int slimeMinLevel = API.monsterConfig.getInt("Slime.min_level");
/*  202 */   private final int skeletonMinLevel = API.monsterConfig.getInt("Skeleton.min_level");
/*  203 */   private final int silverfishMinLevel = API.monsterConfig.getInt("Silverfish.min_level");
/*  204 */   private final int sheepMinLevel = API.monsterConfig.getInt("Sheep.min_level");
/*  205 */   private final int rabbitMinLevel = API.monsterConfig.getInt("Rabbit.min_level");
/*  206 */   private final int pigzombieMinLevel = API.monsterConfig.getInt("Pigzombie.min_level");
/*  207 */   private final int pigMinLevel = API.monsterConfig.getInt("Pig.min_level");
/*  208 */   private final int mushroomcowMinLevel = API.monsterConfig.getInt("Mushroomcow.min_level");
/*  209 */   private final int magmacubeMinLevel = API.monsterConfig.getInt("Magmacube.min_level");
/*  210 */   private final int guardianMinLevel = API.monsterConfig.getInt("Guardian.min_level");
/*  211 */   private final int giantMinLevel = API.monsterConfig.getInt("Giant.min_level");
/*  212 */   private final int ghastMinLevel = API.monsterConfig.getInt("Ghast.min_level");
/*  213 */   private final int endermiteMinLevel = API.monsterConfig.getInt("Endermite.min_leveL");
/*  214 */   private final int endermanMinLevel = API.monsterConfig.getInt("Enderman.min_level");
/*  215 */   private final int enderdragonMinLevel = API.monsterConfig.getInt("Enderdragon.min_level");
/*  216 */   private final int creeperMinLevel = API.monsterConfig.getInt("Creeper.min_level");
/*  217 */   private final int cowMinLevel = API.monsterConfig.getInt("Cow.min_level");
/*  218 */   private final int chickenMinLevel = API.monsterConfig.getInt("Chicken.min_level");
/*  219 */   private final int cavespiderMinLevel = API.monsterConfig.getInt("Cavespider.min_level");
/*  220 */   private final int blazeMinLevel = API.monsterConfig.getInt("Blaze.min_level");
/*  221 */   private final int witchMinLevel = API.monsterConfig.getInt("Witch.min_leveL");
/*  222 */   private final int witherSkeletonMinLevel = API.monsterConfig.getInt("Witherskeleton.min_level");
/*  223 */   private final int shulkerMinLevel = API.monsterConfig.getInt("Shulker.min_level");
/*  224 */   private final int pillagerMinLevel = API.monsterConfig.getInt("Pillager.min_level");
/*  225 */   private final int illusionerMinLevel = API.monsterConfig.getInt("Illusioner.min_level");
/*  226 */   private final int evokerMinLevel = API.monsterConfig.getInt("Evoker.min_level");
/*  227 */   private final int ravagerMinLevel = API.monsterConfig.getInt("Ravager.min_level");
/*  228 */   private final int batMinLevel = API.monsterConfig.getInt("Bat.min_level");
/*  229 */   private final int drownedMinLevel = API.monsterConfig.getInt("Drowned.min_level");
/*  230 */   private final int zombievillagerMinLevel = API.monsterConfig.getInt("Zombievillager.min_level");
/*  231 */   private final int polarBearMinLevel = API.monsterConfig.getInt("Polarbear.min_level");
/*  232 */   private final int wanderingTraderMinLevel = API.monsterConfig.getInt("Wanderingtrader.min_level");
/*  233 */   private final int donkeyMinLevel = API.monsterConfig.getInt("Donkey.min_level");
/*  234 */   private final int llamaMinLevel = API.monsterConfig.getInt("Llama.min_level");
/*  235 */   private final int salmonMinLevel = API.monsterConfig.getInt("Salmon.min_level");
/*  236 */   private final int huskMinLevel = API.monsterConfig.getInt("Husk.min_level");
/*  237 */   private final int vindicatorMinLevel = API.monsterConfig.getInt("Vindicator.min_level");
/*  238 */   private final int vexMinLevel = API.monsterConfig.getInt("Vex.min_level");
/*  239 */   private final int foxMinLevel = API.monsterConfig.getInt("Fox.min_level");
/*  240 */   private final int pandaMinLevel = API.monsterConfig.getInt("Panda.min_level");
/*  241 */   private final int beeMinLevel = API.monsterConfig.getInt("Bee.min_level");
/*      */ 
/*      */ 
/*      */   
/*      */   private int calculateDistanceFromSpawn(Location worldSpawn, Location monsterSpawn) {
/*  246 */     float deltaX = (float)(worldSpawn.getX() - monsterSpawn.getX());
/*  247 */     float deltaY = (float)(worldSpawn.getY() - monsterSpawn.getY());
/*  248 */     float deltaZ = (float)(worldSpawn.getZ() - monsterSpawn.getZ());
/*  249 */     float distance = (float)Math.sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ));
/*  250 */     String worldName = ((World)Objects.<World>requireNonNull(monsterSpawn.getWorld())).getName();
/*  251 */     int distance2 = Math.round(distance) / this.blocksPerMobLevel;
/*      */     
/*  253 */     int baseWorldDamage = API.worldConfig.getInt(worldName);
/*      */     
/*  255 */     distance2 += baseWorldDamage;
/*  256 */     if (distance2 < 1)
/*      */     {
/*  258 */       distance2 = 1;
/*      */     }
/*  260 */     return distance2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @EventHandler(priority = EventPriority.HIGHEST)
/*      */   public void applyMonsterLevelOnSpawn(CreatureSpawnEvent e) {
/*  273 */     int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(e.getLocation().getWorld())).getName());
/*  274 */     if (worldEnabled != -1) {
/*      */       String levelColor; int hp; Zombie zombie;
/*  276 */       Location worldSpawn = e.getLocation().getWorld().getSpawnLocation();
/*  277 */       Location monsterSpawn = e.getLocation();
/*  278 */       int distance = calculateDistanceFromSpawn(worldSpawn, monsterSpawn);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  283 */       Random r = new Random();
/*  284 */       int low = 0;
/*  285 */       int high = 100;
/*  286 */       int result = r.nextInt(high - low) + low;
/*  287 */       String mobName = e.getEntityType().toString().substring(0, 1).toUpperCase() + e.getEntityType().toString().toLowerCase().substring(1);
/*  288 */       if (result >= 98 && e.getEntity() instanceof org.bukkit.entity.Monster && this.kingMobsEnabled) {
/*      */         
/*  290 */         distance += this.kingMobsLV;
/*  291 */         if (distance < 1)
/*      */         {
/*  293 */           distance = 1;
/*      */         }
/*  295 */         String str = ChatColor.YELLOW + "[" + distance + "]";
/*  296 */         e.getEntity().setCustomName("King " + mobName + str);
/*  297 */         if (this.specialMobGlowingEnabled) {
/*  298 */           e.getEntity().setGlowing(true);
/*      */         }
/*  300 */         if (this.specialMobSilentEnabled) {
/*  301 */           e.getEntity().setSilent(true);
/*      */         
/*      */         }
/*      */       }
/*  305 */       else if (result >= 90 && e.getEntity() instanceof org.bukkit.entity.Monster && this.notoriousMobsEnabled) {
/*      */         
/*  307 */         distance += this.notoriousMobsLV;
/*  308 */         if (distance < 1)
/*      */         {
/*  310 */           distance = 1;
/*      */         }
/*  312 */         String str = ChatColor.YELLOW + "[" + distance + "]";
/*  313 */         e.getEntity().setCustomName("Notorious " + mobName + str);
/*  314 */         if (this.specialMobGlowingEnabled) {
/*  315 */           e.getEntity().setGlowing(true);
/*      */         }
/*  317 */         if (this.specialMobSilentEnabled) {
/*  318 */           e.getEntity().setSilent(true);
/*      */         
/*      */         }
/*      */       }
/*  322 */       else if (result == 66 && e.getEntity() instanceof org.bukkit.entity.Monster && this.devilishMobsEnabled) {
/*      */         
/*  324 */         distance += this.devilishMobsLV;
/*  325 */         if (distance < 1)
/*      */         {
/*  327 */           distance = 1;
/*      */         }
/*  329 */         String str = ChatColor.YELLOW + "[" + distance + "]";
/*  330 */         e.getEntity().setCustomName("Devilish " + mobName + str);
/*  331 */         if (this.specialMobGlowingEnabled) {
/*  332 */           e.getEntity().setGlowing(true);
/*      */         }
/*  334 */         if (this.specialMobSilentEnabled) {
/*  335 */           e.getEntity().setSilent(true);
/*      */         }
/*      */       }
/*  338 */       else if (result <= 1 && e.getEntity() instanceof org.bukkit.entity.Monster && this.dumbMobsEnabled) {
/*      */ 
/*      */         
/*  341 */         distance += this.dumbMobsLV;
/*  342 */         if (distance < 1)
/*      */         {
/*  344 */           distance = 1;
/*      */         }
/*  346 */         String str = ChatColor.YELLOW + "[" + distance + "]";
/*  347 */         e.getEntity().setCustomName("Dumb " + mobName + str);
/*  348 */         if (this.specialMobGlowingEnabled) {
/*  349 */           e.getEntity().setGlowing(true);
/*      */         }
/*  351 */         if (this.specialMobSilentEnabled) {
/*  352 */           e.getEntity().setSilent(true);
/*      */         }
/*  354 */         ((EntityEquipment)Objects.<EntityEquipment>requireNonNull(e.getEntity().getEquipment())).setHelmet(new ItemStack(Material.BUCKET, 1));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  362 */       switch (e.getEntity().getType()) {
/*      */         case ZOMBIE:
/*  364 */           zombie = (Zombie)e.getEntity();
/*  365 */           if (zombie.isBaby()) {
/*  366 */             int i = distance * this.zombieBaseHP;
/*  367 */             if (i < this.zombieMinLevel * this.zombieBaseHP) {
/*      */               
/*  369 */               i = this.zombieMinLevel * this.zombieBaseHP;
/*  370 */               distance = this.zombieMinLevel;
/*      */             } 
/*  372 */             nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), i);
/*  373 */             ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(i);
/*  374 */             e.getEntity().setHealth(i);
/*  375 */             String str = ChatColor.YELLOW + "[" + distance + "]";
/*  376 */             if (e.getEntity().getCustomName() == null) {
/*  377 */               e.getEntity().setCustomName(mobName + str);
/*      */             }
/*  379 */             ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.zombieSpeed);
/*      */ 
/*      */ 
/*      */             
/*  383 */             if (this.monsterNameplatesEnabled && this.zombieNameplate)
/*      */             {
/*      */               
/*  386 */               e.getEntity().setCustomNameVisible(true);
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/*  391 */           hp = distance * this.zombieBaseHP;
/*  392 */           if (hp < this.zombieMinLevel * this.zombieBaseHP) {
/*      */             
/*  394 */             hp = this.zombieMinLevel * this.zombieBaseHP;
/*  395 */             distance = this.zombieMinLevel;
/*      */           } 
/*  397 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  398 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  399 */           e.getEntity().setHealth(hp);
/*  400 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  401 */           if (e.getEntity().getCustomName() == null) {
/*  402 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*      */           
/*  405 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.zombieSpeed);
/*      */ 
/*      */ 
/*      */           
/*  409 */           if (this.monsterNameplatesEnabled && this.zombieNameplate)
/*      */           {
/*      */             
/*  412 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case WOLF:
/*  418 */           hp = distance * this.wolfBaseHP;
/*  419 */           if (hp < this.wolfMinLevel * this.wolfBaseHP) {
/*      */             
/*  421 */             hp = this.wolfMinLevel * this.wolfBaseHP;
/*  422 */             distance = this.wolfMinLevel;
/*      */           } 
/*  424 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  425 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  426 */           e.getEntity().setHealth(hp);
/*  427 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  428 */           if (e.getEntity().getCustomName() == null) {
/*  429 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  431 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.wolfSpeed);
/*  432 */           if (this.monsterNameplatesEnabled && this.wolfNameplate)
/*      */           {
/*  434 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case FOX:
/*  439 */           hp = distance * this.foxBaseHP;
/*  440 */           if (hp < this.foxMinLevel * this.foxBaseHP) {
/*      */             
/*  442 */             hp = this.foxMinLevel * this.foxBaseHP;
/*  443 */             distance = this.foxMinLevel;
/*      */           } 
/*  445 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  446 */           e.getEntity().setHealth(hp);
/*  447 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  448 */           if (e.getEntity().getCustomName() == null) {
/*  449 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  451 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  452 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.foxSpeed);
/*  453 */           if (this.monsterNameplatesEnabled && this.foxNameplate)
/*      */           {
/*  455 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case PANDA:
/*  460 */           hp = distance * this.pandaBaseHP;
/*  461 */           if (hp < this.pandaMinLevel * this.pandaBaseHP) {
/*      */             
/*  463 */             hp = this.pandaMinLevel * this.pandaBaseHP;
/*  464 */             distance = this.pandaMinLevel;
/*      */           } 
/*  466 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  467 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  468 */           e.getEntity().setHealth(hp);
/*  469 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  470 */           if (e.getEntity().getCustomName() == null) {
/*  471 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  473 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.pandaSpeed);
/*  474 */           if (this.monsterNameplatesEnabled && this.pandaNameplate)
/*      */           {
/*  476 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case VILLAGER:
/*  481 */           hp = distance * this.villagerBaseHP;
/*  482 */           if (hp < this.villagerMinLevel * this.villagerBaseHP) {
/*      */             
/*  484 */             hp = this.villagerMinLevel * this.villagerBaseHP;
/*  485 */             distance = this.villagerMinLevel;
/*      */           } 
/*  487 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  488 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  489 */           e.getEntity().setHealth(hp);
/*  490 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  491 */           if (e.getEntity().getCustomName() == null) {
/*  492 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  494 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.villagerSpeed);
/*  495 */           if (this.monsterNameplatesEnabled && this.villagerNameplate)
/*      */           {
/*  497 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SQUID:
/*  501 */           hp = distance * this.squidBaseHP;
/*  502 */           if (hp < this.squidMinLevel * this.squidBaseHP) {
/*      */             
/*  504 */             hp = this.squidMinLevel * this.squidBaseHP;
/*  505 */             distance = this.squidMinLevel;
/*      */           } 
/*  507 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  508 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  509 */           e.getEntity().setHealth(hp);
/*  510 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  511 */           if (e.getEntity().getCustomName() == null) {
/*  512 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  514 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.squidSpeed);
/*  515 */           if (this.monsterNameplatesEnabled && this.squidNameplate)
/*      */           {
/*  517 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SPIDER:
/*  521 */           hp = distance * this.spiderBaseHP;
/*  522 */           if (hp < this.spiderMinLevel * this.spiderBaseHP) {
/*      */             
/*  524 */             hp = this.spiderMinLevel * this.spiderBaseHP;
/*  525 */             distance = this.spiderMinLevel;
/*      */           } 
/*  527 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  528 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  529 */           e.getEntity().setHealth(hp);
/*  530 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  531 */           if (e.getEntity().getCustomName() == null) {
/*  532 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  534 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.spiderSpeed);
/*  535 */           if (this.monsterNameplatesEnabled && this.spiderNameplate)
/*      */           {
/*  537 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SNOWMAN:
/*  541 */           hp = distance * this.snowmanBaseHP;
/*  542 */           if (hp < this.snowmanMinLevel * this.snowmanBaseHP) {
/*      */             
/*  544 */             hp = this.snowmanMinLevel * this.snowmanBaseHP;
/*  545 */             distance = this.snowmanMinLevel;
/*      */           } 
/*  547 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  548 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  549 */           e.getEntity().setHealth(hp);
/*  550 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  551 */           if (e.getEntity().getCustomName() == null) {
/*  552 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  554 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.snowmanSpeed);
/*  555 */           if (this.monsterNameplatesEnabled && this.snowmanNameplate)
/*      */           {
/*  557 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SLIME:
/*  561 */           hp = distance * this.slimeBaseHP;
/*  562 */           if (hp < this.slimeMinLevel * this.slimeBaseHP) {
/*      */             
/*  564 */             hp = this.slimeMinLevel * this.slimeBaseHP;
/*  565 */             distance = this.slimeMinLevel;
/*      */           } 
/*  567 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  568 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  569 */           e.getEntity().setHealth(hp);
/*  570 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  571 */           if (e.getEntity().getCustomName() == null) {
/*  572 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  574 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.slimeSpeed);
/*  575 */           if (this.monsterNameplatesEnabled && this.slimeNameplate)
/*      */           {
/*  577 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SKELETON:
/*  581 */           hp = distance * this.skeletonBaseHP;
/*  582 */           if (hp < this.skeletonMinLevel * this.skeletonBaseHP) {
/*      */             
/*  584 */             hp = this.skeletonMinLevel * this.skeletonBaseHP;
/*  585 */             distance = this.skeletonMinLevel;
/*      */           } 
/*  587 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  588 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  589 */           e.getEntity().setHealth(hp);
/*  590 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  591 */           if (e.getEntity().getCustomName() == null) {
/*  592 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*      */           
/*  595 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.skeletonSpeed);
/*  596 */           if (this.monsterNameplatesEnabled && this.skeletonNameplate)
/*      */           {
/*  598 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case SILVERFISH:
/*  603 */           hp = distance * this.silverfishBaseHP;
/*  604 */           if (hp < this.silverfishMinLevel * this.silverfishBaseHP) {
/*      */             
/*  606 */             hp = this.silverfishMinLevel * this.silverfishBaseHP;
/*  607 */             distance = this.silverfishMinLevel;
/*      */           } 
/*  609 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  610 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  611 */           e.getEntity().setHealth(hp);
/*  612 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  613 */           if (e.getEntity().getCustomName() == null) {
/*  614 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  616 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.silverfishSpeed);
/*  617 */           if (this.monsterNameplatesEnabled && this.silverfishNameplate)
/*      */           {
/*  619 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SHEEP:
/*  623 */           hp = distance * this.sheepBaseHP;
/*  624 */           if (hp < this.sheepMinLevel * this.sheepBaseHP) {
/*      */             
/*  626 */             hp = this.sheepMinLevel * this.sheepBaseHP;
/*  627 */             distance = this.sheepMinLevel;
/*      */           } 
/*  629 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  630 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  631 */           e.getEntity().setHealth(hp);
/*  632 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  633 */           if (e.getEntity().getCustomName() == null) {
/*  634 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  636 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.sheepSpeed);
/*  637 */           if (this.monsterNameplatesEnabled && this.sheepNameplate)
/*      */           {
/*  639 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case RABBIT:
/*  643 */           hp = distance * this.rabbitBaseHP;
/*  644 */           if (hp < this.rabbitMinLevel * this.rabbitBaseHP) {
/*      */             
/*  646 */             hp = this.rabbitMinLevel * this.rabbitBaseHP;
/*  647 */             distance = this.rabbitMinLevel;
/*      */           } 
/*  649 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  650 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  651 */           e.getEntity().setHealth(hp);
/*  652 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  653 */           if (e.getEntity().getCustomName() == null) {
/*  654 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  656 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.rabbitSpeed);
/*  657 */           if (this.monsterNameplatesEnabled && this.rabbitNameplate)
/*      */           {
/*  659 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case PIG_ZOMBIE:
/*  663 */           hp = distance * this.pigzombieBaseHP;
/*  664 */           if (hp < this.pigzombieMinLevel * this.pigzombieBaseHP) {
/*      */             
/*  666 */             hp = this.pigzombieMinLevel * this.pigzombieBaseHP;
/*  667 */             distance = this.pigzombieMinLevel;
/*      */           } 
/*  669 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  670 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  671 */           e.getEntity().setHealth(hp);
/*  672 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  673 */           if (e.getEntity().getCustomName() == null) {
/*  674 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*      */           
/*  677 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.pigzombieSpeed);
/*  678 */           if (this.monsterNameplatesEnabled && this.pigzombieNameplate)
/*      */           {
/*  680 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case PIG:
/*  684 */           hp = distance * this.pigBaseHP;
/*  685 */           if (hp < this.pigMinLevel * this.pigBaseHP) {
/*      */             
/*  687 */             hp = this.pigMinLevel * this.pigBaseHP;
/*  688 */             distance = this.pigMinLevel;
/*      */           } 
/*  690 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  691 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  692 */           e.getEntity().setHealth(hp);
/*  693 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  694 */           if (e.getEntity().getCustomName() == null) {
/*  695 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  697 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.pigSpeed);
/*  698 */           if (this.monsterNameplatesEnabled && this.pigNameplate)
/*      */           {
/*  700 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case MUSHROOM_COW:
/*  704 */           hp = distance * this.mushroomcowBaseHP;
/*  705 */           if (hp < this.mushroomcowMinLevel * this.mushroomcowBaseHP) {
/*      */             
/*  707 */             hp = this.mushroomcowMinLevel * this.mushroomcowBaseHP;
/*  708 */             distance = this.mushroomcowMinLevel;
/*      */           } 
/*  710 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  711 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  712 */           e.getEntity().setHealth(hp);
/*  713 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  714 */           if (e.getEntity().getCustomName() == null) {
/*  715 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  717 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.mushroomcowSpeed);
/*  718 */           if (this.monsterNameplatesEnabled && this.mushroomcowNameplate)
/*      */           {
/*  720 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case MAGMA_CUBE:
/*  724 */           hp = distance * this.magmacubeBaseHP;
/*  725 */           if (hp < this.magmacubeMinLevel * this.magmacubeBaseHP) {
/*      */             
/*  727 */             hp = this.magmacubeMinLevel * this.magmacubeBaseHP;
/*  728 */             distance = this.magmacubeMinLevel;
/*      */           } 
/*  730 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  731 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  732 */           e.getEntity().setHealth(hp);
/*  733 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  734 */           if (e.getEntity().getCustomName() == null) {
/*  735 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  737 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.magmacubeSpeed);
/*  738 */           if (this.monsterNameplatesEnabled && this.magmacubeNameplate)
/*      */           {
/*  740 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case GUARDIAN:
/*  744 */           hp = distance * this.guardianBaseHP;
/*  745 */           if (hp < this.guardianMinLevel * this.guardianBaseHP) {
/*      */             
/*  747 */             hp = this.guardianMinLevel * this.guardianBaseHP;
/*  748 */             distance = this.guardianMinLevel;
/*      */           } 
/*  750 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  751 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  752 */           e.getEntity().setHealth(hp);
/*  753 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  754 */           if (e.getEntity().getCustomName() == null) {
/*  755 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  757 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.guardianSpeed);
/*  758 */           if (this.monsterNameplatesEnabled && this.guardianNameplate)
/*      */           {
/*  760 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case ELDER_GUARDIAN:
/*  765 */           hp = distance * this.guardianBaseHP;
/*  766 */           if (hp < this.guardianMinLevel * this.guardianBaseHP) {
/*      */             
/*  768 */             hp = this.guardianMinLevel * this.guardianBaseHP;
/*  769 */             distance = this.guardianMinLevel;
/*      */           } 
/*  771 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  772 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  773 */           e.getEntity().setHealth(hp);
/*  774 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  775 */           if (e.getEntity().getCustomName() == null) {
/*  776 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  778 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.guardianSpeed);
/*  779 */           if (this.monsterNameplatesEnabled && this.guardianNameplate)
/*      */           {
/*  781 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case GIANT:
/*  785 */           hp = distance * this.giantBaseHP;
/*  786 */           if (hp < this.giantMinLevel * this.giantBaseHP) {
/*      */             
/*  788 */             hp = this.giantMinLevel * this.giantBaseHP;
/*  789 */             distance = this.guardianMinLevel;
/*      */           } 
/*  791 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  792 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  793 */           e.getEntity().setHealth(hp);
/*  794 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  795 */           if (e.getEntity().getCustomName() == null) {
/*  796 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  798 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.giantSpeed);
/*  799 */           if (this.monsterNameplatesEnabled && this.giantNameplate)
/*      */           {
/*  801 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case GHAST:
/*  805 */           hp = distance * this.ghastBaseHP;
/*  806 */           if (hp < this.ghastMinLevel * this.ghastBaseHP) {
/*      */             
/*  808 */             hp = this.ghastMinLevel * this.ghastBaseHP;
/*  809 */             distance = this.ghastMinLevel;
/*      */           } 
/*  811 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  812 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  813 */           e.getEntity().setHealth(hp);
/*  814 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  815 */           if (e.getEntity().getCustomName() == null) {
/*  816 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  818 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.ghastSpeed);
/*      */           
/*  820 */           if (this.monsterNameplatesEnabled && this.ghastNameplate)
/*      */           {
/*  822 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case ENDERMITE:
/*  826 */           hp = distance * this.endermiteBaseHP;
/*  827 */           if (hp < this.endermiteMinLevel * this.endermiteBaseHP) {
/*      */             
/*  829 */             hp = this.endermiteMinLevel * this.endermiteBaseHP;
/*  830 */             distance = this.endermiteMinLevel;
/*      */           } 
/*  832 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  833 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  834 */           e.getEntity().setHealth(hp);
/*  835 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  836 */           if (e.getEntity().getCustomName() == null) {
/*  837 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  839 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.endermiteSpeed);
/*  840 */           if (this.monsterNameplatesEnabled && this.endermiteNameplate)
/*      */           {
/*  842 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case ENDERMAN:
/*  846 */           hp = distance * this.endermanBaseHP;
/*  847 */           if (hp < this.endermanMinLevel * this.endermanBaseHP) {
/*      */             
/*  849 */             hp = this.endermanMinLevel * this.endermanBaseHP;
/*  850 */             distance = this.endermanMinLevel;
/*      */           } 
/*  852 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  853 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  854 */           e.getEntity().setHealth(hp);
/*  855 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  856 */           if (e.getEntity().getCustomName() != null)
/*      */           {
/*  858 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  860 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.endermanSpeed);
/*  861 */           if (this.monsterNameplatesEnabled && this.endermanNameplate)
/*      */           {
/*  863 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case ENDER_DRAGON:
/*  867 */           hp = distance * this.enderdragonBaseHP;
/*  868 */           if (hp < this.enderdragonMinLevel * this.enderdragonBaseHP) {
/*      */             
/*  870 */             hp = this.enderdragonMinLevel * this.enderdragonBaseHP;
/*  871 */             distance = this.enderdragonMinLevel;
/*      */           } 
/*  873 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  874 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  875 */           e.getEntity().setHealth(hp);
/*  876 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  877 */           if (e.getEntity().getCustomName() == null) {
/*  878 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  880 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.enderdragonSpeed);
/*  881 */           if (this.monsterNameplatesEnabled && this.enderdragonNameplate)
/*      */           {
/*  883 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case CREEPER:
/*  887 */           hp = distance * this.creeperBaseHP;
/*  888 */           if (hp < this.creeperMinLevel * this.creeperBaseHP) {
/*      */             
/*  890 */             hp = this.creeperMinLevel * this.creeperBaseHP;
/*  891 */             distance = this.creeperMinLevel;
/*      */           } 
/*  893 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  894 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  895 */           e.getEntity().setHealth(hp);
/*  896 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  897 */           if (e.getEntity().getCustomName() == null) {
/*  898 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  900 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.creeperSpeed);
/*      */ 
/*      */ 
/*      */           
/*  904 */           if (this.monsterNameplatesEnabled && this.creeperNameplate)
/*      */           {
/*  906 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case COW:
/*  910 */           hp = distance * this.cowBaseHP;
/*  911 */           if (hp < this.cowMinLevel * this.cowBaseHP) {
/*      */             
/*  913 */             hp = this.cowMinLevel * this.cowBaseHP;
/*  914 */             distance = this.cowMinLevel;
/*      */           } 
/*  916 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  917 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  918 */           e.getEntity().setHealth(hp);
/*  919 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  920 */           if (e.getEntity().getCustomName() == null) {
/*  921 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  923 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.cowSpeed);
/*  924 */           if (this.monsterNameplatesEnabled && this.cowNameplate)
/*      */           {
/*  926 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case CHICKEN:
/*  930 */           hp = distance * this.chickenBaseHP;
/*  931 */           if (hp < this.chickenMinLevel * this.chickenBaseHP) {
/*      */             
/*  933 */             hp = this.chickenMinLevel * this.chickenBaseHP;
/*  934 */             distance = this.chickenMinLevel;
/*      */           } 
/*  936 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  937 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  938 */           e.getEntity().setHealth(hp);
/*  939 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  940 */           if (e.getEntity().getCustomName() == null) {
/*  941 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  943 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.chickenSpeed);
/*  944 */           if (this.monsterNameplatesEnabled && this.chickenNameplate)
/*      */           {
/*  946 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case CAVE_SPIDER:
/*  950 */           hp = distance * this.cavespiderBaseHP;
/*  951 */           if (hp < this.cavespiderMinLevel * this.cavespiderBaseHP) {
/*      */             
/*  953 */             hp = this.cavespiderMinLevel * this.cavespiderBaseHP;
/*  954 */             distance = this.cavespiderMinLevel;
/*      */           } 
/*  956 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  957 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  958 */           e.getEntity().setHealth(hp);
/*  959 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  960 */           if (e.getEntity().getCustomName() == null) {
/*  961 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  963 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.cavespiderSpeed);
/*  964 */           if (this.monsterNameplatesEnabled && this.cavespiderNameplate)
/*      */           {
/*  966 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case BLAZE:
/*  970 */           hp = distance * this.blazeBaseHP;
/*  971 */           if (hp < this.blazeMinLevel * this.blazeBaseHP) {
/*      */             
/*  973 */             hp = this.blazeMinLevel * this.blazeBaseHP;
/*  974 */             distance = this.blazeMinLevel;
/*      */           } 
/*  976 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  977 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  978 */           e.getEntity().setHealth(hp);
/*  979 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/*  980 */           if (e.getEntity().getCustomName() == null) {
/*  981 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/*  983 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.blazeSpeed);
/*  984 */           if (this.monsterNameplatesEnabled && this.blazeNameplate)
/*      */           {
/*  986 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case WITCH:
/*  990 */           hp = distance * this.witchBaseHP;
/*  991 */           if (hp < this.witchMinLevel * this.witchBaseHP) {
/*      */             
/*  993 */             hp = this.witchMinLevel * this.witchBaseHP;
/*  994 */             distance = this.witchMinLevel;
/*      */           } 
/*  996 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*  997 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/*  998 */           e.getEntity().setHealth(hp);
/*  999 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1000 */           if (e.getEntity().getCustomName() == null) {
/* 1001 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1003 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.witchSpeed);
/* 1004 */           if (this.monsterNameplatesEnabled && this.witchNameplate)
/*      */           {
/* 1006 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case WITHER_SKELETON:
/* 1010 */           hp = distance * this.witherSkeletonBaseHP;
/* 1011 */           if (hp < this.witherSkeletonMinLevel * this.witherSkeletonBaseHP) {
/*      */             
/* 1013 */             hp = this.witherSkeletonMinLevel * this.witherSkeletonBaseHP;
/* 1014 */             distance = this.witherSkeletonMinLevel;
/*      */           } 
/* 1016 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1017 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1018 */           e.getEntity().setHealth(hp);
/* 1019 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1020 */           if (e.getEntity().getCustomName() == null) {
/* 1021 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1023 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.witherSkeletonSpeed);
/* 1024 */           if (this.monsterNameplatesEnabled && this.witherSkeletonNameplate)
/*      */           {
/* 1026 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case SHULKER:
/* 1030 */           hp = distance * this.shulkerBaseHP;
/* 1031 */           if (hp < this.shulkerMinLevel * this.shulkerBaseHP) {
/*      */             
/* 1033 */             hp = this.shulkerMinLevel * this.shulkerBaseHP;
/* 1034 */             distance = this.shulkerMinLevel;
/*      */           } 
/* 1036 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1037 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1038 */           e.getEntity().setHealth(hp);
/* 1039 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1040 */           if (e.getEntity().getCustomName() == null) {
/* 1041 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1043 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.shulkerSpeed);
/* 1044 */           if (this.monsterNameplatesEnabled && this.shulkerNameplate)
/*      */           {
/* 1046 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case PILLAGER:
/* 1050 */           hp = distance * this.pillagerBaseHP;
/* 1051 */           if (hp < this.pillagerMinLevel * this.pillagerBaseHP) {
/*      */             
/* 1053 */             hp = this.pillagerMinLevel * this.pillagerBaseHP;
/* 1054 */             distance = this.pillagerMinLevel;
/*      */           } 
/* 1056 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1057 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1058 */           e.getEntity().setHealth(hp);
/* 1059 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1060 */           if (e.getEntity().getCustomName() == null) {
/* 1061 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1063 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.pillagerSpeed);
/* 1064 */           if (this.monsterNameplatesEnabled && this.pillagerNameplate)
/*      */           {
/* 1066 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case ILLUSIONER:
/* 1070 */           hp = distance * this.illusionerBaseHP;
/* 1071 */           if (hp < this.illusionerMinLevel * this.illusionerBaseHP) {
/*      */             
/* 1073 */             hp = this.illusionerMinLevel * this.illusionerBaseHP;
/* 1074 */             distance = this.illusionerMinLevel;
/*      */           } 
/* 1076 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1077 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1078 */           e.getEntity().setHealth(hp);
/* 1079 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1080 */           if (e.getEntity().getCustomName() == null) {
/* 1081 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1083 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.illusionerSpeed);
/* 1084 */           if (this.monsterNameplatesEnabled && this.illusionerNameplate)
/*      */           {
/* 1086 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case EVOKER:
/* 1090 */           hp = distance * this.evokerBaseHP;
/* 1091 */           if (hp < this.evokerMinLevel * this.evokerBaseHP) {
/*      */             
/* 1093 */             hp = this.evokerMinLevel * this.evokerBaseHP;
/* 1094 */             distance = this.evokerMinLevel;
/*      */           } 
/* 1096 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1097 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1098 */           e.getEntity().setHealth(hp);
/* 1099 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1100 */           if (e.getEntity().getCustomName() == null) {
/* 1101 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1103 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.evokerSpeed);
/* 1104 */           if (this.monsterNameplatesEnabled && this.evokerNameplate)
/*      */           {
/* 1106 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case RAVAGER:
/* 1110 */           hp = distance * this.ravagerBaseHP;
/* 1111 */           if (hp < this.ravagerMinLevel * this.ravagerBaseHP) {
/*      */             
/* 1113 */             hp = this.ravagerMinLevel * this.ravagerBaseHP;
/* 1114 */             distance = this.ravagerMinLevel;
/*      */           } 
/* 1116 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1117 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1118 */           e.getEntity().setHealth(hp);
/* 1119 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1120 */           if (e.getEntity().getCustomName() == null) {
/* 1121 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1123 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.ravagerSpeed);
/* 1124 */           if (this.monsterNameplatesEnabled && this.ravagerNameplate)
/*      */           {
/* 1126 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case BAT:
/* 1131 */           hp = distance * this.batBaseHP;
/* 1132 */           if (hp < this.batMinLevel * this.batBaseHP) {
/*      */             
/* 1134 */             hp = this.batMinLevel * this.batBaseHP;
/* 1135 */             distance = this.batMinLevel;
/*      */           } 
/* 1137 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1138 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1139 */           e.getEntity().setHealth(hp);
/* 1140 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1141 */           if (e.getEntity().getCustomName() == null) {
/* 1142 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1144 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.batSpeed);
/* 1145 */           if (this.monsterNameplatesEnabled && this.batNameplate)
/*      */           {
/* 1147 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/* 1149 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*      */           break;
/*      */         case DROWNED:
/* 1152 */           hp = distance * this.drownedBaseHP;
/* 1153 */           if (hp < this.drownedMinLevel * this.drownedBaseHP) {
/*      */             
/* 1155 */             hp = this.drownedMinLevel * this.drownedBaseHP;
/* 1156 */             distance = this.drownedMinLevel;
/*      */           } 
/* 1158 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1159 */           e.getEntity().setHealth(hp);
/* 1160 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1161 */           if (e.getEntity().getCustomName() == null) {
/* 1162 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1164 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1165 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.drownedSpeed);
/* 1166 */           if (this.monsterNameplatesEnabled && this.drownedNameplate)
/*      */           {
/* 1168 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case HUSK:
/* 1172 */           hp = distance * this.huskBaseHP;
/* 1173 */           if (hp < this.huskMinLevel * this.huskBaseHP) {
/*      */             
/* 1175 */             hp = this.huskMinLevel * this.huskBaseHP;
/* 1176 */             distance = this.huskMinLevel;
/*      */           } 
/* 1178 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1179 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1180 */           e.getEntity().setHealth(hp);
/* 1181 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1182 */           if (e.getEntity().getCustomName() == null) {
/* 1183 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1185 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.huskSpeed);
/* 1186 */           if (this.monsterNameplatesEnabled && this.huskNameplate)
/*      */           {
/* 1188 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case ZOMBIE_VILLAGER:
/* 1192 */           hp = distance * this.zombievillagerBaseHP;
/* 1193 */           if (hp < this.zombievillagerMinLevel * this.zombievillagerBaseHP) {
/*      */             
/* 1195 */             hp = this.zombievillagerMinLevel * this.zombievillagerBaseHP;
/* 1196 */             distance = this.zombievillagerMinLevel;
/*      */           } 
/* 1198 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1199 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1200 */           e.getEntity().setHealth(hp);
/* 1201 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1202 */           if (e.getEntity().getCustomName() == null) {
/* 1203 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1205 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.zombievillagerSpeed);
/* 1206 */           if (this.monsterNameplatesEnabled && this.zombievillagerNameplate)
/*      */           {
/* 1208 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case POLAR_BEAR:
/* 1212 */           hp = distance * this.polarBearBaseHP;
/* 1213 */           if (hp < this.polarBearMinLevel * this.polarBearBaseHP) {
/*      */             
/* 1215 */             hp = this.polarBearMinLevel * this.polarBearBaseHP;
/* 1216 */             distance = this.polarBearMinLevel;
/*      */           } 
/* 1218 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1219 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1220 */           e.getEntity().setHealth(hp);
/* 1221 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1222 */           if (e.getEntity().getCustomName() == null) {
/* 1223 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1225 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.polarBearSpeed);
/* 1226 */           if (this.monsterNameplatesEnabled && this.polarBearNameplate)
/*      */           {
/* 1228 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case WANDERING_TRADER:
/* 1232 */           hp = distance * this.wanderingTraderBaseHP;
/* 1233 */           if (hp < this.wanderingTraderMinLevel * this.wanderingTraderBaseHP) {
/*      */             
/* 1235 */             hp = this.wanderingTraderMinLevel * this.wanderingTraderBaseHP;
/* 1236 */             distance = this.wanderingTraderMinLevel;
/*      */           } 
/* 1238 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1239 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1240 */           e.getEntity().setHealth(hp);
/* 1241 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1242 */           if (e.getEntity().getCustomName() == null) {
/* 1243 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1245 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.wanderingTraderSpeed);
/* 1246 */           if (this.monsterNameplatesEnabled && this.wanderingTraderNameplate)
/*      */           {
/* 1248 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case DONKEY:
/* 1252 */           hp = distance * this.donkeyBaseHP;
/* 1253 */           if (hp < this.donkeyMinLevel * this.donkeyBaseHP) {
/*      */             
/* 1255 */             hp = this.donkeyMinLevel * this.donkeyBaseHP;
/* 1256 */             distance = this.donkeyMinLevel;
/*      */           } 
/* 1258 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1259 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1260 */           e.getEntity().setHealth(hp);
/* 1261 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1262 */           if (e.getEntity().getCustomName() == null) {
/* 1263 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1265 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.donkeySpeed);
/* 1266 */           if (this.monsterNameplatesEnabled && this.donkeyNameplate)
/*      */           {
/* 1268 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case LLAMA:
/* 1272 */           hp = distance * this.llamaBaseHP;
/* 1273 */           if (hp < this.llamaMinLevel * this.llamaBaseHP) {
/*      */             
/* 1275 */             hp = this.llamaMinLevel * this.llamaBaseHP;
/* 1276 */             distance = this.llamaMinLevel;
/*      */           } 
/* 1278 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1279 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1280 */           e.getEntity().setHealth(hp);
/* 1281 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1282 */           if (e.getEntity().getCustomName() == null) {
/* 1283 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1285 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.llamaSpeed);
/* 1286 */           if (this.monsterNameplatesEnabled && this.llamaNameplate)
/*      */           {
/* 1288 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/* 1290 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/*      */           break;
/*      */         case SALMON:
/* 1293 */           hp = distance * this.salmonBaseHP;
/* 1294 */           if (hp < this.salmonMinLevel * this.salmonBaseHP) {
/*      */             
/* 1296 */             hp = this.salmonMinLevel * this.salmonBaseHP;
/* 1297 */             distance = this.salmonMinLevel;
/*      */           } 
/* 1299 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1300 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1301 */           e.getEntity().setHealth(hp);
/* 1302 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1303 */           if (e.getEntity().getCustomName() == null) {
/* 1304 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1306 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.salmonSpeed);
/* 1307 */           if (this.monsterNameplatesEnabled && this.salmonNameplate)
/*      */           {
/* 1309 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case VEX:
/* 1313 */           hp = distance * this.vexBaseHP;
/* 1314 */           if (hp < this.vexMinLevel * this.vexBaseHP) {
/*      */             
/* 1316 */             hp = this.vexMinLevel * this.vexBaseHP;
/* 1317 */             distance = this.vexMinLevel;
/*      */           } 
/* 1319 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1320 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1321 */           e.getEntity().setHealth(hp);
/* 1322 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1323 */           if (e.getEntity().getCustomName() == null) {
/* 1324 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1326 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.vexSpeed);
/* 1327 */           if (this.monsterNameplatesEnabled && this.vexNameplate)
/*      */           {
/* 1329 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         case VINDICATOR:
/* 1333 */           hp = distance * this.vindicatorBaseHP;
/* 1334 */           if (hp < this.vindicatorMinLevel * this.vindicatorBaseHP) {
/*      */             
/* 1336 */             hp = this.vindicatorMinLevel * this.vindicatorBaseHP;
/* 1337 */             distance = this.vindicatorMinLevel;
/*      */           } 
/* 1339 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1340 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1341 */           e.getEntity().setHealth(hp);
/* 1342 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1343 */           if (e.getEntity().getCustomName() == null) {
/* 1344 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1346 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.vindicatorSpeed);
/* 1347 */           if (this.monsterNameplatesEnabled && this.vindicatorNameplate)
/*      */           {
/* 1349 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */         
/*      */         case BEE:
/* 1354 */           hp = distance * this.beeBaseHP;
/* 1355 */           if (hp < this.beeMinLevel * this.beeBaseHP) {
/*      */             
/* 1357 */             hp = this.beeMinLevel * this.vindicatorBaseHP;
/* 1358 */             distance = this.beeMinLevel;
/*      */           } 
/* 1360 */           nameSpaceKey.setMonsterLevelContainer((Entity)e.getEntity(), hp);
/* 1361 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(hp);
/* 1362 */           e.getEntity().setHealth(hp);
/* 1363 */           levelColor = ChatColor.YELLOW + "[" + distance + "]";
/* 1364 */           if (e.getEntity().getCustomName() == null) {
/* 1365 */             e.getEntity().setCustomName(mobName + levelColor);
/*      */           }
/* 1367 */           ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).setBaseValue(((AttributeInstance)Objects.<AttributeInstance>requireNonNull(e.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED))).getValue() * this.beeSpeed);
/* 1368 */           if (this.monsterNameplatesEnabled && this.beeNameplate)
/*      */           {
/* 1370 */             e.getEntity().setCustomNameVisible(true);
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\MonsterCombatRelated\MonsterLevelsHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */