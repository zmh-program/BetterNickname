package net.errorpnf.betternickname;

import net.errorpnf.betternickname.commands.AutoJumpCommand;
import net.errorpnf.betternickname.commands.BetterNickAlias;
import net.errorpnf.betternickname.commands.BetterNickCommand;
import net.errorpnf.betternickname.commands.ConfigCommand;
import net.errorpnf.betternickname.commands.NickDebug;
import net.errorpnf.betternickname.config.BetterNickConfig;
import net.errorpnf.betternickname.utils.*;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.handler.ClientboundPacketHandler;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPartyInfoPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPingPacket;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;
import net.hypixel.modapi.serializer.PacketSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import io.netty.buffer.Unpooled;

/**
 * The entrypoint of the BetterNickname mod that initializes it.
 *
 * @see Mod
 * @see FMLInitializationEvent
 */
@Mod(modid = BetterNickname.MODID, name = BetterNickname.NAME, version = BetterNickname.VERSION)
public class BetterNickname {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static BetterNickname INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static BetterNickConfig config;

    // Roll a new nickname: /nick help setrandom
    // Claim the nickname: /nick actuallyset <generated username from above>
    // Set Rank: /nick rank <rank>

    //TODO Make sure people are already nicked before they can use /betternick stuff. Default /betternick to just /nick maybe? something like that


    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new BetterNickConfig();
        MinecraftForge.EVENT_BUS.register(BookParser.getInstance());
        ClientCommandHandler.instance.registerCommand(new NickDebug());
        ClientCommandHandler.instance.registerCommand(new BetterNickCommand());
        ClientCommandHandler.instance.registerCommand(new BetterNickAlias());
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());
        ClientCommandHandler.instance.registerCommand(new AutoJumpCommand());
        MinecraftForge.EVENT_BUS.register(CancelBookGUI.getInstance());
        MinecraftForge.EVENT_BUS.register(AutoReroll.getInstance());
        MinecraftForge.EVENT_BUS.register(IsInLobby.getInstance());
        MinecraftForge.EVENT_BUS.register(AutoJump.getInstance());
        //MinecraftForge.EVENT_BUS.register(PacketLogger.getInstance());
        //ClientRegistry.registerKeyBinding(PacketLogger.toggleSneakKey);
        //ClientCommandHandler.instance.registerCommand(new BoolCommand());

        HypixelModAPI.getInstance().subscribeToEventPacket(ClientboundLocationPacket.class);
        HypixelModAPI.getInstance().registerHandler(new ClientboundPacketHandler() {
            @Override
            public void onPingPacket(ClientboundPingPacket packet) {
                //System.out.println(packet.toString());
            }

            @Override
            public void onLocationEvent(ClientboundLocationPacket packet) {
                //System.out.println(packet.getMode());
                IsInLobby.getInstance().setLastLocationPacket(packet);
            }

            @Override
            public void onPartyInfoPacket(ClientboundPartyInfoPacket packet) {
                //System.out.println(packet.toString());
            }
        });
    }
}
