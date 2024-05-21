package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.interfaces.IConfigResettable;
import fi.dy.masa.malilib.gui.screen.ValueScreen;
import fi.dy.masa.malilib.gui.screen.interfaces.GuiScreenCommented;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.GuiYesNoMITE;
import net.minecraft.I18n;

import static net.oilcake.mitelros.ITFStart.MOD_ID;

public class ITFConfigScreen extends GuiScreenCommented {
    private final ITFConfig configs;

    public ITFConfigScreen(GuiScreen parentScreen) {
        super(parentScreen, MOD_ID);
        this.configs = ITFConfig.getInstance();
    }

    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24, "挑战设置"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 48, "实验性玩法"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 6 + 72, "其他配置项"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 120, "重置全部设置"));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        GuiYesNoMITE var3;
        switch (par1GuiButton.id) {
            case 0 -> this.mc.displayGuiScreen(new ChallengeScreen(this));
            case 1 ->
                    this.mc.displayGuiScreen(new ValueScreen(this, "实验性玩法", this.configs, ITFConfig.experimental));
            case 2 -> this.mc.displayGuiScreen(new OtherScreen(this));
            case 3 -> {
                var3 = new GuiYesNoMITE(this, "真的要重置全部设置吗?", this.configs.getName(), "是", "否", 1);
                this.mc.displayGuiScreen(var3);
            }
            case 200 -> this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    @Override
    public void confirmClicked(boolean par1, int par2) {
        if (par1) {
            if (par2 == 1) {
                ITFConfig.values.forEach(IConfigResettable::resetToDefault);
                this.configs.save();
            }
        }
        this.mc.displayGuiScreen(this);
    }
}
