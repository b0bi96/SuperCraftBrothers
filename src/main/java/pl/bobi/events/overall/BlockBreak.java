package pl.bobi.events.overall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if (event.getBlock() == null) return;
        event.setCancelled(true);
    }
}
