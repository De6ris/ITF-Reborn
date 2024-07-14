package net.oilcake.mitelros.api;

import net.minecraft.Entity;
import net.minecraft.Explosion;

public interface ITFWorld {
    int itf$GetWorldSeason();
    float itf$GetSeasonGrowthModifier();
    Explosion itf$ExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean b);
}
