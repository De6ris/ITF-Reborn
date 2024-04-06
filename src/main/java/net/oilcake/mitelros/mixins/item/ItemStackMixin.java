package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemStack.class})
public class ItemStackMixin {

    @Shadow
    public int itemID;

    public int getWater() {
        return ((ITFItem) getItem()).getWater();
    }

//    @Redirect(method = {"getTooltip(Lnet/minecraft/EntityPlayer;ZLnet/minecraft/Slot;)Ljava/util/List;"}, at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;add(Ljava/lang/Object;)Z", ordinal = 3))
//    private boolean removeOriginallyEnchantmentInfo0(ArrayList list, Object o) {
//        return false;
//    }
//
//    @Redirect(method = {"getTooltip(Lnet/minecraft/EntityPlayer;ZLnet/minecraft/Slot;)Ljava/util/List;"}, at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;add(Ljava/lang/Object;)Z", ordinal = 2))
//    private boolean removeOriginallyEnchantmentInfo1(ArrayList list, Object o) {
//        return false;
//    }


    @Inject(method ="isEnchantable", at = @At("HEAD"))
    private void legendary(CallbackInfoReturnable<Boolean> cir) {
        if (ItemGoldenAppleLegend.isUnenchantedGoldenApple(this.getItem().getItemStackForStatsIcon())) {
            cir.setReturnValue(true);
        }
    }

    @Shadow
    public Item getItem() {
        return null;
    }
}
