package pl.bobi.kits;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;
import pl.bobi.utils.Config;
import pl.bobi.utils.TranslateItemName;

import java.util.*;

@Getter
public class KitMaterials {

    private final Map<Enchantment, Integer> enchantments;
    private final List<ItemStack> materials;
    private final List<String> materialList;
    private final String kitName;

    private final Configuration config;

    public KitMaterials(String kitName) {
        this.enchantments = new HashMap<>();
        this.materials = new ArrayList<>();
        this.materialList = new ArrayList<>();
        this.kitName = kitName;
        this.config = BobiSCB.getPlugin().getConfig();

        ConfigurationSection itemsSection = config.getConfigurationSection("kits." + kitName + ".items.materials");

        if (itemsSection == null) return;

        for (String itemMaterial : itemsSection.getKeys(false)) {
            createItemStack(new ItemStack(Material.getMaterial(itemMaterial)));
        }
    }

    private void createItemStack(ItemStack itemStack) {
        String path = "kits." + kitName + ".items.materials." + itemStack.getType().name();
        ConfigurationSection enchantmentSection = config.getConfigurationSection(path + ".enchantments");
        int amount = config.getInt(path + ".amount");
        String name = config.getString(path + ".name");

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (enchantmentSection != null) {
            for (String enchantmentKey : enchantmentSection.getKeys(false)) {
                Enchantment enchantment = Enchantment.getByName(enchantmentKey.toUpperCase());
                if (enchantment != null) {
                    int level = enchantmentSection.getInt(enchantmentKey);
                    itemMeta.addEnchant(enchantment, level, true);
                    if (Config.AUTO_KITS_DESCRIPTION) {
                        materialList.add(TranslateItemName.translateString(itemStack.getType().name()) + " (" +TranslateItemName.translateString(enchantment.getName() + level) + ")");
                    }
                } else {
                    if (Config.AUTO_KITS_DESCRIPTION) {
                        materialList.add(TranslateItemName.translateString(itemStack.getType().name()));
                    }
                }
            }
        }

        if (name != null) {
            itemMeta.setLore(Collections.singletonList(name));
        }

        itemMeta.spigot().setUnbreakable(true);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        materials.add(itemStack);
    }
}
