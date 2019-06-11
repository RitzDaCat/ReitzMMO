package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Hologram.SpawnChest;
import com.paully104.reitzmmo.Hologram.Hologram;
import com.paully104.reitzmmo.Party_System.Party;
import com.paully104.reitzmmo.Party_System.Party_API;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_14_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerDefeatsMonster implements Listener {

    private final int PartyEXPMaxDistance = API.partyConfig.getInt("PartyEXPMaxDistance");
    private final boolean debug = API.debugConfig.getBoolean("PartyEXP");
    private final boolean debugEnabled = API.debugConfig.getBoolean("PlayerAttackingMonster");
    private final int combatEXPMultipler = API.playerConfig.getInt("CombatEXP_MULTIPLIER");
    private final boolean expHologramEnabled = API.chatConfig.getBoolean("expHologramsEnabled");
    private final boolean expChatEnabled = API.chatConfig.getBoolean("expChatEnabled");

    private final boolean mobsDropAttackUpItems = API.lootConfig.getBoolean("General.MobsDropAttackUpItems.Enabled");
    private final int mobsDropAttackUpItemsChance = API.lootConfig.getInt("General.MobsDropAttackUpItems.PercentChance");

    private final boolean mobsDropBonusChest = API.lootConfig.getBoolean("General.BonusChest.Enabled");
    private final int mobsDropBonusChestPercentChance = API.lootConfig.getInt("General.BonusChest.PercentChance");

    @EventHandler
    public void MonsterDeathCausedByPlayer(EntityDeathEvent e) {
        int worldEnabled = API.worldConfig.getInt(Objects.requireNonNull(e.getEntity().getLocation().getWorld()).getName());
        if (worldEnabled != -1)
        {


            if (e.getEntity().getKiller() != null && !(e.getEntity() instanceof  Player))
            {
                //Get Entities
                Entity dead = e.getEntity();
                Player player = e.getEntity().getKiller();
                String playerName = Objects.requireNonNull(player).getName();
                String monster_level_from_name = Objects.requireNonNull(dead.getCustomName()).replaceAll("\\D+", "");
                int monster_level = Integer.parseInt(monster_level_from_name);
                String lootConfigItem = API.lootConfig.getString(monster_level_from_name+"."+e.getEntity().getType()+".item");
                int lootConfigChance = API.lootConfig.getInt(monster_level_from_name+"."+e.getEntity().getType()+".chance");

                Random randomLoot = new Random();
                //random.nextInt(max - min + 1) + min, Generally speaking, if you need to generate numbers from min to max (including both), you write
                int randomLootChance = randomLoot.nextInt(100 - 1 + 1) + 1; //chance between 1 and 10
                if (randomLootChance <= lootConfigChance) {


                    e.getDrops().add(new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(lootConfigItem)))));

                }

                if (debug) {
                    System.out.println("PARTYEXPDISTANCE: " + PartyEXPMaxDistance);
                }

                //lets handle mob custom drops here
                //dont iteriate while the thread is modifying
                if(e.getEntity() instanceof Monster && mobsDropAttackUpItems) {
                    try
                    {
                        for (ItemStack eachItem : e.getDrops())
                        {
                            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                            if (mobsDropAttackUpItemsChance >= randomNum) {
                                //chance to make the item have attack up
                                int itemDamage = 0;
                                int itemDefense = 0;
                                //check to see if it already has an attack modifier
                                if (!(Objects.requireNonNull(eachItem.getItemMeta()).hasAttributeModifiers())) {
                                    //does not have a modifier so we can add


                                    net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(eachItem);
                                    NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
                                    NBTTagList modifiers = new NBTTagList();
                                    NBTTagCompound damage = new NBTTagCompound();
                                    damage.set("AttributeName", new NBTTagString("generic.attackDamage"));
                                    damage.set("Name", new NBTTagString("generic.attackDamage"));
                                    damage.set("Amount", new NBTTagInt(monster_level + itemDamage));
                                    damage.set("Operation", new NBTTagInt(0));
                                    damage.set("UUIDLeast", new NBTTagInt(894654));
                                    damage.set("UUIDMost", new NBTTagInt(2872));
                                    damage.set("Slot", new NBTTagString("mainhand"));
                                    damage.set("Durability",new NBTTagInt(100));

                                    modifiers.add(damage);
                                    Objects.requireNonNull(compound).set("AttributeModifiers", modifiers);
                                    nmsStack.setTag(compound);
                                    ItemStack item = CraftItemStack.asBukkitCopy(nmsStack);
                                    if (debugEnabled) {
                                        System.out.println("Item drop");
                                    }
                                    Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItemNaturally(e.getEntity().getLocation(), item);


                                }
                                e.getDrops().clear();
                            }


                        }
                        //after the usual items we can do chest logic?
                        int randomNumBonusChest = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        if(mobsDropBonusChest && (mobsDropBonusChestPercentChance >= randomNumBonusChest)) {
                            SpawnChest chestspawn = new SpawnChest();
                            chestspawn.setChest(e.getEntity().getWorld(), e.getEntity().getLocation(), "Bonus Loot",monster_level);
                        }


                    }
                    catch (ConcurrentModificationException ce)
                    {
                        if (debugEnabled) {
                            System.out.println("CME");
                        }


                    }

}

                if (Party_API.Party_Leaders.containsKey(playerName))
                {
                    //Party leader kills the mob

                    Party party = Party_API.Party_Leaders.get(playerName);
                    @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();
                    if(debug) {
                        System.out.println(party.get_MembersList());
                    }
                    for (String people : members) {
                        Player partyMember = Bukkit.getPlayer(people);
                        if (partyMember == null) {
                            if(debug) {
                                System.out.println("Player error");
                            }

                        }//player is the killer make sure party member is within 100 blocks
                        if (debug) {
                            System.out.println("Distance #1 " + dead.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()));
                        }
                        if (dead.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()) <= PartyEXPMaxDistance) {
                            System.out.println(people);
                            Integer currentexp = API.Players.get(partyMember.getUniqueId().toString()).getData().getInt("Combat-EXP");
                            System.out.println(currentexp);
                            int new_exp = currentexp + (monster_level * combatEXPMultipler);
                            API.Players.get(partyMember.getUniqueId().toString()).getData().set("Combat-EXP", new_exp);
                            CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                            test.CheckLevelUp(partyMember);
                        } else {
                            if(debug) {
                                System.out.println("Player is to far #2");
                                System.out.println("Player:" + partyMember.getName());
                                System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                                System.out.println("Location:" + partyMember.getLocation());
                            }
                            //Player is too far for exp!
                        }

                    }
                    Hologram hologram = new Hologram();
                    Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                    //This should be combatEXP Multiple
                    int expGained = monster_level * combatEXPMultipler;
                    if(expHologramEnabled) {
                        hologram.setHologram(player.getWorld(), monster, expGained);
                    }
                    if(expChatEnabled)
                    {
                        Player p = player;
                        TextComponent component = new TextComponent();

                        int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
                        int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                        int combatexpCurrent = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
                        int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                        component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                        String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                        player.spigot().sendMessage(component);

                        //player.sendMessage(ChatColor.WHITE + "You gained: " + ChatColor.GREEN + expGained + " [EXP]");
                    }

                }
                else if (Party_API.inParty.containsKey(playerName))
                {
                    //party member kills mob
                    String leader = Party_API.inParty.get(playerName);

                    Party party = Party_API.Party_Leaders.get(leader);
                    List<String> members = party.get_MembersList();

                    for (String people : members) {

                        Player partyMember = Bukkit.getPlayer(people);
                        if (partyMember == null)
                        {
                            if(debug)
                            {
                                System.out.println("Player error");
                            }

                        }
                        if (debug)
                        {
                            System.out.println("Distance #1 " + dead.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()));
                        }
                        //player is the killer make sure party member is within 100 blocks
                        if (dead.getLocation().distance(Objects.requireNonNull(partyMember).getLocation()) <= PartyEXPMaxDistance)
                        {
                            System.out.println(people);
                            Integer currentexp = API.Players.get(partyMember.getUniqueId().toString()).getData().getInt("Combat-EXP");
                            System.out.println(currentexp);
                            int new_exp = currentexp + (monster_level * combatEXPMultipler);
                            API.Players.get(partyMember.getUniqueId().toString()).getData().set("Combat-EXP", new_exp);
                            CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                            test.CheckLevelUp(partyMember);
                        }
                        else
                            {
                            System.out.println("Player is to far #2");
                            System.out.println("Player:" + partyMember.getName());
                            System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                            System.out.println("Location:" + partyMember.getLocation());
                            //player is too far away
                        }
                    }
                    Hologram hologram = new Hologram();
                    Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                    int expGained = monster_level * combatEXPMultipler;
                    if(expHologramEnabled) {
                        hologram.setHologram(player.getWorld(), monster, expGained);
                    }
                    if(expChatEnabled)
                    {
                        Player p = player;
                        TextComponent component = new TextComponent();

                        int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
                        int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                        int combatexpCurrent = API.Players.get(p.getUniqueId().toString()).getData().getInt("Combat-EXP");
                        int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                        component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                        String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                        player.spigot().sendMessage(component);
                    }

                }
                else
                    {
                        monster_level_from_name = "1";
                        int currentexp = API.Players.get(player.getUniqueId().toString()).getData().getInt("Combat-EXP");
                        try
                        {
                            monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        }
                        catch (NullPointerException ignored)
                        {

                        }
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * combatEXPMultipler);
                        API.Players.get(player.getUniqueId().toString()).getData().set("Combat-EXP", new_exp);
                        CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                        test.CheckLevelUp(player);
                        Hologram hologram = new Hologram();
                        Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                        int expGained = combatEXPMultipler * monster_level;
                        if(expHologramEnabled) {
                            hologram.setHologram(player.getWorld(), monster, expGained);
                        }
                        if(expChatEnabled)
                        {
                            Player p = player;
                            TextComponent component = new TextComponent();

                            int level = API.Players.get(p.getUniqueId().toString()).getData().getInt("Level");
                            int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                            int combatexpCurrent = API.Players.get(p.getUniqueId().toString()).getData().getInt("Combat-EXP");
                            int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                            component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                            String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                            player.spigot().sendMessage(component);

                            //player.sendMessage(ChatColor.WHITE + "You gained: " + ChatColor.GREEN + expGained + " [EXP]");
                        }
                        if (debug)
                        {
                            System.out.println("Player location: " + player.getLocation());
                        }
                        if (debug)
                        {
                            System.out.println("Headheight location: " + player.getEyeLocation());
                        }

                    }
                //Get Experience
                //Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
                //String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                //int monster_level = Integer.parseInt(monster_level_from_name);
                //int new_exp = currentexp + (monster_level*2);
                //API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
                //CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                //test.CheckLevelUp((Player)player);
                //Hologram hologram = new Hologram();
                //Location monster = dead.getLocation().add(0.0,0.0,0.0);
                //hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);
                //System.out.println("Player location: " + player.getLocation());
                //System.out.println("Headheight location: " + ((Player) player).getEyeLocation());


            }
            /*
            else if((e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE) || e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
            {
                if(e.getEntity().getLastDamageCause().getEntity() instanceof  Player)
                {

                    //START
                    //Get Entities
                    Entity dead = e.getEntity();
                    Entity player = (Player)e.getEntity().getLastDamageCause().getEntity();
                    String playerName = player.getName();
                    String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                    int monster_level = Integer.parseInt(monster_level_from_name);
                    String lootConfigItem = API.lootConfig.getString(monster_level_from_name+"."+e.getEntity().getType()+".item");
                    int lootConfigChance = API.lootConfig.getInt(monster_level_from_name+"."+e.getEntity().getType()+".chance");

                    Random randomLoot = new Random();
                    //random.nextInt(max - min + 1) + min, Generally speaking, if you need to generate numbers from min to max (including both), you write
                    int randomLootChance = randomLoot.nextInt(100 - 1 + 1) + 1; //chance between 1 and 10
                    if (randomLootChance <= lootConfigChance) {


                        e.getDrops().add(new ItemStack(Material.getMaterial(lootConfigItem)));

                    }

                    if (debug) {
                        System.out.println("PARTYEXPDISTANCE: " + PartyEXPMaxDistance);
                    }

                    //lets handle mob custom drops here
                    //dont iteriate while the thread is modifying
                    if(e.getEntity() instanceof Monster && mobsDropAttackUpItems == true) {
                        try {
                            for (ItemStack eachItem : e.getDrops())
                            {

                                int itemDamage = 0;
                                int itemDefense = 0;
                                //check to see if it already has an attack modifier
                                if (!(eachItem.getItemMeta().hasAttributeModifiers()))
                                {
                                    //does not have a modifier so we can add




                                    net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(eachItem);
                                    NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
                                    NBTTagList modifiers = new NBTTagList();
                                    NBTTagCompound damage = new NBTTagCompound();
                                    damage.set("AttributeName", new NBTTagString("generic.attackDamage"));
                                    damage.set("Name", new NBTTagString("generic.attackDamage"));

                                    //need the mobs level

                                    //This is where we can effect the % chance of the item dropped
                                    Random random = new Random();
                                    //random.nextInt(max - min + 1) + min, Generally speaking, if you need to generate numbers from min to max (including both), you write
                                    int randomChance = random.nextInt(10 - 1 + 1) + 1; //chance between 1 and 10
                                    if (randomChance >= 9) {

                                        monster_level = monster_level + 1;
                                    }

                                    damage.set("Amount", new NBTTagInt(monster_level + itemDamage));
                                    damage.set("Operation", new NBTTagInt(0));
                                    damage.set("UUIDLeast", new NBTTagInt(894654));
                                    damage.set("UUIDMost", new NBTTagInt(2872));
                                    damage.set("Slot", new NBTTagString("mainhand"));

                                    modifiers.add(damage);
                                    compound.set("AttributeModifiers", modifiers);
                                    nmsStack.setTag(compound);
                                    ItemStack item = CraftItemStack.asBukkitCopy(nmsStack);
                                    if (debugEnabled == true) {
                                        System.out.println("Item drop");
                                    }
                                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);


                                }
                                e.getDrops().clear();


                            }
                        }
                        catch (ConcurrentModificationException ce)
                        {
                            if (debugEnabled) {
                                System.out.println("CME");
                            }


                        }

                    }

                    if (Party_API.Party_Leaders.containsKey(playerName))
                    {
                        //Party leader kills the mob

                        Party party = Party_API.Party_Leaders.get(playerName);
                        @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();
                        if(debug) {
                            System.out.println(party.get_MembersList());
                        }
                        for (String people : members) {
                            Player partyMember = Bukkit.getPlayer(people);
                            if (partyMember == null) {
                                if(debug) {
                                    System.out.println("Player error");
                                }

                            }//player is the killer make sure party member is within 100 blocks
                            if (debug) {
                                System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));
                            }
                            if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance) {
                                System.out.println(people);
                                Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                                System.out.println(currentexp);
                                int new_exp = currentexp + (monster_level * combatEXPMultipler);
                                API.Players.get(people).getData().set("Combat-EXP", new_exp);
                                CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                                test.CheckLevelUp(partyMember);
                            } else {
                                if(debug) {
                                    System.out.println("Player is to far #2");
                                    System.out.println("Player:" + partyMember.getName());
                                    System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                                    System.out.println("Location:" + partyMember.getLocation());
                                }
                                //Player is too far for exp!
                            }

                        }
                        Hologram hologram = new Hologram();
                        Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                        //This should be combatEXP Multiple
                        int expGained = monster_level * combatEXPMultipler;
                        if(expHologramEnabled) {
                            hologram.setHologram((Player) player, player.getWorld(), monster, expGained);
                        }
                        if(expChatEnabled)
                        {
                            Player p = (Player) player;
                            TextComponent component = new TextComponent();

                            int level = API.Players.get(p.getName()).getData().getInt("Level");
                            int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                            int combatexpCurrent = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
                            int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                            component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                            String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                            player.spigot().sendMessage(component);
                        }

                    }
                    else if (Party_API.inParty.containsKey(playerName))
                    {
                        //party member kills mob
                        String leader = Party_API.inParty.get(playerName);

                        Party party = Party_API.Party_Leaders.get(leader);
                        List<String> members = party.get_MembersList();
                        System.out.println(party.get_MembersList());

                        for (String people : members) {

                            Player partyMember = Bukkit.getPlayer(people);
                            if (partyMember == null)
                            {
                                if(debug)
                                {
                                    System.out.println("Player error");
                                }

                            }
                            if (debug)
                            {
                                System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));
                            }
                            //player is the killer make sure party member is within 100 blocks
                            if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance)
                            {
                                System.out.println(people);
                                Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                                System.out.println(currentexp);
                                int new_exp = currentexp + (monster_level * combatEXPMultipler);
                                API.Players.get(people).getData().set("Combat-EXP", new_exp);
                                CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                                test.CheckLevelUp(partyMember);
                            }
                            else
                            {
                                System.out.println("Player is to far #2");
                                System.out.println("Player:" + partyMember.getName());
                                System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                                System.out.println("Location:" + partyMember.getLocation());
                                //player is too far away
                            }
                        }
                        Hologram hologram = new Hologram();
                        Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                        int expGained = monster_level * combatEXPMultipler;
                        if(expHologramEnabled) {
                            hologram.setHologram((Player) player, player.getWorld(), monster, expGained);
                        }
                        if(expChatEnabled)
                        {
                            Player p = (Player) player;
                            TextComponent component = new TextComponent();

                            int level = API.Players.get(p.getName()).getData().getInt("Level");
                            int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                            int combatexpCurrent = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
                            int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                            component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                            String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.GREEN + " to level up!";
                            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                            player.spigot().sendMessage(component);


                            //player.sendMessage(ChatColor.WHITE + "You gained: " + ChatColor.GREEN + expGained + " [EXP]");
                        }

                    }
                    else
                    {
                        monster_level_from_name = "1";
                        Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
                        try
                        {
                            monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        }
                        catch (NullPointerException ignored)
                        {

                        }
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * combatEXPMultipler);
                        API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
                        CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                        test.CheckLevelUp((Player) player);
                        Hologram hologram = new Hologram();
                        Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                        int expGained = combatEXPMultipler * monster_level;
                        if(expHologramEnabled) {
                            hologram.setHologram((Player) player, player.getWorld(), monster, expGained);
                        }
                        if(expChatEnabled)
                        {
                            Player p = (Player) player;
                            TextComponent component = new TextComponent();

                            int level = API.Players.get(p.getName()).getData().getInt("Level");
                            int combatexpNeeded = level * (API.playerConfig.getInt("CombatEXP") * API.playerConfig.getInt("CombatEXP_MULTIPLIER"));
                            int combatexpCurrent = API.Players.get(p.getName()).getData().getInt("Combat-EXP");
                            int expNeededToLevel = combatexpNeeded - combatexpCurrent;
                            component.setText(ChatColor.WHITE + "+ " + ChatColor.GREEN + expGained + " [EXP]");
                            String toNextLevel = "You need: " + ChatColor.GREEN + expNeededToLevel + " [EXP] " + ChatColor.WHITE + " to level up!";
                            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(toNextLevel).create()));

                            player.spigot().sendMessage(component);

                            //player.sendMessage(ChatColor.WHITE + "You gained: " + ChatColor.GREEN + expGained + " [EXP]");
                        }
                        if (debug)
                        {
                            System.out.println("Player location: " + player.getLocation());
                        }
                        if (debug)
                        {
                            System.out.println("Headheight location: " + ((Player) player).getEyeLocation());
                        }

                    }


                }

            }

             */

        }
    }

}
