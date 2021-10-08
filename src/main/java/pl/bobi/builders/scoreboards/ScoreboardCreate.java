package pl.bobi.builders.scoreboards;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import pl.bobi.manager.LifesManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreboardCreate {

    private final String title = ChatColor.GOLD + "" + ChatColor.BOLD + "BobiSCB";
    private final Scoreboard scoreboard;
    private final Objective obj;

    @Getter
    private static String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

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
        obj.getScore(data).getScoreboard().resetScores(data);
    }

    public void createScore(Player player) {
        if (player.getScoreboard().getObjective(title) != null) {
            player.getScoreboard().getObjective(title).unregister();
        }

        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(scoreboard);
    }

    public void createTab(String playerNick) {
        Team team = this.scoreboard.getTeam("lifes");

        if (team == null) {
            team = this.scoreboard.registerNewTeam("lifes");
        }

        team.setSuffix(": " + ChatColor.GOLD + LifesManager.getPlayerLives().get(playerNick));
        team.addEntry(playerNick);
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
