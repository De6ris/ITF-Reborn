package net.oilcake.mitelros.mixins.item.recipes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.CraftingManager;
import net.minecraft.ItemStack;
import net.minecraft.RecipesMITE;
import net.minecraft.ShapelessRecipes;
import net.oilcake.mitelros.item.register.ITFRecipesFood;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipesMITE.class)
public class RecipesMITEMixin {
    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 10))
    private static ShapelessRecipes dough(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[1] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 11))
    private static ShapelessRecipes beefStew(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[3] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 12))
    private static ShapelessRecipes chickenStew(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[3] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 13))
    private static ShapelessRecipes vegetableSoup(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[3] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 19))
    private static ShapelessRecipes pumpkinSoup(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[1] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }

    @WrapOperation(method = "addCraftingRecipes", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/CraftingManager;addShapelessRecipe(Lnet/minecraft/ItemStack;[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", ordinal = 22))
    private static ShapelessRecipes porridge(CraftingManager instance, ItemStack par1ItemStack, Object[] par2ArrayOfObj, Operation<ShapelessRecipes> original) {
        par2ArrayOfObj[3] = ITFRecipesFood.getWaterBowlForCooking();
        return original.call(instance, par1ItemStack, par2ArrayOfObj);
    }
}