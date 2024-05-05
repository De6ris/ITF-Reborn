package net.oilcake.mitelros.mixins.entity.mob;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EntityDragon;
import net.minecraft.EntityLiving;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityDragon.class)
public class EntityEnderDragonMixin extends EntityLiving {
    public EntityEnderDragonMixin(World par1World) {
        super(par1World);
    }

    @WrapOperation(method = "destroyBlocksInAABB", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;setBlockToAir(III)Z"))
    private boolean uruSurvives(World instance, int x, int y, int z, Operation<Boolean> original, @Local(ordinal = 9) int var13) {
        if (var13 == Blocks.oreUru.blockID) {
            return false;
        } else {
            return original.call(instance, x, y, z);
        }
    }
}
