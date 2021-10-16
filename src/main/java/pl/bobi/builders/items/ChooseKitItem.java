package pl.bobi.builders.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChooseKitItem {

    public static ItemStack build(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.EMERALD);
        itemBuilder
                .setLore(ChatColor.GREEN + "Kilknij aby wybrac klase!")
                .setName(ChatColor.AQUA + "Wybor Klasy");
        return itemBuilder.toItemStack();
    }
}
