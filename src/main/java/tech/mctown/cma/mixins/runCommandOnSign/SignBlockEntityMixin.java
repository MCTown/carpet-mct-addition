package tech.mctown.cma.mixins.runCommandOnSign;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.mctown.cma.CMALogger;
import tech.mctown.cma.CMASettings;
import tech.mctown.cma.CMAUtils;

import javax.swing.text.Position;
import java.awt.*;
import java.util.List;


@Mixin(SignBlockEntity.class) // 向告示牌实体注入代码
public abstract class SignBlockEntityMixin {
    //
    // 如果告示牌上的文本以 / 开头，且玩家手持物品为空，且玩家未潜行，则执行命令
    // 倘若不满足，则执行原版告示牌的编辑功能
    @Inject(method = "tryChangeText", at = @At("HEAD"), cancellable = true)
    public void PreventChangeTextWhenEmptyHands(PlayerEntity player, boolean front, List<FilteredMessage> messages, CallbackInfo ci) {
        if (CMASettings.runCommandOnSign) {
            if (player instanceof ServerPlayerEntity) {
                CMALogger.debug("Player is trying to change the text, checking sign text");
                ServerWorld world = (ServerWorld) player.getEntityWorld();
                BlockPos pos = ((SignBlockEntity) (Object) this).getPos();
                if (world.getBlockEntity(pos) instanceof SignBlockEntity signBlockEntity) {
                    boolean isFront = signBlockEntity.isPlayerFacingFront(player);
                    SignText texts = signBlockEntity.getText(isFront);
                    Text[] text = texts.getMessages(false);
                    CMALogger.debug("Sign text: " + text[0].getString());
                    if (text[0].getString().startsWith("/")) {
                        CMALogger.debug("Player is trying to change the text, but the text starts with /");
                        Text message = Text.literal(CMAUtils.getTranslation("carpet.runCommandOnSignTips"));
                        player.sendMessage(message, false);
                    }
                }
            }
        }
    }
}
//    @Shadow

//    protected abstract Text[] getTexts(boolean filtered);
//
//    @Inject(method = "onActivate", at = @At("HEAD"))
//    public void runCommandOnActivated(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
//        if (CMASettings.runCommandOnSign) {
//            Text[] texts = this.getTexts(player.shouldFilterText());
//            StringBuilder rawText = new StringBuilder();
//            for (Text t : texts) {
//                rawText.append(t.getString());
//            }
//            String text = rawText.toString();
//            if (text.startsWith("/") && player.getMainHandStack().isOf(Items.AIR) && !player.isSneaking()) {
//                String actualCommand = text.substring(1);
//                // No cheating!
//                ServerCommandSource commandSource = player.getCommandSource();
//                commandSource.getServer().getCommandManager().execute(commandSource, actualCommand);
//            }
//        }
//    }
