package pl.bobi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationGet {

    public static Location getLocation(String loc) {
        if (loc == null || loc.isEmpty()) return null;
        String[] splitted = loc.split("/");
        if (splitted.length < 5) return null;

        return new Location(
                Bukkit.getServer().getWorld(splitted[0]),
                Double.parseDouble(splitted[1]),
                Double.parseDouble(splitted[2]),
                Double.parseDouble(splitted[3]),
                Float.parseFloat(splitted[4]),
                Float.parseFloat(splitted[5]));
    }
}
