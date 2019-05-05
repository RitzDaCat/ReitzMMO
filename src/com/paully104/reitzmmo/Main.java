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
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
import com.paully104.reitzmmo.Party_System.Scoreboard_Custom;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Paul on 3/22/2016.
 */
public class Main extends JavaPlugin {
//build location: C:\Users\Paul\IdeaProjects\ReitzMMO\out\artifacts\ReitzMMO_jar
    //update 4/25/2017


    @Override
    public void onEnable(){
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

        //Set API data for quicker config reading
        API.setMonsterConfig();
        API.setPlayerConfig();
        API.setDebugConfig();
        API.setWorldConfig();
        API.setPartyConfig();
        API.setWeaponskillConfig();
        API.setcustombowConfig();

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

    }
    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

    private static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
