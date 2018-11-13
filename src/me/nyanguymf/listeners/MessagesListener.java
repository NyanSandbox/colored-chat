package me.nyanguymf.listeners;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessagesListener implements Listener {
    private Player sender;
    private AsyncPlayerChatEvent event;

    @EventHandler
    public void onColoredMessage(AsyncPlayerChatEvent event) {
        this.event  = event;
        this.sender = event.getPlayer();
        
        if (sender == null) {
            return;
        }

        String message = event.getMessage();
        message = parseIntColor(message);
        setMsgOrStop(message);
        message = parseFonts(message);
        setMsgOrStop(message);
        message = parseCharColor(message);
        setMsgOrStop(message);
    }

    private String parseIntColor(String message) {
        for (Integer color = 0; color < 10; color++) {
            if (message.contains("&" + color)) {
                if (hasPermissionForColor(color)) {
                    message = message.replace("&", "\u00a7");
                }
                else {
                    event.setCancelled(true);
                    sender.sendMessage(
                            ChatColor.DARK_RED + "У Вас не прав писать \u00a7"
                            + color + "этим \u00a74цветом");
                    return message;
                }
            }
        }
        return message;
    }
    
    private String parseFonts(String message) {
        for (char font = 'k', fontUp = 'K'; font <= 'o' ; font++, fontUp++) {
            if (message.contains("&" + font) || message.contains("&" + fontUp)) {
                if (hasPermissionForFont(font)) {
                    message = message.replace("&", "\u00a7");
                }
                else {
                    event.setCancelled(true);
                    sender.sendMessage(
                            ChatColor.DARK_RED + "У Вас не прав писать \u00a7"
                            + font + "этим\u00a74 шрифтом");
                    return message;
                }
            }
        }
        return message;
    }

    private String parseCharColor(String message) {
        for (char color = 'a', colorUp = 'A'; color <= 'f'; color++, colorUp++) {
            if (message.contains("&" + color) || message.contains("&" + colorUp)) {
                if (hasPermissionForColor(color)) {
                    message = message.replace("&", "\u00a7");
                }
                else {
                    event.setCancelled(true);
                    sender.sendMessage(
                            ChatColor.DARK_RED + "У Вас не прав писать \u00a7"
                            + color + "этим \u00a74цветом");
                    return message;
                }
            }
        }
        return message;
    }

    private boolean hasPermissionForColor(Integer color) {
        return sender.hasPermission("color." + color.toString());
    }

    private boolean hasPermissionForFont(char color) {
        return sender.hasPermission("font." + color);
    }

    private boolean hasPermissionForColor(char color) {
        return sender.hasPermission("color." + color);
    }
    
    private void setMsgOrStop(String message) {
        if (event.isCancelled()) {
            return;
        }
        else {
            event.setMessage(message);
        }
    }
}
