package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.config.ITFConfig;

public class BlockBlastFurnace extends BlockFurnace {
    private final Material material;

    protected BlockBlastFurnace(int par1, Material par2, boolean par3) {
        super(par1, par2, par3);
        this.material = par2;
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
    }

    public void registerIcons(IconRegister mt) {
        this.blockIcon = mt.registerIcon(ITFStart.ResourceDomainColon + "blastfurnace/" + this.material + "/side");
        this.furnaceIconFront = mt.registerIcon(ITFStart.ResourceDomainColon + (this.isActive ? ("blastfurnace/" + this.material + "/on") : ("blastfurnace/" + this.material + "/off")));
        this.furnaceIconTop = mt.registerIcon(ITFStart.ResourceDomainColon + "blastfurnace/" + this.material + "/top");
    }

    public int getIdleBlockID() {
        return (this.material == Material.stone) ? Blocks.blastFurnaceStoneIdle.blockID : ((this.material == Material.obsidian) ? Blocks.blastFurnaceObsidianIdle.blockID : ((this.material == Material.netherrack) ? Blocks.blastFurnaceNetherrackIdle.blockID : 0));
    }

    public int getActiveBlockID() {
        return (this.material == Material.stone) ? Blocks.blastFurnaceStoneBurning.blockID : ((this.material == Material.obsidian) ? Blocks.blastFurnaceObsidianBurning.blockID : ((this.material == Material.netherrack) ? Blocks.blastFurnaceNetherrackBurning.blockID : 0));
    }

    public int getMaxHeatLevel() {
        return (this.material == Material.stone) ? 2 : ((this.material == Material.obsidian) ? 3 : ((this.material == Material.netherrack) ? 4 : 0));
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        Block furnace_block = Block.getBlock(getIdleBlockID());
        if (ITFConfig.TagBenchingV2.getBooleanValue()) {
            if (!info.wasExploded()) {
                if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
                    dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 8, 1.0F);
                    dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 3, 1.0F);
                } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
                    dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 8, 1.0F);
                    dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 3, 1.0F);
                } else if (furnace_block == Blocks.blastFurnaceStoneIdle) {
                    dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 8, 1.0F);
                    dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 3, 1.0F);
                } else {
                    return 0;
                }
            } else if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
                dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 1, 1.3F);
                dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 1, 1.3F);
            } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
                dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 1, 1.0F);
                dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, 2, 1.3F);
                dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 1, 1.3F);
                dropBlockAsEntityItem(info, Item.mithrilNugget.itemID, 0, 2, 1.3F);
            } else if (furnace_block == Blocks.blastFurnaceStoneIdle) {
                dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 1, 1.3F);
                dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 1, 0.5F);
                dropBlockAsEntityItem(info, Item.ironNugget.itemID, 0, 2, 1.3F);
            } else {
                return 0;
            }
            return 0;
        }
        if (!info.wasExploded())
            return super.dropBlockAsEntityItem(info);
        if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
            dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 1, 1.3F);
            dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 1, 1.3F);
        } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
            dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 1, 1.0F);
            dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, 2, 1.3F);
            dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 1, 1.3F);
            dropBlockAsEntityItem(info, Item.mithrilNugget.itemID, 0, 2, 1.3F);
        } else if (furnace_block == Blocks.blastFurnaceStoneIdle) {
            dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 1, 1.3F);
            dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 1, 0.5F);
            dropBlockAsEntityItem(info, Item.ironNugget.itemID, 0, 2, 1.3F);
        } else {
            return 0;
        }
        return 0;
    }
}
