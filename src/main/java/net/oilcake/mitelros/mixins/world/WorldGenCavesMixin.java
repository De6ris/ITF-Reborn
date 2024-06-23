package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.minecraft.BiomeGenBase;
import net.minecraft.MapGenBase;
import net.minecraft.MapGenCaves;
import net.minecraft.World;
import net.oilcake.mitelros.world.ITFBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MapGenCaves.class)
public class WorldGenCavesMixin extends MapGenBase {
    @Inject(method = "recursiveGenerate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private void modifyFrequency(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte, CallbackInfo ci, @Local BiomeGenBase biome, @Local LocalFloatRef frequency) {
        frequency.set(itfFrequency(biome));
    }

    @Unique
    private static float itfFrequency(BiomeGenBase biome) {
        if (biome != BiomeGenBase.plains && biome != BiomeGenBase.swampland) {
            if (biome == BiomeGenBase.iceMountains) {
                return 1.5F;
            } else if (biome == BiomeGenBase.extremeHills) {
                return 1.5F;
            } else if (biome == ITFBiomes.BIOME_WINDSWEPT_PLEATU) {
                return 2.5F;
            } else {
                return 0.75F;
            }
        } else {
            return 0.5F;
        }
    }
}
