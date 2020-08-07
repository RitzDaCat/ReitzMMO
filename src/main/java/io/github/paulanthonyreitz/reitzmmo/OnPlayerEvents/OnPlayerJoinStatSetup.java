package io.github.paulanthonyreitz.reitzmmo.OnPlayerEvents;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated.createBossBar;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class OnPlayerJoinStatSetup implements Listener {

    private static final String HEALTH = "Health";
    private static final String ATTACK = "Attack";
    private static final String LEVEL = "Level";
    //private static final String PLAYERCOMBATEXP = "Combat-EXP";
    Boolean ReitzMMOBook = API.playerConfig.getBoolean("ReitzMMOBook.Enabled");

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPlayerJoinStatSetup(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!e.getPlayer().hasMetadata("NPC")) {
            p.setWalkSpeed(0.2F);
            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            pd.getData().set("UUID", uuid);
            int Level = pd.getData().getInt("Level");
            int Attack = pd.getData().getInt("Attack");
            double Health = pd.getData().getDouble("Health");
            int CombatEXP = pd.getData().getInt("Combat-EXP");
            if (Level == 0)
                pd.getData().set("Level", 1);
            if (Attack == 0)
                pd.getData().set("Attack", 1);
            if (Health == 0.0D) {
                pd.getData().set("Health", 20);
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20.0D);
            } else {
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Health);
            }
            if (CombatEXP == 0)
                pd.getData().set("Combat-EXP", 0);
            pd.getData().set("DisplayName", p.getDisplayName());
            pd.save();
            API.Players.put(p.getUniqueId().toString(), pd);
            if(ReitzMMOBook) {
                ReitzMMO_Book.setLoginBook(p);
            }

            createBossBar.setBossBaronPlayer(p);
        }
    }
}
