package net.oilcake.mitelros.mixins.item.food;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.FoodDataList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBowl.class)
public abstract class ItemBowlMixin extends ItemVessel implements ITFItem {
    @Inject(method = "<init>(ILnet/minecraft/Material;Ljava/lang/String;)V", at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback) {
        this.itf$SetFoodWater(FoodDataList.bowlFoodWater(this.getContents()));
    }

    @Inject(method = "onItemUseFinish", at = @At("HEAD"))
    private void itfDrink(ItemStack item_stack, World world, EntityPlayer player, CallbackInfo ci) {
        if (player.onServer()) {
            FoodDataList.onWaterDrunk(item_stack.getItem(), player);
            if (!contains(Material.water) && !contains(Material.milk)) {
                player.itf$GetFeastManager().update(this);
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
        if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
            material = Materials.pure_water;
        } else {
            material = Material.water;
        }
        return new ItemStack(this.getPeerForContents(material));
    }

//    @Inject(method = "getPeer", at = @At("HEAD"), cancellable = true)
//    private static void itfPeer(Material vessel_material, Material contents, CallbackInfoReturnable<ItemVessel> cir) {
//        ItemVessel result = Materials.getITFBowl(vessel_material, contents);
//        if (result != null) cir.setReturnValue(result);
//    }
}
