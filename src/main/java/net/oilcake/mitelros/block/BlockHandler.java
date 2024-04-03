package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.Material;
import net.minecraft.Minecraft;

public class BlockHandler {

    public static Block getMatchingBlock(Class<?> item_class, Material material) {
        Block matching_block = null;
        for (int i = 0; i < 256; i++) {
            Block block = Block.getBlock(i);
            if (block != null && block.getClass() == item_class && block.blockMaterial == material)
                if (matching_block == null) {
                    matching_block = block;
                } else {
                    Minecraft.setErrorMessage("getMatchingItem: more than one item matched " + item_class + ", " + material);
                }
        }
        return matching_block;
    }
}
