package net.oilcake.mitelros.mixins.entity;

import java.util.Calendar;

import net.minecraft.Block;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityAIArrowAttack;
import net.minecraft.EntityAIAttackOnCollide;
import net.minecraft.EntityAIAvoidEntity;
import net.minecraft.EntityAIBase;
import net.minecraft.EntityAISeekFiringPosition;
import net.minecraft.EntityArrow;
import net.minecraft.EntityCreature;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.EntityWolf;
import net.minecraft.IRangedAttackMob;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFSkeleton;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntitySkeleton.class})
public abstract class EntitySkeletonMixin extends EntityMob implements IRangedAttackMob, ITFSkeleton {
    @Shadow
    private int frenzied_by_bone_lord_countdown;

    int num_arrows;

    @Shadow
    private EntityAIArrowAttack aiArrowAttack;

    @Shadow
    private EntityAIAttackOnCollide aiAttackOnCollide;

    @Shadow
    public int forced_skeleton_type;

    protected boolean Is_Wizard;

    public boolean getWizard() {
        return this.Is_Wizard;
    }

    public void setWizard(boolean is_Wizard) {
        this.Is_Wizard = is_Wizard;
    }

    public EntitySkeletonMixin(World par1World) {
        super(par1World);
        this.Is_Wizard = false;
    }

    @Inject(method = {"<init>(Lnet/minecraft/World;)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        this.num_arrows = this.rand.nextInt(3) + (isLongdead() ? 6 : 2) + (isLongdeadGuardian() ? 2 : 0);
        this.tasks.addTask(3, (EntityAIBase) new EntityAIAvoidEntity((EntityCreature) this, EntityWolf.class, 10.0F, 1.0D, 1.2D));
    }

    @Overwrite
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("SkeletonType")) {
            byte var2 = par1NBTTagCompound.getByte("SkeletonType");
            setSkeletonType(var2);
        }
        setCombatTask();
        this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
    }

    @Overwrite
    public void onLivingUpdate() {
        if (this.worldObj.isRemote && getSkeletonType() == 1)
            setSize(0.72F, 2.34F);
        if (this.frenzied_by_bone_lord_countdown > 0)
            setFrenziedByBoneLordCountdown(this.frenzied_by_bone_lord_countdown - 1);
        if (this.num_arrows == 0 && getHeldItemStack() != null && getHeldItemStack().getItem() instanceof net.minecraft.ItemBow)
            setHeldItemStack((ItemStack) null);
        if (getHeldItemStack() == null && getSkeletonType() == 0) {
            setSkeletonType(2);
            setCombatTask();
        }
        super.onLivingUpdate();
    }

    @Overwrite
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkeletonType", (byte) getSkeletonType());
        par1NBTTagCompound.setByte("num_arrows", (byte) this.num_arrows);
    }

    @Overwrite
    public void setCombatTask() {
        this.tasks.removeTask((EntityAIBase) this.aiAttackOnCollide);
        this.tasks.removeTask((EntityAIBase) this.aiArrowAttack);
        ItemStack var1 = getHeldItemStack();
        if (var1 != null && var1.getItem() instanceof net.minecraft.ItemBow && this.num_arrows > 0) {
            this.tasks.addTask(4, (EntityAIBase) this.aiArrowAttack);
            this.tasks.addTask(3, (EntityAIBase) new EntityAISeekFiringPosition((EntityLiving) this, 1.0F, true));
        } else {
            this.tasks.addTask(4, (EntityAIBase) this.aiAttackOnCollide);
        }
    }

    @Overwrite
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        int skeleton_type = (this.forced_skeleton_type >= 0) ? this.forced_skeleton_type : getRandomSkeletonType(this.worldObj);
        if (skeleton_type == 1) {
            if (isBoneLord()) {
                setCurrentItemOrArmor(1, (new ItemStack((Item) Items.tungstenBoots)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(2, (new ItemStack((Item) Items.tungstenLeggings)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(3, (new ItemStack((Item) Items.tungstenChestplate)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(4, (new ItemStack((Item) Items.tungstenHelmet)).randomizeForMob((EntityLiving) this, false));
                getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(8.0D);
            } else {
                setCurrentItemOrArmor(1, (new ItemStack((Item) Items.tungstenBootsChain)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(2, (new ItemStack((Item) Items.tungstenLeggingsChain)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(3, (new ItemStack((Item) Items.tungstenChestplateChain)).randomizeForMob((EntityLiving) this, false));
                setCurrentItemOrArmor(4, (new ItemStack((Item) Items.tungstenHelmetChain)).randomizeForMob((EntityLiving) this, false));
                getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(6.0D);
            }
            if (this.rand.nextInt(24) == 0) {
                this.Is_Wizard = true;
                setCurrentItemOrArmor(0, (new ItemStack((Item) Items.LavaWand)).randomizeForMob((EntityLiving) this, false));
                this.tasks.addTask(3, (EntityAIBase) new EntityAIAvoidEntity((EntityCreature) this, EntityPlayer.class, 9.0F, 1.0D, 1.0D));
                this.tasks.addTask(4, (EntityAIBase) this.aiArrowAttack);
            } else {
                setCurrentItemOrArmor(0, (new ItemStack((Item) Items.tungstenSword)).randomizeForMob((EntityLiving) this, false));
                this.tasks.addTask(4, (EntityAIBase) this.aiAttackOnCollide);
            }
            setSkeletonType(1);
        } else {
            if (skeleton_type == 2) {
                setSkeletonType(2);
                this.tasks.addTask(4, (EntityAIBase) this.aiAttackOnCollide);
            } else if (skeleton_type == 0) {
                this.tasks.addTask(4, (EntityAIBase) this.aiArrowAttack);
            } else {
                Minecraft.setErrorMessage("onSpawnWithEgg: Unrecognized skeleton type " + skeleton_type);
            }
            addRandomEquipment();
        }
        setCanPickUpLoot(true);
        if (getCurrentItemOrArmor(4) == null) {
            Calendar var2 = this.worldObj.getCurrentDate();
            if (var2.get(2) + 1 == 10 && var2.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
                setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Block.pumpkinLantern : Block.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }
        return par1EntityLivingData;
    }

    @Shadow
    public boolean isLongdead() {
        return false;
    }

    @Shadow
    public void setSkeletonType(int par1) {
    }

    @Shadow
    public int getRandomSkeletonType(World world) {
        return -1;
    }

    @Shadow
    protected void addRandomEquipment() {
    }

    @Overwrite
    public void addRandomWeapon() {
        if (getSkeletonType() == 2 && this.rand.nextInt(20) == 0) {
            int day_of_world = this.worldObj.getDayOfWorld();
            if (day_of_world >= 10) {
                setCurrentItemOrArmor(0, (new ItemStack((day_of_world >= 20 && !this.rand.nextBoolean()) ? Item.swordRustedIron : Item.daggerRustedIron)).randomizeForMob((EntityLiving) this, false));
                return;
            }
        }
        setCurrentItemOrArmor(0, (new ItemStack((getSkeletonType() == 2) ? Item.clubWood : (Item) Item.bow)).randomizeForMob((EntityLiving) this, true));
    }

    @Shadow
    public int getSkeletonType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    @Shadow
    public abstract boolean isLongdeadGuardian();

    @Shadow
    public boolean setFrenziedByBoneLordCountdown(int frenzied_by_bone_lord_countdown) {
        return false;
    }

    @Shadow
    public abstract void setHeldItemStack(ItemStack paramItemStack);

    @Shadow
    public abstract boolean isBoneLord();

    @Shadow
    public abstract boolean isAncientBoneLord();

    @Overwrite
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, (EntityLivingBase) this, par1EntityLivingBase, 1.6F, (14 - this.worldObj.difficultySetting * 4), isLongdead() ? Item.arrowAncientMetal : Item.arrowRustedIron, false);
        int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItemStack());
        int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItemStack());
        double damage = (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (this.worldObj.difficultySetting * 0.11F);
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

    @Overwrite
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        if (this.Is_Wizard) {
            int j = 1 + this.rand.nextInt(2);
            if (!recently_hit_by_player)
                j = 0;
            int k;
            for (k = 0; k < j; k++)
                dropItem(Item.blazePowder.itemID, 1);
            for (k = 0; k < j; k++)
                dropItem(Item.netherStalkSeeds.itemID, 1);
        }
        if (getSkeletonType() == 1) {
            int j = this.rand.nextInt(3 + looting) - 1;
            if (j > 0 && !recently_hit_by_player)
                j -= this.rand.nextInt(j + 1);
            for (int k = 0; k < j; k++)
                dropItem(Item.coal.itemID, 1);
            if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + looting * 2)
                dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
        } else if (getSkeletonType() != 2) {
            int j = Math.min(this.num_arrows, this.rand.nextInt(2 + looting));
            if (j > 0 && !recently_hit_by_player)
                j -= this.rand.nextInt(j + 1);
            if (isLongdead() && j > 0)
                j = (this.rand.nextInt(3) == 0) ? 1 : 0;
            for (int k = 0; k < j; k++)
                dropItem(isLongdead() ? Item.arrowAncientMetal.itemID : Item.arrowRustedIron.itemID, 1);
        }
        int num_drops = this.rand.nextInt(3);
        if (num_drops > 0 && !recently_hit_by_player)
            num_drops -= this.rand.nextInt(num_drops + 1);
        for (int i = 0; i < num_drops; i++)
            dropItem(Item.bone.itemID, 1);
    }

    public void setNum_arrows(int arrows) {
        this.num_arrows = arrows;
    }
}
