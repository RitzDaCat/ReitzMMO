package io.github.paulanthonyreitz.reitzmmo;


import io.github.paulanthonyreitz.reitzmmo.Citizens.npcCreateEvent;
import io.github.paulanthonyreitz.reitzmmo.Command_Handlers.Party_Commands;
import io.github.paulanthonyreitz.reitzmmo.Command_Handlers.ReitzRPGMain;
import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.*;
import io.github.paulanthonyreitz.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import io.github.paulanthonyreitz.reitzmmo.ItemData.craftingEvents;
import io.github.paulanthonyreitz.reitzmmo.Menu.Melee_Skills;
import io.github.paulanthonyreitz.reitzmmo.Menu.Menu;
import io.github.paulanthonyreitz.reitzmmo.Menu.Party_Menu;
import io.github.paulanthonyreitz.reitzmmo.Menu.Town_Menu;
import io.github.paulanthonyreitz.reitzmmo.Metrics.Metrics;
import io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated.MonsterCombatAbilities;
import io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated.MonsterLevelsDamage;
import io.github.paulanthonyreitz.reitzmmo.MonsterCombatRelated.MonsterLevelsHealth;
import io.github.paulanthonyreitz.reitzmmo.OnPlayerEvents.OnPlayerExitStatSave;
import io.github.paulanthonyreitz.reitzmmo.OnPlayerEvents.OnPlayerJoinStatSetup;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_API;
import io.github.paulanthonyreitz.reitzmmo.PlaceHolderAPI.registerPlaceHolders;
import io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated.MonsterDefeatsPlayer;
import io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated.PlayerAttackingMonster;
import io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated.PlayerDefeatsMonster;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import io.github.paulanthonyreitz.reitzmmo.Scoreboard.Partyboard;
import io.github.paulanthonyreitz.reitzmmo.Skills.onRightClickWeaponSkills;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    public static final String LEVEL = "Level";

    public static final String ATTACK = "Attack";

    public static final String HEALTH = "Health";

    public static final String COMBATEXP = "Combat-EXP";

    public static final String DISPLAYNAME = "DisplayName";

    public void onEnable() {
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        getLogger().info("ReitzRPGMMO is now enabled");
        API.plugin = this;
        PlayerData.setup(this);
        FileManager.FileManagerFiles();
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
        TownConfig.Configuration();
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
        API.setTownConfig();
        Objects.requireNonNull(getCommand("reitz")).setExecutor(new ReitzRPGMain());
        Objects.requireNonNull(getCommand("rrm")).setExecutor(new ReitzRPGMain());
        Objects.requireNonNull(getCommand("rparty")).setExecutor(new Party_Commands());
        registerEvents(this, new OnPlayerJoinStatSetup(), new MonsterLevelsHealth(), new OnPlayerExitStatSave(), new MonsterLevelsDamage(), new PlayerAttackingMonster(),
                new PlayerDefeatsMonster(), new Menu(), new Party_Menu(), new Melee_Skills(), new onRightClickWeaponSkills(),
                new Town_Menu(), new craftingEvents(), new Partyboard(), new npcCreateEvent(), new MonsterCombatAbilities(), new MonsterDefeatsPlayer());
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            pd.getData().set("UUID", uuid);
            int Level = pd.getData().getInt("Level");
            int Attack = pd.getData().getInt("Attack");
            double Health = pd.getData().getDouble("Health");
            int CombatEXP = pd.getData().getInt("Combat-EXP");
            if (Level == 0)
                pd.getData().set("Level", 1);
            if (Attack == 0)
                pd.getData().set("Attack", 1);
            if (Health == 0.0D) {
                pd.getData().set("Health", 20);
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20.0D);
            } else {
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Health);
            }
            if (CombatEXP == 0)
                pd.getData().set("Combat-EXP", 0);
            pd.getData().set("DisplayName", p.getDisplayName());
            pd.save();
            API.Players.put(p.getUniqueId().toString(), pd);
        }
        for (World world : Bukkit.getWorlds()) {
            for (ArmorStand stand : world.getEntitiesByClass(ArmorStand.class)) {
                try {
                    if (!stand.isVisible() && Objects.requireNonNull(stand.getCustomName()).contains("+EXP:"))
                        stand.remove();
                } catch (NullPointerException nullPointerException) {}
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
            registerPlaceHolders.registerPlaceHoldersReitzMMO();
    }

    public void onDisable() {
        Bukkit.broadcastMessage("[ReitzMMO] disabling... saving all users data");
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            System.err.println(p.getName() + " has exited the game!");
            Integer level = API.Players.get(uuid).getData().getInt("Level");
            Integer attack = API.Players.get(uuid).getData().getInt("Attack");
            Integer health = API.Players.get(uuid).getData().getInt("Health");
            Integer combatexp = API.Players.get(uuid).getData().getInt("Combat-EXP");
            pd.getData().set("Level", level);
            pd.getData().set("Attack", attack);
            pd.getData().set("Health", health);
            pd.getData().set("Combat-EXP", combatexp);
            pd.getData().set("DisplayName", p.getDisplayName());
            pd.save();
            if (Party_API.Party_Leaders.containsKey(name)) {
                p.performCommand("Rparty disband");
            } else if (Party_API.inParty.containsKey(name)) {
                p.performCommand("Rparty leave");
            }
            ReitzMMO_Book.removeLoginBook(p);
        }
        Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
    }

    private static void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners)
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
