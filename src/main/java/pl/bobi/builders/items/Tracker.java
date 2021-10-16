package pl.bobi.builders.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Tracker {

    public static ItemStack build(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.COMPASS);
        itemBuilder
                .setLore(ChatColor.GREEN + "...");
        return itemBuilder.toItemStack();
    }
}
