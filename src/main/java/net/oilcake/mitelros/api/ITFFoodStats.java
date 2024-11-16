package net.oilcake.mitelros.api;

public interface ITFFoodStats {
    int itf$GetWater();

    int itf$AddWater(int water);

    void itf$DecreaseWater(float water);

    void itf$DecreaseWaterClientSide(float hungerWater);

    void itf$DecreaseWaterServerSide(float hungerWater);

    int itf$GetWaterLimit();

    void itf$SetWater(int water, boolean check_limit);
}
