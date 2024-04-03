package net.oilcake.mitelros.api;

import net.minecraft.Item;

public interface ITFItem {
    int getWater();

    Item setWater(int water);

    void setResourceLocation(String location);

    String getResourceLocationPrefix();
}
