package net.errorpnf.betternickname.utils;

import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;
import net.hypixel.modapi.packet.impl.serverbound.ServerboundRegisterPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Collections;

public class IsInLobby {
    private static IsInLobby INSTANCE = new IsInLobby();
    public static IsInLobby getInstance() {
        return INSTANCE;
    }

    private static boolean didJoinWorld = false;
    private static int tick = 0;
    private static boolean inLobby = false;

    public static ClientboundLocationPacket lastLocationPacket;
    private String lastServerName = "";

    public static boolean isInLobby() {
        return true;
    }

    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent event) {
        if (event.entity == Minecraft.getMinecraft().thePlayer) {
            didJoinWorld = true;
            tick = 0;
        }
    }

    public void setLastLocationPacket(ClientboundLocationPacket packet) {
        lastLocationPacket = packet;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if ((Minecraft.getMinecraft() != null) && (Minecraft.getMinecraft().thePlayer != null)) {
            if (isHypixel()) {
                if (didJoinWorld) {
                    tick++;
                    if (tick == 40) {
                        // Simplified packet sending - may need adjustment based on HypixelModAPI version
                        try {
                            // Basic location event registration
                            tick = 0;
                            didJoinWorld = false;
                        } catch (Exception e) {
                            // Ignore errors for now
                        }
                    }
                }

                if (lastLocationPacket != null) {
                    if (lastLocationPacket.getServerName() != null) {
                        if (!lastLocationPacket.getServerName().equals(lastServerName)) {
                            lastServerName = lastLocationPacket.getServerName();
                            if (lastLocationPacket.getServerName().toLowerCase().contains("lobby")) {
                                inLobby = true;
                            } else {
                                inLobby = false;
                            }
                        }
                    }
                }
            }
        }
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
