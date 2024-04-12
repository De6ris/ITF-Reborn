package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityArrow.class)
public abstract class EntityArrowMixin extends Entity {
    @Shadow
    public Entity shootingEntity;

    public EntityArrowMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    protected void entityInit() {
    }

    @Shadow
    public ItemStack getLauncher() {
        return null;
    }

    @Shadow
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
    }

    @Shadow
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
    }

    @Shadow
    private int knockbackStrength;

    @ModifyConstant(method = "onUpdate", constant = @org.spongepowered.asm.mixin.injection.Constant(floatValue = 0.25f, ordinal = 0))
    private float inject(float constant) {
        return 0.25f * (this.knockbackStrength + 1);
    }

    @ModifyVariable(method = "setThrowableHeading", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float itfSpeed(float velocity) {
        ItemStack launcher = getLauncher();
        if (launcher != null && launcher.getItem() == Items.bowTungsten && this.shootingEntity instanceof net.minecraft.EntityPlayer)
            velocity *= 1.35F;
        if (launcher != null && launcher.getItem() == Items.bowUru && this.shootingEntity instanceof net.minecraft.EntityPlayer)
            velocity *= 1.45F;
        return velocity;
    }

    @Redirect(method = "onUpdate()V", at = @At(ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/ItemArrow;getDamage()F"))
    public float SPSkeletonExtraDamage(ItemArrow itemArrow) {
        float dummy = 0.0F;
        if (Config.FinalChallenge.get())
            dummy += Constant.CalculateCurrentDiff() / 12.5F;
        if (this.shootingEntity.getClass() == EntityStray.class)
            dummy += 0.5F;
        if (this.shootingEntity.getClass() == EntityBoneBodyguard.class)
            dummy++;
        if (this.shootingEntity.getClass() == EntityWitherBodyguard.class)
            dummy += 1.5F;
        return itemArrow.getDamage() + dummy;
    }
}
