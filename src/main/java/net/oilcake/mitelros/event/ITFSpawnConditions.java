package net.oilcake.mitelros.event;

import moddedmite.rustedironcore.api.event.events.SpawnConditionRegisterEvent;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.*;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.function.Consumer;

public class ITFSpawnConditions implements Consumer<SpawnConditionRegisterEvent> {
    private static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

    @Override
    public void accept(SpawnConditionRegisterEvent event) {
        event.register(EntityCreeper.class, (world, x, y, z) -> {
            if (world.hasSkylight() && !world.isDaytime() && world.rand.nextInt(4) != 0 && world.isOutdoors(x, y, z)) {
                return null;
            }
            if (world.rand.nextInt(40) >= y && world.rand.nextFloat() < 0.5f) {
                return EntityInfernalCreeper.class;
            }
            if (y <= 40)
                return ITFConfig.TagInvisibleFollower.getBooleanValue() ? EntityStalkerCreeper.class : EntityCreeper.class;
            return EntityCreeper.class;
        });

        event.register(EntityRevenant.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            boolean is_blood_moon_up = world.isBloodMoon(true);
            boolean can_spawn_revenants_on_surface = is_blood_moon_up;
            if (!check_depth || y <= 44 || can_spawn_revenants_on_surface) {
                if ((calendar.get(Calendar.MONTH) + 1) == 4 && calendar.get(Calendar.DATE) == 1) {
                    return EntityZombieLord.class;
                } else {
                    return EntityRevenant.class;
                }
            }
            return null;
        });

        event.register(EntityInvisibleStalker.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            if (!check_depth || y <= 40) {
                if (world.rand.nextInt(40) >= y && world.rand.nextFloat() < 0.5F)
                    return EntityGhost.class;
                return EntityInvisibleStalker.class;
            }
            return null;
        });

        event.register(EntityRetinueZombie.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            if (!check_depth || y <= 32) {
                return EntityRetinueZombie.class;
            }
            return null;
        });

        event.register(EntityBoneBodyguard.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            if (!check_depth || y <= 32) {
                return EntityBoneBodyguard.class;
            }
            return null;
        });

        event.register(EntityZombie.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            boolean is_blood_moon_up = world.isBloodMoon(true);
            if (!check_depth || (y <= 32 && world.rand.nextFloat() <= 0.25F) || is_blood_moon_up) {
                return EntityRetinueZombie.class;
            }
            return EntityZombie.class;
        });


        event.register(EntitySkeleton.class, (world, x, y, z) -> {
            boolean check_depth = world.isOverworld();
            boolean is_blood_moon_up = world.isBloodMoon(true);
            if (!check_depth || (y <= 32 && world.rand.nextFloat() <= 0.25F) || is_blood_moon_up) {
                return EntityBoneBodyguard.class;
            }
            return EntitySkeleton.class;
        });
    }
}
