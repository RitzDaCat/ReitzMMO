package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 3/23/2016.
 */
public class DebugConfig {
    public static void Configuration()
    {
        File file = FileManager.debugConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all debug options");

        configuration.addDefault("MonsterAttackingPlayer", true);
        configuration.addDefault("PlayerAttackingMonster", true);
        configuration.addDefault("PlayerLevelUp", true);
        configuration.addDefault("PartyEXP", true);






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
