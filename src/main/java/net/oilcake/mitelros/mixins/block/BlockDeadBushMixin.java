package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockDeadBush.class)
public class BlockDeadBushMixin extends BlockFlower {
    protected BlockDeadBushMixin(int id, Material material) {
        super(id, material);
    }

    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockDeadBush;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"), index = 1)
    private int itfWitherBranch(int par2, @Local float chance) {
        if (chance == 0.5F) {
            return Items.wither_branch.itemID;
        } else {
            return par2;
        }
    }
}
