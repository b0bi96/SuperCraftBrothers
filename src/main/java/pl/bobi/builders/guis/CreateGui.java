package pl.bobi.builders.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.bobi.builders.items.EmptyGlass;


public class CreateGui {
    private final Inventory inventory;

    public CreateGui(String tittle, int slots) {
        this.inventory = Bukkit.getServer().createInventory(null, slots, tittle);

        for (int x = 0; x < slots; x++) {
            inventory.setItem(x, EmptyGlass.build());
        }
    }

    public void openGui(Player player){
        if (this.inventory == null) return;
        player.openInventory(inventory);
    }

    public void addItems(int index, ItemStack itemStack){
        if(this.inventory == null) return;
        this.inventory.setItem(index, itemStack);
    }
}
