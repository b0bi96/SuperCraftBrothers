package pl.bobi.events.overall;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.bobi.builders.items.ChooseKitItem;
import pl.bobi.builders.scoreboards.StartLobbyScore;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;
import pl.bobi.manager.PlayerManager;

import static pl.bobi.manager.PlayerManager.preparePlayerToGame;


public class PlayerJoinQuit implements Listener {

    private final GameManager gameManager;
    private final StartLobbyScore startLobbyScore;

    public PlayerJoinQuit(GameManager gameManager) {
        this.gameManager = gameManager;
        this.startLobbyScore = new StartLobbyScore();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!(gameManager.getGameState() == GameState.INGAME)) {
            startLobbyScore.createStartScore(0);
        } else {
            player.kickPlayer("GRA JUZ SIE ZACZELA :(");
            return;
        }
        preparePlayerToGame(player);
        PlayerManager.teleportPlayer(player, "lobby");
        player.getInventory().addItem(ChooseKitItem.itemStack());
        e.setJoinMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " doloczyl do areny!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " wyszedl z areny!");
    }
}
