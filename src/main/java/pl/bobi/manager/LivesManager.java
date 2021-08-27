package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static pl.bobi.manager.PlayerManager.preparePlayerToGame;

public class LivesManager {

    @Getter
    private static final Map<String, Integer> playerLives = new HashMap<>();
    //K, V

    public static void changePlayerLive(Player player) {
        String playerNick = player.getName();

        preparePlayerToGame(player);

        if (!(playerLives.containsKey(playerNick))) {
            playerLives.put(playerNick, 5);
            player.sendMessage("Dodano");
            PlayerManager.teleportPlayer(player, "random");
        } else {
            int live = playerLives.get(playerNick);
            playerLives.remove(playerNick);
            if (live > 1) {
                playerLives.put(playerNick, (live - 1));
                PlayerManager.teleportPlayer(player, "random");
            } else {
                playerLives.remove(playerNick);
                player.sendMessage("Przegrales, jestes spec!");
                PlayerManager.changePlayerState(player, PlayerManager.PlayerState.SPECTATOR);
                PlayerManager.teleportPlayer(player, "spectator");
            }
        }
    }

    public static void blockMapYBorder() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getY() < 65) {
                changePlayerLive(player);
            }
        }
    }
}
