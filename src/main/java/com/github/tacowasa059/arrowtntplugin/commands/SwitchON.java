package com.github.tacowasa059.arrowtntplugin.commands;

import com.github.tacowasa059.arrowtntplugin.ArrowTNTPlugin;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileHit;
import com.github.tacowasa059.arrowtntplugin.listeners.ProjectileLaunch;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SwitchON implements CommandExecutor {
    private final ArrowTNTPlugin plugin;

    public SwitchON(ArrowTNTPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player=(Player) sender;
            if(player.hasPermission("TNT")){
                plugin.SetON();
                plugin.getServer().getPluginManager().registerEvents(new ProjectileLaunch(plugin),plugin);
                plugin.getServer().getPluginManager().registerEvents(new ProjectileHit(),plugin);
                player.sendMessage(ChatColor.GREEN +"ArrowTNTPlugin was enabled.");
            }
        }
        return true;
    }
}
