package pl.bobi.builders.scoreboards;

import lombok.Getter;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreUtils {

    @Getter
    private static String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    public static void createTeamSuffix(Scoreboard board, String teamName, String entry, String suffix) {
        Team team = board.registerNewTeam(teamName);
        team.addEntry(entry);

        if (suffix.length() <= 16) {
            team.setSuffix(suffix);
            return;
        }

        if (suffix.length() > 32) {
            suffix = suffix.substring(0, 32);
        }

        team.setSuffix(suffix.substring(0, 16));
        team.setPrefix(suffix.substring(16));
    }

    public static void createTeams(Scoreboard board, List<String> teamName, List<String> entry, List<String> prefix) {
        int x = 0;
        for (String s : teamName) {
            Team team = board.registerNewTeam(s);
            team.addEntry(entry.get(x));

            String prefixString = prefix.get(x);

            if (prefixString.length() <= 16) {
                team.setPrefix(prefixString);
                return;
            }

            if (prefixString.length() > 32) {
                prefixString = prefixString.substring(0, 32);
            }

            team.setPrefix(prefixString.substring(0, 16));
            team.setSuffix(prefixString.substring(16));

            x++;
        }

        teamName.clear();
        entry.clear();
        prefix.clear();
    }
}
