package net.oilcake.mitelros.block;

import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockTorch;
import net.minecraft.World;
import net.oilcake.mitelros.config.ITFConfig;

import java.util.Random;

public class BlockTorchIdle extends BlockTorch {
    protected BlockTorchIdle(int par1) {
        super(par1);
        this.setHardness(0.0F);
    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return info.wasExploded() ? 0 : super.dropBlockAsEntityItem(info);
    }

    @Override
    public boolean updateTick(World world, int x, int y, int z, Random random) {
        if (this.checkIfNotLegal(world, x, y, z)) {
            return true;
        } else {
            if (!ITFConfig.TagBurnOut.getBooleanValue()) return false;
            int ran = random.nextInt(512);
            if (ran == 0 && world.getBlockId(x, y, z) == Blocks.torchWoodIdle.blockID) {
                world.setBlock(x, y, z, Blocks.torchWoodExtinguished.blockID, world.getBlockMetadata(x, y, z), 2);
            }
            return false;
        }
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.getBlockId(par2, par3, par4) == Blocks.torchWoodExtinguished.blockID) {
            return;
        }
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
    }
}
