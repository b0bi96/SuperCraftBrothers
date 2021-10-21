package pl.bobi.events.ingame;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.LifesManager;
import pl.bobi.manager.PlayerManager;

public class PlayerDeath implements Listener {

    private final GameManager gameManager;

    public PlayerDeath(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void playerDeath(EntityDamageByEntityEvent event) {
        if (gameManager.getGameState() != GameManager.GameState.INGAME) return;

        Player killer = (Player) event.getDamager();
        Player killed = (Player) event.getEntity();


        if (PlayerManager.getKills().containsKey(killed.getName())) {
            PlayerManager.getKills().replace(killed.getName(), killer.getName());
        } else {
            PlayerManager.getKills().put(killed.getName(), killer.getName());
        }

        if ((killed.getHealth() - event.getFinalDamage()) <= 0.0) {
            LifesManager.changePlayerLive(killed, true);
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
