package net.oilcake.mitelros.mixins.world;

import net.minecraft.ChunkProviderGenerate;
import net.minecraft.IChunkProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkProviderGenerate.class)
public abstract class ChunkProviderGenerateMixin implements IChunkProvider {
    @ModifyConstant(method = "initializeNoiseField", constant = @Constant(doubleValue = 684.412, ordinal = 0))
    private double modify(double constant) {
        return 513.309d;
    }

    @ModifyConstant(method = "initializeNoiseField", constant = @Constant(doubleValue = 200.0))
    private double modify1(double constant) {
        return 120.0d;
    }

    @ModifyConstant(method = "initializeNoiseField", constant = @Constant(doubleValue = 0.5, ordinal = 1))
    private double modify2(double constant) {
        return 0.25d;
    }

    @ModifyConstant(method = "initializeNoiseField", constant = @Constant(doubleValue = 80.0))
    private double modify3(double constant) {
        return 60.0d;
    }// TODO what does them do

}
