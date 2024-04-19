package net.oilcake.mitelros.api;

public interface ITFPacket8 {

    int setWater(int water);

    void setPhytonutrients(int phytonutrients);

    void setProtein(int protein);
    void  setTemperature(float temperature);

    int getWater();

    int getPhytonutrients();

    int getProtein();
    float getTemperature();

}
