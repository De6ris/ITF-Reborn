package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.Entity;
import net.minecraft.EntityFishHook;
import net.minecraft.Item;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityFishHook.class)
public abstract class EntityFishHookMixin extends Entity {
    public EntityFishHookMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "getFishType", at = @At("HEAD"), cancellable = true)
    private void moreFishType(CallbackInfoReturnable<Item> cir) {
        if (this.rand.nextInt(8) == 0) {
            cir.setReturnValue(Item.leather);
        }
    }
}
