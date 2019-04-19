package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 5/9/2017.
 */
public class CustomBowConfig {

    public static void Configuration()
    {
        File file = FileManager.customBowConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set custom bow settings");

        configuration.addDefault("WOOD_BOW_DAMAGE", 2);
        configuration.addDefault("STONE_BOW_DAMAGE", 4);
        configuration.addDefault("IRON_BOW_DAMAGE",6);
        configuration.addDefault("GOLD_BOW_DAMAGE",8);
        configuration.addDefault("DIAMOND_BOW_DAMAGE",10);









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
