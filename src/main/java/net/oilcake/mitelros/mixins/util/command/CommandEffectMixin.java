package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.CommandEffect;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.PotionEffect;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({CommandEffect.class})
public class CommandEffectMixin extends CommandBase {
  @Shadow
  public String getCommandName() {
    return null;
  }
  
  @Shadow
  public String getCommandUsage(ICommandSender iCommandListener) {
    return null;
  }
  
  @Overwrite
  public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    ServerPlayer serverPlayer = getCommandSenderAsPlayer(par1ICommandSender);
    if (par2ArrayOfStr[0] != null && par2ArrayOfStr[1] != null && par2ArrayOfStr[2] != null) {
      serverPlayer.addPotionEffect(new PotionEffect(Integer.parseInt(par2ArrayOfStr[0]), Integer.parseInt(par2ArrayOfStr[1]), Integer.parseInt(par2ArrayOfStr[2])));
      par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("给予buffID：" + Integer.parseInt(par2ArrayOfStr[0]))
          .appendComponent(ChatMessageComponent.createFromText("时间: " + Integer.parseInt(par2ArrayOfStr[1]))
            .appendComponent(ChatMessageComponent.createFromText("等级+1: " + Integer.parseInt(par2ArrayOfStr[2])).setColor(EnumChatFormatting.LIGHT_GRAY))));
    } 
  }
}
