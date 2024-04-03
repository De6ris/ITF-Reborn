package net.oilcake.mitelros.api;

public interface ITFFoodStats {
    int getWater();

    int addWater(int water);
    void decreaseWater(float water);
    void decreaseWaterServerSide(float hungerWater);
    int getWaterLimit();
    void setSatiationWater(int water, boolean check_limit);
}
