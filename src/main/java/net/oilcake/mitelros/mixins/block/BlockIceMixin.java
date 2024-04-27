package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockIce.class)
public abstract class BlockIceMixin extends BlockBreakable {
    protected BlockIceMixin(int par1, String par2Str, Material par3Material, boolean par4, BlockConstants block_constants) {
        super(par1, par2Str, par3Material, par4, block_constants);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At("HEAD"))
    private void addIceChunk(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if (info.wasSilkHarvested()) return;
        int fortune = info.getHarvesterFortune();
        for (int i = 0; i < info.world.rand.nextInt(2 + fortune); i++) {
            this.dropBlockAsEntityItem(info, Items.iceChunk.itemID);
        }
    }
}
