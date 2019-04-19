package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 4/29/2017.
 */
public class WeaponskillConfig {

    public static void Configuration()
    {
        File file = FileManager.weaponskillConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set weaponskill settings");

        configuration.addDefault("Spin_Attack_Level", 2);
        configuration.addDefault("Spin_Attack_Base_Damage", 3);
        configuration.addDefault("Spin_Attack_Damage_Scale",2);








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
