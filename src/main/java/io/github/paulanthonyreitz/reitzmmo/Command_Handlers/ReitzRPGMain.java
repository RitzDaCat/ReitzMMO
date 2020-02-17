package io.github.paulanthonyreitz.reitzmmo.Command_Handlers;

import java.util.Objects;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Menu.Menu;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_API;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ReitzRPGMain implements CommandExecutor {
    private static final String REITZ = "Reitz";

    private static final String REITZMMO = "ReitzMMO";

    private static final String LEVEL = "Level";

    private static final String ATTACK = "Attack";

    private static final String HEALTH = "Health";

    private static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";

    private static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";

    private static final String PLAYERCOMBATEXP = "Combat-EXP";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player pl = Bukkit.getPlayer(sender.getName());
        int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(((Player)Objects.<Player>requireNonNull(pl)).getLocation().getWorld())).getName());
        if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && worldEnabled != -1) {
            if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 0) {
                ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).openInventory(Menu.GUI_MENU);
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("Stats")) {
                Player p = Bukkit.getPlayer(sender.getName());
                String uuid = ((Player)Objects.<Player>requireNonNull(p)).getUniqueId().toString();
                sender.sendMessage(ChatColor.GOLD + "|||Current Stats|||");
                sender.sendMessage(ChatColor.GOLD + "     Level: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
                sender.sendMessage(ChatColor.RED + "     Attack: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
                sender.sendMessage(ChatColor.YELLOW + "     Health: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
                sender.sendMessage(ChatColor.DARK_GREEN + "     Combat-EXP: " + ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
                int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
                int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
                sender.sendMessage(ChatColor.DARK_GREEN + "     CombatEXP Needed: " + combatexpNeeded);
                return true;
            }
            if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixHealth")) {
                Player p = Bukkit.getPlayer(sender.getName());
                String uuid = ((Player)Objects.<Player>requireNonNull(p)).getUniqueId().toString();
                int combatexp = ((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP");
                int level = ((PlayerData)API.Players.get(uuid)).getData().getInt("Level");
                int combatexpneeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
                if (combatexp >= combatexpneeded) {
                    level++;
                    combatexp -= combatexpneeded;
                    p.sendMessage("~Fixing stats due to plugin changes...");
                    p.sendMessage("You leveled up to: " + level);
                    ((PlayerData)API.Players.get(uuid)).getData().set("Level", Integer.valueOf(level));
                    ((PlayerData)API.Players.get(uuid)).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("AttackScale")));
                    ((PlayerData)API.Players.get(uuid)).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("HealthScale")));
                    p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
                    p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
                    ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(API.getPlayerDataFromAPI(p, "Health"));
                    ((PlayerData)API.Players.get(uuid)).getData().set("Combat-EXP", Integer.valueOf(combatexp));
                } else {
                    p.sendMessage("~Fixing stats due to plugin changes...");
                    ((PlayerData)API.Players.get(uuid)).getData().set("Attack", Integer.valueOf(level * API.playerConfig.getInt("AttackScale")));
                    ((PlayerData)API.Players.get(uuid)).getData().set("Health", Integer.valueOf(18 + level * API.playerConfig.getInt("HealthScale")));
                    p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
                    p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
                    ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(API.getPlayerDataFromAPI(p, "Health"));
                }
            } else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixEXP")) {
                World world = ((Player)Objects.<Player>requireNonNull(Bukkit.getPlayer(sender.getName()))).getWorld();
                for (Entity e : world.getEntities()) {
                    if (e instanceof org.bukkit.entity.ArmorStand)
                        e.remove();
                }
            } else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("Reload")) {
                Bukkit.broadcastMessage("[ReitzMMO] reloading... saving all users data");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    String name = p.getName();
                    String uuid = p.getUniqueId().toString();
                    PlayerData pd = new PlayerData(uuid);
                    System.out.println(p.getName() + " has exited the game!");
                    Integer level = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Level"));
                    Integer attack = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Attack"));
                    Integer health = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Health"));
                    Integer combatexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
                    pd.getData().set("Level", level);
                    pd.getData().set("Attack", attack);
                    pd.getData().set("Health", health);
                    pd.getData().set("Combat-EXP", combatexp);
                    pd.getData().set("DisplayName", p.getDisplayName());
                    pd.save();
                    if (Party_API.Party_Leaders.containsKey(name)) {
                        p.performCommand("Rparty disband");
                        continue;
                    }
                    if (Party_API.inParty.containsKey(name))
                        p.performCommand("Rparty leave");
                }
                Bukkit.broadcastMessage("[ReitzMMO] All online player's saved");
                Bukkit.broadcastMessage("[ReitzMMO] Loading players stats from configs");
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    String uuid = p.getUniqueId().toString();
                    PlayerData pd = new PlayerData(uuid);
                    pd.getData().set("UUID", uuid);
                    int Level = pd.getData().getInt("Level");
                    int Attack = pd.getData().getInt("Attack");
                    double Health = pd.getData().getDouble("Health");
                    int CombatEXP = pd.getData().getInt("Combat-EXP");
                    if (Level == 0)
                        pd.getData().set("Level", Integer.valueOf(1));
                    if (Attack == 0)
                        pd.getData().set("Attack", Integer.valueOf(1));
                    if (Health == 0.0D) {
                        pd.getData().set("Health", Integer.valueOf(20));
                        ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(20.0D);
                    } else {
                        ((AttributeInstance)Objects.<AttributeInstance>requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH))).setBaseValue(Health);
                    }
                    if (CombatEXP == 0)
                        pd.getData().set("Combat-EXP", Integer.valueOf(0));
                    pd.getData().set("DisplayName", p.getDisplayName());
                    pd.save();
                    API.Players.put(p.getUniqueId().toString(), pd);
                }
            }
        }
        return false;
    }
}
