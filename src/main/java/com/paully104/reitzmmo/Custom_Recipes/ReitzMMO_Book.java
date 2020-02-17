/*     */ package com.paully104.reitzmmo.Custom_Recipes;
/*     */ 
/*     */ import com.paully104.reitzmmo.ConfigFiles.API;
/*     */ import java.util.Objects;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.BookMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReitzMMO_Book
/*     */ {
/*     */   public static void setLoginBook(Player p) {
/*  22 */     boolean kingMobsEnabled = API.specialMonsterConfig.getBoolean("kingMobsEnabled");
/*  23 */     boolean notoriousMobsEnabled = API.specialMonsterConfig.getBoolean("notoriusMobsEnabled");
/*  24 */     boolean devilishMobsEnabled = API.specialMonsterConfig.getBoolean("devilishMobsEnabled");
/*  25 */     boolean dumbMobsEnabled = API.specialMonsterConfig.getBoolean("dumbMobsEnabled");
/*     */ 
/*     */     
/*  28 */     int kingMobsLV = API.specialMonsterConfig.getInt("kingMobsLVDifference");
/*  29 */     int notoriousMobsLV = API.specialMonsterConfig.getInt("notoriousMobsLVDifference");
/*  30 */     int devilishMobsLV = API.specialMonsterConfig.getInt("devilishMobsLVDifference");
/*  31 */     int dumbMobsLV = API.specialMonsterConfig.getInt("dumbMobsLVDifference");
/*     */ 
/*     */     
/*  34 */     boolean specialMobGlowingEnabled = API.specialMonsterConfig.getBoolean("specialMonsterGlowEnabled");
/*  35 */     boolean specialMobSilentEnabled = API.specialMonsterConfig.getBoolean("specialMonsterSilentEnabled");
/*     */ 
/*     */ 
/*     */     
/*  39 */     int blocksPerMobLevel = API.monsterConfig.getInt("BLOCKS-PER-MOB-LEVEL");
/*  40 */     int zombieBaseHP = API.monsterConfig.getInt("ZOMBIE_BASE_HP");
/*  41 */     int wolfBaseHP = API.monsterConfig.getInt("WOLF_BASE_HP");
/*  42 */     int villagerBaseHP = API.monsterConfig.getInt("VILLAGER_BASE_HP");
/*  43 */     int squidBaseHP = API.monsterConfig.getInt("SQUID_BASE_HP");
/*  44 */     int spiderBaseHP = API.monsterConfig.getInt("SPIDER_BASE_HP");
/*  45 */     int snowmanBaseHP = API.monsterConfig.getInt("SNOWMAN_BASE_HP");
/*  46 */     int slimeBaseHP = API.monsterConfig.getInt("SLIME_BASE_HP");
/*  47 */     int skeletonBaseHP = API.monsterConfig.getInt("SKELETON_BASE_HP");
/*  48 */     int silverfishBaseHP = API.monsterConfig.getInt("SILVERFISH_BASE_HP");
/*  49 */     int sheepBaseHP = API.monsterConfig.getInt("SHEEP_BASE_HP");
/*  50 */     int rabbitBaseHP = API.monsterConfig.getInt("RABBIT_BASE_HP");
/*  51 */     int pigzombieBaseHP = API.monsterConfig.getInt("PIGZOMBIE_BASE_HP");
/*  52 */     int pigBaseHP = API.monsterConfig.getInt("PIG_BASE_HP");
/*  53 */     int mushroomcowBaseHP = API.monsterConfig.getInt("MUSHROOMCOW_BASE_HP");
/*  54 */     int magmacubeBaseHP = API.monsterConfig.getInt("MAGMACUBE_BASE_HP");
/*  55 */     int guardianBaseHP = API.monsterConfig.getInt("GUARDIAN_BASE_HP");
/*  56 */     int giantBaseHP = API.monsterConfig.getInt("GIANT_BASE_HP");
/*  57 */     int ghastBaseHP = API.monsterConfig.getInt("GHAST_BASE_HP");
/*  58 */     int endermiteBaseHP = API.monsterConfig.getInt("ENDERMITE_BASE_HP");
/*  59 */     int endermanBaseHP = API.monsterConfig.getInt("ENDERMAN_BASE_HP");
/*  60 */     int enderdragonBaseHP = API.monsterConfig.getInt("ENDERDRAGON_BASE_HP");
/*  61 */     int creeperBaseHP = API.monsterConfig.getInt("CREEPER_BASE_HP");
/*  62 */     int cowBaseHP = API.monsterConfig.getInt("COW_BASE_HP");
/*  63 */     int chickenBaseHP = API.monsterConfig.getInt("CHICKEN_BASE_HP");
/*  64 */     int cavespiderBaseHP = API.monsterConfig.getInt("CAVESPIDER_BASE_HP");
/*  65 */     int blazeBaseHP = API.monsterConfig.getInt("BLAZE_BASE_HP");
/*  66 */     int witchBaseHP = API.monsterConfig.getInt("WITCH_BASE_HP");
/*  67 */     int witherSkeletonBaseHP = API.monsterConfig.getInt("WITHERSKELETON_BASE_HP");
/*  68 */     int shulkerBaseHP = API.monsterConfig.getInt("SHULKER_BASE_HP");
/*  69 */     int pillagerBaseHP = API.monsterConfig.getInt("PILLAGER_BASE_HP");
/*  70 */     int illusionerBaseHP = API.monsterConfig.getInt("ILLUSIONER_BASE_HP");
/*  71 */     int evokerBaseHP = API.monsterConfig.getInt("EVOKER_BASE_HP");
/*  72 */     int ravagerBaseHP = API.monsterConfig.getInt("RAVAGER_BASE_HP");
/*  73 */     int batBaseHP = API.monsterConfig.getInt("BAT_BASE_HP");
/*  74 */     int drownedBaseHP = API.monsterConfig.getInt("DROWNED_BASE_HP");
/*  75 */     int zombievillagerBaseHP = API.monsterConfig.getInt("ZOMBIEVILLAGER_BASE_HP");
/*  76 */     int polarBearBaseHP = API.monsterConfig.getInt("POLARBEAR_BASE_HP");
/*  77 */     int wanderingTraderBaseHP = API.monsterConfig.getInt("WANDERINGTRADER_BASE_HP");
/*  78 */     int donkeyBaseHP = API.monsterConfig.getInt("DONKEY_BASE_HP");
/*  79 */     int llamaBaseHP = API.monsterConfig.getInt("LLAMA_BASE_HP");
/*  80 */     int salmonBaseHP = API.monsterConfig.getInt("SALMON_BASE_HP");
/*     */ 
/*     */     
/*  83 */     boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("NAMEPLATES_ENABLED");
/*     */     
/*  85 */     boolean zombieNameplate = API.monsterConfig.getBoolean("ZOMBIE_NAMEPLATES_ENABLED");
/*  86 */     boolean wolfNameplate = API.monsterConfig.getBoolean("WOLF_NAMEPLATES_ENABLED");
/*  87 */     boolean villagerNameplate = API.monsterConfig.getBoolean("VILLAGER_NAMEPLATES_ENABLED");
/*  88 */     boolean squidNameplate = API.monsterConfig.getBoolean("SQUID_NAMEPLATES_ENABLED");
/*  89 */     boolean spiderNameplate = API.monsterConfig.getBoolean("SPIDER_NAMEPLATES_ENABLED");
/*  90 */     boolean snowmanNameplate = API.monsterConfig.getBoolean("SNOWMAN_NAMEPLATES_ENABLED");
/*  91 */     boolean slimeNameplate = API.monsterConfig.getBoolean("SLIME_NAMEPLATES_ENABLED");
/*  92 */     boolean skeletonNameplate = API.monsterConfig.getBoolean("SKELETON_NAMEPLATES_ENABLED");
/*  93 */     boolean silverfishNameplate = API.monsterConfig.getBoolean("SILVERFISH_NAMEPLATES_ENABLED");
/*  94 */     boolean sheepNameplate = API.monsterConfig.getBoolean("SHEEP_NAMEPLATES_ENABLED");
/*  95 */     boolean rabbitNameplate = API.monsterConfig.getBoolean("RABBIT_NAMEPLATES_ENABLED");
/*  96 */     boolean pigzombieNameplate = API.monsterConfig.getBoolean("PIGZOMBIE_NAMEPLATES_ENABLED");
/*  97 */     boolean pigNameplate = API.monsterConfig.getBoolean("PIG_NAMEPLATES_ENABLED");
/*  98 */     boolean mushroomcowNameplate = API.monsterConfig.getBoolean("MUSHROOMCOW_NAMEPLATES_ENABLED");
/*  99 */     boolean magmacubeNameplate = API.monsterConfig.getBoolean("MAGMACUBE_NAMEPLATES_ENABLED");
/* 100 */     boolean guardianNameplate = API.monsterConfig.getBoolean("GUARDIAN_NAMEPLATES_ENABLED");
/* 101 */     boolean giantNameplate = API.monsterConfig.getBoolean("GIANT_NAMEPLATES_ENABLED");
/* 102 */     boolean ghastNameplate = API.monsterConfig.getBoolean("GHAST_NAMEPLATES_ENABLED");
/* 103 */     boolean endermiteNameplate = API.monsterConfig.getBoolean("ENDERMITE_NAMEPLATES_ENABLED");
/* 104 */     boolean endermanNameplate = API.monsterConfig.getBoolean("ENDERMAN_NAMEPLATES_ENABLED");
/* 105 */     boolean enderdragonNameplate = API.monsterConfig.getBoolean("ENDERDRAGON_NAMEPLATES_ENABLED");
/* 106 */     boolean creeperNameplate = API.monsterConfig.getBoolean("CREEPER_NAMEPLATES_ENABLED");
/* 107 */     boolean cowNameplate = API.monsterConfig.getBoolean("COW_NAMEPLATES_ENABLED");
/* 108 */     boolean chickenNameplate = API.monsterConfig.getBoolean("CHICKEN_NAMEPLATES_ENABLED");
/* 109 */     boolean cavespiderNameplate = API.monsterConfig.getBoolean("CAVESPIDER_NAMEPLATES_ENABLED");
/* 110 */     boolean blazeNameplate = API.monsterConfig.getBoolean("BLAZE_NAMEPLATES_ENABLED");
/* 111 */     boolean witchNameplate = API.monsterConfig.getBoolean("WITCH_NAMEPLATES_ENABLED");
/* 112 */     boolean witherSkeletonNameplate = API.monsterConfig.getBoolean("WITHERSKELETON_NAMEPLATES_ENABLED");
/* 113 */     boolean shulkerNameplate = API.monsterConfig.getBoolean("SHULKER_NAMEPLATES_ENABLED");
/* 114 */     boolean pillagerNameplate = API.monsterConfig.getBoolean("PILLAGER_NAMEPLATES_ENABLED");
/* 115 */     boolean illusionerNameplate = API.monsterConfig.getBoolean("ILLUSIONER_NAMEPLATES_ENABLED");
/* 116 */     boolean evokerNameplate = API.monsterConfig.getBoolean("EVOKER_NAMEPLATES_ENABLED");
/* 117 */     boolean ravagerNameplate = API.monsterConfig.getBoolean("RAVAGER_NAMEPLATES_ENABLED");
/* 118 */     boolean batNameplate = API.monsterConfig.getBoolean("BAT_NAMEPLATES_ENABLED");
/* 119 */     boolean drownedNameplate = API.monsterConfig.getBoolean("DROWNED_NAMEPLATES_ENABLED");
/* 120 */     boolean zombievillagerNameplate = API.monsterConfig.getBoolean("ZOMBIEVILLAGER_NAMEPLATES_ENABLED");
/* 121 */     boolean polarBearNameplate = API.monsterConfig.getBoolean("POLARBEAR_NAMEPLATES_ENABLED");
/* 122 */     boolean wanderingTraderNameplate = API.monsterConfig.getBoolean("WANDERINGTRADER_NAMEPLATES_ENABLED");
/* 123 */     boolean donkeyNameplate = API.monsterConfig.getBoolean("DONKEY_NAMEPLATES_ENABLED");
/* 124 */     boolean llamaNameplate = API.monsterConfig.getBoolean("LLAMA_NAMEPLATES_ENABLED");
/* 125 */     boolean salmonNameplate = API.monsterConfig.getBoolean("SALMON_NAMEPLATES_ENABLED");
/*     */ 
/*     */     
/* 128 */     int zombieSpeed = API.monsterConfig.getInt("ZOMBIE_SPEED");
/* 129 */     int wolfSpeed = API.monsterConfig.getInt("WOLF_SPEED");
/* 130 */     int villagerSpeed = API.monsterConfig.getInt("VILLAGER_SPEED");
/* 131 */     int squidSpeed = API.monsterConfig.getInt("SQUID_SPEED");
/* 132 */     int spiderSpeed = API.monsterConfig.getInt("SPIDER_SPEED");
/* 133 */     int snowmanSpeed = API.monsterConfig.getInt("SNOWMAN_SPEED");
/* 134 */     int slimeSpeed = API.monsterConfig.getInt("SLIME_SPEED");
/* 135 */     int skeletonSpeed = API.monsterConfig.getInt("SKELETON_SPEED");
/* 136 */     int silverfishSpeed = API.monsterConfig.getInt("SILVERFISH_SPEED");
/* 137 */     int sheepSpeed = API.monsterConfig.getInt("SHEEP_SPEED");
/* 138 */     int rabbitSpeed = API.monsterConfig.getInt("RABBIT_SPEED");
/* 139 */     int pigzombieSpeed = API.monsterConfig.getInt("PIGZOMBIE_SPEED");
/* 140 */     int pigSpeed = API.monsterConfig.getInt("PIG_SPEED");
/* 141 */     int mushroomcowSpeed = API.monsterConfig.getInt("MUSHROOMCOW_SPEED");
/* 142 */     int magmacubeSpeed = API.monsterConfig.getInt("MAGMACUBE_SPEED");
/* 143 */     int guardianSpeed = API.monsterConfig.getInt("GUARDIAN_SPEED");
/* 144 */     int giantSpeed = API.monsterConfig.getInt("GIANT_SPEED");
/* 145 */     int ghastSpeed = API.monsterConfig.getInt("GHAST_SPEED");
/* 146 */     int endermiteSpeed = API.monsterConfig.getInt("ENDERMITE_SPEED");
/* 147 */     int endermanSpeed = API.monsterConfig.getInt("ENDERMAN_SPEED");
/* 148 */     int enderdragonSpeed = API.monsterConfig.getInt("ENDERDRAGON_SPEED");
/* 149 */     int creeperSpeed = API.monsterConfig.getInt("CREEPER_SPEED");
/* 150 */     int cowSpeed = API.monsterConfig.getInt("COW_SPEED");
/* 151 */     int chickenSpeed = API.monsterConfig.getInt("CHICKEN_SPEED");
/* 152 */     int cavespiderSpeed = API.monsterConfig.getInt("CAVESPIDER_SPEED");
/* 153 */     int blazeSpeed = API.monsterConfig.getInt("BLAZE_SPEED");
/* 154 */     int witchSpeed = API.monsterConfig.getInt("WITCH_SPEED");
/* 155 */     int witherSkeletonSpeed = API.monsterConfig.getInt("WITHERSKELETON_SPEED");
/* 156 */     int shulkerSpeed = API.monsterConfig.getInt("SHULKER_SPEED");
/* 157 */     int pillagerSpeed = API.monsterConfig.getInt("PILLAGER_SPEED");
/* 158 */     int illusionerSpeed = API.monsterConfig.getInt("ILLUSIONER_SPEED");
/* 159 */     int evokerSpeed = API.monsterConfig.getInt("EVOKER_SPEED");
/* 160 */     int ravagerSpeed = API.monsterConfig.getInt("RAVAGER_SPEED");
/* 161 */     int batSpeed = API.monsterConfig.getInt("BAT_SPEED");
/* 162 */     int drownedSpeed = API.monsterConfig.getInt("DROWNED_SPEED");
/* 163 */     int zombievillagerSpeed = API.monsterConfig.getInt("ZOMBIEVILLAGER_SPEED");
/* 164 */     int polarBearSpeed = API.monsterConfig.getInt("POLARBEAR_SPEED");
/* 165 */     int wanderingTraderSpeed = API.monsterConfig.getInt("WANDERINGTRADER_SPEED");
/* 166 */     int donkeySpeed = API.monsterConfig.getInt("DONKEY_SPEED");
/* 167 */     int llamaSpeed = API.monsterConfig.getInt("LLAMA_SPEED");
/* 168 */     int salmonSpeed = API.monsterConfig.getInt("SALMON_SPEED");
/*     */ 
/*     */     
/* 171 */     int zombieMinLevel = API.monsterConfig.getInt("ZOMBIE_MIN_LEVEL");
/* 172 */     int wolfMinLevel = API.monsterConfig.getInt("WOLF_MIN_LEVEL");
/* 173 */     int villagerMinLevel = API.monsterConfig.getInt("VILLAGER_MIN_LEVEL");
/* 174 */     int squidMinLevel = API.monsterConfig.getInt("SQUID_MIN_LEVEL");
/* 175 */     int spiderMinLevel = API.monsterConfig.getInt("SPIDER_MIN_LEVEL");
/* 176 */     int snowmanMinLevel = API.monsterConfig.getInt("SNOWMAN_MIN_LEVEL");
/* 177 */     int slimeMinLevel = API.monsterConfig.getInt("SLIME_MIN_LEVEL");
/* 178 */     int skeletonMinLevel = API.monsterConfig.getInt("SKELETON_MIN_LEVEL");
/* 179 */     int silverfishMinLevel = API.monsterConfig.getInt("SILVERFISH_MIN_LEVEL");
/* 180 */     int sheepMinLevel = API.monsterConfig.getInt("SHEEP_MIN_LEVEL");
/* 181 */     int rabbitMinLevel = API.monsterConfig.getInt("RABBIT_MIN_LEVEL");
/* 182 */     int pigzombieMinLevel = API.monsterConfig.getInt("PIGZOMBIE_MIN_LEVEL");
/* 183 */     int pigMinLevel = API.monsterConfig.getInt("PIG_MIN_LEVEL");
/* 184 */     int mushroomcowMinLevel = API.monsterConfig.getInt("MUSHROOMCOW_MIN_LEVEL");
/* 185 */     int magmacubeMinLevel = API.monsterConfig.getInt("MAGMACUBE_MIN_LEVEL");
/* 186 */     int guardianMinLevel = API.monsterConfig.getInt("GUARDIAN_MIN_LEVEL");
/* 187 */     int giantMinLevel = API.monsterConfig.getInt("GIANT_MIN_LEVEL");
/* 188 */     int ghastMinLevel = API.monsterConfig.getInt("GHAST_MIN_LEVEL");
/* 189 */     int endermiteMinLevel = API.monsterConfig.getInt("ENDERMITE_MIN_LEVEL");
/* 190 */     int endermanMinLevel = API.monsterConfig.getInt("ENDERMAN_MIN_LEVEL");
/* 191 */     int enderdragonMinLevel = API.monsterConfig.getInt("ENDERDRAGON_MIN_LEVEL");
/* 192 */     int creeperMinLevel = API.monsterConfig.getInt("CREEPER_MIN_LEVEL");
/* 193 */     int cowMinLevel = API.monsterConfig.getInt("COW_MIN_LEVEL");
/* 194 */     int chickenMinLevel = API.monsterConfig.getInt("CHICKEN_MIN_LEVEL");
/* 195 */     int cavespiderMinLevel = API.monsterConfig.getInt("CAVESPIDER_MIN_LEVEL");
/* 196 */     int blazeMinLevel = API.monsterConfig.getInt("BLAZE_MIN_LEVEL");
/* 197 */     int witchMinLevel = API.monsterConfig.getInt("WITCH_MIN_LEVEL");
/* 198 */     int witherSkeletonMinLevel = API.monsterConfig.getInt("WITHERSKELETON_MIN_LEVEL");
/* 199 */     int shulkerMinLevel = API.monsterConfig.getInt("SHULKER_MIN_LEVEL");
/* 200 */     int pillagerMinLevel = API.monsterConfig.getInt("PILLAGER_MIN_LEVEL");
/* 201 */     int illusionerMinLevel = API.monsterConfig.getInt("ILLUSIONER_MIN_LEVEL");
/* 202 */     int evokerMinLevel = API.monsterConfig.getInt("EVOKER_MIN_LEVEL");
/* 203 */     int ravagerMinLevel = API.monsterConfig.getInt("RAVAGER_MIN_LEVEL");
/* 204 */     int batMinLevel = API.monsterConfig.getInt("BAT_MIN_LEVEL");
/* 205 */     int drownedMinLevel = API.monsterConfig.getInt("DROWNED_MIN_LEVEL");
/* 206 */     int zombievillagerMinLevel = API.monsterConfig.getInt("ZOMBIEVILLAGER_MIN_LEVEL");
/* 207 */     int polarBearMinLevel = API.monsterConfig.getInt("POLARBEAR_MIN_LEVEL");
/* 208 */     int wanderingTraderMinLevel = API.monsterConfig.getInt("WANDERINGTRADER_MIN_LEVEL");
/* 209 */     int donkeyMinLevel = API.monsterConfig.getInt("DONKEY_MIN_LEVEL");
/* 210 */     int llamaMinLevel = API.monsterConfig.getInt("LLAMA_MIN_LEVEL");
/* 211 */     int salmonMinLevel = API.monsterConfig.getInt("SALMON_MIN_LEVEL");
/*     */ 
/*     */     
/* 214 */     ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
/* 215 */     BookMeta bookMeta = (BookMeta)book.getItemMeta();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     BaseComponent[] page1 = (new ComponentBuilder("ReitzMMO")).bold(true).event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/reitzmmo.1159/")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Go to the plugin website")).create())).create();
/*     */ 
/*     */ 
/*     */     
/* 231 */     BaseComponent[] page2 = (new ComponentBuilder("ReitzMMO Command Menu(Click Here)")).bold(false).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reitz")).create();
/*     */ 
/*     */     
/* 234 */     ((BookMeta)Objects.<BookMeta>requireNonNull(bookMeta)).spigot().addPage(new BaseComponent[][] { page1 });
/* 235 */     bookMeta.spigot().addPage(new BaseComponent[][] { page2 });
/*     */ 
/*     */     
/* 238 */     bookMeta.setTitle("ReitzMMO");
/* 239 */     bookMeta.setAuthor("Paully104");
/*     */ 
/*     */     
/* 242 */     book.setItemMeta((ItemMeta)bookMeta);
/* 243 */     p.getInventory().addItem(new ItemStack[] { book });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeLoginBook(Player p) {
/* 248 */     for (ItemStack b : p.getInventory().getStorageContents()) {
/*     */       try {
/* 250 */         if (b.hasItemMeta() && 
/* 251 */           b.getItemMeta() instanceof BookMeta) {
/* 252 */           BookMeta meta = (BookMeta)b.getItemMeta();
/* 253 */           if (((String)Objects.<String>requireNonNull(meta.getAuthor())).equals("Paully104") && ((String)Objects.<String>requireNonNull(meta.getTitle())).equals("ReitzMMO")) {
/* 254 */             p.getInventory().remove(b);
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 261 */       catch (NullPointerException nullPointerException) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Minecraft\plugins\ReitzMMO.jar!\com\paully104\reitzmmo\Custom_Recipes\ReitzMMO_Book.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */