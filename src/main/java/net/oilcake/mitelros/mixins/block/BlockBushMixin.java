package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.BlockBush;
import net.minecraft.BlockGrowingPlant;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockBush.class)
public abstract class BlockBushMixin extends BlockGrowingPlant {
    public BlockBushMixin(int block_id) {
        super(block_id);
    }

    @ModifyConstant(method = "getGrowthRate", constant = @Constant(floatValue = 0.1F))
    private float itfSeason(float constant, @Local(argsOnly = true) World world) {
        return constant + ((((ITFWorld) world).getWorldSeason() == 2) ? 0.15F : 0.0F);
    }
}
