package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.BiomeGenBase;
import net.minecraft.GenLayer;
import net.minecraft.GenLayerHills;
import net.oilcake.mitelros.world.ITFBiomes;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GenLayerHills.class)
public abstract class GenLayerHillsMixin extends GenLayer {
    public GenLayerHillsMixin(long par1) {
        super(par1);
    }
    // areBiomesViable params: blockX, blockZ, radius
    // geInts params: blockX, blockZ, xWidth, zWidth, but with >> 2

    @ModifyReceiver(method = "getInts", at = @At(value = "FIELD", target = "Lnet/minecraft/BiomeGenBase;biomeID:I", ordinal = 3, opcode = Opcodes.GETFIELD))
    private BiomeGenBase modifyForestHills(BiomeGenBase instance) {
        return switch ((int) (this.chunkSeed % 3L)) {
            case 0 -> ITFBiomes.BIOME_SAVANNA;
            case 1 -> ITFBiomes.BIOME_SAVANNA_PLEATU;
            default -> BiomeGenBase.forestHills;
        };
    }

    @ModifyReceiver(method = "getInts", at = @At(value = "FIELD", target = "Lnet/minecraft/BiomeGenBase;biomeID:I", ordinal = 7, opcode = Opcodes.GETFIELD))
    private BiomeGenBase modifyForest(BiomeGenBase instance) {
        return switch ((int) (this.chunkSeed % 3L)) {
            case 0 -> ITFBiomes.BIOME_SAVANNA;
            case 1 -> ITFBiomes.BIOME_SAVANNA_PLEATU;
            default -> BiomeGenBase.forest;
        };
    }

    @ModifyReceiver(method = "getInts", at = @At(value = "FIELD", target = "Lnet/minecraft/BiomeGenBase;biomeID:I", ordinal = 9, opcode = Opcodes.GETFIELD))
    private BiomeGenBase modifyIceMountains(BiomeGenBase instance) {
        return (this.chunkSeed % 2L == 0L) ? ITFBiomes.BIOME_WINDSWEPT_PLEATU : BiomeGenBase.iceMountains;
    }
}
