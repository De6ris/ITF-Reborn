package net.oilcake.mitelros.command;

import net.minecraft.CommandBase;
import net.minecraft.ICommandSender;
import net.minecraft.ServerPlayer;
import net.minecraft.WrongUsageException;
import net.oilcake.mitelros.api.ITFPlayer;

public class CommandSetTemperature extends CommandBase {

    public String getCommandName() {
        return "setTemperature";
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.setTemperature.usage";
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        if (strings.length > 1)
            throw new WrongUsageException("commands.setTemperature.usage");
        ServerPlayer serverPlayer = getCommandSenderAsPlayer(iCommandListener);
        double temp = parseDouble(iCommandListener, strings[0]);
        ((ITFPlayer) serverPlayer).getTemperatureManager().bodyTemperature = (float) temp;
    }
}
