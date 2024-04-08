package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
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
        if (recently_hit_by_player && this.rand.nextInt(damage_source.getLootingModifier() + 4) > 2) {
            int num_drops = this.rand.nextInt(2);
            for (int i = 0; i < num_drops; ++i) {
                this.dropItem(Items.stretchPotion, 1);// TODO Temporary
            }
        }
    }
}
