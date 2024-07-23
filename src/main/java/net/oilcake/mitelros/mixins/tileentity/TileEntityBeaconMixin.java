package net.oilcake.mitelros.mixins.tileentity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFTileEntityBeacon;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityBeacon.class)
public abstract class TileEntityBeaconMixin extends TileEntity implements ITFTileEntityBeacon {
    @Shadow
    private int primaryEffect;
    @Shadow
    private int levels;
    @Shadow
    private int secondaryEffect;
    @Unique
    private boolean isAdvanced;

    @Override
    public TileEntityBeacon itf$SetIsAdvanced(boolean advanced) {
        this.isAdvanced = advanced;
        return ReflectHelper.dyCast(this);
    }

    @Override
    public boolean itf$GetIsAdvanced() {
        return this.isAdvanced;
    }

    @Override
    public Potion[][] itf$GetITFEffectList() {
        return new Potion[][]{{Potion.fireResistance, Potion.resistance}, {Potion.nightVision, Potion.waterBreathing}, {PotionExtend.stretch}, {Potion.field_76443_y}};
    }

    @Inject(method = "setPrimaryEffect", at = @At("HEAD"), cancellable = true)
    private void itfPrimaryEffect(int par1, CallbackInfo ci) {
        if (!this.isAdvanced) return;
        this.primaryEffect = 0;
        for (int var2 = 0; var2 < this.levels && var2 < 3; ++var2) {
            for (Potion var6 : this.itf$GetITFEffectList()[var2]) {
                if (var6.id != par1) continue;
                this.primaryEffect = par1;
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "setSecondaryEffect", at = @At("HEAD"), cancellable = true)
    private void itfSecondaryEffect(int par1, CallbackInfo ci) {
        if (!this.isAdvanced) return;
        this.secondaryEffect = 0;
        if (this.levels >= 4) {
            for (int var2 = 0; var2 < 4; ++var2) {
                for (Potion var6 : this.itf$GetITFEffectList()[var2]) {
                    if (var6.id != par1) continue;
                    this.secondaryEffect = par1;
                    ci.cancel();
                    return;
                }
            }
        }
    }

    @ModifyExpressionValue(method = "updateState", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;getBlockId(III)I"))
    private int addITFMetals(int original) {
        if (this.isAdvanced) {
            if (original == Block.blockGold.blockID || original == Block.blockSilver.blockID || original == Block.blockCopper.blockID) {
                return Block.blockAncientMetal.blockID;//can not activate
            }
        }
        if (original == Block.blockAncientMetal.blockID || original == Blocks.blockNickel.blockID || original == Blocks.blockTungsten.blockID)
            return Block.blockEmerald.blockID;//can activate
        return original;
    }

    @Inject(method = "isItemValidForSlot", at = @At("HEAD"), cancellable = true)
    private void addITFMetal(int par1, ItemStack par2ItemStack, CallbackInfoReturnable<Boolean> cir) {
        if (par2ItemStack.itemID == Items.nickelIngot.itemID || par2ItemStack.itemID == Items.tungstenIngot.itemID || par2ItemStack.itemID == Items.uruIngot.itemID)
            cir.setReturnValue(true);
    }
}
