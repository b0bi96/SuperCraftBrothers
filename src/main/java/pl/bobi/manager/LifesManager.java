package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.bobi.builders.scoreboards.NewInGameScore;

import java.util.HashMap;
import java.util.Map;

import static pl.bobi.manager.PlayerManager.preparePlayerToGame;

public class LifesManager {

    private static GameManager gameManager;

    public LifesManager(GameManager gameManager) {
        LifesManager.gameManager = gameManager;
    }

    @Getter
    private static final Map<String, Integer> playerLives = new HashMap<>();

    public static void changePlayerLive(Player player, boolean sendKillMsg) {
        String playerNick = player.getName();

        preparePlayerToGame(player);

        if (!(gameManager.getGameState() == GameManager.GameState.INGAME)) {
            PlayerManager.teleportPlayer(player, "random");
            return;
        }

        if (!(playerLives.containsKey(playerNick))) {
            playerLives.put(playerNick, 5);
            PlayerManager.teleportPlayer(player, "random");
        } else {
            int live = playerLives.get(playerNick);
            playerLives.remove(playerNick);
            if (live > 1) {
                playerLives.put(playerNick, (live - 1));
                PlayerManager.teleportPlayer(player, "random");
            } else {
                playerLives.remove(playerNick);
                player.sendMessage(ChatColor.AQUA + "Przegrales! Od teraz mozesz obserwowac gre jako obserwator!");
                PlayerManager.changePlayerState(player, PlayerManager.PlayerState.SPECTATOR);
                PlayerManager.teleportPlayer(player, "spectator");
            }
        }

        if (sendKillMsg) {
            if (PlayerManager.getKills().get(playerNick) != null) {
                Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + playerNick + ChatColor.GRAY + " zostal zabity przez "
                        + ChatColor.WHITE + PlayerManager.getKills().get(playerNick));
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Gracz " + ChatColor.WHITE + playerNick + ChatColor.GRAY + " zginal!");
            }
        }

//        if (PlayerManager.getPlayers().size() == 1) {
//            gameManager.setGameState(GameManager.GameState.END);
//        }
        NewInGameScore.updataScoreData(player);
    }

    public static int getPlayerLife(String player) {
        return getPlayerLives().get(player);
    }

    public static void blockMapYBorder() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getY() < 65) {
                changePlayerLive(player, true);
            }
        }
    }
}
