package net.oilcake.mitelros.mixins.entity.ai;

import net.minecraft.Entity;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.Packet;
import net.minecraft.Packet23VehicleSpawn;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {
    @Unique
    public Entity myEntity;
    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntity(CallbackInfoReturnable<Packet> cir) {
        if (this.myEntity instanceof EntityWandIceBall)
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 100));
        if (this.myEntity instanceof EntityWandFireball)
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 101));
        if (this.myEntity instanceof EntityWandShockWave)
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 102));
    }
}
