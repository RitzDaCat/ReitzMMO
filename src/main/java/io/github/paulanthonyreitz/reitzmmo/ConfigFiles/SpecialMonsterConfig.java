package io.github.paulanthonyreitz.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpecialMonsterConfig {

    public static void Configuration()
    {
        File file = FileManager.specialMonsterConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to configure special monsters");

        configuration.addDefault("specialMonsterGlowEnabled", true);
        configuration.addDefault("specialMonsterSilentEnabled", true);
        configuration.addDefault("kingMobsEnabled", true);
        configuration.addDefault("kingMobsLVDifference", 25);

        configuration.addDefault("notoriousMobsEnabled", true);
        configuration.addDefault("notoriousMobsLVDifference", 15);

        configuration.addDefault("devilishMobsEnabled", true);
        configuration.addDefault("devilishMobsLVDifference", 5);

        configuration.addDefault("dumbMobsEnabled", true);
        configuration.addDefault("dumbMobsLVDifference", -5);









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
