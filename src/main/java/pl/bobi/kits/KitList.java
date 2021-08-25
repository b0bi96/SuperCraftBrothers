package pl.bobi.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.bobi.builders.items.ItemBuilder;

public enum KitList {
    ZOMBIE(Material.STONE_HOE, "Zombie", " ", ChatColor.WHITE + "Opis klasy: ",
            ChatColor.AQUA + "> Lopata", ChatColor.AQUA + "> Kupa", ChatColor.AQUA + "> Zbroja"),
    BLAZE(Material.BLAZE_ROD, "Blaze", " ", ChatColor.WHITE + "Opis klasy: ",
            ChatColor.AQUA + "> Luk", ChatColor.AQUA + "> Blaze rod");


    private final Material material;
    private final String displaname;
    private final String[] lore;

    KitList(Material material, String displaname, String... lore) {
        this.material = material;
        this.displaname = displaname;
        this.lore = lore;
    }

    public ItemStack build() {
        ItemBuilder itemBuilder = new ItemBuilder(material)
                .setName(this.displaname)
                .setLore(this.lore);

        return itemBuilder.toItemStack();
    }
}
