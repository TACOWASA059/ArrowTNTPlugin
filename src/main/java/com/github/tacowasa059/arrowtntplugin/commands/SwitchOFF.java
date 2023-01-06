package com.github.tacowasa059.arrowtntplugin.commands;

import com.github.tacowasa059.arrowtntplugin.ArrowTNTPlugin;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileHit;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileLaunch;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SwitchOFF implements CommandExecutor {
    private final ArrowTNTPlugin plugin;

    public SwitchOFF(ArrowTNTPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            if(player.hasPermission("TNT")) {
                plugin.SetOFF();
                HandlerList.unregisterAll();
                player.sendMessage(ChatColor.GREEN +"ArrowTNTPlugin was disabled.");
            }
        }
        return true;
    }
}