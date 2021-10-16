package pl.bobi.tasks;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.builders.scoreboards.NewLobbyScore;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;

public class StartTask extends BukkitRunnable {

    private final GameManager gameManager;
    @Getter
    private final NewLobbyScore newLobbyScore;

    public StartTask(GameManager gameManager) {
        this.gameManager = gameManager;
        this.newLobbyScore = new NewLobbyScore();
    }

    private int timeLeft = 2;

    @Override
    public void run() {
        if (timeLeft == 20) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
            newLobbyScore.updataScoreData(timeLeft);
            timeLeft--;
            return;
        }
        timeLeft--;
        newLobbyScore.updataScoreData(timeLeft);

        if (timeLeft <= 0) {
            gameManager.setGameState(GameState.INGAME);
            Bukkit.broadcastMessage(ChatColor.BLUE + "GRA ROZPOCZETA, POWODZENIA!");
            this.cancel();
            return;
        }

        if (timeLeft == 10) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
        } else if (timeLeft <= 5) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
        }
    }
}
