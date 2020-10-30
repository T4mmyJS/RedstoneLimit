package me.QuantumDev.RedstoneLimit.commands;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import me.QuantumDev.RedstoneLimit.RedstoneLimit;
import org.bukkit.command.CommandSender;

public abstract class RedstoneLimitCommand {

    private final boolean allowConsole;

    public RedstoneLimitCommand(boolean allowConsole) {
        this.allowConsole = allowConsole;
    }

    @Getter
    public boolean allowsConsole() {
        return allowConsole;
    }

    public abstract void execute(CommandSender sender, String[] args, RedstoneLimit plugin);

}
