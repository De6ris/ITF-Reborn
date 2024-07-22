package net.oilcake.mitelros.item.potion;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;

public class ItemPotionExperimental extends Item {
    public ItemPotionExperimental(int id) {
        super(id, Material.glass, "experimental_potion");
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(25.0F);
        setCreativeTab(CreativeTabs.tabMisc);
        ((ITFItem) this).itf$SetFoodWater(3);
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        double rand = Math.random();
        if (player.onServer()) {
            if (rand < 0.25D)
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 800, 0));
            if (rand > 0.15D && rand < 0.7D)
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 800, 0));
            if (rand > 0.4D && rand < 0.95D)
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 800, 0));
            if (rand > 0.9D)
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 400, 0));
            ((ITFPlayer) player).itf$AddWater(((ITFItem) this).itf$GetFoodWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    public Item getItemProducedOnItemUseFinish() {
        return glassBottle;
    }
}
