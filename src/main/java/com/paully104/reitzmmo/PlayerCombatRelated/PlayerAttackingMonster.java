package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Enum.Weapon_Damage;
import com.paully104.reitzmmo.ItemData.nameSpaceKey;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.paully104.reitzmmo.ConfigFiles.API.plugin;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerAttackingMonster implements Listener {

    public static List<String> playerHasMusic = new ArrayList<>();
    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");
    private final boolean namePlatesEnabled = API.monsterConfig.getBoolean("General.nameplates-enabled");
    private final int bowMinimumDamage = API.playerConfig.getInt("MinimumDamage.Arrow");
    private final boolean battleMusicEnabled = API.playerConfig.getBoolean("Music.BattleMusic.Enabled");


    private final static String ATTACK = "Attack";

    @EventHandler
    public void playerAttackingMonster(EntityDamageByEntityEvent e) {
        int worldEnabled = API.worldConfig.getInt(Objects.requireNonNull(e.getEntity().getLocation().getWorld()).getName());
        if (worldEnabled != -1)
        {


            Entity defender = e.getEntity();//monster
            Entity attacker = e.getDamager();//player

            //We do not want to use this for PVP
            if (!(defender instanceof Player) && !(defender.hasMetadata("NPC"))) {


                int player_attack = 0;
                int monster_defense = 0;
                int damage_done = 0;
                int weaponDamage = 0;
                int weaponBonus = 0;
                int totalDamage = 0;
                String monster_level_from_name;

                if (attacker instanceof Player) {

                    Player p = (Player) attacker;
                    if (!playerHasMusic.contains(p.getUniqueId().toString()) && battleMusicEnabled) {
                        p.playSound(p.getLocation(), Sound.MUSIC_DISC_11, 100f, 1f);
                        playerHasMusic.add(p.getUniqueId().toString());
                    }
                    PlayerData pd = API.Players.get(attacker.getUniqueId().toString());
                    player_attack = pd.getData().getInt(ATTACK);
                    try {
                        monster_level_from_name = Objects.requireNonNull(defender.getCustomName()).replaceAll("\\D+", "");
                    } catch (NullPointerException error) {
                        String levelColor = ChatColor.YELLOW + "[" + 1 + "]";
                        defender.setCustomName(e.getEntityType() + levelColor);
                        monster_level_from_name = "1";
                        if (namePlatesEnabled) {
                            defender.setCustomNameVisible(true);
                        }
                    }
                    monster_defense = Integer.parseInt(monster_level_from_name);
                    //Damage done is player attack - monster defence so a lv 2 attack against a lv 1 would do 1 damage
                    HumanEntity human = (HumanEntity) attacker;

                    //if in list
                    if (!(human.getInventory().getItemInMainHand().toString().contains("AIR"))) {
                        try {
                            weaponDamage = (Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().name()).getValue());
                        } catch (IllegalArgumentException ignored) {
                            weaponDamage = 0;
                        }
                        finally
                        {

                            weaponBonus = nameSpaceKey.getItemDamageFromContainer(human.getInventory().getItemInMainHand());
                            totalDamage = (weaponDamage + weaponBonus + player_attack) - monster_defense;
                            if (totalDamage < 1) {
                                totalDamage = 1;
                            }
                            //System.out.println(totalDamage);
                            e.setDamage(totalDamage);
                        }

                    } else
                        {
                        //empty handed
                        totalDamage = (weaponDamage + weaponBonus + player_attack) - monster_defense;
                        if (totalDamage < 1) {
                            totalDamage = 1;
                        }
                        //System.out.println("empty handed" + totalDamage);


                    }

                    e.setDamage(totalDamage);

                    //end of player attacking monster lets do our bossBar here
                    //createBossBar bar = new createBossBar();
                    String uuid = p.getUniqueId().toString();
                    BossBar bar = createBossBar.playerBossBar.get((uuid));
                    createBossBar.updateBossBaronPlayer((Player)attacker,(LivingEntity)defender,damage_done);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        createBossBar.removeBossBaronPlayer(p);
                        //YOUR MESSAGE TO SAY AFTER THEY SAY STUFF
                    }, 300); //100 MEANS WAIT 5 SECCONDS BEFORE RUNNING THE CODE ABOVE


                }
                if(attacker instanceof  Arrow)
                {
                    Arrow arrow = (Arrow) attacker;
                    if(arrow.getShooter() instanceof Player)
                    {
                        //arrow and shot by a player
                        Player p = (Player)arrow.getShooter();
                        double damage = e.getDamage();
                        PlayerData pd = API.Players.get(p.getUniqueId().toString());
                        player_attack = pd.getData().getInt(ATTACK);

                        try
                        {
                            monster_level_from_name = Objects.requireNonNull(defender.getCustomName()).replaceAll("\\D+", "");
                        } catch (NullPointerException error)
                        {
                            String levelColor = ChatColor.YELLOW + "[" + 1 + "]";
                            defender.setCustomName(e.getEntityType() + levelColor);
                            monster_level_from_name = "1";
                            if (namePlatesEnabled) {
                                defender.setCustomNameVisible(true);
                            }
                        }
                        try
                        {
                            monster_defense = Integer.parseInt(monster_level_from_name);
                        }
                        catch (NumberFormatException f)
                        {
                            monster_defense = 0;

                        }
                        damage = (damage + player_attack) - monster_defense;
                        if(damage < bowMinimumDamage)
                        {
                            damage = bowMinimumDamage + .0;
                        }
                        e.setDamage(damage);



                    }




                }
            }
        }
    }
}
