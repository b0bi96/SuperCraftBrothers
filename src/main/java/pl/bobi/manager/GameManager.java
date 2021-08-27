package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.bobi.BobiSCB;
import pl.bobi.tasks.InGameScoreTask;
import pl.bobi.tasks.StartTask;

public class GameManager {

    private final BobiSCB plugin;
    @Getter
    private GameState gameState = GameState.LOBBY;

    @Getter
    private final KitsManager playerManager;

    private StartTask startTask;
    private InGameScoreTask inGameScoreTask;

    public GameManager(BobiSCB plugin) {
        this.plugin = plugin;
        this.playerManager = new KitsManager(this);
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.INGAME && gameState == GameState.STARTING) return;
        if (this.gameState == gameState) return;

        this.gameState = gameState;

        switch (gameState) {
            case LOBBY:
                break;
            case STARTING:
                this.startTask = new StartTask(this);
                this.startTask.runTaskTimer(plugin, 0, 20);
                break;
            case INGAME:
                if (this.startTask != null) this.startTask.cancel();
                this.inGameScoreTask = new InGameScoreTask(this);
                this.inGameScoreTask.runTaskTimer(plugin, 0, 20);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    LivesManager.changePlayerLive(player);
                }
                getPlayerManager().giveKits();
                KitsManager.getPlayerKit().clear();
                break;
            case END:
                //
                break;
        }
    }
}
