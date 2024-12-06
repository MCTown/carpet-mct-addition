package tech.mctown.cma;

import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import tech.mctown.cma.CMALogger;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.*;

public class CMAEntry implements ModInitializer {
    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(CMAExtension.INSTANCE);
        CMALogger.toConsole("CMA Loaded");
        // Practice
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> Text.literal("调用 /foo，不带参数"), false);
                    CMALogger.toGame("123123", Objects.requireNonNull(context.getSource().getPlayer()));
                    return 1;
                })));
    }
}
