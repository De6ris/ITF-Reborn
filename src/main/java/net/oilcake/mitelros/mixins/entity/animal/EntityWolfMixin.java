package net.oilcake.mitelros.mixins.entity.animal;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWolf.class)
public class EntityWolfMixin extends EntityTameable {
    @Inject(method = "<init>(Lnet/minecraft/World;)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySkeleton.class, 200, false));
    }

    @WrapOperation(method = "dropFewItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityWolf;dropItem(II)Lnet/minecraft/EntityItem;"))
    private EntityItem doNotDropLeather(EntityWolf instance, int id, int quantity, Operation<EntityItem> original) {
        return null;
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"))
    private void dropWolfFur(boolean recently_hit_by_player, DamageSource damage_source, CallbackInfo ci) {
        int looting = damage_source.getLootingModifier();
        for (int i = 0; i < 1 + this.rand.nextInt(2 + looting); i++) {
            dropItem(Items.wolf_fur.itemID, 1);
        }
    }

    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @Shadow
    protected int getTamingOutcome(EntityPlayer entityPlayer) {
        return 0;
    }
}
