package pl.bobi.tasks;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.builders.scoreboards.StartLobbyScore;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;

public class StartTask extends BukkitRunnable {

    private final GameManager gameManager;
    @Getter
    private final StartLobbyScore startScore;

    public StartTask(GameManager gameManager) {
        this.gameManager = gameManager;
        this.startScore = new StartLobbyScore();
    }

    private int timeLeft = 5;

    @Override
    public void run() {
        if (timeLeft == 20) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
            getStartScore().createStartScore(timeLeft);
            timeLeft--;
            return;
        }
        timeLeft--;

        getStartScore().createStartScore(timeLeft);

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
