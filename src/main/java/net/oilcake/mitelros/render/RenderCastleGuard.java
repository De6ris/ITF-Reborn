package net.oilcake.mitelros.render;

import net.minecraft.RenderEarthElemental;
import net.oilcake.mitelros.ITFStart;

public class RenderCastleGuard extends RenderEarthElemental {

    @Override
    protected void setTextures() {
        this.setTexture(0,
                ITFStart.ResourceDomainColon + "textures/entity/earth_elemental/stone_brick/earth_elemental_stone_brick",
                ITFStart.ResourceDomainColon + "textures/entity/earth_elemental/earth_elemental");
    }
}
