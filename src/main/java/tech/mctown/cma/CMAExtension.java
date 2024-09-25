package tech.mctown.cma;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.mctown.cma.commands.DumpEntityCommand;

import java.util.Map;

public class CMAExtension implements CarpetExtension {
    public static final CMAExtension INSTANCE = new CMAExtension();
    public static final String MODID = "CMA";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CMASettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandBuildContext) {
        DumpEntityCommand.registerCommand(dispatcher);
    }

    // 覆写 canHasTranslations 方法，用于加载翻译文件
    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return CMATranslations.getTranslationFromResourcePath(lang);
    }
}
