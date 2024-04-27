package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityShadow.class)
public class EntityShadowMixin extends EntityMob {
    @Unique
    private boolean cursed_player;

    public EntityShadowMixin(World par1World) {
        super(par1World);
        this.cursed_player = false;
    }

    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void setCanPickUpLoot(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> cir) {
        if (ITFConfig.TagPillage.getBooleanValue()) {
            this.setCanPickUpLoot(true);
        }
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityShadow;isInSunlight()Z"))
    private void pseudoVision(CallbackInfo ci) {
        if (!ITFConfig.TagPseudoVision.getBooleanValue()) return;
        if (getTarget() != null && !this.cursed_player) {
            EntityLivingBase entityLivingBase = getTarget();
            if (entityLivingBase instanceof EntityPlayer player) {
                this.cursed_player = true;
                player.vision_dimming += entityLivingBase.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
            }
        }
    }
}
