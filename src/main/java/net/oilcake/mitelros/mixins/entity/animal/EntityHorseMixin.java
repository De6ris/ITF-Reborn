package net.oilcake.mitelros.mixins.entity.animal;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHorse.class)
public abstract class EntityHorseMixin extends EntityAnimal {
    public EntityHorseMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @ModifyArg(method = "dropFewItems", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/EntityHorse;dropItem(II)Lnet/minecraft/EntityItem;", ordinal = 1), index = 0)
    private int horseMeat(int par1) {
        return this.isBurning() ? Items.horse_meat_cooked.itemID : Items.horse_meat.itemID;
    }
}
