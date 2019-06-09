package com.paully104.reitzmmo.PlayerData;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * A class to create (and get) configuration files for individual players.
 * <br>
 * Use this as opposed to storing all of your players' data in one config.yml.
 * @author Wizehh
 */

public class PlayerData {

    private final String uuid;
    private final FileConfiguration config;
    private File file;
    private static File dir;
    private static Plugin plugin;
    private boolean debug;


    /**
     * Configures the directory and allows access to the plugin.
     * <br>
     * There <em>will</em> be a NullPointerException if you don't have this in your onEnable() method!
     * @param instance your plugin's main class
     */

    public static void setup(Plugin instance) {
        plugin = instance;
        dir = new File(plugin.getDataFolder() + File.separator + "Players");
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdir();
            plugin.getLogger().fine("The player data directories have been setup.");
        }
    }

    /**
     * Creates a new player data object.
     * If the file doesn't exist, it will be created
     * @param uuid the uuid of the player
     */

    public PlayerData(String uuid) {
        this.uuid = uuid;
        this.file = new File(dir + File.separator + this.uuid + ".yml");
        if (file.exists()) {
            file = new File(dir + File.separator + this.uuid + ".yml");
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("The data file for " + this.uuid + " could not be created! Reason: " + e.getMessage());
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Returns the configuration object
     * @return the config
     */

    public FileConfiguration getData() {
        return this.config;
    }

    /**
     * Returns the hard file
     * @return the file
     */

    public File getFile() {
        return this.file;
    }

    /**
     * Attempts to save the configuration file.
     * <h1>You must do this after making any edits to the file!
     */

    public void save() {
        try {
            config.save(this.file);
        } catch (IOException e) {
            plugin.getLogger().severe("The data file for " + this.uuid + " could not be saved! Reason: " + e.getMessage());
            if (this.debug)
                e.printStackTrace();
        }
    }

}
