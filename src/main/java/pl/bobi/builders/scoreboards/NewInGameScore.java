package pl.bobi.builders.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import pl.bobi.manager.LifesManager;
import pl.bobi.manager.PlayerManager;


public class NewInGameScore {
    public static void createScoreboard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        int playeringame = PlayerManager.getPlayers().size();

        if (board.getObjectives().contains(ChatColor.GOLD + "BobiSCB")) {
            System.out.println("Juz byl!, wyslano!");
            return;
        }

        Objective obj = board.registerNewObjective(ChatColor.GOLD + "BobiSCB", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(ChatColor.GRAY + ScoreUtils.getData() ).setScore(3);
        obj.getScore(" ").setScore(2);
        obj.getScore(ChatColor.GREEN + "Gracze: ").setScore(1);
        obj.getScore("  ").setScore(0);
        obj.getScore("   ").setScore(-playeringame - 1);
        obj.getScore(ChatColor.GRAY + "(" + Bukkit.getServer().getServerName() + ")").setScore(-playeringame - 2);
        obj.getScore(ChatColor.GOLD + "www.bobi.pl").setScore(-playeringame - 3);

        for (String players : PlayerManager.getPlayers()) {
            ScoreUtils.createTeamSuffix(board, players, players, ": " + ChatColor.GOLD + LifesManager.getPlayerLife(players));
            obj.getScore(players).setScore(-playeringame);

            if (playeringame > 0) {
                playeringame--;
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(board);
        }
    }

    public static void updataScoreData(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        String nick = player.getName();

        if (scoreboard.getTeam(nick) == null) return;

        if (!LifesManager.getPlayerLives().containsKey(nick)) {
            scoreboard.getTeam(nick).setPrefix("" + ChatColor.GRAY);
            scoreboard.getTeam(nick).setSuffix("");
        } else {
            scoreboard.getTeam(nick).setPrefix("");
            scoreboard.getTeam(nick).setSuffix(": " + ChatColor.GOLD + LifesManager.getPlayerLife(nick));
        }
    }
}
