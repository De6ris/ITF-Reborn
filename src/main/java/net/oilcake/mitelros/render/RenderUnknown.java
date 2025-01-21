package net.oilcake.mitelros.render;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;

public class RenderUnknown extends RenderBiped {
    private ModelBiped r;

    public RenderUnknown() {
        super((ModelBiped) new ModelZombie(), 0.5F, 1.0F);
        this.r = this.modelBipedMain;
    }

    protected void setTextures() {
        setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/steve");
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return this.textures[0];
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110856_a((EntityLiving) par1Entity);
    }
}
