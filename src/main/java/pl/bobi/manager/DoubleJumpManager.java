package pl.bobi.manager;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;
import java.util.List;

public class DoubleJumpManager implements Listener {

    private final GameManager gameManager;
    private final List<Player> doubleJump;

    public DoubleJumpManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.doubleJump = new ArrayList<>();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        doubleJump.remove(event.getPlayer());
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        if (!(gameManager.getGameState() == GameState.INGAME)) return;

        Player player = event.getPlayer();
        GameMode gameMode = player.getGameMode();

        if(gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR || player.isFlying()) {
            return;
        }

        event.setCancelled(true);
        doubleJump.add(player);

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(0.5).setY(1));
    }

    @Deprecated
    @EventHandler
    public void onHitGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if ((player.isOnGround() || event.getTo().getBlock().isLiquid()) && doubleJump.remove(player)) {
            player.setAllowFlight(true);
        }
    }
}
