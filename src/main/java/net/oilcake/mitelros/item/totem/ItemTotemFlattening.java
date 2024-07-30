package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemFlattening extends ItemTotem {
    public ItemTotemFlattening(int id) {
        super(id, Material.dirt, "totem");
    }

    @Override
    public boolean canTrigger(World world, EntityPlayer player) {
        return player.worldObj.isOverworld() && player.getBlockPosY() >= 60;
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        this.flattenEffect(player, player.worldObj, player.getBlockPosX(), player.getBlockPosY(), player.getBlockPosZ(), ITFConfig.TagTotemBlessing.getBooleanValue() ? 14 : 7);
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
                this.trySetAir(world, x, y, z);
                this.trySetAir(world, x, y + 1, z);
                this.trySetAir(world, x, y + 2, z);
                this.trySetAir(world, x, y + 3, z);
                this.trySetAir(world, x, y + 4, z);
                this.trySetBlock(world, deepBlockID, x, y - 5, z);
                this.trySetBlock(world, deepBlockID, x, y - 4, z);
                this.trySetBlock(world, surfaceBlockID, x, y - 3, z);
                this.trySetBlock(world, surfaceBlockID, x, y - 2, z);
                this.trySetBlock(world, surfaceBlockID, x, y - 1, z);
            }
        }
    }

    private void trySetAir(World world, int x, int y, int z) {
        if (world.isAirBlock(x, y, z)) return;
        if (world.getBlockHardness(x, y, z) != 0.0F) return;
        world.setBlockToAir(x, y, z);
    }

    private void trySetBlock(World world, int blockID, int x, int y, int z) {
        if (world.isAirBlock(x, y, z) || world.getBlockHardness(x, y, z) == 0.0F) {
            world.setBlock(x, y, z, blockID, 0, 2);
        }
    }
}
