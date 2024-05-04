package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.api.ITFWorkbench;
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

    @Inject(method = "getItemStackForStatsIcon", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<ItemStack> cir) {
        if (this.getBlock() == Blocks.flowerPotExtend) {
            cir.setReturnValue(new ItemStack(Item.flowerPot.itemID, 1, 0));
        }
    }

    @Inject(method = "getBurnTime", at = @At("HEAD"), cancellable = true)
    private void inject(ItemStack item_stack, CallbackInfoReturnable<Integer> cir) {
        Block block = this.getBlock();
        if (block == Blocks.torchWoodIdle) {
            cir.setReturnValue(400);
        } else if (block == Blocks.torchWoodExtinguished) {
            cir.setReturnValue(50);
        }
    }

    @Inject(method = "getItemDisplayName", at = @At("HEAD"), cancellable = true)
    private void itfWorkbench(ItemStack item_stack, CallbackInfoReturnable<String> cir) {
        if (item_stack != null && this.getBlock() == Blocks.itfWorkBench) {
            cir.setReturnValue(Translator.get("tile.toolbench." + ITFWorkbench.getToolMaterial(item_stack.getItemSubtype()).name + ".name"));
        }
    }
}
