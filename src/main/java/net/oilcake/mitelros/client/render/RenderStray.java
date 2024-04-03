package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.ModelBiped;
import net.minecraft.ModelSkeleton;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;

public class RenderStray extends RenderBiped {
  public RenderStray() {
    super((ModelBiped)new ModelSkeleton(), 0.5F);
  }
  
  protected void setTextures() {
    setTexture(0, "textures/entity/skeleton/stray");
  }
  
  protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
    return this.textures[0];
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return func_110856_a((EntityLiving)par1Entity);
  }
}
