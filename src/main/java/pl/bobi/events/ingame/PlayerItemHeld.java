package pl.bobi.events.ingame;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;


public class PlayerItemHeld implements Listener {

    @EventHandler
    public void itemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getPlayer().getItemInHand();


        if (itemStack.getType() == Material.COMPASS) {
            float distance = 100;
            Bukkit.getServer().getScheduler().runTaskTimer(BobiSCB.getPlugin(), () -> {
                ItemStack item = event.getPlayer().getItemInHand();

                for (Entity entity : player.getNearbyEntities(distance, 250.0D, distance)) {
                    if (entity instanceof Player) {
                        Player target = (Player) entity;
                        player.setCompassTarget(target.getLocation());
                        int playerDistance = (int) player.getLocation().distance(target.getLocation());

                        if (item.getType() == Material.COMPASS) {
                            ItemMeta itemMeta = item.getItemMeta();
                            itemMeta.setDisplayName(ChatColor.AQUA + target.getName() + ChatColor.GRAY + ": " + playerDistance + " bloki");
                            item.setItemMeta(itemMeta);
                        }
                    }
                }
            }, 0, 20);
        }
    }
}
