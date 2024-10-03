package net.oilcake.mitelros.mixins.world;

import net.minecraft.ChunkProviderHell;
import net.minecraft.IChunkProvider;
import net.minecraft.Minecraft;
import net.minecraft.World;
import net.oilcake.mitelros.world.WorldGenSulphur;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ChunkProviderHell.class)
public class ChunkProviderHellMixin {
    @Shadow
    private Random hellRNG;

    @Shadow
    private World worldObj;

    @Inject(method = "populate", at = @At(value = "FIELD", target = "Lnet/minecraft/ChunkProviderHell;worldObj:Lnet/minecraft/World;", ordinal = 8, opcode = Opcodes.GETFIELD))
    private void itfSurphur(IChunkProvider par1IChunkProvider, int par2, int par3, CallbackInfo ci) {
        Random par2Random = this.hellRNG;
        World par1World = this.worldObj;
        if (par2Random.nextInt(256) == 0) {
            int var5 = par2 * 16 + par2Random.nextInt(16) + 8;
            int var6 = par3 * 16 + par2Random.nextInt(16) + 8;
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
