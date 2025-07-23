package net.errorpnf.betternickname.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class BoolCommand extends CommandBase {

    public static boolean isShouldEnable() {
        return shouldEnable;
    }

    public static void setShouldEnable(boolean shouldEnable) {
        BoolCommand.shouldEnable = shouldEnable;
    }

    public static boolean shouldEnable = false;

    @Override
    public String getCommandName() {
        return "bool";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/bool";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        shouldEnable = !shouldEnable;
        sendMessage("bool is now " + shouldEnable);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    private void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + message)
        );
    }
}
