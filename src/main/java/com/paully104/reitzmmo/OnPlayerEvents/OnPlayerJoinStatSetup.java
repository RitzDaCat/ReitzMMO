package com.paully104.reitzmmo.OnPlayerEvents;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Paul on 3/22/2016.
 */
public class OnPlayerJoinStatSetup implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPlayerJoinStatSetup(PlayerJoinEvent e) {


        Player p = e.getPlayer();

        PlayerData pd = new PlayerData(p.getName());
        pd.getData().set("Name", p.getName());

        Integer Level = pd.getData().getInt("Level");
        Integer Attack = pd.getData().getInt("Attack");
        Double Health = pd.getData().getDouble("Health");
        Integer CombatEXP = pd.getData().getInt("Combat-EXP");



        pd.getData().set("Name", p.getName());
        if (Level == 0) {
            pd.getData().set("Level", 1);

        }
        if (Attack == 0) {
            pd.getData().set("Attack", 1);

        }
        if (Health == 0.0) {
            pd.getData().set("Health", 20);
            p.setMaxHealth(20);

        } else {

            p.setMaxHealth(Health);
        }
        if (CombatEXP == 0) {
            pd.getData().set("Combat-EXP", 0);

        }
        pd.save();
        API.Players.put(e.getPlayer().getName(), pd); //this loads the player data into the API

        //Lets give the book
        ReitzMMO_Book.setLoginBook(p);

    }





}
