package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.util.DamageSourceExtend;
import net.xiaoyu233.fml.reload.transform.util.EntityLivestockAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityLivestock.class})
public abstract class EntityLivestockMixin extends EntityAnimal implements EntityLivestockAccessor {
    private int illnessToDeathCounter;

    public EntityLivestockMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    private boolean isWell() {
        return false;
    }

    @Inject(method = {"onLivingUpdate()V"}, at = @At(value = "HEAD", shift = At.Shift.AFTER))
    private void injectIllnessToDeath(CallbackInfo c) {
        if (!Config.Realistic.get()) return;
        if (!this.worldObj.isRemote) {
            if (!isWell()) {
                this.illnessToDeathCounter++;
                if (this.illnessToDeathCounter >= 12000) {
                    attackEntityFrom(new Damage(DamageSourceExtend.malnourished, 1.0F));
                    this.illnessToDeathCounter = 0;
                }
            } else if (this.illnessToDeathCounter > 0) {
                this.illnessToDeathCounter--;
            }
        }
    }

    @Inject(method = {"readEntityFromNBT"}, at = {@At("RETURN")})
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        this.illnessToDeathCounter = par1NBTTagCompound.getInteger("illnessToDeathCounter");
    }

    @Inject(method = {"writeEntityToNBT"}, at = {@At("RETURN")})
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("illnessToDeathCounter", this.illnessToDeathCounter);
    }
}
