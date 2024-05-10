package net.oilcake.mitelros.mixins.util;

import net.minecraft.CommandHandler;
import net.minecraft.ServerCommandManager;
import net.oilcake.mitelros.command.CommandGenerate;
import net.oilcake.mitelros.command.CommandHunger;
import net.oilcake.mitelros.command.CommandSetStatus;
import net.oilcake.mitelros.command.CommandStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommandManager.class)
public class ServerCommandManagerMixin extends CommandHandler {
    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        registerCommand(new CommandHunger());
        registerCommand(new CommandSetStatus());
        registerCommand(new CommandStatus());
        registerCommand(new CommandGenerate());
    }
}
