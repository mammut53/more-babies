package io.github.mammut53.more_babies.world.item;

import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class CursedClockOnAStickItem extends Item {

    public CursedClockOnAStickItem(final Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(final @NonNull ItemStack stack, final @NonNull TooltipContext context, final @NonNull TooltipDisplay displayComponent, final Consumer<Component> textConsumer, final @NonNull TooltipFlag type) {
        final MutableComponent component = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "cursed_clock_on_a_stick.desc")));
        textConsumer.accept(component.withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NonNull InteractionResult interactLivingEntity(final @NonNull ItemStack itemStack, final @NonNull Player player, final @NonNull LivingEntity target, final @NonNull InteractionHand type) {
        if (target instanceof Mob mob && mob.isAlive()) {
            final boolean isBaby = mob.isBaby();
            mob.setBaby(!isBaby);

            final boolean successful = isBaby != mob.isBaby();
            if (successful) {
                playConversionSound(mob);
                itemStack.hurtAndBreak(1, player, type.asEquipmentSlot());
                return InteractionResult.SUCCESS;
            } else {
                mob.level().playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.REDSTONE_TORCH_BURNOUT, mob.getSoundSource(), 0.4F, 0.0F);
                return InteractionResult.PASS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    private static void playConversionSound(final Mob mob) {
        final Level level = mob.level();
        level.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.AMETHYST_BLOCK_STEP, mob.getSoundSource(), 1.0F, 0.0F);
        level.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.AMETHYST_BLOCK_STEP, mob.getSoundSource(), 0.5F, 0.2F);
    }

}
