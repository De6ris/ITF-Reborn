package net.oilcake.mitelros.mixins.util;

import net.minecraft.EntityDamageResult;
import net.oilcake.mitelros.api.ITFDamageResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityDamageResult.class)
public class EntityDamageResultMixin implements ITFDamageResult {
    @Shadow
    private boolean entity_was_destroyed;

    @Override
    public void setEntity_was_destroyed(boolean entity_was_destroyed) {
        this.entity_was_destroyed = entity_was_destroyed;
    }
}
