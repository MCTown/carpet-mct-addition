package tech.mctown.cma;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.mctown.cma.commands.DumpEntityCommand;

public class CMAExtension implements CarpetExtension {
    public static final CMAExtension INSTANCE = new CMAExtension();
    public static final String MODID = "CMA";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public String version() {
        return MODID;
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CMASettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        DumpEntityCommand.registerCommand(dispatcher);
    }
}
