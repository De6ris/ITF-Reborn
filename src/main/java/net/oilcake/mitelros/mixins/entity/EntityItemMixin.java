package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {

    private boolean isExploded;

    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public abstract void tryRemoveFromWorldUniques();

    @Inject(method = "onCollideWithPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityItem;playSound(Ljava/lang/String;FF)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void achievements(EntityPlayer par1EntityPlayer, CallbackInfo ci, boolean was_empty_handed_before, ItemStack var2, int var3) {
        if (var2.itemID == Items.pieceCopper.itemID || var2.itemID == Items.pieceGold.itemID || var2.itemID == Items.pieceSilver.itemID || var2.itemID == Items.pieceIron.itemID)
            par1EntityPlayer.triggerAchievement(AchievementExtend.FragofMine);
        if (var2.itemID == Items.pieceCopper.itemID || var2.itemID == Items.pieceGold.itemID || var2.itemID == Items.pieceSilver.itemID || var2.itemID == Items.pieceIron.itemID)
            par1EntityPlayer.triggerAchievement(AchievementExtend.FragofMine);
        if (var2.itemID == Item.recordUnderworld.itemID || var2.itemID == Item.recordDescent.itemID || var2.itemID == Item.recordWanderer.itemID || var2.itemID == Item.recordLegends.itemID)
            par1EntityPlayer.triggerAchievement(AchievementExtend.SoundofUnder);
        if (var2.itemID == Item.skull.itemID)
            par1EntityPlayer.triggerAchievement(AchievementExtend.getWitherSkull);
    }

    @ModifyArg(method = "spentTickInWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemVessel;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;"))
    private Material itfWater(Material var1) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        if (var1 != Material.water) {
            return var1;
        } else {
            if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                return Material.water;
            } else if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) {
                return Materials.dangerous_water;
            } else {
                return Materials.unsafe_water;
            }
        }
    }

    @Inject(method = "handleExplosion(Lnet/minecraft/Explosion;)Z", cancellable = true, at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityItem;calcExplosionForce(FD)F"))
    private void injectCancelExplosionCopy(CallbackInfoReturnable<Boolean> callback) {
        if (this.isExploded) {
            setDead();
            tryRemoveFromWorldUniques();
            callback.setReturnValue(Boolean.valueOf(true));
            callback.cancel();
        }
    }

    @Redirect(method = "handleExplosion(Lnet/minecraft/Explosion;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityItem;tryRemoveFromWorldUniques()V"))
    private void injectUpdateExploded(EntityItem caller) {
        this.isExploded = true;
        tryRemoveFromWorldUniques();
    }
}
