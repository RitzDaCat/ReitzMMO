package io.github.paulanthonyreitz.reitzmmo.Enum;

/**
 * Created by Paul on 7/28/2016.
 */
public class Weapon_Damage {

    public enum Weapon_Damages
    {
        WOODEN_SWORD(4),GOLDEN_SWORD(4),STONE_SWORD(5),IRON_SWORD(6),DIAMOND_SWORD(7),
        WOODEN_AXE(7),GOLDEN_AXE(7),STONE_AXE(9),IRON_AXE(9),DIAMOND_AXE(9),
        WOODEN_SPADE(2),GOLDEN_SPADE(2),STONE_SPADE(3),IRON_SPADE(4),DIAMOND_SPADE(5),
        WOODEN_HOE(1),GOLDEN_HOE(1),STONE_HOE(2),IRON_HOE(3),DIAMOND_HOE(4),BOW(0);
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
