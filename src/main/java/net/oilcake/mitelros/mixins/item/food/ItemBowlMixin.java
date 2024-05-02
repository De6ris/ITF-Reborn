package net.oilcake.mitelros.mixins.item.food;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBowl.class)
public abstract class ItemBowlMixin extends ItemVessel implements ITFItem {
    @Inject(method = "<init>(ILnet/minecraft/Material;Ljava/lang/String;)V", at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback) {
        if (contains(Material.water) || contains(Material.cereal) || contains(Material.ice_cream) || contains(Material.milk)) {
            setFoodWater(2);
        } else if (contains(Materials.dangerous_water) || contains(Materials.suspicious_water)) {
            setFoodWater(1);
        } else if (contains(Material.mashed_potato) || contains(Materials.salad)) {
            setFoodWater(0);
        } else if (contains(Materials.beetroot)) {
            setFoodWater(6);
        } else if (!this.isEmpty()) {
            setFoodWater(4);
        }

        if (contains(Material.water) || contains(Materials.dangerous_water)
                || contains(Materials.suspicious_water) || contains(Material.milk)
                || contains(Materials.lemonade)) {
            this.setFoodTemperature(-1);
        } else if (contains(Materials.ice_cream) || contains(Materials.sorbet)) {
            this.setFoodTemperature(-2);
        } else if (contains(Materials.porkchop_stew) || contains(Materials.chestnut_soup)
                || contains(Material.beef_stew) || contains(Material.chicken_soup)
                || contains(Materials.beetroot) || contains(Material.vegetable_soup)
                || contains(Materials.fish_soup) || contains(Material.mushroom_stew)
                || contains(Material.cream_of_mushroom_soup) || contains(Material.cream_of_vegetable_soup)) {
            this.setFoodTemperature(3);
        } else if (contains(Materials.hot_water) || contains(Material.porridge) || contains(Materials.cereal)) {
            this.setFoodTemperature(2);
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
                if (contains(Materials.suspicious_water)) {
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
                if (contains(Materials.suspicious_water)) {
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

    @ModifyArg(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;convertOneOfHeldItem(Lnet/minecraft/ItemStack;)V", ordinal = 0))
    private ItemStack itfWaterBowl(ItemStack created_item_stack, @Local RaycastCollision rc) {
        BiomeGenBase biome = rc.world.getBiomeGenForCoords(rc.block_hit_x, rc.block_hit_z);
        Material material;
        if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) material = Materials.dangerous_water;
        else if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) material = Material.water;
        else material = Materials.suspicious_water;
        return new ItemStack(this.getPeerForContents(material));
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
            if (contents == Materials.suspicious_water)
                return Items.bowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.bowlWaterSwampland;
        }
        return null;
    }
}
