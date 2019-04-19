package com.paully104.reitzmmo.Skills;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Enum.Weapon_Damage;
import com.paully104.reitzmmo.PlayerData.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Paul on 4/28/2017.
 */
public class Weapon_Skills implements Listener {

    //Get values from config
    private static final int spinAttackDamageScale = API.weaponskillConfig.getInt("Spin_Attack_Damage_Scale");


/*WEAPON SKILL LIST


        configuration.addDefault("Spin_Attack_Level", 2);
        configuration.addDefault("Spin_Attack_Base_Damage", 3);
        configuration.addDefault("Spin_Attack_Damage_Scale",2);

    1. Spin attack - spin weapon around knockback enemys
    2. Charge attack - movement speed up , stun maybe?
    3. Weapon throw
    4. Uppercut - to the moon

    */

    /* put spell on weapons so they can swap around

     */

    @EventHandler
    public void detectRightClick(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof Entity) {
            if (e.getPlayer().getInventory().getItemInMainHand() != null) {
                if (e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
                    if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                        //SPIN ATTACK SECTION
                        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("Spin Attack")) {
                            System.out.println("e.getrightclick instance Entity");
                            //We are right clicking on a monster to use an ability sweet
                            //Should probably find out what our level is now
                            PlayerData pd = API.Players.get(p.getName());
                            int level = pd.getData().getInt("Level");
                            //detect what weaponskill they are trying to use
                            SpinAttack.performSpinAttack(p, e, level);
                        }
                        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("Heavy Swing")) {
                            System.out.println("e.getrightclick instance Entity");
                            //We are right clicking on a monster to use an ability sweet
                            //Should probably find out what our level is now
                            PlayerData pd = API.Players.get(p.getName());
                            int level = pd.getData().getInt("Level");
                            //detect what weaponskill they are trying to use
                            Heavy_Swing.performHeavySwing(e);
                        }
                    }


                }
            }


        }


    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Entity arrow = event.getProjectile();

            if(event.getForce() == 1.0)
            {
                if(((Player)event.getEntity()).getInventory().getItemInMainHand().hasItemMeta())
                {
                    if(((Player)event.getEntity()).getInventory().getItemInMainHand().getItemMeta().hasLore())
                    {
                        if(((Player)event.getEntity()).getInventory().getItemInMainHand().getItemMeta().getLore().contains("Barrage"))
                        {

                            Barrage.performBarrage(event,arrow);


                        }
                        if(((Player)event.getEntity()).getInventory().getItemInMainHand().getItemMeta().getLore().contains("Bomb Arrow"))
                        {

                            Player p = (Player)event.getEntity();
                            PlayerData pd = API.Players.get(p.getName());
                            int level = pd.getData().getInt("Level");
                            Bomb_Arrow.performFireArrow(event,arrow);


                        }
                        if(((Player)event.getEntity()).getInventory().getItemInMainHand().getItemMeta().getLore().contains("Chicken launcher"))
                        {

                            Chicken_Launcher.performChickenLauncher(event,arrow);


                        }
                        if(((Player)event.getEntity()).getInventory().getItemInMainHand().getItemMeta().getLore().contains("Llama Bow"))
                        {

                            Llama_Launcher.performLlamaLauncher(event,arrow);


                        }


                    }



                }


            }


        }


    }

    @EventHandler
    public void onSneakEvent(PlayerToggleSneakEvent event)
    {

                if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
                {
                    if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore())
                    {
                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("Shift Back"))
                        {

                            Shift_Back.performShiftBack(event);
                        }

                    }
                }


    }





    public static int calculateWeaponSkillDamage(Entity player, Entity monster) {
        //Make this so you put in the weapon skill information and it calculates it all
        int player_attack = 0;
        int monster_defense = 0;
        int damage_done = 0;
        String monster_level_from_name;

        PlayerData pd = API.Players.get(player.getName());
        player_attack = pd.getData().getInt("Attack");
        monster_level_from_name = monster.getCustomName().replaceAll("\\D+", "");
        monster_defense = Integer.parseInt(monster_level_from_name);
        //A player has a base damage so at level 1 they do 2 damage at level 2 they do 4 damage, when they
        //level up their attack increases
        //so attack will go 2 4 6 8 10 12 14 16 so if we add the base damage of the weaponskill like +2 then
        // a level one with do 2*2 then a level two will do 4 * 2 SO BASE DAMAGE IS RETARDED
        damage_done = (player_attack * spinAttackDamageScale) - monster_defense;
        if (damage_done < 1) {
            damage_done = 1;
        }
        HumanEntity human = (HumanEntity) player;
        int weaponDamage = (Weapon_Damage.Weapon_Damages.valueOf(human.getInventory().getItemInMainHand().getType().toString().toUpperCase()).getValue());
        damage_done = damage_done + weaponDamage;
        return damage_done;
    }





   public static double getDistanceToEntity(Entity entity, Location pos) {
        double deltaX = entity.getLocation().getX() - pos.getX();
        double deltaY = entity.getLocation().getY() - pos.getY();
        double deltaZ = entity.getLocation().getZ() - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
    public static void setWeaponSkillOnWeapon(Material material, Inventory inv, int Slot, String name, String lore, Map enchantments)
    {
        //weapon skill will get a lore that states the weapon skill [1]SPIN_ATTACK or something
        System.out.println("Applying weapon skill");
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        item.addEnchantments(enchantments);
        inv.setItem(Slot,item);
        System.out.println("Ending on setWeaponSkillOnWeapon");

    }
    public static void removeWeaponSkillOnWeapon(Material material, Inventory inv, int Slot, String name, String lore)
    {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<>();
        Lore.remove(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

    }

}


