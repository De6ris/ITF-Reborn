package net.oilcake.mitelros.mixins.util;

import net.minecraft.LogAgent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LogAgent.class)
public class LogAgentMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/LogAgent;setupLogger()V"))
    private void doNotSetup(LogAgent instance) {
    }
}
