package tech.mctown.cma;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;


public class CMALogger {
    public static void toConsole(String message) {
        System.out.println(message);
    }

    public static void toGame(String message, ServerPlayerEntity player) {
        player.sendMessage(Text.of(message), false);
    }

    public static void toGame(String message, MinecraftServer server) {
        Text textMessage = Text.of(message);
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            player.sendMessage(textMessage, false);
        }
    }

    public static void toGame(String message, PlayerEntity player) {
        player.sendMessage(Text.of(message));
    }
}
