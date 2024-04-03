package net.oilcake.mitelros.mixins.item;

import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockFarmland;
import net.minecraft.BlockFluid;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumBlockFX;
import net.minecraft.EnumFace;
import net.minecraft.IBehaviorDispenseItem;
import net.minecraft.Item;
import net.minecraft.ItemBucket;
import net.minecraft.ItemStack;
import net.minecraft.ItemVessel;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.RaycastCollision;
import net.minecraft.StatList;
import net.minecraft.World;
import net.minecraft.WorldServer;
import net.oilcake.mitelros.block.MaterialHandler;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.DispenseBehaviorEmptyBucketRedirect;
import net.oilcake.mitelros.util.DispenseBehaviorFilledBucketRedirect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemBucket.class})
public class ItemBucketMixin extends ItemVessel {
    public ItemBucketMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }

    @Overwrite
    public float getChanceOfMeltingWhenFilledWithLava() {
        Material material = getVesselMaterial();
        return (material == Material.adamantium || material == Materials.tungsten) ? 0.0F : ((material == Material.gold) ? 0.5F : (0.025F * Material.mithril.durability / material.durability));
    }

    @Overwrite
    public IBehaviorDispenseItem getDispenserBehavior() {
        return isEmpty() ? (IBehaviorDispenseItem) new DispenseBehaviorEmptyBucketRedirect((ItemBucket) getEmptyVessel()) : ((getContents() != Material.water && getContents() != Material.lava) ? null : (IBehaviorDispenseItem) new DispenseBehaviorFilledBucketRedirect((ItemBucket) getEmptyVessel()));
    }

    @Overwrite
    public Block getBlockForContents() {
        if (contains(Material.water) || contains(Materials.dangerous_water) || contains(Materials.unsafe_water))
            return (Block) Block.waterMoving;
        if (contains(Material.lava))
            return (Block) Block.lavaMoving;
        Minecraft.setErrorMessage("getBlockForContents: no handler for contents " + getContents());
        return null;
    }

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
                                        player.convertOneOfHeldItem(new ItemStack((Item) getPeerForContents(Materials.dangerous_water)));
                                    } else if (player.onServer() && (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver)) {
                                        player.convertOneOfHeldItem(new ItemStack((Item) getPeerForContents(Material.water)));
                                    } else if (player.onServer()) {
                                        player.convertOneOfHeldItem(new ItemStack((Item) getPeerForContents(Materials.unsafe_water)));
                                    }
                                    return true;
                                }
                                player.convertOneOfHeldItem(new ItemStack((Item) getPeerForContents(material)));
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
                            player.convertOneOfHeldItem(new ItemStack((Item) getEmptyVessel()));
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
                        player.convertOneOfHeldItem(new ItemStack((Item) getEmptyVessel()));
                    return true;
                }
                return false;
            }
            if (player.onServer())
                player.convertOneOfHeldItem(new ItemStack((Item) getEmptyVessel()));
            return true;
        }
        return false;
    }

    @Overwrite
    public boolean tryPlaceContainedLiquid(World world, EntityPlayer player, int x, int y, int z, boolean allow_placement_of_source_block) {
        if (isEmpty()) {
            Minecraft.setErrorMessage("tryPlaceContainedLiquid: bucket is empty");
            return false;
        }
        Material material_in_bucket = getContents();
        if (material_in_bucket == null) {
            Minecraft.setErrorMessage("tryPlaceContainedLiquid: material in bucket is null");
            return false;
        }
        Material target_block_material = world.getBlockMaterial(x, y, z);
        if (target_block_material.isSolid())
            return false;
        boolean placement_prevented = false;
        if (material_in_bucket.canDouseFire() && world.getBlock(x, y, z) == Block.fire) {
            if (!world.isRemote)
                world.douseFire(x, y, z, (Entity) null);
            placement_prevented = true;
        } else if ((contains(Material.water) || contains(Materials.dangerous_water) || contains(Materials.unsafe_water)) && world.provider.isHellWorld) {
            if (!world.isRemote)
                world.blockFX(EnumBlockFX.steam, x, y, z);
            placement_prevented = true;
        }
        if (!placement_prevented) {
            if (player != null && !player.inCreativeMode() && material_in_bucket == target_block_material)
                return true;
            if (!world.isRemote) {
                WorldServer world_server = (WorldServer) world;
                if (!target_block_material.isSolid() && !target_block_material.isLiquid() && !world.isAirBlock(x, y, z))
                    world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setFlooded((BlockFluid) getBlockForContents()), true);
                if ((material_in_bucket == Material.water || material_in_bucket == Materials.unsafe_water || material_in_bucket == Materials.dangerous_water) && world.getBlockMaterial(x, y, z) == Material.lava) {
                    world.tryConvertLavaToCobblestoneOrObsidian(x, y, z);
                } else {
                    if ((material_in_bucket == Material.water || material_in_bucket == Materials.unsafe_water || material_in_bucket == Materials.dangerous_water) && world.getBlock(x, y - 1, z) == Block.mantleOrCore) {
                        world.blockFX(EnumBlockFX.steam, x, y, z);
                        return true;
                    }
                    if (material_in_bucket == Material.lava && world.getBlockMaterial(x, y, z) == Material.water) {
                        world.tryConvertWaterToCobblestone(x, y, z);
                    } else {
                        if (player == null || !player.inCreativeMode())
                            if (material_in_bucket == Material.water || material_in_bucket == Materials.unsafe_water || material_in_bucket == Materials.dangerous_water) {
                                if (!allow_placement_of_source_block) {
                                    world.scheduleBlockChange(x, y, z, Block.waterStill.blockID, (getBlockForContents()).blockID, 1, 16);
                                } else if (!player.inCreativeMode()) {
                                    player.addExperience(-250);
                                }
                            } else if (material_in_bucket == Material.lava) {
                                if (!allow_placement_of_source_block) {
                                    world.scheduleBlockChange(x, y, z, Block.lavaMoving.blockID, (getBlockForContents()).blockID, 1, 48);
                                } else if (!player.inCreativeMode()) {
                                    player.addExperience(-250);
                                }
                            }
                        world.setBlock(x, y, z, (getBlockForContents()).blockID, 0, 3);
                    }
                }
            }
        }
        return true;
    }

    @Overwrite
    public static boolean shouldContainedLiquidBePlacedAsSourceBlock(EntityPlayer player, boolean ctrl_is_down) {
        if (player == null)
            return false;
        if (player.inCreativeMode())
            return true;
        return (ctrl_is_down && player.experience >= 250);
    }

    @Overwrite
    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (contents == null) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Item.bucketCopperEmpty;
            if (vessel_material == Material.silver)
                return (ItemVessel) Item.bucketSilverEmpty;
            if (vessel_material == Material.gold)
                return (ItemVessel) Item.bucketGoldEmpty;
            if (vessel_material == Material.iron)
                return (ItemVessel) Item.bucketIronEmpty;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Item.bucketMithrilEmpty;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Item.bucketAdamantiumEmpty;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucket;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucket;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Item.bucketAncientMetalEmpty : null;
        }
        if (contents == Material.water) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Item.bucketCopperWater;
            if (vessel_material == Material.silver)
                return (ItemVessel) Item.bucketSilverWater;
            if (vessel_material == Material.gold)
                return (ItemVessel) Item.bucketGoldWater;
            if (vessel_material == Material.iron)
                return (ItemVessel) Item.bucketIronWater;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Item.bucketMithrilWater;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Item.bucketAdamantiumWater;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketWater;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketWater;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Item.bucketAncientMetalWater : null;
        }
        if (contents == Materials.unsafe_water) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Items.copperBucketWaterSuspicious;
            if (vessel_material == Material.silver)
                return (ItemVessel) Items.silverBucketWaterSuspicious;
            if (vessel_material == Material.gold)
                return (ItemVessel) Items.goldBucketWaterSuspicious;
            if (vessel_material == Material.iron)
                return (ItemVessel) Items.ironBucketWaterSuspicious;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Items.mithrilBucketWaterSuspicious;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Items.adamantiumBucketWaterSuspicious;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketWaterSuspicious;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketWaterSuspicious;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Items.ancientmetalBucketWaterSuspicious : null;
        }
        if (contents == Materials.dangerous_water) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Items.copperBucketWaterSwampland;
            if (vessel_material == Material.silver)
                return (ItemVessel) Items.silverBucketWaterSwampland;
            if (vessel_material == Material.gold)
                return (ItemVessel) Items.goldBucketWaterSwampland;
            if (vessel_material == Material.iron)
                return (ItemVessel) Items.ironBucketWaterSwampland;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Items.mithrilBucketWaterSwampland;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Items.adamantiumBucketWaterSwampland;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketWaterSwampland;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketWaterSwampland;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Items.ancientmetalBucketWaterSwampland : null;
        }
        if (contents == Material.lava) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Item.bucketCopperLava;
            if (vessel_material == Material.silver)
                return (ItemVessel) Item.bucketSilverLava;
            if (vessel_material == Material.gold)
                return (ItemVessel) Item.bucketGoldLava;
            if (vessel_material == Material.iron)
                return (ItemVessel) Item.bucketIronLava;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Item.bucketMithrilLava;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Item.bucketAdamantiumLava;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketLava;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketLava;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Item.bucketAncientMetalLava : null;
        }
        if (contents == Material.milk) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Item.bucketCopperMilk;
            if (vessel_material == Material.silver)
                return (ItemVessel) Item.bucketSilverMilk;
            if (vessel_material == Material.gold)
                return (ItemVessel) Item.bucketGoldMilk;
            if (vessel_material == Material.iron)
                return (ItemVessel) Item.bucketIronMilk;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Item.bucketMithrilMilk;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Item.bucketAdamantiumMilk;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketMilk;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketMilk;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Item.bucketAncientMetalMilk : null;
        }
        if (contents == Material.stone) {
            if (vessel_material == Material.copper)
                return (ItemVessel) Item.bucketCopperStone;
            if (vessel_material == Material.silver)
                return (ItemVessel) Item.bucketSilverStone;
            if (vessel_material == Material.gold)
                return (ItemVessel) Item.bucketGoldStone;
            if (vessel_material == Material.iron)
                return (ItemVessel) Item.bucketIronStone;
            if (vessel_material == Material.mithril)
                return (ItemVessel) Item.bucketMithrilStone;
            if (vessel_material == Material.adamantium)
                return (ItemVessel) Item.bucketAdamantiumStone;
            if (vessel_material == Materials.nickel)
                return (ItemVessel) Items.nickelBucketStone;
            if (vessel_material == Materials.tungsten)
                return (ItemVessel) Items.tungstenBucketStone;
            return (vessel_material == Material.ancient_metal) ? (ItemVessel) Item.bucketAncientMetalStone : null;
        }
        return null;
    }

    private Material classifyMaterialForm(Material material) {
        return MaterialHandler.classifyMaterialForm(material);
    }

    @Shadow
    public ItemVessel getPeerForContents(Material material) {
        return null;
    }

    @Shadow
    public ItemVessel getPeerForVesselMaterial(Material material) {
        return null;
    }
}
