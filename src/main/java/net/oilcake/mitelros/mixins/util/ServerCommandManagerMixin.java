package net.oilcake.mitelros.mixins.util;

import net.minecraft.CommandHandler;
import net.minecraft.ServerCommandManager;
import net.oilcake.mitelros.command.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommandManager.class)
public class ServerCommandManagerMixin extends CommandHandler {
    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        registerCommand(new CommandWater());
        registerCommand(new CommandTestFreeze());
        registerCommand(new CommandCurrentSituation());
        registerCommand(new CommandAddCurrentSituation());
        registerCommand(new CommandHunger());
        registerCommand(new CommandDamage());
    }
}
