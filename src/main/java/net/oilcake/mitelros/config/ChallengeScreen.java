package net.oilcake.mitelros.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.interfaces.IConfigResettable;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.gui.screen.ValueScreen;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.GuiYesNoMITE;
import net.minecraft.I18n;

public class ChallengeScreen extends GuiScreen {

    private GuiScreen parentScreen;
    protected String screenTitle;
    private final SimpleConfigs configs;
    private final ImmutableList<ConfigBase> values;

    public ChallengeScreen(GuiScreen parentScreen, String screenTitle, SimpleConfigs configs) {
        this.parentScreen = parentScreen;
        this.screenTitle = screenTitle;
        this.configs = configs;
        this.values = ImmutableList.copyOf(configs.getValues());
    }

    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 24, "自然恶意"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 48, "疯狂劲敌"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 6 + 72, "天赐福星"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 120, "重置全部挑战设置"));

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        GuiYesNoMITE var3;
        switch (par1GuiButton.id) {
            case 0:
                this.mc.displayGuiScreen(new ValueScreen(this, "自然恶意", this.configs.setValues(ITFConfig.spite)));
                break;
            case 1:
                this.mc.displayGuiScreen(new ValueScreen(this, "数值设置", this.configs.setValues(ITFConfig.enemy)));
                break;
            case 2:
                this.mc.displayGuiScreen(new ValueScreen(this, "数值设置", this.configs.setValues(ITFConfig.luck)));
                break;
            case 3:
                var3 = new GuiYesNoMITE(this, "真的要重置全部挑战设置吗?", this.configs.getName(), "是", "否", 1);
                this.mc.displayGuiScreen(var3);
                break;
            case 200:
                this.mc.displayGuiScreen(this.parentScreen);
        }

    }

    public void confirmClicked(boolean par1, int par2) {
        if (par1) {
            if (par2 == 1) {
                this.values.forEach(IConfigResettable::resetToDefault);
                this.values.forEach(x -> System.out.println(x.getName()));
                this.configs.save();
            }
        }
        this.mc.displayGuiScreen(this);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
        super.drawScreen(par1, par2, par3);
    }
}
