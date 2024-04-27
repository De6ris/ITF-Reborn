package net.oilcake.mitelros.api;

import net.minecraft.Item;

public interface ITFItem {
    int getFoodWater();

    void setFoodWater(int water);

    int getFoodTemperature();
    void setFoodTemperature(int temperature);

    Item setExtraInfo(String info);

    String getExtraInfo();
}
