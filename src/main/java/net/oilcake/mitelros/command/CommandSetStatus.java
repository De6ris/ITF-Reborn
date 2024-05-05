package net.oilcake.mitelros.command;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFPlayer;

import java.util.List;

public class CommandSetStatus extends CommandBase {

    public String getCommandName() {
        return "setStatus";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.setStatus.usage";
    }

    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "insulin", "protein", "phytonutrients", "water", "temperature") : null;
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        if (strings.length != 2)
            throw new WrongUsageException("commands.setStatus.usage");
        ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
        switch (strings[0]) {
            case "insulin" -> {
                player.setInsulinResistance(parseInt(iCommandListener, strings[1]));
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的胰岛素抗性为" + player.getInsulinResistance()).setColor(EnumChatFormatting.WHITE));
            }
            case "protein" -> {
                player.setProtein(parseInt(iCommandListener, strings[1]));
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的蛋白质为" + player.getProtein()).setColor(EnumChatFormatting.WHITE));
            }
            case "phytonutrients" -> {
                player.setPhytonutrients(parseInt(iCommandListener, strings[1]));
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的植物营养素为" + player.getPhytonutrients()).setColor(EnumChatFormatting.WHITE));
            }
            case "water" -> {
                ((ITFFoodStats) player.getFoodStats()).setSatiationWater(parseInt(iCommandListener, strings[1]), true);
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("水分值现在为" + ((ITFPlayer) player).getWater()).setColor(EnumChatFormatting.WHITE));
            }
            case "temperature" -> {
                double temp = parseDouble(iCommandListener, strings[1]);
                ((ITFPlayer) player).getTemperatureManager().bodyTemperature = (float) temp;
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("温度现在为" + temp).setColor(EnumChatFormatting.WHITE));
            }
        }
    }

}
