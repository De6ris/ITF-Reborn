package net.oilcake.mitelros.mixins;

import net.minecraft.ILogAgent;
import net.minecraft.Minecraft;
import net.minecraft.client.main.Main;
import net.oilcake.mitelros.network.NoConsoleLogManager;
import net.oilcake.mitelros.util.GuiInGameInfoHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public static ILogAgent MITE_log;

    @Inject(method = "<init>(Lnet/minecraft/Session;IIZZLjava/io/File;Ljava/io/File;Ljava/io/File;Ljava/net/Proxy;Ljava/lang/String;)V", at = @At("RETURN"))
    public void removeInfo(CallbackInfo ci) {
        MITE_log = new NoConsoleLogManager();
    }

    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;getVersionDescriptor(Z)Ljava/lang/String;"))
    private String itfTitle(boolean include_formatting) {
        String difficultyText = GuiInGameInfoHandler.getPlainDifficultyText();
        return "1.6.4-MITE is too false " + (Main.is_MITE_DS ? "-DS" : "") +
                (difficultyText) + (Minecraft.inDevMode() ? " DEV" : "");
    }
}
