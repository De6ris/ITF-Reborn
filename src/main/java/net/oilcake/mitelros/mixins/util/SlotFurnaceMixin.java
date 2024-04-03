package net.oilcake.mitelros.mixins.util;

import net.minecraft.AchievementList;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityXPOrb;
import net.minecraft.IInventory;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.SlotCraftingBase;
import net.minecraft.SlotFurnace;
import net.minecraft.StatBase;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({SlotFurnace.class})
public class SlotFurnaceMixin extends SlotCraftingBase {
  public SlotFurnaceMixin(EntityPlayer player, IInventory inventory, int slot_index, int display_x, int display_y) {
    super(player, inventory, slot_index, display_x, display_y);
  }
  
  @Overwrite
  protected void onCrafting(ItemStack item_stack) {
    if (!this.player.worldObj.isRemote) {
      int xp_reward = item_stack.getExperienceReward(this.quantity_taken);
      if (xp_reward > 0)
        this.player.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.player.worldObj, this.player.posX, this.player.posY + 0.5D, this.player.posZ + 0.5D, xp_reward)); 
    } 
    super.onCrafting(item_stack);
    Item item = item_stack.getItem();
    if (item == Item.ingotIron || item == Item.ironNugget) {
      this.player.addStat((StatBase)AchievementList.acquireIron, 1);
    } else if (item != Item.fishCooked && item != Item.fishLargeCooked) {
      if (item == Item.bread) {
        this.player.addStat((StatBase)AchievementList.makeBread, 1);
      } else if (item == Item.ingotMithril || item == Item.mithrilNugget) {
        this.player.triggerAchievement((StatBase)AchievementList.mithrilIngot);
      } else if (item == Item.ingotAdamantium || item == Item.adamantiumNugget) {
        this.player.triggerAchievement((StatBase)AchievementList.adamantiumIngot);
      } else if (item == Items.UruIngot || item == Items.UruNugget) {
        this.player.triggerAchievement((StatBase)AchievementExtend.neverEnds);
      } 
    } else {
      this.player.addStat((StatBase)AchievementList.cookFish, 1);
    } 
  }
}
