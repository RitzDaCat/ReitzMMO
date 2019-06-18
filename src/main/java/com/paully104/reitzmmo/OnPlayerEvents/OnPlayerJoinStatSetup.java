package com.paully104.reitzmmo.OnPlayerEvents;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Party_System.createPartyScoreboard;
import com.paully104.reitzmmo.PlayerCombatRelated.createBossBar;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

/**
 * Created by Paul on 3/22/2016.
 */
public class OnPlayerJoinStatSetup implements Listener {

    private final static String HEALTH = "Health";
    private final static String ATTACK = "Attack";
    private final static String LEVEL = "Level";
    private final static String PLAYERCOMBATEXP = "Combat-EXP";


    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPlayerJoinStatSetup(PlayerJoinEvent e) {


        Player p = e.getPlayer();
        //make sure players go normal speed which is .2
        p.setWalkSpeed((float)(.2));

        /*
        PlayerData pd = new PlayerData(p.getName());
        pd.getData().set("Name", p.getName());
*/

        //now lets use UUID
        String uuid = p.getUniqueId().toString();
        PlayerData pd = new PlayerData(uuid);
        pd.getData().set("UUID", uuid);

        int Level = pd.getData().getInt(LEVEL);
        int Attack = pd.getData().getInt(ATTACK);
        double Health = pd.getData().getDouble(HEALTH);
        int CombatEXP = pd.getData().getInt(PLAYERCOMBATEXP);

        if (Level == 0) {
            pd.getData().set(LEVEL, 1);

        }
        if (Attack == 0) {
            pd.getData().set(ATTACK, 1);

        }
        if (Health == 0.0) {
            pd.getData().set(HEALTH, 20);
            Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);


        } else {

            Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Health);
        }
        if (CombatEXP == 0) {
            pd.getData().set(PLAYERCOMBATEXP, 0);

        }
        pd.getData().set("DisplayName",p.getDisplayName());
        pd.save();
        API.Players.put(p.getUniqueId().toString(), pd); //this loads the player data into the API
        //p.sendMessage(PlaceholderAPI.setPlaceholders(p,"%ReitzMMO_Attack%"));

        //Lets give the book
        //ReitzMMO_Book.setLoginBook(p);

        //lets try the boss bar creation
        createBossBar bar = new createBossBar();
        bar.setBossBaronPlayer(p);

        createPartyScoreboard board = new createPartyScoreboard();
        board.setPartyScoreboardonPlayer(p);

    }





}
