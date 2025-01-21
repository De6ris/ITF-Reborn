package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiEnchantReserver extends GuiContainer {
    private static final ResourceLocation ENCHANT_RESERVER_TEXTURE = new ResourceLocation(ITFStart.ResourceDomain, "textures/gui/container/enchant_reserver.png");

    private final TileEntityEnchantReserver tileEntityEnchantReserver = new TileEntityEnchantReserver();

    public GuiEnchantReserver(EntityPlayer player, int x, int y, int z, EnchantReserverInventory slots) {
        super(new ContainerEnchantReserver(slots, player, x, y, z));
    }

    @Override
    public void drawScreen(int mouse_x, int mouse_y, float par3) {
        super.drawScreen(mouse_x, mouse_y, par3);
        Slot slot = this.getSlotThatMouseIsOver(mouse_x, mouse_y);
        if (slot == null) return;
        if (slot.getHasStack()) return;
        if (slot.slotNumber > 1) return;
        String tooltip;
        if (slot.slotNumber == 0) {
            tooltip = I18n.getString("gui.enchantReserver.slotTop.tooltip");
        } else {
            tooltip = I18n.getString("gui.enchantReserver.slotBottom.tooltip");
        }
        this.drawCreativeTabHoveringText(EnumChatFormatting.GOLD + tooltip, mouse_x, mouse_y);
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        if (par3 != 0 || !Keyboard.isKeyDown(42)) return;// shift and left click
        Slot mouseOver = this.theSlot;
        if (mouseOver != null && mouseOver.getHasStack() && mouseOver.slotNumber > 1) {
            ItemStack stack = mouseOver.getStack();
            if (stack != null) {
                this.mc.playerController.windowClick(this.inventorySlots.windowId, mouseOver.slotNumber, 0, 0, this.mc.thePlayer);
                this.mc.playerController.windowClick(this.inventorySlots.windowId, 0, 0, 0, this.mc.thePlayer);
                this.mc.playerController.windowClick(this.inventorySlots.windowId, 1, 0, 0, this.mc.thePlayer);
                this.mc.playerController.windowClick(this.inventorySlots.windowId, mouseOver.slotNumber, 0, 0, this.mc.thePlayer);
            }
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String var3 = this.tileEntityEnchantReserver.hasCustomName() ? this.tileEntityEnchantReserver.getCustomNameOrUnlocalized() : I18n.getString(this.tileEntityEnchantReserver.getCustomNameOrUnlocalized());
        this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(ENCHANT_RESERVER_TEXTURE);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        int var6 = this.guiLeft;
        int var7 = this.guiTop;
        drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        int exp = Math.max(0, this.tileEntityEnchantReserver.getEXP() - this.tileEntityEnchantReserver.getLaunchEXP());
        int maxExp = this.tileEntityEnchantReserver.getMaxEXP() - this.tileEntityEnchantReserver.getLaunchEXP();
        int r;
        int g;
        int b;
        if (tileEntityEnchantReserver.getEXP() < this.tileEntityEnchantReserver.getLaunchEXP()) {
            r = 160 + (int) (24.0F * ((float) tileEntityEnchantReserver.getEXP() / (float) this.tileEntityEnchantReserver.getLaunchEXP()));
            g = 30 + (int) (196.0F * ((float) tileEntityEnchantReserver.getEXP() / (float) this.tileEntityEnchantReserver.getLaunchEXP()));
            b = 30 - (int) (27.0F * ((float) tileEntityEnchantReserver.getEXP() / (float) this.tileEntityEnchantReserver.getLaunchEXP()));
        } else {
            r = 184 - (int) (120.0F * ((float) exp / (float) maxExp));
            g = 226 - (int) (66.0F * ((float) exp / (float) maxExp));
            b = 3 + (int) (29.0F * ((float) exp / (float) maxExp));
        }
        int color = (r << 16) + (g << 8) + b;
        drawTexturedModalRect(var6 + 99, var7 + 21, 176, 0, 16, (int) (43.0F * exp / maxExp));
        if (tileEntityEnchantReserver.getEXP() < this.tileEntityEnchantReserver.getLaunchEXP()) {
            this.fontRenderer.drawString(tileEntityEnchantReserver.getEXP() + "/" + this.tileEntityEnchantReserver.getLaunchEXP(), this.width / 2 + 8, this.height / 2 - 70, color);
        } else {
            this.fontRenderer.drawString(exp + "/" + maxExp, this.width / 2 + 8, this.height / 2 - 70, 14737632);
        }

    }

    public void setEnchantInfo(int exp) {
        this.tileEntityEnchantReserver.setEXP(exp);
    }
}
