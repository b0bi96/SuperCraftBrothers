package pl.bobi.events.overall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.bobi.manager.GameManager;

public class BlockBreak implements Listener {

    public BlockBreak(GameManager gameManager) {
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if (event.getBlock() == null) return;
        event.setCancelled(true);
    }
}
