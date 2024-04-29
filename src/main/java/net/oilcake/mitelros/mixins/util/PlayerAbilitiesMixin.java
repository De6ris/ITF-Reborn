package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.EntityPlayer;
import net.minecraft.MathHelper;
import net.minecraft.PlayerCapabilities;
import net.minecraft.Potion;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.CurseExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerCapabilities.class)
public class PlayerAbilitiesMixin {

    @Shadow
    public EntityPlayer player;

    @ModifyConstant(method = "getWalkSpeed", constant = @Constant(floatValue = 0.75F))
    private float slowerIfHungry(float constant) {
        return 0.25F;
    }

    @ModifyReturnValue(method = "getWalkSpeed", at = @At("RETURN"))
    private float itfWalkFactors(float speed) {
        if (ITFConfig.Realistic.get()) {
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
