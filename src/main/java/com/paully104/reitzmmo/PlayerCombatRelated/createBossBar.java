package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Party_System.Party_Queue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.List;

import static com.paully104.reitzmmo.ConfigFiles.API.plugin;

public class createBossBar {

    public static final HashMap<String, BossBar> playerBossBar = new HashMap<>();
    public static final Boolean bossBarEnabled = API.playerConfig.getBoolean("BossBar.Enabled");

    public static void setBossBaronPlayer(Player p)
    {
       if(bossBarEnabled)
       {
           String uuid = p.getUniqueId().toString();
           BossBar bossbar = Bukkit.createBossBar("MobBar",BarColor.RED,BarStyle.SOLID);
           bossbar.setVisible(false);
           bossbar.addPlayer(p);
           playerBossBar.put(uuid,bossbar);
       }
       //now we can assume they have their "own" bar
    }

    public static void updateBossBaronPlayer(Player p, LivingEntity le, int damagedone)
    {
        if(!(le instanceof ArmorStand)  && bossBarEnabled) {
            String uuid = p.getUniqueId().toString();
            playerBossBar.get(uuid).setTitle(le.getCustomName());
            //progress must be between 0 and 1 so how do we normalize it?
            //we need to work in % of max hp
            double percentHealth = (le.getHealth() - damagedone) / le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            if(percentHealth < 0)
            {
                percentHealth = 0;
            }
            playerBossBar.get(uuid).setProgress(percentHealth);
            playerBossBar.get(uuid).setVisible(true);
        }



    }

    public static void removeBossBaronPlayer(Player p)
    {
        if(bossBarEnabled) {
            String uuid = p.getUniqueId().toString();
            playerBossBar.get(uuid).setVisible(false);
        }

    }
    public static void deleteBossBaronPlayer(Player p)
    {
        if(bossBarEnabled) {
            String uuid = p.getUniqueId().toString();
            playerBossBar.get(uuid).removeAll();//delete it?
        }

    }

}