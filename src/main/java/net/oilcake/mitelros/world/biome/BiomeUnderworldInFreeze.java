package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.mob.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.mob.EntityRetinueZombie;
import net.oilcake.mitelros.entity.mob.EntitySpiderKing;
import net.oilcake.mitelros.entity.mob.EntityStalkerCreeper;
import net.oilcake.mitelros.world.WorldGenUnderworldCastle;

import java.util.List;
import java.util.Random;

public class BiomeUnderworldInFreeze extends BiomeGenUnderworld {
    public BiomeUnderworldInFreeze(int id) {
        super(id);
        removeEntityFromSpawnableLists(EntitySkeleton.class);
        removeEntityFromSpawnableLists(EntityZombie.class);
        removeEntityFromSpawnableLists(EntityGhoul.class);
        removeEntityFromSpawnableLists(EntityRevenant.class);
        removeEntityFromSpawnableLists(EntityBoneLord.class);
        removeEntityFromSpawnableLists(EntityCreeper.class);
        removeEntityFromSpawnableLists(EntityBoneBodyguard.class);
        removeEntityFromSpawnableLists(EntityRetinueZombie.class);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 100, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 5, 1, 1));
        setColor(10526880);
        setBiomeName("UnderworldInFreeze");
        setEnableSnow();
        setMinMaxHeight(0.9F, 4.0F);
        setTemperatureRainfall(0.0F, 0.5F);
    }

    private void placeMycelium(World world, int chunk_origin_x, int chunk_origin_z) {
        ChunkPostField mycelium_posts = world.getMyceliumPostField();
        Random random = new Random();
        for (int x = chunk_origin_x; x < chunk_origin_x + 16; x++) {
            for (int z = chunk_origin_z; z < chunk_origin_z + 16; z++) {
                List<ChunkPost> posts = mycelium_posts.getNearbyPostsForBlockCoords(x, z);
                for (int i = 0; i < posts.size(); i++) {
                    ChunkPost post = posts.get(i);
                    if (post.getDistanceSqFromBlockCoords(x, z) <= (mycelium_posts.getPostMaxRadiusOfEffectSq() + 4)) {
                        random.setSeed(post.getSeed());
                        random.nextInt();
                        int y = random.nextInt(random.nextBoolean() ? 16 : 72) + 24;
                        y += world.underworld_y_offset;
                        int height = random.nextInt(5) + 1;
                        for (int dy = 0; dy < height; dy++) {
                            if (world.isAirBlock(x, y + 1, z)) {
                                Block block = world.getBlock(x, y, z);
                                if (block != null && block.isAlwaysSolidOpaqueStandardFormCube() && !(block instanceof net.minecraft.BlockMushroomCap)) {
                                    block = world.getBlock(x, y - 1, z);
                                    if (block != null && block.isAlwaysSolidOpaqueStandardFormCube() && world.setBlock(x, y, z, Block.mycelium.blockID, 0, 2)) {
                                        world.getChunkFromBlockCoords(x, z).setHadNaturallyOccurringMycelium();
                                        random.setSeed(post.getSeed() + MathHelper.getIntPairHash(x, z));
                                        random.nextInt();
                                    }
                                }
                                break;
                            }
                            y++;
                        }
                    }
                }
            }
        }
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        placeMycelium(par1World, par3, par4);
        super.decorate(par1World, par2Random, par3, par4);
        if (par2Random.nextInt(4095) == 0) {
            int i = par3 + par2Random.nextInt(16) + 8;
            int j = par4 + par2Random.nextInt(16) + 8;
            WorldGenUnderworldCastle var7 = new WorldGenUnderworldCastle();
            if (Minecraft.inDevMode())
                System.out.println("Generate Castle at " + i + " " + j + ".");
            var7.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
        }
        int var5 = 8 + par2Random.nextInt(24);
        int var6;
        for (var6 = 0; var6 < var5; var6++) {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(60) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);
            if (var10 == Block.stone.blockID)
                par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
            if (var8 < 32) {
                var7 = par3 + par2Random.nextInt(16);
                var9 = par4 + par2Random.nextInt(16);
                if (var10 == Block.stone.blockID)
                    par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
            }
        }
        var5 = 3 + par2Random.nextInt(6);
        for (var6 = 0; var6 < var5; var6++) {
            int count = par3 + par2Random.nextInt(4);
            int temp = par2Random.nextInt(255);
            int x = par4 + par2Random.nextInt(16);
            int y = par1World.getBlockId(count, temp, x);
            if (y == Block.stone.blockID)
                par1World.setBlock(count, temp, x, Block.oreEmerald.blockID, 0, 2);
        }
    }
}
