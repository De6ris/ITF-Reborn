package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.Config;

public class BlockSmoker extends BlockFurnace {
    protected BlockSmoker(int par1, boolean par2) {
        super(par1, Material.stone, par2);
    }

    public void registerIcons(IconRegister mt) {
        this.furnaceIconFront = mt.registerIcon(this.isActive ? "smoker/smoker_front_on" : "smoker/smoker_front_off");
        this.furnaceIconTop = mt.registerIcon("smoker/smoker_top");
        this.blockIcon = mt.registerIcon("smoker/smoker_side");
    }

    public int getMaxHeatLevel() {
        return 2;
    }

    public int getIdleBlockID() {
        return Blocks.blockSmokerIdle.blockID;
    }

    public int getActiveBlockID() {
        return Blocks.blockSmokerBurning.blockID;
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (Config.TagBenchingV2.get()) {
            if (info.wasExploded()) {
                dropBlockAsEntityItem(info, Block.cobblestone.blockID);
                dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
                return 0;
            }
            dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 8, 1.0F);
            dropBlockAsEntityItem(info, Block.wood.blockID, 0, 4, 1.0F);
            return 0;
        }
        if (info.wasExploded()) {
            dropBlockAsEntityItem(info, Block.cobblestone.blockID);
            dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
            return 0;
        }
        return super.dropBlockAsEntityItem(info);
    }
}
