package net.oilcake.mitelros.api;

import net.minecraft.Item;

public interface ITFItem {
    int getWater();

    Item setWater(int water);

    Item setExtraInfo(String info);

    String getExtraInfo();
}
