package net.oilcake.mitelros.render;

import net.minecraft.EntityLivingBase;
import net.minecraft.ModelArachnid;
import net.minecraft.RenderArachnid;
import net.oilcake.mitelros.ITFStart;
import org.lwjgl.opengl.GL11;

public class RenderSpiderKing extends RenderArachnid {
    private float scale;

    protected boolean forceGlowOverride() {
        return true;
    }

    public RenderSpiderKing(float scale) {
        super(new ModelArachnid(), new ModelArachnid(), scale);
        this.scale = scale;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, ITFStart.ResourceDomainColon + "textures/entity/spider/" + this.getSubtypeName());
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        super.preRenderCallback(par1EntityLivingBase, par2);
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    public String getSubtypeName() {
        return "spider_king";
    }
}
