package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFBiomeDecorator;

import java.util.Random;

public class BiomeSavanna extends BiomeGenBase {

    public BiomeSavanna(int par1) {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 1, 2));
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = 3;
        this.theBiomeDecorator.grassPerChunk = 10;
        ((ITFBiomeDecorator) this.theBiomeDecorator).setFlowersExtendPerChunk(1);
        setBiomeName("Savanna");
        setColor(16421912);
        setDisableRain();
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        setMinMaxHeight(0.1F, 0.4F);
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
