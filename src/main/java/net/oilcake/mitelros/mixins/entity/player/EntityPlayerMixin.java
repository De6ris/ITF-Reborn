package net.oilcake.mitelros.mixins.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.beaconExtend.TileEntityUruBeacon;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.*;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements ICommandSender, ITFPlayer {
    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public InventoryPlayer inventory;

    @Shadow
    public abstract int getExperienceLevel();


    @Shadow
    public abstract boolean hasFoodEnergy();

    @Unique
    public NewPlayerManager newPlayerManager = new NewPlayerManager();

    @Override
    public NewPlayerManager getNewPlayerManager() {
        return newPlayerManager;
    }

    @Unique
    public DiarrheaManager diarrheaManager = new DiarrheaManager(ReflectHelper.dyCast(this));

    @Shadow
    public EnumInsulinResistanceLevel insulin_resistance_level;

    @Shadow
    public PlayerCapabilities capabilities;

    @Unique
    private final HuntManager huntManager = new HuntManager(ReflectHelper.dyCast(this));

    @Override
    public HuntManager getHuntManager() {
        return huntManager;
    }

    @ModifyReturnValue(method = "getReach(Lnet/minecraft/Block;I)F", at = @At("RETURN"))
    private float addReach(float original) {
        return original + (this.isPotionActive(PotionExtend.stretch) ? this.getActivePotionEffect(PotionExtend.stretch).getAmplifier() * 1.5F : 0.0F);
    }

    @ModifyReturnValue(method = "getReach(Lnet/minecraft/EnumEntityReachContext;Lnet/minecraft/Entity;)F", at = @At("RETURN"))
    private float addReachToEntity(float original) {
        return original + (this.isPotionActive(PotionExtend.stretch) ? this.getActivePotionEffect(PotionExtend.stretch).getAmplifier() * 1.5F : 0.0F);
    }

    public int getCurrent_insulin_resistance_lvl() {
        if (this.insulin_resistance_level == null)
            return 0;
        return this.insulin_resistance_level.ordinal() + 1;
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void cancel(CallbackInfo ci) {
        if ((getHealth() > 5.0F && this.capabilities.getWalkSpeed() >= 0.05F && hasFoodEnergy()) || !ITFConfig.Realistic.get()) {
            return;
        }
        ci.cancel();
    }

    @Override // TODO bad override
    public boolean isOnLadder() {
        if (ITFConfig.Realistic.get() && (getHealth() <= 5.0F || this.capabilities.getWalkSpeed() < 0.05F || !hasFoodEnergy())) {
            return this.miscManager.itfLadder();
        }
        return super.isOnLadder();
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;isSprinting()Z"), cancellable = true)
    private void explosion(Entity target, CallbackInfo ci) {
        this.enchantmentManager.destroy(target);
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Entity;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private void thresher(Entity target, CallbackInfo ci) {
        if (onServer() && target instanceof EntityLivingBase entity_living_base) {
            this.enchantmentManager.thresh(entity_living_base);
        }
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;heal(FLnet/minecraft/EnumEntityFX;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void sweep(Entity target, CallbackInfo ci, boolean critical, float damage, int knockback, boolean was_set_on_fire, int fire_aspect, EntityDamageResult result, boolean target_was_harmed, int stunning) {
        this.enchantmentManager.sweep(damage);
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

    @Inject(method = "onDeath(Lnet/minecraft/DamageSource;)V", at = @At("TAIL"))
    public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
        if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            EnchantmentManager.vanish(this.inventory);
        }
    }

    @Unique
    public int getWater() {
        return ((ITFFoodStats) this.foodStats).getWater();
    }

    @Unique
    public int addWater(int water) {
        return ((ITFFoodStats) this.foodStats).addWater(water);
    }

    @Unique
    public void decreaseWaterServerSide(float hungerWater) {
        if (!this.capabilities.isCreativeMode && !this.capabilities.disableDamage)
            ((ITFFoodStats) this.foodStats).decreaseWaterServerSide(hungerWater);
    }

    @ModifyReturnValue(method = "hasFoodEnergy", at = @At("RETURN"))
    private boolean needWater(boolean original) {
        return original && this.getWater() != 0;
    }

    @Redirect(method = "attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private EntityDamageResult redirectEntityAttack(EntityLivingBase caller, Damage damage) {
        EntityDamageResult entityDamageResult = super.attackEntityFrom(damage);
        return this.miscManager.destroyTotemCheck(entityDamageResult);
    }

    @Shadow
    protected FoodStats foodStats;

    @Shadow
    public float vision_dimming;

    @Unique
    private MiscManager miscManager = new MiscManager(ReflectHelper.dyCast(this));
    @Unique
    private EnchantmentManager enchantmentManager = new EnchantmentManager(ReflectHelper.dyCast(this));

    public MiscManager getMiscManager() {
        return miscManager;
    }

    private WaterManager waterManager = new WaterManager(ReflectHelper.dyCast(this));

    @Unique
    private TemperatureManager temperatureManager = new TemperatureManager(ReflectHelper.dyCast(this));

    public TemperatureManager getTemperatureManager() {
        return temperatureManager;
    }

    @Inject(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;onLivingUpdate()V", shift = At.Shift.AFTER))
    private void injectTick(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
            this.miscManager.detectorUpdate();
            this.diarrheaManager.update();
//            this.miscManager.curseUpdate(); // TODO this do nothing
            this.huntManager.update();
            if (Minecraft.inDevMode() && this.vision_dimming > 0.1F && isPlayerInCreative())
                this.vision_dimming = 0.05F;
            this.waterManager.update();
            this.drunkManager.update1();
            if (ITFConfig.TagTemperature.getBooleanValue()) {
                this.temperatureManager.update();
            }
            this.drunkManager.update2();
            if (getHealth() < 5.0F && ITFConfig.Realistic.get())
                this.vision_dimming = Math.max(this.vision_dimming, 1.0F - getHealthFraction());
        }
        this.feastManager.achievementCheck();
        this.enchantmentManager.arroganceUpdate();
        this.enchantmentManager.mendingUpdate();
        this.enchantmentManager.lightMendingUpdate();
    }

    @Inject(method = "addExperience(IZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/FoodStats;setNutrition(IZ)V"))
    private void updateWater(int amount, boolean suppress_healing, boolean suppress_sound, CallbackInfo ci) {
        this.addWater(0);
    }

    @ModifyVariable(method = "addExperience(IZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)// TODO need checking
    private int mending(int amount) {
        return this.enchantmentManager.onAddingEXP(amount);
    }

    @Unique
    private FeastManager feastManager = new FeastManager(ReflectHelper.dyCast(this));

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

    @Override
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
    }

    @Override
    public void displayGUIUruBeacon(TileEntityUruBeacon tileEntityUruBeacon) {
    }

    @Inject(method = "readEntityFromNBT", at = @At("HEAD"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        this.diarrheaManager.setDiarrheaCounter(par1NBTTagCompound.getInteger("diarrheaCounter"));
        this.huntManager.hunt_cap = par1NBTTagCompound.getBoolean("UsedTotemOfHunt");
        this.huntManager.hunt_counter = par1NBTTagCompound.getInteger("TotemDyingCounter");
        this.newPlayerManager.setNew(par1NBTTagCompound.getBoolean("isNewPlayer"));
        this.temperatureManager.freezingCoolDown = par1NBTTagCompound.getInteger("FreezingCooldown");
        this.temperatureManager.freezingWarning = par1NBTTagCompound.getInteger("FreezingWarning");
        this.temperatureManager.heatResistance = par1NBTTagCompound.getInteger("HeatResistance");
        this.temperatureManager.heatWarning = par1NBTTagCompound.getInteger("HeatWarning");
        this.temperatureManager.bodyTemperature = par1NBTTagCompound.getFloat("BodyTemperature");
        this.drunkManager.setDrunk_duration(par1NBTTagCompound.getInteger("DrunkDuration"));
    }

    @Inject(method = "writeEntityToNBT", at = @At("HEAD"))
    private void writeMine(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("diarrheaCounter", this.diarrheaManager.getDiarrheaCounter());
        par1NBTTagCompound.setBoolean("UsedTotemOfHunt", this.huntManager.hunt_cap);
        par1NBTTagCompound.setInteger("TotemDyingCounter", this.huntManager.hunt_counter);
        par1NBTTagCompound.setBoolean("isNewPlayer", this.newPlayerManager.getNew());
        par1NBTTagCompound.setInteger("FreezingCooldown", this.temperatureManager.freezingCoolDown);
        par1NBTTagCompound.setInteger("FreezingWarning", this.temperatureManager.freezingWarning);
        par1NBTTagCompound.setInteger("HeatResistance", this.temperatureManager.heatResistance);
        par1NBTTagCompound.setInteger("HeatWarning", this.temperatureManager.heatWarning);
        par1NBTTagCompound.setFloat("BodyTemperature", this.temperatureManager.bodyTemperature);
        par1NBTTagCompound.setInteger("DrunkDuration", this.drunkManager.getDrunk_duration());
    }

    @Inject(method = "checkForArmorAchievements", at = @At("HEAD"))
    private void itfArmorAchievements(CallbackInfo ci) {// TODO [Optional] add itf metal to wearAllPlateArmor check
        this.miscManager.wolfFurCheck();
        this.miscManager.iceChunkCheck();
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void nickelCheck(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        float nickel_coverage = MathHelper.clamp_float(this.miscManager.getNickelArmorCoverage(), 0.0F, 1.0F);
        if (damage.getResponsibleEntity() instanceof EntityGelatinousCube) {
            if (nickel_coverage >= 0.999F)
                cir.setReturnValue(null);
            damage.scaleAmount(1.0F - nickel_coverage);
        }
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private void inject_1(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        if (ITFConfig.FinalChallenge.get())
            damage.scaleAmount(1.0F + Constant.calculateCurrentDifficulty() / 50.0F);
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
        return ITFConfig.TagDistortion.get() ? HealthLMTwithTag : HealthLMTwithoutTag;
    }

    @ModifyArg(method = "getCurrentPlayerStrVsBlock", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), index = 0)
    private float itfModify(float str_vs_block) {
        return this.miscManager.calculateITFStv(str_vs_block);
    }

    @Inject(method = "fall", at = @At("TAIL"))
    private void TagMovingV2(float par1, CallbackInfo ci) {
        if (ITFConfig.TagMovingV2.get())
            this.setSprinting(false);
    }

    @Shadow
    public ItemStack[] getWornItems() {
        return new ItemStack[0];
    }
}