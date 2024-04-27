package net.oilcake.mitelros.mixins.gui;

import net.minecraft.EnumChatFormatting;
import net.minecraft.GuiMainMenu;
import net.minecraft.Minecraft;
import net.minecraft.client.main.Main;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.GuiInGameInfoHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin {
    @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;getVersionDescriptor(Z)Ljava/lang/String;"))
    private String itfTitle(boolean include_formatting) {
        if (! ITFConfig.DifficultyInfo.getBooleanValue()) return Minecraft.getVersionDescriptor(include_formatting);
        String difficultyText = GuiInGameInfoHandler.getDifficultyText();
        return "1.6.4-MITE is too false " + (Main.is_MITE_DS ? "-DS " : " ") +
                (difficultyText) + (Minecraft.inDevMode() ? (EnumChatFormatting.RED + " DEV") : "");
    }// change the left bottom version info
}
