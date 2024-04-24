package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
            cir.setReturnValue(this.itfDropFurnace(info));
        }
    }

    @Unique
    private int itfDropFurnace(BlockBreakInfo info) {
        Block model_block, furnace_block = Block.getBlock(getIdleBlockID());
        if (furnace_block == Block.furnaceClayIdle) {
            if (!info.wasExploded())
                return dropBlockAsEntityItem(info, Item.clay.itemID, 0, 16, 1.0F);
            return dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.25F);
        }
        if (furnace_block == Block.furnaceHardenedClayIdle) {
            if (!info.wasExploded())
                return dropBlockAsEntityItem(info, Item.brick.itemID, 0, 32, 1.0F);
            return dropBlockAsEntityItem(info, Item.brick.itemID, 0, 4, 1.25F);
        }
        if (furnace_block == Block.furnaceSandstoneIdle) {
            model_block = Block.sandStone;
        } else if (furnace_block == Block.furnaceIdle) {
            model_block = Block.cobblestone;
        } else if (furnace_block == Block.furnaceObsidianIdle) {
            model_block = Block.obsidian;
        } else if (furnace_block == Block.furnaceNetherrackIdle) {
            model_block = Block.netherrack;
        } else {
            return 0;
        }
        if (info.wasExploded())
            return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
        return model_block.dropBlockAsEntityItem(info, model_block.blockID, 0, 8, 1.0F);
    }

    @Shadow
    public abstract int getIdleBlockID();

    @ModifyReturnValue(method = "getCraftingDifficultyAsComponent", at = @At("RETURN"))
    private float setDifficulty(float original) {
        return 1920.0F;
    }
}
