package io.github.paulanthonyreitz.reitzmmo.PlayerData;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class PlayerData {
    private final String uuid;

    private final FileConfiguration config;

    private File file;

    private static File dir;

    private static Plugin plugin;

    private boolean debug;

    public static void setup(Plugin instance) {
        plugin = instance;
        dir = new File(plugin.getDataFolder() + File.separator + "Players");
        if (!dir.exists()) {
            dir.mkdir();
            plugin.getLogger().fine("The player data directories have been setup.");
        }
    }

    public PlayerData(String uuid) {
        this.uuid = uuid;
        this.file = new File(dir + File.separator + this.uuid + ".yml");
        if (this.file.exists()) {
            this.file = new File(dir + File.separator + this.uuid + ".yml");
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("The data file for " + this.uuid + " could not be created! Reason: " + e.getMessage());
            }
        }
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getData() {
        return this.config;
    }

    public File getFile() {
        return this.file;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            plugin.getLogger().severe("The data file for " + this.uuid + " could not be saved! Reason: " + e.getMessage());
            if (this.debug)
                e.printStackTrace();
        }
    }
}