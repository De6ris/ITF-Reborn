package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.AchievementExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {
    @Unique
    private boolean isExploded;

    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public abstract void tryRemoveFromWorldUniques();

    @Shadow
    public abstract ItemStack getEntityItem();

    @Shadow
    private float cooking_progress;

    @Shadow
    public abstract void setEntityItemStack(ItemStack par1ItemStack);

    @Shadow
    private int health;

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
                return Materials.suspicious_water;
            }
        }
    }

    @Inject(method = "handleExplosion(Lnet/minecraft/Explosion;)Z", cancellable = true, at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityItem;calcExplosionForce(FD)F"))
    private void injectCancelExplosionCopy(CallbackInfoReturnable<Boolean> callback) {
        if (this.isExploded) {
            setDead();
            tryRemoveFromWorldUniques();
            callback.setReturnValue(Boolean.TRUE);
            callback.cancel();
        }
    }

    @Redirect(method = "handleExplosion(Lnet/minecraft/Explosion;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityItem;tryRemoveFromWorldUniques()V"))
    private void injectUpdateExploded(EntityItem caller) {
        this.isExploded = true;
        tryRemoveFromWorldUniques();
    }
    @Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityDamageResult;startTrackingHealth(F)V"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void makeCharcoal(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir, EntityDamageResult result) {
        ItemStack item_stack = this.getEntityItem();
        Item item = item_stack.getItem();
        if (damage.isFireDamage() && (item.itemID == Block.wood.blockID || item.itemID == Item.coal.itemID)) {
            if (item.itemID == Block.wood.blockID) {
                int x = this.getBlockPosX();
                int y = this.getBlockPosY();
                int z = this.getBlockPosZ();
                for (int dx = -1; dx <= 1; ++dx) {
                    for (int dz = -1; dz <= 1; ++dz) {
                        Block block = this.worldObj.getBlock(x + dx, y, z + dz);
                        if (block != Block.fire) continue;
                        this.worldObj.getAsWorldServer().addScheduledBlockOperation(EnumBlockOperation.try_extinguish_by_items, x + dx, y, z + dz, (this.worldObj.getTotalWorldTime() / 10L + 1L) * 10L, false);
                    }
                }
            }
            this.cooking_progress += damage.getAmount() * 3.0f;
            if (this.cooking_progress >= 100.0f) {
                ItemStack cooked_item_stack = new ItemStack(Item.coal, item_stack.stackSize, 1);
                this.setEntityItemStack(cooked_item_stack);
            }
            cir.setReturnValue(result.setEntityWasAffected());
        }
    }
}
