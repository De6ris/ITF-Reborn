package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemWritableBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemWritableBook.class)
public class ItemWritableBookMixin extends Item {
    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void setCraftingDifficulty(CallbackInfo callbackInfo) {
        setCraftingDifficultyAsComponent(100.0F);
    }
}
