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
    public static boolean autoclaim = false;
    public static double rerollDelay = 3.0;
    public static int maxLength = 0; // 0 means no limit
    public static boolean allowNumbers = true;
    public static boolean allowUnderscores = true;
    
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
    
    // Quick rule presets
    public static void applyRulePreset(int ruleNumber) {
        switch (ruleNumber) {
            case 1:
                // Rule 1: No numbers, max 8 characters
                allowNumbers = false;
                maxLength = 8;
                break;
            default:
                // Invalid rule number
                break;
        }
    }
    
    public static String getRulePresetDescription(int ruleNumber) {
        switch (ruleNumber) {
            case 1:
                return "No numbers, max 8 characters";
            default:
                return "Unknown rule preset";
        }
    }
}

