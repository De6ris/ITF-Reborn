package net.oilcake.mitelros.mixins.gui;

import net.minecraft.*;
import net.oilcake.mitelros.api.AnvilStatus;
import net.oilcake.mitelros.api.ITFContainerRepair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
            AnvilStatus anvilStatus = ((ITFContainerRepair) this.repairContainer).itf$GetAnvilStatus();
            String text = switch (anvilStatus) {
                case NoAvailableEnchantment -> I18n.getString("gui.repair.no_available_enchantment");
                case EnchantmentConflict -> I18n.getString("gui.repair.enchantment_conflict");
                case Satisfied -> this.getString(((ITFContainerRepair) this.repairContainer).itf$GetXPDifference());
                case LackExp ->
                        I18n.getStringParams("gui.repair.lack_xp", this.repairContainer.player.getExperienceLevel(-((ITFContainerRepair) this.repairContainer).itf$GetXPDifference()));
            };
            this.drawCreativeTabHoveringText(text, mouse_x, mouse_y);
        }
    }

    @Unique
    private String getString(int xpDifference) {
        EntityPlayer player = this.repairContainer.player;
        int hypothetical_level = player.getExperienceLevel(player.experience + xpDifference);
        int level_cost = player.getExperienceLevel() - hypothetical_level;
        String text;
        if (level_cost < 0) {
            text = I18n.getStringParams("gui.repair.rewardMoreThanOneLevel", -level_cost);
        } else if (level_cost > 0) {
            text = I18n.getStringParams("gui.repair.costMoreThanOneLevel", level_cost);
        } else {
            text = I18n.getString("gui.repair.effectLessThanOneLevel");
        }
        return EnumChatFormatting.YELLOW + text;
    }
}
