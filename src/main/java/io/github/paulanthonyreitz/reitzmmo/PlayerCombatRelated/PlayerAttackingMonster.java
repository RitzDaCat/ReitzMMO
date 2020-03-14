package io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Enum.Weapon_Damage;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttackingMonster implements Listener {
    public static List<String> playerHasMusic = new ArrayList<>();

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");

    private final boolean namePlatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");

    private final int bowMinimumDamage = API.playerConfig.getInt("MinimumDamage.Arrow");

    private final boolean battleMusicEnabled = API.playerConfig.getBoolean("Music.BattleMusic.Enabled");

    private static final String ATTACK = "Attack";

    @EventHandler
    public void playerAttackingMonster(EntityDamageByEntityEvent e) {
        int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(e.getEntity().getLocation().getWorld())).getName());
        if (worldEnabled != -1) {
            Entity defender = e.getEntity();
            Entity attacker = e.getDamager();
            if (!(defender instanceof Player) && !defender.hasMetadata("NPC")) {
                int player_attack = 0;
                int monster_defense = 0;
                int damage_done = 0;
                int weaponDamage = 0;
                int weaponBonus = 0;
                int totalDamage = 0;
                if (attacker instanceof Player) {
                    String monster_level_from_name;
                    Player p = (Player)attacker;
                    if (!playerHasMusic.contains(p.getUniqueId().toString()) && this.battleMusicEnabled) {
                        p.playSound(p.getLocation(), Sound.MUSIC_DISC_11, 100.0F, 1.0F);
                        playerHasMusic.add(p.getUniqueId().toString());
                    }
                    PlayerData pd = API.Players.get(attacker.getUniqueId().toString());
                    player_attack = pd.getData().getInt("Attack");
                    try {
                        monster_level_from_name = ((String)Objects.<String>requireNonNull(defender.getCustomName())).replaceAll("\\D+", "");
                    } catch (NullPointerException error) {
                        String levelColor = ChatColor.YELLOW + "[" + 1 + "]";
                        defender.setCustomName(e.getEntityType() + levelColor);
                        monster_level_from_name = "1";
                        if (this.namePlatesEnabled)
                            defender.setCustomNameVisible(true);
                    }
                    monster_defense = Integer.parseInt(monster_level_from_name);
                    HumanEntity human = (HumanEntity)attacker;
                    if (!human.getInventory().getItemInMainHand().toString().contains("AIR")) {
                        try {
                            weaponDamage = Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().name()).getValue();
                        } catch (IllegalArgumentException ignored) {
                            weaponDamage = 0;
                        } finally {
                            weaponBonus = nameSpaceKey.getItemDamageFromContainer(human.getInventory().getItemInMainHand());
                            totalDamage = weaponDamage + weaponBonus + player_attack - monster_defense;
                            if (totalDamage < 1)
                                totalDamage = 1;
                            e.setDamage(totalDamage);
                        }
                    } else {
                        totalDamage = weaponDamage + weaponBonus + player_attack - monster_defense;
                        if (totalDamage < 1)
                            totalDamage = 1;
                    }
                    e.setDamage(totalDamage);
                    String uuid = p.getUniqueId().toString();
                    BossBar bar = createBossBar.playerBossBar.get(uuid);
                    createBossBar.updateBossBaronPlayer((Player)attacker, (LivingEntity)defender, damage_done);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API.plugin, () -> createBossBar.removeBossBaronPlayer(p), 300L);
                }
                if (attacker instanceof Arrow) {
                    Arrow arrow = (Arrow)attacker;
                    if (arrow.getShooter() instanceof Player) {
                        String monster_level_from_name;
                        Player p = (Player)arrow.getShooter();
                        double damage = e.getDamage();
                        PlayerData pd = (PlayerData)API.Players.get(p.getUniqueId().toString());
                        player_attack = pd.getData().getInt("Attack");
                        try {
                            monster_level_from_name = ((String)Objects.<String>requireNonNull(defender.getCustomName())).replaceAll("\\D+", "");
                        } catch (NullPointerException error) {
                            String levelColor = ChatColor.YELLOW + "[" + 1 + "]";
                            defender.setCustomName(e.getEntityType() + levelColor);
                            monster_level_from_name = "1";
                            if (this.namePlatesEnabled)
                                defender.setCustomNameVisible(true);
                        }
                        try {
                            monster_defense = Integer.parseInt(monster_level_from_name);
                        } catch (NumberFormatException f) {
                            monster_defense = 0;
                        }
                        damage = damage + player_attack - monster_defense;
                        if (damage < this.bowMinimumDamage)
                            damage = this.bowMinimumDamage + 0.0D;
                        e.setDamage(damage);
                    }
                }
            }
        }
    }
}
