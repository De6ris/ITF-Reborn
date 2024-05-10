package net.oilcake.mitelros.block.api;

import net.minecraft.Block;
import net.minecraft.BlockDoor;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;

public class ITFDoor extends BlockDoor {
    public ITFDoor(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    public static Block getDoor(Material material) {
        if (material == Materials.nickel) return Blocks.doorNickel;
        if (material == Materials.tungsten) return Blocks.doorTungsten;
        return null;
    }
}
