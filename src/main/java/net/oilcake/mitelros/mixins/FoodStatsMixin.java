package net.oilcake.mitelros.mixins;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.Item;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.Packet;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.api.*;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.DamageSourceExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FoodStats.class})
public class FoodStatsMixin implements ITFFoodStats {
    private float water_for_nutrition_only;

    private int water;

    private float hungerWater;

    @Inject(method = {"<init>(Lnet/minecraft/EntityPlayer;)V"}, at = {@At("RETURN")})
    private void injectInit(CallbackInfo callbackInfo) {
        this.water = getWaterLimit();
    }

    @Inject(method = {"readNBT(Lnet/minecraft/NBTTagCompound;)V"}, at = {@At("RETURN")})
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        this.water_for_nutrition_only = par1NBTTagCompound.getFloat("water_for_nutrition_only");
        this.hungerWater = par1NBTTagCompound.getFloat("hungerWater");
        this.water = par1NBTTagCompound.getInteger("water");
    }

    @Inject(method = {"writeNBT(Lnet/minecraft/NBTTagCompound;)V"}, at = {@At("RETURN")})
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("water", this.water);
        par1NBTTagCompound.setFloat("hungerwater", this.hungerWater);
        par1NBTTagCompound.setFloat("water_for_nutrition_only", this.water_for_nutrition_only);
    }

    public int getWater() {
        return this.water;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addFoodValue(Item item) {
        addSatiation(item.getSatiation(this.player));
        addNutrition(item.getNutrition());
        addWater(((ITFItem) item).getWater());
        if (this.player instanceof ServerPlayer) {
            this.player.getAsEntityPlayerMP().addInsulinResistance(item.getInsulinResponse());
            this.player.getAsEntityPlayerMP().addNutrients(item);
        }
    }

    public void setSatiationWater(int water, boolean check_limit) {
        if (check_limit) {
            this.water = Math.min(water, getWaterValueLimit());
        } else {
            this.water = water;
        }
    }

    public int addWater(int water) {
        setSatiationWater(this.water + water, true);
        return this.water;
    }

    private final float global_water_rate = 1.0F;

    private static float getWaterPerTick() {
        return 0.002F;
    }

    private static float getWaterPerFoodUnit() {
        return 4.0F;
    }

    public void decreaseWater(float water) {
        if (!this.player.capabilities.isCreativeMode && !this.player.capabilities.disableDamage && !this.player.isGhost() && !this.player.isZevimrgvInTournament()) {
            water *= this.global_water_rate;
            this.hungerWater = Math.min(this.hungerWater + water, 40.0F);
            if (this.player.worldObj.isRemote && this.hungerWater > 0.2F) {
                (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue(new PacketDecreaseWater(this.hungerWater));
                this.hungerWater = 0.0F;
            }
        }
    }

    public void decreaseWaterClientSide(float water) {
        if (!this.player.worldObj.isRemote) {
            Minecraft.setErrorMessage("addHungerClientSide: cannot decrease Water to client if not remote");
        } else {
            decreaseWater(water);
        }
    }

    public void decreaseWaterServerSide(float hungerWater) {
        if (this.player.worldObj.isRemote) {
            Minecraft.setErrorMessage("addHungerServerSide: cannot decrease Water to server if remote");
        } else {
            decreaseWater(hungerWater);
        }
    }

    public int getWaterValueLimit() {
        return getWaterLimit();
    }

    public int getWaterLimit() {
        return Math.max(Math.min(6 + this.player.getExperienceLevel() / 5 * 2, 20), 6);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onUpdate(ServerPlayer par1EntityPlayer) {
        if (!par1EntityPlayer.isGhost() && !par1EntityPlayer.isZevimrgvInTournament() &&
                !par1EntityPlayer.isDead && par1EntityPlayer.getHealth() > 0.0F) {
            par1EntityPlayer.decrementNutrients();
            par1EntityPlayer.decrementInsulinResistance();
            decreaseWaterServerSide(getWaterPerTick());
            if (!par1EntityPlayer.inCreativeMode())
                this.water_for_nutrition_only += getWaterPerTick() * 0.3F;
            float hunger_factor = par1EntityPlayer.getWetnessAndMalnourishmentHungerMultiplier();
            addHungerServerSide(getHungerPerTick() * hunger_factor);
            if (!par1EntityPlayer.inCreativeMode())
                this.hunger_for_nutrition_only += getHungerPerTick() * 0.25F;
            if (this.hunger >= getHungerPerFoodUnit()) {
                this.hunger -= getHungerPerFoodUnit();
                if (this.satiation > 0 || this.nutrition > 0)
                    if (this.satiation < 1 || (this.hunger_for_nutrition_only + 0.001F >= getHungerPerFoodUnit() && this.nutrition > 0)) {
                        this.nutrition--;
                        this.hunger_for_nutrition_only = 0.0F;
                    } else {
                        this.satiation--;
                    }
            }
            if (this.satiation < 0)
                this.satiation = 0;
            if (this.water < 0)
                this.water = 0;
            if (par1EntityPlayer.inBed() && par1EntityPlayer.isOnHitList()) {
                par1EntityPlayer.addHungerServerSide(getHungerPerTick() * 20.0F);
                par1EntityPlayer.getAsPlayer().decreaseWaterServerSide(getWaterPerTick() * 20.0F);
            }
            if (this.player.isStarving()) {
                this.heal_progress = 0.0F;
                this.starve_progress += 0.002F;
                if (this.starve_progress >= 1.0F) {
                    if (par1EntityPlayer.getHealth() > 10.0F || this.player.worldObj.difficultySetting >= 3 || (par1EntityPlayer.getHealth() > 1.0F && this.player.worldObj.difficultySetting >= 2))
                        par1EntityPlayer.attackEntityFrom(new Damage(DamageSource.starve, 1.0F));
                    this.starve_progress--;
                    this.hunger_for_nutrition_only = 0.0F;
                }
            } else if (this.player.DuringDehydration()) {
                this.heal_progress = 0.0F;
                this.dehydration_progress += 0.002F;
                if (this.dehydration_progress >= 1.0F) {
                    par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.thirsty, 1.0F));
                    this.dehydration_progress--;
                    this.water_for_nutrition_only = 0.0F;
                }
            } else if (((ITFPlayer) par1EntityPlayer).isMalnourishedFin()) {
                this.heal_progress = 0.0F;
                this.malnourished_progress += 0.002F;
                if (this.malnourished_progress >= 1.0F) {
                    par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.malnourished, 1.0F));
                    this.malnourished_progress--;
                }
            } else {
                this.heal_progress += (4.0E-4F + this.nutrition * 2.0E-5F)
                        * (((ITFPlayer) par1EntityPlayer).isMalnourishedLv1() ? 0.25F : ((((ITFPlayer) par1EntityPlayer).isMalnourishedLv2() ? 0.0F : (((ITFPlayer) par1EntityPlayer).isMalnourishedLv3() ? 0.0F : 1.0F))))
                        * (par1EntityPlayer.inBed() ? 8.0F : 1.0F) * EnchantmentHelper.getRegenerationModifier(this.player);
                this.starve_progress = 0.0F;
                if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
                    if (this.heal_progress >= 1.0F) {
                        par1EntityPlayer.heal(1.0F);
                        addHungerServerSide(1.0F);
                        decreaseWaterServerSide(1.0F);
                        this.heal_progress--;
                    }
                } else {
                    this.heal_progress = 0.0F;
                }
            }
        }
    }

    @Shadow
    private float global_hunger_rate = 1.0F;

    @Shadow
    private int satiation;

    @Shadow
    private int nutrition;

    @Shadow
    private float hunger;

    @Shadow
    private float hunger_for_nutrition_only;

    @Shadow
    private float heal_progress;

    @Shadow
    private float starve_progress;

    private float dehydration_progress;

    private float malnourished_progress;

    @Shadow
    private EntityPlayer player;

    @Shadow
    public int addNutrition(int nutrition) {
        return this.nutrition;
    }

    @Shadow
    public int addSatiation(int satiation) {
        return this.satiation;
    }

    @Shadow
    public static float getHungerPerFoodUnit() {
        return 0.0F;
    }

    @Shadow
    public void addHungerServerSide(float hunger) {
    }

    @Shadow
    public static float getHungerPerTick() {
        return 0.0F;
    }

    @Shadow
    public int getNutritionLimit() {
        return 1;
    }
}
