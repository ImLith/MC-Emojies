package com.lith.emojies;

import com.lith.emojies.config.ConfigManager;
import com.lith.emojies.events.player.PlayerChat;
import com.lith.lithcore.config.MainPlugin;

public class Plugin extends MainPlugin<ConfigManager> {
    public static Plugin plugin;

    public void onEnable() {
        Plugin.plugin = this;

        new ConfigManager(this);
        this.registerEvents();

        Static.log.info("Plugin enabled");
    }

    public void onDisable() {
        Static.log.info("Plugin disabled");
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
    }
}