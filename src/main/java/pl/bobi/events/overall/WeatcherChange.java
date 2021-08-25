package pl.bobi.events.overall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatcherChange implements Listener {

    @EventHandler
    public void weatcher(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
