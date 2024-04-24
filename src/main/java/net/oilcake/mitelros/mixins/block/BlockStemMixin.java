package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockStem.class)
public abstract class BlockStemMixin extends BlockGrowingPlant {
    public BlockStemMixin(int block_id) {
        super(block_id);
    }

    @ModifyConstant(method = "getGrowthRate", constant = @Constant(floatValue = 1.0f, ordinal = 0))
    private float seasonGrowth(float constant, @Local(argsOnly = true) World par1World) {
        return constant + ((ITFWorld) par1World).getSeasonGrowthModifier();
    }
}
