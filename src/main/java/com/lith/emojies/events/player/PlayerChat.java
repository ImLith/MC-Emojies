package com.lith.emojies.events.player;

import java.util.Map;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.lith.emojies.Plugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import io.papermc.paper.event.player.AsyncChatEvent;

public class PlayerChat implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(AsyncChatEvent e) {
        String msg = PlainTextComponentSerializer.plainText().serialize(e.originalMessage());

        for (Map.Entry<String, String> entry : Plugin.plugin.cm.getEmojies().entrySet()) {
            msg = msg.replace(entry.getKey(), entry.getValue());
        }

        e.message(Component.text(msg));
    }
}
