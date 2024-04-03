package net.oilcake.mitelros.mixins.world;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.StructureMineshaftPieces;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({StructureMineshaftPieces.class})
public class WorldGenMineshaftPiecesMixin {
  private static final WeightedRandomChestContent[] mineshaftChestContents = new WeightedRandomChestContent[] { 
      new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.carrot.itemID, 0, 1, 2, 2), new WeightedRandomChestContent(Item.potato.itemID, 0, 1, 2, 2), new WeightedRandomChestContent(Item.onion.itemID, 0, 1, 2, 2), new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 2), new WeightedRandomChestContent(Item.cheese.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Block.mushroomBrown.blockID, 0, 1, 3, 5), new WeightedRandomChestContent(Block.mushroomRed.blockID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), 
      new WeightedRandomChestContent(Item.copperNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.silverNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.ingotCopper.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.ingotSilver.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.chainRustedIron.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Items.lapis.itemID, 0, 2, 5, 5), new WeightedRandomChestContent(Item.redstone.itemID, 0, 2, 5, 5), 
      new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 6, 5), new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 4, 3), new WeightedRandomChestContent(Item.emerald.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 2, 1), new WeightedRandomChestContent(Item.coal.itemID, 0, 2, 5, 10), new WeightedRandomChestContent(Item.bootsLeather.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Block.torchWood.blockID, 0, 1, 6, 10), new WeightedRandomChestContent(Item.flintAndSteel.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Items.ignitionCopper.itemID, 0, 1, 1, 2), 
      new WeightedRandomChestContent(Items.ignitionSilver.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Block.tnt.blockID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bucketCopperEmpty.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.bucketIronEmpty.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.shovelWood.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.shovelRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.hatchetRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.hatchetCopper.itemID, 0, 1, 1, 2), 
      new WeightedRandomChestContent(Item.hatchetIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.axeRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.axeCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.axeIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.mattockRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.mattockCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.mattockIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.pickaxeRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.pickaxeCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.pickaxeIron.itemID, 0, 1, 1, 1), 
      new WeightedRandomChestContent(Item.warHammerRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.warHammerCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.warHammerIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Block.rail.blockID, 0, 2, 5, 1) };
}
