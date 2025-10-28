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
                    BetterNickConfig.autoclaim = value;
                    sendMessage("Auto Claim Name set to: " + value);
                } else {
                    sendMessage("Auto Claim Name: " + BetterNickConfig.autoclaim);
                }
                break;
            case "rerolldelay":
                if (args.length > 1) {
                    try {
                        double value = Double.parseDouble(args[1]);
                        if (value >= 0.1 && value <= 10.0) {
                            BetterNickConfig.rerollDelay = value;
                            sendMessage("Reroll Delay set to: " + value + " seconds");
                        } else {
                            sendMessage(EnumChatFormatting.RED + "Reroll Delay must be between 0.1 and 10.0 seconds");
                        }
                    } catch (NumberFormatException e) {
                        sendMessage(EnumChatFormatting.RED + "Invalid number format");
                    }
                } else {
                    sendMessage("Reroll Delay: " + BetterNickConfig.rerollDelay + " seconds");
                }
                break;
            case "maxlength":
                if (args.length > 1) {
                    try {
                        int value = Integer.parseInt(args[1]);
                        if (value >= 0 && value <= 16) {
                            BetterNickConfig.maxLength = value;
                            if (value == 0) {
                                sendMessage("Max Length set to: No limit");
                            } else {
                                sendMessage("Max Length set to: " + value + " characters");
                            }
                        } else {
                            sendMessage(EnumChatFormatting.RED + "Max Length must be between 0 (no limit) and 16 characters");
                        }
                    } catch (NumberFormatException e) {
                        sendMessage(EnumChatFormatting.RED + "Invalid number format");
                    }
                } else {
                    if (BetterNickConfig.maxLength == 0) {
                        sendMessage("Max Length: No limit");
                    } else {
                        sendMessage("Max Length: " + BetterNickConfig.maxLength + " characters");
                    }
                }
                break;
            case "allownumbers":
                if (args.length > 1) {
                    boolean value = Boolean.parseBoolean(args[1]);
                    BetterNickConfig.allowNumbers = value;
                    sendMessage("Allow Numbers set to: " + value);
                } else {
                    sendMessage("Allow Numbers: " + BetterNickConfig.allowNumbers);
                }
                break;
            case "allowunderscores":
                if (args.length > 1) {
                    boolean value = Boolean.parseBoolean(args[1]);
                    BetterNickConfig.allowUnderscores = value;
                    sendMessage("Allow Underscores set to: " + value);
                } else {
                    sendMessage("Allow Underscores: " + BetterNickConfig.allowUnderscores);
                }
                break;
            case "rule":
                if (args.length > 1) {
                    try {
                        int ruleNumber = Integer.parseInt(args[1]);
                        if (ruleNumber == 1) {
                            BetterNickConfig.applyRulePreset(1);
                            sendMessage("Applied Rule Preset 1: " + BetterNickConfig.getRulePresetDescription(1));
                            sendMessage("Settings applied:");
                            sendMessage("  - Allow Numbers: " + BetterNickConfig.allowNumbers);
                            sendMessage("  - Max Length: " + BetterNickConfig.maxLength + " characters");
                        } else {
                            sendMessage(EnumChatFormatting.RED + "Unknown rule preset: " + ruleNumber);
                        }
                    } catch (NumberFormatException e) {
                        sendMessage(EnumChatFormatting.RED + "Invalid rule number format");
                    }
                } else {
                    sendMessage("Available rule presets:");
                    sendMessage("  Rule 1: " + BetterNickConfig.getRulePresetDescription(1));
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
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig matchtext <text>" + EnumChatFormatting.GRAY + " - Text to match in nickname");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig excludetext <text>" + EnumChatFormatting.GRAY + " - Text to exclude in nickname");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig autoclaim <true/false>" + EnumChatFormatting.GRAY + " - Auto claim matching names");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig rerolldelay <0.1-10.0>" + EnumChatFormatting.GRAY + " - Delay between rerolls (seconds)");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig maxlength <0-16>" + EnumChatFormatting.GRAY + " - Max length for generated names");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig allownumbers <true/false>" + EnumChatFormatting.GRAY + " - Allow numbers in generated names");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig allowunderscores <true/false>" + EnumChatFormatting.GRAY + " - Allow underscores in generated names");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig rule <1>" + EnumChatFormatting.GRAY + " - Apply rule preset (rule 1: no numbers, max 8 chars)");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig list" + EnumChatFormatting.GRAY + " - List all current settings");
        sendMessage(EnumChatFormatting.AQUA + "/betternickconfig help" + EnumChatFormatting.GRAY + " - Show this help message");
    }

    private void listAllSettings() {
        sendMessage(EnumChatFormatting.GOLD + "=== Current Settings ===");
        sendMessage("Match Text: '" + BetterNickConfig.matchText + "'");
        sendMessage("Exclude Text: '" + BetterNickConfig.excludeText + "'");
        sendMessage("Auto Claim Name: " + BetterNickConfig.autoclaim);
        sendMessage("Reroll Delay: " + BetterNickConfig.rerollDelay + " seconds");
        if (BetterNickConfig.maxLength == 0) {
            sendMessage("Max Length: No limit");
        } else {
            sendMessage("Max Length: " + BetterNickConfig.maxLength + " characters");
        }
        sendMessage("Allow Numbers: " + BetterNickConfig.allowNumbers);
        sendMessage("Allow Underscores: " + BetterNickConfig.allowUnderscores);
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
            completions.addAll(Arrays.asList("help", "matchtext", "excludetext", "autoclaim", "rerolldelay", "maxlength", "allownumbers", "allowunderscores", "rule", "list"));
            completions.removeIf(s -> !s.startsWith(input));
        } else if (args.length == 2) {
            String setting = args[0].toLowerCase();
            String input = args[1].toLowerCase();
            
            if (setting.equals("autoclaim")) {
                completions.addAll(Arrays.asList("true", "false"));
                completions.removeIf(s -> !s.startsWith(input));
            } else if (setting.equals("rerolldelay")) {
                completions.addAll(Arrays.asList("0.5", "1", "1.5", "2", "2.5", "3", "4", "5"));
                completions.removeIf(s -> !s.startsWith(input));
            } else if (setting.equals("maxlength")) {
                completions.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "0"));
                completions.removeIf(s -> !s.startsWith(input));
            } else if (setting.equals("rule")) {
                completions.addAll(Arrays.asList("1"));
                completions.removeIf(s -> !s.startsWith(input));
            } else if (setting.equals("allownumbers") || setting.equals("allowunderscores")) {
                completions.addAll(Arrays.asList("true", "false"));
                completions.removeIf(s -> !s.startsWith(input));
            } else if (setting.equals("rule")) {
                completions.addAll(Arrays.asList("1", "2", "3"));
                completions.removeIf(s -> !s.startsWith(input));
            }
        }

        return completions.isEmpty() ? super.addTabCompletionOptions(sender, args, pos) : completions;
    }
} 