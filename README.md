# BetterNickname
A Forge 1.8.9 Hypixel Mod to improve the /nick system for MVP++ players.

### Warning:
This mod and its features are use at your own risk. It is to be used at the user's discretion with full knowledge that it may not be allowed.

## Features
- Automatically reroll nicknames with advanced filtering
  - Rerolls your nickname until a certain phrase is met
  - Customizable reroll interval
  - Multiple filtering options: text matching, exclusion, length limits, number/underscore control
  - Automatically claims the name once condition is met
  - Automatically sets language to English for consistent nickname parsing
- Auto-jump feature to stay active (jumps every 30-90 seconds randomly)
- Removes the need for the bulky written-book-based GUI system implemented by Hypixel
- Let's you set a random nick rank `/betternick rank random`
- Contains a HUD showing the current nick rank and name 
- Simple command-based configuration system

## Commands

### Main Commands
- `/betternick help` (or `/bn help`) - Shows help message with all available commands
- `/betternick run` (or `/bn run`) - Generates a random nickname (doesn't automatically claim it)
- `/betternick claim` (or `/bn claim`) - Claims the generated username (automatically disables auto-jump)
- `/betternick auto [rule]` (or `/bn auto [rule]`) - Toggles auto-reroll to continuously generate nicknames until match found
  - Optional rule parameter (1-3) applies preset filters before starting auto-reroll
  - Automatically enables auto-jump when starting auto-reroll to keep you active
- `/betternick rank <rank|random>` (or `/bn rank <rank|random>`) - Sets your nick rank
  - Available ranks: `default`, `vip`, `vip+`, `mvp`, `mvp+`, `random`
- `/autojump` - Toggles auto-jump (jumps every 30-90 seconds randomly)

**Note:** `/bn` is a convenient alias for `/betternick` - all commands work the same way!

### Configuration Commands
Use `/betternickconfig` to configure the mod settings:

- `/betternickconfig showrank <true/false>` - Toggle rank display in HUD
- `/betternickconfig matchtext <text>` - Set text to match in nickname (for auto-reroll)
- `/betternickconfig excludetext <text>` - Set text to exclude in nickname (for auto-reroll)
- `/betternickconfig autoclaim <true/false>` - Toggle auto-claim when match is found
- `/betternickconfig rerolldelay <0.1-10.0>` - Set delay between rerolls (in seconds, supports decimals)
- `/betternickconfig maxlength <0-16>` - Set maximum length for generated names (0 = no limit)
- `/betternickconfig allownumbers <true/false>` - Allow numbers in generated names
- `/betternickconfig allowunderscores <true/false>` - Allow underscores in generated names
- `/betternickconfig rule <1|2|3>` - Apply quick rule presets:
  - Rule 1: No numbers, max 8 characters
  - Rule 2: No numbers, max 7 characters  
  - Rule 3: No numbers, max 6 characters
- `/betternickconfig list` - Show all current settings
- `/betternickconfig help` - Show configuration help

### Auto-Reroll Feature
The auto-reroll feature will continuously generate new nicknames until one matches your criteria:

1. **Configure filtering criteria** (all optional):
   - `/betternickconfig matchtext <text>` - Set text that must be in the nickname
   - `/betternickconfig excludetext <text>` - Set characters to exclude from nickname
   - `/betternickconfig maxlength <0-16>` - Set maximum length (0 = no limit)
   - `/betternickconfig allownumbers <true/false>` - Control whether numbers are allowed
   - `/betternickconfig allowunderscores <true/false>` - Control whether underscores are allowed

2. **Configure behavior**:
   - `/betternickconfig autoclaim <true/false>` - Auto-claim matching names or notify when found
   - `/betternickconfig rerolldelay <0.1-10.0>` - Set delay between rerolls (in seconds, supports decimals)

3. **Start auto-reroll**:
   - `/betternick auto` - Toggle auto-reroll on/off
   - `/betternick auto <1|2|3>` - Apply rule preset and start auto-reroll

The system will continuously generate nicknames and check all your criteria. When a match is found, it will either automatically claim the name (if autoclaim is enabled) or notify you to manually claim it.

**Language Setting**: The mod automatically sets your language to English (`/lang en`) once per game session when you first use nickname generation to ensure consistent parsing of server responses, regardless of your current language setting.

### Auto-Jump Feature
The auto-jump feature helps you stay active while waiting for nickname generation:

- Use `/autojump` to toggle auto-jump on/off
- When enabled, your character will jump automatically every 30-90 seconds (random interval)
- Only jumps when you're on the ground
- Automatically enabled when using `/betternick auto` and disabled when claiming names
- Useful for staying active while using auto-reroll

### Example Usage
```bash
# Method 1: Use quick rule preset via config command
/betternickconfig rule 1  # Applies: no numbers, max 8 characters
/betternickconfig matchtext dragon
/betternickconfig autoclaim true
/betternickconfig rerolldelay 0.5  # Fast reroll every 0.5 seconds
/bn auto  # Using the short alias

# Method 2: Use rule preset directly with auto command (auto-jump enabled automatically)
/betternickconfig matchtext dragon
/betternickconfig autoclaim true
/betternickconfig rerolldelay 0.5
/bn auto 2  # Applies rule 2 (max 7 chars, no numbers), enables auto-jump, and starts auto-reroll

# Method 3: Manual configuration (auto-jump enabled automatically)
/betternickconfig matchtext dragon
/betternickconfig maxlength 8
/betternickconfig allownumbers false
/betternickconfig allowunderscores false
/betternickconfig autoclaim true
/betternickconfig rerolldelay 1.5  # Reroll every 1.5 seconds
/bn auto  # Enables auto-jump automatically

# Quick examples with short alias
/bn run     # Generate a nickname
/bn claim   # Claim the nickname
/bn rank mvp  # Set rank to MVP

# Note: Auto-jump is automatically enabled with /bn auto and disabled when claiming
```

## Installation
1. Make sure you have Minecraft Forge 1.8.9 installed
2. Download the mod jar file
3. Place it in your `mods` folder
4. Launch Minecraft with the Forge profile

## Building from Source
1. Clone this repository
2. Run `./gradlew build` (or `gradlew.bat build` on Windows)
3. The built jar will be in `build/libs/`

### Questions/Suggestions
If you have any questions or suggestions about the mod then feel free to add me on Discord:

[![Discord](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/2KGUAvpA)

## Version Information
- **Minecraft Version:** 1.8.9
- **Forge Version:** Legacy Forge
- **Java Version:** 8+

## License
This project is licensed under the terms specified in the LICENSE file.
