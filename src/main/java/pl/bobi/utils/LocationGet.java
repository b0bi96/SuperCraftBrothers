package pl.bobi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationGet {

    public static Location getLocation(String loc) {
        if (loc == null || loc.isEmpty()) return null;
        String[] splitted = loc.split("/");

        return new Location(
                Bukkit.getServer().getWorld("world"),
                Double.parseDouble(splitted[0]),
                Double.parseDouble(splitted[1]),
                Double.parseDouble(splitted[2]),
                Float.parseFloat(splitted[3]),
                Float.parseFloat(splitted[4])
        );
    }
}
