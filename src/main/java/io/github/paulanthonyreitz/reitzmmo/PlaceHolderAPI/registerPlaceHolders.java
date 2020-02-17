package io.github.paulanthonyreitz.reitzmmo.PlaceHolderAPI;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class registerPlaceHolders {
    public static void registerPlaceHoldersReitzMMO() {
        PlaceholderAPI.registerPlaceholderHook("ReitzMMO", new PlaceholderHook() {
            public String onRequest(OfflinePlayer p, String params) {
                if (p != null && p.isOnline())
                    return onPlaceholderRequest(p.getPlayer(), params);
                return null;
            }

            public String onPlaceholderRequest(Player p, String params) {
                if (p == null)
                    return null;
                if (params.equalsIgnoreCase("Level"))
                    return API.getPlayerDataFromAPI(p, "Level") + "";
                if (params.equalsIgnoreCase("Attack"))
                    return API.getPlayerDataFromAPI(p, "Attack") + "";
                if (params.equalsIgnoreCase("Health"))
                    return API.getPlayerDataFromAPI(p, "Health") + "";
                if (params.equalsIgnoreCase("Combat-EXP"))
                    return API.getPlayerDataFromAPI(p, "Combat-EXP") + "";
                if (params.equalsIgnoreCase("DisplayName"))
                    return p.getDisplayName();
                return null;
            }
        });
    }
}
