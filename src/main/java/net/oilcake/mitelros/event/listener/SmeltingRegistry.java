package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.SmeltingRecipeRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.SmeltingHandler;
import net.minecraft.*;
import net.oilcake.mitelros.item.ItemKettle;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import net.oilcake.mitelros.item.register.RecipeRegister;

import java.util.function.Consumer;

public class SmeltingRegistry implements Consumer<SmeltingRecipeRegisterEvent> {
    @Override
    public void accept(SmeltingRecipeRegisterEvent event) {
        RecipeRegister.registerFurnaceRecipes(event);
        registerBlastFurnaceRecipes(event);
        event.registerSpecial((itemStack, heatLevel) -> itemStack.getItem() instanceof ItemArmor armor ? SmeltingHandler.result(recycleArmors(itemStack, armor)) : null);
        event.registerSpecial((itemStack, heatLevel) -> itemStack.getItem() instanceof ItemTool tool ? SmeltingHandler.result(recycleArmors(itemStack, tool)) : null);
        event.registerSpecial((itemStack, heatLevel) -> itemStack.getItem() == Items.clayBowlRaw && itemStack.stackSize >= 4 ? SmeltingHandler.result(4, new ItemStack(Items.clayBowlEmpty, 4)) : null);
        event.registerSpecial((itemStack, heatLevel) -> itemStack.getItem() instanceof ItemKettle ? SmeltingHandler.result(ItemKettle.boil(itemStack)) : null);
    }


    private static void registerBlastFurnaceRecipes(SmeltingRecipeRegisterEvent event) {
        Class<?>[] tools = {
                ItemSword.class, ItemAxe.class, ItemPickaxe.class, ItemHoe.class, ItemShovel.class,
                ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class,
                ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class};
        Class<?>[] armors = {
                ItemHelmet.class, ItemCuirass.class, ItemLeggings.class, ItemBoots.class
        };
        Material[] available_material = {Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Materials.tungsten, Material.ancient_metal, Material.rusted_iron};

        for (Material material : available_material) {
            for (Class<?> tool : tools) {
                Item toolItem = Item.getMatchingItem(tool, material);
                registerRecipeSafe(event, toolItem, armorItemStack(material, 1, toolItem.getRepairItem()));
            }
            for (Class<?> armor : armors) {
                ItemArmor matchingArmor = ItemArmor.getMatchingArmor(armor, material, false);
                registerRecipeSafe(event, matchingArmor, armorItemStack(material, 1, matchingArmor.getRepairItem()));
                ItemArmor matchingArmorChain = ItemArmor.getMatchingArmor(armor, material, true);
                registerRecipeSafe(event, matchingArmorChain, armorItemStack(material, 1, matchingArmorChain.getRepairItem()));
            }
        }
    }

    private static void registerRecipeSafe(SmeltingRecipeRegisterEvent event, Item item, ItemStack itemStack) {
        if (item != null && itemStack != null) {
            event.register(item.itemID, itemStack);
        }
    }

    public static <T extends Item & IDamageableItem> ItemStack recycleArmors(ItemStack input_item_stack, T armor) {
        float ingotToNugget = input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F;
        float durabilityRatio = (input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float) input_item_stack.getMaxDamage();
        float component = armor.getNumComponentsForDurability();
        int quantity = (int) (durabilityRatio * component * ingotToNugget / 3.0F);
        return armorItemStack(armor.getHardestMetalMaterial(), quantity, armor.getRepairItem());
    }

    private static ItemStack armorItemStack(Material hardestMaterial, int quantity, Item repairItem) {
        ItemStack output;
        if (hardestMaterial == Material.rusted_iron) {
            quantity /= 3;
            output = new ItemStack(repairItem, quantity);
        } else {
            output = new ItemStack(repairItem, quantity);
        }
        if (quantity == 0) {
            output.setStackSize(1);
        }
        return output;
    }
}
