package com.paully104.reitzmmo.Hologram;

import com.paully104.reitzmmo.ConfigFiles.API;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagInt;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class SpawnChest {

    private final int chestTimeUntilDisappear = API.lootConfig.getInt("General.BonusChest.TimeUntilDisappear");

    public void setChest(World w, Location location, String name,int monster_level) {



        Block block = location.getBlock();
        block.setType(Material.CHEST);
        org.bukkit.block.Chest chest = (org.bukkit.block.Chest)block.getState();
        ItemStack wooden_sword = new ItemStack(Material.WOODEN_SWORD, 1);
        chest.getInventory().addItem(wooden_sword);



        //spawn an armorstand where the chest is at

        ArmorStand a = (ArmorStand)w.spawnEntity(location, EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        a.setCustomName(ChatColor.GOLD+ name);
        a.setCustomNameVisible(true);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, a::remove,400L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
            public void run() {
                chest.getInventory().clear();
                block.setType(Material.AIR);
            }
        }, chestTimeUntilDisappear);


    }


}

