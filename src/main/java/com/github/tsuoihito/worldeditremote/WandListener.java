package com.github.tsuoihito.worldeditremote;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.permission.ActorSelectorLimits;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.util.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WandListener implements Listener {

    private final WorldEditRemote plugin;

    public WandListener(WorldEditRemote plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() == null) {
            return;
        }

        WorldEdit worldEdit = WorldEdit.getInstance();

        if (!event.getItem().getType().getKey().toString().equals(worldEdit.getConfiguration().wandItem)) {
            return;
        }

        Player actor = BukkitAdapter.adapt(event.getPlayer());
        LocalSession localSession = worldEdit.getSessionManager().get(actor);
        RegionSelector regionSelector = localSession.getRegionSelector(actor.getWorld());

        Location blockLocation = actor.getBlockTrace(plugin.getServer().getViewDistance() * 16);
        if (blockLocation == null) {
            return;
        }

        if (event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            regionSelector.selectPrimary(blockLocation.toVector().toBlockPoint(), new ActorSelectorLimits(worldEdit.getConfiguration(), actor));
            regionSelector.explainPrimarySelection(actor, localSession, blockLocation.toVector().toBlockPoint());
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            regionSelector.selectSecondary(blockLocation.toVector().toBlockPoint(), new ActorSelectorLimits(worldEdit.getConfiguration(), actor));
            regionSelector.explainSecondarySelection(actor, localSession, blockLocation.toVector().toBlockPoint());
        }

    }
}
