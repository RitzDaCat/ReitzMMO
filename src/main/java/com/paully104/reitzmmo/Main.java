package com.paully104.reitzmmo;

import com.paully104.reitzmmo.Command_Handlers.Party_Commands;
import com.paully104.reitzmmo.Command_Handlers.ReitzRPGMain;
import com.paully104.reitzmmo.ConfigFiles.*;
import com.paully104.reitzmmo.Menu.Menu;
import com.paully104.reitzmmo.Menu.Party_Menu;
import com.paully104.reitzmmo.Metrics.Metrics;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
import com.paully104.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
import com.paully104.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
import com.paully104.reitzmmo.Party_System.Party_API;
import com.paully104.reitzmmo.Party_System.Scoreboard_Custom;
import com.paully104.reitzmmo.PlaceHolderAPI.registerPlaceHolders;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
import com.paully104.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Created by Paul on 3/22/2016.
 */
public class Main extends JavaPlugin {
//build location: C:\Users\Paul\IdeaProjects\ReitzMMO\out\artifacts\ReitzMMO_jar
    //update 4/25/2017


    public static final String LEVEL = "Level";
    public static final String ATTACK = "Attack";
    public static final String HEALTH = "Health";
    public static final String COMBATEXP = "Combat-EXP";
    public static final String DISPLAYNAME = "DisplayName";

    @Override
    public void onEnable(){

        Metrics metrics = new Metrics(this);

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));


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
        Objects.requireNonNull(this.getCommand("reitz")).setExecutor(new ReitzRPGMain());
        Objects.requireNonNull(this.getCommand("rrm")).setExecutor(new ReitzRPGMain());
        Objects.requireNonNull(this.getCommand("rparty")).setExecutor(new Party_Commands());

        //Register Events
        //removed weaponskills and weaponskill menu
        registerEvents(this,new OnPlayerJoinStatSetup(), new MonsterLevelsHealth(), new OnPlayerExitStatSave(),
        new MonsterLevelsDamage(), new PlayerAttackingMonster(),new PlayerDefeatsMonster(), new Menu(), new Party_Menu(),
                new Scoreboard_Custom());

        //SetCustomItems


        //if they reloaded the server people might be on, lets set their stats
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {

            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            pd.getData().set("UUID", uuid);

            int Level = pd.getData().getInt(LEVEL);
            int Attack = pd.getData().getInt(ATTACK);
            double Health = pd.getData().getDouble(HEALTH);
            int CombatEXP = pd.getData().getInt(COMBATEXP);

            if (Level == 0) {
                pd.getData().set(LEVEL, 1);

            }
            if (Attack == 0) {
                pd.getData().set(ATTACK, 1);

            }
            if (Health == 0.0) {
                pd.getData().set(HEALTH, 20);
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);


            } else {

                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Health);
            }
            if (CombatEXP == 0) {
                pd.getData().set(COMBATEXP, 0);

            }
            pd.getData().set(DISPLAYNAME,p.getDisplayName());
            pd.save();
            API.Players.put(p.getUniqueId().toString(), pd); //this loads the player data into the API

            //Lets give the book
            //ReitzMMO_Book.setLoginBook(p);

        }

        for(World world : Bukkit.getWorlds())
        {
            for(ArmorStand stand : world.getEntitiesByClass(ArmorStand.class))
            {
                if(!(stand.isVisible() && Objects.requireNonNull(stand.getCustomName()).contains("+EXP:")))
                {
                        stand.remove();

                }
            }

        }


        //placeholder nonsense
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
        {
            registerPlaceHolders.registerPlaceHoldersReitzMMO();



        }

    }
    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
        Bukkit.broadcastMessage("[ReitzMMO] disabling... saving all users data");
        for (Player p : Bukkit.getServer().getOnlinePlayers())
        {
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            System.err.println(p.getName() + " has exited the game!");

            //get stats from API
            Integer level = API.Players.get(uuid).getData().getInt(LEVEL);
            Integer attack = API.Players.get(uuid).getData().getInt(ATTACK);
            Integer health = API.Players.get(uuid).getData().getInt(HEALTH);
            Integer combatexp = API.Players.get(uuid).getData().getInt(COMBATEXP);

            //Save stats
            pd.getData().set(LEVEL, level);
            pd.getData().set(ATTACK, attack);
            pd.getData().set(HEALTH, health);
            pd.getData().set(COMBATEXP, combatexp);
            pd.getData().set(DISPLAYNAME,p.getDisplayName());
            pd.save();


            //They disconnect make sure their party status is removed!
            if (Party_API.Party_Leaders.containsKey(name))
            {
                p.performCommand("Rparty disband");

            }
            else if (Party_API.inParty.containsKey(name))
            {
                p.performCommand("Rparty leave");
            }
            //Remove Book
            //ReitzMMO_Book.removeLoginBook(p);

        }
        Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
    }

    private static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
