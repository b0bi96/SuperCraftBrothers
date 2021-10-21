package pl.bobi.events.overall;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.bobi.builders.items.ChooseKitItem;
import pl.bobi.builders.scoreboards.NewLobbyScore;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.LifesManager;
import pl.bobi.manager.PlayerManager;
import pl.bobi.utils.Config;

import static pl.bobi.manager.PlayerManager.preparePlayerInventory;
import static pl.bobi.manager.PlayerManager.preparePlayerToGame;


public class PlayerJoinQuit implements Listener {

    private final GameManager gameManager;

    public PlayerJoinQuit(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!(gameManager.getGameState() == GameManager.GameState.INGAME)) {
            PlayerManager.changePlayerState(player, PlayerManager.PlayerState.PLAYER);
            preparePlayerToGame(player);
            preparePlayerInventory(player);
            PlayerManager.teleportPlayer(player, "lobby");
            player.getInventory().addItem(ChooseKitItem.build());
            player.setGameMode(GameMode.SURVIVAL);
            e.setJoinMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + player.getName() +
                    ChatColor.GRAY + " dolaczyl do areny! (" + ChatColor.WHITE + PlayerManager.getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.WHITE + Config.MAX_SLOTS + ChatColor.GRAY + ")");
            NewLobbyScore.addPlayerToScre(player);
            NewLobbyScore.updateOnline(Bukkit.getOnlinePlayers().size());
        } else {
            PlayerManager.changePlayerState(player, PlayerManager.PlayerState.SPECTATOR);
            PlayerManager.teleportPlayer(player, "spectator");
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().setArmorContents(null);
            preparePlayerToGame(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String nick = player.getName();
        PlayerManager.getPlayers().remove(nick);
        PlayerManager.getSpecPlayers().remove(nick);
        LifesManager.getPlayerLives().replace(nick, 1);
        LifesManager.changePlayerLive(player, false);

        if (gameManager.getGameState() == GameManager.GameState.LOBBY) {
            e.setQuitMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + nick + ChatColor.GRAY + " wyszedl z areny! (" + ChatColor.WHITE + PlayerManager.getPlayers().size() +
                    ChatColor.GRAY + "/" + ChatColor.WHITE + Config.MAX_SLOTS + ChatColor.GRAY + ")");
            NewLobbyScore.updateOnline(Bukkit.getOnlinePlayers().size() - 1);
            NewLobbyScore.removePlayer(player);
        } else {
            e.setQuitMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + nick + ChatColor.GRAY + " zginal!");
        }
    }
}
