package com.lith.emojies.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.bukkit.configuration.ConfigurationSection;
import com.lith.emojies.Plugin;
import com.lith.emojies.Static.ConfigKeys;
import com.lith.lithcore.abstractClasses.AbstractConfigManager;
import lombok.Getter;

public class ConfigManager extends AbstractConfigManager<Plugin, ConfigManager> {
    @Getter
    private Map<Pattern, String> emojies;

    public ConfigManager(final Plugin plugin) {
        super(plugin);
    }

    @Override
    public void load() {
        super.load();
        this.loadEmojies();
    }

    private void loadEmojies() {
        this.emojies = new HashMap<>();

        ConfigurationSection emojiesSection = config.getConfigurationSection(ConfigKeys.Emojis.MAIN);
        if (emojiesSection == null) {
            plugin.log.warning("Emojies not found in the config!");
            return;
        }

        for (String emojieKey : emojiesSection.getKeys(false)) {
            final Object key = getObject(getEmojieTextKey(emojieKey));
            if (key == null)
                continue;

            String emojie = getMessage(genEmojieValueKey(emojieKey));
            if (emojie == null)
                continue;

            emojie = " " + emojie;
            Pattern keyPattern = key instanceof String
                    ? createPatternFromKey((String) key)
                    : key instanceof List<?>
                            ? createPatternFromKey((List<?>) key)
                            : null;

            if (keyPattern == null)
                continue;

            this.emojies.put(keyPattern, emojie);
        }

        plugin.log.info("Loaded " + this.emojies.size() + " emojies!");
    }

    private Pattern createPatternFromKey(String key) {
        return Pattern.compile("(?:^|\s)" + Pattern.quote(key) + "(?=\s|$)", Pattern.CASE_INSENSITIVE);
    }

    private Pattern createPatternFromKey(List<?> keyList) {
        List<String> keys = new ArrayList<String>();

        for (Object key : keyList)
            if (key instanceof String)
                keys.add(Pattern.quote((String) key));

        return keys.size() > 0
                ? Pattern.compile("(?:^|\s)(?:" + String.join("|", keys) + ")(?=\s|$)", Pattern.CASE_INSENSITIVE)
                : null;
    }

    private String genEmojieValueKey(String sectionKey) {
        return getEmojieSectionKey(sectionKey, ConfigKeys.Emojis.Inner.EMOJI);
    }

    private String getEmojieTextKey(String sectionKey) {
        return getEmojieSectionKey(sectionKey, ConfigKeys.Emojis.Inner.TEXT);
    }

    private String getEmojieSectionKey(String sectionKey, String partKey) {
        return ConfigKeys.Emojis.MAIN + "." + sectionKey + "." + partKey;
    }
}
