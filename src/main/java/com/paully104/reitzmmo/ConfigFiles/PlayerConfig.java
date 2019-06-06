package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 3/23/2016.
 */
public class PlayerConfig {
    public static void Configuration()
    {
        File file = FileManager.playerConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all monster health related configurations");

        configuration.addDefault("CombatEXP", 50);
        configuration.addDefault("CombatEXP_MULTIPLIER", 2);
        configuration.addDefault("HealthScale",2);
        configuration.addDefault("AttackScale",2);
        configuration.addDefault("DefenseScale",2);

        configuration.addDefault("MinimumDamage",null);
        configuration.addDefault("MinimumDamage.Arrow",2);








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
