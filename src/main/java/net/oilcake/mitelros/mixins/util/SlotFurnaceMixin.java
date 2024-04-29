package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.AchievementExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotFurnace.class)
public abstract class SlotFurnaceMixin extends SlotCraftingBase {
    public SlotFurnaceMixin(EntityPlayer player, IInventory inventory, int slot_index, int display_x, int display_y) {
        super(player, inventory, slot_index, display_x, display_y);
    }

    @Inject(method = "onCrafting", at = @At("TAIL"))
    private void itfAchievementCheck(ItemStack item_stack, CallbackInfo ci) {
        Item item = item_stack.getItem();
        if (item == Items.uruIngot || item == Items.uruNugget) {
            this.player.triggerAchievement(AchievementExtend.neverEnds);
        }
    }
}
