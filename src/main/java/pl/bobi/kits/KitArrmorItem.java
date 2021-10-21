package pl.bobi.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;
import pl.bobi.utils.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class KitArrmorItem {

    private final String material;
    private final Map<Enchantment, Integer> enchantments;
    private final List<String> enchantsDes;


    public KitArrmorItem(String material, String kitName, String type) {
        this.enchantsDes = new ArrayList<>();
        this.enchantments = new HashMap<>();
        this.material = material;

        ConfigurationSection enchantmentSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items." + type + ".enchantments");
        if (enchantmentSection != null) {
            for (String enchantmentKey : enchantmentSection.getKeys(false)) {
                Enchantment enchantment =
                        Enchantment.getByName(enchantmentKey);

                if (enchantment != null) {
                    int level = enchantmentSection.getInt(enchantmentKey);
                    enchantments.put(enchantment, level);
                    if (Config.AUTO_KITS_DESCRIPTION) {
                        enchantsDes.add(enchantment.getName() + level);
                    }
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

        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
