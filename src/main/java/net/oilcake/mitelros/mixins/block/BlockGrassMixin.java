package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(BlockGrass.class)
public abstract class BlockGrassMixin extends Block {
    protected BlockGrassMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @Overwrite
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        int r;
        int g;
        int b;
        if (ITFConfig.SeasonColor.get()) {
            for (r = -1; r <= 1; ++r) {
                for (g = -1; g <= 1; ++g) {
                    b = par1IBlockAccess.getBiomeGenForCoords(par2 + g, par4 + r).getBiomeGrassColor();
                    var5 += SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), (b & 16711680) >> 16);
                    var6 += (b & '\uff00') >> 8;
                    var7 += b & 255;
                }
            }
        } else {
            for (r = -1; r <= 1; ++r) {
                for (g = -1; g <= 1; ++g) {
                    b = par1IBlockAccess.getBiomeGenForCoords(par2 + g, par4 + r).getBiomeGrassColor();
                    var5 += (b & 16711680) >> 16;
                    var6 += (b & 65280) >> 8;
                    var7 += b & 255;
                }
            }
        }

        r = var5 / 9 & 255;
        g = var6 / 9 & 255;
        b = var7 / 9 & 255;
        float trampling_effect = getTramplingEffect(getTramplings(par1IBlockAccess.getBlockMetadata(par2, par3, par4)));
        if (trampling_effect > 0.0F) {
            float weight_grass = 1.0F - trampling_effect;
            r = (int) (r * weight_grass + 134.0F * trampling_effect);
            g = (int) (g * weight_grass + 96.0F * trampling_effect);
            b = (int) (b * weight_grass + 67.0F * trampling_effect);
        }
        return r << 16 | g << 8 | b;
    }

    @Redirect(method = "fertilize", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlock(IIII)Z"))
    private boolean itfFlower(World world, int var7, int var8, int var9, int blockId) {
        Random random = new Random();
        if (random.nextBoolean()) return world.setBlock(var7, var8, var9, blockId);
        int subtype = Blocks.flowerextend.getRandomSubtypeThatCanOccurAt(world, var7, var8, var9);
        if (random.nextBoolean())
            subtype = -1;
        if (subtype >= 0)
            return world.setBlock(var7, var8, var9, Blocks.flowerextend.blockID, subtype, 3);
        return false;
    }

    @Shadow
    public static float getTramplingEffect(int tramplings) {
        return 0.0F;
    }

    @Shadow
    public static int getTramplings(int metadata) {
        return 0;
    }
}
