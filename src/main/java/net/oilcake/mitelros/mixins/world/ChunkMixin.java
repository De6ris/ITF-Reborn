package net.oilcake.mitelros.mixins.world;

import net.minecraft.Chunk;
import net.minecraft.ExtendedBlockStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public class ChunkMixin {
    @Shadow
    public ExtendedBlockStorage[] storageArrays;

    @Overwrite// TODO fml integration
    public final int getBlockIDOptimized(int xz_index, int y) {
        ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
        int x = xz_index & 15;
        y = y & 15;
        int z = xz_index >> 4;
        return ebs == null ? 0 : ebs.getExtBlockID(x, y, z);
    }
}
