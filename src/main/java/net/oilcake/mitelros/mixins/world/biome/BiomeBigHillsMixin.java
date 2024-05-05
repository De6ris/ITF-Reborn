package net.oilcake.mitelros.mixins.world.biome;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenHills;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BiomeGenHills.class)
public class BiomeBigHillsMixin extends BiomeGenBase {
    protected BiomeBigHillsMixin(int par1) {
        super(par1);
    }

    @WrapOperation(method = "decorate", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlock(IIIIII)Z"))
    private boolean noEmerald(World instance, int var7, int block_id_before, int var9, int i, int par1, int par2, Operation<Boolean> original) {
        return false;
    }
}
