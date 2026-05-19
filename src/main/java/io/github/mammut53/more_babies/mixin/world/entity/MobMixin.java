package io.github.mammut53.more_babies.mixin.world.entity;

import io.github.mammut53.more_babies.world.item.CursedClockOnAStickItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements Targeting, EquipmentUser, Leashable {

    protected MobMixin(final EntityType<? extends LivingEntity> type, final Level level) {
        super(type, level);
    }

    @Inject(
            method = "checkAndHandleImportantInteractions",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCheckAndHandleImportantInteractions(final Player player, final InteractionHand hand, final CallbackInfoReturnable<InteractionResult> cir) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof CursedClockOnAStickItem) {
            final InteractionResult result = itemStack.interactLivingEntity(player, (Mob)(Object) this, hand);
            cir.setReturnValue(result);
        }
    }

}
