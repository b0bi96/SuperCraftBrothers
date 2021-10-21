package pl.bobi.utils;

import org.bukkit.configuration.file.FileConfiguration;
import pl.bobi.BobiSCB;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private Config() {
    }

    public static String MAP_NAME, PLUGIN_VERSION, SPAWNPOINT_SPECTATOR, SPAWN_LOBBY;
    public static List<String> SPAWNPOINTS = new ArrayList<>();
    public static int MAX_SLOTS;
    public static boolean AUTO_KITS_DESCRIPTION;

    private static final FileConfiguration config = BobiSCB.getPlugin().getConfig();

    public static void init(){
        MAP_NAME = config.getString("metadata.name");
        PLUGIN_VERSION = config.getString("metadata.pluginversion");
        SPAWNPOINT_SPECTATOR = config.getString("spectator");
        SPAWN_LOBBY = config.getString("lobby");
        SPAWNPOINTS = config.getStringList("spawnpoints");
        MAX_SLOTS = config.getInt("slots");
        AUTO_KITS_DESCRIPTION = config.getBoolean("autokitsdescription");
    }
}
