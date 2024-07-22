package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemFlattening extends ItemTotem {
    public ItemTotemFlattening(int id) {
        super(id, Material.dirt, "totem");
    }

    @Override
    public boolean canTrigger(World world, EntityPlayer player) {
        return player.worldObj.getDimensionId() == 0 && player.getBlockPosY() >= 60;
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        this.flattenEffect(player, player.worldObj, player.getBlockPosX(), player.getBlockPosY(), player.getBlockPosZ(), ITFConfig.TagTotemBlessing.getBooleanValue() ? 15 : 7);
    }

    private void flattenEffect(EntityPlayer player, World world, int startX, int y, int startZ, int range) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.smoke_and_steam);
        }
        BiomeGenBase biome = player.getBiome();
        boolean sand = biome.isDesertBiome() || biome == BiomeGenBase.beach;
        int surfaceBlockID = sand ? Block.sand.blockID : Block.dirt.blockID;
        int deepBlockID = sand ? Block.sandStone.blockID : Block.stone.blockID;
        for (int x = startX - range; x <= startX + range; x++) {
            for (int z = startZ - range; z <= startZ + range; z++) {
                world.setBlockToAir(x, y, z);
                world.setBlockToAir(x, y + 1, z);
                world.setBlockToAir(x, y + 2, z);
                world.setBlockToAir(x, y + 3, z);
                world.setBlockToAir(x, y + 4, z);
                world.setBlock(x, y - 5, z, deepBlockID, 0, 2);
                world.setBlock(x, y - 4, z, deepBlockID, 0, 2);
                world.setBlock(x, y - 3, z, surfaceBlockID, 0, 2);
                world.setBlock(x, y - 2, z, surfaceBlockID, 0, 2);
                world.setBlock(x, y - 1, z, surfaceBlockID, 0, 2);
            }
        }
    }
}
