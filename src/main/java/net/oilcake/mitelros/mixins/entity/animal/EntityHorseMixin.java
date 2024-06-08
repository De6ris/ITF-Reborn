package net.oilcake.mitelros.mixins.entity.animal;

import net.minecraft.EntityAgeable;
import net.minecraft.EntityAnimal;
import net.minecraft.EntityHorse;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

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
