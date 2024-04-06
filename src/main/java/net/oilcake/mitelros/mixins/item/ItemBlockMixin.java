package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.*;
import net.oilcake.mitelros.block.BlockTorchIdle;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlock.class)
public class ItemBlockMixin extends Item {
    @Shadow
    public Block getBlock() {
        return null;
    }

    @Inject(method = "getItemStackForStatsIcon", at = @At("RETURN"))
    private void inject(CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 0) LocalIntRef id) {
        if (this.getBlock() == Blocks.flowerPotExtend) {
            id.set(Item.flowerPot.itemID);
        }
    }

    @Inject(method = "getBurnTime", at = @At("HEAD"), cancellable = true)
    private void inject(ItemStack item_stack, CallbackInfoReturnable<Integer> cir) {
        Block block = this.getBlock();
        if (block instanceof BlockRedstoneTorch) {
            cir.setReturnValue(100);
        } else if (block instanceof BlockTorchIdle) {
            cir.setReturnValue(25);
        }
    }
}
