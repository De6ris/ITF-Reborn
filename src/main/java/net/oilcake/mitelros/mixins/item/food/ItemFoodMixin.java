package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemFood.class)
public class ItemFoodMixin extends Item {
    @Inject(method = "<init>(ILnet/minecraft/Material;IIIZZZLjava/lang/String;)V", at = @At("RETURN"))
    private void injectInit(int id, Material material, int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture, CallbackInfo callbackInfo) {
        int water = this.itfFoodWater(id, material);
        if (water != 0) {
            ((ITFItem) this).setFoodWater(water);
        }
        int temperature = this.itfFoodTemperature(material);
        if (temperature != 0) {
            ((ITFItem) this).setFoodTemperature(temperature);
        }
    }

    @Unique
    private int itfFoodWater(int id, Material material) {
        if (material == Material.fruit | id == 135)
            return ITFConfig.TagDryDilemma.get() ? 1 : 2;
        if (material == (Materials.glowberries) || material == Materials.peeledSugarcane || material == Materials.agave || material == Materials.mashedCactus)
            return 1;
        if (material == Material.cheese || id == 88 || material == Material.bread || material == Material.desert)
            return -1;
        if (material == Materials.ice_sucker || material == Materials.melon_ice || material == Materials.chocolate_smoothie)
            return 2;
        return 0;
    }

    @Unique
    private int itfFoodTemperature(Material material) {
        if (material == Material.fruit) return -1;
        if (material == Materials.ice_sucker || material == Materials.melon_ice) return -2;
        if (material == Materials.chocolate_smoothie) return -3;
        return 0;
    }

    @Inject(method = "onItemUseFinish", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;addFoodValue(Lnet/minecraft/Item;)V"))
    private void itfFood(ItemStack item_stack, World world, EntityPlayer player, CallbackInfo ci) {
        Random rand = player.rand;
        if (this.itemID == rottenFlesh.itemID)
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
        if (hasMaterial(Material.bread) || hasMaterial(Material.desert))
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
        if (hasMaterial(Materials.glowberries)) {
            if (rand.nextDouble() > (ITFConfig.TagDryDilemma.get() ? 0.5D : 1.0D))
                player.addWater(-1);
        }
        if (hasMaterial(Materials.agave)) {
            if (rand.nextDouble() > (ITFConfig.TagDryDilemma.get() ? 0.2D : 0.4D))
                player.addWater(-1);
        }
        if (this.itemID == Item.egg.itemID) {
            if (rand.nextDouble() > (ITFConfig.TagDryDilemma.get() ? 0.5D : 0.25D))
                player.addWater(1);
        }
        if (ReflectHelper.dyCast(this) instanceof ItemMeat meat) {
            int outcome = rand.nextInt(ITFConfig.Realistic.get() ? 1 : 4);
            if (meat.is_cooked) {
                player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
                player.addPotionEffect(new PotionEffect(PotionExtend.warm.id, 1280, 0));
            } else {
                if (outcome == (ITFConfig.TagDigest.get() ? 4 : 0))
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (240.0D * (1.0D + rand.nextDouble())), 0));
            }
        }
    }
}
