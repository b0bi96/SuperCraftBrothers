package pl.bobi.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.bobi.kits.GameKit;

import java.util.HashMap;
import java.util.Map;


public class KitsManager {

    private final GameManager gameManager;

    @Getter
    private static Map<String, String> playerKit;

    public KitsManager(GameManager gameManager) {
        this.gameManager = gameManager;
        playerKit = new HashMap<>();
    }

    public void giveKits() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL && PlayerManager.getPlayers().contains(player.getDisplayName())) {
                if (!playerKit.isEmpty()) {
                    this.giveClassItems(player, playerKit.get(player.getDisplayName()));
                }
            }
        }
    }

    public void giveClassItems(Player player, String kitName) {
        GameKit gameKit = new GameKit(kitName);
        PlayerInventory playerInventory = player.getInventory();
        playerInventory.setHelmet(gameKit.getHelmet().made());
        playerInventory.setChestplate(gameKit.getChestplate().made());
        playerInventory.setLeggings(gameKit.getLeggings().made());
        playerInventory.setBoots(gameKit.getBoots().made());

        for (ItemStack materials : gameKit.getKitMaterials().getMaterials()) {
            playerInventory.addItem(materials);
        }
    }

    public static void addKitToPlayer(Player player, String kitName) {
        String playerNick = player.getDisplayName();
        if (playerKit.containsKey(playerNick)) {
            playerKit.replace(playerNick, kitName);
        } else {
            playerKit.put(playerNick, kitName);
        }
    }
}
