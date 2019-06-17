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
        configuration.options().header("WeaponSkill Settings");


        configuration.addDefault("Swords", null);
        configuration.addDefault("Swords.WeaponSkills", null);
        configuration.addDefault("Swords.WeaponSkills.Under_Fire",null);
        configuration.addDefault("Swords.WeaponSkills.Under_Fire.Enabled",true);
        configuration.addDefault("Swords.WeaponSkills.Under_Fire.DurationInSeconds",10);
        configuration.addDefault("Swords.WeaponSkills.Under_Fire.MovementSpeedIncreasePercent",200);
        configuration.addDefault("Swords.WeaponSkills.Under_Fire.LevelRequirement",2);








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
