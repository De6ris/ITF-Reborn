package net.oilcake.mitelros.render;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;

public class RenderLichShadow extends RenderBiped {
    public RenderLichShadow() {
        super(new ModelSkeleton(), 0.5F);
    }

    protected void setTextures() {
        setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/skeleton/lich_shadow");
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return this.textures[0];
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110856_a((EntityLiving) par1Entity);
    }
}
