package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.block.MaterialHandler;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.DispenseBehaviorEmptyBucketRedirect;
import net.oilcake.mitelros.util.DispenseBehaviorFilledBucketRedirect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBucket.class)
public abstract class ItemBucketMixin extends ItemVessel {
    @Unique
    private static final int xpNeeded = 250;

    @Shadow
    public static boolean shouldContainedLiquidBePlacedAsSourceBlock(EntityPlayer player, boolean ctrl_is_down) {
        return false;
    }

    @Shadow
    public abstract boolean tryPlaceContainedLiquid(World world, EntityPlayer player, int x, int y, int z, boolean allow_placement_of_source_block);

    @Shadow
    public abstract Block getBlockForContents();

    @Shadow
    public abstract float getChanceOfMeltingWhenFilledWithLava();

    public ItemBucketMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }

    @Inject(method = "getChanceOfMeltingWhenFilledWithLava", at = @At("HEAD"), cancellable = true)
    private void inject_1(CallbackInfoReturnable<Float> cir) {
        if (this.getVesselMaterial() == Materials.tungsten) {
            cir.setReturnValue(0.0F);
        }
    }

    @ModifyReturnValue(method = "getChanceOfMeltingWhenFilledWithLava", at = @At("RETURN"))
    private float harder(float original) {
        return original * 2.5F;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public IBehaviorDispenseItem getDispenserBehavior() {
        return isEmpty() ? new DispenseBehaviorEmptyBucketRedirect((ItemBucket) getEmptyVessel())
                : ((getContents() != Material.water && getContents() != Material.lava) ?
                null : new DispenseBehaviorFilledBucketRedirect((ItemBucket) getEmptyVessel()));
    }

    @Inject(method = "getBlockForContents", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Block> cir) {
        if (this.contains(Materials.dangerous_water) || this.contains(Materials.unsafe_water)) {
            cir.setReturnValue(Block.waterMoving);
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock()) {
            if (isEmpty()) {
                int x;
                int y;
                int z;
                Material material;
                if (rc.getBlockHitMaterial().isLiquid()) {
                    x = rc.block_hit_x;
                    y = rc.block_hit_y;
                    z = rc.block_hit_z;
                    material = rc.getBlockHitMaterial();
                } else {
                    x = rc.neighbor_block_x;
                    y = rc.neighbor_block_y;
                    z = rc.neighbor_block_z;
                    material = rc.getNeighborOfBlockHitMaterial();
                }
                if (material != null && material.isLiquid()) {
                    if (player.inCreativeMode() && !player.canMineAndEditBlock(x, y, z))
                        return false;
                    if (player.onServer()) {
                        if (player.inCreativeMode() || ctrl_is_down)
                            rc.world.setBlockToAir(x, y, z);
                        if (!player.inCreativeMode())
                            System.out.println(getChanceOfMeltingWhenFilledWithLava());
                            if (material == Material.lava && rc.world.rand.nextFloat() < getChanceOfMeltingWhenFilledWithLava()) {
                                player.addStat(StatList.objectBreakStats[this.itemID], 1);
                                ItemStack held_item_stack = player.getHeldItemStack();
                                ItemStack itemStack1 = getItemProducedWhenDestroyed(held_item_stack, DamageSource.lava);
                                if (itemStack1 == null)
                                    rc.world.blockFX(EnumBlockFX.item_consumed_by_lava, x, y, z);
                                player.convertOneOfHeldItem(itemStack1);
                                if (!player.hasHeldItem())
                                    (player.getAsEntityPlayerMP()).prevent_item_pickup_due_to_held_item_breaking_until = System.currentTimeMillis() + 1500L;
                            } else {
                                if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                                    if (player.onServer() && (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland)) {
                                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Materials.dangerous_water)));
                                    } else if (player.onServer() && (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver)) {
                                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Material.water)));
                                    } else if (player.onServer()) {
                                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Materials.unsafe_water)));
                                    }
                                    return true;
                                }
                                player.convertOneOfHeldItem(new ItemStack(getPeerForContents(material)));
                            }
                    }
                    return true;
                }
                return false;
            }
            if (contains(Material.stone))
                return false;
            ItemStack item_stack = player.getHeldItemStack();
            if (contains(Material.water) || contains(Materials.dangerous_water) || contains(Materials.unsafe_water)) {
                Block block = rc.getBlockHit();
                int x = rc.block_hit_x;
                int y = rc.block_hit_y;
                int z = rc.block_hit_z;
                EnumFace face_hit = rc.face_hit;
                if (rc.world.getBlock(x, y - 1, z) == Block.tilledField) {
                    y--;
                    block = rc.world.getBlock(x, y, z);
                    face_hit = EnumFace.TOP;
                }
                if (block == Block.tilledField && face_hit == EnumFace.TOP) {
                    if (BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                        if (player.onServer() && !player.inCreativeMode())
                            player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
                        return true;
                    }
                    return false;
                }
            }
            if (player.inCreativeMode() || (rc.getBlockHitMaterial() != classifyMaterialForm(getContents()) && rc.getNeighborOfBlockHitMaterial() != classifyMaterialForm(getContents()))) {
                int x;
                int y;
                int z;
                if (!rc.getBlockHit().isLiquid() && !rc.isBlockHitReplaceableBy(getBlockForContents(), 0)) {
                    x = rc.neighbor_block_x;
                    y = rc.neighbor_block_y;
                    z = rc.neighbor_block_z;
                } else {
                    x = rc.block_hit_x;
                    y = rc.block_hit_y;
                    z = rc.block_hit_z;
                }
                if (!player.canPlayerEdit(x, y, z, item_stack))
                    return false;
                if (tryPlaceContainedLiquid(rc.world, player, x, y, z, shouldContainedLiquidBePlacedAsSourceBlock(player, ctrl_is_down))) {
                    if (player.onServer() && !player.inCreativeMode())
                        player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
                    return true;
                }
                return false;
            }
            if (player.onServer())
                player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
            return true;
        }
        return false;
    }

    @ModifyExpressionValue(method = "tryPlaceContainedLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBucket;getContents()Lnet/minecraft/Material;"))
    private Material inject(Material material_in_bucket) {
        if (material_in_bucket == Material.water || material_in_bucket == Materials.unsafe_water || material_in_bucket == Materials.dangerous_water) {
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
        } else if (contents == Material.water) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketWater;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketWater;
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
        } else if (contents == Material.stone) {
            if (vessel_material == Materials.nickel)
                return Items.nickelBucketStone;
            if (vessel_material == Materials.tungsten)
                return Items.tungstenBucketStone;
        } else if (contents == Materials.unsafe_water) {
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

    private Material classifyMaterialForm(Material material) {
        return MaterialHandler.classifyMaterialForm(material);
    }

    @ModifyConstant(method = "addInformation", constant = @Constant(intValue = 100))
    private int itfXp(int constant) {
        return xpNeeded;
    }
}
