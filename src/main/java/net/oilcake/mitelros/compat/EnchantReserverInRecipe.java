package net.oilcake.mitelros.compat;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.EmiPort;
import net.minecraft.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

public class EnchantReserverInRecipe implements EmiRecipe {

    final EmiIngredient item;

    final int exp;

    public EnchantReserverInRecipe(EmiIngredient item, int exp) {
        this.item = item;
        this.exp = exp;
    }

    public static final Comparator<EmiRecipe> comparatorIn = Comparator.comparingInt(a -> ((EnchantReserverInRecipe) a).exp);

    @Override
    public EmiRecipeCategory getCategory() {
        return RecipeCategory.EnchantReserverIn;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return null;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(this.item);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of();
    }

    @Override
    public int getDisplayWidth() {
        return 144;
    }

    @Override
    public int getDisplayHeight() {
        return 18;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(this.item, 18, 0).recipeContext(this);
        widgets.addText(EmiPort.translatable("itf.exp.provides", this.exp), 38, 5, -1, true);
    }
}
