package com.paully104.reitzmmo.ConfigFiles;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager
{
    public static File monsterHPConfig;
    public static File playerConfig;
    public static File debugConfig;
    public static File worldConfig;
    public static File partyConfig;
    public static File weaponskillConfig;
    public static File customBowConfig;



    public static void FileManagerFiles()
    {

        monsterHPConfig = new File("plugins/ReitzMMO/MonsterSettings/MonsterConfig.yml");
        playerConfig = new File("plugins/ReitzMMO/PlayerSettings/PlayerConfig.yml");
        debugConfig = new File("plugins/ReitzMMO/DebugSettings/DebugConfig.yml");
        worldConfig = new File("plugins/ReitzMMO/WorldSettings/WorldConfig.yml");
        partyConfig = new File("plugins/ReitzMMO/PartySettings/PartyConfig.yml");
        weaponskillConfig = new File("plugins/ReitzMMO/WeaponSkillSettings/WeaponskillConfig.yml");
        customBowConfig = new File("plugins/ReitzMMO/CustomWeaponSettings/CustomBowConfig.yml");


    }
    public static void FileManagerSave(File file)
    {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
