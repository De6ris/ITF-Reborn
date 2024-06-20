package net.oilcake.mitelros.world;

import net.minecraft.BiomeGenBase;
import net.oilcake.mitelros.world.biome.BiomeSavanna;
import net.oilcake.mitelros.world.biome.BiomeSavannaPlateau;
import net.oilcake.mitelros.world.biome.BiomeUnderworldInFreeze;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPlateau;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class BiomeBases extends BiomeGenBase {
    static {
        getNextBiomeID();
    }

    private static int getNextBiomeID() {
        return IdUtil.getNextBiomeId();
    }

    public static final BiomeGenBase BIOME_WINDSWEPT_PLEATU = new BiomeWindsweptPlateau(getNextBiomeID());

    public static final BiomeGenBase BIOME_UNDERWORLD_IN_FREEZE = new BiomeUnderworldInFreeze(getNextBiomeID());

    public static final BiomeGenBase BIOME_SAVANNA = new BiomeSavanna(getNextBiomeID());

    public static final BiomeGenBase BIOME_SAVANNA_PLEATU = new BiomeSavannaPlateau(getNextBiomeID());

    protected BiomeBases(int par1) {
        super(par1);
    }
}
