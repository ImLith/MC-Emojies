package com.lith.emojies;

import com.lith.emojies.config.ConfigManager;
import com.lith.emojies.events.PlayerChatEvent;
import com.lith.lithcore.abstractClasses.AbstractPlugin;

public class Plugin extends AbstractPlugin<Plugin, ConfigManager> {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        configs = new ConfigManager(this);
        super.onEnable();
    }

    @Override
    protected void registerEvents() {
        registerEvent(new PlayerChatEvent());
    }
}