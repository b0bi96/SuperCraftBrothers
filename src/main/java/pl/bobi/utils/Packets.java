package pl.bobi.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Packets {

    public static void sendTitle(Player player, String text, int len) {
        PlayerConnection connection = ((CraftPlayer) player.getPlayer()).getHandle().playerConnection;
        IChatBaseComponent text2 = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+ text +"\"}");
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, text2);
        PacketPlayOutTitle lenght = new PacketPlayOutTitle(5, len, 5);
        connection.sendPacket(packet);
        connection.sendPacket(lenght);
    }
}
