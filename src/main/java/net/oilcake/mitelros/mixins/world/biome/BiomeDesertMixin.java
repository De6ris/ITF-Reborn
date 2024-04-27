package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.mob.EntityHusk;
import net.oilcake.mitelros.world.WorldGenSulphur;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BiomeGenDesert.class)
public class BiomeDesertMixin extends BiomeGenBase {
    protected BiomeDesertMixin(int par1) {
        super(par1);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        removeEntityFromSpawnableLists(EntityZombie.class);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 100, 1, 4));
    }

    @Inject(method = "decorate", at = @At("TAIL"))
    private void addSulphur(World par1World, Random par2Random, int par3, int par4, CallbackInfo ci) {
        if (par2Random.nextInt(144) == 0) {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenSulphur var7 = new WorldGenSulphur();
            if (par2Random.nextInt(8) == 0) {
                var7.setSuperLarge();
                if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
                    if (Minecraft.inDevMode())
                        System.out.println("Generate Sulphur at " + var5 + " " + var6 + " , superlarge.");
            } else {
                if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
                    if (Minecraft.inDevMode())
                        System.out.println("Generate Sulphur at " + var5 + " " + var6);
            }
        }
    }
}
