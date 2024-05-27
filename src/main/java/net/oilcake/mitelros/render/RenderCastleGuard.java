package net.oilcake.mitelros.render;

import net.minecraft.RenderEarthElemental;

public class RenderCastleGuard extends RenderEarthElemental {

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/earth_elemental/stone_brick/earth_elemental_stone_brick", "textures/entity/earth_elemental/earth_elemental");
    }
}
