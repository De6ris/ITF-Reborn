package net.oilcake.mitelros.compat;

import dev.emi.emi.EmiPort;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

public class EnchantReserverOutRecipe implements EmiRecipe {
    final EmiIngredient nugget;
    final EmiIngredient coin;
    int exp;

    public EnchantReserverOutRecipe(EmiIngredient nugget, EmiIngredient coin, int exp) {
        this.nugget = nugget;
        this.coin = coin;
        this.exp = exp;
    }

    public static final Comparator<EmiRecipe> comparatorOut = Comparator.comparingInt(a -> ((EnchantReserverOutRecipe) a).exp);

    @Override
    public EmiRecipeCategory getCategory() {
        return RecipeCategory.EnchantReserverOut;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return null;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(this.nugget);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.coin.getEmiStacks();
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
        widgets.addSlot(this.nugget, 4, 0).recipeContext(this);
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 26, 0);
        widgets.addSlot(this.coin, 54, 0).recipeContext(this);
        widgets.addText(EmiPort.translatable("itf.exp.consumes", this.exp), 78, 5, -1, true);
    }
}
