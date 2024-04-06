package net.oilcake.mitelros.client.render;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderWitherBodyguard extends RenderBiped {
  public RenderWitherBodyguard() {
    super((ModelBiped)new ModelSkeleton(), 0.5F);
  }
  
  protected void setTextures() {
    setTexture(0, "textures/entity/skeleton/wither_skeleton");
  }
  
  protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
    return this.textures[0];
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return func_110856_a((EntityLiving)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
    a((EntitySkeleton)par1EntityLivingBase, par2);
  }
  
  protected void a(EntitySkeleton par1EntitySkeleton, float par2) {
    if (par1EntitySkeleton.getSkeletonType() == 1)
      GL11.glScalef(1.2F, 1.2F, 1.2F); 
  }
}
