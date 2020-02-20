package io.github.paulanthonyreitz.reitzmmo.OnPlayerEvents;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Custom_Recipes.ReitzMMO_Book;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_API;
import io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated.createBossBar;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerExitStatSave implements Listener {
    private static final String HEALTH = "Health";

    private static final String ATTACK = "Attack";

    private static final String LEVEL = "Level";

    private static final String PLAYERCOMBATEXP = "Combat-EXP";

    @EventHandler
    public void OnPlayerExit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!e.getPlayer().hasMetadata("NPC")) {
            String name = p.getName();
            String uuid = p.getUniqueId().toString();
            PlayerData pd = new PlayerData(uuid);
            System.out.println(p.getName() + " has exited the game!");
            Integer level = API.Players.get(uuid).getData().getInt("Level");
            Integer attack = API.Players.get(uuid).getData().getInt("Attack");
            Integer health = API.Players.get(uuid).getData().getInt("Health");
            Integer combatexp = API.Players.get(uuid).getData().getInt("Combat-EXP");
            pd.getData().set("Level", level);
            pd.getData().set("Attack", attack);
            pd.getData().set("Health", health);
            pd.getData().set("Combat-EXP", combatexp);
            pd.getData().set("DisplayName", p.getDisplayName());
            pd.save();
            if (Party_API.Party_Leaders.containsKey(name)) {
                p.performCommand("Rparty disband");
            } else if (Party_API.inParty.containsKey(name)) {
                p.performCommand("Rparty leave");
            }
            ReitzMMO_Book.removeLoginBook(p);
            createBossBar.deleteBossBaronPlayer(p);
        }
    }
}
