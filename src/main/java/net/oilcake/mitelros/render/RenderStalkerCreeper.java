package net.oilcake.mitelros.render;

import net.minecraft.RenderCreeper;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.entity.mob.EntityStalkerCreeper;

public class RenderStalkerCreeper extends RenderCreeper {
    public RenderStalkerCreeper() {
        this.shadowSize *= this.scale = EntityStalkerCreeper.getScale();
    }

    protected void setTextures() {
        this.setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/creeper/" + this.getSubtypeName());
        this.setTexture(1, "textures/entity/creeper/creeper_armor");
    }

    public String getSubtypeName() {
        return "stalker_creeper";
    }
}
