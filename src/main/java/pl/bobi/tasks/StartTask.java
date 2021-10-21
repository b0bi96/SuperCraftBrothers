package pl.bobi.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.builders.scoreboards.NewLobbyScore;
import pl.bobi.manager.GameManager;
import pl.bobi.utils.Packets;

public class StartTask extends BukkitRunnable {

    private final GameManager gameManager;

    public StartTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timeLeft = 6;

    @Override
    public void run() {
        if (timeLeft == 20) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
            NewLobbyScore.updataScoreData(timeLeft);
            timeLeft--;
            return;
        }
        timeLeft--;
        NewLobbyScore.updataScoreData(timeLeft);

        if (timeLeft <= 0) {
            gameManager.setGameState(GameManager.GameState.INGAME);
            Bukkit.broadcastMessage(ChatColor.BLUE + "GRA ROZPOCZETA, POWODZENIA!");
            this.cancel();
            return;
        }

        if (timeLeft == 10) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
        } else if (timeLeft <= 3) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "Start gry za: " + timeLeft);
            for (Player player : Bukkit.getOnlinePlayers()) {
                Packets.sendTitle(player, ChatColor.RED + "" + timeLeft, 20);
            }
        }
    }
}
