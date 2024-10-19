package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.compat.CompatUtil;
import net.oilcake.mitelros.util.EntitySelectorHunter;

import java.util.ArrayList;
import java.util.List;

public class EntityUndeadGuard extends EntitySkeleton implements IRangedAttackMob {
    IEntitySelector hunterSelector = new EntitySelectorHunter();

    public EntityUndeadGuard(World par1World) {
        super(par1World);
        getNavigator().setAvoidsWater(true);
        this.tasks.clear();
        this.targetTasks.clear();
        setSkeletonType(2);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, this.hunterSelector));
        this.tasks.addTask(4, new EntityAIMoveToRepairItem(this, 1.0F, true));
        setCanPickUpLoot(false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27D);
        CompatUtil.setAttackDamage(this, 6.0D);
        CompatUtil.setMaxHealth(this, 12.0D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.ticksExisted > 300) {
            entityFX(EnumEntityFX.steam_with_hiss);
            setDead();
        }
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
    }

    @Override
    public int getRandomSkeletonType(World world) {
        return 2;
    }

    @Override
    public int getExperienceValue() {
        return 0;
    }

    @Override
    public void addRandomWeapon() {
        List<RandomItemListEntry> items = new ArrayList();
        items.add(new RandomItemListEntry(Item.daggerRustedIron, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
            items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
        }
        RandomItemListEntry entry = (RandomItemListEntry) WeightedRandom.getRandomItem(this.rand, items);
        setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    @Override
    protected void addRandomEquipment() {
        addRandomWeapon();
        setBoots((new ItemStack(Item.bootsChainRustedIron)).randomizeForMob(this, true));
        setLeggings((new ItemStack(Item.legsChainRustedIron)).randomizeForMob(this, true));
        setCuirass((new ItemStack(Item.plateChainRustedIron)).randomizeForMob(this, true));
        setHelmet((new ItemStack(Item.helmetChainRustedIron)).randomizeForMob(this, true));
    }
}
