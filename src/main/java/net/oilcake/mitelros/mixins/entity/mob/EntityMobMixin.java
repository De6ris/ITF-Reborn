package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEntityMob;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityMob.class)
public abstract class EntityMobMixin extends EntityCreature implements ITFEntityMob {
    @Unique
    private boolean modified_attribute;

    public EntityMobMixin(World par1World) {
        super(par1World);
        this.modified_attribute = false;
    }

    @Override
    public boolean modified_attribute() {
        return this.modified_attribute;
    }

    @Override
    public void set_modified_attribute(boolean modified_attribute) {
        this.modified_attribute = modified_attribute;
    }

    @Inject(method = "attackEntityAsMob(Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At("HEAD"), cancellable = true)
    private void preventAttackingIfStunned(Entity target, CallbackInfoReturnable<EntityDamageResult> cir) {
        if (this.isPotionActive(PotionExtend.stunning)) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "attackEntityAsMob(Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityMob;isFrenzied()Z"))
    private void explosion(Entity target, CallbackInfoReturnable<EntityDamageResult> cir) {
        ItemStack held_item = this.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(held_item, Enchantments.enchantmentDestroying)) {
            int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
            target.worldObj.createExplosion(this, target.posX, target.posY, target.posZ, 0.0F, destorying * 0.5F, true);
        }
    }

    @ModifyArg(method = "attackEntityAsMob(Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;addPotionEffect(Lnet/minecraft/PotionEffect;)V"))
    private PotionEffect stun(PotionEffect par1PotionEffect) {
        int stun = par1PotionEffect.getAmplifier() / 5;
        return new PotionEffect(PotionExtend.stunning.id, stun * 60, 0);
    }

    @Inject(method = "attackEntityAsMob(Lnet/minecraft/EntityLiving;Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At("HEAD"), cancellable = true)
    private static void preventAttackingIfStunned(EntityLiving attacker, Entity target, CallbackInfoReturnable<EntityDamageResult> cir) {
        if (attacker.isPotionActive(PotionExtend.stunning)) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "attackEntityAsMob(Lnet/minecraft/EntityLiving;Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLiving;isFrenzied()Z"))
    private static void explosion_1(EntityLiving attacker, Entity target, CallbackInfoReturnable<EntityDamageResult> cir) {
        ItemStack held_item = attacker.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(held_item, Enchantments.enchantmentDestroying)) {
            int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
            ((ITFWorld) target.worldObj).itf$ExplosionC(attacker, target.posX, target.posY, target.posZ, 0.0F, destorying * 0.5F, true);
        }
    }

    @ModifyArg(method = "attackEntityAsMob(Lnet/minecraft/EntityLiving;Lnet/minecraft/Entity;)Lnet/minecraft/EntityDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;addPotionEffect(Lnet/minecraft/PotionEffect;)V"))
    private static PotionEffect stun_1(PotionEffect par1PotionEffect) {
        int stun = par1PotionEffect.getAmplifier() / 5;
        return new PotionEffect(PotionExtend.stunning.id, stun * 60, 0);
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityCreature;onUpdate()V", shift = At.Shift.AFTER))
    private void modifyAttributeForFinalChallenge(CallbackInfo ci) {
        if (!this.worldObj.isRemote && !this.modified_attribute && this.getHealth() > 0.0F && ITFConfig.FinalChallenge.getBooleanValue()) {
            int difficulty = Constant.calculateCurrentDifficulty();
            float healthMultiplier = 1.0F + (difficulty / 16.0F);
            if (healthMultiplier < 0) healthMultiplier = 0;
            this.setEntityAttribute(SharedMonsterAttributes.maxHealth, this.getMaxHealth() * healthMultiplier);
            ItemStack heldItemStack = this.getHeldItemStack();
            double attack_damage = getEntityAttributeValue(SharedMonsterAttributes.attackDamage);
            if (heldItemStack != null && heldItemStack.getItem() instanceof ItemTool itemTool) {
                attack_damage -= itemTool.getMaterialDamageVsEntity();
                attack_damage -= itemTool.getBaseDamageVsEntity();
            }
            float damageMultiplier = 1.0F + (difficulty / 32.0F);
            if (damageMultiplier < 0) damageMultiplier = 0;
            this.setEntityAttribute(SharedMonsterAttributes.attackDamage, attack_damage * damageMultiplier);
            this.setHealth(this.getMaxHealth());
            this.modified_attribute = true;
        }
    }
}
