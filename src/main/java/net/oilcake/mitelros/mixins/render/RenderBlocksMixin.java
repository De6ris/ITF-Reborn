package net.oilcake.mitelros.mixins.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.block.BlockUruBeacon;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class RenderBlocksMixin {
    @Shadow
    public IBlockAccess blockAccess;

    @Shadow
    public abstract boolean renderBlockByRenderType(Block par1Block, int par2, int par3, int par4);

    @ModifyArg(method = "renderBlockAsItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/RenderBlocks;getBlockIcon(Lnet/minecraft/Block;)Lnet/minecraft/Icon;", ordinal = 0))
    private Block uruBeacon(Block par1Block, @Local(argsOnly = true) Block blockInArg) {
        return blockInArg instanceof BlockUruBeacon ? Block.blockMithril : par1Block;
    }

    @ModifyArg(method = "renderBlockBeacon", at = @At(value = "INVOKE", target = "Lnet/minecraft/RenderBlocks;getBlockIcon(Lnet/minecraft/Block;)Lnet/minecraft/Icon;", ordinal = 1))
    private Block uruBeacon_1(Block par1Block, @Local(argsOnly = true) BlockBeacon par1BlockBeacon) {
        return par1BlockBeacon instanceof BlockUruBeacon ? Block.blockMithril : par1Block;
    }

    @Inject(method = "renderBlockFlowerpot", at = @At(value = "INVOKE", target = "Lnet/minecraft/IBlockAccess;getBlockId(III)I"), cancellable = true)
    private void addITFFlower(BlockFlowerPot par1BlockFlowerPot, int par2, int par3, int par4, CallbackInfoReturnable<Boolean> cir) {
        Tessellator var5 = Tessellator.instance;
        if (this.blockAccess.getBlockId(par2, par3, par4) == Blocks.flowerPotExtend.blockID) {
            float var14 = 0.0F;
            float var15 = 4.0F;
            float var16 = 0.0F;
            var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
            this.renderBlockByRenderType(Blocks.flowerextend, par2, par3, par4);
            var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
            cir.setReturnValue(true);
        }
    }
}
