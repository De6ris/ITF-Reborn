package net.oilcake.mitelros.item;

import moddedmite.rustedironcore.api.util.LogUtil;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.util.FoodDataList;
import org.apache.logging.log4j.Logger;

public class ItemKettle extends Item implements IDamageableItem {
    private static final Logger LOGGER = LogUtil.getLogger();
    private static final int drinkUnit = 3;
    private static final int douseUnit = 1;
    private final Material vessel_material;
    private boolean purify = false;

    public ItemKettle(int id, int volume, Material contents, Material vessel_material) {
        super(id, Material.silk, "kettle");
        this.setAlwaysEdible();
        this.addMaterial(vessel_material, contents);
        this.setMaxDamage(volume);
        this.vessel_material = vessel_material;
        this.setCraftingDifficultyAsComponent(100.0F);
    }

    public ItemKettle setPurify() {
        this.purify = true;
        return this;
    }

    public ItemKettle getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.leather) {
            if (contents == Materials.water) {
                return Items.leatherKettle;
            }
            if (contents == Materials.pure_water) {
                return Items.leatherKettlePure;
            }
        } else if (vessel_material == Material.hardened_clay) {
            if (contents == Materials.water) {
                return Items.hardenedClayJug;
            }
            if (contents == Materials.pure_water) {
                return Items.hardenedClayJugPure;
            }
        } else if (vessel_material == Materials.uru) {
            if (contents == Material.water) {
                LOGGER.warn("why get water peer for uru kettle");
                return Items.uruKettle;
            }
            if (contents == Materials.pure_water) {
                return Items.uruKettle;
            }
        }
        return null;
    }

    public static ItemStack boil(ItemStack input) {
        ItemKettle item = (ItemKettle) input.getItem();
        ItemKettle peer = item.getPeer(item.vessel_material, Materials.water);
        return new ItemStack(peer).setItemDamage(input.getItemDamage());
    }

    @Override
    public int getNumComponentsForDurability() {
        return 8;
    }

    @Override
    public int getRepairCost() {
        return 16;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    @Override
    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    @Override
    public float getCompostingValue() {
        return 0.0F;
    }

    public boolean contains(Material material) {
        return this.hasMaterial(material);
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.itf$AddWater(2);
            FoodDataList.onWaterDrunk(item_stack.getItem(), player);
            player.getHeldItemStack().tryDamageItem(world, drinkUnit, true);
        }
    }

    @Override
    public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
        if (item_stack.getItemDamage() + drinkUnit > item_stack.getMaxDamage()) {
            return null;
        }
        ITFFoodStats foodStats = (ITFFoodStats) player.getFoodStats();
        if (foodStats.itf$GetWater() >= foodStats.itf$GetWaterLimit()) {
            return null;
        }
        return EnumItemInUseAction.DRINK;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        if (rc == null || !rc.isBlock()) {
            return false;
        }
        ItemStack item_stack = player.getHeldItemStack();

        if (item_stack.getItemDamage() > 0) {

            if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {

                if (player.onServer()) {
                    Material result;
                    if (this.purify) {
                        result = Materials.pure_water;
                    } else {
                        BiomeGenBase biome = rc.world.getBiomeGenForCoords(rc.block_hit_x, rc.block_hit_z);
                        if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                            result = Materials.pure_water;
                        } else {
                            result = Materials.water;
                        }
                    }
                    player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, result)));

                }
                return true;
            }
        } else {
            if (rc.getNeighborOfBlockHit() == Block.fire && item_stack.getItemDamage() + douseUnit < item_stack.getMaxDamage()) {
                if (player.onServer()) {
                    rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, (Entity) null);
                    player.getHeldItemStack().tryDamageItem(player.worldObj, douseUnit, true);
                }

                return true;
            }

            if (this.contains(Material.water) || this.contains(Materials.pure_water)) {
                Block block = rc.getBlockHit();
                int x = rc.block_hit_x;
                int y = rc.block_hit_y;
                int z = rc.block_hit_z;
                EnumFace face_hit = rc.face_hit;
                if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
                    --y;
                    block = rc.world.getBlock(x, y, z);
                    face_hit = EnumFace.TOP;
                }
                if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                    if (player.onServer() && !player.inCreativeMode()) {
                        player.getHeldItemStack().tryDamageItem(player.worldObj, douseUnit, true);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
