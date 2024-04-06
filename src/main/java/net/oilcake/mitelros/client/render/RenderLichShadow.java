package net.oilcake.mitelros.client.render;

import net.minecraft.*;

public class RenderLichShadow extends RenderBiped {
  public RenderLichShadow() {
    super((ModelBiped)new ModelSkeleton(), 0.5F);
  }
  
  protected boolean forceGlowOverride() {
    return true;
  }
  
  protected void setTextures() {
    setTexture(0, "textures/entity/skeleton/lich_shadow");
  }
  
  protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
    return this.textures[0];
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return func_110856_a((EntityLiving)par1Entity);
  }
}
