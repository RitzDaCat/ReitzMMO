package com.paully104.reitzmmo;

import com.paully104.reitzmmo.Command_Handlers.Party_Commands;
import com.paully104.reitzmmo.Command_Handlers.ReitzRPGMain;
import com.paully104.reitzmmo.ConfigFiles.*;
import com.paully104.reitzmmo.Custom_Recipes.Custom_Arrows;
import com.paully104.reitzmmo.Custom_Recipes.Custom_Bows;
import com.paully104.reitzmmo.Menu.Menu;
import com.paully104.reitzmmo.Menu.Party_Menu;
import com.paully104.reitzmmo.Menu.Town_Menu;
import com.paully104.reitzmmo.Menu.Weaponskill_Menu;
import com.paully104.reitzmmo.Metrics.MetricsLite;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
import com.paully104.reitzmmo.Party_System.Scoreboard_Custom;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import com.sun.javafx.font.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * Created by Paul on 3/22/2016.
 */
public class Main extends JavaPlugin {
//build location: C:\Users\Paul\IdeaProjects\ReitzMMO\out\artifacts\ReitzMMO_jar
    //update 4/25/2017


    @Override
    public void onEnable(){

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            System.out.print("Failed to start metrics...");
            // Failed to submit the stats :-(
        }
        //Fired when the server enables the plugin
        getLogger().info("ReitzRPGMMO is now enabled");
        API.plugin = this;
        //Setup PlayerData
        PlayerData.setup(this);

        //Register Config Files
        FileManager.FileManagerFiles();

        //Set Config File Data
        MonsterConfig.Configuration();
        PlayerConfig.Configuration();
        DebugConfig.Configuration();
        WorldConfig.Configuration();
        PartyConfig.Configuration();
        WeaponskillConfig.Configuration();
        CustomBowConfig.Configuration();
        ChatConfig.Configuration();
        SpecialMonsterConfig.Configuration();
        LootConfig.Configuration();
        MenuConfig.Configuration();

        //Set API data for quicker config reading
        API.setMonsterConfig();
        API.setPlayerConfig();
        API.setDebugConfig();
        API.setWorldConfig();
        API.setPartyConfig();
        API.setWeaponskillConfig();
        API.setcustombowConfig();
        API.setChatConfig();
        API.setSpecialMonsterConfig();
        API.setLootConfig();
        API.setMenuConfig();

        //Main Commands
        this.getCommand("reitz").setExecutor(new ReitzRPGMain());
        this.getCommand("rrm").setExecutor(new ReitzRPGMain());
        this.getCommand("rparty").setExecutor(new Party_Commands());

        //Register Events
        //removed weaponskills and weaponskill menu
        registerEvents(this,new OnPlayerJoinStatSetup(), new MonsterLevelsHealth(), new OnPlayerExitStatSave(),
        new MonsterLevelsDamage(), new PlayerAttackingMonster(),new PlayerDefeatsMonster(), new Menu(), new Party_Menu(),
                new Scoreboard_Custom());

        //SetCustomItems
        Custom_Arrows.setCustomArrow();
        Custom_Bows.setCustomWoodBow();
        Custom_Bows.setStoneBow();
        Custom_Bows.setIronBow();
        Custom_Bows.setGoldBow();
        Custom_Bows.setDiamondBow();
        Custom_Bows.setLlamaBow();

        //if they reloaded the server people might be on, lets set their stats
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {

            PlayerData pd = new PlayerData(p.getName());
            pd.getData().set("Name", p.getName());

            Integer Level = pd.getData().getInt("Level");
            Integer Attack = pd.getData().getInt("Attack");
            Double Health = pd.getData().getDouble("Health");
            Integer CombatEXP = pd.getData().getInt("Combat-EXP");



            pd.getData().set("Name", p.getName());
            if (Level == 0) {
                pd.getData().set("Level", 1);

            }
            if (Attack == 0) {
                pd.getData().set("Attack", 1);

            }
            if (Health == 0.0) {
                pd.getData().set("Health", 20);
                p.setMaxHealth(20);

            } else {

                p.setMaxHealth(Health);
            }
            if (CombatEXP == 0) {
                pd.getData().set("Combat-EXP", 0);

            }
            pd.save();
            API.Players.put(p.getName(), pd); //this loads the player data into the API

        }

    }
    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
        Bukkit.broadcastMessage("[ReitzMMO] disabling... saving all users data");
        for (Player p : Bukkit.getServer().getOnlinePlayers())
        {
            //Get player information;
            PlayerData pd = new PlayerData(p.getName());
            String name = p.getName();
            System.out.println(p.getName() + "[ReitzRPG] has been saved!");

            //get stats from API
            Integer level = API.Players.get(name).getData().getInt("Level");
            Integer attack = API.Players.get(name).getData().getInt("Attack");
            Integer health = API.Players.get(name).getData().getInt("Health");
            Integer combatexp = API.Players.get(name).getData().getInt("Combat-EXP");

            //Save stats
            pd.getData().set("Level", level);
            pd.getData().set("Attack", attack);
            pd.getData().set("Health", health);
            pd.getData().set("Combat-EXP", combatexp);
            pd.save();

        }
        Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
    }

    private static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
