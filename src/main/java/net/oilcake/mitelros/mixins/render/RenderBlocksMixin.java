package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class RenderBlocksMixin {
    @Shadow
    public IBlockAccess blockAccess;

    @Shadow
    public abstract boolean renderBlockByRenderType(Block par1Block, int par2, int par3, int par4);

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
