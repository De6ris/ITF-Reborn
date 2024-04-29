package net.oilcake.mitelros.block;

import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockFlowerPot;
import net.minecraft.Item;
import net.minecraft.ItemStack;

public class BlockFlowerPotExtend extends BlockFlowerPot {
    public BlockFlowerPotExtend(int par1) {
        super(par1);
    }

    @Override
    public String getMetadataNotes() {
        return "15 is empty and other are same as flowers";
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return (metadata != 15 && Blocks.flowerextend.isValidMetadata(metadata));
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (!info.wasExploded() && !info.wasCrushed()) {
            int num_drops;
            return (num_drops = super.dropBlockAsEntityItem(info, Item.flowerPot)) > 0 ? num_drops + this.dropBlockAsEntityItem(info, getPlantForMeta(info.getMetadata())) : 0;
        } else {
            return 0;
        }
    }

    public static ItemStack getPlantForMeta(int metadata) {
        return (metadata == 15) ? null : new ItemStack(Blocks.flowerextend, 1, metadata);
    }

    public static int getMetaForPlant(ItemStack item_stack) {
        return (item_stack.itemID == Blocks.flowerextend.blockID) ? item_stack.getItemSubtype() : 0;
    }
}
