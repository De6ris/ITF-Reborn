package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.potion.PotionExtend;

public class ItemBowlClay extends ItemBowl {
    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, contents, texture);
        this.setMaterial(Material.hardened_clay);
        this.setTextureName("hardened_clay_bowls/" + texture);
    }

    @Override
    public int getSimilarityToItem(Item item) {
        if (item instanceof ItemBowlClay item_bowl) {
            if (item_bowl.isEmpty() || isEmpty())
                return 2;
        }
        return super.getSimilarityToItem(item);
    }

    @Override
    public int getBurnTime(ItemStack item_stack) {
        return 0;
    }

    public static boolean isSoupOrStew(Item item) {
        if (!(item instanceof ItemBowlClay))
            return false;
        Material contents = ((ItemBowlClay) item).getContents();
        return (contents instanceof net.minecraft.MaterialSoup || contents instanceof net.minecraft.MaterialStew);
    }

    @Override
    public float getCompostingValue() {
        return (this == Items.claybowlMilk) ? 0.0F : super.getCompostingValue();
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock())
            if (isEmpty()) {
                if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                    if (player.onServer() && (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland)) {
                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Materials.dangerous_water)));
                    } else if (player.onServer() && (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver)) {
                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Material.water)));
                    } else if (player.onServer()) {
                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Materials.suspicious_water)));
                    }
                    return true;
                }
            } else {
                if (rc.getNeighborOfBlockHit() == Block.fire && canContentsDouseFire()) {
                    if (player.onServer()) {
                        rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, null);
                        player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
                    }
                    return true;
                }
                if (contains(Material.water)) {
                    Block block = rc.getBlockHit();
                    int x = rc.block_hit_x;
                    int y = rc.block_hit_y;
                    int z = rc.block_hit_z;
                    EnumFace face_hit = rc.face_hit;
                    if (block instanceof net.minecraft.BlockCrops || block instanceof net.minecraft.BlockStem || block == Block.mushroomBrown) {
                        y--;
                        block = rc.world.getBlock(x, y, z);
                        face_hit = EnumFace.TOP;
                    }
                    if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                        if (player.onServer() && !player.inCreativeMode())
                            player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
                        return true;
                    }
                }
            }
        return false;
    }

    @Override
    public ItemVessel getPeerForContents(Material contents) {
        return ItemBowlClay.getPeer(this.getVesselMaterial(), contents);
    }

    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.hardened_clay) {
            if (contents == null)
                return Items.claybowlEmpty;
            if (contents == Material.mushroom_stew)
                return Items.claybowlMushroomStew;
            if (contents == Material.milk)
                return Items.claybowlMilk;
            if (contents == Material.water)
                return Items.claybowlWater;
            if (contents == Material.beef_stew)
                return Items.claybowlBeefStew;
            if (contents == Material.chicken_soup)
                return Items.claybowlChickenSoup;
            if (contents == Material.vegetable_soup)
                return Items.claybowlVegetableSoup;
            if (contents == Material.ice_cream)
                return Items.claybowlIceCream;
            if (contents == Material.salad)
                return Items.claybowlSalad;
            if (contents == Material.cream_of_mushroom_soup)
                return Items.claybowlCreamOfMushroomSoup;
            if (contents == Material.cream_of_vegetable_soup)
                return Items.claybowlCreamOfVegetableSoup;
            if (contents == Material.mashed_potato)
                return Items.claybowlMashedPotato;
            if (contents == Material.porridge)
                return Items.claybowlPorridge;
            if (contents == Material.cereal)
                return Items.claybowlCereal;
            if (contents == Materials.chestnut_soup)
                return Items.claybowlChestnutSoup;
            if (contents == Materials.porkchop_stew)
                return Items.claybowlPorkchopStew;
            if (contents == Materials.suspicious_water)
                return Items.claybowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.claybowlWaterSwampland;
        }
        return null;
    }
}
