package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.compat.ITECompatUtil;

import java.util.ArrayList;
import java.util.List;

public class EntityEvil extends EntityGhost {
    public EntityEvil(World par1World) {
        super(par1World);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        ITECompatUtil.setMaxHealth(this, 30.0D);
        ITECompatUtil.setAttackDamage(this, 7.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.3D);
    }

    protected float getSoundVolume(String sound) {
        return 1.25F;
    }

    public void addRandomWeapon() {
        List<RandomItemListEntry> items = new ArrayList();
        items.add(new RandomItemListEntry(Item.swordGold, 1));
        if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode())
            items.add(new RandomItemListEntry(Item.battleAxeGold, 3));
        RandomItemListEntry entry = (RandomItemListEntry) WeightedRandom.getRandomItem(this.rand, items);
        setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving) this, true));
    }

    protected void addRandomEquipment() {
        addRandomWeapon();
        setCuirass((new ItemStack((Item) Item.plateGold)).randomizeForMob((EntityLiving) this, true));
        setHelmet((new ItemStack((Item) Item.helmetGold)).randomizeForMob((EntityLiving) this, true));
    }
}
