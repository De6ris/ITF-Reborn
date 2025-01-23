package net.oilcake.mitelros.mixins.entity.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEntityLivingBase;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.MiscManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity implements ITFEntityLivingBase {

    public EntityLivingBaseMixin(World par1World) {
        super(par1World);
    }

    @Unique
    private final EntityLivingBase instance = (EntityLivingBase) (Object) this;

    @Shadow
    public PotionEffect getActivePotionEffect(Potion par1Potion) {
        return null;
    }

    @ModifyReturnValue(method = "getSpeedBoostVsSlowDown", at = @At("RETURN"))
    private float itfEffects(float original) {
        if (original < 0) original /= (1.0F - this.getResistanceToParalysis());
        PotionEffect stunning_effect = this.getActivePotionEffect(PotionExtend.stunning);
        float stunning_amount = stunning_effect == null ? 0.0F : (float) (stunning_effect.getAmplifier() + 99) * -0.5F;
        double overall_speed_modifier = original + stunning_amount;
        if (overall_speed_modifier < 0.0D)
            overall_speed_modifier *= (1.0F - this.getResistanceToParalysis());
        return (float) overall_speed_modifier;
    }

    @Shadow
    public float getResistanceToParalysis() {
        return 0.0F;
    }

    @WrapWithCondition(method = "attackEntityFromHelper", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityDamageResult;setEntityWasDestroyed()Lnet/minecraft/EntityDamageResult;"))
    private boolean onDestroyed(EntityDamageResult instance) {
        if (this.instance instanceof EntityPlayer player) {
            if (MiscManager.getInstance(player).skipDeath()) return false;
        }
        return true;
    }

    @Inject(method = "onDeathUpdate", at = @At(value = "FIELD", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityLivingBase;deathTime:I", ordinal = 1))
    private void injectTick(CallbackInfo callbackInfo) {
        if (instance instanceof EntityCubic || instance instanceof EntityWight || instance instanceof EntityInvisibleStalker) {
            this.deathTime = 20;
        }
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (this.worldObj.isRemote) {
            this.glow--;
        }
    }

    @Shadow
    public int deathTime;

    @Unique
    private int glow;

    @Override
    public void itf$SetGlow(int duration) {
        if (this.worldObj.isRemote) {
            this.glow = duration;
        }
    }

    @Override
    public boolean itf$IsGlowing() {
        return this.glow > 0;
    }
}
