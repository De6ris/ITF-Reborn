package net.oilcake.mitelros.mixins.server;

import net.minecraft.ChatMessageComponent;
import net.minecraft.DedicatedServer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Minecraft;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DedicatedServer.class})
public class DedicatedServerMixin {
    @Inject(method = {"playerLoggedIn(Lnet/minecraft/ServerPlayer;)V"}, at = {@At("RETURN")})
    public void playerLoggedIn(ServerPlayer player, CallbackInfo callbackInfo) {
        player.setHealth(player.getHealth());
        player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessageComponent.createFromTranslationKey("MITE-ITF挂载成功,当前版本:").setColor(EnumChatFormatting.BLUE))
                .appendComponent(ChatMessageComponent.createFromText(ITFStart.Version).setColor(EnumChatFormatting.YELLOW))
                .appendComponent(ChatMessageComponent.createFromTranslationKey(",作者:Lee074,Huix,Kalsey,并由Debris移植到FML3.2.1")));
        if (Constant.CalculateCurrentDiff() != 0) {
            player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[MITE-ITF]")
                    .appendComponent(ChatMessageComponent.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff())
                            .setColor((Constant.CalculateCurrentDiff() >= 16) ? EnumChatFormatting.DARK_RED :
                                    ((Constant.CalculateCurrentDiff() >= 12) ? EnumChatFormatting.RED :
                                            ((Constant.CalculateCurrentDiff() >= 8) ? EnumChatFormatting.YELLOW :
                                                    ((Constant.CalculateCurrentDiff() >= 4) ? EnumChatFormatting.GREEN :
                                                            ((Constant.CalculateCurrentDiff() > 0) ? EnumChatFormatting.AQUA :
                                                                    EnumChatFormatting.BLUE)))))));
        }        if (!Minecraft.inDevMode())
            player.vision_dimming = 1.25F;
    }

}
