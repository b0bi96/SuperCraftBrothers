package pl.bobi.builders.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.bobi.kits.KitList;

public class ChooseKitsToPlayGui extends CreateGui {

    public ChooseKitsToPlayGui(String tittle, int slots) {
        super(tittle, slots);
    }

    public void createGui(Player player){
        super.addItems(1, KitList.BLAZE.build());
        super.addItems(2, KitList.ZOMBIE.build());
        super.openGui(player);
    }
}
