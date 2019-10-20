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

        configuration.addDefault("BossBar",null);
        configuration.addDefault("BossBar.Enabled",true);
        configuration.addDefault("Music",null);
        configuration.addDefault("Music.BattleMusic",null);
        configuration.addDefault("Music.BattleMusic.Enabled",false);
        configuration.addDefault("Scaling.World",null);
        configuration.addDefault("Scaling.World.WorldBaseCombatEXP", null);
        configuration.addDefault("Scaling.World.WorldBaseCombatEXP.Base", 25);
        configuration.addDefault("Scaling.World.WorldBaseCombatEXP.Multiplier", 2);
        configuration.addDefault("Scaling.Player.HealthScale",2);
        configuration.addDefault("Scaling.Player.AttackScale",2);
        configuration.addDefault("Scaling.Player.DefenseScale",2);
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
