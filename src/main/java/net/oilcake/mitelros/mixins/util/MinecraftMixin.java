package net.oilcake.mitelros.mixins.util;

import net.minecraft.ILogAgent;
import net.minecraft.Minecraft;
import net.minecraft.client.main.Main;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.GuiInGameInfoHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Logger;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public static ILogAgent MITE_log;

    @Inject(method = "<init>(Lnet/minecraft/Session;IIZZLjava/io/File;Ljava/io/File;Ljava/io/File;Ljava/net/Proxy;Ljava/lang/String;)V", at = @At("RETURN"))
    public void removeInfo(CallbackInfo ci) {
        MITE_log = new ILogAgent() {
            private final Logger logger = Logger.getLogger("ITF");

            public Logger func_120013_a() {
                return this.logger;
            }

            @Override
            public void logInfo(String string) {
            }

            @Override
            public void logWarning(String string) {
            }

            @Override
            public void logWarningFormatted(String string, Object... objects) {
            }

            @Override
            public void logWarningException(String string, Throwable throwable) {
            }

            @Override
            public void logSevere(String string) {
            }

            @Override
            public void logSevereException(String string, Throwable throwable) {
            }

            @Override
            public void logFine(String string) {
            }
        };
    }

    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;getVersionDescriptor(Z)Ljava/lang/String;"))
    private String itfTitle(boolean include_formatting) {
        if (!ITFConfig.DifficultyInfo.getBooleanValue()) return Minecraft.getVersionDescriptor(include_formatting);
        String difficultyText = GuiInGameInfoHandler.getPlainDifficultyText();
        return "1.6.4-MITE is too false " + (Main.is_MITE_DS ? "-DS" : "") +
                (difficultyText) + (Minecraft.inDevMode() ? " DEV" : "");
    }// change the title of window
}
