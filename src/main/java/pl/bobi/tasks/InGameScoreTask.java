package pl.bobi.tasks;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bobi.builders.scoreboards.InGameScore;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.GameState;

public class InGameScoreTask extends BukkitRunnable {

    private final GameManager gameManager;
    @Getter
    private final InGameScore inGameScore;

    public InGameScoreTask(GameManager gameManager) {
        this.gameManager = gameManager;
        this.inGameScore = new InGameScore();
    }

    @Override
    public void run() {
        if(!(gameManager.getGameState() == GameState.INGAME)) this.cancel();
        getInGameScore().createInGameScore();
    }
}
