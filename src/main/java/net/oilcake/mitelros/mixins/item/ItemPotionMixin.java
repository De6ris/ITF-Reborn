package net.oilcake.mitelros.mixins.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemPotion;
import net.minecraft.ItemStack;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemPotion.class})
public class ItemPotionMixin extends Item {
    @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
    private void injectCtor(CallbackInfo callback) {
        ((ITFItem) this).setWater(3);
    }

    @Shadow
    public List getEffects(ItemStack par1ItemStack) {
        return null;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            List effects = getEffects(item_stack);
            if (effects != null) {
                Iterator<PotionEffect> i = effects.iterator();
                while (i.hasNext())
                    player.addPotionEffect(new PotionEffect(i.next()));
            }
            player.addWater(((ITFItem)this).getWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
