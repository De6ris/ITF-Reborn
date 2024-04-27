package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityAnimalWatcher;
import net.minecraft.EntityGhoul;
import net.minecraft.EntityLivingData;
import net.minecraft.World;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityGhoul.class)
public class EntityGhoulMixin extends EntityAnimalWatcher {
    public EntityGhoulMixin(World world) {
        super(world);
    }

    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void setCanPickUpLoot(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> cir) {
        if (ITFConfig.TagPillager.getBooleanValue()) {
            this.setCanPickUpLoot(true);
        }
    }
}
