package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.EntityTrackerRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.EntityTrackerHandler;
import net.minecraft.Entity;
import net.minecraft.WorldClient;
import net.oilcake.mitelros.entity.misc.*;

import java.util.function.Consumer;

public class EntityTrackerRegistry implements Consumer<EntityTrackerRegisterEvent> {
    @Override
    public void accept(EntityTrackerRegisterEvent event) {
        event.registerEntityTracker(entity -> entity instanceof EntityWandIceBall, 64, 10, true);
        event.registerEntityTracker(entity -> entity instanceof EntityWandFireBall, 64, 10, true);
        event.registerEntityTracker(entity -> entity instanceof EntityWandShockWave, 64, 10, true);
        event.registerEntityTracker(entity -> entity instanceof EntityWandSlimeBall, 64, 10, true);
        event.registerEntityTracker(entity -> entity instanceof EntityWandPearl, 64, 10, true);

        event.registerEntityPacket(entity -> entity instanceof EntityWandIceBall, transform(EntityWandIceBall::new));
        event.registerEntityPacket(entity -> entity instanceof EntityWandFireBall, transform(EntityWandFireBall::new));
        event.registerEntityPacket(entity -> entity instanceof EntityWandShockWave, transform(EntityWandShockWave::new));
        event.registerEntityPacket(entity -> entity instanceof EntityWandSlimeBall, transform(EntityWandSlimeBall::new));
        event.registerEntityPacket(entity -> entity instanceof EntityWandPearl, transform(EntityWandPearl::new));
    }

    private static EntityTrackerHandler.EntitySupplier transform(SimpleConstructor simpleConstructor) {
        return (world, x, y, z, packet) -> simpleConstructor.get(world, x, y, z);
    }

    @FunctionalInterface
    private interface SimpleConstructor {
        Entity get(WorldClient world, double x, double y, double z);
    }
}
