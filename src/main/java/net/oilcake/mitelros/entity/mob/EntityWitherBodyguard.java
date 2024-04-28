package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFSkeleton;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBodyguard extends EntitySkeleton {
    ItemStack stowed_item_stack;

    public EntityWitherBodyguard(World par1World) {
        super(par1World);
        setSkeletonType(1);
        setCanPickUpLoot(false);
        ((ITFSkeleton) this).setNumArrows(6);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.followRange, 64.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, 6.0D);
        setEntityAttribute(SharedMonsterAttributes.maxHealth, 12.0D);
    }

    public boolean isHoldingRangedWeapon() {
        return getHeldItem() instanceof net.minecraft.ItemBow;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.stowed_item_stack != null && (getHeldItemStack() == null || getTicksExistedWithOffset() % 10 == 0))
            if (getHeldItemStack() == null) {
                swapHeldItemStackWithStowed();
            } else {
                EntityLivingBase entityLivingBase = getTarget();
                if (entityLivingBase != null && canSeeTarget(true)) {
                    double distance = getDistanceToEntity(entityLivingBase);
                    if (isHoldingRangedWeapon()) {
                        if (distance < 5.0D)
                            swapHeldItemStackWithStowed();
                    } else if (distance > 6.0D && this.rand.nextBoolean()) {
                        swapHeldItemStackWithStowed();
                    }
                }
            }
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        int num_drops = this.rand.nextInt(3 + looting) - 1;
        if (num_drops > 0 && !recently_hit_by_player)
            num_drops -= this.rand.nextInt(num_drops + 1);
        for (int i = 0; i < num_drops; i++)
            dropItem(Item.coal.itemID, 1);
        if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + looting * 2)
            dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
    }

    public void addRandomWeapon() {
        List<RandomItemListEntry> items = new ArrayList();
        items.add(new RandomItemListEntry(Items.tungstenDagger, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenSword, 1));
            items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
        }
        if (this.rand.nextInt(8) == 0)
            this.stowed_item_stack = (new ItemStack(Items.bowTungsten)).randomizeForMob(this, true);
        RandomItemListEntry entry = (RandomItemListEntry) WeightedRandom.getRandomItem(this.rand, items);
        setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        addRandomWeapon();
        setBoots((new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, true));
        setLeggings((new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, true));
        setCuirass((new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, true));
        setHelmet((new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, true));
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof net.minecraft.EntityPlayer)
                target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.wither.id, 300));
            return result;
        }
        return result;
    }

    public void swapHeldItemStackWithStowed() {
        ItemStack item_stack = this.stowed_item_stack;
        this.stowed_item_stack = getHeldItemStack();
        setHeldItemStack(item_stack);
    }

    public boolean isHarmedByFire() {
        return false;
    }

    public boolean isHarmedByLava() {
        return false;
    }
}
