package net.oilcake.mitelros.mixins.entity.misc;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.FireCookHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {

    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public abstract ItemStack getEntityItem();

    @Shadow
    private float cooking_progress;

    @Shadow
    public abstract void setEntityItemStack(ItemStack par1ItemStack);

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

    @ModifyArg(method = "spentTickInWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemVessel;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;", ordinal = 1))
    private Material itfWater(Material var1) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
            return Material.water;
        } else if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) {
            return Materials.dangerous_water;
        } else {
            return Materials.suspicious_water;
        }
    }

    @Inject(method = "convertItem", at = @At("HEAD"), cancellable = true)
    private void checkNull(Item item, CallbackInfo ci) {
        if (this.worldObj.isRemote) {
            Minecraft.setErrorMessage("convertItem: not meant to be called on client");
            ci.cancel();
            return;
        }
        if (item == null) {
            this.setDead();
            this.entityFX(EnumEntityFX.burned_up_in_lava);
            ci.cancel();
        }
    }

    @WrapOperation(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;canDouseFire()Z"))
    private boolean protectWaterBowl(ItemStack instance, Operation<Boolean> original) {
        FireCookHandler fireCookHandler = FireCookHandler.getInstance();
        if (fireCookHandler.getCookResult(instance).isPresent() || fireCookHandler.isCookResult(instance)) return false;
        return original.call(instance);
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityDamageResult;startTrackingHealth(F)V", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void itfCookItemEntity(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir, EntityDamageResult result) {
        ItemStack item_stack = this.getEntityItem();
        FireCookHandler fireCookHandler = FireCookHandler.getInstance();
        Optional<ItemStack> cookResult = fireCookHandler.getCookResult(item_stack);
        if (damage.isFireDamage() && (cookResult.isPresent() || fireCookHandler.isCookResult(item_stack))) {
            if (cookResult.isPresent()) {
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
                if (cookResult.isEmpty()) {
                    this.setDead();
                    cir.setReturnValue(result.setEntityWasDestroyed());
                    return;
                }
                this.setEntityItemStack(cookResult.get());
            }
            cir.setReturnValue(result.setEntityWasAffected());
        }
    }
}
