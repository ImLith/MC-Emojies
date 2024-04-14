package com.lith.emojies.util;

import java.util.Map;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import com.lith.emojies.Plugin;

public class EmojiesUtil {
    public static String addEmojies(@NotNull String text) {
        for (Map.Entry<Pattern, String> entry : Plugin.plugin.configs.getEmojies().entrySet())
            text = entry.getKey().matcher(text).replaceAll(entry.getValue());

        return text.trim();
    }
}
