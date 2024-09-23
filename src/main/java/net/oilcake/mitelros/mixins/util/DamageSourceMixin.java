package net.oilcake.mitelros.mixins.util;

import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityFishHook;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
    @Shadow
    public abstract Entity getImmediateEntity();

    @Inject(method = "getItemAttackedWith", at = @At("HEAD"), cancellable = true)
    private void tracksTheFishingRod(CallbackInfoReturnable<ItemStack> cir) {
        Entity immediate_entity = this.getImmediateEntity();
        if (immediate_entity != null) {
            if (immediate_entity instanceof EntityFishHook hook) {
                cir.setReturnValue(hook.angler.getHeldItemStack());
            }
        }
    }
}
