package com.github.tsuoihito.worldeditremote;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldEditRemote extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WandListener(this), this);
    }

    @Override
    public void onDisable() {
    }
}
