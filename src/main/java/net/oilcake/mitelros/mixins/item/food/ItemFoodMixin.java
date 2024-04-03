package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin({ItemFood.class})
public class ItemFoodMixin extends Item {
    @Inject(method = {"<init>(ILnet/minecraft/Material;IIZZZLjava/lang/String;)V"}, at = {@At("RETURN")})
    private void injectInit(int id, Material material, int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture, CallbackInfo callbackInfo) {
      ((ITFItem)this).setWater(resetWaterVal(id, material));
    }

    @Inject(method = {"<init>(ILnet/minecraft/Material;IIIZZZLjava/lang/String;)V"}, at = {@At("RETURN")})
    private void injectInit(int id, Material material, int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture, CallbackInfo callbackInfo) {
      ((ITFItem)this).setWater(resetWaterVal(id, material));
    }

    public int resetWaterVal(int id, Material material) {
        if (material == Material.fruit)
            return ((Boolean) Config.TagDryDilemma.get()) ? 1 : 2;
        if (id == 135)
            return ((Boolean) Config.TagDryDilemma.get()) ? 1 : 2;
        if (material == Materials.glowberries)
            return 1;
        if (material == Material.cheese || id == 88)
            return -1;
        if (material == Material.bread || material == Material.desert)
            return -1;
        if (material == Materials.agave)
            return 1;
        return 0;
    }

    @Shadow
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if (this.itemID == rottenFlesh.itemID)
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
            if (hasMaterial(Material.bread) || hasMaterial(Material.desert))
                player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
            if (hasMaterial(Materials.glowberries)) {
                Random rand = new Random();
                if (rand.nextDouble() > (((Boolean) Config.TagDryDilemma.get()) ? 0.5D : 1.0D))
                    player.addWater(-1);
            }
            if (hasMaterial(Materials.agave)) {
                Random rand = new Random();
                if (rand.nextDouble() > (((Boolean) Config.TagDryDilemma.get()) ? 0.2D : 0.4D))
                    player.addWater(-1);
            }
            if (this.itemID == Item.egg.itemID) {
                Random rand = new Random();
                if (rand.nextDouble() > (((Boolean) Config.TagDryDilemma.get()).booleanValue() ? 0.5D : 0.25D))
                    player.addWater(1);
            }
            player.addFoodValue(this);
            world.playSoundAtEntity((Entity) player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
            onEaten(item_stack, world, player);
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
