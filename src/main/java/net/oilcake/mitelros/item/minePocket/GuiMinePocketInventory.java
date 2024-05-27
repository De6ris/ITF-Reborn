package net.oilcake.mitelros.item.minePocket;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

/**
 * Basically copied from gui hopper
 * */
public class GuiMinePocketInventory extends GuiContainer {

    private static final ResourceLocation hopperGuiTextures = new ResourceLocation("textures/gui/container/hopper.png");
    private final IInventory playerInventory;
    private final IInventory pocketInventory;

    public GuiMinePocketInventory(EntityPlayer player, IInventory par2IInventory) {
        super(new ContainerMinePocket(player, par2IInventory));
        this.playerInventory = player.inventory;
        this.pocketInventory = par2IInventory;
        this.allowUserInput = false;
        this.ySize = 133;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String text = this.pocketInventory.hasCustomName() ? this.pocketInventory.getCustomNameOrUnlocalized() : I18n.getString(this.pocketInventory.getCustomNameOrUnlocalized());
        this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, 9, 0x282828);
        this.fontRenderer.drawString(this.playerInventory.hasCustomName() ? this.playerInventory.getCustomNameOrUnlocalized() : I18n.getString(this.playerInventory.getCustomNameOrUnlocalized()), 7, this.ySize - 96 + 3, 0x282828);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(hopperGuiTextures);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }
}
