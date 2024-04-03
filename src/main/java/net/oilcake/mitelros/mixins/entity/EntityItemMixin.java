package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({EntityItem.class})
public abstract class EntityItemMixin extends Entity {

    private boolean isExploded;

    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public abstract void tryRemoveFromWorldUniques();

    @Shadow
    public abstract void convertItem(Item item);

    @Shadow
    public abstract ItemStack getEntityItem();

//    /**// TODO I dont know its function
//     * @author
//     * @reason
//     */
//    @Overwrite
//    public EntityDamageResult attackEntityFrom(Damage damage) {
//        EntityDamageResult result = super.attackEntityFrom(damage);
//        if (result != null && !result.entityWasDestroyed()) {
//            ItemStack item_stack = getEntityItem();
//            if (item_stack == null) {
//                Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item_stack");
//                return null;
//            }
//            Item item = item_stack.getItem();
//            if (item == null) {
//                Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item");
//                return null;
//            }
//            if (item == Item.netherStar && damage.isExplosion())
//                return null;
//            if (damage.isLavaDamage() && isHarmedByLava())
//                return destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
//            if (damage.isFireDamage() && getEntityItem().canDouseFire())
//                return destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
//            if (damage.getSource() == DamageSource.pepsin && !isHarmedByPepsin())
//                return null;
//            if (damage.getSource() == DamageSource.acid && !isHarmedByAcid())
//                return null;
//            setBeenAttacked();
//            if (item_stack.isItemStackDamageable()) {
//                float scaled_damage = damage.getAmount() * 20.0F * 5.0F;
//                if (item instanceof net.minecraft.ItemArmor) {
//                    scaled_damage *= Item.plateIron.getMaxDamage(EnumQuality.average) / Item.swordIron.getMaxDamage(EnumQuality.average);
//                } else if (!(item instanceof net.minecraft.ItemTool)) {
//                    scaled_damage = damage.getAmount();
//                }
//                if (scaled_damage < 1.0F)
//                    scaled_damage = 1.0F;
//                result.startTrackingHealth(item_stack.getRemainingDurability());
//                ItemDamageResult idr = item_stack.tryDamageItem(this.worldObj, Math.round(scaled_damage), false);
//                result.finishTrackingHealth(item_stack.getRemainingDurability());
//                if (idr != null && idr.itemWasDestroyed()) {
//                    this.health = 0;
//                } else {
//                    this.health = 5 * item_stack.getItemDamage() / item_stack.getMaxDamage();
//                    if (this.health < 1)
//                        this.health = 1;
//                }
//            } else {
//                if (damage.isFireDamage() && item instanceof ItemFood item_food) {
//                    if (item_food.getCookedItem() != null || item_food.getUncookedItem() != null) {
//                        if (item_food.getCookedItem() != null) {
//                            int x = getBlockPosX();
//                            int xp_reward = getBlockPosY();
//                            int xp_share = getBlockPosZ();
//                            for (int dx = -1; dx <= 1; dx++) {
//                                for (int dz = -1; dz <= 1; dz++) {
//                                    Block block = this.worldObj.getBlock(x + dx, xp_reward, xp_share + dz);
//                                    if (block == Block.fire)
//                                        this.worldObj.getAsWorldServer().addScheduledBlockOperation(EnumBlockOperation.try_extinguish_by_items, x + dx, xp_reward, xp_share + dz, (this.worldObj.getTotalWorldTime() / 10L + 1L) * 10L, false);
//                                }
//                            }
//                        }
//                        this.cooking_progress += damage.getAmount() * 3.0F;
//                        if (this.cooking_progress >= 100.0F) {
//                            ItemStack cooked_item_stack = item.getItemProducedWhenDestroyed(item_stack, damage.getSource());
//                            if (cooked_item_stack == null) {
//                                setDead();
//                                return result.setEntityWasDestroyed();
//                            }
//                            if (item instanceof net.minecraft.ItemMeat)
//                                playSound("imported.random.sizzle", 1.0F, 1.0F);
//                            setEntityItemStack(cooked_item_stack);
//                            int xp_reward = cooked_item_stack.getExperienceReward();
//                            while (xp_reward > 0) {
//                                int xp_share = EntityXPOrb.getXPSplit(xp_reward);
//                                xp_reward -= xp_share;
//                                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5D, this.posZ + 0.5D, xp_share));
//                            }
//                        }
//                        return result.setEntityWasAffected();
//                    }
//                }
//                result.startTrackingHealth(this.health);
//                this.health = (int) (this.health - damage.getAmount());
//                result.finishTrackingHealth(this.health);
//            }
//            if (result.entityWasNegativelyAffected() && (damage.isPepsinDamage() || damage.isAcidDamage()))
//                if (this.health <= 0) {
//                    entityFX(damage.isAcidDamage() ? EnumEntityFX.smoke_and_steam_with_hiss : EnumEntityFX.steam_with_hiss);
//                } else {
//                    entityFX(EnumEntityFX.item_vanish);
//                }
//            if (this.health <= 0) {
//                if (damage.isFireDamage())
//                    entityFX(EnumEntityFX.smoke);
//                if (!getEntityItem().hasSignature() && getEntityItem().getItem().hasContainerItem()) {
//                    Item container = getEntityItem().getItem().getContainerItem();
//                    if (!container.isHarmedBy(damage.getSource())) {
//                        convertItem(container);
//                        return result;
//                    }
//                }
//                setDead();
//                if (item_stack.hasSignatureThatHasBeenAddedToWorld(this.worldObj))
//                    tryRemoveFromWorldUniques();
//                result.setEntityWasDestroyed();
//            }
//            return result;
//        }
//        return result;
//    }

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

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void spentTickInWater() {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        Item item = getEntityItem().getItem();
        if (item instanceof ItemVessel vessel) {
            if (vessel.contains(Material.lava)) {
                if (!this.worldObj.isRemote) {
                    entityFX(EnumEntityFX.steam_with_hiss);
                    convertItem(vessel.getPeerForContents(Material.stone));
                }
                return;
            }
            if (!this.worldObj.isRemote && !vessel.contains(Material.stone) && (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver)) {
                convertItem(vessel.getPeerForContents(Material.water));
            } else if (!this.worldObj.isRemote && !vessel.contains(Material.stone) && (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland)) {
                convertItem(vessel.getPeerForContents(Materials.dangerous_water));
            } else if (!this.worldObj.isRemote && !vessel.contains(Material.stone)) {
                convertItem(vessel.getPeerForContents(Materials.unsafe_water));
            }
        } else if (onServer() && item.hasMaterial(Material.water, true)) {
            if (!this.isDead)
                setDead();
        } else if (onServer() && item.isDissolvedByWater() && !this.isDead && this.ticksExisted % 20 == 0) {
            attackEntityFrom(new Damage(DamageSource.melt, 1.0F));
            if (this.isDead)
                entityFX(EnumEntityFX.item_vanish);
        }
        super.spentTickInWater();
    }

    @Inject(method = {"handleExplosion(Lnet/minecraft/Explosion;)Z"}, cancellable = true, at = {@At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityItem;calcExplosionForce(FD)F")})
    private void injectCancelExplosionCopy(CallbackInfoReturnable<Boolean> callback) {
        if (this.isExploded) {
            setDead();
            tryRemoveFromWorldUniques();
            callback.setReturnValue(Boolean.valueOf(true));
            callback.cancel();
        }
    }

    @Redirect(method = {"handleExplosion(Lnet/minecraft/Explosion;)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityItem;tryRemoveFromWorldUniques()V"))
    private void injectUpdateExploded(EntityItem caller) {
        this.isExploded = true;
        tryRemoveFromWorldUniques();
    }
}
