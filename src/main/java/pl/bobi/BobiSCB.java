package pl.bobi;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bobi.commands.StartCommand;
import pl.bobi.events.ingame.PlayerDeath;
import pl.bobi.events.overall.*;
import pl.bobi.manager.GameManager;
import pl.bobi.manager.PlayerManager;
import pl.bobi.utils.Config;

public final class BobiSCB extends JavaPlugin {

    @Getter
    private static BobiSCB plugin;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Config.init();
        this.gameManager = new GameManager(this);

        registerEvents(this,
                new BlockBreak(gameManager),
                new PlayerJoinQuit(gameManager),
                new PlayerClickItem(),
                new PlayerClickInGui(),
                new WeatcherChange(),
                new PlayerDeath(gameManager),
                new BlockDamage());


        getCommand("start").setExecutor(new StartCommand(gameManager));
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelAllTasks();
        PlayerManager.getPlayers().clear();
        PlayerManager.getSpecPlayers().clear();
    }

    public void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
