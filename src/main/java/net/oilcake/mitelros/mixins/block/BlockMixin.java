package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemMultiTextureTile;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "<clinit>()V", at = @At("TAIL"))
    private static void injectClinit(CallbackInfo callback) {
        Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTextureTile(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
        Block.pumpkinLantern.setLightValue(0.9375f);
    }
}
