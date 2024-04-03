package net.oilcake.mitelros.mixins.util;

import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.MathHelper;
import net.minecraft.PlayerCapabilities;
import net.minecraft.Potion;
import net.oilcake.mitelros.util.CurseExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({PlayerCapabilities.class})
public class PlayerAbilitiesMixin {
    @Shadow
    private float flySpeed = 0.05F;

    @Shadow
    private float walkSpeed = 0.1F;

    @Shadow
    public EntityPlayer player;

    @Overwrite
    public float getFlySpeed() {
        return (this.player.isSprinting() || (this.player.getPlayerController() != null && this.player.getPlayerController().isRunToggledOn(this.player) && this.player.inCreativeMode())) ? (this.flySpeed * 2.5F) : this.flySpeed;
    }

    @Overwrite
    public float getWalkSpeed() {
        float speed_boost_or_slow_down_factor = this.player.getSpeedBoostOrSlowDownFactor();
        float speed = this.player.hasFoodEnergy() ? this.walkSpeed : (this.walkSpeed * 0.25F);
        speed *= EnchantmentHelper.getSpeedModifier((EntityLivingBase) this.player);
        speed *= speed_boost_or_slow_down_factor;
        if (((Boolean) ExperimentalConfig.TagConfig.Realistic.ConfigValue).booleanValue()) {
            speed *= Math.min((float) Math.pow(this.player.getHealth(), 2.0D) / 25.0F, 1.0F);
            if (!this.player.isPotionActive(Potion.nightVision)) {
                float light_modifier = (this.player.worldObj.getBlockLightValue(MathHelper.floor_double(this.player.posX), MathHelper.floor_double(this.player.posY + this.player.yOffset), MathHelper.floor_double(this.player.posZ)) + 3) / 15.0F;
                speed *= Math.min(light_modifier, 1.0F);
            }
            if (speed <= 0.0F)
                speed = 0.0F;
            return speed;
        }
        if (this.player.hasCurse(CurseExtend.fear_of_light, false)) {
            float light_modifier = (21 - this.player.worldObj.getBlockLightValue(MathHelper.floor_double(this.player.posX), MathHelper.floor_double(this.player.posY + this.player.yOffset), MathHelper.floor_double(this.player.posZ))) / 15.0F;
            speed *= Math.min(light_modifier, 1.0F);
        }
        if (this.player.hasCurse(CurseExtend.fear_of_darkness, false) && !this.player.isPotionActive(Potion.nightVision)) {
            float light_modifier = (this.player.worldObj.getBlockLightValue(MathHelper.floor_double(this.player.posX), MathHelper.floor_double(this.player.posY + this.player.yOffset), MathHelper.floor_double(this.player.posZ)) + 3) / 15.0F;
            speed *= Math.min(light_modifier, 1.0F);
        }
        return speed;
    }
}
