package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.interfaces.IConfigResettable;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.gui.button.interfaces.GuiButtonCommented;
import fi.dy.masa.malilib.gui.screen.ValueScreen;
import fi.dy.masa.malilib.gui.screen.interfaces.GuiScreenCommented;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.GuiYesNoMITE;
import net.minecraft.I18n;

import java.util.function.Consumer;

public class ChallengeScreen extends GuiScreenCommented {
    private final SimpleConfigs configs;

    public ChallengeScreen(GuiScreen parentScreen) {
        super(parentScreen, "挑战设置");
        this.configs = ITFConfig.getInstance();
    }

    @Override
    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24, "自然恶意"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 48, "疯狂劲敌"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 6 + 72, "天赐福星"));
        this.buttonList.add(new GuiButtonCommented(3, this.width / 2 - 100, this.height / 6 + 120, "重置全部挑战设置", "仅重置挑战设置, 而不影响实验性玩法等"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 6 + 96, "启用终极挑战"));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        GuiYesNoMITE var3;
        switch (par1GuiButton.id) {
            case 0 -> this.mc.displayGuiScreen(new ValueScreen(this, "自然恶意", this.configs, ITFConfig.spite));
            case 1 -> this.mc.displayGuiScreen(new ValueScreen(this, "数值设置", this.configs, ITFConfig.enemy));
            case 2 -> this.mc.displayGuiScreen(new ValueScreen(this, "数值设置", this.configs, ITFConfig.luck));
            case 3 -> {
                var3 = new GuiYesNoMITE(this, "真的要重置全部挑战设置吗?", this.configs.getName(), "是", "否", 3);
                this.mc.displayGuiScreen(var3);
            }
            case 4 -> {
                var3 = new GuiYesNoMITE(this, "真的要启用终极挑战吗?", this.configs.getName(), "是", "否", 4);
                this.mc.displayGuiScreen(var3);
            }
            case 200 -> this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    @Override
    public void confirmClicked(boolean par1, int par2) {
        if (par1) {
            if (par2 == 3) {
                ITFConfig.challenge.forEach(IConfigResettable::resetToDefault);
                this.configs.save();
            }
            if (par2 == 4) {
                Consumer<ConfigBase> setMaximized = x -> {
                    if (x instanceof ConfigBooleanChallenge challenge) challenge.setBooleanValue(true);
                    if (x instanceof ConfigInteger configInteger)
                        configInteger.setIntegerValue(configInteger.getMaxIntegerValue());
                };
                ITFConfig.spite.forEach(setMaximized);
                ITFConfig.enemy.forEach(setMaximized);
                Consumer<ConfigBase> setMinimized = x -> {
                    if (x instanceof ConfigBooleanChallenge challenge) challenge.setBooleanValue(false);
                    if (x instanceof ConfigInteger configInteger) configInteger.setIntegerValue(0);
                };
                ITFConfig.luck.forEach(setMinimized);
                this.configs.save();
            }
        }
        this.mc.displayGuiScreen(this);
    }
}
