package com.lith.emojies.util;

import java.util.Map;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import com.lith.emojies.Plugin;

public class EmojiesUtil {
    public static String addEmojies(@NotNull String text) {
        return addEmojies(text, "", "");
    }

    public static String addEmojies(@NotNull String text, @NotNull String after) {
        return addEmojies(text, after, "");
    }

    public static String addEmojies(@NotNull String text, @NotNull String after, @NotNull String before) {
        for (Map.Entry<Pattern, String> entry : Plugin.plugin.configs.getEmojies().entrySet())
            text = entry.getKey().matcher(text).replaceAll(before + entry.getValue() + after);

        return text.trim();
    }
}
