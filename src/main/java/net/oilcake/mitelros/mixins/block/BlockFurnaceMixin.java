package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.util.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockFurnace.class)
public abstract class BlockFurnaceMixin extends BlockDirectionalWithTileEntity {
    protected BlockFurnaceMixin(int id, Material material, BlockConstants constants) {
        super(id, material, constants);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At("HEAD"), cancellable = true)
    private void inject(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if (ITFConfig.TagBenchingV2.get()) {
            Block furnace_block = Block.getBlock(getIdleBlockID());
            if (furnace_block == Block.furnaceClayIdle) {
                if (!info.wasExploded()) {
                    cir.setReturnValue(dropBlockAsEntityItem(info, Item.clay.itemID, 0, 16, 1.0F));
                }
                cir.setReturnValue(dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.25F));
            }
            if (furnace_block == Block.furnaceHardenedClayIdle) {
                if (!info.wasExploded()) {
                    cir.setReturnValue(dropBlockAsEntityItem(info, Item.brick.itemID, 0, 32, 1.0F));
                }
                cir.setReturnValue(dropBlockAsEntityItem(info, Item.brick.itemID, 0, 4, 1.25F));
            }
            Block model_block = Block.sandStone;// initialize to pass compile
            if (furnace_block == Block.furnaceIdle) {
                model_block = Block.cobblestone;
            } else if (furnace_block == Block.furnaceObsidianIdle) {
                model_block = Block.obsidian;
            } else if (furnace_block == Block.furnaceNetherrackIdle) {
                model_block = Block.netherrack;
            } else {
                cir.setReturnValue(0);
            }
            if (info.wasExploded()) {
                cir.setReturnValue(model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0)));
            }
            cir.setReturnValue(model_block.dropBlockAsEntityItem(info, model_block.blockID, 0, 8, 1.0F));
        }
    }

    @Shadow
    public abstract int getIdleBlockID();

    @ModifyReturnValue(method = "getCraftingDifficultyAsComponent", at = @At("RETURN"))
    private float setDifficulty(float original) {
        return 1920.0F;
    }
}
