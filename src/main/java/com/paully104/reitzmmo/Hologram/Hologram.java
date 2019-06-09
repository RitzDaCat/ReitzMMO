package com.paully104.reitzmmo.Hologram;


import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 */


public class Hologram {


    public void setHologram( World w, Location location,int exp) {
        ArmorStand a = (ArmorStand)w.spawnEntity(location,EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        a.setCustomName(ChatColor.GREEN+ "+EXP: " + exp);
        a.setCustomNameVisible(true);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove,50L);}

}