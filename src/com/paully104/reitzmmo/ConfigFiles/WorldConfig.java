package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 7/29/2016.
 */
public class WorldConfig {

    public static void Configuration()
    {
        File file = FileManager.worldConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all world levels configurations.\n Use -1 to make the plugin disabled on a world");

        configuration.addDefault("world", 1);
        configuration.addDefault("world_nether", 15);
        configuration.addDefault("world_the_end", 30);
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
