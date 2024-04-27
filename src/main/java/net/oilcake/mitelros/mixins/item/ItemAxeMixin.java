package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemAxe;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAxe.class)
public abstract class ItemAxeMixin extends ItemTool {
    protected ItemAxeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.addMaterialsEffectiveAgainst(new Material[]{Materials.crystal});
    }
}
