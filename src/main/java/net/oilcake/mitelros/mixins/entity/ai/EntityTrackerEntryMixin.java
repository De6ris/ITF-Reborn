package net.oilcake.mitelros.mixins.entity.ai;

import net.minecraft.Entity;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.Packet;
import net.minecraft.Packet23VehicleSpawn;
import net.oilcake.mitelros.util.EntityTrackerMap;
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
        EntityTrackerMap.match(this.myEntity).ifPresent(x -> cir.setReturnValue(new Packet23VehicleSpawn(this.myEntity, x)));
    }
}
