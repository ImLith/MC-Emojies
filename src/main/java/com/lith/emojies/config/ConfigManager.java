package com.lith.emojies.config;

import java.util.HashMap;
import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import com.lith.emojies.Static;
import com.lith.lithcore.abstractClasses.MainPlugin;
import com.lith.lithcore.abstractClasses.PluginConfigManager;

public class ConfigManager extends PluginConfigManager {
    private Map<String, String> emojies;

    public ConfigManager(final MainPlugin<ConfigManager> plugin) {
        super(plugin);

        this.getEmojiesFromConfig();
    }

    public final Map<String, String> getEmojies() {
        return this.emojies;
    }

    private void getEmojiesFromConfig() {
        this.emojies = new HashMap<>();

        ConfigurationSection emojiesSection = config.getConfigurationSection(Static.ConfigKeys.Emojis.MAIN);
        if (emojiesSection == null) {
            Static.log.warning("Emojies not found in the config!");
            return;
        }

        for (String key : emojiesSection.getKeys(false)) {
            ConfigurationSection keySection = emojiesSection.getConfigurationSection(key);

            if (keySection == null)
                continue;

            final String text = keySection.getString(Static.ConfigKeys.Emojis.Inner.TEXT);
            String emoji = keySection.getString(Static.ConfigKeys.Emojis.Inner.EMOJI);

            if (text == null || emoji == null)
                continue;

            if (emoji.startsWith("\\u"))
                emoji = Character.toString((char) Integer.parseInt(emoji.substring(2), 16));
            else
                emoji = ChatColor.translateAlternateColorCodes('&', emoji + "&r");

            this.emojies.put(text, emoji);
        }
    }
}
