package pl.bobi.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.bobi.builders.items.ItemBuilder;
import pl.bobi.utils.TranslateItemName;

import java.util.stream.Collectors;

public class KitDescription {

    public static ItemStack kitDes(String name, String material, GameKit gameKit) {
        ItemBuilder itemBuilder = new ItemBuilder(Material.getMaterial(material));

        itemBuilder
                .setName(ChatColor.GOLD + "" + ChatColor.BOLD + name)
                .setLore(
                        (gameKit.isVipKit() ? ChatColor.GOLD + "Klasa " + ChatColor.GOLD + "Vip" : ChatColor.GRAY + "Klasa Gracz"),
                        " ",
                        ChatColor.AQUA + "Helm: " + ChatColor.GRAY + TranslateItemName.translateString(gameKit.getHelmet().getMaterial()) + " (" +
                                gameKit.getHelmet().getEnchantsDes().stream().map(Object::toString).collect(Collectors.joining(" ")) + ")",
                        ChatColor.AQUA + "Klata: " + ChatColor.GRAY + TranslateItemName.translateString(gameKit.getChestplate().getMaterial()) + " (" +
                                gameKit.getChestplate().getEnchantsDes().stream().map(Object::toString).collect(Collectors.joining(" ")) + ")",
                        ChatColor.AQUA + "Spodnie: " + ChatColor.GRAY + TranslateItemName.translateString(gameKit.getLeggings().getMaterial()) + " (" +
                                gameKit.getLeggings().getEnchantsDes().stream().map(Object::toString).collect(Collectors.joining(" ")) + ")",
                        ChatColor.AQUA + "Buty: " + ChatColor.GRAY + TranslateItemName.translateString(gameKit.getBoots().getMaterial()) + " (" +
                                gameKit.getBoots().getEnchantsDes().stream().map(Object::toString).collect(Collectors.joining(" ")) + ")",
                        "  ",
                        ChatColor.AQUA + "Przedmioty w eq: ",
                        ChatColor.GRAY + "   -> " + gameKit.getKitMaterials().getMaterialList().stream().map(Object::toString).collect(Collectors.joining(", ")),
                        "  ",
                        ChatColor.WHITE + "Kliknij aby wybrac!"
                );

        return itemBuilder.toItemStack();
    }
}
