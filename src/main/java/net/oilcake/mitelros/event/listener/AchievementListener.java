package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IAchievementListener;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.ItemBowlClay;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.Constant;

import static net.minecraft.AchievementList.fineDining;

public class AchievementListener implements IAchievementListener {
    @Override
    public void onItemCrafted(EntityPlayer player, ItemStack par1ItemStack) {
        Item item = par1ItemStack.getItem();
        Block block = (item instanceof ItemBlock) ? ((ItemBlock) item).getBlock() : null;
        if (item == Items.leggingsAncientMetalSacred || item == Items.chestplateAncientMetalSacred || item == Items.helmetAncientMetalSacred || item == Items.bootsAncientMetalSacred)
            player.addStat(AchievementExtend.forgingLegend, 1);
        if (item == Items.forgingNote)
            player.addStat(AchievementExtend.copying, 1);
        if (item == Items.bowTungsten)
            player.addStat(AchievementExtend.Arbalistic, 1);
        if (block == Block.beacon)
            player.addStat(AchievementExtend.getBeacon, 1);
        if (block == Blocks.uruBeacon)
            player.addStat(AchievementExtend.obtainUruBeacon, 1);
        if (item == Items.mashedCactus)
            player.addStat(AchievementList.seeds, 1);
        if (item == Items.glowberries || item == Items.agave)
            player.addStat(AchievementExtend.mashedCactus, 1);
        if (item == Items.pulque || item == Items.ale)
            player.addStat(AchievementExtend.cheersforMinecraft, 1);
        if (item == Items.experimentalPotion)
            player.addStat(AchievementExtend.nochoice, 1);
        if (item instanceof ItemBowlClay && (item == Items.clayBowlSalad || ItemBowlClay.isSoupOrStew(item))) {
            player.triggerAchievement(fineDining);
        }
    }

    @Override
    public void onItemSmelt(EntityPlayer player, ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (item == Items.uruIngot || item == Items.uruNugget) {
            player.triggerAchievement(AchievementExtend.neverEnds);
        }
    }

    @Override
    public void onItemPickUp(EntityPlayer player, ItemStack itemStack) {
        if (itemStack.itemID == Items.pieceCopper.itemID || itemStack.itemID == Items.pieceGold.itemID || itemStack.itemID == Items.pieceSilver.itemID || itemStack.itemID == Items.pieceIron.itemID)
            player.triggerAchievement(AchievementExtend.FragofMine);
        if (itemStack.itemID == Items.pieceCopper.itemID || itemStack.itemID == Items.pieceGold.itemID || itemStack.itemID == Items.pieceSilver.itemID || itemStack.itemID == Items.pieceIron.itemID)
            player.triggerAchievement(AchievementExtend.FragofMine);
        if (itemStack.itemID == Item.recordUnderworld.itemID || itemStack.itemID == Item.recordDescent.itemID || itemStack.itemID == Item.recordWanderer.itemID || itemStack.itemID == Item.recordLegends.itemID)
            player.triggerAchievement(AchievementExtend.SoundofUnder);
        if (itemStack.itemID == Item.skull.itemID)
            player.triggerAchievement(AchievementExtend.getWitherSkull);
    }

    @Override
    public void onDimensionTravel(EntityPlayer player, int currentDimension, int destinationDimension) {
        if (currentDimension == 1 && destinationDimension == 1) {
            if (Constant.calculateCurrentDifficulty() >= 12) {
                player.triggerAchievement(AchievementExtend.stormStriker);
            }
        }
    }
}
