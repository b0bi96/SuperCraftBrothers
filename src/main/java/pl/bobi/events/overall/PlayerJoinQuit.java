package pl.bobi.events.overall;

import org.bukkit.Bukkit;
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
import pl.bobi.manager.LivesManager;
import pl.bobi.manager.PlayerManager;
import pl.bobi.utils.Config;

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
            PlayerManager.changePlayerState(player, PlayerManager.PlayerState.PLAYER);
            preparePlayerToGame(player);
            PlayerManager.teleportPlayer(player, "lobby");
            player.getInventory().addItem(ChooseKitItem.itemStack());
            e.setJoinMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + player.getName() +
                    ChatColor.GRAY + " dolaczyl do areny! (" + ChatColor.WHITE + PlayerManager.getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.WHITE + Config.MAX_SLOTS + ChatColor.GRAY + ")");

            startLobbyScore.createStartScore(0, Bukkit.getOnlinePlayers().size());
        } else {
            PlayerManager.changePlayerState(player, PlayerManager.PlayerState.SPECTATOR);
            PlayerManager.teleportPlayer(player, "spectator");
            preparePlayerToGame(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerManager.getPlayers().remove(player.getDisplayName());
        LivesManager.getPlayerLives().replace(player.getDisplayName(), 1);
        LivesManager.changePlayerLive(player);
        if (gameManager.getGameState() == GameState.LOBBY) {
            e.setQuitMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " wyszedl z areny! (" + ChatColor.WHITE + PlayerManager.getPlayers().size() +
                    ChatColor.GRAY + "/" + ChatColor.WHITE + Config.MAX_SLOTS + ChatColor.GRAY + ")");
            startLobbyScore.createStartScore(0, Bukkit.getOnlinePlayers().size() - 1);
        }
    }
}
