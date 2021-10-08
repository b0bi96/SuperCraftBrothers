package pl.bobi.builders.scoreboards;

import org.bukkit.ChatColor;
import pl.bobi.manager.LifesManager;
import pl.bobi.manager.PlayerManager;

public class InGameScore {

    public static void createInGameScore() {
        int playeringame = PlayerManager.getPlayers().size();

        ScoreboardCreate scoreboardCreate = new ScoreboardCreate();

        scoreboardCreate.addLine(ChatColor.GRAY + ScoreboardCreate.getData(), 3);
        scoreboardCreate.addLine(" ", 2);
        scoreboardCreate.addLine(ChatColor.GRAY + "Gracze:", 1);
        scoreboardCreate.addLine("  ", 0);
        scoreboardCreate.addLine("   ", -playeringame - 1);
        scoreboardCreate.addLine(ChatColor.GOLD + "www.bobi.pl", -playeringame - 2);
        for (String players : PlayerManager.getPlayers()) {
            scoreboardCreate.addLine(players + ": " + ChatColor.AQUA + LifesManager.getPlayerLives().get(players), -playeringame);
            scoreboardCreate.createTab(players);

            if (playeringame > 0) {
                playeringame--;
            }
        }

        scoreboardCreate.create();
    }
}
