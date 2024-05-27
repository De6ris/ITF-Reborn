package net.oilcake.mitelros.api;

import net.minecraft.ResourceLocation;

public interface ITFPotion {
    default boolean usesIndividualTexture() {
        return false;
    }

    ResourceLocation getTexture();
}
