package net.oilcake.mitelros.mixins.entity.ai;

import net.minecraft.Entity;
import net.minecraft.EntityTracker;
import net.oilcake.mitelros.entity.misc.EntityWandFireBall;
import net.oilcake.mitelros.entity.misc.EntityWandIceBall;
import net.oilcake.mitelros.entity.misc.EntityWandShockWave;
import net.oilcake.mitelros.entity.misc.EntityWandSlimeBall;
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
            addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandIceBall) {
            addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandShockWave) {
            addEntityToTracker(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandSlimeBall) {
            addEntityToTracker(par1Entity, 64, 10, true);
        }
    }

    @Shadow
    public void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4) {
    }
}
