package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityPigmanGuard extends EntityPigZombie {
    private int angerLevel;
    private int randomSoundDelay;

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 8.0);
    }

    public EntityPigmanGuard(World par1World) {
        super(par1World);
    }
    protected EntityPlayer findPlayerToAttack(float max_distance) {
        Entity previous_target = this.getEntityToAttack();
        EntityPlayer target = super.findPlayerToAttack(max_distance);
        if (target != null && target != previous_target) {
            this.becomeAngryAt(target);
        }

        return target;
    }
    private void becomeAngryAt(Entity par1Entity) {
        this.entityToAttack = par1Entity;
        this.angerLevel = 4000;
        this.randomSoundDelay = this.rand.nextInt(40);
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Items.tungstenSword, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
            items.add(new RandomItemListEntry(Items.tungstenWarHammer, 1));
        }
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    public void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, true));
    }

    public int getExperienceValue() {
        return (int) (super.getExperienceValue() * 1.5F);
    }

    public int getMaxSpawnedInChunk() {
        return 2;
    }

}