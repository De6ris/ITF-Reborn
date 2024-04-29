package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAppleGold.class)
public abstract class ItemGoldenAppleMixin extends ItemFood {
    @Inject(method = "<init>(IIILjava/lang/String;)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        ((ITFItem) this).setFoodWater(-3);
        this.setPotionEffect(Potion.regeneration.id, 30, 0, 1.0F);
        this.setPotionEffect("+0+1+2-3+13&4-4");
    }

    @Inject(method = "onEaten", at = @At("HEAD"))
    private void addEffect(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, CallbackInfo ci) {
        if (par1ItemStack.getItemSubtype() == 0 && !par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 0));
        }
    }

    @Redirect(method = "onEaten", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;addPotionEffect(Lnet/minecraft/PotionEffect;)V", ordinal = 2))
    private void doNotAddResistance(EntityPlayer instance, PotionEffect potionEffect) {
    }
}
