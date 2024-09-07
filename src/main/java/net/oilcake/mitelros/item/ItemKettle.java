package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.util.FoodDataList;

public class ItemKettle extends Item implements IDamageableItem {
    private static final int drinkUnit = 3;
    private static final int douseUnit = 1;
    private final Material vessel_material;

    public ItemKettle(int id, int volume, Material contents, Material vessel_material) {
        super(id, Material.silk, "kettle");
        this.setAlwaysEdible();
        this.addMaterial(vessel_material, contents);
        this.setMaxDamage(volume);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.vessel_material = vessel_material;
        this.setCraftingDifficultyAsComponent(100.0F);
    }

    public ItemKettle getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.leather) {
            if (contents == Materials.suspicious_water) {
                return Items.leatherKettleSuspicious;
            }
            if (contents == Materials.dangerous_water) {
                return Items.leatherKettleSwampland;
            }
            if (contents == Materials.water) {
                return Items.leatherKettle;
            }
        } else if (vessel_material == Material.hardened_clay) {
            if (contents == Materials.suspicious_water) {
                return Items.hardenedClayJugSuspicious;
            }
            if (contents == Materials.dangerous_water) {
                return Items.hardenedClayJugSwampland;
            }
            if (contents == Materials.water) {
                return Items.hardenedClayJug;
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
        return (item_stack.getItemDamage() + drinkUnit <= item_stack.getMaxDamage()) ? EnumItemInUseAction.DRINK : null;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        if (rc == null || !rc.isBlock()) {
            return false;
        }
        BiomeGenBase biome = rc.world.getBiomeGenForCoords(rc.block_hit_x, rc.block_hit_z);
        ItemStack item_stack = player.getHeldItemStack();

        if (item_stack.getItemDamage() > 0) {
            if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                if (player.onServer()) {
                    if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.dangerous_water)));
                    } else if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.water)));
                    } else {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.suspicious_water)));
                    }
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

            if (this.contains(Material.water) || this.contains(Materials.suspicious_water) || this.contains(Materials.dangerous_water)) {
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
