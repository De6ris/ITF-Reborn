package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockTorch.class)
public abstract class BlockTorchMixin extends BlockMounted {
    public BlockTorchMixin(int id, Material material, BlockConstants constants) {
        super(id, material, constants);
    }

    @Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
    public void updateTickToExtinguish(World world, int x, int y, int z, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (super.updateTick(world, x, y, z, random)) {
            cir.setReturnValue(true);
        } else {
            int ran = random.nextInt(512);
            if (ran == 0 && world.getBlockId(x, y, z) == Block.torchWood.blockID) {
                world.setBlock(x, y, z, Blocks.torchWoodIdle.blockID, world.getBlockMetadata(x, y, z), 2);
            }
            cir.setReturnValue(false);
        }
    }
}
