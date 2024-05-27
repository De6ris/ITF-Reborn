package net.oilcake.mitelros.api;

import net.minecraft.IInventory;
import net.minecraft.ItemStack;

public interface ITFItem {
    default int getFoodWater() {
        return 0;
    }

    default void setFoodWater(int water) {
    }

    default int getFoodTemperature() {
        return 0;
    }

    default void setFoodTemperature(int temperature) {
    }

    default void setExtraInfo(String info) {
    }

    default String getExtraInfo() {
        return null;
    }
}
