package io.github.paulanthonyreitz.reitzmmo.PlayerCombatRelated;

import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import io.github.paulanthonyreitz.reitzmmo.Hologram.Hologram;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party;
import io.github.paulanthonyreitz.reitzmmo.Party_System.Party_API;
import io.github.paulanthonyreitz.reitzmmo.PlayerData.PlayerData;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PlayerDefeatsMonster implements Listener {
    public static final String PLAYERCOMBATEXP = "Combat-EXP";

    public static final String WORLDBASECOMBATEXP = "Scaling.World.WorldBaseCombatEXP.Base";

    public static final String LEVEL = "Level";

    public static final String WORLDBASECOMBATEXP_MULTIPLIER = "Scaling.World.WorldBaseCombatEXP.Multiplier";

    private final int PartyEXPMaxDistance = API.partyConfig.getInt("PartyEXPMaxDistance");

    private final boolean debug = API.debugConfig.getBoolean("PartyEXP");

    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");

    private final int combatEXPMultipler = API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");

    private final boolean expHologramEnabled = API.chatConfig.getBoolean("expHologramsEnabled");

    private final boolean expChatEnabled = API.chatConfig.getBoolean("expChatEnabled");

    private final boolean mobsDropAttackUpItems = API.lootConfig.getBoolean("General.MobsDropAttackUpItems.Enabled");

    private final int mobsDropAttackUpItemsChance = API.lootConfig.getInt("General.MobsDropAttackUpItems.PercentChance");

    private final boolean mobsDropBonusChest = API.lootConfig.getBoolean("General.BonusChest.Enabled");

    private final int mobsDropBonusChestPercentChance = API.lootConfig.getInt("General.BonusChest.PercentChance");

    @EventHandler
    public void MonsterDeathCausedByPlayer(EntityDeathEvent e) {
        int worldEnabled = API.worldConfig.getInt(((World)Objects.<World>requireNonNull(e.getEntity().getLocation().getWorld())).getName());
        if (worldEnabled != -1 && e.getEntity().getKiller() != null && !(e.getEntity() instanceof Player)) {
            LivingEntity livingEntity = e.getEntity();
            Player player = e.getEntity().getKiller();
            if (player.getGameMode() != GameMode.CREATIVE) {
                if (PlayerAttackingMonster.playerHasMusic.contains(player.getUniqueId().toString())) {
                    player.stopSound(Sound.MUSIC_DISC_11);
                    PlayerAttackingMonster.playerHasMusic.remove(player.getUniqueId().toString());
                }
                String playerName = ((Player)Objects.<Player>requireNonNull(player)).getName();
                String monster_level_from_name = "1";
                try {
                    monster_level_from_name = ((String)Objects.<String>requireNonNull(livingEntity.getCustomName())).replaceAll("\\D+", "");
                } catch (NullPointerException ex) {
                    monster_level_from_name = "1";
                }
                int monster_level;
                try {
                    monster_level = Integer.parseInt(monster_level_from_name);
                }
                catch (NumberFormatException exc)
                {
                    monster_level = 1;
                }
                String lootConfigItem = API.lootConfig.getString(monster_level_from_name + "." + e.getEntity().getType() + ".item");
                int lootConfigChance = API.lootConfig.getInt(monster_level_from_name + "." + e.getEntity().getType() + ".chance");
                Random randomLoot = new Random();
                int randomLootChance = randomLoot.nextInt(100) + 1;
                if (randomLootChance <= lootConfigChance) {
                    assert lootConfigItem != null;
                    e.getDrops().add(new ItemStack(Objects.requireNonNull(Material.getMaterial((lootConfigItem)))));
                }
                if (this.debug)
                    System.out.println("PARTYEXPDISTANCE: " + this.PartyEXPMaxDistance);
                if (Party_API.Party_Leaders.containsKey(playerName)) {
                    Party party = Party_API.Party_Leaders.get(playerName);
                    List<String> members = party.get_MembersList();
                    if (this.debug)
                        System.out.println(party.get_MembersList());
                    for (String people : members) {
                        Player partyMember = Bukkit.getPlayer(people);
                        if (partyMember == null && this.debug)
                            System.out.println("Player error");
                        if (this.debug)
                            System.out.println("Distance #1 " + livingEntity.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()));
                        if (livingEntity.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()) <= this.PartyEXPMaxDistance) {
                            Integer currentexp = API.Players.get(partyMember.getUniqueId().toString()).getData().getInt("Combat-EXP");
                            int new_exp = currentexp.intValue() + monster_level * this.combatEXPMultipler;
                            API.Players.get(partyMember.getUniqueId().toString()).getData().set("Combat-EXP", Integer.valueOf(new_exp));
                            CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                            test.CheckLevelUp(partyMember);
                            continue;
                        }
                        if (this.debug) {
                            System.out.println("Player is to far #2");
                            System.out.println("Player:" + partyMember.getName());
                            System.out.println("Distance" + livingEntity.getLocation().distance(partyMember.getLocation()));
                            System.out.println("Location:" + partyMember.getLocation());
                        }
                    }
                    Hologram hologram = new Hologram();
                    Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
                    int expGained = monster_level * this.combatEXPMultipler;
                    if (this.expHologramEnabled)
                        hologram.setHologram(player.getWorld(), monster, expGained);
                    if (this.expChatEnabled) {
                        Player p = player;
                        String uuid = p.getUniqueId().toString();
                        TextComponent component = new TextComponent();
                        int level = API.Players.get(uuid).getData().getInt("Level");
                        int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
                        int combatexpCurrent = API.Players.get(uuid).getData().getInt("Combat-EXP");
                        int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                        component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                        String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
                        player.spigot().sendMessage(component);
                    }
                } else if (Party_API.inParty.containsKey(playerName)) {
                    String leader = Party_API.inParty.get(playerName);
                    Party party = Party_API.Party_Leaders.get(leader);
                    List<String> members = party.get_MembersList();
                    for (String people : members) {
                        Player partyMember = Bukkit.getPlayer(people);
                        if (partyMember == null && this.debug)
                            System.out.println("Player error");
                        if (this.debug)
                            System.out.println("Distance #1 " + livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()));
                        if (livingEntity.getLocation().distance(((Player)Objects.<Player>requireNonNull(partyMember)).getLocation()) <= this.PartyEXPMaxDistance) {
                            String uuid = partyMember.getUniqueId().toString();
                            Integer currentexp = Integer.valueOf(((PlayerData)API.Players.get(uuid)).getData().getInt("Combat-EXP"));
                            int new_exp = currentexp.intValue() + monster_level * this.combatEXPMultipler;
                            API.Players.get(uuid).getData().set("Combat-EXP", Integer.valueOf(new_exp));
                            CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                            test.CheckLevelUp(partyMember);
                            continue;
                        }
                        if (this.debugEnabled) {
                            System.out.println("Player is to far #2");
                            System.out.println("Player:" + partyMember.getName());
                            System.out.println("Distance" + livingEntity.getLocation().distance(partyMember.getLocation()));
                            System.out.println("Location:" + partyMember.getLocation());
                        }
                    }
                    Hologram hologram = new Hologram();
                    Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
                    int expGained = monster_level * this.combatEXPMultipler;
                    if (this.expHologramEnabled)
                        hologram.setHologram(player.getWorld(), monster, expGained);
                    if (this.expChatEnabled) {
                        Player p = player;
                        TextComponent component = new TextComponent();
                        String uuid = p.getUniqueId().toString();
                        int level = API.Players.get(uuid).getData().getInt("Level");
                        int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
                        int combatexpCurrent = API.Players.get(uuid).getData().getInt("Combat-EXP");
                        int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                        component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                        String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
                        player.spigot().sendMessage(component);
                    }
                } else {
                    monster_level_from_name = "1";
                    String uuid = player.getUniqueId().toString();
                    int currentexp = API.Players.get(uuid).getData().getInt("Combat-EXP");
                    try {
                        monster_level_from_name = livingEntity.getCustomName().replaceAll("\\D+", "");
                    } catch (NullPointerException nullPointerException) {}
                    monster_level = Integer.parseInt(monster_level_from_name);
                    int new_exp = currentexp + monster_level * this.combatEXPMultipler;
                    API.Players.get(uuid).getData().set("Combat-EXP", Integer.valueOf(new_exp));
                    CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                    test.CheckLevelUp(player);
                    Hologram hologram = new Hologram();
                    Location monster = livingEntity.getLocation().add(0.0D, 0.0D, 0.0D);
                    int expGained = this.combatEXPMultipler * monster_level;
                    if (this.expHologramEnabled)
                        hologram.setHologram(player.getWorld(), monster, expGained);
                    if (this.expChatEnabled) {
                        Player p = player;
                        TextComponent component = new TextComponent();
                        int level = API.Players.get(uuid).getData().getInt("Level");
                        int combatexpNeeded = level * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Base") * API.playerConfig.getInt("Scaling.World.WorldBaseCombatEXP.Multiplier");
                        int combatexpCurrent = API.Players.get(p.getUniqueId().toString()).getData().getInt("Combat-EXP");
                        int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                        component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                        String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(toNextLevel)).create()));
                        player.spigot().sendMessage(component);
                    }
                    if (this.debug)
                        System.out.println("Player location: " + player.getLocation());
                    if (this.debug)
                        System.out.println("Headheight location: " + player.getEyeLocation());
                }
            }
            createBossBar.removeBossBaronPlayer(player);
        }
    }
}
