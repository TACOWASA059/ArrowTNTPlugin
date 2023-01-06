package com.github.tacowasa059.arrowtntplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHit implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // 矢が着弾したときの処理
        if (event.getEntityType() == EntityType.ARROW) {
            // 矢を取得する
            Arrow arrow = (Arrow) event.getEntity();
            // スケジュールタスクをキャンセルする
            if(ProjectileLaunch.isContains(arrow)){
                Integer taskid=ProjectileLaunch.getID(arrow);
                Bukkit.getScheduler().cancelTask(taskid);
                ProjectileLaunch.Remove(arrow);
            }
        }
    }
}
