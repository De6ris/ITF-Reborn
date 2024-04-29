package net.oilcake.mitelros.mixins.render;

import net.minecraft.Entity;
import net.minecraft.Item;
import net.minecraft.Render;
import net.minecraft.RenderSnowball;
import net.oilcake.mitelros.entity.misc.EntityWandFireBall;
import net.oilcake.mitelros.entity.misc.EntityWandIceBall;
import net.oilcake.mitelros.entity.misc.EntityWandShockWave;
import net.oilcake.mitelros.entity.misc.EntityWandSlimeBall;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSnowball.class)
public abstract class RenderSnowballMixin extends Render {
    @Shadow
    private Item field_94151_a;

    @Inject(method = "doRender", at = @At("HEAD"))
    private void itfBalls(Entity par1Entity, double par2, double par4, double par6, float par8, float par9, CallbackInfo ci) {
        if (par1Entity instanceof EntityWandIceBall) {
            this.field_94151_a = par1Entity.getModelItem();
        } else if (par1Entity instanceof EntityWandFireBall) {
            this.field_94151_a = par1Entity.getModelItem();
        } else if (par1Entity instanceof EntityWandShockWave) {
            this.field_94151_a = par1Entity.getModelItem();
        } else if (par1Entity instanceof EntityWandSlimeBall) {
            this.field_94151_a = par1Entity.getModelItem();
        }
    }
}
