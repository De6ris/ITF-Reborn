package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.interfaces.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.screen.DefaultConfigScreen;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.GuiScreen;
import net.minecraft.GuiYesNoMITE;

public class ITFConfigScreen extends DefaultConfigScreen {

    public static final int ConfirmFlag = "FinalChallenge".hashCode();

    public ITFConfigScreen(GuiScreen parentScreen, IConfigHandler configInstance) {
        super(parentScreen, configInstance);
    }

    @Override
    protected void initElements() {
        super.initElements();
        ButtonGeneric buttonGeneric = ButtonGeneric.builder("启用终极挑战", buttonBase -> {
            String question = "确定启用终极挑战吗";
            String yes = StringUtils.translate("gui.yes");
            String no = StringUtils.translate("gui.no");
            GuiYesNoMITE var3 = new GuiYesNoMITE(this, question, "这很困难!", yes, no, 0);
            this.mc.displayGuiScreen(var3);
        }).dimensions(this.width - 80, 30, 60, 20).build();
        this.addButton(buttonGeneric);
    }

    @Override
    public void confirmClicked(boolean result, int flag) {
        if (result && flag == ConfirmFlag) {
            this.enableFinalChallenge();
        }
        super.confirmClicked(result, flag);
    }

    void enableFinalChallenge() {
        for (ConfigBase<?> configBase : ITFConfig.spite) {
            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
                configBooleanChallenge.setBooleanValue(true);
            }
            if (configBase instanceof ConfigInteger configInteger) {
                configInteger.setIntegerValue(configInteger.getMaxIntegerValue());
            }
        }
        for (ConfigBase<?> configBase : ITFConfig.enemy) {
            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
                configBooleanChallenge.setBooleanValue(true);
            }
            if (configBase instanceof ConfigInteger configInteger) {
                configInteger.setIntegerValue(configInteger.getMaxIntegerValue());
            }
        }
        for (ConfigBase<?> configBase : ITFConfig.luck) {
            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
                configBooleanChallenge.setBooleanValue(false);
            }
        }
    }
}
