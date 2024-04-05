package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.Packet;
import net.minecraft.Packet23VehicleSpawn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityTrackerEntry.class})
public class EntityTrackerEntryMixin {
    @Unique
    public Entity myEntity;
    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntity(CallbackInfoReturnable<Packet> cir) {
        if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandIceBall)
            cir.setReturnValue((Packet) new Packet23VehicleSpawn(this.myEntity, 100));
        if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandFireball)
            cir.setReturnValue((Packet) new Packet23VehicleSpawn(this.myEntity, 101));
        if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandShockWave)
            cir.setReturnValue((Packet) new Packet23VehicleSpawn(this.myEntity, 102));
    }
}
