package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.World;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityInvisibleStalker.class)
public class EntityInvisibleStalkerMixin extends EntityMob {
    public EntityInvisibleStalkerMixin(World world) {
        super(world);
    }

    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void setCanPickUpLoot(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> cir) {
        if (ITFConfig.TagPillager.getBooleanValue()) {
            this.setCanPickUpLoot(true);
        }
    }
}
