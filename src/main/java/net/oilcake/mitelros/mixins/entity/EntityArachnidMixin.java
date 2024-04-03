package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityArachnid;
import net.minecraft.EntityMob;
import net.minecraft.NBTTagCompound;
import net.minecraft.World;
import net.oilcake.mitelros.status.FrenziedManager;
import net.oilcake.mitelros.api.ITFSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityArachnid.class)
public class EntityArachnidMixin extends EntityMob implements ITFSpider {

    @Unique
    private FrenziedManager frenziedManager = new FrenziedManager();

    public EntityArachnidMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.frenziedManager.getCounter());
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.frenziedManager.getCounter());
    }

    @Inject(method = "onUpdate()V", at = @At("TAIL"))
    public void injectUpdate(CallbackInfo callbackInfo) {
        this.frenziedManager.update();
    }

    @Override
    public FrenziedManager getFrenziedManager() {
        return this.frenziedManager;
    }

    @Unique
    public void setFrenzied_counter(int counter) {
        this.frenziedManager.setCounter(counter);
    }

    @Override
    public boolean isFrenzied() {
        return (super.isFrenzied() || this.frenziedManager.getCounter() > 0);
    }
}
