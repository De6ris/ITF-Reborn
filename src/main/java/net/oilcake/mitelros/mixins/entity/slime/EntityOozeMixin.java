package net.oilcake.mitelros.mixins.entity.slime;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
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
    private boolean notImmuneToNickelDamage(boolean original, @Local(argsOnly = true) DamageSource damage_source) {
        if (damage_source.isExplosion()) return false;
        ItemStack itemAttackedWith = damage_source.getItemAttackedWith();
        if (itemAttackedWith == null) return original;
        if (itemAttackedWith.getMaterialForRepairs() == Materials.nickel) return false;
        if (itemAttackedWith.getItem() instanceof ItemFishingRod itemFishingRod && itemFishingRod.getHookMaterial() == Materials.nickel) return false;
        return original;
    }

    @ModifyConstant(method = "setSize", constant = @Constant(intValue = 2))
    private int larger(int constant) {
        return 4;
    }
}
