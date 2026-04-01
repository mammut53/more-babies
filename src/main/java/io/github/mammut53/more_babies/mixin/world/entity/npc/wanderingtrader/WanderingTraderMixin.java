package io.github.mammut53.more_babies.mixin.world.entity.npc.wanderingtrader;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager implements Consumable.OverrideConsumeSound {

    @Unique
    private static final EntityDimensions more_babies$BABY_DIMENSIONS = EntityDimensions.scalable(0.49F, 0.99F).withEyeHeight(0.63F);

    protected WanderingTraderMixin(final EntityType<? extends AbstractVillager> type, final Level level) {
        super(type, level);
    }

    @ModifyArg(
            method = "readAdditionalSaveData",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/wanderingtrader/WanderingTrader;setAge(I)V"
            )
    )
    private int injectReadAdditionalSaveDataSetBaby(final int input) {
        return this.getAge();
    }

    @Override
    public @NonNull EntityDimensions getDefaultDimensions(final @NonNull Pose pose) {
        return this.isBaby() ? more_babies$BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

}
