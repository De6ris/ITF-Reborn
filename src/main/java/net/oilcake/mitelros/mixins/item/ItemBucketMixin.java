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
        if (this.contains(Materials.dangerous_water) || this.contains(Materials.suspicious_water)) {
            cir.setReturnValue(Block.waterMoving);
        }
    }

    @ModifyArg(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;"))
    private Material moreWaterType(Material contents, @Local RaycastCollision rc) {
        if (contents != Material.water) return contents;
        BiomeGenBase biome = rc.world.getBiomeGenForCoords(rc.block_hit_x, rc.block_hit_z);
        Material material;
        if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) material = Materials.dangerous_water;
        else if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) material = Material.water;
        else material = Materials.suspicious_water;
        return material;
    }

    @ModifyExpressionValue(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;"))
    private ItemVessel fixNPE(ItemVessel original) {
        if (original == null) return this;
        return original;
    }// if null, won't convert anything

    @ModifyExpressionValue(method = "tryPlaceContainedLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getContents()Lnet/minecraft/Material;"))
    private Material inject(Material material_in_bucket) {
        if (material_in_bucket == Material.water || material_in_bucket == Materials.suspicious_water || material_in_bucket == Materials.dangerous_water) {
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

    @Unique
    private static ItemVessel itfVessels(Material vessel_material, Material contents) {
        if (contents == null) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucket;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucket;
            if (vessel_material == Material.wood)
                return Items.woodBucket;
        } else if (contents == Material.water) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketWater;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketWater;
            if (vessel_material == Material.wood)
                return Items.woodBucketWater;
        } else if (contents == Material.lava) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketLava;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketLava;
        } else if (contents == Material.milk) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketMilk;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketMilk;
            if (vessel_material == Material.wood)
                return Items.woodBucketMilk;
        } else if (contents == Material.stone) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketStone;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketStone;
        } else if (contents == Materials.suspicious_water) {
            if (vessel_material == Material.copper)
                return Items.copperBucketWaterSuspicious;
            if (vessel_material == Material.silver)
                return Items.silverBucketWaterSuspicious;
            if (vessel_material == Material.gold)
                return Items.goldBucketWaterSuspicious;
            if (vessel_material == Material.iron)
                return Items.ironBucketWaterSuspicious;
            if (vessel_material == Material.mithril)
                return Items.mithrilBucketWaterSuspicious;
            if (vessel_material == Material.adamantium)
                return Items.adamantiumBucketWaterSuspicious;
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketWaterSuspicious;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketWaterSuspicious;
            if (vessel_material == Materials.ancient_metal)
                return Items.ancientmetalBucketWaterSuspicious;
            if (vessel_material == Material.wood)
                return Items.woodBucketWaterSuspicious;
            return null;
        } else if (contents == Materials.dangerous_water) {
            if (vessel_material == Material.copper)
                return Items.copperBucketWaterDangerous;
            if (vessel_material == Material.silver)
                return Items.silverBucketWaterDangerous;
            if (vessel_material == Material.gold)
                return Items.goldBucketWaterDangerous;
            if (vessel_material == Material.iron)
                return Items.ironBucketWaterDangerous;
            if (vessel_material == Material.mithril)
                return Items.mithrilBucketWaterDangerous;
            if (vessel_material == Material.adamantium)
                return Items.adamantiumBucketWaterDangerous;
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketWaterDangerous;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketWaterDangerous;
            if (vessel_material == Material.ancient_metal)
                return Items.ancientmetalBucketWaterDangerous;
            if (vessel_material == Material.wood)
                return Items.woodBucketWaterDangerous;
            return null;
        }
        return null;
    }

    @Inject(method = "getPeer", at = @At("HEAD"), cancellable = true)
    private static void itfVessel(Material vessel_material, Material contents, CallbackInfoReturnable<ItemVessel> cir) {
        ItemVessel itfVessel = itfVessels(vessel_material, contents);
        if (itfVessel != null) {
            cir.setReturnValue(itfVessel);
        }
    }

    @ModifyConstant(method = "addInformation", constant = @Constant(intValue = 100))
    private int itfXp(int constant) {
        return xpNeeded;
    }

    @ModifyArg(method = "addInformation", at = @At(value = "INVOKE", target = "Lnet/minecraft/Translator;getFormatted(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"), index = 1)
    private Object[] fixChances(Object[] par1ArrayOfObj) {
        return new Object[]{Math.round(this.getChanceOfMeltingWhenFilledWithLava() * 100.0f)};
    }
}
