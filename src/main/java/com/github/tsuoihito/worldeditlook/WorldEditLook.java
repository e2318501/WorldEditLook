package com.github.tsuoihito.worldeditlook;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldEditLook extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WandListener(this), this);
    }

    @Override
    public void onDisable() {
    }
}
