package pl.bobi.kits;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;

import java.util.HashMap;
import java.util.Map;

public class KitArrmorItem {

    private final String material;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();

    public KitArrmorItem(String material, String kitName, String type) {
        this.material = material;

        ConfigurationSection enchantmentSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items." + type + ".enchantments");
        if (enchantmentSection != null) {
            for (String enchantmentKey : enchantmentSection.getKeys(false)) {
                Enchantment enchantment =
                        Enchantment.getByName(enchantmentKey);

                if (enchantment != null) {
                    int level = enchantmentSection.getInt(enchantmentKey);
                    enchantments.put(enchantment, level);
                }
            }
        }
    }

    public ItemStack made() {
        if (this.material == null) return null;

        ItemStack itemStack = new ItemStack(Material.getMaterial(this.material));
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> enchantEntry : enchantments.entrySet()) {
                itemMeta.addEnchant(
                        enchantEntry.getKey(),
                        enchantEntry.getValue(),
                        true
                );
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
