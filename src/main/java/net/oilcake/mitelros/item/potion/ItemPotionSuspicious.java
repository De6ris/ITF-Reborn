package net.oilcake.mitelros.item.potion;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Config;

public class ItemPotionSuspicious extends Item {
    public ItemPotionSuspicious(int id) {
        super(id, Material.glass, "suspicious_potion");
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(25.0F);
        setCreativeTab(CreativeTabs.tabMisc);
        ((ITFItem) this).setWater(1);
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        double rand = Math.random();
        if (player.onServer()) {
            if (Config.Realistic.get()) {
                player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (450.0D * (1.0D + rand)), 0));
                player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
            } else {
                if (rand > (Config.TagDigest.get().booleanValue() ? 1.0D : 0.8D))
                    player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
                player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
            }
            if (rand == 0.0D) {
                player.getFoodStats().addNutrition(1);
                player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("欢迎来到Double随机等于0的欧皇大殿").setColor(EnumChatFormatting.AQUA));
            }
            player.addWater(((ITFItem) this).getWater());
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
