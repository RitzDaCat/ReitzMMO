package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TownConfig {

    public static void Configuration()
    {
        File file = FileManager.townConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("Town information for Town Menu");

        configuration.addDefault("Towns", null);
        configuration.addDefault("Towns.StartingTown", null);
        configuration.addDefault("Towns.StartingTown.Name", "Starting Town");
        configuration.addDefault("Towns.StartingTown.World", "World");
        configuration.addDefault("Towns.StartingTown.X", 38.809);
        configuration.addDefault("Towns.StartingTown.Y", 77);
        configuration.addDefault("Towns.StartingTown.Z", -30.477);

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
