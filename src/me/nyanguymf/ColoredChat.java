package me.nyanguymf;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import me.nyanguymf.listeners.MessagesListener;

public class ColoredChat extends JavaPlugin {
    private Logger log = getLogger();
    
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MessagesListener(), this);
        log.info("Plugin loaded!");
    }
    
    public void onDisable() {
        log.info("Plugin has been disabled!");
    }
}
