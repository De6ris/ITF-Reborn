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
        ((ITFBiomeDecorator) this.theBiomeDecorator).itf$setFlowersExtendPerChunk(1);
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
}
