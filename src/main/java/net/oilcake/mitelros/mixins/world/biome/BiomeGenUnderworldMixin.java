package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;
import net.oilcake.mitelros.world.WorldGenUnderworldCastle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BiomeGenUnderworld.class)
public class BiomeGenUnderworldMixin extends BiomeGenBase {
    protected BiomeGenUnderworldMixin(int par1) {
        super(par1);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        removeEntityFromSpawnableLists(EntityCreeper.class);
        removeEntityFromSpawnableLists(EntityBoneBodyguard.class);
        removeEntityFromSpawnableLists(EntityRetinueZombie.class);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 100, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 5, 1, 1));
    }

    @Inject(method = "decorate", at = @At("TAIL"))
    public void itfDecorate(World par1World, Random par2Random, int par3, int par4, CallbackInfo ci) {
        if (par2Random.nextInt(2048) == 0) {
            int i = par3 + par2Random.nextInt(16) + 8;
            int j = par4 + par2Random.nextInt(16) + 8;
            WorldGenUnderworldCastle var7 = new WorldGenUnderworldCastle();
            if (var7.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j)) {
                if (Minecraft.inDevMode()) {
                    System.out.println("Generate Castle at " + i + " " + j + ".");
                }
            }
        }
        int var5 = 8 + par2Random.nextInt(24);
        for (int var6 = 0; var6 < var5; var6++) {
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
    }
}
