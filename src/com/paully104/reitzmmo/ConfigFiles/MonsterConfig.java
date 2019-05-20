package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 3/23/2016.
 */
public class MonsterConfig {
    public static void Configuration()
    {
        File file = FileManager.monsterHPConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all monster related configurations");
        configuration.addDefault("BLOCKS-PER-MOB-LEVEL", 100);
        configuration.addDefault("ATTACK-GAIN-PER-LEVEL", 1);
        configuration.addDefault("HEALTH-GAIN-PER-LEVEL", 1);
        configuration.addDefault("NAMEPLATES_ENABLED", true);

        configuration.addDefault("APPLY-ON-SPAWNER-SPAWNS", true);

        configuration.addDefault("BLAZE", true);
        configuration.addDefault("BLAZE_BASE_HP", 4);
        configuration.addDefault("BLAZE_BASE_ATTACK", 3);


        configuration.addDefault("CAVESPIDER", true);
        configuration.addDefault("CAVESPIDER_BASE_HP", 4);
        configuration.addDefault("CAVESPIDER_BASE_ATTACK", 3);

        configuration.addDefault("CHICKEN", true);
        configuration.addDefault("CHICKEN_BASE_HP", 4);

        configuration.addDefault("COW", true);
        configuration.addDefault("COW_BASE_HP", 2);

        configuration.addDefault("CREEPER", true);
        configuration.addDefault("CREEPER_BASE_ATTACK", 5);
        configuration.addDefault("CREEPER_BASE_HP", 8);

        configuration.addDefault("ENDERDRAGON", true);
        configuration.addDefault("ENDERDRAGON_BASE_HP", 4);
        configuration.addDefault("ENDERDRAGON_BASE_ATTACK", 3);

        configuration.addDefault("ENDERMAN", true);
        configuration.addDefault("ENDERMAN_BASE_HP", 5);
        configuration.addDefault("ENDERMAN_BASE_ATTACK", 3);

        configuration.addDefault("ENDERMITE", true);
        configuration.addDefault("ENDERMITE_BASE_HP", 4);
        configuration.addDefault("ENDERMITE_BASE_ATTACK", 3);

        configuration.addDefault("GHAST", true);
        configuration.addDefault("GHAST_BASE_HP", 4);
        configuration.addDefault("GHAST_BASE_ATTACK", 3);

        configuration.addDefault("GIANT", true);
        configuration.addDefault("GIANT_BASE_HP", 4);
        configuration.addDefault("GIANT_BASE_ATTACK", 3);

        configuration.addDefault("GOLEM", true);
        configuration.addDefault("GOLEM_BASE_HP", 100);
        configuration.addDefault("GOLEM_BASE_ATTACK", 10);

        configuration.addDefault("GUARDIAN", true);
        configuration.addDefault("GUARDIAN_BASE_HP", 4);
        configuration.addDefault("GUARDIAN_BASE_ATTACK", 3);

        configuration.addDefault("MAGMACUBE", true);
        configuration.addDefault("MAGMACUBE_BASE_HP", 4);
        configuration.addDefault("MAGMACUBE_BASE_ATTACK", 3);

        configuration.addDefault("MUSHROOMCOW", true);
        configuration.addDefault("MUSHROOMCOW_BASE_HP", 2);

        configuration.addDefault("PIG", true);
        configuration.addDefault("PIG_BASE_HP", 2);

        configuration.addDefault("PIGZOMBIE", true);
        configuration.addDefault("PIGZOMBIE_BASE_HP", 4);
        configuration.addDefault("PIGZOMBIE_BASE_ATTACK", 3);

        configuration.addDefault("RABBIT", false);
        configuration.addDefault("RABBIT_BASE_HP", 4);


        configuration.addDefault("SHEEP", true);
        configuration.addDefault("SHEEP_BASE_HP", 2);

        configuration.addDefault("SILVERFISH", true);
        configuration.addDefault("SILVERFISH_BASE_HP", 4);
        configuration.addDefault("SILVERFISH_BASE_ATTACK", 3);

        configuration.addDefault("SKELETON", true);
        configuration.addDefault("SKELETON_BASE_HP", 4);
        configuration.addDefault("SKELETON_BASE_ATTACK", 2);

        configuration.addDefault("SLIME", true);
        configuration.addDefault("SLIME_BASE_HP", 4);
        configuration.addDefault("SLIME_BASE_ATTACK", 3);

        configuration.addDefault("SNOWMAN", false);
        configuration.addDefault("SNOWMAN_BASE_HP", 4);

        configuration.addDefault("SPIDER", true);
        configuration.addDefault("SPIDER_BASE_HP", 5);
        configuration.addDefault("SPIDER_BASE_ATTACK", 3);


        configuration.addDefault("SQUID", true);
        configuration.addDefault("SQUID_BASE_HP", 4);

        configuration.addDefault("VILLAGER", false);
        configuration.addDefault("VILLAGER_BASE_HP", 4);

        configuration.addDefault("WOLF", true);
        configuration.addDefault("WOLF_BASE_HP", 4);
        configuration.addDefault("WOLF_BASE_ATTACK", 3);

        configuration.addDefault("ZOMBIE", true);
        configuration.addDefault("ZOMBIE_BASE_HP", 5);
        configuration.addDefault("ZOMBIE_BASE_ATTACK", 3);

        configuration.addDefault("WITCH", true);
        configuration.addDefault("WITCH_BASE_HP", 8);
        configuration.addDefault("WITCH_BASE_ATTACK", 4);

        configuration.addDefault("WITHERSKELETON", true);
        configuration.addDefault("WITHERSKELETON_BASE_HP", 8);
        configuration.addDefault("WITHERSKELETON_BASE_ATTACK", 4);

        configuration.addDefault("SHULKER", true);
        configuration.addDefault("SHULKER_BASE_HP", 8);
        configuration.addDefault("SHULKER_BASE_ATTACK", 4);

        configuration.addDefault("PILLAGER", true);
        configuration.addDefault("PILLAGER_BASE_HP", 8);
        configuration.addDefault("PILLAGER_BASE_ATTACK", 4);

        configuration.addDefault("ILLUSIONER", true);
        configuration.addDefault("ILLUSIONER_BASE_HP", 8);
        configuration.addDefault("ILLUSIONER_BASE_ATTACK", 4);

        configuration.addDefault("EVOKER", true);
        configuration.addDefault("EVOKER_BASE_HP", 8);
        configuration.addDefault("EVOKER_BASE_ATTACK", 4);

        configuration.addDefault("RAVAGER", true);
        configuration.addDefault("RAVAGER_BASE_HP", 8);
        configuration.addDefault("RAVAGER_BASE_ATTACK", 4);

        configuration.addDefault("BAT", true);
        configuration.addDefault("BAT_BASE_HP", 4);
        configuration.addDefault("BAT_BASE_ATTACK", 2);



        configuration.options().copyDefaults(true);

        try
        {
            configuration.save(file);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();



        }
    }
}
