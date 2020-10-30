package me.QuantumDev.RedstoneLimit.listeners;

import me.QuantumDev.RedstoneLimit.RedstoneLimit;
import me.QuantumDev.RedstoneLimit.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final RedstoneLimit plugin;

    public BlockPlaceListener(RedstoneLimit plugin) {
        this.plugin = plugin;
        registerEvents();
    }

    private void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void BlockPlaceEvent(BlockPlaceEvent event) {

        String blockName = event.getBlock().getType().toString();
        boolean isRedstone = plugin.getRestrictedBlocks().contains(blockName);

        if (!isRedstone || !plugin.restrict()) {
            event.setCancelled(false);
            return;
        }

        final int minX = 0;
        final int minZ = 0;
        final int maxX = 15;
        final int maxY = 255;
        final int maxZ = 15;
        int count = 0;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = 0; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    blockName = event.getBlock().getChunk().getBlock(x, y, z).getType().toString();
                    isRedstone = plugin.getRestrictedBlocks().contains(blockName);

                    if (isRedstone) {
                        count++;

                    }
                }
            }

            if (count == (plugin.getRedstoneLimit() + 1) || (count > plugin.getRedstoneLimit() + 1)) {
                if (plugin.getPlayerBypass(event.getPlayer().getUniqueId())) {
                    event.getPlayer().sendMessage(Utils.colour("&3This chunk exceeds maximum redstone, but you bypassed!"));
                    event.setCancelled(false);
                    return;
                }

                event.getPlayer().sendMessage(Utils.colour("&cThis chunk exceeds maximum redstone!"));
                event.setCancelled(true);
                return;
            }

            event.setCancelled(false);
        }
    }

}
