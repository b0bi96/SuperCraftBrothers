package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.bobi.BobiSCB;
import pl.bobi.builders.scoreboards.NewInGameScore;
import pl.bobi.builders.scoreboards.NewLobbyScore;
import pl.bobi.tasks.InGameTask;
import pl.bobi.tasks.EndTask;
import pl.bobi.tasks.StartTask;
import pl.bobi.utils.Packets;

public class GameManager {

    private final BobiSCB plugin;
    @Getter
    private GameState gameState = GameState.LOBBY;

    @Getter
    private final KitsManager kitsManager;

    private StartTask startTask;
    private InGameTask inGameTask;
    private EndTask endTask;
//    private ReaperTask reaperTask;

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
                NewLobbyScore.createScoreboard();
                break;
            case STARTING:
                this.startTask = new StartTask(this);
                this.startTask.runTaskTimer(plugin, 0, 20);
                break;
            case INGAME:
                if (this.startTask != null) this.startTask.cancel();
                this.inGameTask = new InGameTask();
                this.inGameTask.runTaskTimer(plugin, 0, 20);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getInventory().clear();
                    LifesManager.changePlayerLive(player, false);
                    player.setAllowFlight(true);
                    player.setFlying(false);
                }
                NewInGameScore.createScoreboard();
                getKitsManager().giveKits();
                KitsManager.getPlayerKit().clear();
                break;
            case END:
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerManager.preparePlayerInventory(player);
                    Packets.sendTitle(player, ChatColor.GOLD + "Koniec gry!", 60);
                }
                Bukkit.broadcastMessage(ChatColor.GOLD + "Koniec gry!");
                Bukkit.broadcastMessage(ChatColor.AQUA + "Wygrany: " + PlayerManager.getPlayers().get(0));
                Bukkit.broadcastMessage(ChatColor.GOLD + "GRATULACJE!");
                this.endTask = new EndTask();
                this.endTask.runTaskTimer(plugin, 0, 20);
                break;
        }
    }

    public enum GameState {
        LOBBY, STARTING, INGAME, END
    }
}
