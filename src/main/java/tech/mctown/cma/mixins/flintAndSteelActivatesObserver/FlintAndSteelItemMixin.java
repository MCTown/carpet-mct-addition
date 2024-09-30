package tech.mctown.cma.mixins.flintAndSteelActivatesObserver;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ObserverBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.mctown.cma.CMALogger;
import tech.mctown.cma.CMASettings;

import java.lang.reflect.Method;

@Mixin(FlintAndSteelItem.class)
public abstract class FlintAndSteelItemMixin {
    @Inject(method = "useOnBlock", at = @At("TAIL"), cancellable = true)
        // At指定注入代码插入的位置
    void activatesObserver(ItemUsageContext context, CallbackInfoReturnable<ActionResult> ci) {
        if (CMASettings.flintAndSteelActivatesObserver) {
            PlayerEntity playerEntity = context.getPlayer();
            if (playerEntity == null) return;
            World world = context.getWorld();
            BlockPos blockPos = context.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.OBSERVER) && !playerEntity.isSneaking()) {
                ObserverBlock observer = (ObserverBlock) blockState.getBlock();
                try {
                    Method scheduleTickMethod = ObserverBlock.class.getDeclaredMethod("scheduledTick", BlockState.class, ServerWorld.class, BlockPos.class, Random.class);
                    scheduleTickMethod.setAccessible(true); // 设置为可访问
                    ServerWorld serverWorld = (ServerWorld) world;
                    scheduleTickMethod.invoke(observer, blockState, serverWorld, blockPos, null);
                } catch (Exception e) {
                    CMALogger.toConsole("Failed to activate observer");
                }
                ci.setReturnValue(ActionResult.success(world.isClient()));
            }
        }
    }
}
