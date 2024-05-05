package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
    protected BlockGrassMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @WrapOperation(method = "fertilize", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlock(IIII)Z"))
    private boolean itfFlower(World instance, int var7, int var8, int var9, int blockId, Operation<Boolean> original) {
        Random random = new Random();
        if (random.nextBoolean()) return original.call(instance, var7, var8, var9, blockId);
        int subtype = Blocks.flowerextend.getRandomSubtypeThatCanOccurAt(instance, var7, var8, var9);
        if (random.nextBoolean()) {
            subtype = -1;
        }
        if (subtype >= 0) {
            return instance.setBlock(var7, var8, var9, Blocks.flowerextend.blockID, subtype, 3);
        }
        return false;
    }
}
