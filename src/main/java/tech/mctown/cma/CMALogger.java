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

    public static void debug(String message) {
        System.out.println("\033[34m[CMA DEBUG]\033[0m" + "\033[33m" + message + "\033[0m");
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
        player.sendMessage(Text.of(message),false);
    }

    public static void error(String s, Exception e) {
        System.out.println("\033[31m[CMA ERROR]\033[0m" + "\033[33m" + s + "\033[0m");
        e.printStackTrace();
    }
}
