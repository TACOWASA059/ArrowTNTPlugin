package com.github.tacowasa059.arrowtntplugin.listeners;

import com.github.tacowasa059.arrowtntplugin.ArrowTNTPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectileLaunch implements Listener {
    private final ArrowTNTPlugin plugin;
    static private HashMap<Arrow,Integer> task_list=new HashMap<>();

    //方向のカウント
    private int count;
    private Vector perpendicularDirection;
    private Vector arrowDirection;

    private Arrow arrow;

    public ProjectileLaunch(ArrowTNTPlugin plugin) {
        this.plugin = plugin;
        this.count=0;
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event){
        if (event.getEntityType() == EntityType.ARROW) {
            // 矢を取得する
            arrow = (Arrow) event.getEntity();
            // 矢の進行方向を取得する処理
            arrowDirection = arrow.getVelocity().normalize();
            // 矢の進行方向に対して垂直な方向を求める処理
            perpendicularDirection = arrowDirection.clone().rotateAroundAxis(new Vector(0, 1, 0), 90);
            if(perpendicularDirection.length()<1e-8){
                perpendicularDirection=new Vector(1.0, 0.0, 0.0);
            }
            // スケジュールタスクを開始する
            //int taskid=Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new TNTtask(arrow), 0L, 20L);//20Lが1秒毎
            int taskid=Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
                @Override
                public void run() {
                    World world = arrow.getWorld();
                    Location location = arrow.getLocation();
                    //System.out.println(perpendicularDirection+" "+arrowDirection);
                    //Vector circularDirection = perpendicularDirection.clone().rotateAroundAxis(arrowDirection, 90*(count%4));
                    // TNTのエンティティを生成する処理
                    TNTPrimed tnt = (TNTPrimed) world.spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT);
                    tnt.setVelocity((arrow.getVelocity().clone()).multiply(0.5));
                    //tnt.setVelocity((arrow.getVelocity().clone().multiply(0.5)).add(circularDirection.clone().multiply(arrow.getVelocity().length()/2.0)));
                    //count++;
                    // TNTの速度を設定する処理
                    /*
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 10L); // 0.5秒間遅らせる
                    */


                }
            }, 0L, 1L);//20Lが1秒毎

            add(arrow,taskid);
        }
    }
    static public void add(Arrow arrow,Integer id){
        task_list.put(arrow, id);
    }
    static public Integer getID(Arrow arrow){
        return task_list.get(arrow);
    }
    static public void Remove(Arrow arrow){
        task_list.remove(arrow);
    }
    static public boolean isContains(Arrow arrow){
        return task_list.containsKey(arrow);
    }
}
