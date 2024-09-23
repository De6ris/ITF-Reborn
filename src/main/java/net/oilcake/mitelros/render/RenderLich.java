package net.oilcake.mitelros.render;

import net.minecraft.*;
import net.oilcake.mitelros.entity.boss.EntityLich;

public class RenderLich extends RenderBiped {
    public RenderLich() {
        super(new ModelSkeleton(), 0.5F);
    }

    protected boolean forceGlowOverride() {
        return true;
    }

    @Override
    protected void setTextures() {
        setTexture(0, "textures/entity/skeleton/lich");
    }

    @Override
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return this.textures[0];
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return func_110856_a((EntityLiving) par1Entity);
    }

    public void renderLichAsBoss(EntityLich par1EntityLich, double par2, double par4, double par6, float par8, float par9) {
        BossStatus.setBossStatus(par1EntityLich, false);
        super.doRenderLiving(par1EntityLich, par2, par4, par6, par8, par9);
        func_110827_b(par1EntityLich, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        renderLichAsBoss((EntityLich) par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderLichAsBoss((EntityLich) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        renderLichAsBoss((EntityLich) par1EntityLiving, par2, par4, par6, par8, par9);
    }
}
