package com.lith.emojies;

import com.lith.emojies.config.ConfigManager;
import com.lith.emojies.events.PlayerChatEvent;
import com.lith.lithcore.abstractClasses.AbstractPlugin;
import com.lith.lithcore.helpers.ReloadConfigCmd;
import com.lith.emojies.Static.Commands;

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

    @Override
    protected void registerCommands() {
        new ReloadConfigCmd<Plugin>(this, Commands.Permission.RELOAD, Commands.Name.RELOAD);
    }
}