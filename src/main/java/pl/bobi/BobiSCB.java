package pl.bobi;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bobi.commands.StartCommand;
import pl.bobi.events.ingame.PlayerDeath;
import pl.bobi.events.ingame.PlayerItemCick;
import pl.bobi.events.ingame.PlayerItemHeld;
import pl.bobi.events.lobby.PlayerClickInGui;
import pl.bobi.events.lobby.PlayerClickItem;
import pl.bobi.events.overall.*;
import pl.bobi.kits.KitArrmorItem;
import pl.bobi.manager.DoubleJumpManager;
import pl.bobi.manager.GameManager;
import pl.bobi.utils.Config;

import java.io.File;
import java.io.IOException;

public final class BobiSCB extends JavaPlugin {

    @Getter
    private static BobiSCB plugin;
    private GameManager gameManager;

    public static FileConfiguration fileConfiguration;



    @SneakyThrows
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Config.init();
        this.gameManager = new GameManager(this);

        registerEvents(this,
                new BlockBreak(),
                new PlayerJoinQuit(gameManager),
                new PlayerClickItem(),
                new PlayerClickInGui(),
                new WeatcherChange(),
                new PlayerDeath(gameManager),
                new BlockDamage(),
                new DoubleJumpManager(gameManager),
                new PlayerItemHeld(),
                new PlayerItemCick());

        getCommand("start").setExecutor(new StartCommand(gameManager));

        if (Config.AUTO_KITS_DESCRIPTION) {
            File file = new File(plugin.getDataFolder(), "itemsNames.yml");

            if (!file.exists())
                throw new IOException("Nie wczytano itemsNames.yml!");

            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }
    }
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelAllTasks();
    }

    public void registerEvents(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

}
