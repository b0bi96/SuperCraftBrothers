package pl.bobi.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class KitMaterials {

    private final int amount;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final List<ItemStack> materials = new ArrayList<>();
    private final String kitName;

    public KitMaterials(int amount, String kitName) {
        this.amount = amount;
        this.kitName = kitName;

        ConfigurationSection itemsSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items.materials");

        if (itemsSection == null) return;

        for (String itemMaterial : itemsSection.getKeys(false)) {
            createItemStack(new ItemStack(Material.getMaterial(itemMaterial)));
        }
    }

    private void createItemStack(ItemStack itemStack) {
        ConfigurationSection enchantmentSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items.materials." + itemStack.getType().name() + ".enchantments");
        if (enchantmentSection != null) {
            for (String enchantmentKey : enchantmentSection.getKeys(false)) {
                Enchantment enchantment = Enchantment.getByName(enchantmentKey);

                if (enchantment != null) {
                    int level = enchantmentSection.getInt(enchantmentKey);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.addEnchant(enchantment, level, true);
                    itemStack.setItemMeta(itemMeta);
                }
            }
        }

        materials.add(itemStack);
    }


}
