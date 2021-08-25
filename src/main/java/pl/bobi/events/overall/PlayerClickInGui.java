package pl.bobi.events.overall;

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
        if (player == null) return;
        if (!e.getInventory().getTitle().equals(ChatColor.AQUA + "Wybor klasy")) return;

        KitsManager.addKitToPlayer(player, itemStack.getItemMeta().getDisplayName());
        player.sendMessage("Wybrales klase: " + itemStack.getItemMeta().getDisplayName());

        e.setCancelled(true);
        player.closeInventory();
    }
}
