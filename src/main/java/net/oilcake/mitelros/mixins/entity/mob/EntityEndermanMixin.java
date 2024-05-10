package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.DamageSource;
import net.minecraft.EntityEnderman;
import net.minecraft.EntityMob;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityEnderman.class)
public abstract class EntityEndermanMixin extends EntityMob {
    public EntityEndermanMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"))
    private void inject(boolean recently_hit_by_player, DamageSource damage_source, CallbackInfo ci) {
        if (recently_hit_by_player && this.rand.nextFloat() < 0.05f + 0.02f * damage_source.getLootingModifier()) {
            this.dropItem(Items.enderRod, 1);
        }
    }
}
