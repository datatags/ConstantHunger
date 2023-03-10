package me.datatags.constanthunger;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ConstantHunger extends JavaPlugin implements Listener {
    private int food;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        food = getConfig().getInt("food", 19);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setFoodLevel(food);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(food);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        getServer().getScheduler().runTaskLater(this, () -> event.getPlayer().setFoodLevel(food), 1);
    }
}
