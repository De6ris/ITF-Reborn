package net.oilcake.mitelros.mixins.gui;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFContainerRepair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRepair.class)
public abstract class GuiRepairMixin
        extends GuiContainer {
    @Shadow
    private ContainerRepair repairContainer;

    public GuiRepairMixin(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "drawScreen", at = @At("TAIL"))
    private void itfRepair(int mouse_x, int mouse_y, float par3, CallbackInfo ci) {
        ItemStack itemStack = this.repairContainer.getSlot(1).getStack();
        if (itemStack == null) return;
        if (itemStack.itemID != Item.enchantedBook.itemID && itemStack.itemID != Item.bottleOfDisenchanting.itemID)
            return;
        if (this.isMouseOverSlot(this.repairContainer.getSlot(2), mouse_x, mouse_y)) {
            int xpDifference = ((ITFContainerRepair) this.repairContainer).getXPDifference();
            EntityPlayer player = this.repairContainer.player;
            int hypothetical_level = player.getExperienceLevel(player.experience + xpDifference);
            int level_cost = player.getExperienceLevel() - hypothetical_level;
            String text;
            if (level_cost < 0) {
                text = EnumChatFormatting.YELLOW + "此操作将让你提升" + -level_cost + "等级";
            } else if (level_cost > 0) {
                text = EnumChatFormatting.YELLOW + "此操作将让你消耗" + level_cost + "等级";
            } else {
                text = EnumChatFormatting.YELLOW + "此操作对你等级的影响小于一级";
            }
            this.drawCreativeTabHoveringText(text, mouse_x, mouse_y);
        }
    }
}
