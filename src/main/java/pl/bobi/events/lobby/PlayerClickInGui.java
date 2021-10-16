package pl.bobi.events.lobby;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.bobi.manager.KitsManager;

public class PlayerClickInGui implements Listener {

    @EventHandler
    public void onGuiClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        final ItemStack itemStack = e.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) return;

        final String itemName = itemStack.getItemMeta().getDisplayName();

        if (player == null) return;
        if (e.getInventory().getTitle().equals(ChatColor.AQUA + "Wybor klasy")) {
            KitsManager.addKitToPlayer(player, ChatColor.stripColor(itemName));
            player.sendMessage(ChatColor.GOLD + "Wybrales klase: " + itemName);

            e.setCancelled(true);
            player.closeInventory();
        }
        //TODO Check if player is vip
        e.setCancelled(true);
    }
}
