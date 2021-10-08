package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.bobi.BobiSCB;
import pl.bobi.builders.scoreboards.InGameScore;
import pl.bobi.tasks.BorderTask;
import pl.bobi.tasks.EndTask;
import pl.bobi.tasks.StartTask;

public class GameManager {

    private final BobiSCB plugin;
    @Getter
    private GameState gameState = GameState.LOBBY;

    @Getter
    private final KitsManager kitsManager;

    private StartTask startTask;
    private BorderTask inGameScoreTask;
    private EndTask endTask;
    private final LifesManager livesManager;
    private final DoubleJumpManager doubleJumpManager;

    public GameManager(BobiSCB plugin) {
        this.plugin = plugin;
        this.kitsManager = new KitsManager(this);
        this.livesManager = new LifesManager(this);
        this.doubleJumpManager = new DoubleJumpManager(this);
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
                this.inGameScoreTask = new BorderTask(this);
                this.inGameScoreTask.runTaskTimer(plugin, 0, 20);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    LifesManager.changePlayerLive(player);
                    player.setAllowFlight(true);
                    player.setFlying(false);
                }
                getKitsManager().giveKits();
                KitsManager.getPlayerKit().clear();
                break;
            case END:
                Bukkit.broadcastMessage(ChatColor.GOLD + "Koniec gry! Wygral: " + PlayerManager.getPlayers().get(0) + " gz!");
                this.endTask = new EndTask();
                this.endTask.runTaskTimer(plugin, 0, 20);
                break;
        }
    }
}
