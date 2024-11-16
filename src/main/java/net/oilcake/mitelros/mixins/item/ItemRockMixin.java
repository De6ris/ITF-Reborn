package net.oilcake.mitelros.mixins.item;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRock.class)
public class ItemRockMixin {
    @Inject(method = "onItemRightClick(Lnet/minecraft/EntityPlayer;Lnet/minecraft/ItemStack;FZ)Z", at = @At("HEAD"), cancellable = true)
    private static void cancel(EntityPlayer player, ItemStack item_stack, float partial_tick, boolean ctrl_is_down, CallbackInfoReturnable<Boolean> cir) {
        if (ITFConfig.CancelItemRockUse.getBooleanValue()) {
            cir.setReturnValue(false);
        }
    }
}
