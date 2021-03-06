package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import pl.bobi.utils.Config;
import pl.bobi.utils.LocationGet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerManager {

    @Getter
    private static final List<String> specPlayers = new ArrayList<>();
    @Getter
    private static final List<String> players = new ArrayList<>();
    @Getter
    private static final Map<String, String> kills = new HashMap<>();

    public static void changePlayerState(Player player, PlayerState playerState) {
        final String playerNick = player.getDisplayName();

        switch (playerState) {
            case PLAYER:
                players.add(playerNick);
                break;
            case SPECTATOR:
                players.remove(playerNick);
                specPlayers.add(playerNick);
                player.setGameMode(GameMode.SPECTATOR); //setSpecmode
                break;
        }
    }

    public enum PlayerState {
        SPECTATOR, PLAYER
    }

    public static void preparePlayerToGame(Player player) {
        if (player == null) return;

        player.setHealth(20);
        player.setFoodLevel(20);
    }

    public static void preparePlayerInventory(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.clear();
        inv.setArmorContents(null);
    }

    public static void teleportPlayer(Player player, String place) {
        switch (place) {
            case "random":
                player.teleport(teleportPlayerToRandomLoc());
                break;
            case "lobby":
                player.teleport(LocationGet.getLocation(Config.SPAWN_LOBBY));
                break;
            case "spectator":
                player.teleport(LocationGet.getLocation(Config.SPAWNPOINT_SPECTATOR));
                break;
            default:
                throw new IllegalArgumentException("Place not found (PlayerManager.teleportPlayer)");
        }
    }

    public static Location teleportPlayerToRandomLoc() {
        String stringLoc = Config.SPAWNPOINTS.get(getRandomInt());

        return LocationGet.getLocation(stringLoc);
    }

    public static Integer getRandomInt() {
        return ThreadLocalRandom.current().nextInt(12);
    }

    public static String getKilled(String nick) {
        return kills.get(nick);
    }
}
