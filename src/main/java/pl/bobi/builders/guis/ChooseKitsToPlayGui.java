package pl.bobi.builders.guis;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pl.bobi.BobiSCB;
import pl.bobi.kits.GameKit;
import pl.bobi.kits.KitDescription;
import pl.bobi.kits.KitList;

public class ChooseKitsToPlayGui extends CreateGui {

    public ChooseKitsToPlayGui(String tittle, int slots) {
        super(tittle, slots);
    }

    public void createGui(Player player) {
        super.addItems(1, KitList.BLAZE.build());
        super.addItems(2, KitList.ZOMBIE.build());

        ConfigurationSection section = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits");
        int x = 0;
        for (String s : section.getKeys(false)) {
            GameKit gameKit = new GameKit(s);
            super.addItems(gameKit.getPlaceInGui().getSlotnumber(), KitDescription.kitDes(s, gameKit.getPlaceInGui().getMaterial(), gameKit));
        }
        super.openGui(player);
    }
}
