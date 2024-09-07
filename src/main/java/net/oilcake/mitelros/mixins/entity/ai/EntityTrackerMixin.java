package net.oilcake.mitelros.mixins.entity.ai;

import net.minecraft.Entity;
import net.minecraft.EntityTracker;
import net.oilcake.mitelros.entity.misc.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTracker.class)
public class EntityTrackerMixin {
    @Inject(method = "addEntityToTracker(Lnet/minecraft/Entity;)V", at = @At("TAIL"))
    private void itfEntity(Entity par1Entity, CallbackInfo ci) {
        if (par1Entity instanceof EntityWandFireBall) {
            this.addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandIceBall) {
            this.addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandShockWave) {
            this.addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandSlimeBall) {
            this.addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandPearl) {
            this.addEntityToTracker(par1Entity, 64, 10, true);
        }
    }

    @Shadow
    public void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4) {
    }
}
