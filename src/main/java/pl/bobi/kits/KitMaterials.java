package pl.bobi.kits;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bobi.BobiSCB;
import pl.bobi.utils.TranslateItemName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class KitMaterials {

    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final List<ItemStack> materials = new ArrayList<>();
    private final List<String> materialList = new ArrayList<>();
    private final String kitName;

    public KitMaterials(String kitName) {
        this.kitName = kitName;

        ConfigurationSection itemsSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items.materials");

        if (itemsSection == null) return;

        for (String itemMaterial : itemsSection.getKeys(false)) {
            createItemStack(new ItemStack(Material.getMaterial(itemMaterial)));
        }
    }

    private void createItemStack(ItemStack itemStack) {
        ConfigurationSection enchantmentSection = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits." + kitName + ".items.materials." + itemStack.getType().name() + ".enchantments");
        int amount = BobiSCB.getPlugin().getConfig().getInt("kits." + kitName + ".items.materials." + itemStack.getType().name() + ".amount");
        if (enchantmentSection != null) {
            for (String enchantmentKey : enchantmentSection.getKeys(false)) {
                Enchantment enchantment = Enchantment.getByName(enchantmentKey.toUpperCase());
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.spigot().setUnbreakable(true);
                if (enchantment != null) {
                    int level = enchantmentSection.getInt(enchantmentKey);
                    itemMeta.addEnchant(enchantment, level, true);
                    itemStack.setItemMeta(itemMeta);
                    materialList.add(TranslateItemName.translateString(itemStack.getType().name()) + " (" +TranslateItemName.translateString(enchantment.getName() + level) + ")");
                } else {
                    materialList.add(TranslateItemName.translateString(itemStack.getType().name()));
                }
            }
        }



        itemStack.setAmount(amount);
        materials.add(itemStack);
    }
}
