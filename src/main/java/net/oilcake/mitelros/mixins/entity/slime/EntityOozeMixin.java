package net.oilcake.mitelros.mixins.entity.slime;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.DamageSource;
import net.minecraft.EntityCubic;
import net.minecraft.EntityOoze;
import net.minecraft.World;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityOoze.class)
public abstract class EntityOozeMixin extends EntityCubic {
    public EntityOozeMixin(World par1World) {
        super(par1World);
    }

    @ModifyReturnValue(method = "isImmuneTo", at = @At("RETURN"))
    private boolean nickelDamage(boolean original, @Local(argsOnly = true) DamageSource damage_source) {
        original &= !damage_source.isExplosion();
        return (damage_source.getItemAttackedWith() != null) ? (original && damage_source.getItemAttackedWith().getMaterialForRepairs() != Materials.nickel) : original;
    }

    @ModifyConstant(method = "setSize", constant = @Constant(intValue = 2))
    private int larger(int constant) {
        return 4;
    }
}
