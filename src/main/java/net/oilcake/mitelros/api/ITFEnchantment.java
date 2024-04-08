package net.oilcake.mitelros.api;

public interface ITFEnchantment {
    default boolean isCurse() {
        return false;
    }

    default boolean isTreasure() {
        return false;
    }
}
