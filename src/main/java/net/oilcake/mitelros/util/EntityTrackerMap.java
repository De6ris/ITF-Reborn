package net.oilcake.mitelros.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Entity;
import net.minecraft.Packet23VehicleSpawn;
import net.minecraft.WorldClient;
import net.oilcake.mitelros.entity.misc.*;

import java.util.Map;
import java.util.Optional;

public class EntityTrackerMap {
    private final static Map<Integer, Constructor> IntToConstructorMap;
    private final static Map<Class<? extends Entity>, Integer> ClassToIntMap;

    static {
        ImmutableMap.Builder<Integer, Constructor> builder = ImmutableMap.builder();
        builder.put(100, transform(EntityWandIceBall::new));
        builder.put(101, transform(EntityWandFireBall::new));
        builder.put(102, transform(EntityWandShockWave::new));
        builder.put(103, transform(EntityWandSlimeBall::new));
        builder.put(104, transform(EntityWandPearl::new));
        IntToConstructorMap = builder.build();

        ImmutableMap.Builder<Class<? extends Entity>, Integer> builder1 = ImmutableMap.builder();
        builder1.put(EntityWandIceBall.class, 100);
        builder1.put(EntityWandFireBall.class, 101);
        builder1.put(EntityWandShockWave.class, 102);
        builder1.put(EntityWandSlimeBall.class, 103);
        builder1.put(EntityWandPearl.class, 104);
        ClassToIntMap = builder1.build();
    }

    public static Optional<Integer> match(Entity entity) {
        return Optional.ofNullable(ClassToIntMap.getOrDefault(entity.getClass(), null));
    }

    public static Optional<Constructor> match(int type) {
        return Optional.ofNullable(IntToConstructorMap.getOrDefault(type, null));
    }

    private static Constructor transform(SimpleConstructor simpleConstructor) {
        return (world, x, y, z, packet) -> simpleConstructor.get(world, x, y, z);
    }

    @FunctionalInterface
    private interface SimpleConstructor {
        Entity get(WorldClient world, double x, double y, double z);
    }

    @FunctionalInterface
    public interface Constructor {
        Entity get(WorldClient world, double x, double y, double z, Packet23VehicleSpawn packet);
    }

}
