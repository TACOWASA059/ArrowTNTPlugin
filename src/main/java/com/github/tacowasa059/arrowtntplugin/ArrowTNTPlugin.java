package com.github.tacowasa059.arrowtntplugin;

import com.github.tacowasa059.arrowtntplugin.commands.SwitchOFF;
import com.github.tacowasa059.arrowtntplugin.commands.SwitchON;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileHit;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileLaunch;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArrowTNTPlugin extends JavaPlugin {
    private boolean inplay=false;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Plugin startup logic
        getCommand("TNTon").setExecutor(new SwitchON(this));
        getCommand("TNToff").setExecutor(new SwitchOFF(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void SetON(){
        inplay=true;
    }
    public void SetOFF(){
        inplay=false;
    }
}
