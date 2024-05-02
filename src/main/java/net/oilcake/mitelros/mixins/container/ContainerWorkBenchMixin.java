package net.oilcake.mitelros.mixins.container;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.Block;
import net.minecraft.ContainerWorkbench;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ContainerWorkbench.class)
public class ContainerWorkBenchMixin {
    @ModifyExpressionValue(method = "canInteractWith", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;getBlockId(III)I"))
    private int enableITFWorkBench(int original) {
        if (original == Blocks.itfWorkBench.blockID) return Block.workbench.blockID;
        return original;
    }
}
