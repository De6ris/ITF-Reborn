package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.compat.CompatUtil;

public class EntityBoneBodyguard extends EntitySkeleton {
    public EntityBoneBodyguard(World par1World) {
        super(par1World);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int iteDay = CompatUtil.getITEDay(this.worldObj);
        setEntityAttribute(SharedMonsterAttributes.maxHealth, CompatUtil.getAttribute(iteDay, 6.0D));
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.29D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, CompatUtil.getAttribute(iteDay, 5.0D));
    }

    @Override
    public void addRandomWeapon() {
        setHeldItemStack((new ItemStack((getSkeletonType() == 2) ? ((this.rand.nextInt(20) == 0) ? Item.battleAxeRustedIron : Item.swordRustedIron) : Item.bow)).randomizeForMob(this, true));
    }

    @Override
    protected void addRandomEquipment() {
        addRandomWeapon();
        setBoots((new ItemStack(Item.bootsChainRustedIron)).randomizeForMob(this, true));
        setLeggings((new ItemStack(Item.legsChainRustedIron)).randomizeForMob(this, true));
        setCuirass((new ItemStack(Item.plateChainRustedIron)).randomizeForMob(this, true));
        setHelmet((new ItemStack(Item.helmetChainRustedIron)).randomizeForMob(this, true));
    }

    @Override
    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }
}
