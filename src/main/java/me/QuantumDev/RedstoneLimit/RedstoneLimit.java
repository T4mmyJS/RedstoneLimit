package me.QuantumDev.RedstoneLimit;

import me.QuantumDev.RedstoneLimit.commands.CommandManager;
import me.QuantumDev.RedstoneLimit.listeners.BlockPlaceListener;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class RedstoneLimit extends JavaPlugin {

    private ConsoleCommandSender console;
    private int redstoneLimit;
    private final Map<UUID, Boolean> playersBypassing = new HashMap<UUID, Boolean>();
    private boolean restrict;
    private List<String> restrictedBlocks = new ArrayList<String>();

    @Override
    public void onEnable() {
        console = getServer().getConsoleSender();
        new BlockPlaceListener(this);

        this.redstoneLimit = getConfig().getInt("redstone_limit");
        this.restrict = getConfig().getBoolean("restrict");
        this.restrictedBlocks = getConfig().getStringList("restricted");

        saveDefaultConfig();
        getServer().getPluginCommand("redstonelimit").setExecutor(new CommandManager(this));

        console.sendMessage(Utils.colour("&3Enabled &cRedstoneLimit&3!"));

    }

    @Override
    public void onDisable() {
        console.sendMessage(Utils.colour("&cDisabled &3RedstoneLimit&c!"));
    }

    public int getRedstoneLimit() {
        return this.redstoneLimit;
    }

    public void setRedstoneLimit(int limit) {
        this.redstoneLimit = limit;
        getConfig().set("redstone_limit", redstoneLimit);
        saveConfig();
    }


    public Boolean getPlayerBypass(UUID player) {
        if (this.playersBypassing.containsKey(player)) return this.playersBypassing.get(player);
        return false;
    }

    public void setPlayerBypass(UUID player, boolean bypass) {
        this.playersBypassing.put(player, bypass);
    }

    public boolean restrict() {
        return this.restrict;
    }

    public List<String> getRestrictedBlocks() {
        return this.restrictedBlocks;
    }

}
