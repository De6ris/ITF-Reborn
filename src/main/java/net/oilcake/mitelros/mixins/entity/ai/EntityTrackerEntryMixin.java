package net.oilcake.mitelros.mixins.entity.ai;

import net.minecraft.Entity;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.Packet;
import net.minecraft.Packet23VehicleSpawn;
import net.oilcake.mitelros.entity.misc.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Shadow
    public Entity myEntity;
    @Inject(method = "getPacketForThisEntity", at = @At("HEAD"), cancellable = true)
    private void getPacketForThisEntityITF(CallbackInfoReturnable<Packet> cir) {
        if (this.myEntity instanceof EntityWandIceBall) {
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 100));
        }
        if (this.myEntity instanceof EntityWandFireBall) {
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 101));
        }
        if (this.myEntity instanceof EntityWandShockWave) {
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 102));
        }
        if (this.myEntity instanceof EntityWandSlimeBall) {
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 103));
        }
        if (this.myEntity instanceof EntityWandPearl) {
            cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, 104));
        }
    }
}
