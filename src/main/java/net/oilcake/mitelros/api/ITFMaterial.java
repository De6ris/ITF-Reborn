package net.oilcake.mitelros.api;

public interface ITFMaterial {
    default int getMinHarvestLevel(){
        throw new IllegalStateException("Should be implemented in mixin");
    };
    default float getDurability(){
        throw new IllegalStateException("Should be implemented in mixin");
    };
    default String getName(){
        throw new IllegalStateException("Should be implemented in mixin");
    };
}
