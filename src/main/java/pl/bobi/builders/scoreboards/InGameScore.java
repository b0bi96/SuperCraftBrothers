package pl.bobi.builders.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.bobi.manager.LivesManager;

public class InGameScore {

    public void createInGameScore() {
        ScoreboardCreate scoreboardCreate = new ScoreboardCreate();
        scoreboardCreate.addLine(" ", 3);
        scoreboardCreate.addLine(ChatColor.GRAY + "Gracze:", 2);
         int playeringame = Bukkit.getOnlinePlayers().size();
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (LivesManager.getPlayerLives().containsKey(player1.getName())) {
                scoreboardCreate.addLine(player1.getName() + ": " + LivesManager.getPlayerLives().get(player1.getName()), playeringame);
            }
            if (playeringame > 0) {
                playeringame--;
            }
        }
        scoreboardCreate.addLine("Twoje zycia: ", -10);
        scoreboardCreate.create();

    }
}
