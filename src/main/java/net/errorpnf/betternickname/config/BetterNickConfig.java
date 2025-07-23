package net.errorpnf.betternickname.config;

import net.errorpnf.betternickname.hud.CurrentNickHud;
import org.lwjgl.input.Keyboard;

/**
 * Simple configuration class to manage mod settings via commands
 */
public class BetterNickConfig {
    
    // HUD Options
    public static boolean showRank = true;
    
    // HUD instance
    public static CurrentNickHud hud = new CurrentNickHud();
    
    // Auto-Reroll Settings
    public static String matchText = "";
    public static String excludeText = "";
    public static boolean autoclaim = true;
    public static int rerollDelay = 3;
    
    // Keybinds (using LWJGL Keyboard constants)
    public static int autoRerollKeybind = Keyboard.KEY_Y;
    public static int rerollNickKeybind = Keyboard.KEY_NONE;
    public static int claimKeybind = Keyboard.KEY_NONE;
    
    // Auto-reroll state
    public static boolean autoRerollEnabled = false;
    
    public BetterNickConfig() {
        // Simple initialization
    }
    
    public static void save() {
        // TODO: Implement config file saving if needed
    }
    
    public static void load() {
        // TODO: Implement config file loading if needed
    }
}

