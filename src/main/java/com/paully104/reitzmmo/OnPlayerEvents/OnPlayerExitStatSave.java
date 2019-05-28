package com.paully104.reitzmmo.OnPlayerEvents;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import com.paully104.reitzmmo.Party_System.Party_API;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by Paul on 3/22/2016.
 */
public class OnPlayerExitStatSave implements Listener {

    @EventHandler
    public void OnPlayerExit(PlayerQuitEvent e) {
        //Get player information
        Player p = e.getPlayer();
        String name = p.getName();
        String uuid = p.getUniqueId().toString();
        PlayerData pd = new PlayerData(uuid);
        System.out.println(p.getName() + " has exited the game!");

        //get stats from API
        Integer level = API.Players.get(uuid).getData().getInt("Level");
        Integer attack = API.Players.get(uuid).getData().getInt("Attack");
        Integer health = API.Players.get(uuid).getData().getInt("Health");
        Integer combatexp = API.Players.get(uuid).getData().getInt("Combat-EXP");

        //Save stats
        pd.getData().set("Level", level);
        pd.getData().set("Attack", attack);
        pd.getData().set("Health", health);
        pd.getData().set("Combat-EXP", combatexp);
        pd.getData().set("DisplayName",p.getDisplayName());
        pd.save();


        //They disconnect make sure their party status is removed!
        if (Party_API.Party_Leaders.containsKey(name))
        {
            p.performCommand("Rparty disband");

        }
        else if (Party_API.inParty.containsKey(name))
        {
            p.performCommand("Rparty leave");
        }
        //Remove Book
        ReitzMMO_Book.removeLoginBook(p);
    }

}
