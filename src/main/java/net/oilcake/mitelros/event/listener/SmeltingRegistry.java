package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.SmeltingRecipeRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.SmeltingHandler;
import net.minecraft.*;
import net.oilcake.mitelros.item.ItemKettle;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

import static net.oilcake.mitelros.block.Blocks.*;
import static net.oilcake.mitelros.item.Items.*;

public class SmeltingRegistry implements Consumer<SmeltingRecipeRegisterEvent> {
    @Override
    public void accept(SmeltingRecipeRegisterEvent event) {
        event.register(pieceAdamantium.itemID, new ItemStack(adamantiumNugget));
        event.register(pieceCopper.itemID, new ItemStack(copperNugget));
        event.register(pieceGold.itemID, new ItemStack(goldNugget));
        event.register(pieceGoldNether.itemID, new ItemStack(goldNugget));
        event.register(pieceSilver.itemID, new ItemStack(silverNugget));
        event.register(pieceIron.itemID, new ItemStack(ironNugget));
        event.register(pieceNickel.itemID, new ItemStack(nickelNugget));
        event.register(pieceMithril.itemID, new ItemStack(mithrilNugget));
        event.register(pieceTungsten.itemID, new ItemStack(tungstenNugget));
        event.register(pieceUru.itemID, new ItemStack(uruNugget));
        event.register(ancientMetalArmorPiece.itemID, new ItemStack(ancientMetalNugget));

        event.register(clayBowlWater.itemID, new ItemStack(clayBowlWaterPure));
        event.register(bowlWater.itemID, new ItemStack(bowlWaterPure));
        event.register(suspiciousPotion.itemID, new ItemStack(potion, 1, 0));
        event.register(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        event.register(clayBowlRaw.itemID, new ItemStack(clayBowlEmpty));// TODO remove when ric 1.3.7
        event.register(leatherKettle.itemID, new ItemStack(leatherKettlePure));//
        event.register(hardenedClayJug.itemID, new ItemStack(hardenedClayJugPure));//
        event.register(clayJug.itemID, new ItemStack(hardenedClayJug).setItemDamage(hardenedClayJug.maxDamage - 1));

        ItemFood.setCookingResult((ItemFood) horse_meat, (ItemFood) horse_meat_cooked, 6);

        event.register(oreTungsten.blockID, new ItemStack(Items.tungstenIngot));
        event.register(oreNickel.blockID, new ItemStack(Items.nickelIngot));
        event.register(oreUru.blockID, new ItemStack(Items.uruIngot));

        registerBlastFurnaceRecipes(event);

        event.registerSpecial((itemStack, heatLevel) -> {
            Class<? extends Item> clazz = itemStack.getItem().getClass();
            if (tools.contains(clazz) || armors.contains(clazz)) {
                return SmeltingHandler.result(recycleArmors(itemStack, (Item & IDamageableItem) itemStack.getItem()));
            }
            return null;
        });

        event.registerSpecial((itemStack, heatLevel) -> itemStack.getItem() == Items.clayBowlRaw && itemStack.stackSize >= 4 ? SmeltingHandler.result(4, new ItemStack(Items.clayBowlEmpty, 4)) : null);
        event.registerSpecial((itemStack, heatLevel) -> {
            if (itemStack.getItem() instanceof ItemKettle itemKettle) {
                if (itemKettle.canBoil()) {
                    return SmeltingHandler.result(itemKettle.onBoil(itemStack));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        });
    }


    private static final List<Class<? extends IDamageableItem>> tools = List.of(
            ItemSword.class, ItemAxe.class, ItemPickaxe.class, ItemHoe.class, ItemShovel.class,
            ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class,
            ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class);

    private static final List<Class<? extends IDamageableItem>> armors = List.of(
            ItemHelmet.class, ItemCuirass.class, ItemLeggings.class, ItemBoots.class
    );

    private static void registerBlastFurnaceRecipes(SmeltingRecipeRegisterEvent event) {
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

    @Nullable
    public static <T extends Item & IDamageableItem> ItemStack recycleArmors(ItemStack input_item_stack, T armor) {
        Item repairItem = armor.getRepairItem();
        if (repairItem == null) return null;
        float ingotToNugget = input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F;
        float durabilityRatio = (input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float) input_item_stack.getMaxDamage();
        float component = armor.getNumComponentsForDurability();
        int quantity = (int) (durabilityRatio * component * ingotToNugget / 3.0F);
        return armorItemStack(armor.getHardestMetalMaterial(), quantity, repairItem);
    }

    private static ItemStack armorItemStack(Material hardestMaterial, int quantity, @Nonnull Item repairItem) {
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
