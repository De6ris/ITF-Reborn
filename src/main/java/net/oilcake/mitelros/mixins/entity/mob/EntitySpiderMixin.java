package net.oilcake.mitelros.mixins.entity.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.EntityArachnid;
import net.minecraft.EntitySpider;
import net.minecraft.World;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntitySpider.class)
public class EntitySpiderMixin extends EntityArachnid {
    public EntitySpiderMixin(World par1World, float scaling) {
        super(par1World, scaling);
    }

    @ModifyExpressionValue(method = "getMountedSkeleton", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntitySkeleton;getSkeletonType()I"))
    private int underAlliance(int original) {// TODO what does it do
        if (original == 0 || ITFConfig.TagUnderAlliance.getBooleanValue()) {
            return 0;
        } else {
            return 1;
        }
    }
}
