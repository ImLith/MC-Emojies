package com.lith.emojies;

import com.lith.emojies.config.ConfigManager;
import com.lith.emojies.events.PlayerChatEvent;
import com.lith.lithcore.abstractClasses.AbstractPlugin;

public class Plugin extends AbstractPlugin<Plugin, ConfigManager> {
    public static Plugin plugin;
    public ConfigManager configs = new ConfigManager(this);

    @Override
    protected void registerEvents() {
        registerEvent(new PlayerChatEvent(this));
    }
}