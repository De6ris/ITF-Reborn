package net.oilcake.mitelros.render;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.api.ITFEntityLivingBase;
import net.oilcake.mitelros.entity.boss.EntityLich;
import org.lwjgl.opengl.GL11;

public class RenderLich extends RenderBiped {
    public RenderLich() {
        super(new ModelSkeleton(), 0.5F);
    }

    @Override
    protected void setTextures() {
        setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/skeleton/lich");
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
//        if (par1EntityLich.isPotionActive(PotionExtend.glowing)) {
        if (((ITFEntityLivingBase) par1EntityLich).itf$IsGlowing()) {
            GL11.glPushAttrib(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            this.renderBox(par1EntityLich, par2, par4, par6);
            GL11.glPopAttrib();
        }
    }

    private void renderBox(Entity par1Entity, double par2, double par4, double par6) {
        GL11.glDepthMask(false);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glDisable(2884);
        GL11.glDisable(3042);
        GL11.glPushMatrix();
        Tessellator var10 = Tessellator.instance;
        GL11.glLineWidth(2.0F);
        double halfWidth = (double) (par1Entity.width / 2.0F);
        double height = (double) par1Entity.height;
        var10.startDrawing(2);
        var10.setColorRGBA(255, 255, 255, 255);
        var10.addVertex(par2 - halfWidth, par4 + height, par6 - halfWidth);
        var10.addVertex(par2 - halfWidth, par4, par6 - halfWidth);
        var10.addVertex(par2 + halfWidth, par4, par6 - halfWidth);
        var10.addVertex(par2 + halfWidth, par4 + height, par6 - halfWidth);
        var10.draw();
        var10.startDrawing(2);
        var10.addVertex(par2 + halfWidth, par4 + height, par6 + halfWidth);
        var10.addVertex(par2 + halfWidth, par4, par6 + halfWidth);
        var10.addVertex(par2 - halfWidth, par4, par6 + halfWidth);
        var10.addVertex(par2 - halfWidth, par4 + height, par6 + halfWidth);
        var10.draw();
        var10.startDrawing(2);
        var10.addVertex(par2 + halfWidth, par4 + height, par6 - halfWidth);
        var10.addVertex(par2 + halfWidth, par4, par6 - halfWidth);
        var10.addVertex(par2 + halfWidth, par4, par6 + halfWidth);
        var10.addVertex(par2 + halfWidth, par4 + height, par6 + halfWidth);
        var10.draw();
        var10.startDrawing(2);
        var10.addVertex(par2 - halfWidth, par4 + height, par6 + halfWidth);
        var10.addVertex(par2 - halfWidth, par4, par6 + halfWidth);
        var10.addVertex(par2 - halfWidth, par4, par6 - halfWidth);
        var10.addVertex(par2 - halfWidth, par4 + height, par6 - halfWidth);
        var10.draw();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2884);
        GL11.glEnable(3042);
        GL11.glDepthMask(true);
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
