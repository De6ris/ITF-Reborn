package net.oilcake.mitelros.mixins.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.api.ITFDamageResult;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFInventory;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.ItemTotem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.*;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.CurseExtend;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements ICommandSender, ITFPlayer {

    @Shadow
    public abstract void addExperience(int amount, boolean suppress_healing, boolean suppress_sound);

    @Shadow
    public abstract boolean hasFoodEnergy();

    @Shadow
    public abstract void triggerAchievement(StatBase par1StatBase);

    public NewPlayerManager newPlayerManager = new NewPlayerManager();

    @Override
    public NewPlayerManager getNewPlayerManager() {
        return newPlayerManager;
    }

    public DiarrheaManager diarrheaManager = new DiarrheaManager();

    public DiarrheaManager getDiarrheaManager() {
        return diarrheaManager;
    }

    @Shadow
    public EnumInsulinResistanceLevel insulin_resistance_level;

    @Shadow
    private int field_82249_h;

    @Shadow
    public PlayerCapabilities capabilities;

    private final HuntManager huntManager = new HuntManager();

    @Override
    public HuntManager getHuntManager() {
        return huntManager;
    }

    public float getBodyTemperature() {
        return BodyTemperature;
    }

    private int HeatResistance;

    private int FreezingCooldown;

    private int FreezingWarning;

    private int detectorDelay = 0;

    public void broadcast() {
        this.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("ITF-Reborn挂载成功,当前版本:").setColor(EnumChatFormatting.BLUE)
                .appendComponent(ChatMessageComponent.createFromText(ITFStart.Version).setColor(EnumChatFormatting.YELLOW))
                .appendComponent(ChatMessageComponent.createFromTranslationKey(",作者:Lee074,Huix,Kalsey,由Debris移植到FML3.2.1,现由Debris和Xy_Lose共同维护")));
        if (Constant.CalculateCurrentDiff() != 0) {
            this.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[MITE-ITF]")
                    .appendComponent(ChatMessageComponent.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff())
                            .setColor((Constant.CalculateCurrentDiff() >= 16) ? EnumChatFormatting.DARK_RED :
                                    ((Constant.CalculateCurrentDiff() >= 12) ? EnumChatFormatting.RED :
                                            ((Constant.CalculateCurrentDiff() >= 8) ? EnumChatFormatting.YELLOW :
                                                    ((Constant.CalculateCurrentDiff() >= 4) ? EnumChatFormatting.GREEN :
                                                            ((Constant.CalculateCurrentDiff() > 0) ? EnumChatFormatting.AQUA :
                                                                    EnumChatFormatting.BLUE)))))));
        }
    }

    @ModifyReturnValue(method = "getReach(Lnet/minecraft/Block;I)F", at = @At("RETURN"))
    private float addReach(float original) {
        return original + (this.isPotionActive(PotionExtend.stretch) ? 3.0f : 0.0f);// TODO
    }

    public int getCurrent_insulin_resistance_lvl() {
        if (this.insulin_resistance_level == null)
            return 0;
        return this.insulin_resistance_level.ordinal() + 1;
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void cancel(CallbackInfo ci) {
        if ((getHealth() > 5.0F && this.capabilities.getWalkSpeed() >= 0.05F && hasFoodEnergy()) || !Config.Realistic.get()) {
            return;
        }
        ci.cancel();
    }

    @Override
    public boolean isOnLadder() {
        if (Config.Realistic.get() && (getHealth() <= 5.0F || this.capabilities.getWalkSpeed() < 0.05F || !hasFoodEnergy())) {
            int x = MathHelper.floor_double(this.posX);
            int y = MathHelper.floor_double(this.boundingBox.minY);
            int z = MathHelper.floor_double(this.posZ);
            int var0 = this.worldObj.getBlockId(x, y, z);
            if (var0 == Block.ladder.blockID || var0 == Block.vine.blockID)
                return true;
            float yaw = this.rotationYaw % 360.0F;
            if (yaw < -45.0F)
                yaw += 360.0F;
            int towards = (int) ((yaw + 45.0F) % 360.0F) / 90;
            switch (towards) {
                case 0:
                    z++;
                    break;
                case 1:
                    x--;
                    break;
                case 2:
                    z--;
                    break;
                case 3:
                    x++;
                    break;
                default:
                    Minecraft.setErrorMessage("isOnLadder: Undefined Facing : " + towards + ".");
                    break;
            }
            Block block1 = this.worldObj.getBlock(x, y, z);
            Block block2 = this.worldObj.getBlock(x, y + 1, z);
            return ((this.fallDistance == 0.0F && block1 != null && block1.isSolid(0) && block2 == null) || (block2 != null && !block2.isSolid(0)));
        }
        return super.isOnLadder();
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;willDeliverCriticalStrike()Z"), cancellable = true)
    private void explosion(Entity target, CallbackInfo ci) {
        float damage = calcRawMeleeDamageVs(target, willDeliverCriticalStrike(), isSuspendedInLiquid());
        if (damage <= 0.0F) {
            ci.cancel();
            return;
        }
        if (this.isPotionActive(PotionExtend.stunning)) {
            return;
        }
        ItemStack heldItemStack = getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)) {
            int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
            target.worldObj.createExplosion(this, target.posX, target.posY, target.posZ, 0.0F, destorying * 0.5F, true);
        }
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Entity;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private void thresher(Entity target, CallbackInfo ci) {
        if (onServer() && target instanceof EntityLivingBase entity_living_base) {
            ItemStack[] item_stack_to_drop = entity_living_base.getWornItems();
            int rand = this.rand.nextInt(item_stack_to_drop.length);
            if (item_stack_to_drop[rand] != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentThresher, getHeldItemStack()) && entity_living_base instanceof EntityLiving entity_living) {
                entity_living.dropItemStack(item_stack_to_drop[rand], entity_living.height / 2.0F);
                entity_living.clearMatchingEquipmentSlot(item_stack_to_drop[rand]);
                entity_living.ticks_disarmed = 40;
            }
        }
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;heal(FLnet/minecraft/EnumEntityFX;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void sweep(Entity target, CallbackInfo ci, boolean critical, float damage, int knockback, boolean was_set_on_fire, int fire_aspect, EntityDamageResult result, boolean target_was_harmed, int stunning) {
        ItemStack heldItemStack = getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentSweeping)) {
            List<Entity> targets = getNearbyEntities(5.0F, 5.0F);
            attackMonsters(targets, damage * EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentSweeping, heldItemStack));
        }
    }

    @ModifyExpressionValue(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Ljava/lang/Math;random()D"))
    private double stun(double original) {
        return original * 0.5D;
    }

    @ModifyArg(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;addPotionEffect(Lnet/minecraft/PotionEffect;)V"))
    private PotionEffect stun(PotionEffect par1PotionEffect) {
        return new PotionEffect(PotionExtend.stunning.id, par1PotionEffect.getDuration(), par1PotionEffect.getAmplifier());
    }

    @ModifyConstant(method = "attackTargetEntityWithCurrentItem", constant = @org.spongepowered.asm.mixin.injection.Constant(floatValue = 18.0F))
    private float achievement(float constant) {
        return 40.0F;
    }

    @Inject(method = {"onDeath(Lnet/minecraft/DamageSource;)V"}, at = @At("TAIL"))
    public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
        if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
            ((ITFInventory) this.inventory).vanishingItems();
    }

    public int getWater() {
        return ((ITFFoodStats) getFoodStats()).getWater();
    }

    public int addWater(int water) {
        return ((ITFFoodStats) getFoodStats()).addWater(water);
    }

    public void decreaseWaterServerSide(float hungerWater) {
        if (!this.capabilities.isCreativeMode && !this.capabilities.disableDamage)
            ((ITFFoodStats) getFoodStats()).decreaseWaterServerSide(hungerWater);
    }

    public boolean DuringDehydration() {
        return (getWater() == 0);
    }

    @ModifyReturnValue(method = "hasFoodEnergy", at = @At("RETURN"))
    private boolean needWater(boolean original) {
        return original && this.getWater() != 0;
    }


    public boolean willRepair(ItemStack holding) {
        return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentMending);
    }

    private void activeNegativeUndying() {
        clearActivePotions();
        setHealth(getMaxHealth(), true, getHealFX());
        entityFX(EnumEntityFX.smoke_and_steam);
        makeSound("imported.random.totem_use", 3.0F, 1.0F + this.rand.nextFloat() * 0.1F);
        addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
        this.vision_dimming += 0.75F;
        triggerAchievement(AchievementExtend.cheatdeath);
    }

    protected void checkForAfterDamage(Damage damage, EntityDamageResult result) {
        if (result.entityWasDestroyed()) {
            ItemStack var5 = getHeldItemStack();
            if (var5 != null && var5.getItem() instanceof ItemTotem) {
                ((ITFDamageResult) result).setEntity_was_destroyed(false);
                ((ItemTotem) var5.getItem()).performNegativeEffect(this.getAsPlayer());
            }
            if (this.huntManager.hunt_counter > 0) {
                ((ITFDamageResult) result).setEntity_was_destroyed(false);
                setHealth(1.0F);
            }
        }
    }

    @Redirect(method = {"attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private EntityDamageResult redirectEntityAttack(EntityLivingBase caller, Damage damage) {
        EntityDamageResult entityDamageResult = super.attackEntityFrom(damage);
        if (entityDamageResult != null && getHealthFraction() <= 0.1D && !entityDamageResult.entityWasDestroyed()) {
            ItemStack var5 = getHeldItemStack();
            if (var5 != null && var5.getItem() instanceof ItemTotem) {
                ((ITFDamageResult) entityDamageResult).setEntity_was_destroyed(false);
                activeNegativeUndying();
                setHeldItemStack(null);
            }
        }
        return entityDamageResult;
    }

    public float BodyTemperature = 37.2F;

    @Shadow
    protected FoodStats foodStats;

    @Shadow
    public InventoryPlayer inventory;

    @Shadow
    public float vision_dimming;

    private MiscManager weightManager = new MiscManager(ReflectHelper.dyCast(this));

    public MiscManager getMiscManager() {
        return weightManager;
    }

    private WaterManager waterManager = new WaterManager();

    private TemperatureManager temperatureManager = new TemperatureManager(ReflectHelper.dyCast(this));

    public TemperatureManager getTemperatureManager() {
        return temperatureManager;
    }

    @Inject(method = {"onLivingUpdate()V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;onLivingUpdate()V", shift = At.Shift.AFTER)})
    private void injectTick(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
            //探测器：绿宝石
            if (this.inventory.getHotbarSlotContainItem(Items.detectorEmerald) > 0) {
                if (detectorDelay < 80) {
                    detectorDelay++;
                } else {
                    detectorDelay = 0;
                    List<Entity> targets = this.getNearbyEntities(16.0F, 8.0F);
                    float range_div = Math.min(2.0F, 20.0F / this.detectNearestMonstersDistance(targets));
                    if (range_div > 0.0F) {
                        this.makeSound("imported.random.warning", 0.3F, 1.0F + range_div);
                        detectorDelay += (int) (38.0F * range_div);
                    }
                }
            }
            //探测器：钻石
            if (this.inventory.getHotbarSlotContainItem(Items.detectorDiamond) > 0) {
                if (detectorDelay < 80) {
                    detectorDelay++;
                } else {
                    detectorDelay = 0;
                    List<Entity> targets = this.getNearbyEntities(28.0F, 12.0F);
                    float range_div = Math.min(2.0F, 40.0F / this.detectNearestMonstersDistance(targets));
                    if (range_div > 0.0F) {
                        this.makeSound("imported.random.warning", 0.3F, 1.0F + range_div);
                        detectorDelay += (int) (38.0F * range_div);
                    }
                }
            }
            this.diarrheaManager.update(ReflectHelper.dyCast(this));

            if (hasCurse(CurseExtend.fear_of_light)) {
                float light_modifier = (18 - this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.yOffset), MathHelper.floor_double(this.posZ))) / 15.0F;
                if (light_modifier >= 0.5F || hasCurse(CurseExtend.fear_of_light, true)) ;
            }
            if (hasCurse(CurseExtend.fear_of_darkness)) {
                float light_modifier = (this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.yOffset), MathHelper.floor_double(this.posZ)) + 3) / 15.0F;
                if (light_modifier >= 0.5F || hasCurse(CurseExtend.fear_of_darkness, true)) ;
            }
            this.huntManager.update(ReflectHelper.dyCast(this));
            if (Minecraft.inDevMode() && this.vision_dimming > 0.1F && isPlayerInCreative())
                this.vision_dimming = 0.05F;

            this.waterManager.update(ReflectHelper.dyCast(this));
            this.drunkManager.update1();
            this.temperatureManager.update();
            this.drunkManager.update2();

            if (getHealth() < 5.0F && Config.Realistic.get())
                this.vision_dimming = Math.max(this.vision_dimming, 1.0F - getHealthFraction());
        }

        this.feastManager.achievementCheck(ReflectHelper.dyCast(this));

        if (isPotionActive(Potion.moveSpeed) && isPotionActive(Potion.regeneration) && isPotionActive(Potion.fireResistance) && isPotionActive(Potion.nightVision) && isPotionActive(Potion.damageBoost) && isPotionActive(Potion.resistance) && isPotionActive(Potion.invisibility) && !this.feastManager.rewarded_disc_connected) {
            triggerAchievement(AchievementExtend.invincible);
            addExperience(2500);
            this.feastManager.rewarded_disc_connected = true;
            EntityItem RewardingRecord = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.recordConnected.itemID, 1));
            this.worldObj.spawnEntityInWorld(RewardingRecord);
            RewardingRecord.entityFX(EnumEntityFX.summoned);
        }
        if (this.getMiscManager().UnderArrogance()) {
            addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
        }
        ItemStack holding = getHeldItemStack();
        if (holding != null && willRepair(holding) &&
                holding.getRemainingDurability() / holding.getMaxDamage() < 0.5F && getExperienceLevel() >= 10 + 15 * holding.getItem().getHardestMetalMaterial().min_harvest_level) {
            this.addExperience(-holding.getMaxDamage() / 32, false, true);
            holding.setItemDamage(holding.getItemDamage() - holding.getMaxDamage() / 8);
        }
        ItemStack[] item_stack_to_repair = getWornItems();
        for (int n = 0; n < item_stack_to_repair.length; n++) {
            if (item_stack_to_repair[n] != null && willRepair(item_stack_to_repair[n]) &&
                    item_stack_to_repair[n].getRemainingDurability() / item_stack_to_repair[n].getMaxDamage() < 0.5F && getExperienceLevel() >= 10 + 15 * item_stack_to_repair[n].getItem().getHardestMetalMaterial().min_harvest_level) {
                addExperience(-50, false, true);
                item_stack_to_repair[n].setItemDamage(item_stack_to_repair[n].getItemDamage() - 1);
            }
        }
    }

    @Inject(method = "addExperience(IZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/FoodStats;setNutrition(IZ)V"))
    private void updateWater(int amount, boolean suppress_healing, boolean suppress_sound, CallbackInfo ci) {
        this.addWater(0);
    }

    @ModifyVariable(method = "addExperience(IZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int mending(int amount) {
        if (amount <= 0) {
            return amount;
        } else {
            int before = amount;
            ItemStack holding = this.getHeldItemStack();
            if (holding != null && this.willRepair(holding))
                for (; this.getHeldItemStack().getItemDamage() >= 4 && amount > 0; amount--)
                    this.getHeldItemStack().setItemDamage(holding.getItemDamage() - 4);
            this.addScore(before - amount);
            return amount;
        }
    }

    public FoodStats getFoodStats() {
        return this.foodStats;
    }

    @Unique
    private FeastManager feastManager = new FeastManager();

    @Unique
    public FeastManager getFeastManager() {
        return feastManager;
    }

    @Unique
    private DrunkManager drunkManager = new DrunkManager(ReflectHelper.dyCast(this));

    @Override
    public DrunkManager getDrunkManager() {
        return drunkManager;
    }

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
    }

//    // TODO Only for reversing the > to be <=, also I dont know why
//
//    @ModifyExpressionValue(method = "getMaxCraftingQuality", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;getCraftingExperienceCost(F)I"))
//    private int reverse_1(int original) {
//        return -original;
//    }
//
//    @ModifyExpressionValue(method = "getMaxCraftingQuality", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityPlayer;experience:I", opcode = Opcodes.GETFIELD))
//    private int reverse_2(int original) {
//        return -original;
//    }


    @Inject(method = "readEntityFromNBT", at = @At("HEAD"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        this.diarrheaManager.setDiarrheaCounter(par1NBTTagCompound.getInteger("diarrheaCounter"));
        this.huntManager.hunt_cap = par1NBTTagCompound.getBoolean("UsedTotemOfHunt");
        this.huntManager.hunt_counter = par1NBTTagCompound.getInteger("TotemDyingCounter");
        this.newPlayerManager.setNew(par1NBTTagCompound.getBoolean("isNewPlayer"));
        this.FreezingCooldown = par1NBTTagCompound.getInteger("FreezingCooldown");
        this.FreezingWarning = par1NBTTagCompound.getInteger("FreezingWarning");
        this.drunkManager.setDrunk_duration(par1NBTTagCompound.getInteger("DrunkDuration"));
        this.HeatResistance = par1NBTTagCompound.getInteger("HeatResistance");
    }

    @Inject(method = "writeEntityToNBT", at = @At("HEAD"))
    private void writeMine(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("diarrheaCounter", this.diarrheaManager.getDiarrheaCounter());
        par1NBTTagCompound.setBoolean("UsedTotemOfHunt", this.huntManager.hunt_cap);
        par1NBTTagCompound.setInteger("TotemDyingCounter", this.huntManager.hunt_counter);
        par1NBTTagCompound.setBoolean("isNewPlayer", this.newPlayerManager.getNew());
        par1NBTTagCompound.setInteger("FreezingCooldown", this.FreezingCooldown);
        par1NBTTagCompound.setInteger("FreezingWarning", this.FreezingWarning);
        par1NBTTagCompound.setInteger("DrunkDuration", this.drunkManager.getDrunk_duration());
        par1NBTTagCompound.setInteger("HeatResistance", this.HeatResistance);
    }

    @Inject(method = "checkForArmorAchievements", at = @At("HEAD"))
    private void itfArmorAchievements(CallbackInfo ci) { // TODO [Optional] add itf metal to wearAllPlateArmor check
        boolean wearing_full_suit_wolf_fur = true;
        for (int i = 0; i < 4; ++i) {
            if (this.inventory.armorInventory[i] != null && this.inventory.armorInventory[i].getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) this.inventory.armorInventory[i].getItem();
                Material material = armor.getArmorMaterial();
                if (material != Materials.wolf_fur) {
                    wearing_full_suit_wolf_fur = false;
                    break;
                }
            } else {
                wearing_full_suit_wolf_fur = false;
                break;
            }
        }
        if (wearing_full_suit_wolf_fur) {
            this.triggerAchievement(AchievementExtend.BravetheCold);
        }
    }

    public float getNickelArmorCoverage() {
        float coverage = 0.0F;
        ItemStack[] worn_items = getWornItems();
        for (int i = 0; i < worn_items.length; i++) {
            ItemStack item_stack = worn_items[i];
            if (item_stack != null)
                if (item_stack.isArmor()) {
                    ItemArmor barding = item_stack.getItem().getAsArmor();
                    if (barding.getArmorMaterial() == Materials.nickel)
                        coverage += barding.getCoverage() * barding.getDamageFactor(item_stack, this);
                } else if (item_stack.getItem() instanceof ItemHorseArmor var6) {
                    if (var6.getArmorMaterial() == Materials.nickel)
                        coverage += var6.getCoverage();
                }
        }
        return coverage;
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void inject(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {// TODO check validity
        float nickel_coverage = MathHelper.clamp_float(getNickelArmorCoverage(), 0.0F, 1.0F);
        if (damage.getResponsibleEntity() instanceof net.minecraft.EntityGelatinousCube) {
            System.out.println("nickel_coverage = " + nickel_coverage);
            if (nickel_coverage >= 0.999F)
                cir.setReturnValue(null);
            damage.scaleAmount(1.0F - nickel_coverage);
        }
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private void inject_1(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        if (Config.FinalChallenge.get())
            damage.scaleAmount(1.0F + Constant.CalculateCurrentDiff() / 50.0F);// TODO check validity
    }

    @ModifyReturnValue(method = "getHealthLimit()F", at = @At("RETURN"))
    private float itfHealth(float HealthLMTwithoutTag) {
        float HealthLMTwithTag;
        int level = this.getExperienceLevel();
        if (level <= 35) {
            HealthLMTwithTag = HealthLMTwithoutTag;
        } else {
            HealthLMTwithTag = Math.max(Math.min(14 + level / 10 * 2, 40), 20);
        }
        return Config.TagDistortion.get() ? HealthLMTwithTag : HealthLMTwithoutTag;
    }


    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;isInsideOfMaterial(Lnet/minecraft/Material;)Z"))
    private void inject(int x, int y, int z, boolean apply_held_item, CallbackInfoReturnable<Float> cir, @Local(ordinal = 0) LocalFloatRef str_vs_block) {// TODO unstable
        if (isPotionActive(PotionExtend.freeze)) {
            float newStr = str_vs_block.get() * (1.0F - (getActivePotionEffect(PotionExtend.freeze).getAmplifier() + 1) * 0.5F);
            str_vs_block.set(newStr);
        }
    }

    @ModifyArg(method = "getCurrentPlayerStrVsBlock", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), index = 0)
    private float inject(float str_vs_block) {
        if (Config.FinalChallenge.get())
            str_vs_block *= 1.0F - Constant.CalculateCurrentDiff() / 100.0F;
        if (Config.Realistic.get())
            str_vs_block *= Math.min((float) Math.pow(getHealth(), 2.0D) / 25.0F, 1.0F);
        return str_vs_block;
    }


    public void attackMonsters(List<Entity> targets, float damage) {
        for (int i = 0; i < targets.size(); i++) {
            EntityLivingBase entityMonster = (targets.get(i) instanceof EntityLivingBase) ? (EntityLivingBase) targets.get(i) : null;
            if (entityMonster != null && entityMonster.getDistanceSqToEntity(this) <= getReach(EnumEntityReachContext.FOR_MELEE_ATTACK, entityMonster) && entityMonster.canEntityBeSeenFrom(this.posX, getEyePosY(), this.posZ, 5.0D))
                entityMonster.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(getAsPlayer()), damage));
        }
    }

    public float detectNearestMonstersDistance(List<Entity> targets) {
        float distance = -1.0F;
        for (int i = 0; i < targets.size(); i++) {
            EntityMob entityMonster = targets.get(i) instanceof EntityMob ? (EntityMob) targets.get(i) : null;
            if (entityMonster != null) {
                if (distance < 0.0F) {
                    distance = (float) entityMonster.getDistanceSqToEntity(this);
                } else {
                    distance = Math.min(distance, (float) entityMonster.getDistanceSqToEntity(this));
                }
            }
        }
        return distance;
    }

    @Inject(method = "fall", at = @At("TAIL"))
    private void TagMovingV2(float par1, CallbackInfo ci) {
        if (Config.TagMovingV2.get())
            this.setSprinting(false);
    }

    @Shadow
    public ItemStack[] getWornItems() {
        return new ItemStack[0];
    }

    @Shadow
    public boolean willDeliverCriticalStrike() {
        return false;
    }

    @Shadow
    public float calcRawMeleeDamageVs(Entity target, boolean critical, boolean suspended_in_liquid) {
        return 0.0F;
    }

    @Shadow
    public abstract void addExperience(int paramInt);

    @Shadow
    public abstract float getReach(EnumEntityReachContext paramEnumEntityReachContext, Entity paramEntity);

    @Shadow
    public abstract double getEyePosY();

    @Shadow
    public void addScore(int par1) {
    }

    @Shadow
    public final int getExperienceLevel() {
        return 0;
    }

    @Shadow
    public boolean hasCurse(Curse curse) {
        return hasCurse(curse, false);
    }
}