package pl.bobi.kits;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import pl.bobi.BobiSCB;

@Getter
public class GuiInfo {

    private final FileConfiguration config = BobiSCB.getPlugin().getConfig();
    private final int slotnumber;
    private final String material;

    public GuiInfo(String name) {
        this.slotnumber = config.getInt("kits." + name + ".gui." + "slotnumber");
        this.material = config.getString("kits." + name + ".gui." + "material");
    }
}
