package net.oilcake.mitelros.api;

import java.util.Optional;

public interface ITFItem {
    default int itf$GetFoodWater() {
        return 0;
    }

    default void itf$SetFoodWater(int water) {
    }

    default int itf$GetFoodTemperature() {
        return 0;
    }

    default void itf$SetFoodTemperature(int temperature) {
    }

    default void itf$SetExtraInfo(String info) {
    }

    default Optional<String> itf$GetExtraInfo() {
        return Optional.empty();
    }
}
