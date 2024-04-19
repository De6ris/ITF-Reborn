package net.oilcake.mitelros.command;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;

public class CommandTestFreeze extends CommandBase {
    public String getCommandName() {
        return "testfreeze";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.testfreeze.usage";
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        ServerPlayer serverPlayer = getCommandSenderAsPlayer(iCommandListener);
        BiomeGenBase biome = serverPlayer.worldObj.getBiomeGenForCoords(serverPlayer.getBlockPosX(), serverPlayer.getBlockPosZ());
        if (((ITFPlayer) (EntityPlayer) serverPlayer).getTemperatureManager().inFreeze()) {
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("当前温度为:" + biome.temperature + "，玩家寒冷度为" + ((ITFPlayer) (EntityPlayer) serverPlayer).getTemperatureManager().getFreezingCoolDown() + "，玩家受到寒冷影响").setColor(EnumChatFormatting.WHITE));
        } else {
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("当前温度为:" + biome.temperature + "，玩家寒冷度为" + ((ITFPlayer) (EntityPlayer) serverPlayer).getTemperatureManager().getFreezingCoolDown() + "，玩家未受到寒冷影响").setColor(EnumChatFormatting.WHITE));
        }
    }
}
