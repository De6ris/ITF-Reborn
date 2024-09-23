package net.oilcake.mitelros.api;

public interface ITFFurnace {
    boolean itf$IsActive();

    void itf$setActive(boolean activated);

    boolean itf$IsBlastFurnace();

    boolean itf$IsSmoker();

    boolean itf$CanNormallyWork();

    boolean canBurnbyItself();
}
