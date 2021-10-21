package pl.bobi.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.manager.LifesManager;
import pl.bobi.manager.TrackerManager;

public class InGameTask extends BukkitRunnable {

    public void run() {
        LifesManager.blockMapYBorder();
        TrackerManager.trackPlayer();
    }
}
