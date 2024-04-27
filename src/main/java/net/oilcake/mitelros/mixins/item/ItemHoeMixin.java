package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.ItemHoe;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemHoe.class)
public abstract class ItemHoeMixin extends ItemTool {
    protected ItemHoeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Inject(method = "<init>(ILnet/minecraft/Material;)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.addMaterialsEffectiveAgainst(new Material[]{Materials.beetroot});
        this.addBlocksEffectiveAgainst(new Block[]{Blocks.beetroots});
    }
}
