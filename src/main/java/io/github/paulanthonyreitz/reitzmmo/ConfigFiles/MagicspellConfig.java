package io.github.paulanthonyreitz.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 4/29/2017.
 */
public class MagicspellConfig {

    public static void Configuration()
    {
        File file = FileManager.magicspellConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("Magic Spell Settings");


        configuration.addDefault("Magic", null);
        configuration.addDefault("Magic.Spells", null);
        configuration.addDefault("Magic.Spells.Heal",null);
        configuration.addDefault("Magic.Spells.Heal.Enabled",true);
        configuration.addDefault("Magic.Spells.Heal.DurationInSeconds",10);
        configuration.addDefault("Magic.Spells.Heal.LevelRequirement",2);
        configuration.addDefault("Magic.Spells.Heal.FoodLoss",2);

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
