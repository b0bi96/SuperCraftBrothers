package pl.bobi.events.ingame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;
import pl.bobi.manager.LifesManager;

public class PlayerDeath implements Listener {

    private final GameManager gameManager;

    public PlayerDeath(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void playerDeath(EntityDamageByEntityEvent event) {
        if (gameManager.getGameState() == GameState.LOBBY) return;

        Player killer = (Player) event.getDamager();
        Player killed = (Player) event.getEntity();

        if (gameManager.getGameState() != GameState.INGAME) return;

        if ((killed.getHealth() - event.getFinalDamage()) <= 0.0) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + killed.getDisplayName() + ChatColor.GRAY + " zostal zabity przez " + ChatColor.WHITE + killer.getDisplayName());

            LifesManager.changePlayerLive(killed);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setKeepInventory(true);
    }

    @EventHandler
    public void dropItems(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
