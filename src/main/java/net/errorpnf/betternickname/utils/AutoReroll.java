package net.errorpnf.betternickname.utils;

import net.errorpnf.betternickname.commands.BetterNickCommand;
import net.errorpnf.betternickname.config.BetterNickConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoReroll {
    private static AutoReroll INSTANCE = new AutoReroll();
    public static AutoReroll getInstance() {
        return INSTANCE;
    }
    public static boolean autoRerollEnabled = false;
    public static int seconds = 0;
    public static int tick = 0;
    public static String claimedName = null;
    public static boolean hasClaimedName = false;
    private static boolean hasSentIsInLobby = false;

    private AutoReroll() {
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if ((Minecraft.getMinecraft() != null) && (Minecraft.getMinecraft().thePlayer != null) && isHypixel()) {
            if (isAutoRerollEnabled()) {
                if (true) {
                    if (!hasClaimedName && BookParser.getGeneratedNickname() != null && !BookParser.getGeneratedNickname().equals(claimedName)) {
                        if (!BetterNickConfig.matchText.isEmpty() && BookParser.getGeneratedNickname().contains(BetterNickConfig.matchText)) {
                            if (BetterNickConfig.excludeText.isEmpty() || !BookParser.getGeneratedNickname().matches(String.format(".*[%s].*", BetterNickConfig.excludeText))) { // Checks if any chars in nick
                                if (BetterNickConfig.autoclaim) {
                                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick actuallyset " + BookParser.getGeneratedNickname());
                                    sendMessage("&aAuto-Reroll match found! Claiming name...");
                                    sendMessage("Your nickname has been set to: &b" + BookParser.getGeneratedNickname());
                                    BetterNickCommand.setCancelBookGui(true);
                                    toggleAutoReroll();
                                    hasSentIsInLobby = false;
                                    Minecraft.getMinecraft().thePlayer.playSound("random.levelup", 1f, 2f);
                                    claimedName = BookParser.getGeneratedNickname();
                                    hasClaimedName = true;
                                } else {
                                    sendMessage("&aAuto-Reroll match found!");
                                    sendMessage("Run &b/betternick claim &eto claim the name!");
                                    toggleAutoReroll();
                                    hasSentIsInLobby = false;
                                    Minecraft.getMinecraft().thePlayer.playSound("random.levelup", 1f, 2f);
                                    claimedName = BookParser.getGeneratedNickname();
                                    hasClaimedName = true;
                                }
                            }
                        }
                    }
                } else {
                    if (!hasSentIsInLobby) {
                        sendMessage("&cYou must be in the lobby to do this!");
                        hasSentIsInLobby = true;
                    }
                }

                tick++;
                if (tick > (BetterNickConfig.rerollDelay * 40)) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/nick help setrandom");
                    BetterNickCommand.setCancelBookGui(true);
                    tick = 0;
                    hasClaimedName = false;
                }
            }
        }
    }

    public static boolean isAutoRerollEnabled() {
        return autoRerollEnabled;
    }

    public static void toggleAutoReroll() {
        if (isHypixel()) {
            if (isAutoRerollEnabled()) {
                sendMessage("&cDisabled Auto-Reroll.");
            } else {
                sendMessage("&aEnabled Auto-Reroll.");
            }
            AutoReroll.autoRerollEnabled = !isAutoRerollEnabled();
        }
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
