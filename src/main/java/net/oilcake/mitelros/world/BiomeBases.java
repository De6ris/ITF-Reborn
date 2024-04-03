package net.oilcake.mitelros.world;

import net.minecraft.BiomeGenBase;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.world.biome.BiomeUnderworldInFreeze;
import net.oilcake.mitelros.world.biome.BiomeSavanna;
import net.oilcake.mitelros.world.biome.BiomeSavannaPleatu;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;

public class BiomeBases extends BiomeGenBase {
    public static final BiomeGenBase BIOME_WINDSWEPT_PLEATU = new BiomeWindsweptPleatu(Constant.getNextBiomeID());

    public static final BiomeGenBase BIOME_UNDERWORLD_IN_FREEZE = new BiomeUnderworldInFreeze(Constant.getNextBiomeID());

    public static final BiomeGenBase BIOME_SAVANNA = new BiomeSavanna(Constant.getNextBiomeID());

    public static final BiomeGenBase BIOME_SAVANNA_PLEATU = new BiomeSavannaPleatu(Constant.getNextBiomeID());

    protected BiomeBases(int par1) {
        super(par1);
    }
}
