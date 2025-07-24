package net.errorpnf.betternickname.commands;

import net.errorpnf.betternickname.utils.AutoJump;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AutoJumpCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "autojump";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/autojump";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        AutoJump.toggleAutoJump();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    private static void sendMessage(String message) {
        String formattedMessage = message.replace("&", "§");
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + formattedMessage)
        );
    }
} 