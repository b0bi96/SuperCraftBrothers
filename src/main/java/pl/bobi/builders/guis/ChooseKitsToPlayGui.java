package pl.bobi.builders.guis;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pl.bobi.BobiSCB;
import pl.bobi.kits.GameKit;
import pl.bobi.kits.KitDescription;
import pl.bobi.kits.KitList;
import pl.bobi.utils.Config;

public class ChooseKitsToPlayGui extends CreateGui {

    public ChooseKitsToPlayGui(String tittle, int slots) {
        super(tittle, slots);
    }

    public void createGui(Player player) {
        if (Config.AUTO_KITS_DESCRIPTION) {
            ConfigurationSection section = BobiSCB.getPlugin().getConfig().getConfigurationSection("kits");

            for (String s : section.getKeys(false)) {
                GameKit gameKit = new GameKit(s);
                super.addItems(gameKit.getPlaceInGui().getSlotnumber(), KitDescription.kitDes(s, gameKit.getPlaceInGui().getMaterial(), gameKit));
            }
        } else {
            super.addItems(10, KitList.BLAZE.build());
            super.addItems(11, KitList.ZOMBIE.build());
            super.addItems(28, KitList.REAPER.build());
        }
        super.openGui(player);
    }
}
