package net.oilcake.mitelros.api;

public interface ITFClientPlayer {
    default int getPhytonutrients(){
        throw new IllegalStateException("Should be implemented in Mixin");
    };

    default void setPhytonutrients(int phytonutrients){
        throw new IllegalStateException("Should be implemented in Mixin");
    };

    default int getProtein(){
        throw new IllegalStateException("Should be implemented in Mixin");
    };

    default void setProtein(int protein){
        throw new IllegalStateException("Should be implemented in Mixin");
    };

}
