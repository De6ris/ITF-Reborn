package net.oilcake.mitelros.render;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;

public class RenderStray extends RenderBiped {
    public RenderStray() {
        super((ModelBiped) new ModelSkeleton(), 0.5F);
    }

    protected void setTextures() {
        setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/skeleton/stray");
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return this.textures[0];
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110856_a((EntityLiving) par1Entity);
    }
}
