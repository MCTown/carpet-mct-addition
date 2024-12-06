package tech.mctown.cma.mixins.runCommandOnSign;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.mctown.cma.CMALogger;
import tech.mctown.cma.CMASettings;
import tech.mctown.cma.CMAUtils;

import java.util.Arrays;
import java.util.Objects;

@Mixin(net.minecraft.block.AbstractSignBlock.class) // 向告示牌注入代码
public class AbstractSignBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    public void cancelOpenGUI(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (CMASettings.runCommandOnSign) {
            if (player instanceof ServerPlayerEntity) {
                if (world.getBlockEntity(pos) instanceof SignBlockEntity signBlockEntity) {
                    if (player.getMainHandStack().isOf(Items.AIR) && player.isSneaking()) {
                        CMALogger.debug("AbstractSignBlockMixin cancelOpenGUI");
                        boolean isFront = signBlockEntity.isPlayerFacingFront(player);
                        SignText texts = signBlockEntity.getText(isFront);
                        Text[] text = texts.getMessages(false);
                        CMALogger.debug(Arrays.toString(text));
                        // 若告示牌上的文本以 / 开头，则执行命令
                        StringBuilder command = new StringBuilder(text[0].getString());
                        if (command.toString().startsWith("/")) {
                            for (int i = 1; i < 4; i++) {
                                if (Objects.equals(text[i].getString(), "")) {
                                    continue;
                                }
                                command.append(text[i].getString());
                            }
                            CMALogger.debug("Command: " + command);
                            CMAUtils.executeCommand((ServerPlayerEntity) player, command.toString());
//                            Objects.requireNonNull(player.getServer()).getCommandManager().executeWithPrefix(createCommandSource(player, world, pos), command.toString());
                        }
                        cir.setReturnValue(ActionResult.SUCCESS);
                    }
                }
            }
        }
    }

    @Unique
    private ServerCommandSource createCommandSource(PlayerEntity player, World world, BlockPos pos) {
        String string = player == null ? "Sign" : player.getName().getString();
        Text text = (Text) (player == null ? Text.literal("Sign") : player.getDisplayName());
        return new ServerCommandSource(CommandOutput.DUMMY, Vec3d.ofCenter(pos), Vec2f.ZERO, (ServerWorld) world, 2, string, text, world.getServer(), player);
    }
}
