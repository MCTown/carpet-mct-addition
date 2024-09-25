package tech.mctown.cma.mixins.editableSign;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.mctown.cma.CMASettings;

@Mixin(SignBlockEntity.class)
public abstract class SignBlockEntityMixin {
    @Shadow
    abstract public void setEditable(boolean editable);

    @Inject(method = "onActivate", at = @At("HEAD"))
    public void editSign(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> ci) {
        if (CMASettings.editableSign && player.getAbilities().allowModifyWorld && player.getMainHandStack().isOf(Items.AIR) && player.isSneaking()) {
            this.setEditable(true);
            player.openEditSignScreen((SignBlockEntity) (Object) this);
        }
    }
}
