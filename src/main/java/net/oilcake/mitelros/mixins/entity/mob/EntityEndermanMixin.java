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
        if (recently_hit_by_player && this.rand.nextInt(2) > 0) {
            this.dropItem(Items.stretchPotion, 1);// TODO Temporary
        }
    }
}
