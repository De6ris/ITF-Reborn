package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.network.C2SDecreaseWater;
import net.oilcake.mitelros.util.DamageSourceExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public class FoodStatsMixin implements ITFFoodStats {
    @Unique
    private float water_for_nutrition_only;

    @Unique
    private int water;

    @Unique
    private float hungerWater;

    @Inject(method = "<init>(Lnet/minecraft/EntityPlayer;)V", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        this.water = itf$GetWaterLimit();
    }

    @Inject(method = "readNBT(Lnet/minecraft/NBTTagCompound;)V", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        this.water_for_nutrition_only = par1NBTTagCompound.getFloat("water_for_nutrition_only");
        this.hungerWater = par1NBTTagCompound.getFloat("hungerWater");
        this.water = par1NBTTagCompound.getInteger("water");
    }

    @Inject(method = "writeNBT(Lnet/minecraft/NBTTagCompound;)V", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("water", this.water);
        par1NBTTagCompound.setFloat("hungerwater", this.hungerWater);
        par1NBTTagCompound.setFloat("water_for_nutrition_only", this.water_for_nutrition_only);
    }

    public int itf$GetWater() {
        return this.water;
    }

    @Inject(method = "addFoodValue", at = @At("HEAD"))
    private void inject(Item item, CallbackInfo ci) {
        this.itf$AddWater(((ITFItem) item).itf$GetFoodWater());
        if (ITFConfig.TagTemperature.getBooleanValue()) {
            int temperature = ((ITFItem) item).itf$GetFoodTemperature();
            if (temperature > 0) {
                player.addPotionEffect(new PotionEffect(PotionExtend.warm.id, 6000, temperature - 1));
                if (player.itf$GetTemperatureManager().feelsCold()) {
                    player.itf$GetTemperatureManager().addBodyTemperature(temperature * 0.2D);
                }
            } else if (temperature < 0) {
                player.addPotionEffect(new PotionEffect(PotionExtend.cool.id, 6000, -temperature - 1));
                if (player.itf$GetTemperatureManager().feelsHot()) {
                    player.itf$GetTemperatureManager().addBodyTemperature(temperature * 0.2D);
                }
            }
        }
    }

    public void itf$SetSatiationWater(int water, boolean check_limit) {
        if (check_limit) {
            this.water = Math.min(water, itf$GetWaterLimit());
        } else {
            this.water = water;
        }
    }

    public int itf$AddWater(int water) {
        itf$SetSatiationWater(this.water + water, true);
        return this.water;
    }

    @Unique
    private final float global_water_rate = 1.0F;

    @Unique
    private static float getWaterPerTick() {
        return 0.002F;
    }

    public void itf$DecreaseWater(float water) {
        if (!this.player.capabilities.isCreativeMode && !this.player.capabilities.disableDamage && !this.player.isGhost() && !this.player.isZevimrgvInTournament()) {
            water *= this.global_water_rate;
            this.hungerWater = Math.min(this.hungerWater + water, 40.0F);
            if (this.player.worldObj.isRemote && this.hungerWater > 0.2F) {
                (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue(new C2SDecreaseWater(this.hungerWater));
                this.hungerWater = 0.0F;
            }
        }
    }

    public void itf$DecreaseWaterServerSide(float hungerWater) {
        if (this.player.worldObj.isRemote) {
            Minecraft.setErrorMessage("addHungerServerSide: cannot decrease Water to server if remote");
        } else {
            itf$DecreaseWater(hungerWater);
        }
    }

    public int itf$GetWaterLimit() {
        return Math.max(Math.min(6 + this.player.getExperienceLevel() / 5 * 2, 20), 6);
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/ServerPlayer;decrementInsulinResistance()V"))
    private void inject(ServerPlayer par1EntityPlayer, CallbackInfo ci) {
        this.itf$DecreaseWaterServerSide(getWaterPerTick());
        if (!par1EntityPlayer.inCreativeMode())
            this.water_for_nutrition_only += getWaterPerTick() * 0.3F;
        if (this.water < 0) {
            this.water = 0;
        }
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/ServerPlayer;heal(F)V"))
    private void inject_1(ServerPlayer par1EntityPlayer, CallbackInfo ci) {
        this.itf$DecreaseWaterServerSide(1.0F);
    }

    @Inject(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;isStarving()Z"), cancellable = true)
    private void newJudge(ServerPlayer par1EntityPlayer, CallbackInfo ci) {
        if (this.player.isStarving()) {
            this.heal_progress = 0.0F;
            this.starve_progress += 0.002F;
            if (this.starve_progress >= 1.0F) {
                if (par1EntityPlayer.getHealth() > 10.0F || this.player.worldObj.difficultySetting >= 3 || (par1EntityPlayer.getHealth() > 1.0F && this.player.worldObj.difficultySetting >= 2))
                    par1EntityPlayer.attackEntityFrom(new Damage(DamageSource.starve, 1.0F));
                this.starve_progress--;
                this.hunger_for_nutrition_only = 0.0F;
            }
        } else if (this.player.itf$GetWater() == 0) {
            this.heal_progress = 0.0F;
            this.dehydration_progress += 0.002F;
            if (this.dehydration_progress >= 1.0F) {
                par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.thirsty, 1.0F));
                this.dehydration_progress--;
                this.water_for_nutrition_only = 0.0F;
            }
        } else if (((ITFPlayer) par1EntityPlayer).itf$IsMalnourishedFinal()) {
            this.heal_progress = 0.0F;
            this.malnourished_progress += 0.002F;
            if (this.malnourished_progress >= 1.0F) {
                par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.malnourished, 1.0F));
                this.malnourished_progress--;
            }
        } else {
            int malnourishedLevel = ((ITFPlayer) par1EntityPlayer).itf$MalnourishedLevel();
            float factor = malnourishedLevel > 1 ? 0.0F : (malnourishedLevel == 1 ? 0.25F : 1.0F);
            this.heal_progress += (4.0E-4F + this.nutrition * 2.0E-5F)
                    * factor
                    * (par1EntityPlayer.inBed() ? 8.0F : 1.0F) * EnchantmentHelper.getRegenerationModifier(this.player);
            this.starve_progress = 0.0F;
            if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
                if (this.heal_progress >= 1.0F) {
                    par1EntityPlayer.heal(1.0F);
                    addHungerServerSide(1.0F);
                    itf$DecreaseWaterServerSide(1.0F);
                    this.heal_progress--;
                }
            } else {
                this.heal_progress = 0.0F;
            }
        }
        ci.cancel();
    }

    @Shadow
    private int nutrition;

    @Shadow
    private float hunger_for_nutrition_only;

    @Shadow
    private float heal_progress;

    @Shadow
    private float starve_progress;

    @Unique
    private float dehydration_progress;

    @Unique
    private float malnourished_progress;

    @Shadow
    private EntityPlayer player;

    @Shadow
    public void addHungerServerSide(float hunger) {
    }

}
