package com.lith.emojies.config;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;
import com.lith.emojies.Plugin;
import com.lith.emojies.Static;
import com.lith.lithcore.abstractClasses.AbstractConfigManager;
import com.lith.lithcore.utils.StringUtil;
import lombok.Getter;

public class ConfigManager extends AbstractConfigManager<Plugin, ConfigManager> {
    @Getter
    private Map<String, String> emojies;

    public ConfigManager(final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        super.load();

        this.getEmojiesFromConfig();
    }

    private void getEmojiesFromConfig() {
        this.emojies = new HashMap<>();

        ConfigurationSection emojiesSection = config.getConfigurationSection(Static.ConfigKeys.Emojis.MAIN);
        if (emojiesSection == null) {
            plugin.log.warning("Emojies not found in the config!");
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

            this.emojies.put(text, StringUtil.addUnicodes(StringUtil.addColors(emoji)));
        }
    }
}
