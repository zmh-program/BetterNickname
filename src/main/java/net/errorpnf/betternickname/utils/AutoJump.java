package net.errorpnf.betternickname.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class AutoJump {
    private static AutoJump INSTANCE = new AutoJump();
    public static AutoJump getInstance() {
        return INSTANCE;
    }
    
    public static boolean autoJumpEnabled = false;
    private static int tick = 0;
    private static int nextJumpTime = 0;
    private static Random random = new Random();
    
    private AutoJump() {
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if ((Minecraft.getMinecraft() != null) && (Minecraft.getMinecraft().thePlayer != null)) {
            if (isAutoJumpEnabled()) {
                tick++;
                
                // Check if it's time to jump
                if (tick >= nextJumpTime) {
                    // Make the player jump
                    if (Minecraft.getMinecraft().thePlayer.onGround) {
                        Minecraft.getMinecraft().thePlayer.jump();
                    }
                    
                    // Set next jump time (30-90 seconds = 600-1800 ticks, since 20 ticks = 1 second)
                    int minTicks = 30 * 20; // 30 seconds
                    int maxTicks = 90 * 20; // 90 seconds
                    nextJumpTime = tick + minTicks + random.nextInt(maxTicks - minTicks + 1);
                }
            }
        }
    }

    public static boolean isAutoJumpEnabled() {
        return autoJumpEnabled;
    }

    public static void toggleAutoJump() {
        autoJumpEnabled = !autoJumpEnabled;
        
        if (autoJumpEnabled) {
            sendMessage("&aEnabled Auto-Jump. Will jump every 30-90 seconds.");
            // Set initial jump time (30-90 seconds from now)
            int minTicks = 30 * 20; // 30 seconds
            int maxTicks = 90 * 20; // 90 seconds
            nextJumpTime = tick + minTicks + random.nextInt(maxTicks - minTicks + 1);
        } else {
            sendMessage("&cDisabled Auto-Jump.");
            tick = 0;
            nextJumpTime = 0;
        }
    }

    private static void sendMessage(String message) {
        String formattedMessage = message.replace("&", "§");
        Minecraft.getMinecraft().thePlayer.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[BetterNick] " + EnumChatFormatting.RESET + formattedMessage)
        );
    }
} 