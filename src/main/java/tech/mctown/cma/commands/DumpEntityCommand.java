package tech.mctown.cma.commands;

import carpet.utils.CommandHelper;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import tech.mctown.cma.CMASettings;

import static net.minecraft.server.command.CommandManager.literal;

public class DumpEntityCommand {
    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("dumpentity"). // 指定指令名称
                        requires(src -> CommandHelper.canUseCommand(src, CMASettings.commandDumpEntity))
                        .executes(DumpEntityCommand::dumpEntity)
        );
    }

    public static int dumpEntity(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource src = ctx.getSource();
        ServerWorld world = src.getWorld();
        for (Entity e : world.iterateEntities()) {
            Messenger.m(src, Text.of(e.toString()));
        }
        return 0;
    }
}
