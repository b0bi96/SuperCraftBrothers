package pl.bobi.events.ingame;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.bobi.kits.specials.Reaper;

public class PlayerItemCick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        Action action = e.getAction();

        if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && itemStack.getItemMeta().getLore().contains("MOTYKA REAPER")) {
            Reaper.attackPlayer(player);
        }
    }
}
