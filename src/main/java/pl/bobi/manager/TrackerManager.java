package pl.bobi.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TrackerManager {
    private static float distance = 100;
    private static ItemStack item = new ItemStack(Material.COMPASS);
    private static ItemMeta itemMeta = item.getItemMeta();

    public static void trackPlayer() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Entity entity = player.getNearbyEntities(distance, 250.0D, distance).get(0);
            Player target = (Player) entity;
            int playerDistance = (int) player.getLocation().distance(target.getLocation());

            if (entity == null) {
                itemMeta.setDisplayName(ChatColor.AQUA + "Brak graczy");
                return;
            }
            itemMeta.setDisplayName(ChatColor.AQUA + target.getName() + ChatColor.GRAY + ": " + playerDistance + " kratki");
            item.setItemMeta(itemMeta);

            player.setCompassTarget(target.getLocation());
        }
    }
}
