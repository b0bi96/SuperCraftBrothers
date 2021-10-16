package pl.bobi.events.lobby;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.bobi.builders.guis.ChooseKitsToPlayGui;

public class PlayerClickItem implements Listener {

    @EventHandler
    public void itemClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        if (itemStack == null) return;
        if (player.getItemInHand().getItemMeta().getLore() == null) return;
        if (!player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GREEN + "Kilknij aby wybrac klase!")) return;

        new ChooseKitsToPlayGui(ChatColor.AQUA + "Wybor klasy", 27).createGui(player);
    }
}
