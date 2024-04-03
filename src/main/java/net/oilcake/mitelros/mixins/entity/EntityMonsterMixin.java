package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityMob.class})
public class EntityMonsterMixin extends EntityCreature {
    private boolean modified_attribute;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static EntityDamageResult attackEntityAsMob(EntityLiving attacker, Entity target) {
        if (attacker.isDecoy())
            return null;
        if (target instanceof net.minecraft.EntityPlayer && target.getAsPlayer().isImmuneByGrace())
            return null;
        ItemStack held_item = attacker.getHeldItemStack();
        Damage damage = new Damage(DamageSource.causeMobDamage(attacker), (float) attacker.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
        if (attacker.isFrenzied())
            damage.addAmount((float) attacker.getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage) * 0.5F);
        if (EnchantmentHelper.hasEnchantment(held_item, Enchantments.enchantmentDestroying)) {
            int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
            ((ITFWorld) target.worldObj).newExplosionC(target, target.posX, target.posY, target.posZ, destorying, destorying);
        }
        int knockback_bonus = 0;
        if (target.isEntityLivingBase()) {
            damage.addAmount(EnchantmentDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
            knockback_bonus += EnchantmentHelper.getKnockbackModifier(attacker, target.getAsEntityLivingBase());
        }
        int fire_aspect = EnchantmentHelper.getFireAspectModifier(attacker);
        EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect((fire_aspect > 0)));
        if (result == null)
            return result;
        if (result.entityWasNegativelyAffected()) {
            if (knockback_bonus > 0) {
                target.addVelocity((-MathHelper.sin(attacker.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F), 0.1D, (MathHelper.cos(attacker.rotationYaw * 3.1415927F / 180.0F) * knockback_bonus * 0.5F));
                attacker.motionX *= 0.6D;
                attacker.motionZ *= 0.6D;
            }
            if (fire_aspect > 0)
                target.setFire(fire_aspect * 4);
            if (attacker.isBurning() && !attacker.hasHeldItem() && attacker.rand.nextFloat() < attacker.worldObj.difficultySetting * 0.3F)
                target.setFire(2 * attacker.worldObj.difficultySetting);
            if (target.isEntityLivingBase()) {
                if (attacker.worldObj.isRemote) {
                    System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
                    Minecraft.temp_debug = "mob";
                }
                EnchantmentThorns.func_92096_a((Entity) attacker, target.getAsEntityLivingBase(), attacker.rand);
                int stunning = EnchantmentHelper.getStunModifier(attacker, target.getAsEntityLivingBase());
                if (stunning > Math.random() * 10.0D)
                    target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, stunning * 50, stunning * 5));
                attacker.heal(EnchantmentHelper.getVampiricTransfer(attacker, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
            }
            if (target instanceof net.minecraft.EntityPlayer)
                attacker.refreshDespawnCounter(-9600);
        }
        return result;
    }

    public EntityMonsterMixin(World par1World) {
        super(par1World);
        this.modified_attribute = false;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && !this.modified_attribute && getHealth() > 0.0F && Config.FinalChallenge.get()) {
            setEntityAttribute(SharedMonsterAttributes.maxHealth, (getMaxHealth() * (1.0F + Constant.CalculateCurrentDiff() / 16.0F)));
            double attack_damage = getEntityAttributeValue(SharedMonsterAttributes.attackDamage);
            if (getHeldItemStack() != null && getHeldItemStack().getItem() instanceof net.minecraft.ItemTool) {
                attack_damage -= getHeldItemStack().getItemAsTool().getMaterialDamageVsEntity();
                attack_damage -= getHeldItemStack().getItemAsTool().getBaseDamageVsEntity();
            }
            setEntityAttribute(SharedMonsterAttributes.attackDamage, attack_damage * (1.0F + Constant.CalculateCurrentDiff() / 32.0F));
            setHealth(getMaxHealth());
            this.modified_attribute = true;
        }
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
            setDead();
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("modified_attribute", this.modified_attribute);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.modified_attribute = par1NBTTagCompound.getBoolean("modified_attribute");
    }
}
