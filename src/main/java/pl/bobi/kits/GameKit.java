package pl.bobi.kits;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import pl.bobi.BobiSCB;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameKit {

    private final FileConfiguration config = BobiSCB.getPlugin().getConfig();
    private boolean isVipKit;
    private KitArrmorItem helmet;
    private KitArrmorItem chestplate;
    private KitArrmorItem leggings;
    private KitArrmorItem boots;
    private KitMaterials kitMaterials;

    public GameKit(final String name) {
        if (config.getString("kits." + name) == null) return;

        String path = "kits." + name + ".";

        this.isVipKit = config.getBoolean(path + "isVipKit");
        this.helmet = new KitArrmorItem(
                config.getString(path + "items.helmet.material"),
                name,
                "helmet"
        );

        this.chestplate = new KitArrmorItem(
                config.getString(path + "items.chestplate.material"),
                name,
                "chestplate"
        );

        this.leggings = new KitArrmorItem(
                config.getString(path + "items.leggings.material"),
                name,
                "leggings"
        );

        this.boots = new KitArrmorItem(
                config.getString(path + "items.boots.material"),
                name,
                "boots"
        );

        this.kitMaterials = new KitMaterials(
            1,
            name
        );


    }
}
