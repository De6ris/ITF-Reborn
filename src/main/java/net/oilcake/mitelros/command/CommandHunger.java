package net.oilcake.mitelros.command;

import net.minecraft.*;

import java.util.List;

public class CommandHunger extends CommandBase {
    public String getCommandName() {
        return "hunger";
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.hunger.usage";
    }

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[]{"client", "server"}) : null;
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        ServerPlayer serverPlayer = getCommandSenderAsPlayer(iCommandListener);
        float hunger = Float.parseFloat(strings[1]);
        if (strings[1] != null) {
            switch (strings[0]) {
                case "client":
                    serverPlayer.addHungerClientSide(hunger);
                    iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的饥饿为: " + serverPlayer.getNutrition() + ", 饱和为: " + serverPlayer.getSatiation() + ".").setColor(EnumChatFormatting.WHITE));
                    return;
                case "server":
                    serverPlayer.addHungerServerSide(hunger);
                    iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的饥饿为: " + serverPlayer.getNutrition() + ", 饱和为: " + serverPlayer.getSatiation() + ".").setColor(EnumChatFormatting.WHITE));
                    return;
            }
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormatting.RED));
        } else {
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormatting.RED));
        }
    }
}
