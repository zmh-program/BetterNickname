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
- Auto-jump feature to stay active (jumps every 30-90 seconds randomly)
- Removes the need for the bulky written-book-based GUI system implemented by Hypixel
- Let's you set a random nick rank `/betternick rank random`
- Contains a HUD showing the current nick rank and name 
- Simple command-based configuration system

## Commands

### Main Commands
- `/betternick help` - Shows help message with all available commands
- `/betternick run` - Generates a random nickname (doesn't automatically claim it)
- `/betternick claim` - Claims the generated username
- `/betternick auto` - Toggles auto-reroll to continuously generate nicknames until match found
- `/betternick rank <rank|random>` - Sets your nick rank
  - Available ranks: `default`, `vip`, `vip+`, `mvp`, `mvp+`, `random`
- `/autojump` - Toggles auto-jump (jumps every 30-90 seconds randomly)

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
- `/betternickconfig rule 1` - Apply quick rule preset 1 (no numbers, max 8 characters)
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

The system will continuously generate nicknames and check all your criteria. When a match is found, it will either automatically claim the name (if autoclaim is enabled) or notify you to manually claim it.

### Auto-Jump Feature
The auto-jump feature helps you stay active while waiting for nickname generation:

- Use `/autojump` to toggle auto-jump on/off
- When enabled, your character will jump automatically every 30-90 seconds (random interval)
- Only jumps when you're on the ground
- Useful for staying active while using auto-reroll

### Example Usage
```bash
# Method 1: Use quick rule preset
/betternickconfig rule 1  # Applies: no numbers, max 8 characters
/betternickconfig matchtext dragon
/betternickconfig autoclaim true
/betternickconfig rerolldelay 0.5  # Fast reroll every 0.5 seconds

# Method 2: Manual configuration
/betternickconfig matchtext dragon
/betternickconfig maxlength 8
/betternickconfig allownumbers false
/betternickconfig allowunderscores false
/betternickconfig autoclaim true
/betternickconfig rerolldelay 1.5  # Reroll every 1.5 seconds

# Start the auto-reroll process
/betternick auto

# Optional: Enable auto-jump to stay active
/autojump
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
