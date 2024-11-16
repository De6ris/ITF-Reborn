package net.oilcake.mitelros.mixins.item.recipes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.CraftingManager;
import net.minecraft.ItemStack;
import net.minecraft.RecipesFood;
import net.minecraft.ShapelessRecipes;
import net.oilcake.mitelros.item.register.ITFRecipesFood;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipesFood.class)
public class RecipesFoodMixin {
    @WrapOperation(method = "addRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 0))
    private ShapelessRecipes mushroomStew(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[2] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 3))
    private ShapelessRecipes cookie1(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[1] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 4))
    private ShapelessRecipes cookie2(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[1] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }
}