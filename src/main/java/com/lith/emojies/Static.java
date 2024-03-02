package com.lith.emojies;

import java.util.logging.Logger;

public class Static {
    public static final String pluginName = "Emojies";
    public static final Logger log = Logger.getLogger(Static.pluginName);

    public static class ConfigKeys {
        public static class Emojis {
            public static final String MAIN = "emojies";

            public static class Inner {
                public static final String TEXT = "text";
                public static final String EMOJI = "emoji";
            }
        }
    }
}
