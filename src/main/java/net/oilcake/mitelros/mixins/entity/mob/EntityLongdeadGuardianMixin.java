package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLongdeadGuardian.class)
public class EntityLongdeadGuardianMixin extends EntityLongdead {
    @Shadow
    ItemStack stowed_item_stack;

    public EntityLongdeadGuardianMixin(World world) {
        super(world);
    }

    @Inject(method = "addRandomWeapon", at = @At("TAIL"))
    private void addSword(CallbackInfo ci) {
        if (getHeldItem() instanceof ItemSword)
            this.stowed_item_stack = (new ItemStack(Item.bowAncientMetal)).randomizeForMob(this, true);
    }
}
