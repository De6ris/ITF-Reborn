package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin({FurnaceRecipes.class})
public class RecipesFurnaceMixin {
  @Shadow
  private Map smeltingList = new HashMap<>();
  
  @Overwrite
  public ItemStack getSmeltingResult(ItemStack input_item_stack, int heat_level) {
    ItemStack result_item_stack;
    if (input_item_stack == null)
      return null; 
    int input_item_id = input_item_stack.itemID;
    if (heat_level == -1)
      return (ItemStack)this.smeltingList.get(Integer.valueOf(input_item_id)); 
    if (input_item_stack.getItem() instanceof ItemArmor) {
      ItemStack itemStack;
      int quantity = (int)((input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / input_item_stack.getMaxDamage() * ((ItemArmor)input_item_stack.getItem()).getNumComponentsForDurability() * (input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F));
      if (input_item_stack.getItem().getHardestMetalMaterial() == Material.rusted_iron) {
        quantity /= 3;
        itemStack = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron), quantity);
      } else {
        itemStack = new ItemStack(Item.getMatchingItem(ItemNugget.class, input_item_stack.getItem().getMaterialForRepairs()), quantity);
      } 
      if (quantity == 0)
        itemStack = null; 
      result_item_stack = itemStack;
    } else if (input_item_stack.getItem() instanceof ItemTool) {
      ItemStack itemStack;
      int quantity = (int)((input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / input_item_stack.getMaxDamage() * ((ItemTool)input_item_stack.getItem()).getNumComponentsForDurability() * 9.0F);
      if (input_item_stack.getItem().getHardestMetalMaterial() == Material.rusted_iron) {
        quantity /= 3;
        itemStack = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron), quantity);
      } else {
        itemStack = new ItemStack(Item.getMatchingItem(ItemNugget.class, input_item_stack.getItem().getMaterialForRepairs()), quantity);
      } 
      if (quantity == 0)
        itemStack = null; 
      result_item_stack = itemStack;
    } else if (input_item_id == Items.claybowlRaw.itemID) {
      result_item_stack = (input_item_stack.stackSize >= 4) ? new ItemStack((Item)Items.claybowlEmpty, 4) : null;
    } else if (input_item_id == Block.sand.blockID) {
      if ((heat_level != 1 || input_item_stack.stackSize >= 4) && input_item_stack.stackSize >= 4) {
      
      } else {
      
      } 
      result_item_stack = null;
    } else {
      result_item_stack = (ItemStack)this.smeltingList.get(Integer.valueOf(input_item_id));
    } 
    return (heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID)) ? null : result_item_stack;
  }
}
