package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin({ItemMeat.class})
public class ItemMeatMixin extends ItemFood {
  @Shadow
  public boolean is_cooked;
  
  public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
    if (player.onServer()) {
      Random rand = new Random();
      int outcome = rand.nextInt(((Boolean)Config.Realistic.get()) ? 1 : 2);
      if (!this.is_cooked) {
        if (outcome == (((Boolean) Config.TagDigest.get()).booleanValue() ? 4 : 0))
          player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int)(240.0D * (1.0D + rand.nextDouble())), 0)); 
      } else {
        player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
      } 
    } 
    super.onItemUseFinish(item_stack, world, player);
  }
}
