package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemPotion.class)
public class ItemPotionMixin extends Item {
    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback) {
        ((ITFItem) this).setWater(3);
    }

    @Inject(method = "onItemUseFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/Item;onItemUseFinish(Lnet/minecraft/ItemStack;Lnet/minecraft/World;Lnet/minecraft/EntityPlayer;)V"))
    private void addWater(ItemStack item_stack, World world, EntityPlayer player, CallbackInfo ci) {
        if (player.onServer()) {
            player.addWater(((ITFItem) this).getWater());
        }
    }
}
