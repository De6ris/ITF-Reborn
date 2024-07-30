package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockPortal.class)
public abstract class BlockPortalMixin {
    @Shadow
    protected abstract int getFrameMinX(World world, int x, int y, int z);

    @Shadow
    protected abstract int getFrameMaxX(World world, int x, int y, int z);

    @Shadow
    protected abstract int getFrameMinY(World world, int x, int y, int z);

    @Shadow
    protected abstract int getFrameMaxY(World world, int x, int y, int z);

    @Shadow
    protected abstract int getFrameMinZ(World world, int x, int y, int z);

    @Shadow
    protected abstract int getFrameMaxZ(World world, int x, int y, int z);

    // unchecked
    @ModifyReturnValue(method = "getRunegateType", at = @At("RETURN"))
    private BlockRunestone addTungstenRunegate(BlockRunestone original, @Local(argsOnly = true) World world, @Local(argsOnly = true, ordinal = 0) int x, @Local(argsOnly = true, ordinal = 1) int y, @Local(argsOnly = true, ordinal = 2) int z) {
        if (original != null) return original;
        int frame_min_x = this.getFrameMinX(world, x, y, z);
        int frame_max_x = this.getFrameMaxX(world, x, y, z);
        int frame_min_y = this.getFrameMinY(world, x, y, z);
        int frame_max_y = this.getFrameMaxY(world, x, y, z);
        int frame_min_z = this.getFrameMinZ(world, x, y, z);
        int frame_max_z = this.getFrameMaxZ(world, x, y, z);
        int blockId = Blocks.tungstenRuneStone.blockID;
        if (frame_max_x - frame_min_x > frame_max_z - frame_min_z) {
            if (world.getBlockId(frame_min_x, frame_min_y, z) == blockId && world.getBlockId(frame_max_x, frame_min_y, z) == blockId && world.getBlockId(frame_min_x, frame_max_y, z) == blockId && world.getBlockId(frame_max_x, frame_max_y, z) == blockId) {
                return Blocks.tungstenRuneStone;
            }
            return null;
        }
        if (world.getBlockId(x, frame_min_y, frame_min_z) == blockId && world.getBlockId(x, frame_min_y, frame_max_z) == blockId && world.getBlockId(x, frame_max_y, frame_min_z) == blockId && world.getBlockId(x, frame_max_y, frame_max_z) == blockId) {
            return Blocks.tungstenRuneStone;
        }
        return null;
    }

    @WrapOperation(method = "getRunegateDestinationCoords", at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldServer;getRunegateDomainRadius(Lnet/minecraft/Material;)I"))
    private int addTungstenRunegate(WorldServer instance, Material material, Operation<Integer> original) {
        if (material == Materials.tungsten) {
            return 20000;
        } else {
            return original.call(instance, material);
        }
    }
}
