/*     */ package com.paully104.reitzmmo.ConfigFiles;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonsterConfig
/*     */ {
/*     */   public static void Configuration() {
/*  14 */     File file = FileManager.monsterHPConfig;
/*  15 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
/*  16 */     configuration.options().header("This config is used to set all monster related configurations");
/*     */     
/*  18 */     configuration.addDefault("General", null);
/*  19 */     configuration.addDefault("General.blocks-per-mob-level", Integer.valueOf(150));
/*  20 */     configuration.addDefault("General.nameplates-enabled", Boolean.valueOf(true));
/*  21 */     configuration.addDefault("General.apply-on-spawner-spawns", Boolean.valueOf(true));
/*     */     
/*  23 */     configuration.addDefault("Blaze", null);
/*  24 */     configuration.addDefault("Blaze.base_hp", Integer.valueOf(3));
/*  25 */     configuration.addDefault("Blaze.base_attack", Integer.valueOf(3));
/*  26 */     configuration.addDefault("Blaze.speed", Integer.valueOf(1));
/*  27 */     configuration.addDefault("Blaze.min_level", Integer.valueOf(1));
/*  28 */     configuration.addDefault("Blaze.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  30 */     configuration.addDefault("Cavespider", null);
/*  31 */     configuration.addDefault("Cavespider.base_hp", Integer.valueOf(3));
/*  32 */     configuration.addDefault("Cavespider.base_attack", Integer.valueOf(3));
/*  33 */     configuration.addDefault("Cavespider.speed", Integer.valueOf(1));
/*  34 */     configuration.addDefault("Cavespider.min_level", Integer.valueOf(1));
/*  35 */     configuration.addDefault("Cavespider.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  37 */     configuration.addDefault("Chicken", null);
/*  38 */     configuration.addDefault("Chicken.base_hp", Integer.valueOf(1));
/*  39 */     configuration.addDefault("Chicken.speed", Integer.valueOf(1));
/*  40 */     configuration.addDefault("Chicken.min_level", Integer.valueOf(1));
/*  41 */     configuration.addDefault("Chicken.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  43 */     configuration.addDefault("Cow", null);
/*  44 */     configuration.addDefault("Cow.base_hp", Integer.valueOf(1));
/*  45 */     configuration.addDefault("Cow.speed", Integer.valueOf(1));
/*  46 */     configuration.addDefault("Cow.min_level", Integer.valueOf(1));
/*  47 */     configuration.addDefault("Cow.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  49 */     configuration.addDefault("Creeper", null);
/*  50 */     configuration.addDefault("Creeper.base_attack", Integer.valueOf(4));
/*  51 */     configuration.addDefault("Creeper.base_hp", Integer.valueOf(7));
/*  52 */     configuration.addDefault("Creeper.speed", Double.valueOf(1.2D));
/*  53 */     configuration.addDefault("Creeper.min_level", Integer.valueOf(1));
/*  54 */     configuration.addDefault("Creeper.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  56 */     configuration.addDefault("Enderdragon", null);
/*  57 */     configuration.addDefault("Enderdragon.base_hp", Integer.valueOf(5));
/*  58 */     configuration.addDefault("Enderdragon.base_attack", Integer.valueOf(3));
/*  59 */     configuration.addDefault("Enderdragon.speed", Double.valueOf(1.2D));
/*  60 */     configuration.addDefault("Enderdragon.min_level", Integer.valueOf(50));
/*  61 */     configuration.addDefault("Enderdragon.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  63 */     configuration.addDefault("Enderman", null);
/*  64 */     configuration.addDefault("Enderman.base_hp", Integer.valueOf(4));
/*  65 */     configuration.addDefault("Enderman.base_attack", Integer.valueOf(3));
/*  66 */     configuration.addDefault("Enderman.speed", Integer.valueOf(1));
/*  67 */     configuration.addDefault("Enderman.min_level", Integer.valueOf(10));
/*  68 */     configuration.addDefault("Enderman.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  70 */     configuration.addDefault("Endermite", null);
/*  71 */     configuration.addDefault("Endermite.base_hp", Integer.valueOf(3));
/*  72 */     configuration.addDefault("Endermite.base_attack", Integer.valueOf(3));
/*  73 */     configuration.addDefault("Endermite.speed", Integer.valueOf(1));
/*  74 */     configuration.addDefault("Endermite.min_level", Integer.valueOf(5));
/*  75 */     configuration.addDefault("Endermite.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  77 */     configuration.addDefault("Ghast", null);
/*  78 */     configuration.addDefault("Ghast.base_hp", Integer.valueOf(4));
/*  79 */     configuration.addDefault("Ghast.base_attacK", Integer.valueOf(3));
/*  80 */     configuration.addDefault("Ghast.speed", Integer.valueOf(1));
/*  81 */     configuration.addDefault("Ghast.min_level", Integer.valueOf(25));
/*  82 */     configuration.addDefault("Ghast.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  84 */     configuration.addDefault("Giant", null);
/*  85 */     configuration.addDefault("Giant.base_hp", Integer.valueOf(4));
/*  86 */     configuration.addDefault("Giant.base_attack", Integer.valueOf(3));
/*  87 */     configuration.addDefault("Giant.speed", Integer.valueOf(1));
/*  88 */     configuration.addDefault("Giant.min_level", Integer.valueOf(10));
/*  89 */     configuration.addDefault("Giant.nameplates_enableD", Boolean.valueOf(true));
/*     */     
/*  91 */     configuration.addDefault("Golem", null);
/*  92 */     configuration.addDefault("Golem.base_hp", Integer.valueOf(80));
/*  93 */     configuration.addDefault("Golem.base_attack", Integer.valueOf(10));
/*  94 */     configuration.addDefault("Golem.speed", Integer.valueOf(1));
/*  95 */     configuration.addDefault("Golem.min_leveL", Integer.valueOf(10));
/*  96 */     configuration.addDefault("Golem.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/*  98 */     configuration.addDefault("Guardian", null);
/*  99 */     configuration.addDefault("Guardian.base_hp", Integer.valueOf(4));
/* 100 */     configuration.addDefault("Guardian.base_attack", Integer.valueOf(3));
/* 101 */     configuration.addDefault("Guardian.speed", Integer.valueOf(1));
/* 102 */     configuration.addDefault("Guardian.min_level", Integer.valueOf(12));
/* 103 */     configuration.addDefault("Guardian.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 105 */     configuration.addDefault("Magmacube", null);
/* 106 */     configuration.addDefault("Magmacube.base_hp", Integer.valueOf(4));
/* 107 */     configuration.addDefault("Magmacube.base_attack", Integer.valueOf(3));
/* 108 */     configuration.addDefault("Magmacube.speed", Integer.valueOf(1));
/* 109 */     configuration.addDefault("Magmacube.min_level", Integer.valueOf(5));
/* 110 */     configuration.addDefault("Magmacube.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 112 */     configuration.addDefault("Mushroomcow", null);
/* 113 */     configuration.addDefault("Mushroomcow.base_hp", Integer.valueOf(2));
/* 114 */     configuration.addDefault("Mushroomcow.speed", Integer.valueOf(1));
/* 115 */     configuration.addDefault("Mushroomcow.min_level", Integer.valueOf(1));
/* 116 */     configuration.addDefault("Mushroomcow.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 118 */     configuration.addDefault("Pig", null);
/* 119 */     configuration.addDefault("Pig.base_hp", Integer.valueOf(1));
/* 120 */     configuration.addDefault("Pig.speed", Integer.valueOf(1));
/* 121 */     configuration.addDefault("Pig.min_level", Integer.valueOf(1));
/* 122 */     configuration.addDefault("Pig.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 124 */     configuration.addDefault("Pigzombie", null);
/* 125 */     configuration.addDefault("Pigzombie.base_hp", Integer.valueOf(4));
/* 126 */     configuration.addDefault("Pigzombie.base_attack", Integer.valueOf(3));
/* 127 */     configuration.addDefault("Pigzombie.speed", Integer.valueOf(1));
/* 128 */     configuration.addDefault("Pigzombie.min_level", Integer.valueOf(8));
/* 129 */     configuration.addDefault("Pigzombie.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 131 */     configuration.addDefault("Rabbit", null);
/* 132 */     configuration.addDefault("Rabbit.base_hp", Integer.valueOf(2));
/* 133 */     configuration.addDefault("Rabbit.speed", Integer.valueOf(1));
/* 134 */     configuration.addDefault("Rabbit.min_level", Integer.valueOf(1));
/* 135 */     configuration.addDefault("Rabbit.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 137 */     configuration.addDefault("Sheep", null);
/* 138 */     configuration.addDefault("Sheep.base_hp", Integer.valueOf(2));
/* 139 */     configuration.addDefault("Sheep.speed", Integer.valueOf(1));
/* 140 */     configuration.addDefault("Sheep.min_level", Integer.valueOf(1));
/* 141 */     configuration.addDefault("Sheep.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 143 */     configuration.addDefault("Fox", null);
/* 144 */     configuration.addDefault("Fox.base_hp", Integer.valueOf(2));
/* 145 */     configuration.addDefault("Fox.speed", Double.valueOf(1.5D));
/* 146 */     configuration.addDefault("Fox.min_level", Integer.valueOf(1));
/* 147 */     configuration.addDefault("Fox.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 149 */     configuration.addDefault("Panda", null);
/* 150 */     configuration.addDefault("Panda.base_hp", Integer.valueOf(1));
/* 151 */     configuration.addDefault("Panda.speed", Integer.valueOf(2));
/* 152 */     configuration.addDefault("Panda.min_level", Integer.valueOf(1));
/* 153 */     configuration.addDefault("Panda.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 155 */     configuration.addDefault("Silverfish", null);
/* 156 */     configuration.addDefault("Silverfish.base_hp", Integer.valueOf(2));
/* 157 */     configuration.addDefault("Silverfish.base_attack", Integer.valueOf(3));
/* 158 */     configuration.addDefault("Silverfish.speed", Integer.valueOf(1));
/* 159 */     configuration.addDefault("Silverfish.min_level", Integer.valueOf(1));
/* 160 */     configuration.addDefault("Silverfish.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 162 */     configuration.addDefault("Skeleton", null);
/* 163 */     configuration.addDefault("Skeleton.base_hp", Integer.valueOf(3));
/* 164 */     configuration.addDefault("Skeleton.base_attack", Integer.valueOf(1));
/* 165 */     configuration.addDefault("Skeleton.speed", Integer.valueOf(1));
/* 166 */     configuration.addDefault("Skeleton.min_level", Integer.valueOf(2));
/* 167 */     configuration.addDefault("Skeleton.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 169 */     configuration.addDefault("Slime", null);
/* 170 */     configuration.addDefault("Slime.base_hp", Integer.valueOf(4));
/* 171 */     configuration.addDefault("Slime.base_attack", Integer.valueOf(3));
/* 172 */     configuration.addDefault("Slime.speed", Integer.valueOf(1));
/* 173 */     configuration.addDefault("Slime.min_level", Integer.valueOf(3));
/* 174 */     configuration.addDefault("Slime.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 176 */     configuration.addDefault("Snowman", null);
/* 177 */     configuration.addDefault("Snowman.base_hp", Integer.valueOf(4));
/* 178 */     configuration.addDefault("Snowman.base_attack", Integer.valueOf(4));
/* 179 */     configuration.addDefault("Snowman.speed", Integer.valueOf(1));
/* 180 */     configuration.addDefault("Snowman.min_level", Integer.valueOf(5));
/* 181 */     configuration.addDefault("Snowman.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 183 */     configuration.addDefault("Spider", null);
/* 184 */     configuration.addDefault("Spider.base_hp", Integer.valueOf(3));
/* 185 */     configuration.addDefault("Spider.base_attack", Integer.valueOf(3));
/* 186 */     configuration.addDefault("Spider.speed", Double.valueOf(1.2D));
/* 187 */     configuration.addDefault("Spider.min_level", Integer.valueOf(2));
/* 188 */     configuration.addDefault("Spider.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 190 */     configuration.addDefault("Squid", null);
/* 191 */     configuration.addDefault("Squid.base_hp", Integer.valueOf(2));
/* 192 */     configuration.addDefault("Squid.speed", Integer.valueOf(1));
/* 193 */     configuration.addDefault("Squid.min_level", Integer.valueOf(1));
/* 194 */     configuration.addDefault("Squid.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 196 */     configuration.addDefault("Villager", null);
/* 197 */     configuration.addDefault("Villager.base_hp", Integer.valueOf(4));
/* 198 */     configuration.addDefault("Villager.speed", Integer.valueOf(1));
/* 199 */     configuration.addDefault("Villager.min_level", Integer.valueOf(1));
/* 200 */     configuration.addDefault("Villager.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 202 */     configuration.addDefault("Wolf", null);
/* 203 */     configuration.addDefault("Wolf.base_hp", Integer.valueOf(3));
/* 204 */     configuration.addDefault("Wolf.base_attack", Integer.valueOf(3));
/* 205 */     configuration.addDefault("Wolf.speed", Double.valueOf(1.5D));
/* 206 */     configuration.addDefault("Wolf.min_level", Integer.valueOf(1));
/* 207 */     configuration.addDefault("Wolf.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 209 */     configuration.addDefault("Zombie", null);
/* 210 */     configuration.addDefault("Zombie.base_hp", Integer.valueOf(3));
/* 211 */     configuration.addDefault("Zombie.base_attack", Integer.valueOf(2));
/* 212 */     configuration.addDefault("Zombie.speed", Double.valueOf(1.2D));
/* 213 */     configuration.addDefault("Zombie.min_level", Integer.valueOf(1));
/* 214 */     configuration.addDefault("Zombie.nameplates_enableD", Boolean.valueOf(true));
/*     */     
/* 216 */     configuration.addDefault("Witch", null);
/* 217 */     configuration.addDefault("Witch.base_hp", Integer.valueOf(4));
/* 218 */     configuration.addDefault("Witch.base_attack", Integer.valueOf(3));
/* 219 */     configuration.addDefault("Witch.speed", Integer.valueOf(1));
/* 220 */     configuration.addDefault("Witch.min_level", Integer.valueOf(8));
/* 221 */     configuration.addDefault("Witch.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 223 */     configuration.addDefault("Witherskeleton", null);
/* 224 */     configuration.addDefault("Witherskeleton.base_hp", Integer.valueOf(6));
/* 225 */     configuration.addDefault("Witherskeleton.base_attack", Integer.valueOf(3));
/* 226 */     configuration.addDefault("Witherskeleton.speed", Integer.valueOf(1));
/* 227 */     configuration.addDefault("Witherskeleton.min_level", Integer.valueOf(11));
/* 228 */     configuration.addDefault("Witherskeleton.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 230 */     configuration.addDefault("Shulker", null);
/* 231 */     configuration.addDefault("Shulker.base_hp", Integer.valueOf(6));
/* 232 */     configuration.addDefault("Shulker.base_attack", Integer.valueOf(3));
/* 233 */     configuration.addDefault("Shulker.speed", Integer.valueOf(1));
/* 234 */     configuration.addDefault("Shulker.min_level", Integer.valueOf(15));
/* 235 */     configuration.addDefault("Shulker.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 237 */     configuration.addDefault("Pillager", null);
/* 238 */     configuration.addDefault("Pillager.base_hp", Integer.valueOf(5));
/* 239 */     configuration.addDefault("Pillager.base_attack", Integer.valueOf(4));
/* 240 */     configuration.addDefault("Pillager.speed", Integer.valueOf(2));
/* 241 */     configuration.addDefault("Pillager.min_level", Integer.valueOf(8));
/* 242 */     configuration.addDefault("Pillager.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 244 */     configuration.addDefault("Illusioner", null);
/* 245 */     configuration.addDefault("Illusioner.base_hp", Integer.valueOf(5));
/* 246 */     configuration.addDefault("Illusioner.base_attack", Integer.valueOf(4));
/* 247 */     configuration.addDefault("Illusioner.speed", Integer.valueOf(1));
/* 248 */     configuration.addDefault("Illusioner.min_level", Integer.valueOf(7));
/* 249 */     configuration.addDefault("Illusioner.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 251 */     configuration.addDefault("Evoker", null);
/* 252 */     configuration.addDefault("Evoker.base_hp", Integer.valueOf(5));
/* 253 */     configuration.addDefault("Evoker.base_attack", Integer.valueOf(4));
/* 254 */     configuration.addDefault("Evoker.speed", Integer.valueOf(1));
/* 255 */     configuration.addDefault("Evoker.min_level", Integer.valueOf(10));
/* 256 */     configuration.addDefault("Evoker.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 258 */     configuration.addDefault("Ravager", null);
/* 259 */     configuration.addDefault("Ravager.base_hp", Integer.valueOf(5));
/* 260 */     configuration.addDefault("Ravager.base_attack", Integer.valueOf(5));
/* 261 */     configuration.addDefault("Ravager.speed", Integer.valueOf(1));
/* 262 */     configuration.addDefault("Ravager.min_level", Integer.valueOf(1));
/* 263 */     configuration.addDefault("Ravager.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 265 */     configuration.addDefault("Bat", null);
/* 266 */     configuration.addDefault("Bat.base_hp", Integer.valueOf(3));
/* 267 */     configuration.addDefault("Bat.base_attack", Integer.valueOf(3));
/* 268 */     configuration.addDefault("Bat.speed", Double.valueOf(1.5D));
/* 269 */     configuration.addDefault("Bat.min_level", Integer.valueOf(1));
/* 270 */     configuration.addDefault("Bat.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 272 */     configuration.addDefault("Drowned", null);
/* 273 */     configuration.addDefault("Drowned.base_hp", Integer.valueOf(3));
/* 274 */     configuration.addDefault("Drowned.base_attack", Integer.valueOf(2));
/* 275 */     configuration.addDefault("Drowned.speed", Double.valueOf(1.2D));
/* 276 */     configuration.addDefault("Drowned.min_level", Integer.valueOf(3));
/* 277 */     configuration.addDefault("Drowned.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 279 */     configuration.addDefault("Husk", null);
/* 280 */     configuration.addDefault("Husk.base_hp", Integer.valueOf(3));
/* 281 */     configuration.addDefault("Husk.base_attack", Integer.valueOf(2));
/* 282 */     configuration.addDefault("Husk.speed", Integer.valueOf(1));
/* 283 */     configuration.addDefault("Husk.min_level", Integer.valueOf(3));
/* 284 */     configuration.addDefault("Husk.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 286 */     configuration.addDefault("Zombievillager", null);
/* 287 */     configuration.addDefault("Zombievillager.base_hp", Integer.valueOf(3));
/* 288 */     configuration.addDefault("Zombievillager.base_attack", Integer.valueOf(2));
/* 289 */     configuration.addDefault("Zombievillager.speed", Integer.valueOf(1));
/* 290 */     configuration.addDefault("Zombievillager.min_level", Integer.valueOf(3));
/* 291 */     configuration.addDefault("Zombievillager.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 293 */     configuration.addDefault("Polarbear", null);
/* 294 */     configuration.addDefault("Polarbear.base_hp", Integer.valueOf(4));
/* 295 */     configuration.addDefault("Polarbear.base_attack", Integer.valueOf(4));
/* 296 */     configuration.addDefault("Polarbear.speed", Integer.valueOf(1));
/* 297 */     configuration.addDefault("Polarbear.min_level", Integer.valueOf(3));
/* 298 */     configuration.addDefault("Polarbear.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 300 */     configuration.addDefault("Wanderingtrader", null);
/* 301 */     configuration.addDefault("Wanderingtrader.base_hp", Integer.valueOf(20));
/* 302 */     configuration.addDefault("Wanderingtrader.base_attack", Integer.valueOf(4));
/* 303 */     configuration.addDefault("Wanderingtrader.speed", Integer.valueOf(1));
/* 304 */     configuration.addDefault("Wanderingtrader.min_level", Integer.valueOf(20));
/* 305 */     configuration.addDefault("Wanderingtrader.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 307 */     configuration.addDefault("Donkey", null);
/* 308 */     configuration.addDefault("Donkey.base_hp", Integer.valueOf(2));
/* 309 */     configuration.addDefault("Donkey.base_attack", Integer.valueOf(3));
/* 310 */     configuration.addDefault("Donkey.speed", Integer.valueOf(1));
/* 311 */     configuration.addDefault("Donkey.min_level", Integer.valueOf(20));
/* 312 */     configuration.addDefault("Donkey.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 314 */     configuration.addDefault("llama", null);
/* 315 */     configuration.addDefault("Llama.base_hp", Integer.valueOf(2));
/* 316 */     configuration.addDefault("Llama.base_attack", Integer.valueOf(3));
/* 317 */     configuration.addDefault("Llama.speed", Integer.valueOf(1));
/* 318 */     configuration.addDefault("Llama.min_level", Integer.valueOf(1));
/* 319 */     configuration.addDefault("Llama.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 321 */     configuration.addDefault("Salmon", null);
/* 322 */     configuration.addDefault("Salmon.base_hp", Integer.valueOf(2));
/* 323 */     configuration.addDefault("Salmon.base_attack", Integer.valueOf(1));
/* 324 */     configuration.addDefault("Salmon.speed", Integer.valueOf(1));
/* 325 */     configuration.addDefault("Salmon.min_level", Integer.valueOf(1));
/* 326 */     configuration.addDefault("Salmon.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 328 */     configuration.addDefault("Vex", null);
/* 329 */     configuration.addDefault("Vex.base_hp", Integer.valueOf(8));
/* 330 */     configuration.addDefault("Vex.base_attack", Integer.valueOf(3));
/* 331 */     configuration.addDefault("Vex.speed", Integer.valueOf(1));
/* 332 */     configuration.addDefault("Vex.min_level", Integer.valueOf(5));
/* 333 */     configuration.addDefault("Vex.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 335 */     configuration.addDefault("Vindicator", null);
/* 336 */     configuration.addDefault("Vindicator.base_hp", Integer.valueOf(10));
/* 337 */     configuration.addDefault("Vindicator.base_attack", Integer.valueOf(5));
/* 338 */     configuration.addDefault("Vindicator.speed", Integer.valueOf(1));
/* 339 */     configuration.addDefault("Vindicator.min_level", Integer.valueOf(5));
/* 340 */     configuration.addDefault("Vindicator.nameplates_enabled", Boolean.valueOf(true));
/*     */     
/* 342 */     configuration.options().copyDefaults(true);
/*     */ 
/*     */     
/*     */     try {
/* 346 */       configuration.save(file);
/*     */     }
/* 348 */     catch (IOException e) {
/*     */ 
/*     */       
/* 351 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\ConfigFiles\MonsterConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */