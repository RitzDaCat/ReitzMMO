package io.github.paulanthonyreitz.reitzmmo.Enum;

/**
 * Created by Paul on 4/30/2017.
 */
public class Weaponskill_Item_Check {

    public enum Weaponskill_Check
    {
        WOOD_SWORD(), GOLD_SWORD(), STONE_SWORD(), IRON_SWORD(), DIAMOND_SWORD(),
        WOOD_AXE(), GOLD_AXE(), STONE_AXE(), IRON_AXE(), DIAMOND_AXE(),
        WOOD_SPADE(), GOLD_SPADE(), STONE_SPADE(), IRON_SPADE(), DIAMOND_SPADE(),
        WOOD_HOE(), GOLD_HOE(), STONE_HOE(), IRON_HOE(), DIAMOND_HOE();
        private final boolean value;

        Weaponskill_Check() {
            this.value = true;
        }

        public boolean getValue() {
            return value;

        }
    }
}
