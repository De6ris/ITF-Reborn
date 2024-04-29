package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
    protected BlockGrassMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @Redirect(method = "fertilize", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlock(IIII)Z"))
    private boolean itfFlower(World world, int var7, int var8, int var9, int blockId) {
        Random random = new Random();
        if (random.nextBoolean()) return world.setBlock(var7, var8, var9, blockId);
        int subtype = Blocks.flowerextend.getRandomSubtypeThatCanOccurAt(world, var7, var8, var9);
        if (random.nextBoolean())
            subtype = -1;
        if (subtype >= 0)
            return world.setBlock(var7, var8, var9, Blocks.flowerextend.blockID, subtype, 3);
        return false;
    }
}
