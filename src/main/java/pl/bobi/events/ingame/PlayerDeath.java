package pl.bobi.events.ingame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;
import pl.bobi.manager.LivesManager;

public class PlayerDeath implements Listener {

    private final GameManager gameManager;

    public PlayerDeath(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void playerDeath(EntityDamageByEntityEvent event) {
        Player killer = (Player) event.getDamager();
        Player killed = (Player) event.getEntity();

        if (gameManager.getGameState() != GameState.INGAME) return;

        if ((killed.getHealth() - event.getFinalDamage()) <= 0.0) {
            Bukkit.getServer().broadcastMessage(ChatColor.WHITE + "Gracz " + killed.getDisplayName() + " zostal zabity przez " + killer.getDisplayName());

            LivesManager.changePlayerLive(killed);
            event.setCancelled(true);
        }



    }
}
