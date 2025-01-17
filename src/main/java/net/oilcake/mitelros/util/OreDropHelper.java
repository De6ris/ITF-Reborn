package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.registry.ITFRegistryImpl;

import java.util.Map;
import java.util.Random;

public class OreDropHelper {
    public static boolean isVulnerableToExplosion(BlockOre blockOre) {
        return blockOre == Blocks.blockAzurite;
    }

    public static int getRawPieceItemID(BlockOre blockOre, int metadata) {
        if (blockOre == Block.oreGold) return metadata == 2 ? Items.pieceGoldNether.itemID : Items.pieceGold.itemID;
        Map<Block, Integer> map = ITFRegistryImpl.PIECE_MAP;
        if (map.containsKey(blockOre)) {
            return map.get(blockOre);
        }
        return 0;
    }

    public static int getMeltPieceItemID(BlockOre blockOre) {
        Map<Block, Integer> map = ITFRegistryImpl.MELTING_MAP;
        if (map.containsKey(blockOre)) {
            return map.get(blockOre);
        }
        return 0;
    }

    public static boolean canAbsorb(BlockOre blockOre) {
        return calcAbsorbXP(blockOre, 1.0F) != 0;
//        return this == Block.oreDiamond || this == Block.oreEmerald || this == Blocks.blockAzurite || this == Block.oreNetherQuartz || this == Block.oreLapis;
    }

    public static int calcAbsorbXP(BlockOre blockOre, float chance) {
        if (blockOre == Blocks.blockAzurite)
            return (int) (((3 + new Random().nextInt(5)) * 1.1F * ItemRock.getExperienceValueWhenSacrificed(new ItemStack(Items.shardAzurite))) * chance);
        if (blockOre == Block.oreLapis)
            return (int) (((3 + new Random().nextInt(3)) * 1.2F * ItemRock.getExperienceValueWhenSacrificed(new ItemStack(Item.dyePowder, 1, 4))) * chance);

        Map<Block, ItemStack> map = ITFRegistryImpl.ABSORBING_MAP;
        if (map.containsKey(blockOre)) {
            ItemStack itemStack = map.get(blockOre);
            return (int) (1.1F * ItemRock.getExperienceValueWhenSacrificed(itemStack) * chance);
        }
        return 0;
    }
}
