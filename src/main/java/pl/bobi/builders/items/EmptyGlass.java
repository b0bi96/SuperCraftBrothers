package pl.bobi.builders.items;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EmptyGlass {

    public static ItemStack build(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7);
        itemBuilder
                .setName(" ");
        return itemBuilder.toItemStack();
    }
}
