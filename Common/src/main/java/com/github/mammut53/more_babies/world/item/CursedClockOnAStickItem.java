package com.github.mammut53.more_babies.world.item;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.MoreBabiesConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CursedClockOnAStickItem extends Item {

    public CursedClockOnAStickItem(Item.Properties properties) {
        super(properties);
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        MutableComponent mutableComponent = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MoreBabiesConstants.MOD_ID, "cursed_clock_on_a_stick.desc")));
        list.add(mutableComponent.withStyle(ChatFormatting.GRAY));
    }

    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (!(livingEntity instanceof Player)) {
            if (!player.level.isClientSide && livingEntity.isAlive()) {

                if (livingEntity instanceof Mob mob) {
                    EntityType<? extends Mob> babyType = MoreBabiesCommon.PARENT_BABY_RELATION.get(mob.getType());
                    EntityType<? extends Mob> entityType = MoreBabiesCommon.PARENT_BABY_RELATION.inverse().get(mob.getType());

                    if (babyType == null && entityType == null) {

                        boolean isBaby = mob.isBaby();
                        mob.setBaby(!isBaby);

                        boolean wasSuccessful = isBaby != mob.isBaby();
                        if (wasSuccessful) {
                            playConversionSound(mob);
                        } else {
                            mob.level.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.REDSTONE_TORCH_BURNOUT, mob.getSoundSource(), 0.5F, 0.0F);
                        }

                        return InteractionResult.sidedSuccess(player.level.isClientSide);
                    }

                    if (babyType != null) {
                        playConversionSound(mob);
                        mob.convertTo(babyType, true);
                        return InteractionResult.sidedSuccess(player.level.isClientSide);
                    }

                    playConversionSound(mob);
                    mob.convertTo(entityType, true);
                    return InteractionResult.sidedSuccess(player.level.isClientSide);
                }
            }

            return InteractionResult.sidedSuccess(player.level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    private static void playConversionSound(Mob mob) {
        mob.level.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.AMETHYST_BLOCK_STEP, mob.getSoundSource(), 1.0F, 0.0F);
        mob.level.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.AMETHYST_BLOCK_STEP, mob.getSoundSource(), 0.5F, 0.2F);
    }

}
