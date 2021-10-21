package pl.bobi.kits.specials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

public class Reaper {

    private static final Map<String, Long> cooldown = new HashMap<>();

//    @Getter
//    private static Set<Player> infPlayer = new HashSet<>();

    public static void attackPlayer(Player reaperPlayer) {
        String playerNick = reaperPlayer.getName();

        if (cooldown.containsKey(playerNick)) {
            int time = 20;
            long secLeft = ((cooldown.get(playerNick) / 1000) + time) - (System.currentTimeMillis() / 1000);

            if (secLeft > 0) {
                reaperPlayer.sendMessage(ChatColor.AQUA + "Swojej mocy bedziesz mogl uzyc za: " + ChatColor.WHITE + secLeft);
                return;
            }
        }

        float distance = 100;
        for (Entity entity : reaperPlayer.getNearbyEntities(distance, 250.0D, distance)) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
//                int playerDistance = (int) reaperPlayer.getLocation().distance(target.getLocation());

//                if (playerDistance <= 5) {
//                    infPlayer.add(target);
                reaperPlayer.sendMessage(ChatColor.GOLD + "Uzyles swojej mocy na " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + "!");
                double hp = target.getHealth();
                target.setHealth(hp - 6.5);
//                } else {
//                    reaperPlayer.sendMessage("Nie ma w odleglosci 5 kratek!");
//                    return;
//                }
            }
        }

        cooldown.put(playerNick, System.currentTimeMillis());
    }
}
