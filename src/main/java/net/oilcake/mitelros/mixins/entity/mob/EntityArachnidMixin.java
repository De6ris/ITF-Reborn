package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityArachnid;
import net.minecraft.EntityMob;
import net.minecraft.NBTTagCompound;
import net.minecraft.World;
import net.oilcake.mitelros.api.BadOverride;
import net.oilcake.mitelros.api.ITFSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityArachnid.class)
public abstract class EntityArachnidMixin extends EntityMob implements ITFSpider {
    @Unique
    private int counter;

    public EntityArachnidMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.counter);
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.counter);
    }

    @Inject(method = "onUpdate()V", at = @At("TAIL"))
    public void injectUpdate(CallbackInfo callbackInfo) {
        if (this.counter > 0)
            this.counter--;
    }

    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @BadOverride
    @Override
    public boolean isFrenzied() {
        return (super.isFrenzied() || this.counter > 0);
    }
}