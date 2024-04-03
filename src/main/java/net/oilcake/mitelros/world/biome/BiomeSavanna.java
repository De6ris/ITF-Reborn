package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFBiomeDecorator;
import net.oilcake.mitelros.util.Config;

import java.util.Random;

public class BiomeSavanna extends BiomeGenBase {
    private boolean enableRain;

    public BiomeSavanna(int par1) {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 1, 2));
        ((ITFBiomeDecorator) this.theBiomeDecorator).setTreesPerChunk(0);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setFlowersPerChunk(3);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setGrassPerChunk(10);
        ((ITFBiomeDecorator) this.theBiomeDecorator).setFlowersExtendPerChunk(1);
        setBiomeName("Savanna");
        setColor(16421912);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        setMinMaxHeight(0.1F, 0.4F);
        setTemperatureRainfall(1.6F, 0.0F);
        setDisableRain();
        if (((Boolean) Config.TagApocalypse.get()))
            removeEntityFromSpawnableLists(EntityHorse.class);
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return (par1Random.nextInt(10) == 0) ? (WorldGenerator) this.worldGeneratorBigTree : (WorldGenerator) this.worldGeneratorTrees;
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

    private BiomeGenBase setDisableRain() {
        this.enableRain = false;
        return this;
    }
}
