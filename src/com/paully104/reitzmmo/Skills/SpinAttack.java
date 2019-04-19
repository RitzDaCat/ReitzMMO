package com.paully104.reitzmmo.Skills;

import com.paully104.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Paul on 4/30/2017.
 */
class SpinAttack {

    private static final int spinAttackLevel = API.weaponskillConfig.getInt("Spin_Attack_Level");
    private static final int spinAttackDamageScale = API.weaponskillConfig.getInt("Spin_Attack_Damage_Scale");

    public static void performSpinAttack(Player p, PlayerInteractEntityEvent e, int level)
    {
                        if(level >= spinAttackLevel) {
                            final int RANGE = 3;
                            if (Weapon_Skills.getDistanceToEntity(e.getRightClicked(), p.getLocation()) <= RANGE) {
                                // do stuff
                                double maxDist = 3.00; // whatever
                                for (Entity other : e.getPlayer().getNearbyEntities(3.0, 3.0, 3.0)) {

                                    if (other.getLocation().distance(e.getPlayer().getLocation()) <= maxDist) {
                                        p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.flat(1, Note.Tone.E));
                                        if (other instanceof Player) {
                                            return;
                                        }
                                        if (other instanceof Tameable) {
                                            if (((Tameable) other).isTamed()) {
                                                return;
                                            }

                                        }
                                        //dont aoe back your friends kids
                                        else {
                                            other.setVelocity(other.getLocation().getDirection().multiply(-3));
                                            if (other instanceof Damageable) {
                                                //lv 1 spin attack does 2 damage + (level + scale so 1* 2) so 4.

                                                ((Damageable) other).damage(Weapon_Skills.calculateWeaponSkillDamage(e.getPlayer(), other), e.getPlayer());
                                            }
                                            //Bukkit.getPluginManager().callEvent(new EntityDamageEvent(other, EntityDamageEvent.DamageCause.CUSTOM,calculateWeaponSkillDamage(e.getPlayer(),other)));

                                        }

                                    }
                                }

                            }
                            else
                            {
                                p.sendMessage(ChatColor.RED + "[ERROR]" + ChatColor.WHITE + " Target is out of range");

                            }
                        }

    }
}
