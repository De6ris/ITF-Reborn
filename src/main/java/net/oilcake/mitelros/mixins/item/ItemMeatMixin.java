package net.oilcake.mitelros.mixins.item;

import java.util.Random;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemFood;
import net.minecraft.ItemMeat;
import net.minecraft.ItemStack;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemMeat.class})
public class ItemMeatMixin extends ItemFood {
  @Shadow
  public boolean is_cooked;
  
  public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
    if (player.onServer()) {
      Random rand = new Random();
      int outcome = rand.nextInt(((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue).booleanValue() ? 1 : 2);
      if (!this.is_cooked) {
        if (outcome == (((Boolean)StuckTagConfig.TagConfig.TagDigest.ConfigValue).booleanValue() ? 4 : 0))
          player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int)(240.0D * (1.0D + rand.nextDouble())), 0)); 
      } else {
        player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
      } 
    } 
    super.onItemUseFinish(item_stack, world, player);
  }
}
