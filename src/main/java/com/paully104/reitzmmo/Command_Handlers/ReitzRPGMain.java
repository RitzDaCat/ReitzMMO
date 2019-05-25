package com.paully104.reitzmmo.Command_Handlers;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Created by Paul on 7/24/2016.
 */
public class ReitzRPGMain implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 0) {
            Bukkit.getPlayer(sender.getName()).openInventory(Menu.GUI_MENU);
            //The Idea is to make these obsolute with a nifty menu :)
            //sender.sendMessage(ChatColor.GOLD + "~ReitzRPGMMO main  menu listing commands~");
            //sender.sendMessage(ChatColor.GOLD + "1. /Reitz Stats");
            //sender.sendMessage(ChatColor.GOLD + "2. /Reitz Fix");
            //sender.sendMessage(ChatColor.GOLD + "3. /Rparty");
            return true;
        }


        else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("Stats")) {

            sender.sendMessage(ChatColor.GOLD + "|||Current Stats|||");
            sender.sendMessage(ChatColor.GOLD + "     Level: " + API.getPlayerDataFromAPI(Bukkit.getPlayer(sender.getName()), "Level"));
            sender.sendMessage(ChatColor.RED + "     Attack: " + API.getPlayerDataFromAPI(Bukkit.getPlayer(sender.getName()), "Attack"));
            sender.sendMessage(ChatColor.YELLOW + "     Health: " + API.getPlayerDataFromAPI(Bukkit.getPlayer(sender.getName()), "Health"));
            sender.sendMessage(ChatColor.DARK_GREEN + "     CombatEXP: " + API.getPlayerDataFromAPI(Bukkit.getPlayer(sender.getName()), "Combat-EXP"));
            int combatexpneeded = API.getPlayerDataFromAPI(Bukkit.getPlayer(sender.getName()), "Level") * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
            sender.sendMessage(ChatColor.DARK_GREEN + "     CombatEXP Needed: " + combatexpneeded);
            return true;
        }

        else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixHealth"))
        {
            Player p = Bukkit.getPlayer(sender.getName());

            int combatexp = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
            int level = API.Players.get(p.getName()).getData().getInt("Level");
            int combatexpneeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
            //level up occurs
            if(combatexp >= combatexpneeded) {
                //fixing level also
                level = level+1;
                combatexp = combatexp - combatexpneeded;
                p.sendMessage("~Fixing stats due to plugin changes...");
                p.sendMessage("You leveled up to: " + level);
                API.Players.get(p.getName()).getData().set("Level", level);
                API.Players.get(p.getName()).getData().set("Attack", (level * API.playerConfig.getInt("AttackScale")));
                API.Players.get(p.getName()).getData().set("Health", (18 + (level * API.playerConfig.getInt("HealthScale"))));
                p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
                p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
                p.setMaxHealth(API.getPlayerDataFromAPI(p, "Health"));
                API.Players.get(p.getName()).getData().set("CombatEXP", combatexp);
            }
            else
            {
                //just fixing stats
                p.sendMessage("~Fixing stats due to plugin changes...");
                API.Players.get(p.getName()).getData().set("Attack", (level * API.playerConfig.getInt("AttackScale")));
                API.Players.get(p.getName()).getData().set("Health", (18 + (level * API.playerConfig.getInt("HealthScale"))));
                p.sendMessage("Attack is now: " + API.getPlayerDataFromAPI(p, "Attack"));
                p.sendMessage("Health is now: " + API.getPlayerDataFromAPI(p, "Health"));
                p.setMaxHealth(API.getPlayerDataFromAPI(p, "Health"));

            }
        }
        else if ((cmd.getName().equalsIgnoreCase("Reitz") || cmd.getName().equalsIgnoreCase("RRM") || cmd.getName().equalsIgnoreCase("ReitzMMO")) && args.length == 1 && args[0].equalsIgnoreCase("FixEXP"))
        {
            World world = Bukkit.getPlayer(sender.getName()).getWorld();
            for(Entity e :world.getEntities())
            {
                if(e instanceof ArmorStand)
                {
                    e.remove();

                }

            }
        }


        return false;
    }

}
