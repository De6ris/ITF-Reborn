package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemBowlClay extends ItemVessel {

    private int water;

    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, Material.hardened_clay, contents, 1, 4, 4, "hardened_clay_bowls/" + texture);
        setCraftingDifficultyAsComponent(25.0F);
        setCreativeTab(CreativeTabs.tabMisc);
        if (contains(Material.water)) {
            setWater(2);
        } else if (contains(Materials.dangerous_water) || contains(Materials.unsafe_water) || contains(Material.milk)) {
            setWater(1);
        } else if (contains(Material.mashed_potato)) {
            setWater(0);
        } else if (contains(Material.cereal)) {
            setWater(2);
        } else if (contains(Material.ice_cream)) {
            setWater(2);
        } else if (contains(Material.cream_of_mushroom_soup)) {
            setWater(2);
        } else if (contains(Materials.beetroot)) {
            setWater(6);
        } else if (contains(Materials.salad)) {
            setWater(0);
        } else if (!isEmpty()) {
            setWater(4);
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
        return !isIngestable(item_stack) ? null : super.getItemInUseAction(item_stack, player);
    }

    public int getSimilarityToItem(Item item) {
        if (item instanceof ItemBowlClay) {
            ItemBowlClay item_bowl = (ItemBowlClay) item;
            if (item_bowl.isEmpty() || isEmpty())
                return 2;
        }
        return super.getSimilarityToItem(item);
    }

    public ItemBowlClay setAnimalProduct() {
        super.setAnimalProduct();
        return this;
    }

    public ItemBowlClay setPlantProduct() {
        super.setPlantProduct();
        return this;
    }

    public int getBurnTime(ItemStack item_stack) {
        return 0;
    }

    public ItemVessel getPeerForContents(Material contents) {
        return getPeer(getVesselMaterial(), contents);
    }

    public ItemVessel getPeerForVesselMaterial(Material vessel_material) {
        return getPeer(vessel_material, getContents());
    }

    public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
        return !contains(Material.water);
    }

    public static boolean isSoupOrStew(Item item) {
        if (!(item instanceof ItemBowlClay))
            return false;
        Material contents = ((ItemBowlClay) item).getContents();
        return (contents instanceof net.minecraft.MaterialSoup || contents instanceof net.minecraft.MaterialStew);
    }

    public float getCompostingValue() {
        return (this == Items.claybowlMilk) ? 0.0F : super.getCompostingValue();
    }

    public Item getCompostingRemains(ItemStack item_stack) {
        return getEmptyVessel();
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if (((Boolean) ITFConfig.Realistic.get())) {
                if (contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (450.0D * (1.0D + rand)), 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
                if (contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if (rand > (ITFConfig.TagDigest.get() ? 1.0D : 0.5D))
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (450.0D * (1.0D + rand)), 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
            } else {
                if (contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    if (rand > (ITFConfig.TagDigest.get() ? 1.0D : 0.2D))
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
                if (contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if (rand > (ITFConfig.TagDigest.get() ? 1.0D : 0.8D))
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
            }
            if (contains(Material.milk))
                player.clearActivePotions();
            if (!contains(Material.water) && !contains(Material.milk)) {
                ((ITFPlayer)player).getFeastManager().update(this);
            }
            player.addFoodValue(this);
            if (isEatable(item_stack))
                world.playSoundAtEntity(player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock())
            if (isEmpty()) {
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
            } else {
                if (rc.getNeighborOfBlockHit() == Block.fire && canContentsDouseFire()) {
                    if (player.onServer()) {
                        rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, (Entity) null);
                        player.convertOneOfHeldItem(new ItemStack((Item) getEmptyVessel()));
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
                            player.convertOneOfHeldItem(new ItemStack((Item) getEmptyVessel()));
                        return true;
                    }
                }
            }
        return false;
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
            if (contents == Materials.unsafe_water)
                return Items.claybowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.claybowlWaterSwampland;
        }
        return null;
    }

    public int getWater() {
        return this.water;
    }

    public Item setWater(int water) {
        this.water = water;
        return this;
    }
}
