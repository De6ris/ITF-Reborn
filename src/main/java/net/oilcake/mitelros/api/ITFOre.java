package net.oilcake.mitelros.api;

import huix.glacier.api.extension.item.IFusibleItem;
import net.minecraft.BlockOre;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;

public class ITFOre extends BlockOre implements IFusibleItem {
    public ITFOre(int par1, Material vein_material, int min_harvest_level) {
        super(par1, vein_material, min_harvest_level);
    }

    @Override
    public int getHeatLevelRequired() {
        if (this == Blocks.oreNickel) return 2;
        if (this == Blocks.oreTungsten) return 3;
        if (this == Blocks.oreUru) return 4;
        throw new IllegalCallerException("unhandled instance");
    }
}
