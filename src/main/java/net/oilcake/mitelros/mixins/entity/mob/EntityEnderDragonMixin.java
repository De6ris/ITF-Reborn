package net.oilcake.mitelros.mixins.entity.mob;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EntityDragon;
import net.minecraft.EntityLiving;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragon.class)
public class EntityEnderDragonMixin extends EntityLiving {
    public EntityEnderDragonMixin(World par1World) {
        super(par1World);
    }

    @Redirect(method = "destroyBlocksInAABB", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlockToAir(III)Z"))
    private boolean uruSurvives(World instance, int x, int y, int z, @Local(ordinal = 9) int var13) {
        if (var13 == Blocks.oreUru.blockID) {
            return false;
        } else {
            return instance.setBlockToAir(x, y, z);
        }
    }
}
