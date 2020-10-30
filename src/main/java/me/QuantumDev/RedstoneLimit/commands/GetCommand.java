package me.QuantumDev.RedstoneLimit.commands;

import me.QuantumDev.RedstoneLimit.RedstoneLimit;
import me.QuantumDev.RedstoneLimit.Utils;
import org.bukkit.command.CommandSender;

public class GetCommand extends RedstoneLimitCommand {

    public GetCommand() {
        super(true);
    }

    public void execute(CommandSender sender, String[] args, RedstoneLimit plugin) {
        if (args.length != 1) {
            sender.sendMessage(Utils.colour("&3Usage: &c/rl get"));
            return;
        }

        sender.sendMessage(Utils.colour("&3The redstone limit is &c" + plugin.getRedstoneLimit() + " &3blocks!"));
    }
}
