package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.WontFix;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.DispenseBehaviorEmptyBucketRedirect;
import net.oilcake.mitelros.util.DispenseBehaviorFilledBucketRedirect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBucket.class)
public abstract class ItemBucketMixin extends ItemVessel {
    @Shadow
    public abstract float getChanceOfMeltingWhenFilledWithLava();

    @Unique
    private static final int xpNeeded = 250;

    public ItemBucketMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }

    @ModifyReturnValue(method = "getChanceOfMeltingWhenFilledWithLava", at = @At("RETURN"))
    private float burnEasier(float original) {
        return original * 2.5F;
    }

    /**
     * @author
     * @reason
     */
    @WontFix
    @Overwrite
    public IBehaviorDispenseItem getDispenserBehavior() {
        return isEmpty() ? new DispenseBehaviorEmptyBucketRedirect((ItemBucket) getEmptyVessel())
                : ((getContents() != Material.water && getContents() != Material.lava) ?
                null : new DispenseBehaviorFilledBucketRedirect((ItemBucket) getEmptyVessel()));
    }

    @Inject(method = "getBlockForContents", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Block> cir) {
        if (this.contains(Materials.pure_water)) {
            cir.setReturnValue(Block.waterMoving);
        }
    }

    @ModifyArg(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;"))
    private Material moreWaterType(Material contents, @Local RaycastCollision rc) {
        if (contents != Material.water) return contents;
        BiomeGenBase biome = rc.world.getBiomeGenForCoords(rc.block_hit_x, rc.block_hit_z);
        Material material;
        if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) material = Materials.pure_water;
        else material = Materials.water;
        return material;
    }

    @ModifyExpressionValue(method = "tryPlaceContainedLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getContents()Lnet/minecraft/Material;"))
    private Material inject(Material material_in_bucket) {
        if (material_in_bucket == Material.water || material_in_bucket == Materials.pure_water) {
            return Material.water;
        }
        return material_in_bucket;
    }

    @ModifyConstant(method = "tryPlaceContainedLiquid", constant = @Constant(intValue = -100))
    private int itfXP_1(int constant) {
        return -xpNeeded;
    }

    @ModifyConstant(method = "shouldContainedLiquidBePlacedAsSourceBlock", constant = @Constant(intValue = 100))
    private static int itfXP(int constant) {
        return xpNeeded;
    }

    @ModifyConstant(method = "addInformation", constant = @Constant(intValue = 100))
    private int itfXp(int constant) {
        return xpNeeded;
    }

    @ModifyArg(method = "addInformation", at = @At(value = "INVOKE", target = "Lnet/minecraft/Translator;getFormatted(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"), index = 1)
    private Object[] fixChances(Object[] par1ArrayOfObj) {
        return new Object[]{Math.round(this.getChanceOfMeltingWhenFilledWithLava() * 100.0f)};
    }

    @Inject(method = "getPeer", at = @At("HEAD"), cancellable = true)
    @Deprecated(since = "ric1.3.9")// TODO temp fix
    private static void tempFix(Material vessel_material, Material contents, CallbackInfoReturnable<ItemVessel> cir) {
        if (vessel_material == Material.wood && contents == Material.milk) {
            cir.setReturnValue(Items.woodBucketMilk);
        }
    }
}
