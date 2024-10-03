package net.oilcake.mitelros.mixins.tileentity;

import net.minecraft.Potion;
import net.minecraft.TileEntity;
import net.minecraft.TileEntityBeacon;
import net.oilcake.mitelros.api.ITFTileEntityBeacon;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
