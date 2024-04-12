package net.oilcake.mitelros.mixins.entity.villager;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Mixin(EntityVillager.class)
public class EntityVillagerMixin extends EntityAgeable implements IMerchant, INpc {
    private MerchantRecipeList buyingList;

    private float field_82191_bN;

    public EntityVillagerMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public void setProfession(int par1) {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        setProfession(1 + this.worldObj.rand.nextInt(14));
        return par1EntityLivingData;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void addDefaultEquipmentAndRecipies(int par1) {
        int var6, var3[], var4[], var5;
        if (this.buyingList != null) {
            this.field_82191_bN = MathHelper.sqrt_float(this.buyingList.size()) * 0.2F;
        } else {
            this.field_82191_bN = 0.0F;
        }
        MerchantRecipeList var2 = new MerchantRecipeList();
        switch (getProfession()) {
            case 1:
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.wheat, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.carrot, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.potato, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.onion, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Items.beetroot, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.4F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.chickenRaw, 10 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chickenCooked.itemID, 5 + this.rand.nextInt(3), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.9F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bread.itemID, 6 + this.rand.nextInt(3), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.melon.itemID, 4 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.pumpkin.blockID, 1 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.appleGold.itemID, 2 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.goldenCarrot.itemID, 2 + this.rand.nextInt(2), 0)));
                break;
            case 2:
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Block.cloth.blockID, 7 + this.rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack((Item) Item.shears, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Item.shearsCopper, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 1)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 2)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 3)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 4)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 5)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 6)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 7)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 8)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 9)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 10)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 11)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 12)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 13)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 14)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 15)));
                break;
            case 3:
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Block) Block.gravel, 4), new ItemStack(Item.emerald), new ItemStack(Item.flint.itemID, 4 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.chipFlint, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowFlint.itemID, 4, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.copperNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowCopper.itemID, 4, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.ironNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowIron.itemID, 4, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.silverNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowSilver.itemID, 4, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Item.bow, 1, 15)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + this.rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                break;
            case 4:
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.pumpkinPie.itemID, 1 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Items.lemonPie.itemID, 1 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Items.cake.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.9F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.cookie.itemID, 6 + this.rand.nextInt(3), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.9F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chocolate.itemID, 6 + this.rand.nextInt(3), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.dough, 4 + this.rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.sugar, 15 + this.rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack((Item) Item.bowlSorbet, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack((Item) Item.bowlIceCream, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack((Item) Items.bowlLemonade, 2, 0)));
                break;
            case 5:
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack((Item) Items.ignitionIron, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Items.ignitionCopper, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Item.bowlMashedPotato, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack((Item) Item.bowlCereal, 2, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Item.bowlVegetableSoup, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack((Item) Item.bowlChickenSoup, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack((Item) Items.bowlPorkchopStew, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.bowlEmpty, 4), new ItemStack(Item.shardEmerald, 1, 0)));
                break;
            case 6:
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack((Item) Item.fishingRodIron, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack((Item) Items.fishingRodCopper, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.wormRaw, 14 + this.rand.nextInt(2)), new ItemStack(Item.emerald, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.fishRaw, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishCooked.itemID, 7 + this.rand.nextInt(3), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack((Item) Item.fishLargeRaw, 12 + this.rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishLargeCooked.itemID, 7 + this.rand.nextInt(3), 0)));
                break;
            case 7:
                if (this.rand.nextFloat() < adjustProbability(0.07F)) {
                    Enchantment var8 = Enchantment.enchantmentsBookList[this.rand.nextInt(Enchantment.enchantmentsBookList.length)];
                    int var10 = MathHelper.getRandomIntegerInRange(this.rand, 1, var8.getNumLevels());
                    ItemStack var11 = Item.enchantedBook.getEnchantedItemStack(new EnchantmentData(var8, var10));
                    int i = 2 + this.rand.nextInt(5 + var10 * 2) + 3 * var10;
                    var2.add(new MerchantRecipe(new ItemStack(Item.book), new ItemStack(Item.emerald, i), var11));
                }
                if (this.rand.nextFloat() < adjustProbability(0.8F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.paper, 24 + this.rand.nextInt(10)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.8F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.book, 6 + this.rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.8F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + this.rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Block.bookShelf, 1, 0)));
                break;
            case 8:
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.compass, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.pocketSundial, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.2F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Block.glass, 3, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.8F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.writtenBook, 1), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.15F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 7), new ItemStack(Item.nameTag, 1, 0)));
                break;
            case 9:
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 1), new ItemStack(Item.expBottle.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 3), new ItemStack(Item.eyeOfEnder.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.rottenFlesh, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.bone, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.1F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + this.rand.nextInt(3)), new ItemStack(Items.totemoffecund.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.1F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + this.rand.nextInt(3)), new ItemStack(Items.totemofhunting.itemID, 1, 0)));
                var3 = new int[]{Items.nickelSword.itemID, Items.nickelPickaxe.itemID, Item.swordIron.itemID, Item.pickaxeIron.itemID};
                var4 = var3;
                var5 = var3.length;
                var6 = 0;
                while (var6 < var5) {
                    int var7 = var4[var6];
                    if (this.rand.nextFloat() < adjustProbability(0.05F))
                        var2.add(new MerchantRecipe(new ItemStack(var7, 1, 0), new ItemStack(Item.emerald, 1 + this.rand.nextInt(2), 0), EnchantmentHelper.addRandomEnchantment(this.rand, new ItemStack(var7, 1, 0), 5 + this.rand.nextInt(15))));
                    var6++;
                }
                break;
            case 10:
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.netherStalkSeeds, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.4F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.redstone, 5 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.glowstone, 5 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2 + this.rand.nextInt(3)), new ItemStack(Item.potion.itemID, 1, 32696)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.redstone.itemID, 2 + this.rand.nextInt(2), 0)));
                if (this.rand.nextFloat() < adjustProbability(0.3F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.glowStone.blockID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.spiderEye, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.25F))
                    var2.add(new MerchantRecipe(new ItemStack(Block.mushroomBrown.blockID, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                break;
            case 11:
                addMerchantItem(var2, Item.coal.itemID, this.rand, adjustProbability(0.7F));
                addMerchantItem(var2, Item.ingotIron.itemID, this.rand, adjustProbability(0.5F));
                addMerchantItem(var2, Item.ingotGold.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.swordIron.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.axeIron.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.pickaxeIron.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.shovelIron.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.hoeIron.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.pickaxeCopper.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.shovelCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.axeCopper.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.hoeCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.daggerCopper.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.swordCopper.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.daggerIron.itemID, this.rand, adjustProbability(0.5F));
                break;
            case 12:
                addMerchantItem(var2, Item.coal.itemID, this.rand, adjustProbability(0.7F));
                addMerchantItem(var2, Item.ingotIron.itemID, this.rand, adjustProbability(0.5F));
                addMerchantItem(var2, Item.ingotGold.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.helmetCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.plateCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.legsCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.bootsCopper.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.helmetChainCopper.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateChainCopper.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.legsChainCopper.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.bootsChainCopper.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.helmetChainIron.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateChainIron.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.legsChainIron.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.bootsChainIron.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.helmetIron.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.plateIron.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.legsIron.itemID, this.rand, adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.bootsIron.itemID, this.rand, adjustProbability(0.2F));
                break;
            case 13:
                addMerchantItem(var2, Item.coal.itemID, this.rand, adjustProbability(0.7F));
                addMerchantItem(var2, Item.porkRaw.itemID, this.rand, adjustProbability(0.5F));
                addMerchantItem(var2, Item.beefRaw.itemID, this.rand, adjustProbability(0.5F));
                addMerchantItem(var2, Item.lambchopRaw.itemID, this.rand, adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.porkCooked.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.beefCooked.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.lambchopCooked.itemID, this.rand, adjustProbability(0.3F));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Items.horse_meat, 12 + this.rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                if (this.rand.nextFloat() < adjustProbability(0.5F))
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Items.horse_meat_cooked.itemID, 5 + this.rand.nextInt(2), 0)));
                break;
            case 14:
                addBlacksmithItem(var2, Item.saddle.itemID, this.rand, adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateLeather.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.bootsLeather.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.helmetLeather.itemID, this.rand, adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.legsLeather.itemID, this.rand, adjustProbability(0.3F));
                if (this.rand.nextFloat() < adjustProbability(0.8F))
                    var2.add(new MerchantRecipe(new ItemStack(Items.leather, 14 + this.rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                break;
        }
        if (var2.isEmpty())
            var2.add(new MerchantRecipe(new ItemStack(Item.ingotGold, 8), new ItemStack(Items.emerald.itemID, 1, 0)));
        Collections.shuffle((List<?>) var2);
        if (this.buyingList == null)
            this.buyingList = new MerchantRecipeList();
        for (int var9 = 0; var9 < par1 && var9 < var2.size(); var9++)
            this.buyingList.addToListWithCheck((MerchantRecipe) var2.get(var9));
    }

    @Shadow
    private float adjustProbability(float par1) {
        return par1;
    }

    @Shadow
    private static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
    }

    @Shadow
    private static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
    }

    @Shadow
    private int getProfession() {
        return 0;
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @Shadow
    public void setCustomer(EntityPlayer entityPlayer) {
    }

    @Shadow
    public EntityPlayer getCustomer() {
        return null;
    }

    @Shadow
    public MerchantRecipeList getRecipes(EntityPlayer entityPlayer) {
        return null;
    }

    @Shadow
    public void setRecipes(MerchantRecipeList merchantRecipeList) {
    }

    @Shadow
    public void useRecipe(MerchantRecipe merchantRecipe) {
    }

    @Shadow
    public void func_110297_a_(ItemStack itemStack) {
    }
}
