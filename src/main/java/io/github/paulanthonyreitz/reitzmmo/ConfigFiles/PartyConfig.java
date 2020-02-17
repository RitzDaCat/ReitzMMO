package io.github.paulanthonyreitz.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 8/1/2016.
 */
public class PartyConfig {
    public static void Configuration()
    {
        File file = FileManager.partyConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all party configurations");

        configuration.addDefault("Parties_Enabled", true);
        configuration.addDefault("PartyEXPMaxDistance", 100);
        configuration.addDefault("Party_Scoreboard",null);
        configuration.addDefault("Party_Scoreboard.Enabled",false);










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
