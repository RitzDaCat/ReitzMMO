package com.paully104.reitzmmo.OnPlayerEvents;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.attribute.Attribute;
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

        /*
        PlayerData pd = new PlayerData(p.getName());
        pd.getData().set("Name", p.getName());
*/

        //now lets use UUID
        String uuid = p.getUniqueId().toString();
        PlayerData pd = new PlayerData(uuid);
        pd.getData().set("UUID", uuid);

        Integer Level = pd.getData().getInt("Level");
        Integer Attack = pd.getData().getInt("Attack");
        Double Health = pd.getData().getDouble("Health");
        Integer CombatEXP = pd.getData().getInt("Combat-EXP");

        if (Level == 0) {
            pd.getData().set("Level", 1);

        }
        if (Attack == 0) {
            pd.getData().set("Attack", 1);

        }
        if (Health == 0.0) {
            pd.getData().set("Health", 20);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);


        } else {

            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Health);
        }
        if (CombatEXP == 0) {
            pd.getData().set("Combat-EXP", 0);

        }
        pd.getData().set("DisplayName",p.getDisplayName());
        pd.save();
        API.Players.put(p.getUniqueId().toString(), pd); //this loads the player data into the API

        //Lets give the book
        //ReitzMMO_Book.setLoginBook(p);

    }





}
