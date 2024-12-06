package tech.mctown.cma;

import carpet.CarpetSettings;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

import java.util.Map;

public class CMAUtils {
    public static void executeCommand(ServerPlayerEntity player, String command) {
        if (player == null || command == null || command.trim().isEmpty()) {
            throw new IllegalArgumentException("Player or command cannot be null or empty");
        }
// 若带有 / 则去掉
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        var server = player.getServer();
        if (server == null) {
            throw new IllegalStateException("Player is not on a server");
        }

        ServerCommandSource source = player.getCommandSource();
        CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager().getDispatcher();

        try {
            // 解析和执行命令
            var parseResults = dispatcher.parse(command, source);
            dispatcher.execute(parseResults);
        } catch (Exception e) {
            Text text = Text.literal(getTranslation("carpet.commandWentWrong"))
                    .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.RED))).append(Text.literal(" /" + command + ": " + e.getMessage())
                            .setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.RED)).withUnderline(true)));
            player.sendMessage(text, false);
            CMALogger.debug("Failed to execute command: " + command + e.getMessage());
        }
    }

    public static String getTranslation(String key) {
        Map<String, String> lang = CMATranslations.getTranslationFromResourcePath(CarpetSettings.language);
        String translationText = lang.get(key);
        CMALogger.debug("Translation with key" + key + ": " + translationText);
        return lang.get(key);
    }
}