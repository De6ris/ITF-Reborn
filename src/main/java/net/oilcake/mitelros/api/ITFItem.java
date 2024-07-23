package net.oilcake.mitelros.api;

import java.util.Optional;

public interface ITFItem {
    default int itf$GetFoodWater() {
        return 0;
    }

    default void itf$SetFoodWater(int water) {
    }

    default void itf$SetExtraInfo(String info) {
    }

    default Optional<String> itf$GetExtraInfo() {
        return Optional.empty();
    }
}
