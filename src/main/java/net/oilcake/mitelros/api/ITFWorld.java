package net.oilcake.mitelros.api;

import net.minecraft.Entity;
import net.minecraft.Explosion;
import net.minecraft.World;
import net.oilcake.mitelros.util.EnumSeason;

public interface ITFWorld {
    EnumSeason itf$GetWorldSeason();

    float itf$GetSeasonGrowthModifier();

    Explosion itf$ExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean b);

    static EnumSeason getSeason(World world) {
        return ((ITFWorld) world).itf$GetWorldSeason();
    }
}
