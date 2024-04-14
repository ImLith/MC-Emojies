package com.lith.emojies.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import com.lith.emojies.util.EmojiesUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import io.papermc.paper.event.player.AsyncChatEvent;

public class PlayerChatEvent implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncChatEvent e) {
        String message = PlainTextComponentSerializer.plainText().serialize(e.originalMessage());

        e.message(Component.text(EmojiesUtil.addEmojies(message)));
    }
}
