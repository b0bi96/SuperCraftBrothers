package pl.bobi.builders.scoreboards;

import org.bukkit.ChatColor;
import pl.bobi.BobiSCB;
import pl.bobi.utils.Config;


public class StartLobbyScore {

    public void createStartScore(int timeleft, int playerSize) {
        ScoreboardCreate scoreboardCreate = new ScoreboardCreate();
        scoreboardCreate.addLine(ChatColor.GRAY + ScoreboardCreate.getData() + " " + ChatColor.BOLD + ChatColor.GRAY + BobiSCB.getPlugin().getServer().getServerName(),  9);
        scoreboardCreate.addLine(" ", 8);
        scoreboardCreate.addLine(ChatColor.GRAY + "Mapa: " + ChatColor.WHITE + Config.MAP_NAME, 7);
        scoreboardCreate.addLine(ChatColor.GRAY + "Wersja: " + ChatColor.WHITE + Config.PLUGIN_VERSION, 6);
        scoreboardCreate.addLine("  ", 5);
        if (timeleft == 0) {
            scoreboardCreate.addLine(ChatColor.GRAY + "Start za: " + ChatColor.WHITE + "oczekiwanie...", 4);
        } else {
            scoreboardCreate.addLine(ChatColor.GRAY + "Start za: " + ChatColor.WHITE + timeleft + ChatColor.GRAY + " sek", 4);
        }
        scoreboardCreate.addLine("   ", 3);
        scoreboardCreate.addLine(ChatColor.GRAY + "Graczy: " + ChatColor.WHITE + playerSize + "/" + Config.MAX_SLOTS, 2);
        scoreboardCreate.addLine("    ", 1);
        scoreboardCreate.addLine(ChatColor.GOLD + "" + ChatColor.BOLD + "www.bobi.pl", 0);

        scoreboardCreate.create();
    }
}
