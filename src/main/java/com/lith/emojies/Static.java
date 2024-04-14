package com.lith.emojies;

public final class Static {
    public final static class ConfigKeys {
        public final static class Emojis {
            public static final String MAIN = "emojies";

            public final static class Inner {
                public static final String TEXT = "text";
                public static final String EMOJI = "emoji";
            }
        }
    }

    public final static class Commands {
        public final static class Name {
            public final static String RELOAD = "emojiesReload";
        }

        public final static class Permission {
            private final static String PREFIX = "emojies.";
            public final static String RELOAD = PREFIX + "reload";
        }
    }
}
