package net.errorpnf.betternickname.commands;

import net.errorpnf.betternickname.utils.BookParser;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class NickDebug extends CommandBase {
    @Override
    public String getCommandName() {
        return "nickdebug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nickdebug <command> [args]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            if (args[0].equals("getnick")) {
                sendMessage("&aCurrent Nick:&f " + BookParser.getGeneratedNickname());
            } else if (args[0].equals("setnick")) {
                if (args[1] != null) {
                    BookParser.setGeneratedNickname(args[1]);
                    sendMessage("&aSuccessfully changed the debug nickname to:&f " + BookParser.getGeneratedNickname());
                } else {
                    sendMessage("&cPlease provided a name to set the debug nick to.");
                }

            } else if (args[0].equals("getrank")) {
                sendMessage("&aCurrent Rank:&f " + BookParser.getCurrentRank());
            } else if (args[0].equals("setrank")) {
                if (args[1] != null) {
                    BookParser.setCurrentRank(args[1]);
                    sendMessage("&aSuccessfully changed the debug rank to:&f " + BookParser.getCurrentRank());
                } else {
                    sendMessage("&cPlease provided a rank to set the debug rank to.");
                }
            } else if (args[0].equals("printchat")) {
                ChatComponentText message = new ChatComponentText("§e[BetterNick] Generated nickname: §b" + BookParser.getGeneratedNickname());

                // Create hover event
                ChatComponentText hoverText = new ChatComponentText(
                        "§eTo claim a generated nickname, either run the\n"
                                + "§ecommand §b/betternick claimname§e, or §bclick this message§e."
                                + "\n\n§cWARNING:§7 When claiming a name, you are only able to claim"
                                + "\n§7the most recently generated name. Once you have"
                                + "\n§7rerolled your nickname, all previous names will be lost.");
                message.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText));

                // Create click event to run a command
                message.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nick actuallyset " + BookParser.getGeneratedNickname()));

                Minecraft.getMinecraft().thePlayer.addChatMessage(message);
            } else if (args[0].equals("sendcommand")) {
                //ClientCommandHandler.instance.executeCommand(commandSender, "/betternick" + "randomname");
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/betternick randomname");
            }
        } else {
            sendMessage(getCommandUsage(sender));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    private void sendMessage(String message) {
        String formattedMessage = message.replace("&", "§");
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + formattedMessage)
        );
    }
}
