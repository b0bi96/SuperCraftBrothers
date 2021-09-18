package pl.bobi.builders.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.bobi.manager.LivesManager;
import pl.bobi.manager.PlayerManager;

public class InGameScore {

    public static void createInGameScore() {
        int playeringame = PlayerManager.getPlayers().size();

        ScoreboardCreate scoreboardCreate = new ScoreboardCreate();
        scoreboardCreate.addLine(" ", 2);
        scoreboardCreate.addLine(ChatColor.GRAY + "Gracze:", 1);
        scoreboardCreate.addLine("  ", 0);
        scoreboardCreate.addLine("   ", -playeringame - 1);
        scoreboardCreate.addLine(ChatColor.GOLD + "www.bobi.pl", -playeringame - 2);
        for (String player1 : PlayerManager.getPlayers()) {
            scoreboardCreate.addLine(player1 + ": " + ChatColor.AQUA + LivesManager.getPlayerLives().get(player1), -playeringame);

            if (playeringame > 0) {
                playeringame--;
            }
        }
        scoreboardCreate.create();
    }
}
