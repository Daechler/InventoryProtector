package net.daechler.inventoryprotector;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryProtector extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("InventoryProtector plugin has been enabled.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Player victim = event.getEntity();
        try {
            if (!(killer instanceof Player)) {
                event.setKeepInventory(true);
                event.getDrops().clear();
                getLogger().info(String.format("Player %s died and their inventory was protected.", victim.getName()));
            } else {
                getLogger().info(String.format("Player %s was killed by %s. Inventory was not protected.", victim.getName(), killer.getName()));
            }
        } catch (Exception ex) {
            getLogger().severe(String.format("An error occurred while protecting the inventory of player %s: %s", victim.getName(), ex.getMessage()));
        }
    }
}
