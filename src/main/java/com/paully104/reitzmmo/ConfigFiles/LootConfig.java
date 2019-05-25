package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LootConfig {

    public static void Configuration()
    {
        File file = FileManager.lootConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to configure the loot table of mobs by level and mob type and you can add 1 item");

        configuration.addDefault("MOBSDROPATTACKUP", true);


        configuration.addDefault("1.ZOMBIE", 1);
        configuration.addDefault("1.ZOMBIE.chance", 10);
        configuration.addDefault("1.ZOMBIE.item", "BUCKET");

        configuration.addDefault("2.ZOMBIE", 2);
        configuration.addDefault("2.ZOMBIE.chance", 10);
        configuration.addDefault("2.ZOMBIE.item", "BREAD");









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
