package net.oilcake.mitelros.entity;

import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntitySkeleton;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFSkeleton;
import net.oilcake.mitelros.item.Items;

public class EntityStray extends EntitySkeleton {

    private int spawnCounter;

    int num_arrows;


    public EntityStray(World par1World) {
        super(par1World);
        this.num_arrows = 4;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.maxHealth, 8.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.28999999165534973D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkeletonType", (byte) getSkeletonType());
        par1NBTTagCompound.setByte("num_arrows", (byte) this.num_arrows);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("SkeletonType")) {
            byte var2 = par1NBTTagCompound.getByte("SkeletonType");
            setSkeletonType(var2);
        }
        setCombatTask();
        this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
    }

    public void onLivingUpdate() {
        if (this.worldObj.isRemote && getSkeletonType() == 1)
            setSize(0.72F, 2.34F);
        if (this.frenzied_by_bone_lord_countdown > 0)
            setFrenziedByBoneLordCountdown(this.frenzied_by_bone_lord_countdown - 1);
        if (this.num_arrows == 0 && getHeldItemStack() != null &&
                getHeldItemStack().getItem() instanceof net.minecraft.ItemBow)
            setHeldItemStack(null);
        if (getHeldItemStack() == null && getSkeletonType() == 0) {
            setSkeletonType(2);
            setCombatTask();
        }
        if (!(getWorld()).isRemote) {
            this.spawnCounter++;
            if (this.spawnCounter > 300 &&
                    getHeldItemStack() != null) {
                if (getTarget() != null && (getHeldItemStack()).itemID == Items.FreezeWand.itemID)
                    getTarget().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 350, 0));
                this.spawnCounter = 0;
            }
        }
        super.onLivingUpdate();
    }

    public void addRandomWeapon() {
        if (getSkeletonType() == 2 && this.rand.nextInt(8) == 0) {
            setHeldItemStack(new ItemStack((Item) Items.FreezeWand));
            ((ITFSkeleton) this).setWizard(true);
        } else {
            setHeldItemStack((new ItemStack((getSkeletonType() == 2) ? ((this.rand.nextInt(20) == 0) ? Item.battleAxeRustedIron : Item.daggerRustedIron) : (Item) Item.bow)).randomizeForMob((EntityLiving) this, true));
        }
    }

    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, (EntityLivingBase) this, par1EntityLivingBase, 1.6F, (14 - this.worldObj.difficultySetting * 4), Item.arrowIron, false);
        int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItemStack());
        int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItemStack());
        double damage = (par2 * 3.0F) + this.rand.nextGaussian() * 0.25D + (this.worldObj.difficultySetting * 0.11F);
        var3.setDamage(damage);
        if (var4 > 0)
            var3.setDamage(var3.getDamage() + var4 * 0.5D + 0.5D);
        if (var5 > 0)
            var3.setKnockbackStrength(var5);
        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, getHeldItemStack()) > 0 || getSkeletonType() == 1 || (isBurning() && this.rand.nextInt(3) == 0))
            var3.setFire(100);
        playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld((Entity) var3);
        this.num_arrows--;
    }

    protected void addRandomEquipment() {
        addRandomWeapon();
    }

    public float getNaturalDefense(DamageSource damage_source) {
        return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 1.0F);
    }

    public boolean catchesFireInSunlight() {
        return false;
    }
}
