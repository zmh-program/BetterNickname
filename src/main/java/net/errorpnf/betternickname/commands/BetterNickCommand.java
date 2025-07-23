package net.errorpnf.betternickname.commands;

import net.errorpnf.betternickname.utils.AutoReroll;
import net.errorpnf.betternickname.utils.BookParser;
import net.errorpnf.betternickname.utils.IsInLobby;
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
import java.util.Random;

public class BetterNickCommand extends CommandBase {
    public static boolean cancelBookGui = false;
    public static final String[] nickRank = {"&7Default", "&a[VIP]", "&a[VIP&6+&a]", "&b[MVP]", "&b[MVP&c+&b]"};
    public static final String[] commandRank = {"default", "vip", "vip+", "mvp", "mvp+"};


    @Override
    public String getCommandName() {
        return "betternick";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/betternick";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            helpCommand();
            return;
        }

        if (!args[0].equals("help") && !args[0].equals("run") && !args[0].equals("claim") && !args[0].equals("rank") && !args[0].equals("autoreroll")) {
            helpCommand();
            return;
        }

        if (args[0].equals("help")) {
            helpCommand();
            return;
        }

        if (args[0].equals("run")) {
            rerollNick();
            return;
        } else if (args[0].equals("claim")) {
            claimNick();
            return;
        } else if (args[0].equals("autoreroll")) {
            AutoReroll.toggleAutoReroll();
            return;
        }

        if (args[0].equals("rank")) {
            if (isHypixel()) {
                if (true) {
                    if (args.length > 1) {
                        if (args[1] != null) {
                            if (args[1].equalsIgnoreCase("random")) {
                                Random random = new Random();
                                int randomRank = random.nextInt(5);

                                sendMessage("Set your nick rank to: " + nickRank[randomRank]);
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank " + commandRank[randomRank]);

                            } else if (args[1].equalsIgnoreCase("default") || args[1].equalsIgnoreCase("non")) {
                                sendMessage("Set your nick rank to: &7Default");
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank default");

                            } else if (args[1].equalsIgnoreCase("vip")) {
                                sendMessage("Set your nick rank to: &a[VIP]");
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank vip");

                            } else if (args[1].equalsIgnoreCase("vip+")) {
                                sendMessage("Set your nick rank to: &a[VIP&6+&a]");
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank vip+");

                            } else if (args[1].equalsIgnoreCase("mvp")) {
                                sendMessage("Set your nick rank to: &b[MVP]");
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank mvp");

                            } else if (args[1].equalsIgnoreCase("mvp+")) {
                                sendMessage("Set your nick rank to: &b[MVP&c+&b]");
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick rank mvp+");
                            }
                        } else {
                            sendMessage("&cPlease provide the rank you wish to use.");
                        }
                    } else {
                        sendMessage("&cPlease provide the rank you wish to use.");
                    }
                } else {
                    sendMessage("&cYou must be in the lobby to do this!");
                }

            } else {
                sendMessage("&cYou must be connected to Hypixel to use this command.");
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) { // Check if only one argument is provided (subcommand)
            String input = args[0].toLowerCase(); // Convert input to lowercase for case-insensitive matching

            // Add your top-level subcommands to the tab completion list
            completions.add("run");
            completions.add("claim");
            completions.add("rank");
            completions.add("autoreroll");

            // Optionally, add more top-level subcommands here

            // Filter the completions based on the input provided so far
            completions.removeIf(s -> !s.startsWith(input));
        } else if (args.length == 2 && args[0].equalsIgnoreCase("rank")) {
            String input = args[1].toLowerCase(); // Convert input to lowercase for case-insensitive matching

            // Add your rank options to the tab completion list
            completions.addAll(Arrays.asList("random", "default", "vip", "vip+", "mvp", "mvp+"));

            // Filter the completions based on the input provided so far
            completions.removeIf(s -> !s.startsWith(input));
        }

        return completions.isEmpty() ? super.addTabCompletionOptions(sender, args, pos) : completions;
    }

    public static boolean shouldCancelBookGui() {
        return cancelBookGui;
    }

    public static void setCancelBookGui(boolean cancelBookGui) {
        BetterNickCommand.cancelBookGui = cancelBookGui;
    }


    public static void claimNick() {
        if (isHypixel()) {
            if (true) {
                if (AutoReroll.isAutoRerollEnabled()) {
                    AutoReroll.toggleAutoReroll();
                }

                if (BookParser.getGeneratedNickname() != null) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick actuallyset " + BookParser.getGeneratedNickname());
                    sendMessage("Your nickname has been set to: &b" + BookParser.getGeneratedNickname());
                    cancelBookGui = true;
                    AutoReroll.claimedName = BookParser.getGeneratedNickname();
                    AutoReroll.hasClaimedName = true;
                } else {
                    sendMessage("&cGenerated nickname is null. Please try generating a nickname with &e/betternick run&c before attempting to claim a username.");
                }
            } else {
                sendMessage("&cYou must be in the lobby to do this!");
            }
        } else {
            sendMessage("&cYou must be connected to Hypixel to use this command.");
        }
    }

    public static void rerollNick() {
        if (isHypixel()) {
            if (true) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick help setrandom");
                cancelBookGui = true;
            } else {
                sendMessage("&cYou must be in the lobby to do this!");
            }
        } else {
            sendMessage("&cYou must be connected to Hypixel to use this command.");
        }
    }

    public static void helpCommand() {
        // displays help
        sendMessage("&e===================================================");
        sendMessage("&b/betternick run &e- Generates a random nickname for you (but doesn't automatically claim it).");
        sendMessage("&b/betternick claim &e- Claims the username you generated.");
        sendMessage("&b/betternick autoreroll &e- Toggles auto-reroll to continuously generate nicknames until match found.");
        sendMessage("&b/betternick rank &e- Sets your nick rank.");
        sendMessage("&b/betternick help &e- Displays this message.");
        sendMessage("&b/betternickconfig ... &e- Set configuration.");
        sendMessage("&b/betternickconfig help &e- Displays the configuration help message.");
        sendMessage("&e===================================================");
    }
    
    private static void sendMessage(String message) {
        String formattedMessage = message.replace("&", "§");
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + formattedMessage)
        );
    }
    
    private static boolean isHypixel() {
        try {
            if (Minecraft.getMinecraft().getCurrentServerData() != null) {
                String serverIp = Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase();
                return serverIp.contains("hypixel.net") || serverIp.contains("hypixel.io");
            }
        } catch (Exception e) {
            // Ignore any errors
        }
        return false;
    }
}
