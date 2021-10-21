package pl.bobi.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.bobi.builders.items.ItemBuilder;

public enum KitList {
    ZOMBIE(Material.STONE_SPADE, ChatColor.GOLD + "Zombie",
            ChatColor.GRAY + "Klasa Gracz"
            , " ",
            ChatColor.AQUA + "Helm: " + ChatColor.GRAY + "-",
            ChatColor.AQUA + "Klata: " + ChatColor.GRAY + "Skorzana",
            ChatColor.AQUA + "Spodnie: " + ChatColor.GRAY + "Skorzane",
            ChatColor.AQUA + "Buty: " + ChatColor.GRAY + "Skorzane",
            "  ",
            ChatColor.AQUA + "Przedmioty w eq: ",
            ChatColor.GRAY + "  -> Zelazna lopata (Knockback II, Sharpnes I)",
            "   ",
            ChatColor.AQUA + "Moce: ",
            "    ",
            ChatColor.WHITE + "" + ChatColor.BOLD + "Kliknij aby wybrac klase!"),

    BLAZE(Material.BLAZE_ROD, ChatColor.GOLD + "Blaze",
            ChatColor.GRAY + "Klasa Gracz"
            , " ",
            ChatColor.AQUA + "Helm: ",
            ChatColor.AQUA + "Klata: ",
            ChatColor.AQUA + "Spodnie: ",
            ChatColor.AQUA + "Buty: ",
            "  ",
            ChatColor.AQUA + "Przedmioty w eq: ",
            ChatColor.GRAY + "->",
            "   ",
            ChatColor.AQUA + "Moce: ",
            "    ",
            ChatColor.WHITE + "" + ChatColor.BOLD + "Kliknij aby wybrac klase!"),

    REAPER(Material.IRON_HOE, ChatColor.GOLD + "Reaper",
            ChatColor.GRAY + "Klasa " + ChatColor.GOLD + "Vip"
            , " ",
            ChatColor.AQUA + "Helm: " + ChatColor.GRAY + "-",
            ChatColor.AQUA + "Klata: " + ChatColor.GRAY + "Skorzana",
            ChatColor.AQUA + "Spodnie: " + ChatColor.GRAY + "Skorzane",
            ChatColor.AQUA + "Buty: " + ChatColor.GRAY + "Skorzane",
            "  ",
            ChatColor.AQUA + "Przedmioty w eq: ",
            ChatColor.GRAY + "  -> Zelazna motyka (Sharpnes III, Fire Aspect I)",
            ChatColor.GRAY + "  -> Mikstura leczenia",
            "   ",
            ChatColor.AQUA + "Moce: ",
            ChatColor.GRAY + "  -> Zabranie najblizszemu przeciwnikowi 2,5 hp, ",
            ChatColor.GRAY + "     Sposob uzycia: Klikniecie prawym przyciskiem na motyke w eq",
            "    ",
            ChatColor.GRAY + "by Ender690x",
            ChatColor.WHITE + "" + ChatColor.BOLD + "Kliknij aby wybrac klase!");

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
