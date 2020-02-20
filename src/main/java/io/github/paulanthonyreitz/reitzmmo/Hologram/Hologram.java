package io.github.paulanthonyreitz.reitzmmo.Hologram;


import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

/**
 */


public class Hologram {


    public void setHologram(World w, Location location, int exp) {
        ArmorStand a = (ArmorStand)w.spawnEntity(location,EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        a.setCustomName(ChatColor.GREEN+ "+EXP: " + exp);
        a.setCustomNameVisible(true);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove,50L);}

}