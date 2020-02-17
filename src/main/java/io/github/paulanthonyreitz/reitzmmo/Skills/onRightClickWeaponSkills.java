package io.github.paulanthonyreitz.reitzmmo.Skills;

import java.util.ArrayList;
import java.util.List;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.ItemData.nameSpaceKey;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class onRightClickWeaponSkills implements Listener {
    ArrayList<String> underFireUsers = new ArrayList<>();

    private final boolean underFireEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Under_Fire.Enabled");

    private final int underFireDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurationInSeconds");

    private final int underFireSpeedIncrease = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.MovementSpeedIncreasePercent");

    private final int underFireLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.LevelRequirement");

    private final int underFireDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Under_Fire.DurabilityLoss");

    private final boolean knockbackEnabled = API.weaponskillConfig.getBoolean("Melee.WeaponSkills.Knockback.Enabled");

    private final int knockbackDuration = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurationInSeconds");

    private final int knockbackSpeedIncrease = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.MovementSpeedIncreasePercent");

    private final int knockbackLevelNeeded = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.LevelRequirement");

    private final int knockbackDurabilityLoss = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.DurabilityLoss");

    private final int knockbackStrength = API.weaponskillConfig.getInt("Melee.WeaponSkills.Knockback.KnockbackStrength");

    public void knockback(Player p, Entity t) {
        Location l = t.getLocation().subtract(p.getLocation());
        double distance = t.getLocation().distance(p.getLocation());
        Vector v = l.toVector().multiply(this.knockbackStrength / distance);
        t.setVelocity(v);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();
        PlayerData pd = (PlayerData)API.Players.get(uuid);
        int level = pd.getData().getInt("Level");
        ItemStack modifiedItem = p.getInventory().getItemInMainHand();
        if (modifiedItem.hasItemMeta()) {
            String weaponSkill = nameSpaceKey.getItemWeaponSkillFromContainer(p.getInventory().getItemInMainHand());
            Damageable im = (Damageable)modifiedItem.getItemMeta();
            int currentDamage = im.getDamage();
            int maxDamage = modifiedItem.getType().getMaxDurability();
            if (currentDamage < maxDamage) {
                if (level >= this.underFireLevelNeeded && !this.underFireUsers.contains(p.getUniqueId().toString()) && this.underFireEnabled && weaponSkill.equalsIgnoreCase("Under Fire") && (event.getAction() == Action.RIGHT_CLICK_AIR || event
                        .getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    p.getPlayer().setWalkSpeed(0.2F * (this.underFireSpeedIncrease / 100));
                    this.underFireUsers.add(p.getUniqueId().toString());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Under Fire [Activated]")).color(ChatColor.GREEN).create());
                    int damage = this.underFireDurabilityLoss;
                    im.setDamage(currentDamage + damage);
                    modifiedItem.setItemMeta((ItemMeta)im);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(API.plugin, new Runnable() {
                        public void run() {
                            p.getPlayer().setWalkSpeed(0.2F);
                            onRightClickWeaponSkills.this.underFireUsers.remove(p.getUniqueId().toString());
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Under Fire " + ChatColor.RED + "[Deactivated]")).color(ChatColor.GREEN).create());
                        }
                    },this.underFireDuration * 20L);
                }
                if (level >= this.knockbackLevelNeeded && this.knockbackEnabled && weaponSkill.equalsIgnoreCase("Knockback") && (event.getAction() == Action.RIGHT_CLICK_AIR || event
                        .getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    int damage = this.knockbackDurabilityLoss;
                    im.setDamage(currentDamage + damage);
                    modifiedItem.setItemMeta((ItemMeta)im);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("Knockback [Activated]")).color(ChatColor.GREEN).create());
                    p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.0F, 1.0F);
                    List entities = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
                    for (Object e : entities) {
                        Entity en = (Entity)e;
                        if (!(en instanceof Player))
                            knockback(p, en);
                    }
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder("NOT ENOUGH DURABILITY")).color(ChatColor.RED).create());
            }
        }
    }
}
