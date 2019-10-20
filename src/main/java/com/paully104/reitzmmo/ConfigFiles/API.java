package com.paully104.reitzmmo.ConfigFiles;

import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * Created by Paul on 3/22/2016.
 */
public class API {
    //Configs
    //UUID STRING then playerData
    public static final HashMap<String, PlayerData> Players = new HashMap<>();
    public static FileConfiguration monsterConfig;
    public static FileConfiguration playerConfig;
    public static FileConfiguration debugConfig;
    public static FileConfiguration worldConfig;
    public static FileConfiguration partyConfig;
    public static FileConfiguration weaponskillConfig;
    public static FileConfiguration custombowConfig;
    public static FileConfiguration chatConfig;
    public static FileConfiguration specialMonsterConfig;
    public static FileConfiguration lootConfig;
    public static FileConfiguration menuConfig;
    public static FileConfiguration townConfig;
    public static Plugin plugin;

    //Start of sets
    public static void setMonsterConfig() {monsterConfig = YamlConfiguration.loadConfiguration(FileManager.monsterHPConfig);}
    public static void setPlayerConfig() {playerConfig = YamlConfiguration.loadConfiguration(FileManager.playerConfig);}
    public static void setPartyConfig() {partyConfig = YamlConfiguration.loadConfiguration(FileManager.partyConfig);}
    public static void setWorldConfig() {
        worldConfig = YamlConfiguration.loadConfiguration(FileManager.worldConfig);
    }
    public static void setDebugConfig() {
        debugConfig = YamlConfiguration.loadConfiguration(FileManager.debugConfig);
    }
    public static void setWeaponskillConfig() {weaponskillConfig = YamlConfiguration.loadConfiguration(FileManager.weaponskillConfig);}
    public static void setcustombowConfig() {custombowConfig = YamlConfiguration.loadConfiguration(FileManager.customBowConfig);}
    public static void setChatConfig() {chatConfig = YamlConfiguration.loadConfiguration(FileManager.chatConfig);}
    public static void setSpecialMonsterConfig() {specialMonsterConfig = YamlConfiguration.loadConfiguration(FileManager.specialMonsterConfig);}
    public static void setLootConfig() {lootConfig = YamlConfiguration.loadConfiguration(FileManager.lootConfig);}
    public static void setMenuConfig() {menuConfig = YamlConfiguration.loadConfiguration(FileManager.menuConfig);}
    public static void setTownConfig() {townConfig = YamlConfiguration.loadConfiguration(FileManager.townConfig);}


    public static int getPlayerDataFromAPI(Player p, String requestedStat)
    {

        return API.Players.get(p.getUniqueId().toString()).getData().getInt(requestedStat);
    }
    public static void setPlayerDataFromAPI(Player p, String requestedStat, int value)
    {
        Players.get(p.getUniqueId().toString()).getData().set(requestedStat,value);

    }
    public static FileConfiguration getTownConfig()
    {
        return townConfig;
    }
}

