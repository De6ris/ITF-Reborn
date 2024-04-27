package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemPickaxe;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemPickaxe.class)
public abstract class ItemPickaxeMixin extends ItemTool {
    protected ItemPickaxeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Inject(method = {"<init>(ILnet/minecraft/Material;)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        addMaterialsEffectiveAgainst(new Material[]{Materials.uru, Materials.tungsten, Materials.nickel, Materials.crystal});
    }
}
