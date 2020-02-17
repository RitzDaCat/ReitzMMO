package io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated;

import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;

import org.bukkit.entity.Player;

class CheckPlayerCombatLevelUp {
    private static final String PLAYERCOMBATEXP = "Combat-EXP";

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerLevelUp");

    private static final String ATTACKSCALE = "Scaling.Player.AttackScale";

    private static final String HEALTHSCALE = "Scaling.Player.HealthScale";

    private static final String DEFENSESCALE = "Scaling.Player.DefenseScale";

    private static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";

    private static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";

    private static final String ATTACK = "Attack";

    private static final String HEALTH = "Health";

    private static final String LEVEL = "Level";

    public void CheckLevelUp(Player p) {
        int combatexp = API.Players.get(p.getUniqueId().toString()).getData().getInt("Combat-EXP");
        int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
        int combatexpneeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
        if (combatexp >= combatexpneeded) {
            level++;
            combatexp -= combatexpneeded;
            API.Players.get(p.getUniqueId().toString()).getData().set("Combat-EXP", Integer.valueOf(combatexp));
            API.Players.get(p.getUniqueId().toString()).getData().set("Level", Integer.valueOf(level));
            p.sendMessage(ChatColor.GREEN + "[ReitzMMO]" + ChatColor.WHITE + " You have leveled up to: " + ChatColor.YELLOW + level);
            String levelMessage = ChatColor.YELLOW + Integer.toString(level);
            p.sendTitle(levelMessage, "Congratulations, you have leveled up!", 10, 70, 10);
            API.Players.get(p.getUniqueId().toString()).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("Scaling.Player.AttackScale")));
            API.Players.get(p.getUniqueId().toString()).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("Scaling.Player.HealthScale")));
            API.Players.get(p.getUniqueId().toString()).getData().set("Combat-EXP", Integer.valueOf(combatexp));
            Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((18 + level * API.playerConfig.getInt("Scaling.Player.HealthScale")));
        }
        if (this.debugEnabled)
            System.out.println(p.getName() + " combatexp: " + combatexp + " combatexpneeded: " + combatexpneeded);
    }
}
