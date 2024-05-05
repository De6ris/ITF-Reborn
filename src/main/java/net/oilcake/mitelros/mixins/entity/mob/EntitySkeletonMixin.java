package net.oilcake.mitelros.mixins.entity.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFSkeleton;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonMixin extends EntityMob implements IRangedAttackMob, ITFSkeleton {

    @Unique
    private int numArrows;

    @Shadow
    private EntityAIArrowAttack aiArrowAttack;

    @Shadow
    private EntityAIAttackOnCollide aiAttackOnCollide;
    @Unique
    protected boolean isWizard;

    public void setWizard(boolean isWizard) {
        this.isWizard = isWizard;
    }

    @Override
    public void setNumArrows(int arrowNum) {
        this.numArrows = arrowNum;
    }

    public EntitySkeletonMixin(World par1World) {
        super(par1World);
        this.isWizard = false;
    }

    @Inject(method = "<init>(Lnet/minecraft/World;)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.numArrows = this.rand.nextInt(3) + (isLongdead() ? 6 : 2) + (isLongdeadGuardian() ? 2 : 0);
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 10.0F, 1.0D, 1.2D));
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void readArrows(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        this.numArrows = par1NBTTagCompound.getByte("num_arrows");
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityMob;onLivingUpdate()V"))
    public void itfUpdate(CallbackInfo ci) {
        if (this.numArrows == 0 && getHeldItemStack() != null && getHeldItemStack().getItem() instanceof ItemBow)
            setHeldItemStack(null);
        if (getHeldItemStack() == null && getSkeletonType() == 0) {
            setSkeletonType(2);
            setCombatTask();
        }
    }

    @Inject(method = "attackEntityWithRangedAttack", at = @At("TAIL"))
    private void consumeArrow(EntityLivingBase par1EntityLivingBase, float par2, CallbackInfo ci) {
        this.numArrows--;
    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    public void writeArrows(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setByte("num_arrows", (byte) this.numArrows);
    }

    @WrapOperation(method = "setCombatTask", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntitySkeleton;getHeldItemStack()Lnet/minecraft/ItemStack;"))
    private ItemStack redirect(EntitySkeleton instance, Operation<ItemStack> original) {
        if (this.numArrows == 0) {
            return null;
        }
        return original.call(instance);
    }

    @WrapOperation(method = "onSpawnWithEgg", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityAITasks;addTask(ILnet/minecraft/EntityAIBase;)V", ordinal = 0))
    private void cancel(EntityAITasks instance, int par1, EntityAIBase par2EntityAIBase, Operation<Void> original) {
    }

    @WrapOperation(method = "onSpawnWithEgg", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntitySkeleton;setCurrentItemOrArmor(ILnet/minecraft/ItemStack;)V", ordinal = 0))
    private void cancel(EntitySkeleton instance, int par1, ItemStack par2ItemStack, Operation<Void> original) {
    }

    @WrapOperation(method = "onSpawnWithEgg", at = @At(value = "INVOKE", target = "Lnet/minecraft/AttributeInstance;setAttribute(D)V"))
    private void cancel(AttributeInstance instance, double v, Operation<Void> original) {
        if (isBoneLord()) {
            setCurrentItemOrArmor(1, (new ItemStack(Items.tungstenBoots)).randomizeForMob(this, false));
            setCurrentItemOrArmor(2, (new ItemStack(Items.tungstenLeggings)).randomizeForMob(this, false));
            setCurrentItemOrArmor(3, (new ItemStack(Items.tungstenChestplate)).randomizeForMob(this, false));
            setCurrentItemOrArmor(4, (new ItemStack(Items.tungstenHelmet)).randomizeForMob(this, false));
            instance.setAttribute(8.0D);
        } else {
            setCurrentItemOrArmor(1, (new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, false));
            setCurrentItemOrArmor(2, (new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, false));
            setCurrentItemOrArmor(3, (new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, false));
            setCurrentItemOrArmor(4, (new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, false));
            instance.setAttribute(6.0D);
        }
        if (this.rand.nextInt(24) == 0) {
            this.isWizard = true;
            setCurrentItemOrArmor(0, (new ItemStack(Items.lavaWand)).randomizeForMob(this, false));
            this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 9.0F, 1.0D, 1.0D));
            this.tasks.addTask(4, this.aiArrowAttack);
        } else {
            setCurrentItemOrArmor(0, (new ItemStack(Items.tungstenSword)).randomizeForMob(this, false));
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }

    @Shadow
    public boolean isLongdead() {
        return false;
    }

    @Shadow
    public void setSkeletonType(int par1) {
    }

    @Shadow
    public int getSkeletonType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    @Shadow
    public abstract boolean isLongdeadGuardian();

    @Shadow
    public abstract void setHeldItemStack(ItemStack paramItemStack);

    @Shadow
    public abstract boolean isBoneLord();

    @Shadow
    public abstract void setCombatTask();

    @Shadow
    protected abstract void addRandomEquipment();

    @Inject(method = "dropFewItems", at = @At("HEAD"))
    private void wizardDrop(boolean recently_hit_by_player, DamageSource damage_source, CallbackInfo ci) {
        if (this.isWizard) {
            int j = 1 + this.rand.nextInt(2);
            if (!recently_hit_by_player)
                j = 0;
            int k;
            for (k = 0; k < j; k++)
                dropItem(Item.blazePowder.itemID, 1);
            for (k = 0; k < j; k++)
                dropItem(Item.netherStalkSeeds.itemID, 1);
        }
    }

    @ModifyExpressionValue(method = "dropFewItems", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 3))
    private int limitArrowDrop(int original) {
        return Math.min(this.numArrows, original);
    }
}
