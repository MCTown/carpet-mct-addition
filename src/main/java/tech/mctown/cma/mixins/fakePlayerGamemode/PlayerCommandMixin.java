package tech.mctown.cma.mixins.fakePlayerGamemode;

import carpet.commands.PlayerCommand;
import carpet.utils.Messenger;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import tech.mctown.cma.CMASettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerCommand.class)
public abstract class PlayerCommandMixin {
    @Inject(method = "cantSpawn", at = @At("HEAD"), remap = false, cancellable = true)
    static private void checkGamemode(CommandContext<ServerCommandSource> context, CallbackInfoReturnable<Boolean> ci) {
        try {
            StringArgumentType.getString(context, "gamemode");
        } catch (IllegalArgumentException e) {
            return;
        }
        if (!CMASettings.fakePlayerGamemode) {
            ci.setReturnValue(true);
        }
    }
}
