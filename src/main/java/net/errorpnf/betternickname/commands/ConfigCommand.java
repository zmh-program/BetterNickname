package net.errorpnf.betternickname.commands;

import net.errorpnf.betternickname.config.BetterNickConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "betternickconfig";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/betternickconfig <setting> [value]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sendHelpMessage();
            return;
        }

        String setting = args[0].toLowerCase();

        switch (setting) {
            case "help":
                sendHelpMessage();
                break;
            case "showrank":
                if (args.length > 1) {
                    boolean value = Boolean.parseBoolean(args[1]);
                    BetterNickConfig.showRank = value;
                    sendMessage("Show Rank set to: " + value);
                } else {
                    sendMessage("Show Rank: " + BetterNickConfig.showRank);
                }
                break;
            case "matchtext":
                if (args.length > 1) {
                    BetterNickConfig.matchText = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    sendMessage("Match Text set to: '" + BetterNickConfig.matchText + "'");
                } else {
                    sendMessage("Match Text: '" + BetterNickConfig.matchText + "'");
                }
                break;
            case "excludetext":
                if (args.length > 1) {
                    BetterNickConfig.excludeText = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    sendMessage("Exclude Text set to: '" + BetterNickConfig.excludeText + "'");
                } else {
                    sendMessage("Exclude Text: '" + BetterNickConfig.excludeText + "'");
                }
                break;
            case "autoclaim":
                if (args.length > 1) {
                    boolean value = Boolean.parseBoolean(args[1]);
                    BetterNickConfig.autoClaimName = value;
                    sendMessage("Auto Claim Name set to: " + value);
                } else {
                    sendMessage("Auto Claim Name: " + BetterNickConfig.autoClaimName);
                }
                break;
            case "rerolldelay":
                if (args.length > 1) {
                    try {
                        int value = Integer.parseInt(args[1]);
                        if (value >= 1 && value <= 10) {
                            BetterNickConfig.rerollDelay = value;
                            sendMessage("Reroll Delay set to: " + value + " seconds");
                        } else {
                            sendMessage(EnumChatFormatting.RED + "Reroll Delay must be between 1 and 10 seconds");
                        }
                    } catch (NumberFormatException e) {
                        sendMessage(EnumChatFormatting.RED + "Invalid number format");
                    }
                } else {
                    sendMessage("Reroll Delay: " + BetterNickConfig.rerollDelay + " seconds");
                }
                break;
            case "list":
                listAllSettings();
                break;
            default:
                sendMessage(EnumChatFormatting.RED + "Unknown setting: " + setting);
                sendHelpMessage();
                break;
        }

        BetterNickConfig.save();
    }

    private void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + message)
        );
    }

    private void sendHelpMessage() {
        sendMessage(EnumChatFormatting.GOLD + "=== BetterNick Configuration ===");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig showrank <true/false>" + EnumChatFormatting.GRAY + " - Show rank in HUD");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig matchtext <text>" + EnumChatFormatting.GRAY + " - Text to match in nickname");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig excludetext <text>" + EnumChatFormatting.GRAY + " - Text to exclude in nickname");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig autoclaim <true/false>" + EnumChatFormatting.GRAY + " - Auto claim matching names");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig rerolldelay <1-10>" + EnumChatFormatting.GRAY + " - Delay between rerolls (seconds)");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig list" + EnumChatFormatting.GRAY + " - List all current settings");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig help" + EnumChatFormatting.GRAY + " - Show this help message");
    }

    private void listAllSettings() {
        sendMessage(EnumChatFormatting.GOLD + "=== Current Settings ===");
        sendMessage("Show Rank: " + BetterNickConfig.showRank);
        sendMessage("Match Text: '" + BetterNickConfig.matchText + "'");
        sendMessage("Exclude Text: '" + BetterNickConfig.excludeText + "'");
        sendMessage("Auto Claim Name: " + BetterNickConfig.autoClaimName);
        sendMessage("Reroll Delay: " + BetterNickConfig.rerollDelay + " seconds");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String input = args[0].toLowerCase();
            completions.addAll(Arrays.asList("help", "showrank", "matchtext", "excludetext", "autoclaim", "rerolldelay", "list"));
            completions.removeIf(s -> !s.startsWith(input));
        } else if (args.length == 2) {
            String setting = args[0].toLowerCase();
            String input = args[1].toLowerCase();
            
            if (setting.equals("showrank") || setting.equals("autoclaim")) {
                completions.addAll(Arrays.asList("true", "false"));
                completions.removeIf(s -> !s.startsWith(input));
            }
        }

        return completions.isEmpty() ? super.addTabCompletionOptions(sender, args, pos) : completions;
    }
} 