package net.oilcake.mitelros.mixins.util;

import net.minecraft.Material;
import net.oilcake.mitelros.api.ITFMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Material.class)
public class MaterialMixin implements ITFMaterial {
    @Shadow
    protected float durability;
    @Shadow
    protected int min_harvest_level;
    @Shadow
    protected String name;

    @Override
    @Unique
    public float getDurability() {
        return this.durability;
    }

    @Override
    @Unique
    public int getMinHarvestLevel() {
        return this.min_harvest_level;
    }

    @Override
    @Unique
    public String getName() {
        return this.name;
    }
}

