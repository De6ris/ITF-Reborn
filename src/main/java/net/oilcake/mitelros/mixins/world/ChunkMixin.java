package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Chunk;
import net.oilcake.mitelros.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Chunk.class)
public class ChunkMixin {
    @ModifyExpressionValue(method = "setBlockIDWithMetadata", at = @At(value = "FIELD", target = "Lnet/minecraft/BlockRunestone;blockID:I", opcode = Opcodes.GETFIELD, ordinal = 0))
    private int addTungstenRuneStone(int original, @Local(argsOnly = true, ordinal = 3) int par4) {
        if (par4 == Blocks.tungstenRuneStone.blockID) {
            return Blocks.tungstenRuneStone.blockID;
        }
        return original;
    }
}
