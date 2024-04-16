package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.config.ITFConfig;

import java.util.ArrayList;
import java.util.List;

public class EntityPigmanLord extends EntityPigZombie {
  private int angerLevel;
  
  private int randomSoundDelay;
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.setEntityAttribute(SharedMonsterAttributes.maxHealth, ITFConfig.TagDemonDescend.get() ? 45.0D : 30.0D);
    this.setEntityAttribute(SharedMonsterAttributes.attackDamage, ITFConfig.TagDemonDescend.get() ? 11.25D : 9.0D);
  }
  
  public EntityPigmanLord(World par1World) {
    super(par1World);
  }
  
  protected EntityPlayer findPlayerToAttack(float max_distance) {
    Entity previous_target = getEntityToAttack();
    EntityPlayer target = super.findPlayerToAttack(max_distance);
    if (target != null && target != previous_target)
      becomeAngryAt((Entity)target); 
    return target;
  }
  
  private void becomeAngryAt(Entity par1Entity) {
    this.entityToAttack = par1Entity;
    this.angerLevel = 4000;
    this.randomSoundDelay = this.rand.nextInt(40);
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry((Item)Items.tungstenSword, 2));
    if (!Minecraft.isInTournamentMode()) {
      items.add(new RandomItemListEntry((Item)Items.tungstenBattleAxe, 1));
      items.add(new RandomItemListEntry((Item)Items.tungstenWarHammer, 1));
        if (ITFConfig.TagDemonDescend.get()){
          items.add(new RandomItemListEntry(Items.morningStarTungsten, 4));
        }
    }
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  public void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.tungstenBoots)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.tungstenLeggings)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.tungstenChestplate)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.tungstenHelmet)).randomizeForMob((EntityLiving)this, true));
  }
  
  public int getExperienceValue() {
    return super.getExperienceValue() * 6;
  }

  public int getMaxSpawnedInChunk() {
    return 1;
  }

  private int spawnCounter;
  private int spawnSums;
  private boolean gathering_troops = false;

  public void onUpdate() {
    super.onUpdate();
    if (!getWorld().isRemote) {
      if (this.getTarget() != null) {
        if (!this.isNoDespawnRequired() && this.getTarget() != null) {
          this.gathering_troops = true;
          this.func_110163_bv();
        }
      }
      if (spawnSums <= 4 && gathering_troops) {
        if (spawnCounter < 20) {
          if (ITFConfig.TagDemonDescend.get()) spawnCounter++;
        } else {
          EntityPigmanGuard Belongings = new EntityPigmanGuard(worldObj);
          Belongings.setPosition(posX, posY, posZ);
          Belongings.refreshDespawnCounter(-9600);
          worldObj.spawnEntityInWorld(Belongings);
          Belongings.onSpawnWithEgg(null);
          Belongings.entityFX(EnumEntityFX.summoned);
          spawnCounter = 0;
          spawnSums++;
        }
      }
    }
  }

}
