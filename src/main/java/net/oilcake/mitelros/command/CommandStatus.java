package net.oilcake.mitelros.command;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;

public class CommandStatus extends CommandBase {
    @Override
    public String getCommandName() {
        return "status";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "command.status.usage";
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        ServerPlayer player = getCommandSenderAsPlayer(iCommandSender);
        if (strings.length > 0) {
            iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/status").setColor(EnumChatFormatting.RED));
        }
        String stringBuilder = "胰岛素抗性: " + player.getInsulinResistance() +
                ", 蛋白质: " + player.getProtein() +
                ", 植物营养素: " + player.getPhytonutrients() +
                ", 体温: " + ((ITFPlayer) player).getTemperatureManager().bodyTemperature;
        iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(stringBuilder));
    }
}
