package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.CommandHandler;
import net.minecraft.ICommand;
import net.minecraft.ServerCommandManager;
import net.oilcake.mitelros.command.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerCommandManager.class})
public class ServerCommandManagerMixin extends CommandHandler {
  @Inject(method = {"<init>()V"}, at = {@At("RETURN")})
  private void injectInit(CallbackInfo callbackInfo) {
    registerCommand((ICommand)new CommandWater());
    registerCommand((ICommand)new CommandTestFreeze());
    registerCommand((ICommand)new CommandCurrentSituation());
    registerCommand((ICommand)new CommandAddCurrentSituation());
    registerCommand((ICommand)new CommandHunger());
    registerCommand((ICommand)new CommandDamage());
  }
}
