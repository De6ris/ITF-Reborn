package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBowl.class)
public abstract class ItemBowlMixin extends ItemVessel implements ITFItem {
    @Inject(method = "<init>(ILnet/minecraft/Material;Ljava/lang/String;)V", at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback) {
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

    @Inject(method = "onItemUseFinish", at = @At("HEAD"))
    private void itfDrink(ItemStack item_stack, World world, EntityPlayer player, CallbackInfo ci) {
        if (player.onServer()) {
            if (ITFConfig.Realistic.get()) {
                if (contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (450.0D * (1.0D + rand)), 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
                if (contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if (rand > 0.5D)
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (450.0D * (1.0D + rand)), 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
            } else {
                if (contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    if (rand > 0.2D)
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
                if (contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if (rand > 0.8D)
                        player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
                }
            }
            if (!contains(Material.water) && !contains(Material.milk)) {
                player.getFeastManager().update(this);
            }
        }
    }

    public ItemBowlMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
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
                        player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Materials.unsafe_water)));
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
                if (contains(Material.water) || contains(Materials.unsafe_water) || contains(Materials.dangerous_water)) {
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

    @Inject(method = "getPeer", at = @At("HEAD"), cancellable = true)
    private static void itfPeer(Material vessel_material, Material contents, CallbackInfoReturnable<ItemVessel> cir) {
        ItemVessel result = itfPeer(vessel_material, contents);
        if (result != null) cir.setReturnValue(result);
    }

    @Unique
    private static ItemVessel itfPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.wood) {
            if (contents == Materials.chestnut_soup)
                return Items.bowlChestnutSoup;
            if (contents == Materials.porkchop_stew)
                return Items.bowlPorkchopStew;
            if (contents == Materials.unsafe_water)
                return Items.bowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.bowlWaterSwampland;
        }
        return null;
    }
}
