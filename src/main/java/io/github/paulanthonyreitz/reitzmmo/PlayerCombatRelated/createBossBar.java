package io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class createBossBar {
    public static final HashMap<String, BossBar> playerBossBar = new HashMap<>();

    public static final Boolean bossBarEnabled = Boolean.valueOf(API.playerConfig.getBoolean("BossBar.Enabled"));

    public static void setBossBaronPlayer(Player p) {
        if (bossBarEnabled.booleanValue()) {
            String uuid = p.getUniqueId().toString();
            BossBar bossbar = Bukkit.createBossBar("MobBar", BarColor.RED, BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
            bossbar.setVisible(false);
            bossbar.addPlayer(p);
            playerBossBar.put(uuid, bossbar);
        }
    }

    public static void updateBossBaronPlayer(Player p, LivingEntity le,int damagedone) {
        if (!(le instanceof org.bukkit.entity.ArmorStand) && bossBarEnabled.booleanValue()) {
            String uuid = p.getUniqueId().toString();
            ((BossBar)playerBossBar.get(uuid)).setTitle(le.getCustomName());
            double percentHealth = (le.getHealth() - damagedone) / le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            //double percentHealth = le.getHealth() / le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            if (percentHealth < 0.0D)
                percentHealth = 0.0D;
            playerBossBar.get(uuid).setProgress(percentHealth);
            playerBossBar.get(uuid).setVisible(true);
        }
    }

    public static void removeBossBaronPlayer(Player p) {
        if (bossBarEnabled.booleanValue()) {
            String uuid = p.getUniqueId().toString();
            playerBossBar.get(uuid).setVisible(false);
        }
    }

    public static void deleteBossBaronPlayer(Player p) {
        if (bossBarEnabled.booleanValue()) {
            String uuid = p.getUniqueId().toString();
            playerBossBar.get(uuid).removeAll();
        }
    }
}
