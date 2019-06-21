package com.paully104.reitzmmo.Enum;

public class Armor_Defense {

    public enum Armor_Defenses
    {

        LEATHER_HELMET(1),GOLDEN_HELMET(2),CHAINMAIL_HELMET(2),IRON_HELMET(2),TURTLE_HELMET(2),DIAMOND_HELMET(3),
        LEATHER_CHESTPLATE(3),GOLDEN_CHESTPLATE(5),CHAINMAIL_CHESTPLATE(5),IRON_CHESTPLATE(6),DIAMOND_CHESTPLATE(8),
        LEATHER_LEGGINGS(2),GOLDEN_LEGGINGS(3),CHAINMAIL_LEGGINGS(4),IRON_LEGGINGS(5),DIAMOND_LEGGINGS(6),
        LEATHER_BOOTS(1),GOLDEN_BOOTS(2),CHAINMAIL_BOOTS(1),IRON_BOOTS(2),DIAMOND_BOOTS(3);


        private final int value;

        Armor_Defenses(int value)
        {
            this.value = value;
        }

        public int getValue()

        {

            return value;
        }





    }
}
