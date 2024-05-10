package net.oilcake.mitelros.command;

import net.minecraft.*;
import net.oilcake.mitelros.world.WorldGenUnderworldCastle;

import java.util.List;
import java.util.Random;

public class CommandGenerate extends CommandBase {

    public String getCommandName() {
        return "generate";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender iCommandListener) {
        return "commands.generate.usage";
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        World par1World = iCommandSender.getEntityWorld();
        Random par2Random = par1World.rand;
        ChunkCoordinates playerCoordinates = iCommandSender.getPlayerCoordinates();
        int i = playerCoordinates.posX;
        int y = playerCoordinates.posY;
        int j = playerCoordinates.posZ;
        WorldGenUnderworldCastle var7 = new WorldGenUnderworldCastle();
        if ( var7.forceGenerate(par1World, par2Random, i, y - 3, j)){
            iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("已成功生成"));
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "lichCastle") : null;
    }
}
