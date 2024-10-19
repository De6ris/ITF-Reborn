package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.TradingRegisterEvent;
import moddedmite.rustedironcore.api.util.IdUtilExtra;
import moddedmite.rustedironcore.villager.VillagerSettings;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.function.Consumer;

public class TradingListener implements Consumer<TradingRegisterEvent> {

    private static int nextProfessionID() {
        return IdUtilExtra.getNextVillagerProfessionID();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void accept(TradingRegisterEvent event) {
        for (int i = 0; i < 5; i++) {
            event.getForProfession(i).ifPresent(x -> x.setBanned(true));
        }// ban the original 5 professions

        event.registerProfession(nextProfessionID(), "villager.profession.farmer", VillagerSettings.FarmerTexture)//1 farmer
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.wheat, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.carrot, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.potato, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.onion, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Items.beetroot, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.4F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.chickenRaw, 10 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chickenCooked.itemID, 5 + rand.nextInt(3), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.9F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bread.itemID, 6 + rand.nextInt(3), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.melon.itemID, 4 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.pumpkin.blockID, 1 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.appleGold.itemID, 2 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.goldenCarrot.itemID, 2 + rand.nextInt(2), 0)));
                });

        event.registerProfession(nextProfessionID(), "villager.profession.shepherd", VillagerSettings.FarmerTexture)//2 shepherd
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Block.cloth.blockID, 7 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.shears, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.shearsCopper, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 1)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 2)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 3)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 4)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 5)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 6)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 7)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 8)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 9)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 10)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 11)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 12)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 13)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 14)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + rand.nextInt(2), 15)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.fletcher", VillagerSettings.FarmerTexture)//3 Fletcher
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack((Block) Block.gravel, 4), new ItemStack(Item.emerald), new ItemStack(Item.flint.itemID, 4 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.chipFlint, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowFlint.itemID, 4, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.copperNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowCopper.itemID, 4, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.ironNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowIron.itemID, 4, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.silverNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowSilver.itemID, 4, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bow, 1, 15)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.baker", VillagerSettings.FarmerTexture)//4 baker
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.pumpkinPie.itemID, 1 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Items.lemonPie.itemID, 1 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Items.cake.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.9F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.cookie.itemID, 6 + rand.nextInt(3), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.9F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chocolate.itemID, 6 + rand.nextInt(3), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.dough, 4 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.sugar, 15 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlSorbet, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlIceCream, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Items.bowlLemonade, 2, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.chef", VillagerSettings.FarmerTexture)//5 chef
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.flintAndSteel, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Items.ignitionCopper, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bowlMashedPotato, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlCereal, 2, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bowlVegetableSoup, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.bowlChickenSoup, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Items.bowlPorkchopStew, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.bowlEmpty, 4), new ItemStack(Item.shardEmerald, 1, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.fisherman", VillagerSettings.FarmerTexture)//6 fisherman
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.fishingRodIron, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Items.fishingRodCopper, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.wormRaw, 14 + rand.nextInt(2)), new ItemStack(Item.emerald, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.fishRaw, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishCooked.itemID, 7 + rand.nextInt(3), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.fishLargeRaw, 12 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishLargeCooked.itemID, 7 + rand.nextInt(3), 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.copier", VillagerSettings.LibrarianTexture)//7 copier
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.07F)) {
                        Enchantment var8 = Enchantment.enchantmentsBookList[rand.nextInt(Enchantment.enchantmentsBookList.length)];
                        int var10 = MathHelper.getRandomIntegerInRange(rand, 1, var8.getNumLevels());
                        ItemStack var11 = Item.enchantedBook.getEnchantedItemStack(new EnchantmentData(var8, var10));
                        int i = 2 + rand.nextInt(5 + var10 * 2) + 3 * var10;
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.book), new ItemStack(Item.emerald, i), var11));
                    }
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.8F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.paper, 24 + rand.nextInt(10)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.8F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.book, 6 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.8F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Block.bookShelf, 1, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.professor", VillagerSettings.LibrarianTexture)//8 professor
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.compass, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.pocketSundial, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Block.glass, 3, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.8F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.writtenBook, 1), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.15F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 7), new ItemStack(Item.nameTag, 1, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.priest", VillagerSettings.PriestTexture)//9 priest
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 1), new ItemStack(Item.expBottle.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 3), new ItemStack(Item.eyeOfEnder.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.rottenFlesh, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.bone, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.1F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + rand.nextInt(3)), new ItemStack(Items.totemOfFecund.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.1F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + rand.nextInt(3)), new ItemStack(Items.totemOfHunting.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.2F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Items.ancientMetalArmorPiece.itemID, 12 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }).addEntry((recipeList, villager, rand) -> {
                    int[] var3 = new int[]{Items.nickelSword.itemID, Items.nickelPickaxe.itemID, Item.swordIron.itemID, Item.pickaxeIron.itemID};
                    int var5 = var3.length;
                    int var6 = 0;
                    while (var6 < var5) {
                        int var7 = var3[var6];
                        if (rand.nextFloat() < villager.adjustProbability(0.05F))
                            recipeList.add(new MerchantRecipe(new ItemStack(var7, 1, 0), new ItemStack(Item.emerald, 1 + rand.nextInt(2), 0), EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(var7, 1, 0), 5 + rand.nextInt(15))));
                        var6++;
                    }
                });


        event.registerProfession(nextProfessionID(), "villager.profession.brewer", VillagerSettings.PriestTexture)//10 brewer
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.netherStalkSeeds, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.4F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.redstone, 5 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.glowstone, 5 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 2 + rand.nextInt(3)), new ItemStack(Item.potion.itemID, 1, 32696)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.redstone.itemID, 2 + rand.nextInt(2), 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.3F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.glowStone.blockID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.spiderEye, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.25F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Block.mushroomBrown.blockID, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.toolsmith", VillagerSettings.SmithTexture)//11 toolsmith
                .buyEntry(Item.coal.itemID, 0.7F)
                .buyEntry(Item.ingotIron.itemID, 0.5F)
                .buyEntry(Item.ingotGold.itemID, 0.5F)
                .sellEntry(Item.swordIron.itemID, 0.5F)
                .sellEntry(Item.axeIron.itemID, 0.3F)
                .sellEntry(Item.pickaxeIron.itemID, 0.5F)
                .sellEntry(Item.shovelIron.itemID, 0.2F)
                .sellEntry(Item.hoeIron.itemID, 0.2F)
                .sellEntry(Item.pickaxeCopper.itemID, 0.5F)
                .sellEntry(Item.shovelCopper.itemID, 0.2F)
                .sellEntry(Item.axeCopper.itemID, 0.3F)
                .sellEntry(Item.hoeCopper.itemID, 0.2F)
                .sellEntry(Item.daggerCopper.itemID, 0.5F)
                .sellEntry(Item.swordCopper.itemID, 0.5F)
                .sellEntry(Item.daggerIron.itemID, 0.5F);


        event.registerProfession(nextProfessionID(), "villager.profession.armorer", VillagerSettings.SmithTexture)//12 armorer
                .buyEntry(Item.coal.itemID, 0.7F)
                .buyEntry(Item.ingotIron.itemID, 0.5F)
                .buyEntry(Item.ingotGold.itemID, 0.5F)
                .sellEntry(Item.helmetCopper.itemID, 0.2F)
                .sellEntry(Item.plateCopper.itemID, 0.2F)
                .sellEntry(Item.legsCopper.itemID, 0.2F)
                .sellEntry(Item.bootsCopper.itemID, 0.2F)
                .sellEntry(Item.helmetChainCopper.itemID, 0.1F)
                .sellEntry(Item.plateChainCopper.itemID, 0.1F)
                .sellEntry(Item.legsChainCopper.itemID, 0.1F)
                .sellEntry(Item.bootsChainCopper.itemID, 0.1F)
                .sellEntry(Item.helmetChainIron.itemID, 0.1F)
                .sellEntry(Item.plateChainIron.itemID, 0.1F)
                .sellEntry(Item.legsChainIron.itemID, 0.1F)
                .sellEntry(Item.bootsChainIron.itemID, 0.1F)
                .sellEntry(Item.helmetIron.itemID, 0.2F)
                .sellEntry(Item.plateIron.itemID, 0.2F)
                .sellEntry(Item.legsIron.itemID, 0.2F)
                .sellEntry(Item.bootsIron.itemID, 0.2F);


        event.registerProfession(nextProfessionID(), "villager.profession.butcher", VillagerSettings.ButcherTexture)//13 butcher
                .buyEntry(Item.coal.itemID, 0.7F)
                .buyEntry(Item.porkRaw.itemID, 0.5F)
                .buyEntry(Item.beefRaw.itemID, 0.5F)
                .buyEntry(Item.lambchopRaw.itemID, 0.5F)
                .sellEntry(Item.porkCooked.itemID, 0.3F)
                .sellEntry(Item.beefCooked.itemID, 0.3F)
                .sellEntry(Item.lambchopCooked.itemID, 0.3F)
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Items.horse_meat, 12 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                })
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.5F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Items.horse_meat_cooked.itemID, 5 + rand.nextInt(2), 0)));
                });


        event.registerProfession(nextProfessionID(), "villager.profession.leatherworker", VillagerSettings.ButcherTexture)//14 leatherworker
                .sellEntry(Item.saddle.itemID, 0.1F)
                .sellEntry(Item.plateLeather.itemID, 0.3F)
                .sellEntry(Item.bootsLeather.itemID, 0.3F)
                .sellEntry(Item.helmetLeather.itemID, 0.3F)
                .sellEntry(Item.legsLeather.itemID, 0.3F)
                .addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.8F))
                        recipeList.add(new MerchantRecipe(new ItemStack(Items.leather, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                });

    }
}
