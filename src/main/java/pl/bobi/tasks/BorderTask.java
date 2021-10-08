package pl.bobi.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.LifesManager;

public class BorderTask extends BukkitRunnable {

    private final GameManager gameManager;

    public BorderTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void run() {
       LifesManager.blockMapYBorder();
    }
}
