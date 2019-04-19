package com.paully104.reitzmmo.Enum;

/**
 * Created by Paul on 7/28/2016.
 */
public class Weapon_Damage {

    public enum Weapon_Damages
    {
        WOOD_SWORD(4),GOLD_SWORD(4),STONE_SWORD(5),IRON_SWORD(6),DIAMOND_SWORD(7),
        WOOD_AXE(5),GOLD_AXE(7),STONE_AXE(7),IRON_AXE(8),DIAMOND_AXE(9),
        WOOD_SPADE(2),GOLD_SPADE(2),STONE_SPADE(3),IRON_SPADE(4),DIAMOND_SPADE(5),
        WOOD_HOE(1),GOLD_HOE(1),STONE_HOE(2),IRON_HOE(3),DIAMOND_HOE(4),BOW(0);
        private final int value;

        Weapon_Damages(int value)
        {
            this.value = value;
        }

        public int getValue()

        {
            return value;
        }





    }
}
