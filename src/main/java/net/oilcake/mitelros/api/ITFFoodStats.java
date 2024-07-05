package net.oilcake.mitelros.api;

public interface ITFFoodStats {
    int itf$GetWater();

    int itf$AddWater(int water);

    void itf$DecreaseWater(float water);

    void itf$DecreaseWaterServerSide(float hungerWater);

    int itf$GetWaterLimit();

    void itf$SetSatiationWater(int water, boolean check_limit);
}
