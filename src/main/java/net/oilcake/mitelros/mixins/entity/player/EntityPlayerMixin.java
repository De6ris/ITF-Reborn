package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.api.*;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.ItemTotem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.*;
import net.oilcake.mitelros.util.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements ICommandSender, ITFPlayer {

    @Shadow public abstract void triggerAchievement(StatBase par1StatBase);

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

    public int getCurrent_insulin_resistance_lvl() {
        if (this.insulin_resistance_level == null)
            return 0;
        return this.insulin_resistance_level.ordinal() + 1;
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void cancel(CallbackInfo ci) {
        if ((getHealth() > 5.0F && this.capabilities.getWalkSpeed() >= 0.05F && hasFoodEnergy()) || !ExperimentalConfig.TagConfig.Realistic.ConfigValue.booleanValue()) {
            return;
        }
        ci.cancel();
    }

    @Override
    public boolean isOnLadder() {
        if (ExperimentalConfig.TagConfig.Realistic.ConfigValue.booleanValue() && (getHealth() <= 5.0F || this.capabilities.getWalkSpeed() < 0.05F || !hasFoodEnergy())) {
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

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void attackTargetEntityWithCurrentItem(Entity target) {
        if (!isImmuneByGrace() &&
                target.canAttackWithItem()) {
            boolean critical = willDeliverCriticalStrike();
            float damage = calcRawMeleeDamageVs(target, critical, isSuspendedInLiquid());
            if (damage <= 0.0F)
                return;
            ItemStack heldItemStack = getHeldItemStack();
            if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)) {
                int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
                target.worldObj.createExplosion(this, target.posX, target.posY, target.posZ, 0.0F, destorying * 0.5F, true);
            }
            int knockback = 0;
            if (target instanceof EntityLivingBase)
                knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) target);
            if (isSprinting())
                knockback++;
            boolean was_set_on_fire = false;
            int fire_aspect = EnchantmentHelper.getFireAspectModifier(this);
            if (target instanceof EntityLivingBase && fire_aspect > 0 && !target.isBurning()) {
                was_set_on_fire = true;
                target.setFire(1);
            }
            if (onServer() && target instanceof EntityLivingBase entity_living_base) {
                ItemStack item_stack_to_drop = entity_living_base.getHeldItemStack();
                if (item_stack_to_drop != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.disarming, getHeldItemStack()) && entity_living_base instanceof EntityLiving entity_living) {
                    entity_living.dropItemStack(item_stack_to_drop, entity_living.height / 2.0F);
                    entity_living.clearMatchingEquipmentSlot(item_stack_to_drop);
                    entity_living.ticks_disarmed = 40;
                }
            }
            if (onServer() && target instanceof EntityLivingBase entity_living_base) {
                ItemStack[] item_stack_to_drop = entity_living_base.getWornItems();
                int rand = this.rand.nextInt(item_stack_to_drop.length);
                if (item_stack_to_drop[rand] != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentThresher, getHeldItemStack()) && entity_living_base instanceof EntityLiving entity_living) {
                    entity_living.dropItemStack(item_stack_to_drop[rand], entity_living.height / 2.0F);
                    entity_living.clearMatchingEquipmentSlot(item_stack_to_drop[rand]);
                    entity_living.ticks_disarmed = 40;
                }
            }
            EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(ReflectHelper.dyCast(this)).setFireAspect((fire_aspect > 0)), damage));
            boolean target_was_harmed = (result != null && result.entityWasNegativelyAffected());
            target.onMeleeAttacked(this, result);
            if (target_was_harmed) {
                if (target instanceof EntityLivingBase) {
                    int stunning = EnchantmentHelper.getStunModifier(this, (EntityLivingBase) target);
                    if (stunning > Math.random() * 10.0D)
                        ((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, stunning * 50, stunning * 5));
                    heal(EnchantmentHelper.getVampiricTransfer(this, (EntityLivingBase) target, damage), EnumEntityFX.vampiric_gain);
                    if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentSweeping)) {
                        List<Entity> targets = getNearbyEntities(5.0F, 5.0F);
                        attackMonsters(targets, damage * EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentSweeping, heldItemStack));
                    }
                }
                if (knockback > 0) {
                    target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F));
                    this.motionX *= 0.6D;
                    this.motionZ *= 0.6D;
                    setSprinting(false);
                }
                if (critical)
                    onCriticalHit(target);
                if (target instanceof EntityLivingBase && EnchantmentDamage.getDamageModifiers(getHeldItemStack(), (EntityLivingBase) target) > 0.0F)
                    onEnchantmentCritical(target);
                if (damage >= 40.0F)
                    triggerAchievement(AchievementList.overkill);
                setLastAttackTarget(target);
                if (target instanceof EntityLivingBase) {
                    if (this.worldObj.isRemote) {
                        System.out.println("EntityPlayer.attackTargetEntityWithCurrentItem() is calling EnchantmentThorns.func_92096_a() on client");
                        Minecraft.temp_debug = "player";
                    }
                    EnchantmentThorns.func_92096_a(this, (EntityLivingBase) target, this.rand);
                }
            }
            ItemStack held_item_stack = getHeldItemStack();
            Object var10 = target;
            if (target instanceof EntityDragonPart) {
                IEntityMultiPart var11 = ((EntityDragonPart) target).entityDragonObj;
                if (var11 != null && var11 instanceof EntityLivingBase)
                    var10 = var11;
            }
            if (target_was_harmed && held_item_stack != null && var10 instanceof EntityLivingBase)
                held_item_stack.hitEntity((EntityLivingBase) var10, ReflectHelper.dyCast(this));
            if (target instanceof EntityLivingBase) {
                addStat(StatList.damageDealtStat, Math.round(damage * 10.0F));
                if (fire_aspect > 0 && target_was_harmed) {
                    target.setFire(fire_aspect * 4);
                } else if (was_set_on_fire) {
                    target.extinguish();
                }
            }
            if (onServer())
                addHungerServerSide(0.3F * EnchantmentHelper.getEnduranceModifier(this));
        }
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

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean isStarving() {
        return (getNutrition() == 0);
    }

    public boolean DuringDehydration() {
        return (getWater() == 0);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean hasFoodEnergy() {
        return (getSatiation() + getNutrition() != 0 && getWater() != 0);
    }

    public boolean UnderArrogance() {
        boolean Hel_Arro = false;
        boolean Cst_Arro = false;
        boolean Lgs_Arro = false;
        boolean Bts_Arro = false;
        boolean Hnd_Arro = false;
        ItemStack Helmet = getHelmet();
        ItemStack Cuirass = getCuirass();
        ItemStack Leggings = getLeggings();
        ItemStack Boots = getBoots();
        ItemStack Holding = getHeldItemStack();
        if (Helmet != null)
            Hel_Arro = EnchantmentHelper.hasEnchantment(Helmet, Enchantments.enchantmentArrogance);
        if (Cuirass != null)
            Cst_Arro = EnchantmentHelper.hasEnchantment(Cuirass, Enchantments.enchantmentArrogance);
        if (Leggings != null)
            Lgs_Arro = EnchantmentHelper.hasEnchantment(Leggings, Enchantments.enchantmentArrogance);
        if (Boots != null)
            Bts_Arro = EnchantmentHelper.hasEnchantment(Boots, Enchantments.enchantmentArrogance);
        if (Holding != null)
            Hnd_Arro = EnchantmentHelper.hasEnchantment(Holding, Enchantments.enchantmentArrogance);
        boolean Arro = (Hel_Arro || Cst_Arro || Lgs_Arro || Bts_Arro || Hnd_Arro);
        return (this.experience < 2300 && Arro);
    }

    public boolean InFreeze() {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        ItemStack wearingItemStack = getCuirass();
        if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofNether))
            return false;
        if (biome.temperature <= ((((ITFWorld) this.worldObj).getWorldSeason() == 3) ? 1.0F : 0.16F) && (isOutdoors() || (this.worldObj.provider.dimensionId == -2 && StuckTagConfig.TagConfig.TagDeadgeothermy.ConfigValue.booleanValue()))) {
            return getHelmet() == null || (getHelmet()).itemID != Items.WolfHelmet.itemID ||
                    getCuirass() == null || (getCuirass()).itemID != Items.WolfChestplate.itemID ||
                    getLeggings() == null || (getLeggings()).itemID != Items.WolfLeggings.itemID ||
                    getBoots() == null || (getBoots()).itemID != Items.WolfBoots.itemID;
        }
        return false;
    }

    public boolean willRepair(ItemStack holding) {
        return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentMending);
    }

    public boolean InHeat() {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        return (biome.temperature >= 1.5F && StuckTagConfig.TagConfig.TagHeatStorm.ConfigValue.booleanValue());
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

    private double dry_resist;

    @Shadow
    protected FoodStats foodStats;

    @Shadow
    public int experience;

    @Shadow
    public InventoryPlayer inventory;

    @Shadow
    public float vision_dimming;

    private int water_duration = 0;

    private WeightManager weightManager = new WeightManager();

    @Inject(method = {"onLivingUpdate()V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;onLivingUpdate()V", shift = At.Shift.AFTER)})
    private void injectTick(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
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
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
            if (getBlockAtFeet() != null && (getBlockAtFeet()).blockMaterial == Material.water && isSneaking()) {
                this.water_duration++;
            } else {
                this.water_duration = 0;
            }
            if (this.water_duration > 160) {
                this.water_duration = 0;
                if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) {
                    ((ITFFoodStats) getFoodStats()).addWater(1);
                    addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                } else if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                    ((ITFFoodStats) getFoodStats()).addWater(2);
                } else {
                    ((ITFFoodStats) getFoodStats()).addWater(1);
                    addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, 160, 0));
                }
            }
            this.dry_resist += (StuckTagConfig.TagConfig.TagHeatStroke.ConfigValue.booleanValue() ? 2.0D : 1.0D) + biome.getFloatTemperature();
            if (isPotionActive(PotionExtend.dehydration))
                this.dry_resist += Math.min(80.0D, (getActivePotionEffect(PotionExtend.dehydration).getAmplifier() + 1) * 20.0D);
            if (isPotionActive(PotionExtend.thirsty))
                this.dry_resist += Math.min(80.0D, (getActivePotionEffect(PotionExtend.thirsty).getAmplifier() + 1) * 10.0D);
            if (this.dry_resist > 12800.0D) {
                ((ITFFoodStats) getFoodStats()).addWater(-1);
                this.dry_resist = 0.0D;
            }
            this.drunkManager.update();
            int freezeunit = Math.max(this.FreezingCooldown - 1500 * weightManager.getWeight(ReflectHelper.dyCast(this)), 0);
            this.BodyTemperature = 37.2F - 1.25E-4F * freezeunit;
            int freezelevel = Math.max(freezeunit / 12000, 0);
            if (freezeunit > 12000 && InFreeze() &&
                    freezelevel >= 1) {
                if (freezelevel >= 4) {
                    this.FreezingWarning++;
                    triggerAchievement(AchievementExtend.hypothermia);
                }
                if (this.FreezingWarning > 500) {
                    attackEntityFrom(new Damage(DamageSourceExtend.freeze, 4.0F));
                    this.FreezingWarning = 0;
                }
                addPotionEffect(new PotionEffect(PotionExtend.freeze.id, freezeunit, isInRain() ? freezelevel : (freezelevel - 1)));
            }
            if (this.HeatResistance > 3200 - weightManager.getWeight(ReflectHelper.dyCast(this)) * 50) {
                addPotionEffect(new PotionEffect(Potion.confusion.id, 1600, 1));
                this.HeatResistance = 0;
            }
            if (InHeat())
                this.HeatResistance++;
            if (InFreeze() || this.drunkManager.isDrunk()) {
                this.FreezingCooldown += StuckTagConfig.TagConfig.TagLegendFreeze.ConfigValue.booleanValue() ? 3 : 1;
                this.FreezingCooldown += (this.drunkManager.isDrunk()) ? (StuckTagConfig.TagConfig.TagLegendFreeze.ConfigValue.booleanValue() ? 3 : 1) : 0;
            } else if (this.FreezingCooldown > 0) {
                this.FreezingCooldown--;
            }
            this.drunkManager.decrease();
            if (getHealth() < 5.0F && ExperimentalConfig.TagConfig.Realistic.ConfigValue.booleanValue())
                this.vision_dimming = Math.max(this.vision_dimming, 1.0F - getHealthFraction());
        }
        if (this.feastManager.achievementCheck()) {
            triggerAchievement(AchievementExtend.feast);
            addExperience(2500);
            this.feastManager.rewarded_disc_damnation = true;
            EntityItem RewardingRecord = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.recordDamnation.itemID, 1));
            this.worldObj.spawnEntityInWorld(RewardingRecord);
            RewardingRecord.entityFX(EnumEntityFX.summoned);
        }
        if (isPotionActive(Potion.moveSpeed) && isPotionActive(Potion.regeneration) && isPotionActive(Potion.fireResistance) && isPotionActive(Potion.nightVision) && isPotionActive(Potion.damageBoost) && isPotionActive(Potion.resistance) && isPotionActive(Potion.invisibility) && !this.feastManager.rewarded_disc_connected) {
            triggerAchievement(AchievementExtend.invincible);
            addExperience(2500);
            this.feastManager.rewarded_disc_connected = true;
            EntityItem RewardingRecord = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.recordConnected.itemID, 1));
            this.worldObj.spawnEntityInWorld(RewardingRecord);
            RewardingRecord.entityFX(EnumEntityFX.summoned);
        }
        if (UnderArrogance())
            addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
        ItemStack holding = getHeldItemStack();
        if (holding != null && willRepair(holding) &&
                holding.getRemainingDurability() / holding.getMaxDamage() < 0.5F && getExperienceLevel() >= 10 + 15 * holding.getItem().getHardestMetalMaterial().min_harvest_level) {
            addExperience(-holding.getMaxDamage() / 32, false, true);
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

    public int getFreezingCooldown() {
        return this.FreezingCooldown;
    }

    public void addFreezingCooldown(int dummy) {
        if (this.FreezingCooldown + dummy < 0) {
            this.FreezingCooldown = 0;
        } else {
            this.FreezingCooldown += dummy;
        }
    }

    public float getCurrentBiomeTemperature() {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        return biome.getFloatTemperature();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addExperience(int amount, boolean suppress_healing, boolean suppress_sound) {
        suppress_healing = true;
        if (amount < 0) {
            if (!suppress_sound)
                this.worldObj.playSoundAtEntity(this, "imported.random.level_drain");
        } else if (amount > 0) {
            addScore(amount);
            if (!suppress_sound)
                this.worldObj.playSoundAtEntity(this, "random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
            ItemStack holding = getHeldItemStack();
            if (holding != null && willRepair(holding))
                for (; getHeldItemStack().getItemDamage() >= 4 && amount > 0; amount--)
                    getHeldItemStack().setItemDamage(holding.getItemDamage() - 4);
        }
        float health_limit_before = getHealthLimit();
        int level_before = getExperienceLevel();
        this.experience += amount;
        if (this.experience < getExperienceRequired(-40))
            this.experience = getExperienceRequired(-40);
        int level_after = getExperienceLevel();
        int level_change = level_after - level_before;
        if (level_change < 0) {
            setHealth(getHealth());
            this.foodStats.setSatiation(this.foodStats.getSatiation(), true);
            this.foodStats.setNutrition(this.foodStats.getNutrition(), true);
            addWater(0);
        } else if (level_change > 0) {
            if (getHealthLimit() > health_limit_before && this.field_82249_h < this.ticksExisted - 100.0F) {
                float volume = (level_after > 30) ? 1.0F : (level_after / 30.0F);
                if (!suppress_sound)
                    this.worldObj.playSoundAtEntity(this, "random.levelup", volume * 0.75F, 1.0F);
                this.field_82249_h = this.ticksExisted;
            }
            if (!suppress_healing)
                setHealth(getHealth() + getHealthLimit() - health_limit_before);
        }
        if (level_change != 0 && !this.worldObj.isRemote)
            MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).sendPlayerInfoToAllPlayers(true);
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
    private DrunkManager drunkManager = new DrunkManager();

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

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void checkForArmorAchievements() {
        boolean wearing_leather = false;
        boolean wearing_full_suit_plate = true;
        boolean wearing_full_suit_adamantium_plate = true;
        boolean wearing_full_suit_wolf_fur = true;
        for (int i = 0; i < 4; i++) {
            if (this.inventory.armorInventory[i] != null && this.inventory.armorInventory[i].getItem() instanceof ItemArmor armor) {
                Material material = armor.getArmorMaterial();
                if (material == Material.leather)
                    wearing_leather = true;
                if (material != Material.copper && material != Material.silver && material != Material.gold && material != Material.iron && material != Material.mithril && material != Material.adamantium && material != Material.ancient_metal && material != Materials.tungsten && material != Materials.nickel && material != Materials.ancient_metal_sacred && material != Materials.uru)
                    wearing_full_suit_plate = false;
                if (material != Material.adamantium)
                    wearing_full_suit_adamantium_plate = false;
                if (material != Materials.wolf_fur)
                    wearing_full_suit_wolf_fur = false;
            } else {
                wearing_full_suit_plate = false;
                wearing_full_suit_adamantium_plate = false;
                wearing_full_suit_wolf_fur = false;
            }
        }
        if (wearing_leather)
            triggerAchievement(AchievementList.wearLeather);
        if (wearing_full_suit_plate)
            triggerAchievement(AchievementList.wearAllPlateArmor);
        if (wearing_full_suit_adamantium_plate)
            triggerAchievement(AchievementList.wearAllAdamantiumPlateArmor);
        if (wearing_full_suit_wolf_fur)
            triggerAchievement(AchievementExtend.BravetheCold);
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

    /**
     * @author
     * @reason
     */
    @Overwrite
    public EntityDamageResult attackEntityFrom(Damage damage) {
        float nickel_coverage = MathHelper.clamp_float(getNickelArmorCoverage(), 0.0F, 1.0F);
        if (damage.getResponsibleEntity() instanceof net.minecraft.EntityGelatinousCube) {
            System.out.println("nickel_coverage = " + nickel_coverage);
            if (nickel_coverage >= 0.999F)
                return null;
            damage.scaleAmount(1.0F - nickel_coverage);
        }
        if (this.ticksExisted < 1000 && Damage.wasCausedByPlayer(damage) && isWithinTournamentSafeZone())
            return null;
        if (this.capabilities.disableDamage && !damage.canHarmInCreative())
            return null;
        if (inBed())
            wakeUpPlayer(true, damage.getResponsibleEntity());
        if (damage.isExplosion()) {
            if (damage.getResponsibleEntity() == this)
                return null;
            damage.scaleAmount(1.5F);
        }
        if (ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue.booleanValue())
            damage.scaleAmount(1.0F + Constant.CalculateCurrentDiff() / 50.0F);
        EntityDamageResult result = super.attackEntityFrom(damage);
        return result;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static int getHealthLimit(int level) {
        int HealthLMTwithTag = 0;
        int HealthLMTwithoutTag = Math.max(Math.min(6 + level / 5 * 2, 20), 6);
        if (level <= 35) {
            HealthLMTwithTag = HealthLMTwithoutTag;
        } else {
            HealthLMTwithTag = Math.max(Math.min(14 + level / 10 * 2, 40), 20);
        }
        return StuckTagConfig.TagConfig.TagDistortion.ConfigValue.booleanValue() ? HealthLMTwithTag : HealthLMTwithoutTag;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public float getCurrentPlayerStrVsBlock(int x, int y, int z, boolean apply_held_item) {
        float str_vs_block;
        Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
        if (block == null)
            return 0.0F;
        float block_hardness = this.worldObj.getBlockHardness(x, y, z);
        if (block_hardness == 0.0F)
            return 1.0F;
        float min_str_vs_block = -3.4028235E38F;
        Item held_item = getHeldItem();
        if (block.isPortable(this.worldObj, this, x, y, z)) {
            str_vs_block = min_str_vs_block = 4.0F * block_hardness;
        } else if (apply_held_item && held_item != null) {
            int metadata = this.worldObj.getBlockMetadata(x, y, z);
            str_vs_block = held_item.getStrVsBlock(block, metadata);
            if (str_vs_block < 1.0F)
                return getCurrentPlayerStrVsBlock(x, y, z, false);
            int var4 = EnchantmentHelper.getEfficiencyModifier(this);
            if (var4 > 0) {
                float var6 = (var4 * var4 + 1);
                str_vs_block += var6;
            }
        } else {
            int metadata = this.worldObj.getBlockMetadata(x, y, z);
            if (block.blockMaterial.requiresTool(block, metadata)) {
                str_vs_block = 0.0F;
            } else {
                str_vs_block = 1.0F;
            }
        }
        if (block == Block.web) {
            boolean decrease_strength = !apply_held_item || held_item == null || !held_item.isTool() || !held_item.getAsTool().isEffectiveAgainstBlock(block, 0);
            if (decrease_strength)
                str_vs_block *= 0.2F;
        }
        if (isPotionActive(Potion.digSpeed))
            str_vs_block *= 1.0F + (getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
        if (isPotionActive(Potion.digSlowdown))
            str_vs_block *= 1.0F - (getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
        if (isPotionActive(PotionExtend.freeze))
            str_vs_block *= 1.0F - (getActivePotionEffect(PotionExtend.freeze).getAmplifier() + 1) * 0.5F;
        if (isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this))
            str_vs_block /= 5.0F;
        if (!this.onGround)
            str_vs_block /= 5.0F;
        if (!hasFoodEnergy())
            str_vs_block /= 5.0F;
        str_vs_block *= 1.0F + getLevelModifier(EnumLevelBonus.HARVESTING);
        if (ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue.booleanValue())
            str_vs_block *= 1.0F - Constant.CalculateCurrentDiff() / 100.0F;
        if (ExperimentalConfig.TagConfig.Realistic.ConfigValue.booleanValue())
            str_vs_block *= Math.min((float) Math.pow(getHealth(), 2.0D) / 25.0F, 1.0F);
        return Math.max(str_vs_block, min_str_vs_block);
    }

    public void attackMonsters(List<Entity> targets, float damage) {
        for (int i = 0; i < targets.size(); i++) {
            EntityLivingBase entityMonster = (targets.get(i) instanceof EntityLivingBase) ? (EntityLivingBase) targets.get(i) : null;
            if (entityMonster != null && entityMonster.getDistanceSqToEntity(this) <= getReach(EnumEntityReachContext.FOR_MELEE_ATTACK, entityMonster) && entityMonster.canEntityBeSeenFrom(this.posX, getEyePosY(), this.posZ, 5.0D))
                entityMonster.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(getAsPlayer()), damage));
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected void fall(float par1) {
        if (!this.capabilities.allowFlying) {
            if (par1 >= 2.0F)
                addStat(StatList.distanceFallenStat, (int) Math.round(par1 * 100.0D));
            super.fall(par1);
        }
        if (ExperimentalConfig.TagConfig.TagMovingV2.ConfigValue.booleanValue())
            setSprinting(false);
    }

    @Shadow
    public float getLevelModifier(EnumLevelBonus kind) {
        return 0.0F;
    }

    @Shadow
    public int getNutrition() {
        return 1;
    }

    @Shadow
    public int getSatiation() {
        return 1;
    }

    @Shadow
    public ItemStack[] getWornItems() {
        return new ItemStack[0];
    }

    @Shadow
    public void setHeldItemStack(ItemStack itemStack) {
    }

    @Shadow
    public ItemStack getHeldItemStack() {
        return null;
    }

    @Shadow
    public ItemStack getCurrentItemOrArmor(int i) {
        return null;
    }

    @Shadow
    public void setCurrentItemOrArmor(int i, ItemStack itemStack) {
    }

    @Shadow
    public ItemStack[] getLastActiveItems() {
        return new ItemStack[0];
    }

    @Shadow
    public void wakeUpPlayer(boolean get_out_of_bed, Entity entity_to_look_at) {
    }

    @Shadow
    public void addStat(StatBase par1StatBase, int par2) {
    }

    @Shadow
    public boolean isImmuneByGrace() {
        return false;
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
    public void onCriticalHit(Entity par1Entity) {
    }

    @Shadow
    public void onEnchantmentCritical(Entity par1Entity) {
    }

    @Shadow
    public void addHungerServerSide(float hunger) {
    }

    @Shadow
    public abstract void addExperience(int paramInt);

    @Shadow
    protected abstract void entityInit();

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
    public float getHealthLimit() {
        return 0.0F;
    }

    @Shadow
    protected static final int getExperienceRequired(int level) {
        return 0;
    }

    @Shadow
    public boolean hasCurse(Curse curse) {
        return hasCurse(curse, false);
    }
}
