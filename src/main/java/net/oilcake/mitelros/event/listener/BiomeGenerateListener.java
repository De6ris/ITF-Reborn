package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IBiomeGenerateListener;
import net.minecraft.BiomeGenBase;
import net.minecraft.GenLayer;
import net.oilcake.mitelros.world.ITFBiomes;

import java.util.List;

public class BiomeGenerateListener implements IBiomeGenerateListener {
    // areBiomesViable params: blockX, blockZ, radius
    // geInts params: blockX, blockZ, xWidth, zWidth, but with >> 2
    @Override
    public int onLayerHills(GenLayer genLayer, int original) {
        if (original == BiomeGenBase.forestHills.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 3L)) {
                case 0 -> ITFBiomes.BIOME_SAVANNA.biomeID;
                case 1 -> ITFBiomes.BIOME_SAVANNA_PLEATU.biomeID;
                default -> BiomeGenBase.forestHills.biomeID;
            };
        }
        if (original == BiomeGenBase.forest.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 3L)) {
                case 0 -> ITFBiomes.BIOME_SAVANNA.biomeID;
                case 1 -> ITFBiomes.BIOME_SAVANNA_PLEATU.biomeID;
                default -> BiomeGenBase.forest.biomeID;
            };
        }
        if (original == BiomeGenBase.iceMountains.biomeID) {
            return switch ((int) (genLayer.chunkSeed % 2L)) {
                case 0 -> ITFBiomes.BIOME_WINDSWEPT_PLEATU.biomeID;
                default -> BiomeGenBase.iceMountains.biomeID;
            };
        }
        return original;
    }

    @Override
    public void onStrongholdAllowedRegister(List<BiomeGenBase> original) {
        original.add(ITFBiomes.BIOME_SAVANNA);
        original.add(ITFBiomes.BIOME_SAVANNA_PLEATU);
        original.add(ITFBiomes.BIOME_WINDSWEPT_PLEATU);
    }
}
