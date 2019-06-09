package com.paully104.reitzmmo.Custom_Recipes;

import com.paully104.reitzmmo.ConfigFiles.API;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Objects;

public class ReitzMMO_Book {


    public static void setLoginBook(Player p)
    {

        //SpecialMobs Enabled Section
         final boolean kingMobsEnabled = API.specialMonsterConfig.getBoolean("kingMobsEnabled");
         final boolean notoriousMobsEnabled = API.specialMonsterConfig.getBoolean("notoriusMobsEnabled");
         final boolean devilishMobsEnabled = API.specialMonsterConfig.getBoolean("devilishMobsEnabled");
         final boolean dumbMobsEnabled = API.specialMonsterConfig.getBoolean("dumbMobsEnabled");

        //SpecialMobs LV Section
         final int kingMobsLV = API.specialMonsterConfig.getInt("kingMobsLVDifference");
         final int notoriousMobsLV = API.specialMonsterConfig.getInt("notoriousMobsLVDifference");
         final int devilishMobsLV = API.specialMonsterConfig.getInt("devilishMobsLVDifference");
         final int dumbMobsLV = API.specialMonsterConfig.getInt("dumbMobsLVDifference");

        //SpecialMobsSpecialStuff
         final boolean specialMobGlowingEnabled = API.specialMonsterConfig.getBoolean("specialMonsterGlowEnabled");
         final boolean specialMobSilentEnabled = API.specialMonsterConfig.getBoolean("specialMonsterSilentEnabled");


        //MONSTER HP SECTION
         final int blocksPerMobLevel = API.monsterConfig.getInt("BLOCKS-PER-MOB-LEVEL");
         final int zombieBaseHP = API.monsterConfig.getInt("ZOMBIE_BASE_HP");
         final int wolfBaseHP = API.monsterConfig.getInt("WOLF_BASE_HP");
         final int villagerBaseHP = API.monsterConfig.getInt("VILLAGER_BASE_HP");
         final int squidBaseHP = API.monsterConfig.getInt("SQUID_BASE_HP");
         final int spiderBaseHP = API.monsterConfig.getInt("SPIDER_BASE_HP");
         final int snowmanBaseHP = API.monsterConfig.getInt("SNOWMAN_BASE_HP");
         final int slimeBaseHP = API.monsterConfig.getInt("SLIME_BASE_HP");
         final int skeletonBaseHP = API.monsterConfig.getInt("SKELETON_BASE_HP");
         final int silverfishBaseHP = API.monsterConfig.getInt("SILVERFISH_BASE_HP");
         final int sheepBaseHP = API.monsterConfig.getInt("SHEEP_BASE_HP");
         final int rabbitBaseHP = API.monsterConfig.getInt("RABBIT_BASE_HP");
         final int pigzombieBaseHP = API.monsterConfig.getInt("PIGZOMBIE_BASE_HP");
         final int pigBaseHP = API.monsterConfig.getInt("PIG_BASE_HP");
         final int mushroomcowBaseHP = API.monsterConfig.getInt("MUSHROOMCOW_BASE_HP");
         final int magmacubeBaseHP = API.monsterConfig.getInt("MAGMACUBE_BASE_HP");
         final int guardianBaseHP = API.monsterConfig.getInt("GUARDIAN_BASE_HP");
         final int giantBaseHP = API.monsterConfig.getInt("GIANT_BASE_HP");
         final int ghastBaseHP = API.monsterConfig.getInt("GHAST_BASE_HP");
         final int endermiteBaseHP = API.monsterConfig.getInt("ENDERMITE_BASE_HP");
         final int endermanBaseHP = API.monsterConfig.getInt("ENDERMAN_BASE_HP");
         final int enderdragonBaseHP = API.monsterConfig.getInt("ENDERDRAGON_BASE_HP");
         final int creeperBaseHP = API.monsterConfig.getInt("CREEPER_BASE_HP");
         final int cowBaseHP = API.monsterConfig.getInt("COW_BASE_HP");
         final int chickenBaseHP = API.monsterConfig.getInt("CHICKEN_BASE_HP");
         final int cavespiderBaseHP = API.monsterConfig.getInt("CAVESPIDER_BASE_HP");
         final int blazeBaseHP = API.monsterConfig.getInt("BLAZE_BASE_HP");
         final int witchBaseHP = API.monsterConfig.getInt("WITCH_BASE_HP");
         final int witherSkeletonBaseHP = API.monsterConfig.getInt("WITHERSKELETON_BASE_HP");
         final int shulkerBaseHP = API.monsterConfig.getInt("SHULKER_BASE_HP");
         final int pillagerBaseHP = API.monsterConfig.getInt("PILLAGER_BASE_HP");
         final int illusionerBaseHP = API.monsterConfig.getInt("ILLUSIONER_BASE_HP");
         final int evokerBaseHP = API.monsterConfig.getInt("EVOKER_BASE_HP");
         final int ravagerBaseHP = API.monsterConfig.getInt("RAVAGER_BASE_HP");
         final int batBaseHP = API.monsterConfig.getInt("BAT_BASE_HP");
         final int drownedBaseHP = API.monsterConfig.getInt("DROWNED_BASE_HP");
         final int zombievillagerBaseHP = API.monsterConfig.getInt("ZOMBIEVILLAGER_BASE_HP");
         final int polarBearBaseHP = API.monsterConfig.getInt("POLARBEAR_BASE_HP");
         final int wanderingTraderBaseHP = API.monsterConfig.getInt("WANDERINGTRADER_BASE_HP");
         final int donkeyBaseHP = API.monsterConfig.getInt("DONKEY_BASE_HP");
         final int llamaBaseHP = API.monsterConfig.getInt("LLAMA_BASE_HP");
         final int salmonBaseHP = API.monsterConfig.getInt("SALMON_BASE_HP");

        //NAMEPLATE SECTION
         final boolean monsterNameplatesEnabled = API.monsterConfig.getBoolean("NAMEPLATES_ENABLED");

         final boolean zombieNameplate = API.monsterConfig.getBoolean("ZOMBIE_NAMEPLATES_ENABLED");
         final boolean wolfNameplate = API.monsterConfig.getBoolean("WOLF_NAMEPLATES_ENABLED");
         final boolean villagerNameplate = API.monsterConfig.getBoolean("VILLAGER_NAMEPLATES_ENABLED");
         final boolean squidNameplate = API.monsterConfig.getBoolean("SQUID_NAMEPLATES_ENABLED");
         final boolean spiderNameplate = API.monsterConfig.getBoolean("SPIDER_NAMEPLATES_ENABLED");
         final boolean snowmanNameplate = API.monsterConfig.getBoolean("SNOWMAN_NAMEPLATES_ENABLED");
         final boolean slimeNameplate = API.monsterConfig.getBoolean("SLIME_NAMEPLATES_ENABLED");
         final boolean skeletonNameplate = API.monsterConfig.getBoolean("SKELETON_NAMEPLATES_ENABLED");
         final boolean silverfishNameplate = API.monsterConfig.getBoolean("SILVERFISH_NAMEPLATES_ENABLED");
         final boolean sheepNameplate = API.monsterConfig.getBoolean("SHEEP_NAMEPLATES_ENABLED");
         final boolean rabbitNameplate = API.monsterConfig.getBoolean("RABBIT_NAMEPLATES_ENABLED");
         final boolean pigzombieNameplate = API.monsterConfig.getBoolean("PIGZOMBIE_NAMEPLATES_ENABLED");
         final boolean pigNameplate = API.monsterConfig.getBoolean("PIG_NAMEPLATES_ENABLED");
         final boolean mushroomcowNameplate = API.monsterConfig.getBoolean("MUSHROOMCOW_NAMEPLATES_ENABLED");
         final boolean magmacubeNameplate = API.monsterConfig.getBoolean("MAGMACUBE_NAMEPLATES_ENABLED");
         final boolean guardianNameplate = API.monsterConfig.getBoolean("GUARDIAN_NAMEPLATES_ENABLED");
         final boolean giantNameplate = API.monsterConfig.getBoolean("GIANT_NAMEPLATES_ENABLED");
         final boolean ghastNameplate = API.monsterConfig.getBoolean("GHAST_NAMEPLATES_ENABLED");
         final boolean endermiteNameplate = API.monsterConfig.getBoolean("ENDERMITE_NAMEPLATES_ENABLED");
         final boolean endermanNameplate = API.monsterConfig.getBoolean("ENDERMAN_NAMEPLATES_ENABLED");
         final boolean enderdragonNameplate = API.monsterConfig.getBoolean("ENDERDRAGON_NAMEPLATES_ENABLED");
         final boolean creeperNameplate = API.monsterConfig.getBoolean("CREEPER_NAMEPLATES_ENABLED");
         final boolean cowNameplate = API.monsterConfig.getBoolean("COW_NAMEPLATES_ENABLED");
         final boolean chickenNameplate = API.monsterConfig.getBoolean("CHICKEN_NAMEPLATES_ENABLED");
         final boolean cavespiderNameplate = API.monsterConfig.getBoolean("CAVESPIDER_NAMEPLATES_ENABLED");
         final boolean blazeNameplate = API.monsterConfig.getBoolean("BLAZE_NAMEPLATES_ENABLED");
         final boolean witchNameplate = API.monsterConfig.getBoolean("WITCH_NAMEPLATES_ENABLED");
         final boolean witherSkeletonNameplate = API.monsterConfig.getBoolean("WITHERSKELETON_NAMEPLATES_ENABLED");
         final boolean shulkerNameplate = API.monsterConfig.getBoolean("SHULKER_NAMEPLATES_ENABLED");
         final boolean pillagerNameplate = API.monsterConfig.getBoolean("PILLAGER_NAMEPLATES_ENABLED");
         final boolean illusionerNameplate = API.monsterConfig.getBoolean("ILLUSIONER_NAMEPLATES_ENABLED");
         final boolean evokerNameplate = API.monsterConfig.getBoolean("EVOKER_NAMEPLATES_ENABLED");
         final boolean ravagerNameplate = API.monsterConfig.getBoolean("RAVAGER_NAMEPLATES_ENABLED");
         final boolean batNameplate = API.monsterConfig.getBoolean("BAT_NAMEPLATES_ENABLED");
         final boolean drownedNameplate = API.monsterConfig.getBoolean("DROWNED_NAMEPLATES_ENABLED");
         final boolean zombievillagerNameplate = API.monsterConfig.getBoolean("ZOMBIEVILLAGER_NAMEPLATES_ENABLED");
         final boolean polarBearNameplate = API.monsterConfig.getBoolean("POLARBEAR_NAMEPLATES_ENABLED");
         final boolean wanderingTraderNameplate = API.monsterConfig.getBoolean("WANDERINGTRADER_NAMEPLATES_ENABLED");
         final boolean donkeyNameplate = API.monsterConfig.getBoolean("DONKEY_NAMEPLATES_ENABLED");
         final boolean llamaNameplate = API.monsterConfig.getBoolean("LLAMA_NAMEPLATES_ENABLED");
         final boolean salmonNameplate = API.monsterConfig.getBoolean("SALMON_NAMEPLATES_ENABLED");

        //Monster SPEED SECTION
         final int zombieSpeed = API.monsterConfig.getInt("ZOMBIE_SPEED");
         final int wolfSpeed = API.monsterConfig.getInt("WOLF_SPEED");
         final int villagerSpeed = API.monsterConfig.getInt("VILLAGER_SPEED");
         final int squidSpeed = API.monsterConfig.getInt("SQUID_SPEED");
         final int spiderSpeed = API.monsterConfig.getInt("SPIDER_SPEED");
         final int snowmanSpeed = API.monsterConfig.getInt("SNOWMAN_SPEED");
         final int slimeSpeed = API.monsterConfig.getInt("SLIME_SPEED");
         final int skeletonSpeed = API.monsterConfig.getInt("SKELETON_SPEED");
         final int silverfishSpeed = API.monsterConfig.getInt("SILVERFISH_SPEED");
         final int sheepSpeed = API.monsterConfig.getInt("SHEEP_SPEED");
         final int rabbitSpeed = API.monsterConfig.getInt("RABBIT_SPEED");
         final int pigzombieSpeed = API.monsterConfig.getInt("PIGZOMBIE_SPEED");
         final int pigSpeed = API.monsterConfig.getInt("PIG_SPEED");
         final int mushroomcowSpeed = API.monsterConfig.getInt("MUSHROOMCOW_SPEED");
         final int magmacubeSpeed = API.monsterConfig.getInt("MAGMACUBE_SPEED");
         final int guardianSpeed = API.monsterConfig.getInt("GUARDIAN_SPEED");
         final int giantSpeed = API.monsterConfig.getInt("GIANT_SPEED");
         final int ghastSpeed = API.monsterConfig.getInt("GHAST_SPEED");
         final int endermiteSpeed = API.monsterConfig.getInt("ENDERMITE_SPEED");
         final int endermanSpeed = API.monsterConfig.getInt("ENDERMAN_SPEED");
         final int enderdragonSpeed = API.monsterConfig.getInt("ENDERDRAGON_SPEED");
         final int creeperSpeed = API.monsterConfig.getInt("CREEPER_SPEED");
         final int cowSpeed = API.monsterConfig.getInt("COW_SPEED");
         final int chickenSpeed = API.monsterConfig.getInt("CHICKEN_SPEED");
         final int cavespiderSpeed = API.monsterConfig.getInt("CAVESPIDER_SPEED");
         final int blazeSpeed = API.monsterConfig.getInt("BLAZE_SPEED");
         final int witchSpeed = API.monsterConfig.getInt("WITCH_SPEED");
         final int witherSkeletonSpeed = API.monsterConfig.getInt("WITHERSKELETON_SPEED");
         final int shulkerSpeed = API.monsterConfig.getInt("SHULKER_SPEED");
         final int pillagerSpeed = API.monsterConfig.getInt("PILLAGER_SPEED");
         final int illusionerSpeed = API.monsterConfig.getInt("ILLUSIONER_SPEED");
         final int evokerSpeed = API.monsterConfig.getInt("EVOKER_SPEED");
         final int ravagerSpeed = API.monsterConfig.getInt("RAVAGER_SPEED");
         final int batSpeed = API.monsterConfig.getInt("BAT_SPEED");
         final int drownedSpeed = API.monsterConfig.getInt("DROWNED_SPEED");
         final int zombievillagerSpeed = API.monsterConfig.getInt("ZOMBIEVILLAGER_SPEED");
         final int polarBearSpeed = API.monsterConfig.getInt("POLARBEAR_SPEED");
         final int wanderingTraderSpeed = API.monsterConfig.getInt("WANDERINGTRADER_SPEED");
         final int donkeySpeed = API.monsterConfig.getInt("DONKEY_SPEED");
         final int llamaSpeed = API.monsterConfig.getInt("LLAMA_SPEED");
         final int salmonSpeed = API.monsterConfig.getInt("SALMON_SPEED");

        //Monster MINIMUM SECTION
         final int zombieMinLevel = API.monsterConfig.getInt("ZOMBIE_MIN_LEVEL");
         final int wolfMinLevel = API.monsterConfig.getInt("WOLF_MIN_LEVEL");
         final int villagerMinLevel = API.monsterConfig.getInt("VILLAGER_MIN_LEVEL");
         final int squidMinLevel = API.monsterConfig.getInt("SQUID_MIN_LEVEL");
         final int spiderMinLevel = API.monsterConfig.getInt("SPIDER_MIN_LEVEL");
         final int snowmanMinLevel = API.monsterConfig.getInt("SNOWMAN_MIN_LEVEL");
         final int slimeMinLevel = API.monsterConfig.getInt("SLIME_MIN_LEVEL");
         final int skeletonMinLevel = API.monsterConfig.getInt("SKELETON_MIN_LEVEL");
         final int silverfishMinLevel = API.monsterConfig.getInt("SILVERFISH_MIN_LEVEL");
         final int sheepMinLevel = API.monsterConfig.getInt("SHEEP_MIN_LEVEL");
         final int rabbitMinLevel = API.monsterConfig.getInt("RABBIT_MIN_LEVEL");
         final int pigzombieMinLevel = API.monsterConfig.getInt("PIGZOMBIE_MIN_LEVEL");
         final int pigMinLevel = API.monsterConfig.getInt("PIG_MIN_LEVEL");
         final int mushroomcowMinLevel = API.monsterConfig.getInt("MUSHROOMCOW_MIN_LEVEL");
         final int magmacubeMinLevel = API.monsterConfig.getInt("MAGMACUBE_MIN_LEVEL");
         final int guardianMinLevel = API.monsterConfig.getInt("GUARDIAN_MIN_LEVEL");
         final int giantMinLevel = API.monsterConfig.getInt("GIANT_MIN_LEVEL");
         final int ghastMinLevel = API.monsterConfig.getInt("GHAST_MIN_LEVEL");
         final int endermiteMinLevel = API.monsterConfig.getInt("ENDERMITE_MIN_LEVEL");
         final int endermanMinLevel = API.monsterConfig.getInt("ENDERMAN_MIN_LEVEL");
         final int enderdragonMinLevel = API.monsterConfig.getInt("ENDERDRAGON_MIN_LEVEL");
         final int creeperMinLevel = API.monsterConfig.getInt("CREEPER_MIN_LEVEL");
         final int cowMinLevel = API.monsterConfig.getInt("COW_MIN_LEVEL");
         final int chickenMinLevel = API.monsterConfig.getInt("CHICKEN_MIN_LEVEL");
         final int cavespiderMinLevel = API.monsterConfig.getInt("CAVESPIDER_MIN_LEVEL");
         final int blazeMinLevel = API.monsterConfig.getInt("BLAZE_MIN_LEVEL");
         final int witchMinLevel = API.monsterConfig.getInt("WITCH_MIN_LEVEL");
         final int witherSkeletonMinLevel = API.monsterConfig.getInt("WITHERSKELETON_MIN_LEVEL");
         final int shulkerMinLevel = API.monsterConfig.getInt("SHULKER_MIN_LEVEL");
         final int pillagerMinLevel = API.monsterConfig.getInt("PILLAGER_MIN_LEVEL");
         final int illusionerMinLevel = API.monsterConfig.getInt("ILLUSIONER_MIN_LEVEL");
         final int evokerMinLevel = API.monsterConfig.getInt("EVOKER_MIN_LEVEL");
         final int ravagerMinLevel = API.monsterConfig.getInt("RAVAGER_MIN_LEVEL");
         final int batMinLevel = API.monsterConfig.getInt("BAT_MIN_LEVEL");
         final int drownedMinLevel = API.monsterConfig.getInt("DROWNED_MIN_LEVEL");
         final int zombievillagerMinLevel = API.monsterConfig.getInt("ZOMBIEVILLAGER_MIN_LEVEL");
         final int polarBearMinLevel = API.monsterConfig.getInt("POLARBEAR_MIN_LEVEL");
         final int wanderingTraderMinLevel = API.monsterConfig.getInt("WANDERINGTRADER_MIN_LEVEL");
         final int donkeyMinLevel = API.monsterConfig.getInt("DONKEY_MIN_LEVEL");
         final int llamaMinLevel = API.monsterConfig.getInt("LLAMA_MIN_LEVEL");
         final int salmonMinLevel = API.monsterConfig.getInt("SALMON_MIN_LEVEL");


        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

       /* BaseComponent[] page1 = new ComponentBuilder("ReitzMMO Entity Diary")
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://spigotmc.org"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to the spigot website!").create()))
                .create();
*/

        BaseComponent[] page1 = new ComponentBuilder("ReitzMMO")
                .bold(true)
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/reitzmmo.1159/"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to the plugin website").create()))
                .create();
        BaseComponent[] page2 = new ComponentBuilder("Chickens")
               .append("Base Health: " + chickenBaseHP)
                .append("Base Attack: " + "0")
                .append("Speed:" + chickenSpeed)
                .append("Minimum LV: " + chickenMinLevel)
                .create();

        //add the page to the meta
        Objects.requireNonNull(bookMeta).spigot().addPage(page1);
        bookMeta.spigot().addPage(page2);

//set the title and author of this book
        bookMeta.setTitle("ReitzMMO");
        bookMeta.setAuthor("Paully104");

//update the ItemStack with this new meta
        book.setItemMeta(bookMeta);
        p.getInventory().addItem(book);

    }
    public static void removeLoginBook(Player p)
    {
        for(ItemStack b : p.getInventory().getStorageContents()) {
            try {
                if (b.hasItemMeta()){
                    if (b.getItemMeta() instanceof  BookMeta) {
                        BookMeta meta = (BookMeta)b.getItemMeta();
                        if(Objects.requireNonNull(meta.getAuthor()).equals("Paully104") && Objects.requireNonNull(meta.getTitle()).equals("ReitzMMO")) {
                            p.getInventory().remove(b);
                        }
                    }

                }

            }
            catch(NullPointerException ignored)
            {

            }
        }




    }


}
