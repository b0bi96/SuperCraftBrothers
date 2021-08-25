package pl.bobi.utils;

import org.bukkit.configuration.file.FileConfiguration;
import pl.bobi.BobiSCB;

import java.util.List;

public class Config {

    private Config() {
    }

    public static String MAP_NAME, PLUGIN_VERSION, SPAWNPOINT_SPECTATOR, SPAWN_LOBBY;
    public static List<String> SPAWNPOINTS;

    private static final FileConfiguration config = BobiSCB.getPlugin().getConfig();

    public static void init(){
        MAP_NAME = config.getString("metadata.name");
        PLUGIN_VERSION = config.getString("metadata.pluginversion");
        SPAWNPOINT_SPECTATOR = config.getString("spectator");
        SPAWN_LOBBY = config.getString("lobby");
        SPAWNPOINTS = config.getStringList("spawnpoints");
    }
}
