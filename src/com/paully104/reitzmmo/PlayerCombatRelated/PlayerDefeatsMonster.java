package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Hologram.Hologram;
import com.paully104.reitzmmo.Party_System.Party;
import com.paully104.reitzmmo.Party_System.Party_API;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagInt;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerDefeatsMonster implements Listener {

    private final int PartyEXPMaxDistance = API.partyConfig.getInt("PartyEXPMaxDistance");
    private final boolean debug = API.debugConfig.getBoolean("PartyEXP");

    @EventHandler
    public void MonsterDeathCausedByPlayer(EntityDeathEvent e)
    {


        if(e.getEntity().getKiller() instanceof  Player)
        {
            //Get Entities
            Entity dead = e.getEntity();
            Entity player = e.getEntity().getKiller();
            String playerName = player.getName();
            int monster_level = 0;
            if(debug){System.out.println("PARTYEXPDISTANCE: " + PartyEXPMaxDistance);}

            //lets handle mob custom drops here
            //dont iteriate while the thread is modifying
            try {
                for (ItemStack eachItem : e.getDrops()) {

                    //apply to each item


                    net.minecraft.server.v1_14_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(eachItem);
                    NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
                    NBTTagList modifiers = new NBTTagList();
                    NBTTagCompound damage = new NBTTagCompound();
                    damage.set("AttributeName", new NBTTagString("generic.attackDamage"));
                    damage.set("Name", new NBTTagString("generic.attackDamage"));

                    //need the mobs level
                    String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                    monster_level = Integer.parseInt(monster_level_from_name);

                    //This is where we can effect the % chance of the item dropped
                    Random random = new Random();
                    //random.nextInt(max - min + 1) + min, Generally speaking, if you need to generate numbers from min to max (including both), you write
                    int randomChance = random.nextInt(10-1+1)+1; //chance between 1 and 10
                    if (randomChance >= 9)
                    {

                        monster_level=monster_level+1;
                    }

                    damage.set("Amount", new NBTTagInt(monster_level));
                    damage.set("Operation", new NBTTagInt(0));
                    damage.set("UUIDLeast", new NBTTagInt(894654));
                    damage.set("UUIDMost", new NBTTagInt(2872));
                    damage.set("Slot", new NBTTagString("mainhand"));

                    modifiers.add(damage);
                    compound.set("AttributeModifiers", modifiers);
                    nmsStack.setTag(compound);
                    ItemStack item = CraftItemStack.asBukkitCopy(nmsStack);
                    System.out.println("Item drop");
                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(),item);




                }
                e.getDrops().clear();



            }
            catch (ConcurrentModificationException ce)
            {
                System.out.println("CME");


            }


            if(Party_API.Party_Leaders.containsKey(playerName))
            {
                //Party leader kills the mob

                Party party = Party_API.Party_Leaders.get(playerName);
                @SuppressWarnings("unchecked") List<String> members = party.get_MembersList();
                System.out.println(party.get_MembersList());

                for(String people : members)
                {
                    Player partyMember = Bukkit.getPlayer(people);
                    if(partyMember == null)
                    {
                        System.out.println("Player error");

                    }//player is the killer make sure party member is within 100 blocks
                    if(debug){ System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));}
                    if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance)
                    {
                        System.out.println(people);
                        Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                        System.out.println(currentexp);
                        String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * 2);
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
                        //Player is too far for exp!
                    }

                }
                Hologram hologram = new Hologram();
                Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                hologram.setHologram((Player) player, player.getWorld(), monster, monster_level * 2);

            }
            else if(Party_API.inParty.containsKey(playerName))
            {
                //party member kills mob
                String leader = Party_API.inParty.get(playerName);

                Party party = Party_API.Party_Leaders.get(leader);
                List<String> members = party.get_MembersList();
                System.out.println(party.get_MembersList());

                for(String people : members)
                {

                    Player partyMember = Bukkit.getPlayer(people);
                    if(partyMember == null)
                    {
                        System.out.println("Player error");

                    }
                    if(debug){System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));}
                    //player is the killer make sure party member is within 100 blocks
                    if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance) {
                        System.out.println(people);
                        Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                        System.out.println(currentexp);
                        String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * 2);
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
                Location monster = dead.getLocation().add(0.0,0.0,0.0);
                hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);

            }
            else
            {
                String monster_level_from_name = "1";
                Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
                try {
                    monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                }
                catch (NullPointerException ignored)
                {

                }
                monster_level = Integer.parseInt(monster_level_from_name);
                int new_exp = currentexp + (monster_level*2);
                API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
                CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                test.CheckLevelUp((Player)player);
                Hologram hologram = new Hologram();
                Location monster = dead.getLocation().add(0.0,0.0,0.0);
                hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);
                if(debug){System.out.println("Player location: " + player.getLocation());}
                if(debug){ System.out.println("Headheight location: " + ((Player) player).getEyeLocation());}

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

    }

}
