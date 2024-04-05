package net.oilcake.mitelros.mixins;

import net.minecraft.EnumChatFormatting;
import net.minecraft.ILogAgent;
import net.minecraft.Minecraft;
import net.minecraft.PlayerControllerMP;
import net.minecraft.client.main.Main;
import net.oilcake.mitelros.network.NoConsoleLogManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public static ILogAgent MITE_log;

    @Inject(method = {"<init>(Lnet/minecraft/Session;IIZZLjava/io/File;Ljava/io/File;Ljava/io/File;Ljava/net/Proxy;Ljava/lang/String;)V"}, at = {@At("RETURN")})
    public void removeInfo(CallbackInfo ci) {
        MITE_log = new NoConsoleLogManager();
    }

    @Inject(method = "getVersionDescriptor", at = @At("HEAD"), cancellable = true)
    private static void inject(boolean include_formatting, CallbackInfoReturnable<String> cir) {
        String red = include_formatting ? EnumChatFormatting.RED.toString() : "";
        cir.setReturnValue("1.6.4-MITE is too false" + (Main.is_MITE_DS ? "-DS" : "") + (
                Minecraft.inDevMode() ? (red + " DEV") : ""));
    }
}
