package pl.bobi.builders.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardCreate {

    private final String title = ChatColor.GOLD + "" + ChatColor.BOLD + "BobiSCB";
    private final Scoreboard scoreboard;
    private final Objective obj;

    public ScoreboardCreate() {
        ScoreboardManager scoreboardManager = Bukkit.getServer().getScoreboardManager();
        this.scoreboard = scoreboardManager.getNewScoreboard();
        this.obj = scoreboard.registerNewObjective(title, "dummy");
    }

    public void addLine(String data, int scorenumber) {
        obj.getScore(data).setScore(scorenumber);
    }

    public void deleteLine(String data) {
        scoreboard.resetScores(data);
    }

    public void createScore(Player player) {
        if (player.getScoreboard().getObjective(title) != null) {
            player.getScoreboard().getObjective(title).unregister();
        }

        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(scoreboard);
    }

    public void create(Player player) {
        createScore(player);
    }

    public void create() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            createScore(players);
        }
    }

}
