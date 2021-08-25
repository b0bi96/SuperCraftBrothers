package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LivesManager {

    @Getter
    private static final Map<String, Integer> playerLives = new HashMap<>();
    //K, V

    public static void changePlayerLive(Player player) {
        String playerNick = player.getName();

        if (!(playerLives.containsKey(playerNick))) {
            playerLives.put(playerNick, 5);
            player.sendMessage("Dodano");
        } else {
            int live = playerLives.get(playerNick);
            playerLives.remove(playerNick);
            if (live >= 1) {
                playerLives.put(playerNick, (live - 1));
            } else {
                playerLives.put(playerNick, 0);
                player.sendMessage("Przegrales, jestes spec!");
                PlayerManager.changePlayerState(player, PlayerManager.PlayerState.SPECTATOR);
            }
        }
    }

}
