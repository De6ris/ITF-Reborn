package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemIngot;
import net.minecraft.ItemNugget;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemNugget.class)
public class ItemNuggetMixin extends ItemIngot {
    protected ItemNuggetMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(at = @At("RETURN"), method = "<init>(ILnet/minecraft/Material;)V")
    private void injectXP(int id, Material material, CallbackInfo callbackInfo) {
        if (material == Material.copper || material == Material.silver || material == Material.iron || material == Materials.nickel) {
            setXPReward(1);
        } else if (material == Material.gold) {
            setXPReward(2);
        } else if (material == Material.ancient_metal) {
            setXPReward(3);
        } else if (material == Material.mithril) {
            setXPReward(4);
        } else if (material == Materials.tungsten) {
            setXPReward(7);
        } else if (material == Materials.adamantium) {
            setXPReward(10);
        } else if (material == Materials.uru) {
            setXPReward(15);
        }
    }
}
