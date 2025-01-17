package net.oilcake.mitelros.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.WorldGenerator;
import net.oilcake.mitelros.api.ITFBiomeDecorator;

import java.util.Random;

public class BiomeSavannaPlateau extends BiomeGenBase {
    public BiomeSavannaPlateau(int par1) {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 3;
        this.theBiomeDecorator.grassPerChunk = 10;
        ((ITFBiomeDecorator) this.theBiomeDecorator).itf$setFlowersExtendPerChunk(1);
        setBiomeName("SavannaPlateau");
        setColor(16421912);
        setDisableRain();
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.minHeight = 0.9F;
        this.maxHeight = 1.5F;
        setMinMaxHeight(0.9F, 1.5F);
        setTemperatureRainfall(1.6F, 0.0F);
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return (par1Random.nextInt(10) == 0) ? this.worldGeneratorBigTree : this.worldGeneratorTrees;
    }
}
