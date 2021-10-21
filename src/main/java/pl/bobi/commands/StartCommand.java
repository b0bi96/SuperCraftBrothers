package pl.bobi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bobi.manager.GameManager;

public class StartCommand implements CommandExecutor {

    private final GameManager gameManager;

    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        gameManager.setGameState(GameManager.GameState.STARTING);
        return false;
    }
}
