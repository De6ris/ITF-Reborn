package net.oilcake.mitelros.mixins.server;

import net.minecraft.ChatMessageComponent;
import net.minecraft.DedicatedServer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Minecraft;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.client.ITFEvent;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {
    @Inject(method = "playerLoggedIn(Lnet/minecraft/ServerPlayer;)V", at = @At("RETURN"))
    public void playerLoggedIn(ServerPlayer player, CallbackInfo callbackInfo) {
        player.setHealth(player.getHealth());
        ((ITFPlayer)player).broadcast();
        if (!Minecraft.inDevMode())
            player.vision_dimming = 1.25F;
    }

}
