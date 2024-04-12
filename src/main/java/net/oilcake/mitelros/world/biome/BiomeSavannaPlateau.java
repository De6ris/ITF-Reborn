package net.oilcake.mitelros.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.WorldGenerator;
import net.oilcake.mitelros.api.ITFBiomeDecorator;

import java.util.Random;

public class BiomeSavannaPlateau extends BiomeGenBase {
    public BiomeSavannaPlateau(int par1) {
        super(par1);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setTreesPerChunk(1);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setFlowersPerChunk(3);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setGrassPerChunk(10);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setFlowersExtendPerChunk(1);
        setBiomeName("SavannaPlateau");
        setColor(16421912);
        setDisableRain();
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        setMinMaxHeight(0.9F, 1.5F);
        setTemperatureRainfall(1.6F, 0.0F);
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return (par1Random.nextInt(10) == 0) ? this.worldGeneratorBigTree : this.worldGeneratorTrees;
    }

    private BiomeGenBase setMinMaxHeight(float par1, float par2) {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }

    private BiomeGenBase setTemperatureRainfall(float par1, float par2) {
        if (par1 > 0.1F && par1 < 0.2F)
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        this.temperature = par1;
        this.rainfall = par2;
        return this;
    }
}
