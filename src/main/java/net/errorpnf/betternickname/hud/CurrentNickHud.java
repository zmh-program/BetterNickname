package net.errorpnf.betternickname.hud;

import net.errorpnf.betternickname.config.BetterNickConfig;
import net.errorpnf.betternickname.utils.BookParser;
import net.errorpnf.betternickname.utils.IsInLobby;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Simple HUD to display current nickname information
 * Renders in the top-left corner of the screen
 */
public class CurrentNickHud {
    
    public CurrentNickHud() {
        // Simple initialization
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (shouldShow()) {
            String text = getText(false);
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            
            // Render the text in the top-left corner with some padding
            fontRenderer.drawStringWithShadow(text, 10, 10, 0xFFFFFF);
        }
    }

    public String getText(boolean example) {
        if (example) {
            return "§eGenerated Nickname";
        }
        
        if (BetterNickConfig.showRank) {
            if (BookParser.getCurrentRank() == null) {
                if (BookParser.getGeneratedNickname() != null) {
                    return "§f" + BookParser.getGeneratedNickname();
                }
                return "§fNot Generated";
            } else if (BookParser.getGeneratedNickname() == null){
                return "§f" + BookParser.getCurrentRank() + "Player";
            } else {
                return "§f" + BookParser.getCurrentRank() + BookParser.getGeneratedNickname();
            }
        } else {
            if (BookParser.getGeneratedNickname() == null) {
                return "§fNot Generated";
            } else {
                return "§f" + BookParser.getGeneratedNickname();
            }
        }
    }

    public boolean shouldShow() {
        if (Minecraft.getMinecraft().thePlayer == null) {
            return false;
        }
        
        if (true) {
            return true;
        } else {
            return true;
        }
    }
}
