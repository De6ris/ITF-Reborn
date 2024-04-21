package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityUnknown extends EntityZombie {
    public EntityUnknown(World par1World) {
        super(par1World);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(999.0D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!(getWorld()).isRemote)
            if (getTarget() != null &&
                    !isNoDespawnRequired() && getTarget() != null)
                setDead();
    }

    public void addRandomEquipment() {
        setBoots((new ItemStack((Item) Items.bootsCustom_a)).randomizeForMob((EntityLiving) this, true));
        setLeggings((new ItemStack((Item) Items.leggingsCustom_a)).randomizeForMob((EntityLiving) this, true));
        setCuirass((new ItemStack((Item) Items.chestplateCustom_a)).randomizeForMob((EntityLiving) this, true));
        setHelmet((new ItemStack((Item) Items.helmetCustom_a)).randomizeForMob((EntityLiving) this, true));
    }
}
