package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.compat.ITECompatUtil;

public class EntityLongdeadSentry extends EntityLongdead {
    public EntityLongdeadSentry(World world) {
        super(world);
        this.setSkeletonType(2);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));

    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        ITECompatUtil.setMaxHealth(this, 30.0);
        this.setEntityAttribute(SharedMonsterAttributes.followRange, 32.0);
    }
    @Override
    protected void addRandomEquipment() {
        this.setCurrentItemOrArmor(0, (new ItemStack(Item.warHammerAncientMetal, 1)));
        this.setCurrentItemOrArmor(1, (new ItemStack(Item.helmetGold, 1)));
        this.setCurrentItemOrArmor(2, (new ItemStack(Item.plateAncientMetal, 1)));
        this.setCurrentItemOrArmor(3, (new ItemStack(Item.legsAncientMetal, 1)));
        this.setCurrentItemOrArmor(4, (new ItemStack(Item.bootsAncientMetal, 1)));
        this.addPotionEffect(new PotionEffect(Potion.wither.id, 2147483647, 0));
    }
    @Override
    public int getExperienceValue() {
        return super.getExperienceValue() * 0;
    }
}

