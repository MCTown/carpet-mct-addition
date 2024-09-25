package tech.mctown.cma.mixins.runCommandOnSign;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.mctown.cma.CMASettings;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin {
    @Shadow
    protected abstract Text[] getTexts(boolean filtered);

    @Inject(method = "onActivate", at = @At("HEAD"))
    public void runCommandOnActivated(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
        if (CMASettings.runCommandOnSign) {
            Text[] texts = this.getTexts(player.shouldFilterText());
            StringBuilder rawText = new StringBuilder();
            for (Text t : texts) {
                rawText.append(t.getString());
            }
            String text = rawText.toString();
            if (text.startsWith("/") && player.getMainHandStack().isOf(Items.AIR) && !player.isSneaking()) {
                String actualCommand = text.substring(1);
                // No cheating!
                ServerCommandSource commandSource = player.getCommandSource();
                commandSource.getServer().getCommandManager().execute(commandSource, actualCommand);
            }
        }
    }
}
