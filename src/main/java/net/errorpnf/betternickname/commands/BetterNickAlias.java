package net.errorpnf.betternickname.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class BetterNickAlias extends CommandBase {
    private final BetterNickCommand mainCommand = new BetterNickCommand();

    @Override
    public String getCommandName() {
        return "bn";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/bn";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        mainCommand.processCommand(sender, args);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return mainCommand.addTabCompletionOptions(sender, args, pos);
    }
}