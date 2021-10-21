package pl.bobi.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class EndTask extends BukkitRunnable {
    private int time = 10;

    @Override
    public void run() {
        time--;

        if (time == 0) {
            Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("TP do lobby!"));
            Bukkit.getServer().reload();
            this.cancel();
        }
    }
}
