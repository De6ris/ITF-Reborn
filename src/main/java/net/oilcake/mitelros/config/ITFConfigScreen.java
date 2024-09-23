//package net.oilcake.mitelros.config;
//
//import fi.dy.masa.malilib.config.interfaces.IConfigHandler;
//import fi.dy.masa.malilib.config.options.ConfigBase;
//import fi.dy.masa.malilib.config.options.ConfigInteger;
//import fi.dy.masa.malilib.gui.button.ButtonGeneric;
//import fi.dy.masa.malilib.gui.screen.DefaultConfigScreen;
//import fi.dy.masa.malilib.gui.widgets.WidgetBase;
//import fi.dy.masa.malilib.util.StringUtils;
//import net.fabricmc.loader.api.ModContainer;
//import net.fabricmc.loader.api.Version;
//import net.fabricmc.loader.api.VersionParsingException;
//import net.fabricmc.loader.impl.util.version.SemanticVersionImpl;
//import net.minecraft.GuiScreen;
//import net.minecraft.GuiYesNoMITE;
//import net.xiaoyu233.fml.FishModLoader;
//
//public class ITFConfigScreen extends DefaultConfigScreen {
//
//    public static final int ConfirmFlag = "FinalChallenge".hashCode();
//
//    ButtonGeneric buttonGeneric;
//
//    public ITFConfigScreen(GuiScreen parentScreen, IConfigHandler configInstance) {
//        super(parentScreen, configInstance);
//    }
//
//    @Override
//    public void initGui() {
//        super.initGui();
//        this.buttonGeneric = ButtonGeneric.builder("启用终极挑战", buttonBase -> {
//            String question = "确定启用终极挑战吗";
//            String yes = StringUtils.translate("gui.yes");
//            String no = StringUtils.translate("gui.no");
//            GuiYesNoMITE var3 = new GuiYesNoMITE(this, question, "这很困难!", yes, no, 0);
//            this.mc.displayGuiScreen(var3);
//        }).dimensions(0, 0, 100, 20).build();
//        this.registerButton(this.buttonGeneric);
//    }
//
//    void registerButton(WidgetBase widgetBase) {
//        ModContainer manyLib = FishModLoader.getModContainer("many-lib").orElseThrow();
//        Version version = manyLib.getMetadata().getVersion();
//        int compare = 1;
//        try {
//            Version version220 = new SemanticVersionImpl("2.2.0", false);
//            compare = version.compareTo(version220);
//        } catch (VersionParsingException e) {
//            throw new RuntimeException(e);
//        }
//        switch (compare) {
//            case 1 -> {// 2.2.0+
//            }
//            case 0 -> {// 2.2.0
//            }
//            case -1 -> {// 2.2.0-
//
//            }
//        }
//
//    }// because the manyLib api changes
//
//    @Override
//    public void confirmClicked(boolean result, int flag) {
//        if (result && flag == ConfirmFlag) {
//            this.finalChallenge();
//        }
//        super.confirmClicked(result, flag);
//    }
//
//    void finalChallenge() {
//        for (ConfigBase<?> configBase : ITFConfig.spite) {
//            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
//                configBooleanChallenge.setBooleanValue(true);
//            }
//            if (configBase instanceof ConfigInteger configInteger) {
//                configInteger.setIntegerValue(configInteger.getMaxIntegerValue());
//            }
//        }
//        for (ConfigBase<?> configBase : ITFConfig.enemy) {
//            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
//                configBooleanChallenge.setBooleanValue(true);
//            }
//            if (configBase instanceof ConfigInteger configInteger) {
//                configInteger.setIntegerValue(configInteger.getMaxIntegerValue());
//            }
//        }
//        for (ConfigBase<?> configBase : ITFConfig.luck) {
//            if (configBase instanceof ConfigBooleanChallenge configBooleanChallenge) {
//                configBooleanChallenge.setBooleanValue(false);
//            }
//        }
//    }
//}
