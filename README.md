# BetterNickname
A Forge 1.8.9 Hypixel Mod to improve the /nick system for MVP++ players.

### Warning:
This mod and its features are use at your own risk. It is to be used at the user's discretion with full knowledge that it may not be allowed.

## Features
- Automatically reroll nicknames
  - Rerolls your nickname until a certain phrase is met
  - Let's you customize reroll interval
  - Automatically claims the name once condition is met
- Removes the need for the bulky written-book-based GUI system implemented by Hypixel
- Let's you set a random nick rank `/betternick rank random`
- Contains a HUD showing the current nick rank and name 
- Simple command-based configuration system

## Commands

### Main Commands
- `/betternick help` - Shows help message with all available commands
- `/betternick randomname` - Generates a random nickname (doesn't automatically claim it)
- `/betternick claimname` - Claims the generated username
- `/betternick rank <rank|random>` - Sets your nick rank
  - Available ranks: `default`, `vip`, `vip+`, `mvp`, `mvp+`, `random`

### Configuration Commands
Use `/betternickconfig` to configure the mod settings:

- `/betternickconfig showrank <true/false>` - Toggle rank display in HUD
- `/betternickconfig matchtext <text>` - Set text to match in nickname (for auto-reroll)
- `/betternickconfig excludetext <text>` - Set text to exclude in nickname (for auto-reroll)
- `/betternickconfig autoclaim <true/false>` - Toggle auto-claim when match is found
- `/betternickconfig rerolldelay <1-10>` - Set delay between rerolls (in seconds)
- `/betternickconfig list` - Show all current settings
- `/betternickconfig help` - Show configuration help

### Auto-Reroll Feature
The auto-reroll feature will continuously generate new nicknames until one matches your criteria:

1. Use `/betternickconfig matchtext <text>` to set what text should be in the nickname
2. Optionally use `/betternickconfig excludetext <text>` to exclude certain characters
3. Set `/betternickconfig autoclaim true` if you want it to automatically claim matching names
4. Adjust `/betternickconfig rerolldelay <seconds>` to control how fast it rerolls
5. Press Y (default keybind) to start/stop auto-reroll, or use `/betternick randomname` to manually reroll

### Keybinds
- **Y** - Toggle auto-reroll on/off (can be changed in code if needed)

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
