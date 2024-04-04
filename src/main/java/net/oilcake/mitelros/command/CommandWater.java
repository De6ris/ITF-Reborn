package net.oilcake.mitelros.command;

import java.util.List;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;

public class CommandWater extends CommandBase {
    public String getCommandName() {
        return "water";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.water.usage";
    }

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[]{"get", "add"}) : null;
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        EntityPlayer serverPlayer = getCommandSenderAsPlayer(iCommandListener);
        switch (strings[0]) {
            case "get":
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("水分值为" + ((ITFPlayer) serverPlayer).getWater()).setColor(EnumChatFormatting.WHITE));
                return;
            case "add":
                ((ITFPlayer) serverPlayer).addWater(Integer.parseInt(strings[1]));
                iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("水分值现在为" + ((ITFPlayer) serverPlayer).getWater()).setColor(EnumChatFormatting.WHITE));
                return;
        }
        iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/water <get|add>").setColor(EnumChatFormatting.RED));
    }
}
