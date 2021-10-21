package pl.bobi.builders.scoreboards;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import pl.bobi.utils.Config;

import java.util.Arrays;

public class NewLobbyScore {

    public static Scoreboard createScoreboard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        if (board.getObjectives().contains(ChatColor.GOLD + "BobiSCB")) {
            System.out.println("Juz byl!, wyslano!");
            return board;
        }

        Objective obj = board.registerNewObjective(ChatColor.GOLD + "BobiSCB", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        ScoreUtils.createTeams(board,
                Arrays.asList("timer", "online"),
                Arrays.asList(ChatColor.WHITE + "", ChatColor.GRAY + ""),
                Arrays.asList(ChatColor.WHITE + "Oczekiwanie na graczy...", ""));

        obj.getScore(ChatColor.GRAY + ScoreUtils.getData() + " " + ChatColor.BLACK +  Bukkit.getServer().getServerName()).setScore(9);
        obj.getScore(" ").setScore(8);
        obj.getScore(ChatColor.WHITE + "Mapa: " + ChatColor.GREEN + Config.MAP_NAME).setScore(7);
        obj.getScore(ChatColor.WHITE + "Wersja: " + ChatColor.GREEN + Config.PLUGIN_VERSION).setScore(6);
        obj.getScore("  ").setScore(5);
        obj.getScore(ChatColor.WHITE + "").setScore(4);
        obj.getScore("   ").setScore(3);
        obj.getScore(ChatColor.GRAY + "").setScore(2);
        obj.getScore("    ").setScore(1);
        obj.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "www.bobi.pl").setScore(0);

        return board;
    }

    public static void addPlayerToScre(Player player) {
        player.setScoreboard(createScoreboard());
    }

    public static void removePlayer(Player player) {
        player.getScoreboard().getObjective(ChatColor.GOLD + "BobiSCB").unregister();
    }

    public static void updataScoreData(int x) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getTeam("timer").setPrefix(ChatColor.WHITE + "Start za: " + ChatColor.GREEN + x + "s");
            scoreboard.getTeam("timer").setSuffix(" ");
        }
    }

    public static void updateOnline(int players) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = player.getScoreboard();
            if (scoreboard.getTeam("online") == null) return;
            scoreboard.getTeam("online").setPrefix(ChatColor.WHITE + "Graczy: " + ChatColor.GREEN + players + "/" + Config.MAX_SLOTS);
        }
    }
}
