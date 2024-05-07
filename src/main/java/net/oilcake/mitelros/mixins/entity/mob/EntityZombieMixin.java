package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityZombie.class)
public class EntityZombieMixin extends EntityAnimalWatcher {
    @Shadow
    private boolean is_smart;

    @Shadow
    Item[] rare_drops_villager;

    public EntityZombieMixin(World world) {
        super(world);
    }

    @Inject(method = "<init>(Lnet/minecraft/World;)V", at = @At("RETURN"))
    public void addZombieLoot(CallbackInfo callbackInfo) {
        this.is_smart = true;
        Item[] original = this.rare_drops_villager;
        Item[] expanded = new Item[original.length + 1];
        System.arraycopy(original, 0, expanded, 0, original.length);
        expanded[original.length] = Items.seedsBeetroot;
        this.rare_drops_villager = expanded;
    }

    @Inject(method = "onUpdate()V", at = @At("RETURN"))
    public void ModifyAIInjector(CallbackInfo callbackInfo) {
        if (ITFConfig.TagWorshipDark.get())
            tryDisableNearbyLightSource();
    }

    @Inject(method = "onSpawnWithEgg(Lnet/minecraft/EntityLivingData;)Lnet/minecraft/EntityLivingData;", at = @At("RETURN"))
    public void ModifyAIInjector(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> callbackInfo) {
        if (ITFConfig.TagWorshipDark.get()) {
            this.tasks.addTask(4, new EntityAISeekLitTorch(this, 1.0F));
        }
    }
}
