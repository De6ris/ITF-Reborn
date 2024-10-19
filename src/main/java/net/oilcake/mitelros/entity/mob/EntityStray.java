package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFSkeleton;
import net.oilcake.mitelros.compat.CompatUtil;
import net.oilcake.mitelros.item.Items;

public class EntityStray extends EntitySkeleton {
    private int spawnCounter;

    public EntityStray(World par1World) {
        super(par1World);
        ((ITFSkeleton) this).setNumArrows(4);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        CompatUtil.setMaxHealth(this, 8.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.29D);
        CompatUtil.setAttackDamage(this, 5.0D);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!(getWorld()).isRemote) {
            this.spawnCounter++;
            if (this.spawnCounter > 300 &&
                    getHeldItemStack() != null) {
                if (getTarget() != null && (getHeldItemStack()).itemID == Items.freezeWand.itemID)
                    getTarget().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 350, 0));
                this.spawnCounter = 0;
            }
        }
    }

    @Override
    public void addRandomWeapon() {
        if (getSkeletonType() == 2 && this.rand.nextInt(8) == 0) {
            setHeldItemStack(new ItemStack(Items.freezeWand));
            ((ITFSkeleton) this).setWizard(true);
        } else {
            setHeldItemStack((new ItemStack((getSkeletonType() == 2) ? ((this.rand.nextInt(20) == 0) ? Item.battleAxeRustedIron : Item.daggerRustedIron) : Item.bow)).randomizeForMob(this, true));
        }
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        super.dropFewItems(recently_hit_by_player, damage_source);
        if (recently_hit_by_player && this.rand.nextInt(damage_source.getLootingModifier() + 4) > 2) {
            int num_drops = this.rand.nextInt(3);
            for (int i = 0; i < num_drops; ++i) {
                this.dropItem(Items.frostRod, 1);
            }
        }
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
