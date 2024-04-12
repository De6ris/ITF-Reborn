package net.oilcake.mitelros.command;

import net.minecraft.*;

public class CommandDamage extends CommandBase {
    public String getCommandName() {
        return "damage";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.damage.usage";
    }

    public void processCommand(ICommandSender iCommandListener, String[] strings) {
        ServerPlayer serverPlayer = getCommandSenderAsPlayer(iCommandListener);
        ItemStack holding = serverPlayer.getHeldItemStack();
        if (holding != null && Integer.parseInt(strings[0]) >= 0 && Integer.parseInt(strings[0]) < holding.getMaxDamage()) {
            holding.setItemDamage(Integer.parseInt(strings[0]));
        } else {
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/damage <num>;<num> 为 0 ~ " + serverPlayer.getHeldItemStack().getMaxDamage() + ";请确认手中物品合法。").setColor(EnumChatFormatting.RED));
        }
    }
}
