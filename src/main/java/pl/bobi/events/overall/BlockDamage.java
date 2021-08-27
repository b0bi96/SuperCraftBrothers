package pl.bobi.events.overall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BlockDamage implements Listener {

    @EventHandler
    public void blockDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }
}
