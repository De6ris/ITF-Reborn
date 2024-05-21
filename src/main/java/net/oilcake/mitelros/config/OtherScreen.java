package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.interfaces.IConfigResettable;
import fi.dy.masa.malilib.gui.screen.ValueScreen;
import fi.dy.masa.malilib.gui.screen.interfaces.GuiScreenCommented;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.GuiYesNoMITE;
import net.minecraft.I18n;

public class OtherScreen extends GuiScreenCommented {
    private final SimpleConfigs configs;

    public OtherScreen(GuiScreen parentScreen) {
        super(parentScreen, "其它设置");
        this.configs = ITFConfig.getInstance();
    }

    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24, "信息显示"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 48, "杂项"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 120, "重置其它设置"));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        GuiYesNoMITE var3;
        switch (par1GuiButton.id) {
            case 0:
                this.mc.displayGuiScreen(new ValueScreen(this, "信息显示", this.configs,ITFConfig.info));
                break;
            case 1:
                this.mc.displayGuiScreen(new ValueScreen(this, "杂项", this.configs,ITFConfig.misc));
                break;
            case 3:
                var3 = new GuiYesNoMITE(this, "真的要重置全部其它设置吗?", this.configs.getName(), "是", "否", 3);
                this.mc.displayGuiScreen(var3);
                break;
            case 200:
                this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    public void confirmClicked(boolean par1, int par2) {
        if (par1) {
            if (par2 == 3) {
                ITFConfig.others.forEach(IConfigResettable::resetToDefault);
                this.configs.save();
            }
        }
        this.mc.displayGuiScreen(this);
    }
}
